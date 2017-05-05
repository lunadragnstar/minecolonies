package com.minecolonies.coremod.util.transactions.processing;

import com.minecolonies.coremod.util.transactions.Transaction;
import com.minecolonies.coremod.util.transactions.TransactionPhase;

/**
 * Callback that gets called when a phase of a transaction has been finished.
 */
@FunctionalInterface
public interface ITransactionProcessingStateChangeCallback
{

    /**
     * Method called when the given {@link Transaction<T>} moves from the given old {@link TransactionPhase}
     * to the given new {@link TransactionPhase}.
     *
     * @param oldPhase    The old {@link TransactionPhase} that the given {@link Transaction<T>} was in.
     * @param transaction The {@link Transaction<T>} that is being transition from the given old {@link TransactionPhase} to the given new {@link TransactionPhase}
     * @param newPhase    The new {@link TransactionPhase} that the given {@link Transaction<T>} is being transition into.
     * @param <T>         The type of the {@link Transaction} that is being processed.
     */
    <T> void apply(TransactionPhase oldPhase, Transaction<T> transaction, TransactionPhase newPhase);
}
