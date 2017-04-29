package com.minecolonies.coremod.entity.pathfinding;

import com.minecolonies.api.entity.pathfinding.AbstractPathJob;
import com.minecolonies.api.entity.pathfinding.IBlockPathingHelper;
import com.minecolonies.api.entity.pathfinding.SurfaceType;
import com.minecolonies.coremod.blocks.BlockConstructionTape;
import com.minecolonies.coremod.blocks.BlockConstructionTapeCorner;
import com.minecolonies.coremod.blocks.BlockHutField;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.BlockFluidBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Pathing Helpers used to handle block specific pathing issues.
 */
public final class BlockPathingHelper
{

    public static void init()
    {
        AbstractPathJob.helpers.add(new FluidPathingHelper());
        AbstractPathJob.helpers.add(new FenceLikePathingHelper());
    }

    private BlockPathingHelper()
    {
        throw new IllegalStateException("Utility Class");
    }

    public static final class FluidPathingHelper implements IBlockPathingHelper
    {

        /**
         * Generates a good path starting location for the entity to path from, correcting for the following conditions.
         * <p>
         * Example usages:
         * - Being in water: pathfinding in water occurs along the surface; adjusts position to surface.
         * - Being in a fence space: finds correct adjacent position which is not a fence space, to prevent starting path.
         * from within the fence block.
         *
         * @param current      The current {@link BlockPos.MutableBlockPos} that the pathing will start from.
         * @param currentState The current  {@link IBlockState} that is the Blockstate of the starting Block.
         * @param entityLiving The {@link EntityLiving} that is a Path is about to be found for.
         * @return The new starting position.
         */
        @Override
        public BlockPos.MutableBlockPos getStartPosition(
                                                          @NotNull final BlockPos.MutableBlockPos current,
                                                          @NotNull IBlockState currentState,
                                                          @NotNull final EntityLiving entityLiving)
        {
            if (currentState.getBlock() instanceof BlockFluidBase)
            {
                while (currentState.getMaterial().isLiquid())
                {
                    current.setPos(current.getX(), current.getY() + 1, current.getZ());
                    currentState = entityLiving.worldObj.getBlockState(current);
                }
            }

            return current;
        }

        @Nullable
        @Override
        public Boolean isPassable(@NotNull final IBlockState block)
        {
            if (block.getMaterial().isLiquid())
            {
                return false;
            }

            return null;
        }
    }

    public static final class FenceLikePathingHelper implements IBlockPathingHelper
    {

        /**
         * Distance which is considered to be too close to a fence.
         */
        private static final double TOO_CLOSE_TO_FENCE = 0.1D;

        /**
         * Distance which is considered to be too far from a fence.
         */
        private static final double TOO_FAR_FROM_FENCE = 0.9D;

        /**
         * Generates a good path starting location for the entity to path from, correcting for the following conditions.
         * <p>
         * Example usages:
         * - Being in water: pathfinding in water occurs along the surface; adjusts position to surface.
         * - Being in a fence space: finds correct adjacent position which is not a fence space, to prevent starting path.
         * from within the fence block.
         *
         * @param current      The current {@link BlockPos.MutableBlockPos} that the pathing will start from.
         * @param currentState The current  {@link IBlockState} that is the Blockstate of the starting Block.
         * @param entityLiving The {@link EntityLiving} that is a Path is about to be found for.
         * @return The new starting position.
         */
        @Override
        @NotNull
        public BlockPos.MutableBlockPos getStartPosition(
                                                          @NotNull final BlockPos.MutableBlockPos current,
                                                          @NotNull final IBlockState currentState,
                                                          @NotNull final EntityLiving entityLiving)
        {
            Block b = currentState.getBlock();

            if (b instanceof BlockFence || b instanceof BlockWall || b instanceof BlockHutField)
            {
                //Push away from fence
                final double dX = entityLiving.posX - Math.floor(entityLiving.posX);
                final double dZ = entityLiving.posZ - Math.floor(entityLiving.posZ);

                if (dX < TOO_CLOSE_TO_FENCE)
                {
                    current.setPos(current.getX() - 1, current.getY(), current.getZ());
                }
                else if (dX > TOO_FAR_FROM_FENCE)
                {
                    current.setPos(current.getX() + 1, current.getY(), current.getZ());
                }

                if (dZ < TOO_CLOSE_TO_FENCE)
                {
                    current.setPos(current.getX(), current.getY(), current.getZ() - 1);
                }
                else if (dZ > TOO_FAR_FROM_FENCE)
                {
                    current.setPos(current.getX(), current.getY(), current.getZ() + 1);
                }
            }

            return current;
        }

        /**
         * Method used to get the surface type of the top side of a Block.
         *
         * @param blockState {@link IBlockState} to check for.
         * @return an appropriate {@link SurfaceType} that can represents the block in the pathfinding.
         */
        @Nullable
        @Override
        public SurfaceType getSurfaceTypeForBlock(@NotNull final IBlockState blockState)
        {
            if (blockState.getBlock() instanceof BlockFence
                  || blockState.getBlock() instanceof BlockFenceGate
                  || blockState.getBlock() instanceof BlockWall
                  || blockState.getBlock() instanceof BlockHutField)
            {
                return SurfaceType.NOT_PASSABLE;
            }

            return null;
        }

        @Nullable
        @Override
        public Boolean isPassable(@NotNull final IBlockState block)
        {
            if (block.getMaterial().blocksMovement())
            {
                return (block.getBlock() instanceof BlockDoor
                          || block.getBlock() instanceof BlockFenceGate
                          || block.getBlock() instanceof BlockConstructionTape
                          || block.getBlock() instanceof BlockConstructionTapeCorner ? false : null);
            }

            return null;
        }
    }

    public static final class ConstructionTapePathingHelper implements IBlockPathingHelper
    {
        /**
         * Method used to get the surface type of the top side of a Block.
         *
         * @param blockState {@link IBlockState} to check for.
         * @return an appropriate {@link SurfaceType} that can represents the block in the pathfinding, or null if this helper cannot answer the question.
         */
        @Override
        public SurfaceType getSurfaceTypeForBlock(@NotNull final IBlockState blockState)
        {
            if (blockState.getBlock() instanceof BlockConstructionTape || blockState.getBlock() instanceof BlockConstructionTapeCorner)
            {
                return SurfaceType.DROPABLE;
            }

            return null;
        }
    }

    public static final class SolidBlockPathingHelper implements IBlockPathingHelper
    {
        /**
         * Method used to get the surface type of the top side of a Block.
         *
         * @param blockState {@link IBlockState} to check for.
         * @return an appropriate {@link SurfaceType} that can represents the block in the pathfinding, or null if this helper cannot answer the question.
         */
        @Nullable
        @Override
        public SurfaceType getSurfaceTypeForBlock(@NotNull final IBlockState blockState)
        {
            if (blockState.getMaterial().isSolid())
            {
                return SurfaceType.WALKABLE;
            }

            return null;
        }
    }
}
