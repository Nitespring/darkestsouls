package github.nitespring.darkestsouls.client.render.entity.mob.abyss;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.mob.abyss.SewerCentipede;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.Bonewheel;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;


public class SewerCentipedeModel extends GeoModel<SewerCentipede>{

	@Override
	public ResourceLocation getAnimationResource(SewerCentipede animatable) {
		
		return new ResourceLocation(DarkestSouls.MODID, "animations/sewer_centipede.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(SewerCentipede object) {
		
		return new ResourceLocation(DarkestSouls.MODID, "geo/sewer_centipede.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(SewerCentipede object) {
		
		return new ResourceLocation(DarkestSouls.MODID, "textures/entity/sewer_centipede.png");
	}
	
	
	@Override
	public void setCustomAnimations(SewerCentipede entity, long uniqueID, AnimationState<SewerCentipede> customPredicate) {
    	super.setCustomAnimations(entity, uniqueID, customPredicate);
    	CoreGeoBone head = this.getAnimationProcessor().getBone("waist_rotation");
        assert customPredicate != null;
        EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(extraData.headPitch() *0.15f* ((float) Math.PI / 180F));
        //head.setRotY(extraData.netHeadYaw() *0.15f* ((float) Math.PI / 180F));

		
	}

}
