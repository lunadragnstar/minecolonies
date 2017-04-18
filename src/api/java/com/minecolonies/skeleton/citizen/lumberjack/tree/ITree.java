package com.minecolonies.skeleton.citizen.lumberjack.tree;

import com.minecolonies.api.citizen.lumberjack.tree.ITree;
import com.minecolonies.api.util.BlockPosUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Interface describing a tree for the lumberjack.
 *
 * Classes implementing this interface should have a default public constructor so that they can be read from disk.
 */
public abstract class SkeletonTree implements ITree
{
    /**
     * All wood blocks connected to the tree.
     */
    private LinkedList<BlockPos> woodBlocks;

    /**
     * All leaves of the tree.
     */
    private LinkedList<BlockPos> leaves;

    /**
     * The stumplocations of this tree.
     */
    private List<BlockPos> stumpLocations;

    /**
     * The position of the Tree.
     */
    private BlockPos location;

    /**
     * Returns the next log block.
     *
     * @return the position.
     */
    @Override
    public BlockPos pollNextLog()
    {
        return woodBlocks.pollLast();
    }

    /**
     * Returns the next leaf block.
     *
     * @return the position.
     */
    @Override
    public BlockPos pollNextLeaf()
    {
        return leaves.pollLast();
    }

    /**
     * Looks up the next log block.
     *
     * @return the position.
     */
    @Override
    public BlockPos peekNextLog()
    {
        return woodBlocks.peekLast();
    }

    /**
     * Looks up the next leaf block.
     *
     * @return the position.
     */
    @Override
    public BlockPos peekNextLeaf()
    {
        return leaves.peekLast();
    }

    /**
     * Check if the found tree has any leaves.
     *
     * @return true if there are leaves associated with the tree.
     */
    @Override
    public boolean hasLeaves()
    {
        return !leaves.isEmpty();
    }

    /**
     * Check if the found tree has any logs.
     *
     * @return true if there are wood blocks associated with the tree.
     */
    @Override
    public boolean hasLogs()
    {
        return !woodBlocks.isEmpty();
    }

    /**
     * All stump positions of a tree (A tree may have been planted with different saplings).
     *
     * @return an Arraylist of the positions.
     */
    @Override
    @NotNull
    public List<BlockPos> getStumpLocations()
    {
        return new ArrayList<>(stumpLocations);
    }

    /**
     * Removes a stump from the stump list.
     *
     * @param pos the position of the stump.
     */
    @Override
    public void removeStump(final BlockPos pos)
    {
        stumpLocations.remove(pos);
    }

    /**
     * Method called when the tree has been chopped to determine if is a valid sapling to be replaced.
     *
     * @param stack The stack in the inventory of the LumberJack to check.
     * @return True when the stack is matching the sapling of this tree.
     */
    @Override
    public abstract boolean isItemStackMatching(final ItemStack stack);

    /**
     * Calculates the squareDistance to another Tree.
     *
     * @param other the other tree.
     * @return the square distance in double.
     */
    @Override
    public double squareDistance(@NotNull final ITree other)
    {
        return this.getLocation().distanceSq(other.getLocation());
    }

    /**
     * Returns the trees location.
     *
     * @return the position.
     */
    @Override
    public BlockPos getLocation()
    {
        return location;
    }

    /**
     * Method used to check if a given Object is equal to this {@link SkeletonTree}
     * @param o The object to check for.
     * @return True when the tree is in the same position as this tree.
     */
    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof ITree))
        {
            return false;
        }

        final SkeletonTree that = (SkeletonTree) o;

        return getLocation().equals(that.getLocation());
    }

    /**
     * Method to get the hashcode for this {@link SkeletonTree}
     * @return The same hashcode as {@link #getLocation()#hashCode}
     */
    @Override
    public int hashCode()
    {
        return getLocation().hashCode();
    }

    /**
     * Method used to serialize this tree.
     * @return
     */
    @Override
    public NBTTagCompound serializeNBT()
    {
        BlockPosUtil.writeToNBT(compound, TAG_LOCATION, location);

        @NotNull final NBTTagList logs = new NBTTagList();
        for (@NotNull final BlockPos log : woodBlocks)
        {
            BlockPosUtil.writeToNBTTagList(logs, log);
        }
        compound.setTag(TAG_LOGS, logs);

        @NotNull final NBTTagList stumps = new NBTTagList();
        for (@NotNull final BlockPos stump : stumpLocations)
        {
            BlockPosUtil.writeToNBTTagList(stumps, stump);
        }
        compound.setTag(TAG_STUMPS, stumps);

        BlockPosUtil.writeToNBT(compound, TAG_TOP_LOG, topLog);
    }

    /**
     * Method used to update the data in this tree with the data in the given {@link NBTTagCompound}
     * @param nbt The compound to read from.
     */
    @Override
    public void deserializeNBT(final NBTTagCompound nbt)
    {

    }
}
