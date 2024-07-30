package github.nitespring.darkestsouls.client.render.equipment.armour;

import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class EliteKnightArmourModel<T extends LivingEntity> extends HumanoidArmorModel<T> {

    public EliteKnightArmourModel(ModelPart root) {
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

        PartDefinition helm_part = head.addOrReplaceChild("helm_part", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -2.8F, 0.6F, 0.6109F, 0.0F, 0.0F));

        PartDefinition helm_part_r1 = helm_part.addOrReplaceChild("helm_part_r1", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.75F)), PartPose.offsetAndRotation(0.0F, 0.25F, -0.85F, 0.0F, -0.7854F, 0.0F));

        PartDefinition body_part = body.addOrReplaceChild("body_part", CubeListBuilder.create().texOffs(104, 48).addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.8F))
                .texOffs(70, 0).addBox(-4.0F, -23.6F, -2.0F, 8.0F, 4.0F, 4.0F, new CubeDeformation(1.25F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition belt = body.addOrReplaceChild("belt", CubeListBuilder.create().texOffs(76, 22).addBox(-3.0F, -16.0F, -3.6F, 4.0F, 3.0F, 2.0F, new CubeDeformation(-0.25F))
                .texOffs(66, 11).addBox(-5.0F, -15.5F, -3.0F, 10.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(64, 28).addBox(-5.35F, -15.5F, -3.5F, 11.0F, 6.0F, 7.0F, new CubeDeformation(-0.25F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition belt_r1 = belt.addOrReplaceChild("belt_r1", CubeListBuilder.create().texOffs(76, 22).addBox(-2.0F, -17.0F, -3.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(-0.4F)), PartPose.offsetAndRotation(-5.0F, 2.55F, -0.7F, 0.0F, 0.0F, 0.2618F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 13.0F, 0.0F));

        PartDefinition tail_r1 = tail.addOrReplaceChild("tail_r1", CubeListBuilder.create().texOffs(84, 54).mirror().addBox(0.1743F, -0.9924F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.8F)).mirror(false)
                .texOffs(84, 41).mirror().addBox(0.1743F, -0.9924F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.9F)).mirror(false), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition tail_r2 = tail.addOrReplaceChild("tail_r2", CubeListBuilder.create().texOffs(68, 54).addBox(-4.1743F, -0.9924F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.8F))
                .texOffs(68, 41).addBox(-4.1743F, -0.9924F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.9F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, 0.0F, 0.0873F));

        PartDefinition left_boot_part = left_leg.addOrReplaceChild("left_boot_part", CubeListBuilder.create().texOffs(35, 40).mirror().addBox(-3.0F, 5.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(-0.1F)).mirror(false)
                .texOffs(42, 35).mirror().addBox(-2.0F, 4.05F, -3.2F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 1.0F, 0.0F));

        PartDefinition right_boot_part = right_leg.addOrReplaceChild("right_boot_part", CubeListBuilder.create().texOffs(35, 40).addBox(-3.0F, 5.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(-0.1F))
                .texOffs(42, 35).addBox(-2.0F, 4.05F, -3.2F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

        PartDefinition left_shoulder = left_arm.addOrReplaceChild("left_shoulder", CubeListBuilder.create().texOffs(106, 0).mirror().addBox(-1.0F, -3.0F, -3.0F, 5.0F, 5.0F, 6.0F, new CubeDeformation(0.25F)).mirror(false)
                .texOffs(106, 11).mirror().addBox(-1.0F, -3.0F, -3.0F, 5.0F, 5.0F, 6.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_arm_part = left_arm.addOrReplaceChild("left_arm_part", CubeListBuilder.create().texOffs(118, 42).mirror().addBox(-4.9F, 14.75F, -2.75F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(104, 22).addBox(-5.85F, 9.0F, -8.0F, 6.0F, 14.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -12.0F, 5.0F));

        PartDefinition right_arm_part = right_arm.addOrReplaceChild("right_arm_part", CubeListBuilder.create().texOffs(118, 42).addBox(0.9F, 14.75F, -2.75F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(104, 22).mirror().addBox(-0.05F, 9.0F, -8.0F, 6.0F, 14.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, -12.0F, 5.0F));

        return LayerDefinition.create(meshdefinition, 128, 64);
    }


}
