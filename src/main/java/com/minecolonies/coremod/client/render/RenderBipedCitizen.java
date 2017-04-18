package com.minecolonies.coremod.client.render;

import com.minecolonies.api.client.model.CitizenModel;
import com.minecolonies.coremod.client.model.*;
import com.minecolonies.coremod.entity.EntityCitizen;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;

/**
 * Renderer for the citizens.
 */
public class RenderBipedCitizen extends RenderBiped<EntityCitizen>
{
    private static final ModelBiped                    defaultModelMale   = new ModelBiped();
    private static final ModelBiped                    defaultModelFemale = new ModelEntityCitizenFemaleCitizen();
    private static final Map<CitizenModel, ModelBiped> idToMaleModelMap   = new EnumMap<>(CitizenModel.class);
    private static final Map<CitizenModel, ModelBiped> idToFemaleModelMap = new EnumMap<>(CitizenModel.class);
    private static final double                        SHADOW_SIZE        = 0.5F;
    static
    {
        idToMaleModelMap.put(CitizenModel.DELIVERYMAN, new ModelEntityDeliverymanMale());
        idToMaleModelMap.put(CitizenModel.LUMBERJACK, new ModelEntityLumberjackMale());
        idToMaleModelMap.put(CitizenModel.FARMER, new ModelEntityFarmerMale());
        idToMaleModelMap.put(CitizenModel.FISHERMAN, new ModelEntityFishermanMale());

        idToFemaleModelMap.put(CitizenModel.NOBLE, new ModelEntityCitizenFemaleNoble());
        idToFemaleModelMap.put(CitizenModel.ARISTOCRAT, new ModelEntityCitizenFemaleAristocrat());
        idToFemaleModelMap.put(CitizenModel.BUILDER, new ModelEntityBuilderFemale());
        idToFemaleModelMap.put(CitizenModel.DELIVERYMAN, new ModelEntityDeliverymanFemale());
        idToFemaleModelMap.put(CitizenModel.MINER, new ModelEntityMinerFemale());
        idToFemaleModelMap.put(CitizenModel.LUMBERJACK, new ModelEntityLumberjackFemale());
        idToFemaleModelMap.put(CitizenModel.FARMER, new ModelEntityFarmerFemale());
        idToFemaleModelMap.put(CitizenModel.FISHERMAN, new ModelEntityFishermanFemale());
        idToFemaleModelMap.put(CitizenModel.ARCHER_GUARD, new ModelBiped());
        idToFemaleModelMap.put(CitizenModel.KNIGHT_GUARD, new ModelBiped());
    }
    /**
     * Renders model, see {@link RenderBiped}.
     *
     * @param renderManagerIn the RenderManager for this Renderer.
     */
    public RenderBipedCitizen(final RenderManager renderManagerIn)
    {
        super(renderManagerIn, defaultModelMale, (float) SHADOW_SIZE);
        this.addLayer(new LayerBipedArmor(this));
    }

    @Override
    public void doRender(@NotNull final EntityCitizen citizen, final double d, final double d1, final double d2, final float f, final float f1)
    {
        modelBipedMain = citizen.isFemale()
                           ? idToFemaleModelMap.get(citizen.getModelID())
                           : idToMaleModelMap.get(citizen.getModelID());

        if (modelBipedMain == null)
        {
            modelBipedMain = citizen.isFemale() ? defaultModelFemale : defaultModelMale;
        }

        mainModel = modelBipedMain;

        super.doRender(citizen, d, d1, d2, f, f1);
    }

    @Override
    protected ResourceLocation getEntityTexture(@NotNull final EntityCitizen entity)
    {
        return entity.getTexture();
    }
}
