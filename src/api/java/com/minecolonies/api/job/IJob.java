package com.minecolonies.api.job;

import com.minecolonies.api.citizen.ICitizenData;
import com.minecolonies.api.client.model.CitizenModel;
import com.minecolonies.api.colony.IColony;
import com.minecolonies.skeleton.ai.AbstractAISkeleton;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * ------------ Class not Documented ------------
 */
public interface IJob
{
        /**
     * Restore the Job from an NBTTagCompound.
     *
     * @param compound NBTTagCompound containing saved Job data.
     */
    void readFromNBT(@NotNull NBTTagCompound compound);

    /**
     * Return a Localization textContent for the Job.
     *
     * @return localization textContent String.
     */
    String getName();

    /**
     * Get the CitizenModel to use when the Citizen performs this job role.
     *
     * @return CitizenModel of the citizen.
     */
    CitizenModel getModel();

    /**
     * Get the CitizenData that this Job belongs to.
     *
     * @return CitizenData that owns this Job.
     */
    ICitizenData getCitizen();

    /**
     * Get the Colony that this Job is associated with (shortcut for getCitizen().getColony()).
     *
     * @return {@link IColony} of the citizen.
     */
    IColony getColony();

    /**
     * Save the Job to an NBTTagCompound.
     *
     * @param compound NBTTagCompound to save the Job to.
     */
    void writeToNBT(@NotNull NBTTagCompound compound);

    /**
     * Does the Job have _all_ the needed items.
     *
     * @return true if the Job has no needed items.
     */
    boolean isMissingNeededItem();

    /**
     * Get the list of items needed by the Job.
     *
     * @return List of items needed by the Job.
     */
    @NotNull
    List<ItemStack> getItemsNeeded();

    /**
     * Reset the items needed.
     */
    void clearItemsNeeded();

    /**
     * Add (or increment) an ItemStack to the items needed by the Job.
     * We're not comparing item damage values since i.e a damaged rod is the same as a normal rod for our purpose.
     *
     * @param stack Item+count needed to do the job.
     */
    void addItemNeeded(@NotNull ItemStack stack);

    /**
     * Remove a items from those required to do the Job.
     * We're not comparing item damage values since i.e a damaged rod is the same as a normal rod for our purpose.
     *
     * @param stack ItemStack (item+count) to remove from the list of needed items.
     * @return modified ItemStack with remaining items (or null).
     */
    @Nullable
    ItemStack removeItemNeeded(@NotNull ItemStack stack);

    /**
     * Override to add Job-specific AI tasks to the given EntityAITask list.
     *
     * @param tasks EntityAITasks list to add tasks to.
     */
    void addTasks(@NotNull EntityAITasks tasks);

    /**
     * Generate your AI class to register.
     *
     * @return your personal AI instance.
     */
    AbstractAISkeleton<? extends IJob> generateAI();

    /**
     * This method can be used to display the current status.
     * That a citizen is having.
     *
     * @return Small string to display info in name tag
     */
    String getNameTagDescription();

    /**
     * Used by the AI skeleton to change a citizens name.
     * Mostly used to update debugging information.
     *
     * @param nameTag The name tag to display.
     */
    void setNameTag(String nameTag);

    /**
     * Override this to let the worker return a bedTimeSound.
     *
     * @return soundEvent to be played.
     */
    SoundEvent getBedTimeSound();

    /**
     * Override this to let the worker return a badWeatherSound.
     *
     * @return soundEvent to be played.
     */
    SoundEvent getBadWeatherSound();
}
