package github.nitespring.darkestsouls.client.render.equipment.armour;

import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class SpecialistArmourModel<T extends LivingEntity> extends HumanoidArmorModel<T> {

    public SpecialistArmourModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createOuterLayer() {
        MeshDefinition meshdefinition = HumanoidArmorModel.createBodyLayer(new CubeDeformation(0.75F));
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.getChild("head");
        PartDefinition hat = partdefinition.getChild("hat");
        PartDefinition body = partdefinition.getChild("body");
        PartDefinition right_arm = partdefinition.getChild("right_arm");
        PartDefinition left_arm = partdefinition.getChild("left_arm");
        PartDefinition right_leg = partdefinition.getChild("right_leg");
        PartDefinition left_leg = partdefinition.getChild("left_leg");

        PartDefinition hat_part = head.addOrReplaceChild("hat_part", CubeListBuilder.create().texOffs(61, 15).addBox(-8.0F, 0.25F, -11.0F, 18.0F, 1.0F, 18.0F, new CubeDeformation(0.0F))
                .texOffs(96, 0).addBox(-3.0F, -3.5F, -5.75F, 8.0F, 4.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offset(-1.0F, -5.75F, 1.75F));

        PartDefinition mask = head.addOrReplaceChild("mask", CubeListBuilder.create().texOffs(96, 64).addBox(-4.0F, -32.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.8F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition belt = body.addOrReplaceChild("belt", CubeListBuilder.create().texOffs(32, 43).addBox(-2.0F, -16.0F, -3.25F, 4.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F))
                .texOffs(0, 40).addBox(-5.0F, -15.5F, -3.0F, 10.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 13.0F, 0.0F));

        PartDefinition tail_back_r1 = tail.addOrReplaceChild("tail_back_r1", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(0.1743F, -0.9924F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.79F)).mirror(false), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition tail_back_r2 = tail.addOrReplaceChild("tail_back_r2", CubeListBuilder.create().texOffs(0, 48).addBox(-4.1743F, -0.9924F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.79F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, 0.0F, 0.0873F));

        PartDefinition tail_back = tail.addOrReplaceChild("tail_back", CubeListBuilder.create(), PartPose.offset(0.0F, -1.5F, 0.0F));

        PartDefinition tail_back_r3 = tail_back.addOrReplaceChild("tail_back_r3", CubeListBuilder.create().texOffs(32, 48).addBox(-4.0F, -0.9924F, -1.8257F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.8F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2182F, 0.0F, 0.0F));

        PartDefinition diagonal_belt = body.addOrReplaceChild("diagonal_belt", CubeListBuilder.create(), PartPose.offset(0.0F, 24.5F, 0.0F));

        PartDefinition cube_r1 = diagonal_belt.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(86, 118).addBox(-20.25F, -17.5F, -3.5F, 14.0F, 3.0F, 7.0F, new CubeDeformation(-0.45F)), PartPose.offsetAndRotation(0.25F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6981F));

        PartDefinition cube_r2 = diagonal_belt.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(84, 109).addBox(-20.25F, -16.5F, -3.5F, 15.0F, 2.0F, 7.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, 0.0F, 0.6981F));

        PartDefinition cloak = body.addOrReplaceChild("cloak", CubeListBuilder.create().texOffs(32, 87).addBox(-5.0F, -1.0F, -3.0F, 10.0F, 5.0F, 6.0F, new CubeDeformation(0.32F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cloak_collar = cloak.addOrReplaceChild("cloak_collar", CubeListBuilder.create().texOffs(0, 80).addBox(-4.0F, -32.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(1.5F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cloack_back = cloak.addOrReplaceChild("cloack_back", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition cloak_back_r1 = cloack_back.addOrReplaceChild("cloak_back_r1", CubeListBuilder.create().texOffs(0, 98).addBox(-5.0F, -2.25F, -1.0F, 10.0F, 6.0F, 1.0F, new CubeDeformation(0.42F)), PartPose.offsetAndRotation(0.0F, 3.0F, 2.75F, -0.0436F, 0.0F, 0.0F));

        PartDefinition cloack_front = cloak.addOrReplaceChild("cloack_front", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, -0.25F, -0.0873F, 0.0F, 0.0F));

        PartDefinition cloak_front_r1 = cloack_front.addOrReplaceChild("cloak_front_r1", CubeListBuilder.create().texOffs(0, 106).addBox(-5.0F, -2.0F, -0.1F, 10.0F, 5.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 3.0F, -2.75F, 0.0436F, 0.0F, 0.0F));

        PartDefinition left_boot_part = left_leg.addOrReplaceChild("left_boot_part", CubeListBuilder.create().texOffs(104, 56).addBox(-3.0F, 1.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(-0.22F))
                .texOffs(27, 105).addBox(-3.0F, 3.0F, -2.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition right_boot_part = right_leg.addOrReplaceChild("right_boot_part", CubeListBuilder.create().texOffs(104, 56).addBox(-3.2F, 1.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(-0.22F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cloak_left = left_arm.addOrReplaceChild("cloak_left", CubeListBuilder.create().texOffs(24, 69).addBox(-1.0F, -2.8F, -3.0F, 5.0F, 7.0F, 6.0F, new CubeDeformation(0.21F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cloak_right = right_arm.addOrReplaceChild("cloak_right", CubeListBuilder.create().texOffs(46, 69).mirror().addBox(-4.0F, -2.8F, -3.0F, 5.0F, 7.0F, 6.0F, new CubeDeformation(0.21F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }
    public static LayerDefinition createInnerLayer() {
        MeshDefinition meshdefinition = HumanoidArmorModel.createBodyLayer(new CubeDeformation(0.5F));
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.getChild("body");

        PartDefinition belt = body.addOrReplaceChild("belt", CubeListBuilder.create().texOffs(0, 48).addBox(-7.0F, -2.5F, -3.0F, 10.0F, 2.0F, 6.0F, new CubeDeformation(-0.3F))
                .texOffs(24, 60).addBox(-4.0F, -3.0F, -3.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(-0.27F)), PartPose.offset(1.9F, 10.0F, 0.0F));

        PartDefinition bag = belt.addOrReplaceChild("bag", CubeListBuilder.create().texOffs(0, 37).addBox(-4.75F, -5.0F, 1.0F, 4.0F, 5.0F, 6.0F, new CubeDeformation(-0.75F)), PartPose.offset(-4.0F, 1.75F, -4.0F));

        PartDefinition knife = belt.addOrReplaceChild("knife", CubeListBuilder.create(), PartPose.offset(1.0F, -0.75F, -3.75F));

        PartDefinition cube_r1 = knife.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(34, 48).addBox(-3.0F, -5.0F, -2.0F, 4.0F, 8.0F, 8.0F, new CubeDeformation(-2.0F)), PartPose.offsetAndRotation(2.75F, 2.5F, 3.5F, 1.0472F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 64);
    }

}
