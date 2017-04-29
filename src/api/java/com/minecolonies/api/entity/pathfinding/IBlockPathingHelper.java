package com.minecolonies.api.entity.pathfinding;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * ------------ Class not Documented ------------
 */
public interface IBlockPathingHelper
{

    /**
     * Generates a good path starting location for the entity to path from, correcting for the following conditions.
     * <p>
     * Example usages:
     * - Being in water: pathfinding in water occurs along the surface; adjusts position to surface.
     * - Being in a fence space: finds correct adjacent position which is not a fence space, to prevent starting path.
     * from within the fence block.
     *
     * @param current      The current {@link net.minecraft.util.math.BlockPos.MutableBlockPos} that the pathing will start from.
     * @param currentState The current  {@link IBlockState} that is the Blockstate of the starting Block.
     * @param entityLiving The {@link EntityLiving} that is a Path is about to be found for.
     * @return The new starting position.
     */
    @NotNull
    default BlockPos.MutableBlockPos getStartPosition(@NotNull BlockPos.MutableBlockPos current, @NotNull IBlockState currentState, @NotNull EntityLiving entityLiving)
    {
        return current;
    }

    /**
     * Method used to get the surface type of the top side of a Block.
     *
     * @param blockState {@link IBlockState} to check for.
     * @return an appropriate {@link SurfaceType} that can represents the block in the pathfinding, or null if this helper cannot answer the question.
     */
    @Nullable
    default SurfaceType getSurfaceTypeForBlock(@NotNull IBlockState blockState)
    {
        return null;
    }

    @Nullable
    default Boolean isPassable(@NotNull final IBlockState block)
    {
        return null;
    }
}
