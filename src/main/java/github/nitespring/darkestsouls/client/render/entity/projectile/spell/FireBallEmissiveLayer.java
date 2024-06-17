package github.nitespring.darkestsouls.client.render.entity.projectile.spell;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.projectile.spell.FireBallEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class FireBallEmissiveLayer<T extends FireBallEntity & GeoEntity> extends GeoRenderLayer<T> {

	private static final ResourceLocation CLASSIC = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/projectiles/fireball.png");
	private static final ResourceLocation CHAOS = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/projectiles/chaos_fireball.png");
	private static final ResourceLocation BLACK = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/projectiles/black_fireball.png");

	private static final ResourceLocation SOUL = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/projectiles/soul_fireball.png");

	public FireBallEmissiveLayer(GeoRenderer<T> entityRendererIn) {
		super(entityRendererIn);

	}


	@Override
	public void render(PoseStack poseStack, T animatable, BakedGeoModel bakedModel,
					   RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick,
					   int packedLight, int packedOverlay) {

		RenderType cameo = RenderType.eyes(CLASSIC);

		poseStack.pushPose();


		switch(animatable.getFireballType()) {
			case 1:
				cameo = RenderType.eyes(CHAOS);
				break;
			case 2:
				cameo = RenderType.eyes(BLACK);
				break;
			case 3:
				cameo = RenderType.eyes(SOUL);
				break;
			default:
				cameo = RenderType.eyes(CLASSIC);
				break;
		}



		this.getRenderer().reRender(bakedModel, poseStack, bufferSource, animatable, cameo, bufferSource.getBuffer(cameo), partialTick, packedLight, packedOverlay, -1);
		poseStack.popPose();

	}

}