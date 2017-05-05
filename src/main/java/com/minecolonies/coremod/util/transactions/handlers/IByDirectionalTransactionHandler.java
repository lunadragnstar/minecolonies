package com.minecolonies.coremod.util.transactions.handlers;

/**
 * An Object that implements this interface works as a Transactionhandler that can be used as both insertion and extraction handler.
 * There for it can support Undoes.
 *
 * @param <T> The type that this transaction handler handles.
 */
public interface IByDirectionalTransactionHandler<T> extends IInsertionTransactionHandler<T>, IExtractionTransactionHandler<T>
{
    //No methods
    //Just combines the insertion and extraction handlers so that undoes can be performed.
}
