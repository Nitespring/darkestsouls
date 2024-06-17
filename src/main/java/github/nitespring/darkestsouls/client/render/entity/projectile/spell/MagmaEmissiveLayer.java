package github.nitespring.darkestsouls.client.render.entity.projectile.spell;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.projectile.spell.FireBallEntity;
import github.nitespring.darkestsouls.common.entity.projectile.spell.MagmaEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class MagmaEmissiveLayer<T extends MagmaEntity> extends GeoRenderLayer<T> {

	private static final ResourceLocation MAGMA = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/projectiles/magma.png");


	public MagmaEmissiveLayer(GeoRenderer<T> entityRendererIn) {
		super(entityRendererIn);

	}


	@Override
	public void render(PoseStack poseStack, T animatable, BakedGeoModel bakedModel,
					   RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick,
					   int packedLight, int packedOverlay) {

		RenderType cameo = RenderType.eyes(MAGMA);







		this.getRenderer().reRender(bakedModel, poseStack, bufferSource, animatable, cameo, bufferSource.getBuffer(cameo), partialTick, packedLight, packedOverlay, -1);


	}

}