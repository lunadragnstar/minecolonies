package com.minecolonies.coremod.util.transactions.handlers;

import com.minecolonies.coremod.util.transactions.TransactionResult;

/**
 * An Object that implements this interface works as a Transactionhandler that can be used as both insertion and extraction handler.
 * There for it can support Undoes.
 *
 * @param <T> The type that this transaction handler handles.
 */
public interface IByDirectionalTransactionHandler<T>
{
    /**
     * Method called to handle an insertion transaction.
     *
     * @param index                      The index of the handler to insert into.
     * @param insertionTransactionObject An object of type {@link T} that is being inserted.
     * @return The result of the Transaction.
     */
    TransactionResult<T> performInsertionTransaction(int index, T insertionTransactionObject);

    /**
     * Method called to handle an extraction transaction.
     *
     * @param index  The index of the handler to extract from.
     * @param amount The amount to extract.
     * @return The result of the Transaction.
     */
    TransactionResult<T> performExtractionTransaction(int index, int amount);
}
