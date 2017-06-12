package com.minecolonies.coremod.entity.ai.mobs;

import com.minecolonies.api.util.constant.Constants;
import com.minecolonies.coremod.MineColonies;
import com.minecolonies.coremod.colony.Colony;
import com.minecolonies.coremod.colony.ColonyManager;
import com.minecolonies.coremod.entity.EntityCitizen;
import com.minecolonies.coremod.sounds.BarbarianSounds;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by Asher on 5/6/17.
 */
public class EntityChiefBarbarian extends EntityMob
{
    final Colony colony = ColonyManager.getClosestColony(world, this.getPosition());

    public EntityChiefBarbarian(World worldIn)
    {
        super(worldIn);
        this.getAlwaysRenderNameTag();
    }

    public static final ResourceLocation LOOT = new ResourceLocation(Constants.MOD_ID, "EntityChiefBarbarianDrops");

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.13D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(this.getHealthBasedOnRaidLevel());
    }

    protected double getHealthBasedOnRaidLevel()
    {
        if (colony != null)
        {
            int raidLevel = (int) (colony.getRaidLevel()*1.5);
            return 25+raidLevel;
        }
        return 25.0D;
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(3, new EntityAIAttackMelee(this, 2.3D, true));
        this.tasks.addTask(3, new EntityAIWalkToRandomHuts(this, 2.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.applyEntityAI();
    }

    private void applyEntityAI()
    {
        this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityCitizen.class, true));
    }

    @Override
    protected boolean canDespawn()
    {
        return world.isDaytime();

    }

    @Override
    protected SoundEvent getHurtSound() {
        return BarbarianSounds.barbarianHurt;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return BarbarianSounds.barbarianDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return BarbarianSounds.barbarianSay;
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LOOT;
    }
}
