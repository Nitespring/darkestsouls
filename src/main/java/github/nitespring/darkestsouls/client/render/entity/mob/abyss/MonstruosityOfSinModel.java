package github.nitespring.darkestsouls.client.render.entity.mob.abyss;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.mob.abyss.MonstruosityOfSin;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;


public class MonstruosityOfSinModel extends GeoModel<MonstruosityOfSin>{

	@Override
	public ResourceLocation getAnimationResource(MonstruosityOfSin animatable) {
		
		return new ResourceLocation(DarkestSouls.MODID, "animations/sin.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(MonstruosityOfSin object) {
		
		return new ResourceLocation(DarkestSouls.MODID, "geo/sin.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(MonstruosityOfSin object) {
		
		return new ResourceLocation(DarkestSouls.MODID, "textures/entity/sin.png");
	}
	
	
	@Override
	public void setCustomAnimations(MonstruosityOfSin entity, long uniqueID, AnimationState<MonstruosityOfSin> customPredicate) {
    	super.setCustomAnimations(entity, uniqueID, customPredicate);
    	CoreGeoBone head = this.getAnimationProcessor().getBone("head_rotation");
        assert customPredicate != null;
        EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(extraData.headPitch() * ((float) Math.PI / 180F));
        head.setRotY(extraData.netHeadYaw() *0.5f* ((float) Math.PI / 180F));

		
	}

}
