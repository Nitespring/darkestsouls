package github.nitespring.darkestsouls.client.render.entity.mob.church.huntsman;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.mob.church.Huntsman;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class HuntsmanHairLayer<T extends Huntsman & GeoEntity> extends GeoRenderLayer<T>{

	private static final ResourceLocation BLACK = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/church/huntsman/hair/huntsman_hair_black.png");
	private static final ResourceLocation BROWN = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/church/huntsman/hair/huntsman_hair_brown.png");
	private static final ResourceLocation DARK_BROWN = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/church/huntsman/hair/huntsman_hair_dark_brown.png");
	private static final ResourceLocation GRAY = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/church/huntsman/hair/huntsman_hair_grey.png");
	private static final ResourceLocation BLONDE = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/church/huntsman/hair/huntsman_hair_blonde.png");
	private static final ResourceLocation RED = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/church/huntsman/hair/huntsman_hair_red.png");


	public HuntsmanHairLayer(GeoRenderer<T> entityRendererIn) {
		super(entityRendererIn);
	
	}
	

	@Override
	public void render(PoseStack poseStack, T animatable, BakedGeoModel bakedModel,
			RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick,
			int packedLight, int packedOverlay) {

		RenderType cameo = RenderType.entityCutoutNoCull(BLACK);
		switch (animatable.getHairType()) {
			case 1:
				cameo = RenderType.entityCutoutNoCull(BROWN);
				break;
			case 2:
				cameo = RenderType.entityCutoutNoCull(DARK_BROWN);
				break;
			case 3:
				cameo = RenderType.entityCutoutNoCull(GRAY);
				break;
			case 4:
				cameo = RenderType.entityCutoutNoCull(BLONDE);
				break;
			case 5:
				cameo = RenderType.entityCutoutNoCull(RED);
				break;
			default:
				cameo = RenderType.entityCutoutNoCull(BLACK);
				break;
		}
		this.getRenderer().reRender(bakedModel, poseStack, bufferSource, animatable, cameo, bufferSource.getBuffer(cameo), partialTick, packedLight, packedOverlay, -1);
	}


}
	

