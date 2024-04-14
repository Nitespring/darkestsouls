package github.nitespring.darkestsouls.client.render.entity.projectile.weapon.frayedblade;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.projectile.weapon.melee.FrayedBladeFlameEntity;
import github.nitespring.darkestsouls.core.event.ClientListener;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class FrayedBladeFlameRenderer<T extends FrayedBladeFlameEntity> extends EntityRenderer<T>{

	public static final ResourceLocation TEXTURE_LOCATION_0 = new ResourceLocation(DarkestSouls.MODID, "textures/entity/weapons/frayed_blade/frayed_blade_fire0.png");
	public static final ResourceLocation TEXTURE_LOCATION_1 = new ResourceLocation(DarkestSouls.MODID, "textures/entity/weapons/frayed_blade/frayed_blade_fire1.png");

	 private final FrayedBladeFlameModel<T> model;

	//EvokerFangsRenderer


	public FrayedBladeFlameRenderer(Context context) {
		super(context);
		this.model = new FrayedBladeFlameModel<>(context.bakeLayer(ClientListener.FRAYED_BLADE_FLAME));
		
	}

	@Override
	public ResourceLocation getTextureLocation(T e) {


		if (e.tickCount%2==0){
			return TEXTURE_LOCATION_1;
		}else{
			return TEXTURE_LOCATION_0;
		}

	}
	
	@Override
	 public void render(T entity, float p_114529_, float p_114530_, PoseStack stack, MultiBufferSource p_114532_, int p_114533_) {
		float f = entity.getAnimationProgress(p_114530_);
		if (f != 0.0F) {
			float f1 = 2.0F;
			if (f > 0.9F) {
				f1 *= (1.0F - f) / 0.1F;
			}

			VertexConsumer vertexconsumer;
			stack.pushPose();

			stack.mulPose(Axis.YP.rotationDegrees(entity.getYRot()));
			stack.mulPose(Axis.ZP.rotationDegrees(180));
			stack.mulPose(Axis.XP.rotationDegrees(0));

			this.model.setupAnim(entity, f, 0.0F, 0.0F, entity.getYRot(), entity.getXRot());
			stack.translate(0, 0.4, 0);
			stack.scale(0.75f, 1.2f, 0.75f);
			vertexconsumer = p_114532_.getBuffer(RenderType.eyes(getTextureLocation(entity)));


			this.model.renderToBuffer(stack, vertexconsumer, p_114533_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			stack.popPose();
			super.render(entity, p_114529_, p_114530_, stack, p_114532_, 255);
		}
	}

	

	
}
