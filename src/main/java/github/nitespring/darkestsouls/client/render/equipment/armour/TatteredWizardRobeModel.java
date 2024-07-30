package github.nitespring.darkestsouls.client.render.equipment.armour;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class TatteredWizardRobeModel<T extends LivingEntity> extends HumanoidArmorModel<T> {

    public TatteredWizardRobeModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = HumanoidArmorModel.createBodyLayer(new CubeDeformation(0.75F));
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.getChild("head");
        PartDefinition hat = partdefinition.getChild("hat");
        PartDefinition body = partdefinition.getChild("body");
        PartDefinition right_arm = partdefinition.getChild("right_arm");
        PartDefinition left_arm = partdefinition.getChild("left_arm");
        PartDefinition right_leg = partdefinition.getChild("right_leg");
        PartDefinition left_leg = partdefinition.getChild("left_leg");

        PartDefinition hat_part = head.addOrReplaceChild("hat_part", CubeListBuilder.create().texOffs(36, 44).addBox(-10.0F, -5.18F, -10.0F, 20.0F, 0.0F, 20.0F, new CubeDeformation(0.0F))
                .texOffs(104, 26).addBox(-3.0F, -15.25F, 0.75F, 6.0F, 5.0F, 6.0F, new CubeDeformation(-0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = hat_part.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(108, 16).addBox(-2.0F, -5.0F, -1.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, -10.25F, 4.5F, -0.48F, 0.0F, 0.0F));

        PartDefinition cube_r2 = hat_part.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(96, 37).addBox(-4.0F, -7.0F, -4.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(-0.75F)), PartPose.offsetAndRotation(0.0F, -8.5F, -1.25F, -0.829F, 0.0F, 0.0F));

        PartDefinition cube_r3 = hat_part.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(96, 52).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.75F, -0.25F, -0.2618F, 0.0F, 0.0F));

        PartDefinition hat_decor = hat.addOrReplaceChild("hat_decor", CubeListBuilder.create().texOffs(91, 39).addBox(-2.0F, -18.75F, -3.5F, 4.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.0F, 10.0F, -2.25F));

        PartDefinition cloak = body.addOrReplaceChild("cloak", CubeListBuilder.create().texOffs(24, 38).addBox(-6.0F, -24.0F, -2.0F, 12.0F, 5.0F, 4.0F, new CubeDeformation(1.25F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition belt = body.addOrReplaceChild("belt", CubeListBuilder.create().texOffs(58, 34).addBox(-5.0F, -17.0F, -3.0F, 10.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 13.0F, 0.0F));

        PartDefinition tail_back_r1 = tail.addOrReplaceChild("tail_back_r1", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(0.1743F, -0.9924F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.79F)).mirror(false), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition tail_back_r2 = tail.addOrReplaceChild("tail_back_r2", CubeListBuilder.create().texOffs(0, 48).addBox(-4.1743F, -0.9924F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.79F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, 0.0F, 0.0873F));

        PartDefinition tail_back = tail.addOrReplaceChild("tail_back", CubeListBuilder.create(), PartPose.offset(0.0F, -1.5F, 0.0F));

        PartDefinition tail_back_r3 = tail_back.addOrReplaceChild("tail_back_r3", CubeListBuilder.create().texOffs(32, 48).addBox(-4.0F, -0.9924F, -1.8257F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.8F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2182F, 0.0F, 0.0F));

        PartDefinition cloak_left = left_arm.addOrReplaceChild("cloak_left", CubeListBuilder.create().texOffs(56, 21).addBox(-1.0F, -2.8F, -3.0F, 5.0F, 6.0F, 6.0F, new CubeDeformation(0.21F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cloak_right = right_arm.addOrReplaceChild("cloak_right", CubeListBuilder.create().texOffs(78, 21).mirror().addBox(-4.0F, -2.8F, -3.0F, 5.0F, 6.0F, 6.0F, new CubeDeformation(0.21F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 64);
    }


}
