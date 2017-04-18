package com.minecolonies.api.citizen;

import com.minecolonies.api.colony.IColony;
import com.minecolonies.api.colony.building.IBuilding;
import com.minecolonies.api.job.IJob;
import com.minecolonies.skeleton.colony.building.AbstractBuildingWorker;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * ------------ Class not Documented ------------
 */
public interface ICitizenData
{
    /**
     * Reads data from NBT-tag compound.
     *
     * @param compound NBT-Tag compound.
     */
    void readFromNBT(@NotNull NBTTagCompound compound);

    /**
     * Return the entity instance of the citizen data. Respawn the citizen if needed.
     *
     * @return {@link IEntityCitizen} of the citizen data.
     */
    @Nullable
    IEntityCitizen getCitizenEntity();

    /**
     * Sets the entity of the citizen data.
     *
     * @param citizen {@link IEntityCitizen} instance of the citizen data.
     */
    void setCitizenEntity(IEntityCitizen citizen);

    /**
     * Marks the instance dirty.
     */
    void markDirty();

    /**
     * Create a CitizenData given a CitizenEntity.
     *
     * @param entity Entity to initialize from.
     */
    void initializeFromEntity(@NotNull IEntityCitizen entity);

    /**
     * Returns the id of the citizen.
     *
     * @return id of the citizen.
     */
    int getId();

    /**
     * Returns the colony of the citizen.
     *
     * @return colony of the citizen.
     */
    IColony getColony();

    /**
     * Returns the name of the citizen.
     *
     * @return name of the citizen.
     */
    String getName();

    /**
     * Returns true if citizen is female, false for male.
     *
     * @return true for female, false for male.
     */
    boolean isFemale();

    /**
     * Returns the texture id for the citizen.
     *
     * @return texture ID.
     */
    int getTextureId();

    /**
     * Adds experience of the citizen.
     *
     * @param xp the amount of xp to add.
     */
    void addExperience(double xp);

    /**
     * Sets the level of the citizen.
     */
    void increaseLevel();

    /**
     * Returns whether or not the instance is dirty.
     *
     * @return true when dirty, otherwise false.
     */
    boolean isDirty();

    /**
     * Markt the instance not dirty.
     */
    void clearDirty();

    /**
     * When a building is destroyed, inform the citizen so it can do any cleanup of associations that the building's.
     * own AbstractBuilding.onDestroyed did not do.
     *
     * @param building building that is destroyed.
     */
    void onRemoveBuilding(IBuilding building);

    /**
     * Returns the home building of the citizen.
     *
     * @return home building.
     */
    @Nullable
    IBuilding getHomeBuilding();

    /**
     * Sets the home of the citizen.
     *
     * @param building home building.
     */
    void setHomeBuilding(@Nullable IBuilding building);

    /**
     * Returns the work building of a citizen.
     *
     * @return home building of a citizen.
     */
    @Nullable
    AbstractBuildingWorker getWorkBuilding();

    /**
     * Sets the work building of a citizen.
     *
     * @param building work building.
     */
    void setWorkBuilding(@Nullable AbstractBuildingWorker building);

    /**
     * Sets {@link IEntityCitizen} to null for the instance.
     */
    void clearCitizenEntity();

    /**
     * Returns the job of the citizen.
     *
     * @return Job of the citizen.
     */
    IJob getJob();

    /**
     * Sets the job of this citizen.
     *
     * @param job Job of the citizen.
     */
    void setJob(IJob job);

    /**
     * Returns the job subclass needed. Returns null on type mismatch.
     *
     * @param type the type of job wanted.
     * @param <J>  The job type returned.
     * @return the job this citizen has.
     */
    @Nullable
    <J extends IJob> J getJob(@NotNull Class<J> type);

    /**
     * Writes the citiizen data to an NBT-compound.
     *
     * @param compound NBT-Tag compound.
     */
    void writeToNBT(@NotNull NBTTagCompound compound);

    /**
     * Writes the citizen data to a byte buf for transition.
     *
     * @param buf Buffer to write to.
     */
    void serializeViewNetworkData(@NotNull ByteBuf buf);

    /**
     * Returns the level of the citizen.
     *
     * @return level of the citizen.
     */
    int getLevel();

    /**
     * Sets the level of the citizen.
     *
     * @param lvl the new level for the citizen.
     */
    void setLevel(int lvl);

    /**
     * Resets the experience and the experience level of the citizen.
     */
    void resetExperienceAndLevel();

    /**
     * Returns the experience of the citizen.
     *
     * @return experience of the citizen.
     */
    double getExperience();

    /**
     * Strength getter.
     *
     * @return citizen Strength value.
     */
    int getStrength();

    /**
     * Endurance getter.
     *
     * @return citizen Endurance value.
     */
    int getEndurance();

    /**
     * Charisma getter.
     *
     * @return citizen Charisma value.
     */
    int getCharisma();

    /**
     * Intelligence getter.
     *
     * @return citizen Intelligence value.
     */
    int getIntelligence();

    /**
     * Dexterity getter.
     *
     * @return citizen Dexterity value.
     */
    int getDexterity();
}
