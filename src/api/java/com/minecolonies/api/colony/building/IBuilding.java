package com.minecolonies.api.colony.building;

import com.minecolonies.api.citizen.ICitizenData;
import com.minecolonies.api.colony.IColony;
import com.minecolonies.api.util.Log;
import com.minecolonies.skeleton.colony.building.AbstractBuilding;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * ------------ Class not Documented ------------
 */
public interface IBuilding
{
    /**
     * Load data from NBT compound.
     *
     * @param compound {@link net.minecraft.nbt.NBTTagCompound} to read data from.
     */
    void readFromNBT(@NotNull NBTTagCompound compound);

    /**
     * Children must return the name of their structure.
     *
     * @return StructureProxy name.
     */
    String getSchematicName();

    /**
     * Checks if a block matches the current object.
     *
     * @param block Block you want to know whether it matches this class or not.
     * @return True if the block matches this class, otherwise false.
     */
    boolean isMatchingBlock(@NotNull Block block);

    /**
     * Save data to NBT compound.
     *
     * @param compound {@link NBTTagCompound} to write data to.
     */
    void writeToNBT(@NotNull NBTTagCompound compound);

    /**
     * Returns the {@link BlockPos} of the current object, also used as ID.
     *
     * @return {@link BlockPos} of the current object.
     */
    BlockPos getLocation();

    /**
     * Returns whether the instance is dirty or not.
     *
     * @return true if dirty, false if not.
     */
    boolean isDirty();

    /**
     * Resets the dirty flag..
     */
    void clearDirty();

    /**
     * Destroys the block.
     * Calls {@link #onDestroyed()}.
     */
    void destroy();

    /**
     * Method to do things when a block is destroyed.
     */
    void onDestroyed();

    /**
     * Returns the tile entity that belongs to the colony building.
     *
     * @return {@link TileEntity} object of the building.
     */
    TileEntity getTileEntity();

    /**
     * Sets the tile entity for the building.
     *
     * @param te {@link TileEntity} for this building.
     */
    void setTileEntity(TileEntity te);

    /**
     * Returns the colony of the building.
     *
     * @return {@link IColony} of the current object.
     */
    @NotNull
    IColony getColony();

    /**
     * Method to remove a citizen.
     *
     * @param citizen Citizen to be removed.
     */
    void removeCitizen(ICitizenData citizen);

    /**
     * On tick of the server.
     *
     * @param event {@link net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent}
     */
    void onServerTick(TickEvent.ServerTickEvent event);

    /**
     * On tick of the world.
     *
     * @param event {@link TickEvent.WorldTickEvent}
     */
    void onWorldTick(TickEvent.WorldTickEvent event);

    /**
     * Requests an upgrade for the current building.
     */
    void requestUpgrade();

    /**
     * Checks if this building have a work order.
     *
     * @return true if the building is building, upgrading or repairing.
     */
    boolean hasWorkOrder();

    /**
     * Children must return their max building level.
     *
     * @return Max building level.
     */
    int getMaxBuildingLevel();

    /**
     * Returns the {@link BlockPos} of the current object, also used as ID.
     *
     * @return {@link BlockPos} of the current object.
     */
    BlockPos getID();

    /**
     * Requests a repair for the current building.
     */
    void requestRepair();

    /**
     * Remove the work order for the building.
     *
     * Remove either the upgrade or repair work order
     */
    void removeWorkOrder();

    /**
     * Returns the rotation of the current building.
     *
     * @return integer value of the rotation.
     */
    int getRotation();

    /**
     * Sets the rotation of the current building.
     *
     * @param rotation integer value of the rotation.
     */
    void setRotation(int rotation);

    /**
     * Returns the style of the current building.
     *
     * @return String representation of the current building-style
     */
    String getStyle();

    /**
     * Sets the style of the building.
     *
     * @param style String value of the style.
     */
    void setStyle(String style);

    /**
     * Called upon completion of an upgrade process.
     *
     * @param newLevel The new level.
     */
    void onUpgradeComplete(int newLevel);

    /**
     * Serializes to view.
     * Sends 3 integers.
     * 1) hashcode of the name of the class.
     * 2) building level.
     * 3) max building level.
     *
     * @param buf ByteBuf to write to.
     */
    void serializeToView(@NotNull ByteBuf buf);

    /**
     * Returns the level of the current object.
     *
     * @return Level of the current object.
     */
    int getBuildingLevel();

    /**
     * Sets the current level of the building.
     *
     * @param level Level of the building.
     */
    void setBuildingLevel(int level);

    /**
     * Marks the instance and the building dirty.
     */
    void markDirty();

    /**
     * Add a new container to the building.
     * @param pos position to add.
     */
    void addContainerPosition(BlockPos pos);

    /**
     * Remove a container from the building.
     * @param pos position to remove.
     */
    void removeContainerPosition(BlockPos pos);

    /**
     * Get all additional containers which belong to the building.
     * @return a copy of the list to avoid currentModification exception.
     */
    List<BlockPos> getAdditionalCountainers();

    /**
     * Override this method if you want to keep an amount of items in inventory.
     * When the inventory is full, everything get's dumped into the building chest.
     * But you can use this method to hold some stacks back.
     *
     * @return a list of objects which should be kept.
     */
    Map<ItemStorage, Integer> getRequiredItemsAndAmount();

    /**
     * Check if the building is receiving the required items.
     * @return true if so.
     */
    boolean hasOnGoingDelivery();

    /**
     * Check if the building is receiving the required items.
     * @param valueToSet true or false
     */
    void setOnGoingDelivery(boolean valueToSet);

    /**
     * Check if the worker needs anything. Tool or item.
     * @return true if so.
     */
    boolean needsAnything();

    /**
     * Check if any items are needed at the moment.
     * @return true if so.
     */
    boolean areItemsNeeded();

    /**
     * Check if the worker requires a shovel.
     * @return true if so.
     */
    boolean needsShovel();

    /**
     * Check if the worker requires a axe.
     * @return true if so.
     */
    boolean needsAxe();

    /**
     * Check if the worker requires a hoe.
     * @return true if so.
     */
    boolean needsHoe();

    /**
     * Check if the worker requires a pickaxe.
     * @return true if so.
     */
    boolean needsPickaxe();

    /**
     * Check if the worker requires a weapon.
     * @return true if so.
     */
    boolean needsWeapon();

    /**
     * Check the required pickaxe level..
     * @return the mining level of the pickaxe.
     */
    int getNeededPickaxeLevel();

    /**
     * Set if the worker needs a shovel.
     * @param needsShovel true or false.
     */
    void setNeedsShovel(boolean needsShovel);

    /**
     * Set if the worker needs a axe.
     * @param needsAxe true or false.
     */
    void setNeedsAxe(boolean needsAxe);

    /**
     * Set if the worker needs a hoe.
     * @param needsHoe true or false.
     */
    void setNeedsHoe(boolean needsHoe);

    /**
     * Set if the worker needs a pickaxe.
     * @param needsPickaxe true or false.
     */
    void setNeedsPickaxe(boolean needsPickaxe);

    /**
     * Set if the worker needs a weapon.
     * @param needsWeapon true or false.
     */
    void setNeedsWeapon(boolean needsWeapon);

    /**
     * Add a neededItem to the currentlyNeededItem list.
     * @param stack the stack to add.
     */
    void addNeededItems(@Nullable ItemStack stack);

    /**
     * Getter for the neededItems.
     * @return an unmodifiable list.
     */
    List<ItemStack> getNeededItems();

    /**
     * Getter for the first of the currentlyNeededItems.
     * @return copy of the itemStack.
     */
    @Nullable
    ItemStack getFirstNeededItem();

    /**
     * Clear the currentlyNeededItem list.
     */
    void clearNeededItems();

    /**
     * Overwrite the itemsCurrentlyNeededList with a new one.
     * @param newList the new list to set.
     */
    void setItemsCurrentlyNeeded(@NotNull List<ItemStack> newList);

    /**
     * Set the needed pickaxe level of the worker.
     * @param needsPickaxeLevel the mining level.
     */
    void setNeedsPickaxeLevel(int needsPickaxeLevel);

    /**
     * Check for the required tool and return the describing string.
     * @return the string of the required tool.
     */
    String getRequiredTool();

    /**
     * Try to transfer a stack to one of the inventories of the building.
     * @param stack the stack to transfer.
     * @param world the world to do it in.
     * @return true if was able to.
     */
    boolean transferStack(@NotNull ItemStack stack, @NotNull World world);

    /**
     * Try to transfer a stack to one of the inventories of the building and force the transfer.
     * @param stack the stack to transfer.
     * @param world the world to do it in.
     * @return the itemStack which has been replaced
     */
    @Nullable
    ItemStack forceTransferStack(ItemStack stack, World world);

    /**
     * Returns the mirror of the current building.
     *
     * @return boolean value of the mirror.
     */
    boolean isMirrored();

    /**
     * Sets the mirror of the current building.
     */
    void setMirror();
}
