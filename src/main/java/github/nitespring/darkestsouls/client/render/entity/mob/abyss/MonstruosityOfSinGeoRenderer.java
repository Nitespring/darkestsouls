package github.nitespring.darkestsouls.client.render.entity.mob.abyss;

import com.mojang.blaze3d.vertex.PoseStack;
import github.nitespring.darkestsouls.common.entity.mob.abyss.MonstruosityOfSin;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MonstruosityOfSinGeoRenderer extends GeoEntityRenderer<MonstruosityOfSin>{

	public MonstruosityOfSinGeoRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new MonstruosityOfSinModel());
        
        this.shadowRadius = 0.5F;
        this.addRenderLayer(new MonstruosityOfSinEmissiveLayer<MonstruosityOfSin>(this));
     
       
    }
	
	@Override
	protected float getDeathMaxRotation(MonstruosityOfSin entityLivingBaseIn) {
		
		return 0f;
	}
	
	@Override
	public int getPackedOverlay(MonstruosityOfSin animatable, float u) {

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
		 float scaleFactor = 1.2f;
		 poseStack.pushPose();
		 poseStack.scale(scaleFactor, scaleFactor, scaleFactor);

		 poseStack.translate(0, 0, 0);

		 super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
		 poseStack.popPose();
	}


}
