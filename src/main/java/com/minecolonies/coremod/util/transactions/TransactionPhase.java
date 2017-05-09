package com.minecolonies.coremod.util.transactions;

import com.minecolonies.coremod.util.transactions.handlers.IByDirectionalTransactionHandler;

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
     * Phase used during the extraction of the original Object from its source.
     */
    FORWARDSEXTRACTION,

    /**
     * Phase used during the insertion of the original Object into the target
     */
    FORWARDSINSERTION,

    /**
     * Phase to indicate the processing of the transaction result that comes from the {@link TransactionPhase#FORWARDSEXTRACTION}.
     * 
     * If a {@link TransactionResult} with {@link TransactionResult#isSuccessful()} as {@code True} has been reported as result
     * in the {@link TransactionPhase#FORWARDSEXTRACTION} then this phase is skipped.
     * 
     * If a {@link TransactionResult} with {@link TransactionResult#isPartial()} as {@code True} has been reported as result
     * in the {@link TransactionPhase#FORWARDSEXTRACTION} then in this phase the {@link TransactionResult#getTransactionResult()} is being put into
     * the source handler to indicate that the target {@link IByDirectionalTransactionHandler} did accept the Transaction however it could not
     * accept the Object completely.
     * 
     * If a {@link TransactionResult} with {@link TransactionResult#isReplaced()} as {@code True} has been reported as result
     * in the {@link TransactionPhase#FORWARDSEXTRACTION} then in this phase the {@link TransactionResult#getTransactionResult()} is being put into
     * the source handler to indicate that the target {@link IByDirectionalTransactionHandler} did accept the Transaction however it could not
     * accept the Object add all and replaced an Object that it had already in its storage.
     */
    BACKWARDS,

    /**
     * Phase to indicate that the processing has been completed and that the transaction is being cleaned up.
     * 
     * No failures are reported, if a failure is reported then this phase is never reached.
     */
    SUCCESSFUL,

    /**
     * Phase to indicate that the transaction could not be completed for some reason.
     * 
     * The transaction will ne undone.
     */
    FAILED,

    /**
     * Setup for the undo of the transaction.
     * 
     * An Undo is only supported if both the source and the target handler are an instance of {@link IByDirectionalTransactionHandler}
     */
    PREPROCESSINGUNDO,

    /**
     * Undos the {@link TransactionPhase#BACKWARDS} Phase of the transaction.
     * 
     * During an undo this is the forward phase and it undoes the {@link TransactionPhase#BACKWARDS} phase of a normal transaction,
     * as this was the last transaction phase that got executed.
     * 
     * If the {@link TransactionPhase#BACKWARDS} phase is never reached, this phase is skipped in the undo.
     * 
     * An Undo is only supported if both the source and the target handler are an instance of {@link IByDirectionalTransactionHandler}
     */
    FORWARDSUNDO,

    /**
     * Undos the {@link TransactionPhase#FORWARDSEXTRACTION} Phase of the transaction.
     * 
     * During an undo this is the backwards phase and it undoes the {@link TransactionPhase#FORWARDSEXTRACTION} phase of a normal transaction,
     * as this was the first transaction phase that got executed.
     * 
     * During an undo it will always be executed.
     * 
     * An Undo is only supported if both the source and the target handler are an instance of {@link IByDirectionalTransactionHandler}
     */
    BACKWARDSUNDO,

    /**
     * Phase indicating that a undo has been completed and used during a clean up of the undo.
     * 
     * An Undo is only supported if both the source and the target handler are an instance of {@link IByDirectionalTransactionHandler}
     */
    UNDONE,

    /**
     * Phase indicating that a transaction process has been completed.
     * 
     * It does not indicate success or failure.
     */
    COMPLETE
}
