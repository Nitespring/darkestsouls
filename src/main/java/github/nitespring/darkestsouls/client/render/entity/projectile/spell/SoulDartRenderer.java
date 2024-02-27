package github.nitespring.darkestsouls.client.render.entity.projectile.spell;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.Bonewheel;
import github.nitespring.darkestsouls.common.entity.projectile.spell.SoulDart;
import github.nitespring.darkestsouls.common.entity.projectile.spell.SoulDartEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SoulDartRenderer<T extends SoulDartEntity & GeoEntity> extends GeoEntityRenderer<T> {
    public SoulDartRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SoulDartModel());
        this.shadowRadius = 0.5F;
    }


    @Override
    public RenderType getRenderType(T animatable, ResourceLocation texture, MultiBufferSource bufferSource,
                                    float partialTick) {
        return RenderType.eyes(texture);
    }

    @Override
    public void render(T entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        float scaleFactor = entity.getDimensionScale();
        poseStack.pushPose();
        poseStack.scale(scaleFactor, scaleFactor, scaleFactor);
        poseStack.mulPose(Axis.YP.rotationDegrees(entity.getYRot()));
        poseStack.mulPose(Axis.XP.rotationDegrees(-entity.getXRot()));

        poseStack.translate(0, 6-scaleFactor*18, 0);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, 255);
        poseStack.popPose();
    }
    public static class SoulDartModel<T extends SoulDartEntity & GeoEntity> extends GeoModel<T>{


        @Override
        public ResourceLocation getModelResource(T animatable) {
            return new ResourceLocation(DarkestSouls.MODID, "geo/soul_dart.geo.json");
        }

        @Override
        public ResourceLocation getTextureResource(T animatable) {
            return new ResourceLocation(DarkestSouls.MODID, "textures/entity/projectiles/soul_dart_b.png");
        }

        @Override
        public ResourceLocation getAnimationResource(T animatable) {
            return new ResourceLocation(DarkestSouls.MODID, "animations/soul_dart.animation.json");
        }
    }
}
