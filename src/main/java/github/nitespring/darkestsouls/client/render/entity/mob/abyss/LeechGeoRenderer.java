package github.nitespring.darkestsouls.client.render.entity.mob.abyss;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.mob.abyss.Leech;
import github.nitespring.darkestsouls.common.entity.mob.abyss.SewerCentipede;
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

public class LeechGeoRenderer extends GeoEntityRenderer<Leech>{

	public LeechGeoRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new SewerCentipedeModel());
        
        this.shadowRadius = 0.5F;
     
       
    }
	
	@Override
	protected float getDeathMaxRotation(Leech entityLivingBaseIn) {
		
		return 0f;
	}
	
	@Override
	public int getPackedOverlay(Leech animatable, float u) {

		return OverlayTexture.NO_OVERLAY;
	}

	
	 @Override
	public RenderType getRenderType(Leech animatable, ResourceLocation texture, MultiBufferSource bufferSource,
			float partialTick) {
		 return RenderType.entityCutoutNoCull(texture);
	}
	 
	 @Override
	public void render(Leech entity, float entityYaw, float partialTick, PoseStack poseStack,
			MultiBufferSource bufferSource, int packedLight) {
		 float scaleFactor = 1.0f;
		 poseStack.pushPose();
		 poseStack.scale(scaleFactor, scaleFactor, scaleFactor);

		 poseStack.translate(0, 0, 0);

		 super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
		 poseStack.popPose();
	}


    public static class SewerCentipedeModel extends GeoModel<Leech> {

        @Override
        public ResourceLocation getAnimationResource(Leech animatable) {

            return new ResourceLocation(DarkestSouls.MODID, "animations/leech.animation.json");
        }

        @Override
        public ResourceLocation getModelResource(Leech object) {

            return new ResourceLocation(DarkestSouls.MODID, "geo/leech.geo.json");
        }

        @Override
        public ResourceLocation getTextureResource(Leech object) {

            return new ResourceLocation(DarkestSouls.MODID, "textures/entity/leech.png");
        }


        @Override
        public void setCustomAnimations(Leech entity, long uniqueID, AnimationState<Leech> customPredicate) {
            super.setCustomAnimations(entity, uniqueID, customPredicate);
            CoreGeoBone hips = this.getAnimationProcessor().getBone("hips_rotation");
            CoreGeoBone waist = this.getAnimationProcessor().getBone("waist_rotation");
            CoreGeoBone neck = this.getAnimationProcessor().getBone("neck_rotation");
            CoreGeoBone neck1 = this.getAnimationProcessor().getBone("neck1_rotation");
            CoreGeoBone neck2 = this.getAnimationProcessor().getBone("neck2_rotation");
            CoreGeoBone head = this.getAnimationProcessor().getBone("head_rotation");

            assert customPredicate != null;
            EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
            hips.setRotX(extraData.headPitch() *0.15f* ((float) Math.PI / 180F));
            hips.setRotY(extraData.netHeadYaw() *0.15f* ((float) Math.PI / 180F));
            waist.setRotX(extraData.headPitch() *0.15f* ((float) Math.PI / 180F));
            waist.setRotY(extraData.netHeadYaw() *0.15f* ((float) Math.PI / 180F));
            neck.setRotX(extraData.headPitch() *0.15f* ((float) Math.PI / 180F));
            neck.setRotY(extraData.netHeadYaw() *0.15f* ((float) Math.PI / 180F));
            neck1.setRotX(extraData.headPitch() *0.15f* ((float) Math.PI / 180F));
            neck1.setRotY(extraData.netHeadYaw() *0.15f* ((float) Math.PI / 180F));
            neck2.setRotX(extraData.headPitch() *0.15f* ((float) Math.PI / 180F));
            neck2.setRotY(extraData.netHeadYaw() *0.15f* ((float) Math.PI / 180F));
            head.setRotX(extraData.headPitch() *0.15f* ((float) Math.PI / 180F));
            head.setRotY(extraData.netHeadYaw() *0.15f* ((float) Math.PI / 180F));


        }

    }
}
