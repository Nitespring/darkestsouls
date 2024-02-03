package github.nitespring.darkestsouls.client.render.entity.mob.skeleton;

import com.mojang.blaze3d.vertex.PoseStack;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.Bonewheel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BonewheelGeoRenderer extends GeoEntityRenderer<Bonewheel>{

	public BonewheelGeoRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new BonewheelModel());
        
        this.shadowRadius = 0.5F;
     
       
    }
	
	@Override
	protected float getDeathMaxRotation(Bonewheel entityLivingBaseIn) {
		
		return 0f;
	}
	
	@Override
	public int getPackedOverlay(Bonewheel animatable, float u) {

		return OverlayTexture.NO_OVERLAY;
	}

	
	 @Override
	public RenderType getRenderType(Bonewheel animatable, ResourceLocation texture, MultiBufferSource bufferSource,
			float partialTick) {
		 return RenderType.entityCutoutNoCull(texture);
	}
	 
	 @Override
	public void render(Bonewheel entity, float entityYaw, float partialTick, PoseStack poseStack,
			MultiBufferSource bufferSource, int packedLight) {
		 float scaleFactor = 1.0f;
		 poseStack.pushPose();
		 poseStack.scale(scaleFactor, scaleFactor, scaleFactor);

		 poseStack.translate(0, 0, 0);

		 super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
		 poseStack.popPose();
	}


    public static class BonewheelModel extends GeoModel<Bonewheel> {

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
}
