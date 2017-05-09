package com.minecolonies.coremod.util.transactions.processing;

import com.minecolonies.coremod.util.Log;
import com.minecolonies.coremod.util.transactions.Transaction;
import com.minecolonies.coremod.util.transactions.TransactionPhase;
import com.minecolonies.coremod.util.transactions.TransactionResult;
import org.jetbrains.annotations.NotNull;

/**
 * Performs a transaction on request.
 */
public final class TransactionProcessor<T>
{

    private final Transaction<T> transaction;

    private final ITransactionProcessingStateChangeCallback transactionProcessingStateChangeCallback;

    public TransactionProcessor(
                                 final Transaction<T> transaction,
                                 final ITransactionProcessingStateChangeCallback transactionProcessingStateChangeCallback)
    {
        this.transaction = transaction;
        this.transactionProcessingStateChangeCallback = transactionProcessingStateChangeCallback;
    }

    public TransactionResult<T> execute()
    {
        return null;
    }

    private void transitionToState(@NotNull TransactionPhase newPhase)
    {
        transactionProcessingStateChangeCallback.apply(transaction.getPhase(), transaction, newPhase);
        transaction.setPhase(newPhase);
    }

    private TransactionResult<T> setResultOnTransaction(@NotNull TransactionResult<T> result)
    {
        transaction.setResultForCurrentPhase(result);
        return result;
    }

    private TransactionResult<T> performSetup()
    {
        transitionToState(TransactionPhase.SETUP);

        //No setup yet. The callback might perform something, but that might be situation dependent.
        return setResultOnTransaction(TransactionResult.getNotExecuted());
    }

    private TransactionResult<T> performForwardTransaction()
    {
        //Transition
        transitionToState(TransactionPhase.FORWARDSEXTRACTION);

        //Extract the object from the source
        TransactionResult<T> sourceExtractionResult = transaction.getExtractionTransactionHandler().performExtractionTransaction(transaction.getFrom(), transaction.getAmount());

        //Check if the transaction was successful or not
        if (!sourceExtractionResult.isSuccessful())
        {
            //Transaction seems to have failed for some reason no further processing.
            return TransactionResult.getFailed();
        }

        //Extraction transaction successful, setting the transaction result and the original.
        transaction.setOriginal(sourceExtractionResult.getTransactionResult());
        transaction.setCurrentResult(sourceExtractionResult);

        //Insert the object into the target.
        TransactionResult<T> targetInsertionResult = transaction.getInsertionTransactionHandler().performInsertionTransaction(transaction.getInto(), transaction.getOriginal());

        //Insertion was not possible, yet original is set.
        if (!targetInsertionResult.isSuccessful())
        {
            //Undo the extraction
            TransactionResult<T> undoExtractionResult = transaction.getExtractionTransactionHandler().performInsertionTransaction(transaction.getFrom(), transaction.getOriginal());

            if (!undoExtractionResult.isSuccessful())
            {
                Log.bigWarning("Failed to undo a Transaction!");
                return TransactionResult.getFailed();
            }
        }

        //Set the transaction result to continue processing.
        transaction.setCurrentResult(targetInsertionResult);
        return targetInsertionResult;
    }

    private TransactionResult<T> performBackwardTransaction()
    {
        //Transition
        transitionToState(TransactionPhase.BACKWARDS);

        if ((transaction.getCurrentResult().isPartial() || transaction.getCurrentResult().isReplaced()) && transaction.getCurrentResult().getTransactionResult() != null)
        {
            TransactionResult<T> forwardResultProcessingTransaction = transaction.getExtractionTransactionHandler().performInsertionTransaction(transaction.getFrom(), transaction.getCurrentResult().getTransactionResult());

            //Checking the backwards insertion for success.
            if (!forwardResultProcessingTransaction.isSuccessful())
            {
                return TransactionResult.getFailed();
            }

            //We have a problem if the source handler does not accept the result from the insertion handler. Log it.
            if (forwardResultProcessingTransaction.isPartial() || forwardResultProcessingTransaction.isReplaced())
            {
                Log.bigWarning("A backwards insertion only completed partially");
            }

            transaction.setCurrentResult(forwardResultProcessingTransaction);
            return forwardResultProcessingTransaction;
        }

        return transaction.getCurrentResult();
    }

    private TransactionResult<T> performSuccessfulTransaction()
    {
        transitionToState(TransactionPhase.SUCCESSFUL);

        transaction.setCurrentResult(TransactionResult.getSuccesfull());

        return transaction.getCurrentResult();
    }

    private TransactionResult<T> performForwardsUndoTransaction()
    {
        //Transition
        transitionToState(TransactionPhase.FORWARDSUNDO);


    }
}
