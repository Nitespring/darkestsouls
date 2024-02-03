package github.nitespring.darkestsouls.client.render.entity.projectile.spell;

import com.mojang.blaze3d.vertex.PoseStack;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.Bonewheel;
import github.nitespring.darkestsouls.common.entity.projectile.spell.SoulDart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SoulDartRenderer extends GeoEntityRenderer<SoulDart> {
    public SoulDartRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SoulDartModel());
        this.shadowRadius = 0.5F;
    }


    @Override
    public RenderType getRenderType(SoulDart animatable, ResourceLocation texture, MultiBufferSource bufferSource,
                                    float partialTick) {
        return RenderType.entityCutoutNoCull(texture);
    }

    @Override
    public void render(SoulDart entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        float scaleFactor = 1.0f;
        poseStack.pushPose();
        poseStack.scale(scaleFactor, scaleFactor, scaleFactor);

        poseStack.translate(0, 0, 0);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        poseStack.popPose();
    }
    public static class SoulDartModel extends GeoModel<SoulDart>{


        @Override
        public ResourceLocation getModelResource(SoulDart animatable) {
            return new ResourceLocation(DarkestSouls.MODID, "geo/soul_dart.geo.json");
        }

        @Override
        public ResourceLocation getTextureResource(SoulDart animatable) {
            return new ResourceLocation(DarkestSouls.MODID, "textures/entity/projectiles/soul_dart.png");
        }

        @Override
        public ResourceLocation getAnimationResource(SoulDart animatable) {
            return new ResourceLocation(DarkestSouls.MODID, "animations/soul_dart.animation.json");
        }
    }
}
