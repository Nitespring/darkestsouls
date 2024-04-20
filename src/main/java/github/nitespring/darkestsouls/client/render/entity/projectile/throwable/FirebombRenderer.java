package github.nitespring.darkestsouls.client.render.entity.projectile.throwable;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.client.render.entity.projectile.bullet.BulletModel;
import github.nitespring.darkestsouls.common.entity.projectile.throwable.FirebombEntity;
import github.nitespring.darkestsouls.core.event.ClientListener;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;

public class FirebombRenderer<T extends FirebombEntity> extends EntityRenderer<T> {

    public static final ResourceLocation MAIN = new ResourceLocation(DarkestSouls.MODID, "textures/entity/projectiles/firebomb.png");
    public static final ResourceLocation BLACK = new ResourceLocation(DarkestSouls.MODID, "textures/entity/projectiles/black_firebomb.png");
    private final BulletModel<T> model;

    public FirebombRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model =  new BulletModel<T>(context.bakeLayer(ClientListener.BULLET));
    }

    @Override
    public ResourceLocation getTextureLocation(T p_114482_) {
        switch (p_114482_.getBombType()) {
            case 1:
                return BLACK;
            default:
                return MAIN;
        }
    }

    @Override
    public void render(T e, float f1, float f2, PoseStack stack, MultiBufferSource buf, int i) {
        VertexConsumer vertexconsumer;
        vertexconsumer = buf.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(e)));
        stack.pushPose();
        stack.scale(0.5f, 0.5f, 0.5f);
        stack.mulPose(Axis.XP.rotationDegrees(180));
        stack.translate(0, -1.8f, 0);

        if(!e.isExploding) {
            this.model.renderToBuffer(stack, vertexconsumer, i, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            super.render(e, f1, f2, stack, buf, i);
        }
        stack.popPose();
    }
}
