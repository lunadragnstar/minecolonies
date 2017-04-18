package com.minecolonies.api.citizen.lumberjack.tree;

import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

/**
 * Interface describing a Manager that handles most of the Interaction between the LumberJack and its Trees
 *
 * Classes that implement this interface should have a default public constructor used for registration.
 * @param <T> The type of Tree that this manager manages.
 */
public interface ITreeManager<T extends ITree>
{
    /**
     * Method to get the class of the tree that is used.
     * @return
     */
    @Nonnull
    Class<T> getTreeType();

    /**
     * Searches all logs that belong to the tree.
     *
     * @param world The world where the blocks are in.
     */
    void findLogs(@NotNull World world, @Nonnull T tree);

    /**
     * Checks if the tree has been planted from more than 1 saplings.
     * Meaning that more than 1 log is on the lowest level.
     *
     * @param yLevel The base y.
     */
    void fillTreeStumps(int yLevel, @Nonnull T tree);
}
