package com.minecolonies.coremod.util.transactions.processing;

import com.minecolonies.coremod.util.transactions.Transaction;

/**
 * Exception fired when a phase of the processing failed.
 */
public class TransactionProcessingException extends Exception
{

    private final Transaction<?> transaction;

    public TransactionProcessingException(final Transaction<?> transaction, final String message)
    {
        super(message);
        this.transaction = transaction;
    }

    public TransactionProcessingException(final Transaction<?> transaction, final String message, final Throwable parent)
    {
        super(message, parent);
        this.transaction = transaction;
    }

    public Transaction<?> getTransaction()
    {
        return transaction;
    }
}
