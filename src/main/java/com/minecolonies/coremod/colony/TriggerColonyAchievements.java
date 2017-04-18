package com.minecolonies.coremod.colony;

import com.minecolonies.coremod.achievements.ModAchievementsInit;
import org.jetbrains.annotations.NotNull;

/**
 * Trigger the corresponding colony achievement.
 */
public final class TriggerColonyAchievements
{
    private static final String TAG_LUMBERJACK_SAPLINGS   = "saplings";
    private static final String TAG_LUMBERJACK_TREES      = "trees";
    private static final String TAG_FISHERMAN_FISH        = "fish";
    private static final String TAG_BUILDER_HUTS          = "huts";
    private static final String TAG_GUARD_MOBS            = "mobs";
    private static final String TAG_FARMER_CARROTS        = "carrots";
    private static final String TAG_FARMER_POTATOES       = "potatoes";
    private static final String TAG_FARMER_WHEAT          = "wheat";
    private static final String TAG_MINER_DIAMONDS        = "diamonds";
    private static final String TAG_MINER_ORES            = "ores";

    private TriggerColonyAchievements()
    {
        throw new IllegalAccessError("Utility class");
    }

    /**
     *Trigger fifth achievement.
     * @param statistic the statistic.
     * @param colony the colony.
     */
    public static void triggerFirstAchievement(@NotNull String statistic, @NotNull Colony colony)
    {
        switch (statistic)
        {
            case TAG_GUARD_MOBS:
                colony.triggerAchievement(ModAchievementsInit.achievementKillOneMob);
                break;
            case TAG_MINER_ORES:
                colony.triggerAchievement(ModAchievementsInit.achievementMineOneOre);
                break;
            case TAG_MINER_DIAMONDS:
                colony.triggerAchievement(ModAchievementsInit.achievementMineOneDiamond);
                break;
            case TAG_BUILDER_HUTS:
                colony.triggerAchievement(ModAchievementsInit.achievementBuildOneHut);
                break;
            case TAG_FISHERMAN_FISH:
                colony.triggerAchievement(ModAchievementsInit.achievementCatchOneFish);
                break;
            case TAG_FARMER_WHEAT:
                colony.triggerAchievement(ModAchievementsInit.achievementHarvestOneWheat);
                break;
            case TAG_FARMER_POTATOES:
                colony.triggerAchievement(ModAchievementsInit.achievementHarvestOnePotato);
                break;
            case TAG_FARMER_CARROTS:
                colony.triggerAchievement(ModAchievementsInit.achievementHarvestOneCarrot);
                break;
            case TAG_LUMBERJACK_SAPLINGS:
                colony.triggerAchievement(ModAchievementsInit.achievementPlantOneSapling);
                break;
            case TAG_LUMBERJACK_TREES:
                colony.triggerAchievement(ModAchievementsInit.achievementFellOneTree);
                break;
            default:
                break;
        }
    }

    /**
     *Trigger fifth achievement.
     * @param statistic the statistic.
     * @param colony the colony.
     */
    public static void triggerSecondAchievement(@NotNull String statistic, @NotNull Colony colony)
    {
        switch (statistic)
        {
            case TAG_GUARD_MOBS:
                colony.triggerAchievement(ModAchievementsInit.achievementKill25Mobs);
                break;
            case TAG_MINER_ORES:
                colony.triggerAchievement(ModAchievementsInit.achievementMine25Ores);
                break;
            case TAG_MINER_DIAMONDS:
                colony.triggerAchievement(ModAchievementsInit.achievementMine25Diamonds);
                break;
            case TAG_BUILDER_HUTS:
                colony.triggerAchievement(ModAchievementsInit.achievementBuild25Huts);
                break;
            case TAG_FISHERMAN_FISH:
                colony.triggerAchievement(ModAchievementsInit.achievementCatch25Fish);
                break;
            case TAG_FARMER_WHEAT:
                colony.triggerAchievement(ModAchievementsInit.achievementHarvest25Wheat);
                break;
            case TAG_FARMER_POTATOES:
                colony.triggerAchievement(ModAchievementsInit.achievementHarvest25Potatoes);
                break;
            case TAG_FARMER_CARROTS:
                colony.triggerAchievement(ModAchievementsInit.achievementHarvest25Carrots);
                break;
            case TAG_LUMBERJACK_SAPLINGS:
                colony.triggerAchievement(ModAchievementsInit.achievementPlant25Saplings);
                break;
            case TAG_LUMBERJACK_TREES:
                colony.triggerAchievement(ModAchievementsInit.achievementFell25Trees);
                break;
            default:
                break;
        }
    }

    /**
     *Trigger fifth achievement.
     * @param statistic the statistic.
     * @param colony the colony.
     */
    public static void triggerThirdAchievement(@NotNull String statistic, @NotNull Colony colony)
    {
        switch (statistic)
        {
            case TAG_GUARD_MOBS:
                colony.triggerAchievement(ModAchievementsInit.achievementKill100Mobs);
                break;
            case TAG_MINER_ORES:
                colony.triggerAchievement(ModAchievementsInit.achievementMine100Ores);
                break;
            case TAG_MINER_DIAMONDS:
                colony.triggerAchievement(ModAchievementsInit.achievementMine100Diamonds);
                break;
            case TAG_BUILDER_HUTS:
                colony.triggerAchievement(ModAchievementsInit.achievementBuild100Huts);
                break;
            case TAG_FISHERMAN_FISH:
                colony.triggerAchievement(ModAchievementsInit.achievementCatch100Fish);
                break;
            case TAG_FARMER_WHEAT:
                colony.triggerAchievement(ModAchievementsInit.achievementHarvest100Wheat);
                break;
            case TAG_FARMER_POTATOES:
                colony.triggerAchievement(ModAchievementsInit.achievementHarvest100Potatoes);
                break;
            case TAG_FARMER_CARROTS:
                colony.triggerAchievement(ModAchievementsInit.achievementHarvest100Carrots);
                break;
            case TAG_LUMBERJACK_SAPLINGS:
                colony.triggerAchievement(ModAchievementsInit.achievementPlant100Saplings);
                break;
            case TAG_LUMBERJACK_TREES:
                colony.triggerAchievement(ModAchievementsInit.achievementFell100Trees);
                break;
            default:
                break;
        }
    }

    /**
     *Trigger fifth achievement.
     * @param statistic the statistic.
     * @param colony the colony.
     */
    public static void triggerFourthAchievement(@NotNull String statistic, @NotNull Colony colony)
    {
        switch (statistic)
        {
            case TAG_GUARD_MOBS:
                colony.triggerAchievement(ModAchievementsInit.achievementKill500Mobs);
                break;
            case TAG_MINER_ORES:
                colony.triggerAchievement(ModAchievementsInit.achievementMine500Ores);
                break;
            case TAG_MINER_DIAMONDS:
                colony.triggerAchievement(ModAchievementsInit.achievementMine500Diamonds);
                break;
            case TAG_BUILDER_HUTS:
                colony.triggerAchievement(ModAchievementsInit.achievementBuild500Huts);
                break;
            case TAG_FISHERMAN_FISH:
                colony.triggerAchievement(ModAchievementsInit.achievementCatch500Fish);
                break;
            case TAG_FARMER_WHEAT:
                colony.triggerAchievement(ModAchievementsInit.achievementHarvest500Wheat);
                break;
            case TAG_FARMER_POTATOES:
                colony.triggerAchievement(ModAchievementsInit.achievementHarvest500Potatoes);
                break;
            case TAG_FARMER_CARROTS:
                colony.triggerAchievement(ModAchievementsInit.achievementHarvest500Carrots);
                break;
            case TAG_LUMBERJACK_SAPLINGS:
                colony.triggerAchievement(ModAchievementsInit.achievementPlant500Saplings);
                break;
            case TAG_LUMBERJACK_TREES:
                colony.triggerAchievement(ModAchievementsInit.achievementFell500Trees);
                break;
            default:
                break;
        }
    }

    /**
     *Trigger fifth achievement.
     * @param statistic the statistic.
     * @param colony the colony.
     */
    public static void triggerFifthAchievement(@NotNull String statistic, @NotNull Colony colony)
    {
        switch (statistic)
        {
            case TAG_GUARD_MOBS:
                colony.triggerAchievement(ModAchievementsInit.achievementKill1000Mobs);
                break;
            case TAG_MINER_ORES:
                colony.triggerAchievement(ModAchievementsInit.achievementMine1000Ores);
                break;
            case TAG_MINER_DIAMONDS:
                colony.triggerAchievement(ModAchievementsInit.achievementMine1000Diamonds);
                break;
            case TAG_BUILDER_HUTS:
                colony.triggerAchievement(ModAchievementsInit.achievementBuild1000Huts);
                break;
            case TAG_FISHERMAN_FISH:
                colony.triggerAchievement(ModAchievementsInit.achievementCatch1000Fish);
                break;
            case TAG_FARMER_WHEAT:
                colony.triggerAchievement(ModAchievementsInit.achievementHarvest1000Wheat);
                break;
            case TAG_FARMER_POTATOES:
                colony.triggerAchievement(ModAchievementsInit.achievementHarvest1000Potatoes);
                break;
            case TAG_FARMER_CARROTS:
                colony.triggerAchievement(ModAchievementsInit.achievementHarvest1000Carrots);
                break;
            case TAG_LUMBERJACK_SAPLINGS:
                colony.triggerAchievement(ModAchievementsInit.achievementPlant1000Saplings);
                break;
            case TAG_LUMBERJACK_TREES:
                colony.triggerAchievement(ModAchievementsInit.achievementFell1000Trees);
                break;
            default:
                break;
        }
    }
}
