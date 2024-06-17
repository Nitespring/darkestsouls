package github.nitespring.darkestsouls.client.render.entity.mob.hollow;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.mob.hollow.Hollow;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class HollowHatLayer<T extends Hollow & GeoEntity> extends GeoRenderLayer<T>{

	private static final ResourceLocation BASCINET = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/hollow/hollow_bascinet.png");
	private static final ResourceLocation KETTLE_HAT = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/hollow/hollow_kettle_hat.png");
	private static final ResourceLocation BARBUTE = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/hollow/hollow_barbute_helmet.png");


	public HollowHatLayer(GeoRenderer<T> entityRendererIn) {
		super(entityRendererIn);
	
	}
	

	@Override
	public void render(PoseStack poseStack, T animatable, BakedGeoModel bakedModel,
			RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick,
			int packedLight, int packedOverlay) {

		if(animatable.getHatType()!=0) {
			RenderType cameo = RenderType.entityCutoutNoCull(BASCINET);
			switch (animatable.getHatType()) {
                case 1:
                    cameo = RenderType.entityCutoutNoCull(BASCINET);
					break;
                case 2:
                    cameo = RenderType.entityCutoutNoCull(KETTLE_HAT);
					break;
				case 3:
					cameo = RenderType.entityCutoutNoCull(BARBUTE);
					break;
			}
			this.getRenderer().reRender(bakedModel, poseStack, bufferSource, animatable, cameo, bufferSource.getBuffer(cameo), partialTick, packedLight, packedOverlay, -1);
		}
	}
	
	
	
	


	

	

	

}
