package com.minecolonies.coremod.util.transactions.handlers;

import com.minecolonies.coremod.util.transactions.TransactionResult;

/**
 * A {@link IInsertionTransactionHandler} is a Object that can handle outgoing transactions of the {@link T}
 *
 * @param <T> The type that this transaction handler accepts.
 */
public interface IExtractionTransactionHandler<T>
{

    /**
     * Method called to handle an extraction transaction.
     *
     * @param index  The index of the handler to extract from.
     * @param amount The amount to extract.
     * @return The result of the Transaction.
     */
    TransactionResult<T> performExtractionTransaction(int index, int amount);
}