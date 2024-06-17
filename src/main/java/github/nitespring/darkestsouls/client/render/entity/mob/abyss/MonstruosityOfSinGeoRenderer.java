package github.nitespring.darkestsouls.client.render.entity.mob.abyss;

import com.mojang.blaze3d.vertex.PoseStack;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.mob.abyss.MonstruosityOfSin;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MonstruosityOfSinGeoRenderer extends GeoEntityRenderer<MonstruosityOfSin>{

	public MonstruosityOfSinGeoRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new MonstruosityOfSinModel());
        
        this.shadowRadius = 1.25F;
        this.addRenderLayer(new MonstruosityOfSinEmissiveLayer<MonstruosityOfSin>(this));
     
       
    }
	
	@Override
	protected float getDeathMaxRotation(MonstruosityOfSin entityLivingBaseIn) {
		
		return 0f;
	}
	
	@Override
	public int getPackedOverlay(MonstruosityOfSin animatable, float u, float partialTick) {

		return OverlayTexture.NO_OVERLAY;
	}

	
	 @Override
	public RenderType getRenderType(MonstruosityOfSin animatable, ResourceLocation texture, MultiBufferSource bufferSource,
			float partialTick) {
		 return RenderType.entityCutoutNoCull(texture);
	}
	 
	 @Override
	public void render(MonstruosityOfSin entity, float entityYaw, float partialTick, PoseStack poseStack,
			MultiBufferSource bufferSource, int packedLight) {
		 float scaleFactor = 1.4f;
		 poseStack.pushPose();
		 poseStack.scale(scaleFactor, scaleFactor, scaleFactor);

		 poseStack.translate(0, 0, 0);

		 super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
		 poseStack.popPose();
	}


    public static class MonstruosityOfSinModel extends GeoModel<MonstruosityOfSin> {

        @Override
        public ResourceLocation getAnimationResource(MonstruosityOfSin animatable) {

            return ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "animations/sin.animation.json");
        }

        @Override
        public ResourceLocation getModelResource(MonstruosityOfSin object) {

            return ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "geo/sin.geo.json");
        }

        @Override
        public ResourceLocation getTextureResource(MonstruosityOfSin object) {

            return ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/sin.png");
        }


        @Override
        public void setCustomAnimations(MonstruosityOfSin entity, long uniqueID, AnimationState<MonstruosityOfSin> customPredicate) {
            super.setCustomAnimations(entity, uniqueID, customPredicate);
            GeoBone head = this.getAnimationProcessor().getBone("head_rotation");
            assert customPredicate != null;
            EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(extraData.headPitch() * ((float) Math.PI / 180F));
            head.setRotY(extraData.netHeadYaw() *0.5f* ((float) Math.PI / 180F));


        }

    }
}
