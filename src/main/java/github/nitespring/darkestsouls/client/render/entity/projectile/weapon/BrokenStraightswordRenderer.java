package github.nitespring.darkestsouls.client.render.entity.projectile.weapon;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.client.render.entity.projectile.SquareTextureEntityModel;
import github.nitespring.darkestsouls.common.entity.projectile.weapon.melee.WeaponAttackEntity;
import github.nitespring.darkestsouls.core.event.ClientListener;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class BrokenStraightswordRenderer<T extends WeaponAttackEntity> extends EntityRenderer<T>{

	public static final ResourceLocation TEXTURE_LOCATION_0 = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/frayed_blade/frayed_blade0.png");
	public static final ResourceLocation TEXTURE_LOCATION_1 = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/uchigatana/uchigatana_0.png");
	public static final ResourceLocation TEXTURE_LOCATION_2 = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/uchigatana/uchigatana_1.png");
	public static final ResourceLocation TEXTURE_LOCATION_3 = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/uchigatana/uchigatana_2.png");
	public static final ResourceLocation TEXTURE_LOCATION_4 = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/uchigatana/uchigatana_3.png");
	public static final ResourceLocation TEXTURE_LOCATION_5 = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/uchigatana/uchigatana_4.png");
	public static final ResourceLocation TEXTURE_LOCATION_6 = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/uchigatana/uchigatana_5.png");
	public static final ResourceLocation TEXTURE_LOCATION_7 = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/uchigatana/uchigatana_6.png");
	public static final ResourceLocation TEXTURE_LOCATION_8 = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/uchigatana/uchigatana_7.png");

	 private final SquareTextureEntityModel<T> model;

	//EvokerFangsRenderer


	public BrokenStraightswordRenderer(Context context) {
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


		  stack.translate(0.2, -2.75, -0.75);
	      stack.scale(1.75f, 1.75f, 1.75f);
	      vertexconsumer = p_114532_.getBuffer(RenderType.entityTranslucentEmissive(getTextureLocation(entity)));

	         
	         this.model.renderToBuffer(stack, vertexconsumer, p_114533_, OverlayTexture.NO_OVERLAY, 1);
	      stack.popPose();
	         super.render(entity, p_114529_, p_114530_, stack, p_114532_, 255);
	      
	}

	

	
}
