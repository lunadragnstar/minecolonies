package com.minecolonies.coremod.util.transactions;

import com.minecolonies.coremod.util.transactions.handlers.IByDirectionalTransactionHandler;
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
     * Holds the {@link IByDirectionalTransactionHandler} which is used as the target for the transaction.
     */
    @NotNull
    private final IByDirectionalTransactionHandler<T> insertionTransactionHandler;

    /**
     * Holds the index of the slot to insert into.
     */
    @NotNull
    private final int into;

    /**
     * Holds the {@link IByDirectionalTransactionHandler} which is used as the source for the transaction.
     */
    @NotNull
    private final IByDirectionalTransactionHandler<T> extractionTransactionHandler;

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
    private T original;

    @NotNull
    private TransactionResult<T> currentResult;

    public Transaction(
                        @NotNull final IByDirectionalTransactionHandler<T> insertionTransactionHandler,
                        @NotNull final int into,
                        @NotNull final IByDirectionalTransactionHandler<T> extractionTransactionHandler,
                        @NotNull final int from,
                        @NotNull final int amount)
    {
        this.insertionTransactionHandler = insertionTransactionHandler;
        this.into = into;
        this.extractionTransactionHandler = extractionTransactionHandler;
        this.from = from;
        this.amount = amount;
        this.currentResult = TransactionResult.getNotExecuted(this.original);
    }

    @NotNull
    public TransactionPhase getPhase()
    {
        return phase;
    }

    @NotNull
    public IByDirectionalTransactionHandler<T> getInsertionTransactionHandler()
    {
        return insertionTransactionHandler;
    }

    @NotNull
    public int getInto()
    {
        return into;
    }

    @NotNull
    public IByDirectionalTransactionHandler<T> getExtractionTransactionHandler()
    {
        return extractionTransactionHandler;
    }

    @NotNull
    public int getFrom()
    {
        return from;
    }

    @NotNull
    public int getAmount()
    {
        return amount;
    }

    public T getOriginal()
    {
        return original;
    }

    public void setOriginal(final T original)
    {
        this.original = original;
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
}
