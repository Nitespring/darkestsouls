package github.nitespring.darkestsouls.client.render.entity.projectile.throwable;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.item.throwing.MolotovCocktail;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;

public class MolotovCocktailModel<T extends AbstractHurtingProjectile> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(DarkestSouls.MODID, "molotovcocktail"), "main");
	private final ModelPart main;

	public MolotovCocktailModel(ModelPart root) {
		this.main = root.getChild("main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bb_main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(16, 8).addBox(-2.0F, -7.25F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 8).addBox(-2.0F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
				.texOffs(16, 19).addBox(-1.0F, -20.25F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(12, 8).addBox(-1.0F, -19.75F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.1F))
				.texOffs(0, 24).addBox(-1.0F, -21.75F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(-0.2F))
				.texOffs(0, 0).addBox(-10.5F, -27.25F, 0.0F, 12.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}