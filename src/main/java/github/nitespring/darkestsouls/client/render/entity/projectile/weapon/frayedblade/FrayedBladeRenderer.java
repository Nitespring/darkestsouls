package github.nitespring.darkestsouls.client.render.entity.projectile.weapon.frayedblade;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.client.render.entity.projectile.SquareTextureEntityModel;
import github.nitespring.darkestsouls.common.entity.projectile.weapon.melee.FrayedBladeAttackEntity;
import github.nitespring.darkestsouls.core.event.ClientListener;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class FrayedBladeRenderer<T extends FrayedBladeAttackEntity> extends EntityRenderer<T>{

	public static final ResourceLocation TEXTURE_LOCATION_0 = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/frayed_blade/frayed_blade0.png");
	public static final ResourceLocation TEXTURE_LOCATION_1 = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/frayed_blade/frayed_blade1.png");
	public static final ResourceLocation TEXTURE_LOCATION_2 = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/frayed_blade/frayed_blade2.png");
	public static final ResourceLocation TEXTURE_LOCATION_3 = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/frayed_blade/frayed_blade3.png");
	public static final ResourceLocation TEXTURE_LOCATION_4 = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/frayed_blade/frayed_blade4.png");
	public static final ResourceLocation TEXTURE_LOCATION_5 = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/frayed_blade/frayed_blade5.png");
	public static final ResourceLocation TEXTURE_LOCATION_6 = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/frayed_blade/frayed_blade6.png");
	public static final ResourceLocation TEXTURE_LOCATION_7 = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/frayed_blade/frayed_blade7.png");
	public static final ResourceLocation TEXTURE_LOCATION_8 = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/frayed_blade/frayed_blade8.png");
	public static final ResourceLocation TEXTURE_LOCATION_9 = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/frayed_blade/frayed_blade9.png");
	public static final ResourceLocation TEXTURE_LOCATION_10 = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/frayed_blade/frayed_blade10.png");
	public static final ResourceLocation TEXTURE_LOCATION_11 = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/frayed_blade/frayed_blade11.png");
	public static final ResourceLocation TEXTURE_LOCATION_12 = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/frayed_blade/frayed_blade12.png");
	public static final ResourceLocation TEXTURE_LOCATION_13 = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/frayed_blade/frayed_blade13.png");
	public static final ResourceLocation TEXTURE_LOCATION_14 = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/frayed_blade/frayed_blade14.png");
	public static final ResourceLocation TEXTURE_LOCATION_15 = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/frayed_blade/frayed_blade15.png");
	public static final ResourceLocation TEXTURE_LOCATION_16 = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/frayed_blade/frayed_blade16.png");
	public static final ResourceLocation TEXTURE_LOCATION_17 = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/frayed_blade/frayed_blade17.png");
	public static final ResourceLocation TEXTURE_LOCATION_18 = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/frayed_blade/frayed_blade18.png");
	public static final ResourceLocation TEXTURE_LOCATION_19 = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/frayed_blade/frayed_blade19.png");
	 private final SquareTextureEntityModel<T> model;

	//EvokerFangsRenderer


	public FrayedBladeRenderer(Context context) {
		super(context);
		this.model = new SquareTextureEntityModel<>(context.bakeLayer(ClientListener.SQUARE_TEXTURE));
		
	}

	@Override
	public ResourceLocation getTextureLocation(T e) {
		
		switch(e.getAnimationState()) {
		case 1:
			return TEXTURE_LOCATION_1;
		case 2:
			return TEXTURE_LOCATION_2;
		case 3:
			return TEXTURE_LOCATION_3;
		case 4:
			return TEXTURE_LOCATION_4;
		case 5:
			return TEXTURE_LOCATION_5;
		case 6:
			return TEXTURE_LOCATION_6;
		case 7:
			return TEXTURE_LOCATION_7;
		case 8:
			return TEXTURE_LOCATION_8;
		case 9:
			return TEXTURE_LOCATION_9;
		case 10:
			return TEXTURE_LOCATION_10;
		case 11:
				return TEXTURE_LOCATION_11;
		case 12:
				return TEXTURE_LOCATION_12;
		case 13:
				return TEXTURE_LOCATION_13;
		case 14:
				return TEXTURE_LOCATION_14;
		case 15:
				return TEXTURE_LOCATION_15;
		case 16:
				return TEXTURE_LOCATION_16;
		case 17:
				return TEXTURE_LOCATION_17;
		case 18:
				return TEXTURE_LOCATION_18;
		case 19:
				return TEXTURE_LOCATION_19;
		default:
			return TEXTURE_LOCATION_0;
		}
	}
	
	@Override
	 public void render(T entity, float p_114529_, float p_114530_, PoseStack stack, MultiBufferSource p_114532_, int p_114533_) {
	     
		 VertexConsumer vertexconsumer;
	      stack.pushPose();
	      
	      stack.mulPose(Axis.YP.rotationDegrees(90-entity.getYRot()));
	      stack.mulPose(Axis.ZP.rotationDegrees(170));
	      stack.mulPose(Axis.XP.rotationDegrees(-80));


		  stack.translate(0, -5.5, -0.5);
	      stack.scale(3.25f, 3.25f, 3.25f);
	      vertexconsumer = p_114532_.getBuffer(RenderType.entityTranslucentEmissive(getTextureLocation(entity)));

	         
	         this.model.renderToBuffer(stack, vertexconsumer, p_114533_, OverlayTexture.NO_OVERLAY, 1 );
	      stack.popPose();
	         super.render(entity, p_114529_, p_114530_, stack, p_114532_, 255);
	      
	}

	

	
}
