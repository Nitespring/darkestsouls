package github.nitespring.darkestsouls.client.render.entity.mob.hollow;

import com.mojang.blaze3d.vertex.PoseStack;
import github.nitespring.darkestsouls.common.entity.mob.hollow.Hollow;
import github.nitespring.darkestsouls.common.entity.mob.hollow.HollowSoldierLongsword;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HollowGeoRenderer<T extends Hollow & GeoEntity> extends GeoEntityRenderer<T>{

	public HollowGeoRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new HollowModel());
        this.addRenderLayer(new HollowItemLayer<T>(this));
		this.addRenderLayer(new HollowRobeLayer<>(this));
		this.addRenderLayer(new HollowHatLayer<>(this));
        this.shadowRadius = 0.5F;
     
       
    }
	
	@Override
	protected float getDeathMaxRotation(T entityLivingBaseIn) {
		
		return 0f;
	}
	
	@Override
	public int getPackedOverlay(T animatable, float u, float partialTick) {

		return OverlayTexture.NO_OVERLAY;
	}

	
	 @Override
	public RenderType getRenderType(T animatable, ResourceLocation texture, MultiBufferSource bufferSource,
			float partialTick) {
		 return RenderType.entityCutoutNoCull(texture);
	}
	 
	 @Override
	public void render(T entity, float entityYaw, float partialTick, PoseStack poseStack,
			MultiBufferSource bufferSource, int packedLight) {
		 float scaleFactor = 1.0f;
		 poseStack.pushPose();
		 poseStack.scale(scaleFactor, scaleFactor, scaleFactor);

		 poseStack.translate(0, 0, 0);

		 super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
		 poseStack.popPose();
	}








}
