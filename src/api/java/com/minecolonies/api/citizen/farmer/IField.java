package com.minecolonies.api.citizen.farmer;

import com.minecolonies.api.colony.IColony;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * ------------ Class not Documented ------------
 */
public interface IField<I extends IInventory & INBTSerializable<NBTTagCompound>> extends INBTSerializable<NBTTagCompound>
{

    @Nullable
    ItemStack transferStackInSlot(@NotNull EntityPlayer playerIn, int slotIndex);

    boolean canInteractWith(@NotNull EntityPlayer playerIn);

    /**
     * Returns the colony of the field.
     *
     * @return {@link IColony} of the current object.
     */
    @Nullable
    IColony getColony();

    /**
     * Calculates recursively the length of the field until a certain point.
     * <p>
     * This mutates the field!
     *
     * @param position the start position.
     * @param world    the world the field is in.
     */
    void calculateSize(@NotNull World world, @NotNull BlockPos position);

    /**
     * Checks if a certain position is part of the field. Complies with the definition of field block.
     *
     * @param world    the world object.
     * @param position the position.
     * @return true if it is.
     */
    boolean isNoPartOfField(@NotNull World world, @NotNull BlockPos position);

    /**
     * Returns the {@link BlockPos} of the current object, also used as ID.
     *
     * @return {@link BlockPos} of the current object.
     */
    BlockPos getID();

    /**
     * Has the field been taken?
     *
     * @return true if the field is not free to use, false after releasing it.
     */
    boolean isTaken();

    /**
     * Sets the field taken.
     *
     * @param taken is field free or not
     */
    void setTaken(boolean taken);

    /**
     * Checks if the field has been planted.
     *
     * @return true if there are crops planted.
     */
    FieldStage getFieldStage();

    /**
     * Sets if there are any crops planted.
     *
     * @param fieldStage true after planting, false after harvesting.
     */
    void setFieldStage(FieldStage fieldStage);

    /**
     * Checks if the field needs work (planting, hoeing).
     *
     * @return true if so.
     */
    boolean needsWork();

    /**
     * Sets that the field needs work.
     *
     * @param needsWork true if work needed, false after completing the job.
     */
    void setNeedsWork(boolean needsWork);

    /**
     * Checks if the field is initialized.
     *
     * @return true if so.
     */
    boolean isInitialized();

    /**
     * Sets that the field has been initialized.
     *
     * @param initialized true if so.
     */
    void setInitialized(boolean initialized);

    /**
     * Getter of the seed of the field.
     *
     * @return the ItemSeed
     */
    @Nullable
    ItemStack getSeed();

    /**
     * Getter of the length in plus x direction.
     *
     * @return field length.
     */
    int getLengthPlusX();

    /**
     * Getter of the with in plus z direction.
     *
     * @return field width.
     */
    int getWidthPlusZ();

    /**
     * Getter of the length in minus x direction.
     *
     * @return field length.
     */
    int getLengthMinusX();

    /**
     * Getter of the with in minus z direction.
     *
     * @return field width.
     */
    int getWidthMinusZ();

    /**
     * Location getter.
     *
     * @return the location of the scarecrow of the field.
     */
    BlockPos getLocation();

    /**
     * Return this citizens inventory.
     *
     * @return the inventory this citizen has.
     */
    @NotNull
    I getInventoryField();

    /**
     * Sets the inventory of the field.
     *
     * @param inventory the inventory to set.
     */
    void setInventoryField(I inventory);

    /**
     * Getter of the owner of the field.
     *
     * @return the string description of the citizen.
     */
    @NotNull
    String getOwner();

    /**
     * Sets the owner of the field.
     *
     * @param owner the name of the citizen.
     */
    void setOwner(@NotNull String owner);

    /**
     * Setter for a custom description of the inventory.
     *
     * @param customName the name to set.
     */
    void setCustomName(String customName);

    /**
     * Describes the stage the field is in.
     * Like if it has been hoed, planted or is empty.
     */
    enum FieldStage
    {
        EMPTY,
        HOED,
        PLANTED
    }
}
