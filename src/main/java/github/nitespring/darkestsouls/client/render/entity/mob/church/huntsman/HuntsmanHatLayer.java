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

public class HuntsmanHatLayer<T extends Huntsman & GeoEntity> extends GeoRenderLayer<T>{

	private static final ResourceLocation BLACK = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/church/huntsman/hat/huntsman_hat_black.png");
	private static final ResourceLocation TOP_HAT_BLACK = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/church/huntsman/hat/huntsman_top_hat_black.png");
	private static final ResourceLocation TOP_HAT_WHITE = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/church/huntsman/hat/huntsman_top_hat_white.png");
	private static final ResourceLocation TOP_HAT_GRAY = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/church/huntsman/hat/huntsman_top_hat_gray.png");
	public static final ResourceLocation NO_HAT = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/weapons/frayed_blade/frayed_blade0.png");

	public HuntsmanHatLayer(GeoRenderer<T> entityRendererIn) {
		super(entityRendererIn);
	
	}
	

	@Override
	public void render(PoseStack poseStack, T animatable, BakedGeoModel bakedModel,
			RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick,
			int packedLight, int packedOverlay) {

		if(animatable.getHatType()!=0) {
			RenderType cameo = RenderType.entityCutoutNoCull(TOP_HAT_BLACK);
			switch (animatable.getHatType()) {
				case 1:
					if(animatable.getRobeType()==6||animatable.getRobeType()==7){
						cameo = RenderType.entityCutoutNoCull(TOP_HAT_GRAY);
					}else if(animatable.getRobeType()==8){
						cameo = RenderType.entityCutoutNoCull(TOP_HAT_WHITE);
					}else {
						cameo = RenderType.entityCutoutNoCull(TOP_HAT_BLACK);
					}
					break;
				case 2:
					cameo = RenderType.entityCutoutNoCull(BLACK);
					break;
				case 3: //HOOD
					cameo = RenderType.entityCutoutNoCull(NO_HAT);
					break;
			}
			this.getRenderer().reRender(bakedModel, poseStack, bufferSource, animatable, cameo, bufferSource.getBuffer(cameo), partialTick, packedLight, packedOverlay, -1);
		}
	}
	
	
	
	


	

	

	

}
