package com.minecolonies.entity.ai;

import com.minecolonies.entity.EntityCitizen;
import net.minecraft.entity.ai.EntityAIBase;

import static com.minecolonies.entity.EntityCitizen.Status.SLEEPING;

public class EntityAISleep extends EntityAIBase
{
    private EntityCitizen citizen;

    public EntityAISleep(EntityCitizen citizen)
    {
        this.setMutexBits(1);
        this.citizen = citizen;
    }

    @Override
    public boolean shouldExecute()
    {
        return citizen.isSleepTime() && citizen.isAtHome();//!this.citizen.isWorkTime? - sleep when raining?
    }

    @Override
    public boolean continueExecuting()
    {
        return citizen.isSleepTime();
    }

    @Override
    public void startExecuting()
    {
        //TODO sleep
        citizen.setStatus(SLEEPING);
    }

    @Override
    public void updateTask()
    {
        //TODO snore?
    }
}