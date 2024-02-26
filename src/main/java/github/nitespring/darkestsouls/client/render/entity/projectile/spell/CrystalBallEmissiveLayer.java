package github.nitespring.darkestsouls.client.render.entity.projectile.spell;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.projectile.spell.CrystalBallEntity;
import github.nitespring.darkestsouls.common.entity.projectile.spell.FireBallEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class CrystalBallEmissiveLayer<T extends CrystalBallEntity & GeoEntity> extends GeoRenderLayer<T> {

	private static final ResourceLocation PURPLE = new ResourceLocation(DarkestSouls.MODID, "textures/entity/projectiles/crystal_ball.png");
	private static final ResourceLocation DARK_BLUE = new ResourceLocation(DarkestSouls.MODID, "textures/entity/projectiles/crystal_ball_dark_blue.png");
	private static final ResourceLocation LIGHT_BLUE = new ResourceLocation(DarkestSouls.MODID, "textures/entity/projectiles/crystal_ball_blue.png");


	public CrystalBallEmissiveLayer(GeoRenderer<T> entityRendererIn) {
		super(entityRendererIn);

	}


	@Override
	public void render(PoseStack poseStack, T animatable, BakedGeoModel bakedModel,
					   RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick,
					   int packedLight, int packedOverlay) {

		RenderType cameo = RenderType.eyes(LIGHT_BLUE);

		poseStack.pushPose();


		switch(animatable.getCrystalType()) {
			case 1:
				cameo = RenderType.eyes(PURPLE);
				break;
			case 2:
				cameo = RenderType.eyes(DARK_BLUE);
				break;
			default:
				cameo = RenderType.eyes(LIGHT_BLUE);
				break;
		}



		this.getRenderer().reRender(bakedModel, poseStack, bufferSource, animatable, cameo, bufferSource.getBuffer(cameo), partialTick, packedLight, packedOverlay, 1f, 1f, 1f, 1f);
		poseStack.popPose();

	}

}