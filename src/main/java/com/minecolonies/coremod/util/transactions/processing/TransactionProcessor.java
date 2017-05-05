package com.minecolonies.coremod.util.transactions.processing;

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
        transitionToState(TransactionPhase.SETUP);
    }

    private void transitionToState(@NotNull TransactionPhase newPhase)
    {
        transactionProcessingStateChangeCallback.apply(transaction.getPhase(), transaction, newPhase);
        transaction.setPhase(newPhase);
    }

    private TransactionResult<T> performSetup()
    {
        transitionToState(TransactionPhase.SETUP);

        //No setup yet. The callback might perform something, but that might be situation dependent.

        return TransactionResult.getNotExecuted(transaction.getOriginal());
    }

    private TransactionResult performForwardTransaction() throws TransactionProcessingException
    {
        transitionToState(TransactionPhase.FORWARDS);

        //Extract the transaction Object from the current source
        T transactionObject =
    }
}
