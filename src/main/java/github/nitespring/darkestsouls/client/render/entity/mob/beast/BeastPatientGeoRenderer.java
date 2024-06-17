package github.nitespring.darkestsouls.client.render.entity.mob.beast;

import com.mojang.blaze3d.vertex.PoseStack;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.client.render.entity.mob.abyss.MonstruosityOfSinEmissiveLayer;
import github.nitespring.darkestsouls.common.entity.mob.abyss.MonstruosityOfSin;
import github.nitespring.darkestsouls.common.entity.mob.beast.BeastPatient;
import github.nitespring.darkestsouls.common.entity.mob.beast.BeastPatientEntity;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.Bonewheel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BeastPatientGeoRenderer<T extends BeastPatientEntity & GeoEntity> extends GeoEntityRenderer<T>{

	public BeastPatientGeoRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new BeastPatientModel());
        
        this.shadowRadius = 0.5F;
        this.addRenderLayer(new BeastPatientEmissiveLayer<T>(this));
     
       
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
         if(entity.getBeastPatientType()==2){scaleFactor=1.4f;}
		 poseStack.pushPose();
		 poseStack.scale(scaleFactor, scaleFactor, scaleFactor);

		 poseStack.translate(0, 0, 0);

		 super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
		 poseStack.popPose();
	}


    public static class BeastPatientModel<T extends BeastPatientEntity & GeoEntity> extends GeoModel<T> {

        @Override
        public ResourceLocation getAnimationResource(T animatable) {

            return ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "animations/beast_patient.animation.json");
        }

        @Override
        public ResourceLocation getModelResource(T object) {

            return ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "geo/beast_patient.geo.json");
        }

        @Override
        public ResourceLocation getTextureResource(T object) {

            return ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/beast/beast_patient.png");
        }


        @Override
        public void setCustomAnimations(T entity, long uniqueID, AnimationState<T> customPredicate) {
            super.setCustomAnimations(entity, uniqueID, customPredicate);
            GeoBone head = this.getAnimationProcessor().getBone("head_rotation");
            GeoBone cloakHead = this.getAnimationProcessor().getBone("cloak_head");
            GeoBone cloakChest = this.getAnimationProcessor().getBone("cloak_chest");
            GeoBone cloakRightArm = this.getAnimationProcessor().getBone("cloak_arm_right");
            GeoBone cloakLeftArm = this.getAnimationProcessor().getBone("cloak_arm_left");
            GeoBone hair = this.getAnimationProcessor().getBone("hair");
            GeoBone beard = this.getAnimationProcessor().getBone("beard2");
            assert customPredicate != null;
            EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(extraData.headPitch() * ((float) Math.PI / 180F));
            head.setRotY(extraData.netHeadYaw() *0.5f* ((float) Math.PI / 180F));
            if(entity.getBeastPatientType()==0){
                cloakHead.setHidden(true);
                cloakChest.setHidden(true);
                cloakRightArm.setHidden(true);
                cloakLeftArm.setHidden(true);
                hair.setHidden(false);
                beard.setHidden(false);
            }else{
                cloakHead.setHidden(false);
                cloakChest.setHidden(false);
                cloakRightArm.setHidden(false);
                cloakLeftArm.setHidden(false);
                hair.setHidden(true);
                beard.setHidden(true);
            }


        }

    }
}
