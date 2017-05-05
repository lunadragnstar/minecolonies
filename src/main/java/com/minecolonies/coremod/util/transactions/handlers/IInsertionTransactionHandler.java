package com.minecolonies.coremod.util.transactions.handlers;

import com.minecolonies.coremod.util.transactions.TransactionResult;

/**
 * A {@link IInsertionTransactionHandler} is a Object that can handle incoming transactions of the {@link T}
 *
 * @param <T> The type that this transaction handler accepts.
 */
public interface IInsertionTransactionHandler<T>
{

    /**
     * Method called to handle an insertion transaction.
     *
     * @param index                      The index of the handler to insert into.
     * @param insertionTransactionObject An object of type {@link T} that is being inserted.
     * @return The result of the Transaction.
     */
    TransactionResult<T> performInsertionTransaction(int index, T insertionTransactionObject);
}
