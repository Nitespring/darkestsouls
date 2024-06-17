package github.nitespring.darkestsouls.client.render.entity.mob.abyss;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.mob.abyss.MonstruosityOfSin;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class MonstruosityOfSinEmissiveLayer<T extends MonstruosityOfSin> extends GeoRenderLayer<MonstruosityOfSin>{

	private static final ResourceLocation EYES = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/sin_emissive.png");

	public MonstruosityOfSinEmissiveLayer(GeoRenderer<MonstruosityOfSin> entityRendererIn) {
		super(entityRendererIn);
	
	}
	
	
	@Override
	public void render(PoseStack poseStack, MonstruosityOfSin animatable, BakedGeoModel bakedModel,
			RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick,
			int packedLight, int packedOverlay) {
		
		RenderType cameo = RenderType.entityTranslucentEmissive(EYES);

		this.getRenderer().reRender(bakedModel, poseStack, bufferSource, animatable, cameo, bufferSource.getBuffer(cameo), partialTick, packedLight, packedOverlay, -1);
		

		
	}
	
	
	
	


	

	

	

}
