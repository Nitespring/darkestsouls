package github.nitespring.darkestsouls.client.render.entity.mob.church.huntsman;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.mob.church.Huntsman;
import github.nitespring.darkestsouls.common.entity.mob.hollow.Hollow;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class HuntsmanRobeLayer<T extends Huntsman & GeoEntity> extends GeoRenderLayer<T>{

	private static final ResourceLocation BLACK = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/church/huntsman/coat/huntsman_coat_black.png");
	private static final ResourceLocation BLUE = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/church/huntsman/coat/huntsman_coat_blue.png");
	private static final ResourceLocation BROWN = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/church/huntsman/coat/huntsman_coat_brown.png");
	private static final ResourceLocation GRAY = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/church/huntsman/coat/huntsman_coat_gray.png");
	private static final ResourceLocation GREEN = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/church/huntsman/coat/huntsman_coat_green.png");
	private static final ResourceLocation LIGHT_BROWN = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/church/huntsman/coat/huntsman_coat_light_brown.png");
	private static final ResourceLocation YELLOW = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/church/huntsman/coat/huntsman_coat_yellow.png");
	private static final ResourceLocation WHITE = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/church/huntsman/coat/huntsman_coat_white.png");
	private static final ResourceLocation NO_ROBE = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/frayed_blade/frayed_blade0.png");

	public HuntsmanRobeLayer(GeoRenderer<T> entityRendererIn) {
		super(entityRendererIn);
	
	}


	@Override
	public void render(PoseStack poseStack, T animatable, BakedGeoModel bakedModel,
					   RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick,
					   int packedLight, int packedOverlay) {

		RenderType cameo = RenderType.entityCutoutNoCull(BLACK);
		switch (animatable.getRobeType()) {
			case 1:
				cameo = RenderType.entityCutoutNoCull(NO_ROBE);
				break;
			case 2:
				cameo = RenderType.entityCutoutNoCull(BLUE);
				break;
			case 3:
				cameo = RenderType.entityCutoutNoCull(BROWN);
				break;
			case 4:
				cameo = RenderType.entityCutoutNoCull(GRAY);
				break;
			case 5:
				cameo = RenderType.entityCutoutNoCull(GREEN);
				break;
			case 6:
				cameo = RenderType.entityCutoutNoCull(LIGHT_BROWN);
				break;
			case 7:
				cameo = RenderType.entityCutoutNoCull(YELLOW);
				break;
			case 8:
				cameo = RenderType.entityCutoutNoCull(WHITE);
				break;
			default:
				cameo = RenderType.entityCutoutNoCull(BLACK);
				break;
		}
		this.getRenderer().reRender(bakedModel, poseStack, bufferSource, animatable, cameo, bufferSource.getBuffer(cameo), partialTick, packedLight, packedOverlay, -1);
	}

}
