package com.minecolonies.api.citizen;

import com.minecolonies.api.colony.IColony;
import com.minecolonies.api.entity.pathfinding.PathNavigate;
import com.minecolonies.api.job.IJob;
import com.minecolonies.skeleton.colony.building.AbstractBuildingWorker;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.INpc;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.UUID;

/**
 * ------------ Class not Documented ------------
 */
public interface IEntityCitizen extends ICommandSender, ICapabilitySerializable<NBTTagCompound>, INpc
{
    @Nullable
    IJob getColonyJob();

    /**
     * Defines job changes and state changes of the citizen.
     *
     * @param job the set job.
     */
    void onJobChanged(@Nullable IJob job);

    int getLevel();

    void setRenderMetadata(String metadata);

    /**
     * calculate this workers building.
     *
     * @return the building or null if none present.
     */
    @Nullable
    AbstractBuildingWorker getWorkBuilding();

    CitizenStatus getStatus();

    void setStatus(CitizenStatus status);

    /**
     * On Inventory change, mark the building dirty.
     */
    void onInventoryChanged();

    /**
     * Checks if a worker is at his working site.
     * If he isn't, sets it's path to the location
     *
     * @param site  the place where he should walk to
     * @param range Range to check in
     * @return True if worker is at site, otherwise false.
     */
    boolean isWorkerAtSiteWithMove(@NotNull BlockPos site, int range);

    /**
     * Get the job of the citizen.
     *
     * @param type of the type.
     * @param <J>  wildcard.
     * @return the job.
     */
    @Nullable
    <J extends IJob> J getColonyJob(@NotNull Class<J> type);

    /**
     * Change the citizens Rotation to look at said block.
     *
     * @param block the block he should look at.
     */
    void faceBlock(@Nullable BlockPos block);

    /**
     * Collect exp orbs around the entity.
     */
    void gatherXp();

    /**
     * Add experience points to citizen.
     * Increases the citizen level if he has sufficient experience.
     * This will reset the experience.
     *
     * @param xp the amount of points added.
     */
    void addExperience(double xp);

    /**
     * ExperienceLevel getter.
     *
     * @return citizen ExperienceLevel value.
     */
    int getExperienceLevel();

    boolean attackEntityFrom(@NotNull DamageSource damageSource, float damage);

    /**
     * Called when the mob's health reaches 0.
     *
     * @param par1DamageSource the attacking entity.
     */
    void onDeath(DamageSource par1DamageSource);

    @Nullable
    IColony getColony();

    /**
     * Getter for the last job.
     *
     * @return the last job he had.
     */
    @NotNull
    String getLastJob();

    /**
     * Sets the last job of the citizen.
     *
     * @param jobName the job he last had.
     */
    void setLastJob(@NotNull String jobName);

    /**
     * Getter of the citizens random object.
     *
     * @return random object.
     */
    Random getRandom();

    @NotNull
    PathNavigate getNavigator();

    void setLocationAndAngles(double x, double y, double z, float yaw, float pitch);

    float getRotationYaw();

    float getRotationPith();

    float getHealth();

    float getMaxHealth();

    EntityAITasks getTasks();

    /**
     * Return this citizens inventory.
     *
     * @return the inventory this citizen has.
     */
    @NotNull
    IInventory getInventoryCitizen();

    /**
     * Handles the dropping of items from the entity.
     *
     * @param itemstack to drop.
     * @return the dropped item.
     */
    EntityItem entityDropItem(@NotNull ItemStack itemstack);

    /**
     * Getter of the resource location of the texture.
     *
     * @return location of the texture.
     */
    ResourceLocation getTexture();

    /**
     * Getter which checks if the citizen is female.
     *
     * @return true if female.
     */
    boolean isFemale();

    /**
     * Clears the colony of the citizen.
     */
    void clearColony();

    boolean isAtHome();

    int getEntityId();

    UUID getUniqueID();
}
