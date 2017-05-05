package com.minecolonies.coremod.util.transactions;

import com.minecolonies.coremod.util.transactions.handlers.IExtractionTransactionHandler;
import com.minecolonies.coremod.util.transactions.handlers.IInsertionTransactionHandler;
import org.jetbrains.annotations.NotNull;

/**
 * Represent a single Transaction that is being executed.
 */
public class Transaction<T>
{
    /**
     * Holds the current phase a transaction is in.
     */
    @NotNull
    private TransactionPhase phase = TransactionPhase.SETUP;

    /**
     * Holds the {@link IInsertionTransactionHandler} which is used as the target for the transaction.
     */
    @NotNull
    private final IInsertionTransactionHandler<T> insertionTransactionHandler;

    /**
     * Holds the index of the slot to insert into.
     */
    @NotNull
    private final int into;

    /**
     * Holds the {@link IExtractionTransactionHandler} which is used as the source for the transaction.
     */
    @NotNull
    private final IExtractionTransactionHandler<T> extractionTransactionHandler;

    /**
     * Holds the index of the slot to extract from;
     */
    @NotNull
    private final int from;

    /**
     * Holds the amount that is being extracted and inserted.
     */
    @NotNull
    private final int amount;

    /**
     * The original transaction object that is passed from the source to the target handler.
     */
    private final T original;

    @NotNull
    private TransactionResult<T> currentResult;

    public Transaction(
                        @NotNull final IInsertionTransactionHandler<T> insertionTransactionHandler,
                        @NotNull final int into,
                        @NotNull final IExtractionTransactionHandler<T> extractionTransactionHandler,
                        @NotNull final int from,
                        @NotNull final int amount,
                        final T original)
    {
        this.insertionTransactionHandler = insertionTransactionHandler;
        this.into = into;
        this.extractionTransactionHandler = extractionTransactionHandler;
        this.from = from;
        this.amount = amount;
        this.original = original;
        this.currentResult = TransactionResult.getNotExecuted(this.original);
    }

    @NotNull
    public TransactionPhase getPhase()
    {
        return phase;
    }

    @NotNull
    public IInsertionTransactionHandler<T> getInsertionTransactionHandler()
    {
        return insertionTransactionHandler;
    }

    @NotNull
    private int getInto()
    {
        return into;
    }

    @NotNull
    public IExtractionTransactionHandler<T> getExtractionTransactionHandler()
    {
        return extractionTransactionHandler;
    }

    @NotNull
    private int getFrom()
    {
        return from;
    }

    public T getOriginal()
    {
        return original;
    }

    @NotNull
    public TransactionResult<T> getCurrentResult()
    {
        return currentResult;
    }

    public void setPhase(@NotNull final TransactionPhase phase)
    {
        this.phase = phase;
    }

    public void setCurrentResult(@NotNull final TransactionResult<T> currentResult)
    {
        this.currentResult = currentResult;
    }

    private TransactionResult<T> performExtraction()
    {
        return getExtractionTransactionHandler().performExtractionTransaction(from);
    }
}
