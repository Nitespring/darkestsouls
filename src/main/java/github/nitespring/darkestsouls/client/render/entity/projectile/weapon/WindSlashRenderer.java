package github.nitespring.darkestsouls.client.render.entity.projectile.weapon;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.client.render.entity.projectile.SquareTextureEntityModel;
import github.nitespring.darkestsouls.common.entity.projectile.spell.CrystalShardEntity;
import github.nitespring.darkestsouls.common.entity.projectile.spell.WindSlash;
import github.nitespring.darkestsouls.core.event.ClientListener;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class WindSlashRenderer<T extends WindSlash> extends EntityRenderer<T>{

	public static final ResourceLocation TEXTURE_LOCATION_0 = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/frayed_blade/frayed_blade0.png");
	public static final ResourceLocation WIND = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/projectiles/wind.png");
	private final SquareTextureEntityModel<T> model;

	//EvokerFangsRenderer


	public WindSlashRenderer(Context context) {
		super(context);
		this.model = new SquareTextureEntityModel<>(context.bakeLayer(ClientListener.SQUARE_TEXTURE));
		
	}

	@Override
	public ResourceLocation getTextureLocation(T e) {
		if(e.lifeTicks>2) {
			return WIND;
		}else{return TEXTURE_LOCATION_0;}
	}

	@Override
	protected int getBlockLightLevel(T e, BlockPos pos) {return super.getBlockLightLevel(e,pos);}
	@Override
	protected int getSkyLightLevel(T e, BlockPos pos) {
		return super.getSkyLightLevel(e,pos);
	}

	@Override
	 public void render(T entity, float p_114529_, float p_114530_, PoseStack stack, MultiBufferSource p_114532_, int p_114533_) {
	     
		VertexConsumer vertexconsumer;
		stack.pushPose();

		stack.mulPose(Axis.YP.rotationDegrees(entity.getYRot()));
		stack.mulPose(Axis.XP.rotationDegrees(89-entity.getXRot()));
		stack.mulPose(Axis.ZP.rotationDegrees(180));


		stack.translate(0, -4.0, -0.5);
		stack.scale(2.25f, 2.25f, 2.25f);
		vertexconsumer = p_114532_.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(entity)));

	         
		this.model.renderToBuffer(stack, vertexconsumer, p_114533_, OverlayTexture.NO_OVERLAY, 1 );
		stack.popPose();
		super.render(entity, p_114529_, p_114530_, stack, p_114532_, p_114533_);
	      
	}

}
