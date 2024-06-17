package github.nitespring.darkestsouls.client.render.entity.mob.beast;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.mob.abyss.MonstruosityOfSin;
import github.nitespring.darkestsouls.common.entity.mob.beast.BeastPatientEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class BeastPatientEmissiveLayer<T extends BeastPatientEntity & GeoEntity> extends GeoRenderLayer<T>{

	private static final ResourceLocation YELLOW_EYES = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/beast/beast_patient_eyes_yellow.png");
	private static final ResourceLocation RED_EYES = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/beast/beast_patient_eyes_red.png");

	public BeastPatientEmissiveLayer(GeoRenderer<T> entityRendererIn) {
		super(entityRendererIn);
	
	}
	
	
	@Override
	public void render(PoseStack poseStack, T animatable, BakedGeoModel bakedModel,
			RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick,
			int packedLight, int packedOverlay) {
		
		RenderType cameo = RenderType.entityTranslucentEmissive(YELLOW_EYES);
		if(animatable.getBeastPatientType()==2||animatable.getEntityState()!=0){cameo = RenderType.entityTranslucentEmissive(RED_EYES);}

		this.getRenderer().reRender(bakedModel, poseStack, bufferSource, animatable, cameo, bufferSource.getBuffer(cameo), partialTick, packedLight, packedOverlay, -1);
		

		
	}
	
	
	
	


	

	

	

}
