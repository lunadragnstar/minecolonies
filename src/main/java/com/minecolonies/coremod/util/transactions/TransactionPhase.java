package com.minecolonies.coremod.util.transactions;

import com.minecolonies.coremod.util.transactions.handlers.IByDirectionalTransactionHandler;
import com.minecolonies.coremod.util.transactions.handlers.IInsertionTransactionHandler;

/**
 * Indicated in which phase the transaction currently is.
 */
public enum TransactionPhase
{
    /**
     * Setup for the transaction.
     */
    SETUP,

    /**
     * Phase to indicate the initial requested interaction.
     * <p>
     * EG:
     * Actually transfers the requested stack from the source handler to the target handler.
     */
    FORWARDS,

    /**
     * Phase to indicate the processing of the transaction result that comes from the {@link TransactionPhase#FORWARDS}.
     * <p>
     * If a {@link TransactionResult} with {@link TransactionResult#isSuccessful()} as {@code True} has been reported as result
     * in the {@link TransactionPhase#FORWARDS} then this phase is skipped.
     * <p>
     * If a {@link TransactionResult} with {@link TransactionResult#isPartial()} as {@code True} has been reported as result
     * in the {@link TransactionPhase#FORWARDS} then in this phase the {@link TransactionResult#getTransactionResult()} is being put into
     * the source handler to indicate that the target {@link IInsertionTransactionHandler} did accept the Transaction however it could not
     * accept the Object completely.
     * <p>
     * If a {@link TransactionResult} with {@link TransactionResult#isReplaced()} as {@code True} has been reported as result
     * in the {@link TransactionPhase#FORWARDS} then in this phase the {@link TransactionResult#getTransactionResult()} is being put into
     * the source handler to indicate that the target {@link IInsertionTransactionHandler} did accept the Transaction however it could not
     * accept the Object add all and replaced an Object that it had already in its storage.
     */
    BACKWARDS,

    /**
     * Phase to indicate that the processing has been completed and that the transaction is being cleaned up.
     * <p>
     * No failures are reported, if a failure is reported then this phase is never reached.
     */
    SUCCESSFUL,

    /**
     * Phase to indicate that the transaction could not be completed for some reason.
     * <p>
     * The transaction will ne undone.
     */
    FAILED,

    /**
     * Setup for the undo of the transaction.
     * <p>
     * An Undo is only supported if both the source and the target handler are an instance of {@link IByDirectionalTransactionHandler}
     */
    PREPROCESSINGUNDO,

    /**
     * Undos the {@link TransactionPhase#BACKWARDS} Phase of the transaction.
     * <p>
     * During an undo this is the forward phase and it undoes the {@link TransactionPhase#BACKWARDS} phase of a normal transaction,
     * as this was the last transaction phase that got executed.
     * <p>
     * If the {@link TransactionPhase#BACKWARDS} phase is never reached, this phase is skipped in the undo.
     * <p>
     * An Undo is only supported if both the source and the target handler are an instance of {@link IByDirectionalTransactionHandler}
     */
    FORWARDSUNDO,

    /**
     * Undos the {@link TransactionPhase#FORWARDS} Phase of the transaction.
     * <p>
     * During an undo this is the backwards phase and it undoes the {@link TransactionPhase#FORWARDS} phase of a normal transaction,
     * as this was the first transaction phase that got executed.
     * <p>
     * During an undo it will always be executed.
     * <p>
     * An Undo is only supported if both the source and the target handler are an instance of {@link IByDirectionalTransactionHandler}
     */
    BACKWARDSUNDO,

    /**
     * Phase indicating that a undo has been completed and used during a clean up of the undo.
     * <p>
     * An Undo is only supported if both the source and the target handler are an instance of {@link IByDirectionalTransactionHandler}
     */
    UNDONE,

    /**
     * Phase indicating that a transaction process has been completed.
     * <p>
     * It does not indicate success or failure.
     */
    COMPLETE
}
