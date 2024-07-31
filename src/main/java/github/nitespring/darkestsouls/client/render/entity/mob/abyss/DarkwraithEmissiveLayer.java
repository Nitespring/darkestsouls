package github.nitespring.darkestsouls.client.render.entity.mob.abyss;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.mob.abyss.Darkwraith;
import github.nitespring.darkestsouls.common.entity.mob.abyss.MonstruosityOfSin;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class DarkwraithEmissiveLayer<T extends Darkwraith & GeoEntity> extends GeoRenderLayer<T>{

	private static final ResourceLocation EYES = new ResourceLocation(DarkestSouls.MODID, "textures/entity/darkwraith/darkwraith_emissive.png");

	public DarkwraithEmissiveLayer(GeoRenderer<T> entityRendererIn) {
		super(entityRendererIn);
	
	}
	
	
	@Override
	public void render(PoseStack poseStack, T animatable, BakedGeoModel bakedModel,
			RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick,
			int packedLight, int packedOverlay) {
		
		RenderType cameo = RenderType.eyes(EYES);

		this.getRenderer().reRender(bakedModel, poseStack, bufferSource, animatable, cameo, bufferSource.getBuffer(cameo), partialTick, packedLight, packedOverlay, 1f, 1f, 1f, 1f);
		

		
	}
	
	

	


	

	

	

}
