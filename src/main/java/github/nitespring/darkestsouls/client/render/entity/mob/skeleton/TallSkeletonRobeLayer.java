package github.nitespring.darkestsouls.client.render.entity.mob.skeleton;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.mob.hollow.Hollow;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.Skeleton;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class TallSkeletonRobeLayer<T extends Skeleton & GeoEntity> extends GeoRenderLayer<T>{

	private static final ResourceLocation CLOAK_BLUE = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/skeleton/tall_skeleton_cloak_blue.png");

	public TallSkeletonRobeLayer(GeoRenderer<T> entityRendererIn) {
		super(entityRendererIn);
	
	}
	

	@Override
	public void render(PoseStack poseStack, T animatable, BakedGeoModel bakedModel,
			RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick,
			int packedLight, int packedOverlay) {

		if(animatable.getRobeType()!=0) {
			RenderType cameo = RenderType.entityCutoutNoCull(CLOAK_BLUE);
			switch (animatable.getRobeType()) {
				case 1:
					cameo = RenderType.entityCutoutNoCull(CLOAK_BLUE);
					break;
			}
			this.getRenderer().reRender(bakedModel, poseStack, bufferSource, animatable, cameo, bufferSource.getBuffer(cameo), partialTick, packedLight, packedOverlay, -1);
		}
	}
	
	
	
	


	

	

	

}
