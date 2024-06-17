package github.nitespring.darkestsouls.client.render.entity.projectile.spell;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.client.render.entity.projectile.SquareTextureEntityModel;
import github.nitespring.darkestsouls.common.entity.projectile.spell.CrystalShardEntity;
import github.nitespring.darkestsouls.core.event.ClientListener;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class CrystalShardRenderer<T extends CrystalShardEntity> extends EntityRenderer<T>{

	public static final ResourceLocation LIGHT_BLUE = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/projectiles/crystal_shard_blue.png");
	public static final ResourceLocation DARK_BLUE = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/projectiles/crystal_shard_dark_blue.png");
	public static final ResourceLocation PURPLE = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/projectiles/crystal_shard.png");
	private final SquareTextureEntityModel<T> model;

	//EvokerFangsRenderer


	public CrystalShardRenderer(Context context) {
		super(context);
		this.model = new SquareTextureEntityModel<>(context.bakeLayer(ClientListener.SQUARE_TEXTURE));
		
	}

	@Override
	public ResourceLocation getTextureLocation(T e) {
		
		switch(e.getCrystalType()) {
		case 1:
			return PURPLE;
		case 2:
			return DARK_BLUE;
		default:
			return LIGHT_BLUE;
		}
	}

	@Override
	protected int getBlockLightLevel(T p_114496_, BlockPos p_114497_) {
		return 15;
	}
	@Override
	protected int getSkyLightLevel(T p_114496_, BlockPos p_114497_) {
		return 15;
	}

	@Override
	 public void render(T entity, float p_114529_, float p_114530_, PoseStack stack, MultiBufferSource p_114532_, int p_114533_) {
	     
		VertexConsumer vertexconsumer;
		stack.pushPose();

		stack.mulPose(Axis.YP.rotationDegrees(-135+entity.getYRot()));
		stack.mulPose(Axis.ZP.rotationDegrees(18+entity.getXRot()));
		stack.mulPose(Axis.XP.rotationDegrees(90+15*entity.getzRot()));


		stack.translate(0.06, -0.35, 0);
		stack.scale(0.25f, 0.25f, 0.25f);
		vertexconsumer = p_114532_.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(entity)));

	         
		this.model.renderToBuffer(stack, vertexconsumer, p_114533_, OverlayTexture.NO_OVERLAY, 1);
		stack.popPose();
		super.render(entity, p_114529_, p_114530_, stack, p_114532_, p_114533_);
	      
	}

}
