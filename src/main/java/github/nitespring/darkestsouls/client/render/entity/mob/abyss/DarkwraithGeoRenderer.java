package github.nitespring.darkestsouls.client.render.entity.mob.abyss;

import com.mojang.blaze3d.vertex.PoseStack;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.client.render.entity.mob.hollow.HollowHatLayer;
import github.nitespring.darkestsouls.client.render.entity.mob.hollow.HollowItemLayer;
import github.nitespring.darkestsouls.client.render.entity.mob.hollow.HollowModel;
import github.nitespring.darkestsouls.client.render.entity.mob.hollow.HollowRobeLayer;
import github.nitespring.darkestsouls.common.entity.mob.abyss.Darkwraith;
import github.nitespring.darkestsouls.common.entity.mob.hollow.Hollow;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;

import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DarkwraithGeoRenderer<T extends Darkwraith & GeoEntity> extends GeoEntityRenderer<T>{

	public DarkwraithGeoRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new DarkwraithModel<T>());
        this.addRenderLayer(new DarkwraithItemLayer<T>(this));
		this.addRenderLayer(new DarkwraithEmissiveLayer<T>(this));
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
		 float scaleFactor = 1.08f;
		 poseStack.pushPose();
		 poseStack.scale(scaleFactor, scaleFactor, scaleFactor);

		 poseStack.translate(0, 0, 0);

		 super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
		 poseStack.popPose();
	}


	public static class DarkwraithModel<T extends GeoEntity> extends GeoModel<T> {

		@Override
		public ResourceLocation getAnimationResource(T animatable) {

			return ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "animations/darkwraith.animation.json");
		}

		@Override
		public ResourceLocation getModelResource(T object) {

			return ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "geo/darkwraith.geo.json");
		}

		@Override
		public ResourceLocation getTextureResource(T object) {
					return ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/darkwraith/darkwraith.png");

		}

		@Override
		public void setCustomAnimations(T entity, long uniqueID, AnimationState<T> customPredicate) {
			super.setCustomAnimations(entity, uniqueID, customPredicate);
			GeoBone head = this.getAnimationProcessor().getBone("head_rotation");
			GeoBone waist = this.getAnimationProcessor().getBone("waist_low_rotation");
			assert customPredicate != null;
			EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
			head.setRotX(extraData.headPitch() * ((float) Math.PI / 180F));
			head.setRotY(extraData.netHeadYaw() *0.5f* ((float) Math.PI / 180F));
			waist.setRotY(extraData.netHeadYaw() *0.05f* ((float) Math.PI / 180F));
		}

	}
}
