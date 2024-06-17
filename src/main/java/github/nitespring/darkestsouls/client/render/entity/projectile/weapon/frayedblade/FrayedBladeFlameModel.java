package github.nitespring.darkestsouls.client.render.entity.projectile.weapon.frayedblade;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import github.nitespring.darkestsouls.common.entity.projectile.weapon.melee.FrayedBladeFlameEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class FrayedBladeFlameModel<T extends FrayedBladeFlameEntity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

	private final ModelPart base;
	private final ModelPart interior;

	public FrayedBladeFlameModel(ModelPart root) {
		this.base = root.getChild("base");
		this.interior = root.getChild("interior");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition interior = partdefinition.addOrReplaceChild("interior", CubeListBuilder.create().texOffs(0, 36).addBox(-6.0F, -16.0F, -6.0F, 12.0F, 16.0F, 12.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(FrayedBladeFlameEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

		float f1 = (limbSwing + Mth.sin(limbSwing * 2.7F)) * 0.6F * 12.0F;
		this.base.y = 38-4*f1;
		this.interior.y = 56-6*f1;

		/*
		if(-12+(entity.lifeTicks)<=0) {
			this.base.y = -12 + (entity.lifeTicks);
		}else{
			this.base.y=0;
		}
		this.interior.y = -12+2*(entity.lifeTicks);
		*/

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int i) {
		base.render(poseStack, vertexConsumer, packedLight, packedOverlay);
		interior.render(poseStack, vertexConsumer, packedLight, packedOverlay);
	}
}