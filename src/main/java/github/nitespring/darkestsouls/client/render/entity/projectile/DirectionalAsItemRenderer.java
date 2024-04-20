package github.nitespring.darkestsouls.client.render.entity.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import github.nitespring.darkestsouls.core.interfaces.CustomItemSupplier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public class DirectionalAsItemRenderer<T extends Entity & CustomItemSupplier> extends EntityRenderer<T> {
    private final ItemRenderer itemRenderer;
    public DirectionalAsItemRenderer(Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }
    @Override
    public ResourceLocation getTextureLocation(T e) {return null;}

    @Override
    public void render(T entity, float f1, float f2, PoseStack poseStack, MultiBufferSource buf, int i) {
        ItemStack stack = entity.getItem();
        float size = (float) entity.getSize();
        Vec3 mov = entity.getDeltaMovement();
        double d0 = mov.horizontalDistance();
        poseStack.pushPose();
        poseStack.scale(size,size,size);
        poseStack.translate(0,0.4,0);
        poseStack.mulPose(Axis.YP.rotationDegrees(-90+entity.getYRot()));
        if(entity.getZTilt()!=0&&entity.getXRot()>=-30&&entity.getXRot()<=30) {
            poseStack.mulPose(Axis.XP.rotationDegrees(entity.getZTilt()));
        }
        poseStack.mulPose(Axis.ZP.rotationDegrees(-45 + entity.getXRot()));



        if(entity.tickCount>=1) {
            itemRenderer.renderStatic(null, stack, ItemDisplayContext.NONE, true, poseStack, buf, entity.level(), i, i, i);
        }
        poseStack.popPose();

    }
}
