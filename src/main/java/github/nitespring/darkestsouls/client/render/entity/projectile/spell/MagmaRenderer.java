package github.nitespring.darkestsouls.client.render.entity.projectile.spell;

import com.mojang.blaze3d.vertex.PoseStack;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.projectile.spell.FireBallEntity;
import github.nitespring.darkestsouls.common.entity.projectile.spell.MagmaEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MagmaRenderer<T extends MagmaEntity> extends GeoEntityRenderer<T> {
    public MagmaRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new MagmaModel());
        this.addRenderLayer(new MagmaEmissiveLayer<>(this));
    }

    @Override
    protected int getBlockLightLevel(T p_114496_, BlockPos p_114497_) {
        return 15;
    }

    @Override
    protected int getSkyLightLevel(T p_114509_, BlockPos p_114510_) {
        return 15;
    }

    @Override
    public RenderType getRenderType(T animatable, ResourceLocation texture, MultiBufferSource bufferSource,
                                    float partialTick) {
        return RenderType.entityCutout(texture);
    }

    @Override
    public void render(T entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        float scaleFactor = 1.0f;
        poseStack.pushPose();
        poseStack.scale(scaleFactor, scaleFactor, scaleFactor);

        //poseStack.translate(0, 6-scaleFactor*18, 0);
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        poseStack.popPose();
    }
    public static class MagmaModel<T extends MagmaEntity> extends GeoModel<T>{

        @Override
        public ResourceLocation getModelResource(T animatable) {
            return new ResourceLocation(DarkestSouls.MODID, "geo/magma.geo.json");
        }

        @Override
        public ResourceLocation getTextureResource(T animatable) {
            return new ResourceLocation(DarkestSouls.MODID, "textures/entity/projectiles/magma.png");
        }

        @Override
        public ResourceLocation getAnimationResource(T animatable) {
            return new ResourceLocation(DarkestSouls.MODID, "animations/magma.animation.json");
        }
    }
}
