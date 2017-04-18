package com.minecolonies.api.citizen.lumberjack.tree;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Interface describing a tree for the lumberjack.
 *
 * Classes implementing this interface should have a default public constructor so that they can be read from disk.
 */
public interface ITree extends INBTSerializable<NBTTagCompound>
{
    /**
     * Returns the next log block.
     *
     * @return the position.
     */
    BlockPos pollNextLog();

    /**
     * Returns the next leaf block.
     *
     * @return the position.
     */
    BlockPos pollNextLeaf();

    /**
     * Looks up the next log block.
     *
     * @return the position.
     */
    BlockPos peekNextLog();

    /**
     * Looks up the next leaf block.
     *
     * @return the position.
     */
    BlockPos peekNextLeaf();

    /**
     * Check if the found tree has any leaves.
     *
     * @return true if there are leaves associated with the tree.
     */
    boolean hasLeaves();

    /**
     * Check if the found tree has any logs.
     *
     * @return true if there are wood blocks associated with the tree.
     */
    boolean hasLogs();

    /**
     * All stump positions of a tree (A tree may have been planted with different saplings).
     *
     * @return an Arraylist of the positions.
     */
    @NotNull
    List<BlockPos> getStumpLocations();

    /**
     * Removes a stump from the stump list.
     *
     * @param pos the position of the stump.
     */
    void removeStump(BlockPos pos);

    /**
     * Method called when the tree has been chopped to determine if is a valid sapling to be replaced.
     * @param stack The stack in the inventory of the LumberJack to check.
     * @return True when the stack is matching the sapling of this tree.
     */
    boolean isItemStackMatching(ItemStack stack);

    /**
     * Calculates the squareDistance to another Tree.
     *
     * @param other the other tree.
     * @return the square distance in double.
     */
    double squareDistance(@NotNull ITree other);

    /**
     * Returns the trees location.
     *
     * @return the position.
     */
    BlockPos getLocation();

    /**
     * Needed for the equals method.
     *
     * @return the hash code of the location.
     */
    @Override
    int hashCode();

    /**
     * Overridden equals method checks if the location of the both trees are equal.
     *
     * @param tree the object to compare.
     * @return true if equal or false if not.
     */
    @Override
    boolean equals(@Nullable Object tree);
}
