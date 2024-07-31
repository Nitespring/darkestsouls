package github.nitespring.darkestsouls.client.render.equipment.armour;

import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class WizardRobeModel<T extends LivingEntity> extends HumanoidArmorModel<T> {

    public WizardRobeModel(ModelPart root) {
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

        PartDefinition hat_part = head.addOrReplaceChild("hat_part", CubeListBuilder.create().texOffs(36, 44).addBox(-10.0F, -5.18F, -10.0F, 20.0F, 0.0F, 20.0F, new CubeDeformation(0.0F))
                .texOffs(104, 26).addBox(-3.0F, -15.25F, 0.75F, 6.0F, 5.0F, 6.0F, new CubeDeformation(-0.5F))
                .texOffs(108, -10).addBox(0.0F, -13.75F, 7.0F, 0.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = hat_part.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(108, 16).addBox(-2.0F, -5.0F, -1.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(-0.5F)), PartPose.offsetAndRotation(0.0F, -10.15F, 4.7F, -0.1745F, 0.0F, 0.0F));

        PartDefinition cube_r2 = hat_part.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(96, 37).addBox(-4.0F, -7.0F, -4.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(-0.75F)), PartPose.offsetAndRotation(0.0F, -8.5F, -1.25F, -0.829F, 0.0F, 0.0F));

        PartDefinition cube_r3 = hat_part.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(96, 52).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.75F, -0.25F, -0.2618F, 0.0F, 0.0F));

        PartDefinition hat_decor = hat.addOrReplaceChild("hat_decor", CubeListBuilder.create().texOffs(91, 39).addBox(-2.0F, -18.75F, -3.25F, 4.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.0F, 10.0F, -2.25F));

        PartDefinition hood = body.addOrReplaceChild("hood", CubeListBuilder.create().texOffs(75, 0).addBox(-4.0F, -7.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(1.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cloak = body.addOrReplaceChild("cloak", CubeListBuilder.create().texOffs(46, 35).addBox(-6.0F, -24.0F, -2.0F, 12.0F, 5.0F, 4.0F, new CubeDeformation(1.25F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cloack_back = cloak.addOrReplaceChild("cloack_back", CubeListBuilder.create().texOffs(0, 65).addBox(-6.0F, -0.25F, 2.0F, 12.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -24.0F, 0.25F, 0.2618F, 0.0F, 0.0F));

        PartDefinition cloak_back_r1 = cloack_back.addOrReplaceChild("cloak_back_r1", CubeListBuilder.create().texOffs(17, 75).mirror().addBox(-1.0F, -4.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.0F, 3.75F, 1.6F, 0.0F, -0.7854F, 0.0F));

        PartDefinition cloak_back_r2 = cloack_back.addOrReplaceChild("cloak_back_r2", CubeListBuilder.create().texOffs(17, 75).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 3.75F, 1.6F, 0.0F, 0.7854F, 0.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 13.0F, 0.0F));

        PartDefinition tail_back_r1 = tail.addOrReplaceChild("tail_back_r1", CubeListBuilder.create().texOffs(18, 48).mirror().addBox(-0.8257F, -0.9924F, -2.0F, 5.0F, 12.0F, 4.0F, new CubeDeformation(0.79F)).mirror(false), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition tail_back_r2 = tail.addOrReplaceChild("tail_back_r2", CubeListBuilder.create().texOffs(0, 48).addBox(-4.1743F, -0.9924F, -2.0F, 5.0F, 12.0F, 4.0F, new CubeDeformation(0.79F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, 0.0F, 0.0873F));

        PartDefinition tail_back = tail.addOrReplaceChild("tail_back", CubeListBuilder.create(), PartPose.offset(0.0F, -1.5F, 0.0F));

        PartDefinition tail_back_r3 = tail_back.addOrReplaceChild("tail_back_r3", CubeListBuilder.create().texOffs(28, 65).addBox(-4.0F, -0.9924F, -1.8257F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.8F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.05F, 0.3927F, 0.0F, 0.0F));

        PartDefinition belt = body.addOrReplaceChild("belt", CubeListBuilder.create().texOffs(16, 37).addBox(-2.0F, -16.0F, -3.25F, 4.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F))
                .texOffs(76, 16).addBox(-5.0F, -15.5F, -3.0F, 10.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition pendant = body.addOrReplaceChild("pendant", CubeListBuilder.create().texOffs(80, 39).addBox(-1.5F, 4.0F, -3.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(78, 24).addBox(-4.0F, -1.0F, -3.0F, 8.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cloak_left = left_arm.addOrReplaceChild("cloak_left", CubeListBuilder.create().texOffs(0, 36).addBox(-1.0F, -2.8F, -3.0F, 5.0F, 6.0F, 6.0F, new CubeDeformation(0.21F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_sleeve = left_arm.addOrReplaceChild("left_sleeve", CubeListBuilder.create().texOffs(0, 96).mirror().addBox(-3.0F, -1.8F, -5.75F, 6.0F, 1.0F, 12.0F, new CubeDeformation(-0.25F)).mirror(false)
                .texOffs(0, 109).mirror().addBox(-3.0F, -2.0F, -5.75F, 6.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.0F, 9.6F, 2.7F));

        PartDefinition left_sleeve_r1 = left_sleeve.addOrReplaceChild("left_sleeve_r1", CubeListBuilder.create().texOffs(0, 84).mirror().addBox(-2.0F, -5.85F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.1F)).mirror(false), PartPose.offsetAndRotation(0.0F, -4.1F, -0.4F, 0.4194F, 0.7401F, 0.2921F));

        PartDefinition left_sleeve_r2 = left_sleeve.addOrReplaceChild("left_sleeve_r2", CubeListBuilder.create().texOffs(0, 75).mirror().addBox(-1.95F, -1.86F, -2.05F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.05F)).mirror(false), PartPose.offsetAndRotation(0.0F, -1.2F, 0.1F, 0.0F, 0.7854F, 0.0F));

        PartDefinition cloak_right = right_arm.addOrReplaceChild("cloak_right", CubeListBuilder.create().texOffs(0, 36).mirror().addBox(-4.0F, -2.8F, -3.0F, 5.0F, 6.0F, 6.0F, new CubeDeformation(0.21F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition right_sleeve = right_arm.addOrReplaceChild("right_sleeve", CubeListBuilder.create().texOffs(0, 96).addBox(-3.0F, -1.8F, -5.75F, 6.0F, 1.0F, 12.0F, new CubeDeformation(-0.25F))
                .texOffs(0, 109).addBox(-3.0F, -2.0F, -5.75F, 6.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 9.6F, 2.7F));

        PartDefinition right_sleeve_r1 = right_sleeve.addOrReplaceChild("right_sleeve_r1", CubeListBuilder.create().texOffs(0, 84).addBox(-2.0F, -5.85F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -4.1F, -0.4F, 0.4194F, -0.7401F, -0.2921F));

        PartDefinition right_sleeve_r2 = right_sleeve.addOrReplaceChild("right_sleeve_r2", CubeListBuilder.create().texOffs(0, 75).addBox(-2.05F, -1.86F, -2.05F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.05F)), PartPose.offsetAndRotation(0.0F, -1.2F, 0.1F, 0.0F, -0.7854F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }
    public static LayerDefinition createInnerLayer() {
        MeshDefinition meshdefinition = HumanoidArmorModel.createBodyLayer(new CubeDeformation(0.5F));
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.getChild("body");
        PartDefinition left_leg = partdefinition.getChild("left_leg");
        PartDefinition right_leg = partdefinition.getChild("right_leg");

        PartDefinition body_part = body.addOrReplaceChild("body_part", CubeListBuilder.create().texOffs(0, 58).addBox(-5.0F, -15.0F, -3.0F, 9.0F, 1.0F, 5.0F, new CubeDeformation(0.04F)), PartPose.offset(0.5F, 21.75F, 0.5F));

        PartDefinition left_leg_part = left_leg.addOrReplaceChild("left_leg_part", CubeListBuilder.create().texOffs(0, 52).addBox(-5.0F, -16.0F, -3.0F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.04F)), PartPose.offset(2.6F, 22.0F, 0.5F));

        PartDefinition right_leg_part = right_leg.addOrReplaceChild("right_leg_part", CubeListBuilder.create().texOffs(0, 52).addBox(-5.0F, -15.0F, -3.0F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.04F)), PartPose.offset(2.4F, 21.0F, 0.5F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

}
