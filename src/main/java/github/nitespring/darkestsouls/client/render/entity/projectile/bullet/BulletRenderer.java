package github.nitespring.darkestsouls.client.render.entity.projectile.bullet;

import com.mojang.blaze3d.vertex.PoseStack;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.client.render.entity.projectile.SquareTextureEntityModel;
import github.nitespring.darkestsouls.common.entity.projectile.spell.CrystalShardEntity;
import github.nitespring.darkestsouls.core.event.ClientListener;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;

public class BulletRenderer <T extends AbstractHurtingProjectile> extends EntityRenderer<T> {

    public static final ResourceLocation MAIN = new ResourceLocation(DarkestSouls.MODID, "textures/entity/projectiles/bullet.png");
    private final BulletModel<T> model;

    protected BulletRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model =  new BulletModel<>(context.bakeLayer(ClientListener.BULLET));
    }

    @Override
    public ResourceLocation getTextureLocation(T p_114482_) {
        return MAIN;
    }

    @Override
    public void render(T p_114485_, float p_114486_, float p_114487_, PoseStack p_114488_, MultiBufferSource p_114489_, int p_114490_) {


        super.render(p_114485_, p_114486_, p_114487_, p_114488_, p_114489_, p_114490_);
    }
}
