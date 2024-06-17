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

public class MolotovCocktailRenderer<T extends FirebombEntity> extends EntityRenderer<T> {

    public static final ResourceLocation MAIN = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/projectiles/molotov.png");
    private final MolotovCocktailModel<T> model;

    public MolotovCocktailRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model =  new MolotovCocktailModel<T>(context.bakeLayer(ClientListener.MOLOTOV));
    }

    @Override
    public ResourceLocation getTextureLocation(T p_114482_) {
                return MAIN;
    }

    @Override
    public void render(T e, float f1, float f2, PoseStack stack, MultiBufferSource buf, int i) {
        VertexConsumer vertexconsumer;
        vertexconsumer = buf.getBuffer(RenderType.entityTranslucent(getTextureLocation(e)));
        stack.pushPose();
        stack.scale(0.8f, 0.8f, 0.8f);
        stack.mulPose(Axis.XP.rotationDegrees(170));
        stack.translate(0, -1.5f, 0);

        if(!e.isExploding) {
            this.model.renderToBuffer(stack, vertexconsumer, i, OverlayTexture.NO_OVERLAY, 1);
            super.render(e, f1, f2, stack, buf, i);
        }
        stack.popPose();
    }
}
