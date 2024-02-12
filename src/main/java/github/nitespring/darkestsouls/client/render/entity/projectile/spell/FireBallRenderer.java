package github.nitespring.darkestsouls.client.render.entity.projectile.spell;

import com.mojang.blaze3d.vertex.PoseStack;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.projectile.spell.FireBallEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class FireBallRenderer<T extends FireBallEntity & GeoEntity> extends GeoEntityRenderer<T> {
    public FireBallRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FireballModel());
        this.addRenderLayer(new FireBallEmissiveLayer<>(this));
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
        float scaleFactor = entity.getDimensionScale();
        poseStack.pushPose();
        poseStack.scale(scaleFactor, scaleFactor, scaleFactor);

        //poseStack.translate(0, 6-scaleFactor*18, 0);
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        poseStack.popPose();
    }
    public static class FireballModel<T extends FireBallEntity & GeoEntity> extends GeoModel<T>{

        @Override
        public ResourceLocation getModelResource(T animatable) {
            return new ResourceLocation(DarkestSouls.MODID, "geo/fireball.geo.json");
        }

        @Override
        public ResourceLocation getTextureResource(T animatable) {
            switch(animatable.getFireballType()) {
                case 1:
                    return new ResourceLocation(DarkestSouls.MODID, "textures/entity/projectiles/chaos_fireball.png");
                case 2:
                    return new ResourceLocation(DarkestSouls.MODID, "textures/entity/projectiles/black_fireball.png");
                case 3:
                    return new ResourceLocation(DarkestSouls.MODID, "textures/entity/projectiles/soul_fireball.png");
                default:
                    return new ResourceLocation(DarkestSouls.MODID, "textures/entity/projectiles/fireball.png");
            }
        }

        @Override
        public ResourceLocation getAnimationResource(T animatable) {
            return new ResourceLocation(DarkestSouls.MODID, "animations/fireball.animation.json");
        }
    }
}
