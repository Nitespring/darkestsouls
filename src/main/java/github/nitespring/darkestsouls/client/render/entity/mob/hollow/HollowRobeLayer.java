package github.nitespring.darkestsouls.client.render.entity.mob.hollow;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.mob.abyss.MonstruosityOfSin;
import github.nitespring.darkestsouls.common.entity.mob.hollow.Hollow;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class HollowRobeLayer<T extends Hollow & GeoEntity> extends GeoRenderLayer<T>{

	private static final ResourceLocation LOINCLOTH = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/hollow/hollow_loincloth.png");
	private static final ResourceLocation TROUSERS = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/hollow/hollow_trousers.png");
	private static final ResourceLocation VEST = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/hollow/hollow_vest.png");
	private static final ResourceLocation CLOAK = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/hollow/hollow_gravetender_cloak.png");
	private static final ResourceLocation LOTHRIC = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/hollow/hollow_lothric_armour.png");
	private static final ResourceLocation LORDRAN = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/hollow/hollow_lordran_armour.png");
	private static final ResourceLocation ASSASSIN = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/hollow/hollow_assassin_armour.png");

	public HollowRobeLayer(GeoRenderer<T> entityRendererIn) {
		super(entityRendererIn);
	
	}
	

	@Override
	public void render(PoseStack poseStack, T animatable, BakedGeoModel bakedModel,
			RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick,
			int packedLight, int packedOverlay) {

		if(animatable.getRobeType()!=0) {
			RenderType cameo = RenderType.entityCutoutNoCull(TROUSERS);
			switch (animatable.getRobeType()) {
				case 1:
					cameo = RenderType.entityCutoutNoCull(TROUSERS);
					break;
				case 2:
					cameo = RenderType.entityCutoutNoCull(VEST);
					break;
				case 3:
					cameo = RenderType.entityCutoutNoCull(CLOAK);
					break;
				case 4, 5:
					cameo = RenderType.entityCutoutNoCull(LOTHRIC);
					break;
				case 6:
					cameo = RenderType.entityCutoutNoCull(LORDRAN);
					break;
				case 7:
					cameo = RenderType.entityCutoutNoCull(LOINCLOTH);
					break;
				case 8:
					cameo = RenderType.entityCutoutNoCull(ASSASSIN);
					break;
			}
			this.getRenderer().reRender(bakedModel, poseStack, bufferSource, animatable, cameo, bufferSource.getBuffer(cameo), partialTick, packedLight, packedOverlay, -1);
		}
	}
	
	
	
	


	

	

	

}
