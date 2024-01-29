package github.nitespring.darkestsouls.client.render.entity.mob.abyss;

import com.mojang.blaze3d.vertex.PoseStack;
import github.nitespring.darkestsouls.common.entity.mob.abyss.SewerCentipede;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.Bonewheel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SewerCentipedeGeoRenderer extends GeoEntityRenderer<SewerCentipede>{

	public SewerCentipedeGeoRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new SewerCentipedeModel());
        
        this.shadowRadius = 0.5F;
     
       
    }
	
	@Override
	protected float getDeathMaxRotation(SewerCentipede entityLivingBaseIn) {
		
		return 0f;
	}
	
	@Override
	public int getPackedOverlay(SewerCentipede animatable, float u) {

		return OverlayTexture.NO_OVERLAY;
	}

	
	 @Override
	public RenderType getRenderType(SewerCentipede animatable, ResourceLocation texture, MultiBufferSource bufferSource,
			float partialTick) {
		 return RenderType.entityCutoutNoCull(texture);
	}
	 
	 @Override
	public void render(SewerCentipede entity, float entityYaw, float partialTick, PoseStack poseStack,
			MultiBufferSource bufferSource, int packedLight) {
		 float scaleFactor = 1.0f;
		 poseStack.pushPose();
		 poseStack.scale(scaleFactor, scaleFactor, scaleFactor);

		 poseStack.translate(0, 0, 0);

		 super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
		 poseStack.popPose();
	}


}
