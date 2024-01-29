package github.nitespring.darkestsouls.client.render.entity.mob.skeleton;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.Bonewheel;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;


public class BonewheelModel extends GeoModel<Bonewheel>{

	@Override
	public ResourceLocation getAnimationResource(Bonewheel animatable) {
		
		return new ResourceLocation(DarkestSouls.MODID, "animations/bonewheel.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(Bonewheel object) {
		
		return new ResourceLocation(DarkestSouls.MODID, "geo/bonewheel.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(Bonewheel object) {
		
		return new ResourceLocation(DarkestSouls.MODID, "textures/entity/skeleton/bonewheel.png");
	}
	
	
	@Override
	public void setCustomAnimations(Bonewheel entity, long uniqueID, AnimationState<Bonewheel> customPredicate) {
    	super.setCustomAnimations(entity, uniqueID, customPredicate);
    	CoreGeoBone head = this.getAnimationProcessor().getBone("head_rotation");
        assert customPredicate != null;
        EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(extraData.headPitch() * ((float) Math.PI / 180F));
        head.setRotY(extraData.netHeadYaw() *0.5f* ((float) Math.PI / 180F));

		
	}

}