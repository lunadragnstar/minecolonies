package com.minecolonies.api.citizen.lumberjack.tree;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.jetbrains.annotations.NotNull;

/**
 * ------------ Class not Documented ------------
 */
public interface ITreeManagerController
{

    /**
     * Method used to check if a given {@link BlockPos} in a given {@link IBlockAccess} is a Tree
     *
     * @param world The {@link IBlockAccess} to check in.
     * @param pos   The {@link BlockPos} to check for.
     * @return True when a tree can be found, false when not.
     */
    boolean isTree(@NotNull IBlockAccess world, @NotNull BlockPos pos);

    /**
     * Method used to register a new tree manager.
     *
     * @param manager The tree manager to register.
     */
    void registerManager(@NotNull ITreeManager manager);
}
