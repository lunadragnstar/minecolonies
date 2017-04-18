package com.minecolonies.coremod.colony.jobs;

import com.minecolonies.api.client.model.CitizenModel;
import com.minecolonies.api.job.IJob;
import com.minecolonies.api.util.Log;
import com.minecolonies.coremod.colony.CitizenData;
import com.minecolonies.coremod.colony.Colony;
import com.minecolonies.skeleton.ai.AbstractAISkeleton;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.Constants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Basic job information.
 */
public abstract class AbstractJob implements com.minecolonies.api.job.IJob
{
    private static final String TAG_TYPE         = "type";
    private static final String TAG_ITEMS_NEEDED = "itemsNeeded";

    private static final String MAPPING_PLACEHOLDER = "Placeholder";
    private static final String MAPPING_BUILDER     = "Builder";
    private static final String MAPPING_DELIVERY    = "Deliveryman";
    private static final String MAPPING_MINER       = "Miner";
    private static final String MAPPING_LUMBERJACK  = "Lumberjack";
    private static final String MAPPING_FARMER      = "Farmer";
    private static final String MAPPING_FISHERMAN   = "Fisherman";
    private static final String MAPPING_TOWER_GUARD = "GuardTower";

    /**
     * The priority assigned with every main AI job.
     */
    private static final int TASK_PRIORITY = 3;

    //  Job and View Class Mapping.
    @NotNull
    private static final Map<String, Class<? extends AbstractJob>> nameToClassMap = new HashMap<>();
    @NotNull
    private static final Map<Class<? extends AbstractJob>, String> classToNameMap = new HashMap<>();
    static
    {
        addMapping(MAPPING_PLACEHOLDER, JobPlaceholder.class);
        addMapping(MAPPING_BUILDER, JobBuilder.class);
        addMapping(MAPPING_DELIVERY, JobDeliveryman.class);
        addMapping(MAPPING_MINER, JobMiner.class);
        addMapping(MAPPING_LUMBERJACK, JobLumberjack.class);
        addMapping(MAPPING_FARMER, JobFarmer.class);
        addMapping(MAPPING_FISHERMAN, JobFisherman.class);
        addMapping(MAPPING_TOWER_GUARD, JobGuard.class);
    }

    private final CitizenData citizen;
    @NotNull
    private final List<ItemStack> itemsNeeded = new ArrayList<>();
    private       String          nameTag     = "";

    /**
     * Initialize citizen data.
     *
     * @param entity the citizen data.
     */
    public AbstractJob(final CitizenData entity)
    {
        citizen = entity;
    }

    /**
     * Create a Job from saved NBTTagCompound data.
     *
     * @param citizen  The citizen that owns the Job.
     * @param compound The NBTTagCompound containing the saved Job data.
     * @return New Job created from the data, or null.
     */
    @Nullable
    public static IJob createFromNBT(CitizenData citizen, @NotNull NBTTagCompound compound)
    {
        @Nullable AbstractJob job = null;
        @Nullable Class<? extends AbstractJob> oclass = null;

        try
        {
            oclass = AbstractJob.nameToClassMap.get(compound.getString(AbstractJob.TAG_TYPE));

            if (oclass != null)
            {
                final Constructor<?> constructor = oclass.getDeclaredConstructor(CitizenData.class);
                job = (AbstractJob) constructor.newInstance(citizen);
            }
        }
        catch (@NotNull NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e)
        {
            Log.getLogger().trace(e);
        }

        if (job != null)
        {
            try
            {
                job.readFromNBT(compound);
            }
            catch (final RuntimeException ex)
            {
                Log.getLogger().error(String.format("A Job %s(%s) has thrown an exception during loading, its state cannot be restored. Report this to the mod author",
                  compound.getString(AbstractJob.TAG_TYPE), oclass.getName()), ex);
                job = null;
            }
        }
        else
        {
            Log.getLogger().warn(String.format("Unknown Job type '%s' or missing constructor of proper format.", compound.getString(AbstractJob.TAG_TYPE)));
        }

        return job;
    }

    /**
     * Add a given Job mapping.
     *
     * @param name     name of job class.
     * @param jobClass class of job.
     */
    private static void addMapping(final String name, @NotNull final Class<? extends AbstractJob> jobClass)
    {
        if (nameToClassMap.containsKey(name))
        {
            throw new IllegalArgumentException("Duplicate type '" + name + "' when adding Job class mapping");
        }
        try
        {
            if (jobClass.getDeclaredConstructor(CitizenData.class) != null)
            {
                nameToClassMap.put(name, jobClass);
                classToNameMap.put(jobClass, name);
            }
        }
        catch (final NoSuchMethodException exception)
        {
            throw new IllegalArgumentException("Missing constructor for type '" + name + "' when adding Job class mapping", exception);
        }
    }

    /**
     * Restore the Job from an NBTTagCompound.
     *
     * @param compound NBTTagCompound containing saved Job data.
     */
    @Override
    public void readFromNBT(@NotNull final NBTTagCompound compound)
    {
        final NBTTagList itemsNeededTag = compound.getTagList(TAG_ITEMS_NEEDED, Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < itemsNeededTag.tagCount(); i++)
        {
            final NBTTagCompound itemCompound = itemsNeededTag.getCompoundTagAt(i);
            itemsNeeded.add(ItemStack.loadItemStackFromNBT(itemCompound));
        }
    }

    /**
     * Get the RenderBipedCitizen.CitizenModel to use when the Citizen performs this job role.
     *
     * @return CitizenModel of the citizen.
     */
    @Override
    public CitizenModel getModel()
    {
        return CitizenModel.CITIZEN;
    }

    /**
     * Get the CitizenData that this Job belongs to.
     *
     * @return CitizenData that owns this Job.
     */
    @Override
    public CitizenData getCitizen()
    {
        return citizen;
    }

    /**
     * Get the Colony that this Job is associated with (shortcut for getCitizen().getColony()).
     *
     * @return {@link Colony} of the citizen.
     */
    @Override
    public Colony getColony()
    {
        return citizen.getColony();
    }

    /**
     * Save the Job to an NBTTagCompound.
     *
     * @param compound NBTTagCompound to save the Job to.
     */
    @Override
    public void writeToNBT(@NotNull final NBTTagCompound compound)
    {
        final String s = classToNameMap.get(this.getClass());

        if (s == null)
        {
            throw new IllegalStateException(this.getClass() + " is missing a mapping! This is a bug!");
        }

        compound.setString(TAG_TYPE, s);

        if (!itemsNeeded.isEmpty())
        {
            @NotNull final NBTTagList itemsNeededTag = new NBTTagList();
            for (@NotNull final ItemStack itemstack : itemsNeeded)
            {
                @NotNull final NBTTagCompound itemCompound = new NBTTagCompound();
                itemstack.writeToNBT(itemCompound);
                itemsNeededTag.appendTag(itemCompound);
            }
            compound.setTag(TAG_ITEMS_NEEDED, itemsNeededTag);
        }
    }

    /**
     * Does the Job have _all_ the needed items.
     *
     * @return true if the Job has no needed items.
     */
    @Override
    public boolean isMissingNeededItem()
    {
        return !itemsNeeded.isEmpty();
    }

    /**
     * Get the list of items needed by the Job.
     *
     * @return List of items needed by the Job.
     */
    @Override
    @NotNull
    public List<ItemStack> getItemsNeeded()
    {
        return Collections.unmodifiableList(itemsNeeded);
    }

    /**
     * Reset the items needed.
     */
    @Override
    public void clearItemsNeeded()
    {
        itemsNeeded.clear();
    }

    /**
     * Add (or increment) an ItemStack to the items needed by the Job.
     * We're not comparing item damage values since i.e a damaged rod is the same as a normal rod for our purpose.
     *
     * @param stack Item+count needed to do the job.
     */
    @Override
    public void addItemNeeded(@NotNull final ItemStack stack)
    {
        for (@NotNull final ItemStack neededItem : itemsNeeded)
        {
            if ((stack.getItem().isDamageable() && stack.getItem() == neededItem.getItem()) || stack.isItemEqual(neededItem))
            {
                neededItem.stackSize += stack.stackSize;
                return;
            }
        }

        itemsNeeded.add(stack);
    }

    /**
     * Remove a items from those required to do the Job.
     * We're not comparing item damage values since i.e a damaged rod is the same as a normal rod for our purpose.
     *
     * @param stack ItemStack (item+count) to remove from the list of needed items.
     * @return modified ItemStack with remaining items (or null).
     */
    @Override
    @Nullable
    public ItemStack removeItemNeeded(@NotNull final ItemStack stack)
    {
        @NotNull final ItemStack stackCopy = stack.copy();
        for (@NotNull final ItemStack neededItem : itemsNeeded)
        {
            if ((stack.getItem().isDamageable() && stack.getItem() == neededItem.getItem()) || stack.isItemEqual(neededItem))
            {
                final int itemsToRemove = Math.min(neededItem.stackSize, stackCopy.stackSize);
                neededItem.stackSize -= itemsToRemove;
                stackCopy.stackSize -= itemsToRemove;

                //Deativate this if for now in order to keep working even if not all items are given. previously checked if stackSize is 0 and only removed then.
                itemsNeeded.remove(neededItem);

                break;
            }
        }

        return stackCopy.stackSize == 0 ? null : stackCopy;
    }

    /**
     * Override to add Job-specific AI tasks to the given EntityAITask list.
     *
     * @param tasks EntityAITasks list to add tasks to.
     */
    @Override
    public void addTasks(@NotNull final EntityAITasks tasks)
    {
        final AbstractAISkeleton<? extends AbstractJob> aiTask = generateAI();
        if (aiTask != null)
        {
            tasks.addTask(TASK_PRIORITY, aiTask);
        }
    }

    /**
     * This method can be used to display the current status.
     * That a citizen is having.
     *
     * @return Small string to display info in name tag
     */
    @Override
    public String getNameTagDescription()
    {
        return this.nameTag;
    }

    /**
     * Used by the AI skeleton to change a citizens name.
     * Mostly used to update debugging information.
     *
     * @param nameTag The name tag to display.
     */
    @Override
    public final void setNameTag(final String nameTag)
    {
        this.nameTag = nameTag;
    }

    /**
     * Override this to let the worker return a bedTimeSound.
     *
     * @return soundEvent to be played.
     */
    @Override
    public SoundEvent getBedTimeSound()
    {
        return null;
    }

    /**
     * Override this to let the worker return a badWeatherSound.
     *
     * @return soundEvent to be played.
     */
    @Override
    public SoundEvent getBadWeatherSound()
    {
        return null;
    }
}
