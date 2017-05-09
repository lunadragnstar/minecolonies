package com.minecolonies.coremod.util.transactions;

import org.jetbrains.annotations.Nullable;

/**
 * A class indicating a Transaction result.
 *
 * @param <T> The
 */
public class TransactionResult<T>
{

    private final boolean executed;

    private final boolean successful;

    private final boolean partial;

    private final boolean failed;

    private final boolean replaced;

    private final T transactionResult;

    private TransactionResult(final boolean executed, final boolean successful, final boolean partial, final boolean failed, final boolean replaced, final T transactionResult)
    {
        this.executed = executed;
        this.successful = successful;
        this.partial = partial;
        this.failed = failed;
        this.replaced = replaced;
        this.transactionResult = transactionResult;
    }

    public boolean isExecuted()
    {
        return executed;
    }

    public boolean isSuccessful()
    {
        return successful;
    }

    public boolean isPartial()
    {
        return partial;
    }

    public boolean isFailed()
    {
        return failed;
    }

    public boolean isReplaced()
    {
        return replaced;
    }

    @Nullable
    public T getTransactionResult()
    {
        return transactionResult;
    }

    public static <T> TransactionResult<T> getNotExecuted()
    {
        return new TransactionResult<>(false, false, false, false, false, null);
    }

    public static <T> TransactionResult<T> getSuccesfull()
    {
        return new TransactionResult<>(true, true, false, false, false, null);
    }

    public static <T> TransactionResult<T> getPartial(T leftOver)
    {
        return new TransactionResult<>(true, true, true, false, false, leftOver);
    }

    public static <T> TransactionResult<T> getFailed()
    {
        return new TransactionResult<>(true, false, false, true, false, null);
    }

    public static <T> TransactionResult<T> getReplaced(T replaced)
    {
        return new TransactionResult<>(true, true, false, false, true, replaced);
    }
}
