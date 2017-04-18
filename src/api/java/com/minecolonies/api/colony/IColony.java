package com.minecolonies.api.colony;

import com.minecolonies.api.citizen.ICitizenData;
import com.minecolonies.api.citizen.farmer.IField;
import com.minecolonies.api.colony.building.IBuilding;
import com.minecolonies.api.permission.IPermissions;
import com.minecolonies.skeleton.colony.building.AbstractBuilding;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.Achievement;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Interface of the Colony and ColonyView which will have to implement the following methods.
 */
public interface IColony
{

    /**
     * Read colony from saved data.
     *
     * @param compound compound to read from.
     */
    void readFromNBT(@NotNull NBTTagCompound compound);

    /**
     * Add a AbstractBuilding to the Colony.
     *
     * @param building AbstractBuilding to add to the colony.
     */
    void addBuilding(@NotNull IBuilding building);

    /**
     * Add a Building to the Colony.
     *
     * @param field Field to add to the colony.
     */
    void addField(@NotNull IField field);

    /**
     * Write colony to save data.
     *
     * @param compound compound to write to.
     */
    void writeToNBT(@NotNull NBTTagCompound compound);

    /**
     * Returns the dimension ID.
     *
     * @return Dimension ID.
     */
    int getDimension();

    /**
     * Returns the position of the colony.
     *
     * @return pos of the colony.
     */
    BlockPos getCenter();

    /**
     * Returns the name of the colony.
     *
     * @return Name of the colony.
     */
    String getName();

    /**
     * Sets the name of the colony.
     * Marks dirty.
     *
     * @param n new name.
     */
    void setName(String n);

    /**
     * Marks the instance dirty.
     */
    void markDirty();

    /**
     * Returns the permissions of the colony.
     *
     * @return {@link IPermissions} of the colony.
     */
    IPermissions getPermissions();

    /**
     * Determine if a given chunk coordinate is considered to be within the colony's bounds.
     *
     * @param w   World to check.
     * @param pos Block Position.
     * @return True if inside colony, otherwise false.
     */
    boolean isCoordInColony(World w, BlockPos pos);

    /**
     * Returns the world the colony is in.
     *
     * @return World the colony is in.
     */
    @Nullable
    World getWorld();

    /**
     * Returns the squared (x, z) distance to the center.
     *
     * @param pos Block Position.
     * @return Squared distance to the center in (x, z) direction.
     */
    long getDistanceSquared(BlockPos pos);

    /**
     * Returns whether or not the colony has a town hall.
     *
     * @return whether or not the colony has a town hall.
     */
    boolean hasTownHall();

    /**
     * returns this colonies unique id.
     *
     * @return an int representing the id.
     */
    int getID();

    /**
     * Get the amount of statistic.
     *
     * @param statistic the statistic.
     * @return amount of statistic.
     */
    int getStatisticAmount(@NotNull String statistic);

    /**
     * increment statistic amount.
     * @param statistic the statistic.
     */
    void incrementStatisticAmount(@NotNull String statistic);

    /**
     * Increment the statistic amount and trigger achievement.
     * @param statistic the statistic.
     */
    void incrementStatistic(@NotNull String statistic);

    /**
     * Triggers an achievement on this colony.
     * <p>
     * Will automatically sync to all players.
     *
     * @param achievement The achievement to trigger
     */
    void triggerAchievement(@NotNull Achievement achievement);

    /**
     * Marks citizen data dirty.
     */
    void markCitizensDirty();

    /**
     * Marks building data dirty.
     */
    void markBuildingsDirty();

    /**
     * When the Colony's world is loaded, associate with it.
     *
     * @param w World object.
     */
    void onWorldLoad(@NotNull World w);

    /**
     * Unsets the world if the world unloads.
     *
     * @param w World object.
     */
    void onWorldUnload(@NotNull World w);

    /**
     * Any per-server-tick logic should be performed here.
     *
     * @param event {@link net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent}
     */
    void onServerTick(@NotNull TickEvent.ServerTickEvent event);

    /**
     * Update Subscribers with Colony, Citizen, and AbstractBuilding Views.
     */
    void updateSubscribers();

    void sendColonyViewPackets(@NotNull Set<EntityPlayerMP> oldSubscribers, boolean hasNewSubscribers);

    /**
     * Sends packages to update the permissions.
     *
     * @param oldSubscribers    the existing subscribers.
     * @param hasNewSubscribers the new subscribers.
     */
    void sendPermissionsPackets(@NotNull Set<EntityPlayerMP> oldSubscribers, boolean hasNewSubscribers);

    /**
     * Sends packages to update the workOrders.
     *
     * @param oldSubscribers    the existing subscribers.
     * @param hasNewSubscribers the new subscribers.
     */
    void sendWorkOrderPackets(@NotNull Set<EntityPlayerMP> oldSubscribers, boolean hasNewSubscribers);

    /**
     * Sends packages to update the citizens.
     *
     * @param oldSubscribers    the existing subscribers.
     * @param hasNewSubscribers the new subscribers.
     */
    void sendCitizenPackets(@NotNull Set<EntityPlayerMP> oldSubscribers, boolean hasNewSubscribers);

    /**
     * Sends packages to update the buildings.
     *
     * @param oldSubscribers    the existing subscribers.
     * @param hasNewSubscribers the new subscribers.
     */
    void sendBuildingPackets(@NotNull Set<EntityPlayerMP> oldSubscribers, boolean hasNewSubscribers);

    /**
     * Sends packages to update the fields.
     * @param hasNewSubscribers the new subscribers.
     */
    void sendFieldPackets(boolean hasNewSubscribers);

    /**
     * Get a copy of the freePositions list.
     * @return the list of free to interact positions.
     */
    Set<BlockPos> getFreePositions();

    /**
     * Get a copy of the freeBlocks list.
     * @return the list of free to interact blocks.
     */
    Set<Block> getFreeBlocks();

    /**
     * Add a new free to interact position.
     * @param pos position to add.
     */
    void addFreePosition(@NotNull BlockPos pos);

    /**
     * Add a new free to interact block.
     * @param block block to add.
     */
    void addFreeBlock(@NotNull Block block);

    /**
     * Remove a free to interact position.
     * @param pos position to remove.
     */
    void removeFreePosition(@NotNull BlockPos pos);

    /**
     * Remove a free to interact block.
     * @param block state to remove.
     */
    void removeFreeBlock(@NotNull Block block);

    /**
     * Get the Work Manager for the Colony.
     *
     * @return WorkManager for the Colony.
     */
    @NotNull
    IWorkManager getWorkManager();

    /**
     * Any per-world-tick logic should be performed here.
     * NOTE: If the Colony's world isn't loaded, it won't have a world tick.
     * Use onServerTick for logic that should _always_ run.
     *
     * @param event {@link TickEvent.WorldTickEvent}
     */
    void onWorldTick(@NotNull TickEvent.WorldTickEvent event);

    /**
     * Spawn a citizen with specific citizen data.
     *
     * @param data Data to use to spawn citizen.
     */
    void spawnCitizen(ICitizenData data);

    /**
     * Gets the town hall of the colony.
     *
     * @return Town hall of the colony.
     */
    @Nullable
    IBuilding getTownHall();

    /**
     * Getter of a unmodifiable version of the farmerFields map.
     *
     * @return map of fields and their id.
     */
    @NotNull
    Map<BlockPos, IField> getFields();

    /**
     * Get field in Colony by ID.
     *
     * @param fieldId ID (coordinates) of the field to get.
     * @return field belonging to the given ID.
     */
    IField getField(BlockPos fieldId);

    /**
     * Returns a field which has not been taken yet.
     *
     * @param owner name of the owner of the field.
     * @return a field if there is one available, else null.
     */
    @Nullable
    IField getFreeField(String owner);

    /**
     * Get building in Colony by ID.
     *
     * @param buildingId ID (coordinates) of the building to get.
     * @return AbstractBuilding belonging to the given ID.
     */
    IBuilding getBuilding(BlockPos buildingId);

    /**
     * Get building in Colony by ID. The building will be casted to the provided type.
     *
     * @param buildingId ID (coordinates) of the building to get.
     * @param type       Type of building.
     * @param <B>        Building class.
     * @return the building with the specified id.
     */
    @Nullable
    <B extends AbstractBuilding> B getBuilding(BlockPos buildingId, @NotNull Class<B> type);

    /**
     * Creates a field from a tile entity and adds it to the colony.
     *
     * @param tileEntity      the scarecrow which contains the inventory.
     * @param inventoryPlayer the inventory of the player.
     * @param pos             Position where the field has been placed.
     * @param world           the world of the field.
     */
    void addNewField(TileEntity tileEntity, InventoryPlayer inventoryPlayer, BlockPos pos, World world);

    /**
     * Creates a building from a tile entity and adds it to the colony.
     *
     * @param tileEntity Tile entity to build a building from.
     * @return AbstractBuilding that was created and added.
     */
    @Nullable
    IBuilding addNewBuilding(@NotNull TileEntity tileEntity);

    /**
     * Recalculates how many citizen can be in the colony.
     */
    void calculateMaxCitizens();

    /**
     * Remove a AbstractBuilding from the Colony (when it is destroyed).
     *
     * @param building AbstractBuilding to remove.
     */
    void removeBuilding(@NotNull IBuilding building);

    /**
     * Getter which checks if jobs should be manually allocated.
     *
     * @return true of false.
     */
    boolean isManualHiring();

    /**
     * Setter to set the job allocation manual or automatic.
     *
     * @param manualHiring true if manual, false if automatic.
     */
    void setManualHiring(boolean manualHiring);

    /**
     * Returns the max amount of citizens in the colony.
     *
     * @return Max amount of citizens.
     */
    int getMaxCitizens();

    /**
     * Returns a map of citizens in the colony.
     * The map has ID as key, and citizen data as value.
     *
     * @return Map of citizens in the colony, with as key the citizen ID, and as value the citizen data.
     */
    @NotNull
    Map<Integer, ICitizenData> getCitizens();

    /**
     * Removes a citizen from the colony.
     *
     * @param citizen Citizen data to remove.
     */
    void removeCitizen(@NotNull ICitizenData citizen);

    /**
     * Send the message of a removed workOrder to the client.
     *
     * @param orderId the workOrder to remove.
     */
    void removeWorkOrder(int orderId);

    /**
     * Get citizen by ID.
     *
     * @param citizenId ID of the Citizen.
     * @return CitizenData associated with the ID, or null if it was not found.
     */
    ICitizenData getCitizen(int citizenId);

    /**
     * Get the first unemployed citizen.
     *
     * @return Citizen with no current job.
     */
    @Nullable
    ICitizenData getJoblessCitizen();

    List<BlockPos> getDeliverymanRequired();

    /**
     * Performed when a building of this colony finished his upgrade state.
     *
     * @param building The upgraded building.
     * @param level    The new level.
     */
    void onBuildingUpgradeComplete(@NotNull IBuilding building, int level);

    @NotNull
    List<EntityPlayer> getMessageEntityPlayers();

    @NotNull
    List<Achievement> getAchievements();

    /**
     * Removes a field from the farmerFields list.
     *
     * @param pos the position-id.
     */
    void removeField(BlockPos pos);

    /**
     * Adds a waypoint to the colony.
     *
     * @param point the waypoint to add.
     * @param block the block at the waypoint.
     */
    void addWayPoint(BlockPos point, IBlockState block);

    /**
     * Returns a list of all wayPoints of the colony.
     *
     * @param position start position.
     * @param target   end position.
     * @return list of wayPoints.
     */
    @NotNull
    List<BlockPos> getWayPoints(@NotNull BlockPos position, @NotNull BlockPos target);

    /**
     * Returns a map with all buildings within the colony.
     * Key is ID (Coordinates), value is building object.
     *
     * @return Map with ID (coordinates) as key, and buildings as value.
     */
    @NotNull
    Map<BlockPos, IBuilding> getBuildings();
}
