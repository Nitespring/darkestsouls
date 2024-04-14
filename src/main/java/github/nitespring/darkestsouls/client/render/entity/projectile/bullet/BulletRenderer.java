package github.nitespring.darkestsouls.client.render.entity.projectile.bullet;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.projectile.weapon.Bullet;
import github.nitespring.darkestsouls.core.event.ClientListener;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class BulletRenderer <T extends Bullet> extends EntityRenderer<T> {

    public static final ResourceLocation MAIN = new ResourceLocation(DarkestSouls.MODID, "textures/entity/projectiles/bullet.png");
    private final BulletModel<T> model;

    public BulletRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model =  new BulletModel<>(context.bakeLayer(ClientListener.BULLET));
    }

    @Override
    public ResourceLocation getTextureLocation(T p_114482_) {
        return MAIN;
    }

    @Override
    public void render(T e, float f1, float f2, PoseStack stack, MultiBufferSource buf, int i) {
        VertexConsumer vertexconsumer;
        vertexconsumer = buf.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(e)));
        stack.pushPose();
        stack.scale(e.getSize()*0.25f, e.getSize()*0.25f, e.getSize()*0.25f);
        //stack.mulPose(Axis.XP.rotationDegrees(180));
        //stack.translate(0, -1.8f, 0);

            this.model.renderToBuffer(stack, vertexconsumer, i, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            super.render(e, f1, f2, stack, buf, i);
        stack.popPose();
    }
}
