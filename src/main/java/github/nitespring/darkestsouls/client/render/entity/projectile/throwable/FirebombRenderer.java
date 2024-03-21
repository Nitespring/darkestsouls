package github.nitespring.darkestsouls.client.render.entity.projectile.throwable;

import com.mojang.blaze3d.vertex.PoseStack;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.client.render.entity.projectile.bullet.BulletModel;
import github.nitespring.darkestsouls.common.entity.projectile.throwable.FirebombEntity;
import github.nitespring.darkestsouls.core.event.ClientListener;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;

public class FirebombRenderer<T extends FirebombEntity> extends EntityRenderer<T> {

    public static final ResourceLocation MAIN = new ResourceLocation(DarkestSouls.MODID, "textures/entity/projectiles/firebomb.png");
    public static final ResourceLocation BLACK = new ResourceLocation(DarkestSouls.MODID, "textures/entity/projectiles/black_firebomb.png");
    private final BulletModel<T> model;

    public FirebombRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model =  new BulletModel<>(context.bakeLayer(ClientListener.BULLET));
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
    public void render(T e, float p_114486_, float p_114487_, PoseStack p_114488_, MultiBufferSource p_114489_, int p_114490_) {

        if(!e.isExploding) {
            super.render(e, p_114486_, p_114487_, p_114488_, p_114489_, p_114490_);
        }
    }
}
