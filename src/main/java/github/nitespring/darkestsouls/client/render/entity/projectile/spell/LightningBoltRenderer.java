package github.nitespring.darkestsouls.client.render.entity.projectile.spell;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.projectile.spell.LightningBolt;
import github.nitespring.darkestsouls.common.entity.projectile.spell.SoulDartEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class LightningBoltRenderer<T extends LightningBolt & GeoEntity> extends GeoEntityRenderer<T> {
    public LightningBoltRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new LightningBoltModel());
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
        poseStack.mulPose(Axis.YP.rotationDegrees(90+entity.getYRot()));
        poseStack.mulPose(Axis.ZP.rotationDegrees(-entity.getXRot()));

        poseStack.translate(0, 0.5, 0);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, 255);
        poseStack.popPose();
    }
    public static class LightningBoltModel<T extends LightningBolt & GeoEntity> extends GeoModel<T>{


        @Override
        public ResourceLocation getModelResource(T animatable) {
            return new ResourceLocation(DarkestSouls.MODID, "geo/lightning_bolt.geo.json");
        }

        @Override
        public ResourceLocation getTextureResource(T animatable) {
            return new ResourceLocation(DarkestSouls.MODID, "textures/entity/projectiles/lightning_bolt.png");
        }

        @Override
        public ResourceLocation getAnimationResource(T animatable) {
            return new ResourceLocation(DarkestSouls.MODID, "animations/lightning_bolt.animation.json");
        }
    }
}
