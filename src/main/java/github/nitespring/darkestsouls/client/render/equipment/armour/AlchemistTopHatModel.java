package github.nitespring.darkestsouls.client.render.equipment.armour;

import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class AlchemistTopHatModel<T extends LivingEntity> extends HumanoidArmorModel<T> {

    public AlchemistTopHatModel(ModelPart root) {
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

        PartDefinition hat_part = head.addOrReplaceChild("hat_part", CubeListBuilder.create().texOffs(64, 17).addBox(-7.0F, -0.25F, -10.0F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.1F))
                .texOffs(96, 0).addBox(-3.0F, -8.5F, -5.75F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.75F)), PartPose.offset(-1.0F, -5.75F, 1.75F));

        PartDefinition body_part = body.addOrReplaceChild("body_part", CubeListBuilder.create().texOffs(0, 64).addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.8F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition collar = body.addOrReplaceChild("collar", CubeListBuilder.create().texOffs(56, 48).addBox(-4.0F, -32.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(1.01F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition belt = body.addOrReplaceChild("belt", CubeListBuilder.create().texOffs(32, 43).addBox(-2.0F, -16.0F, -3.25F, 4.0F, 3.0F, 2.0F, new CubeDeformation(-0.1F))
                .texOffs(0, 40).addBox(-5.0F, -15.5F, -3.0F, 10.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 13.0F, 0.0F));

        PartDefinition tail_back_r1 = tail.addOrReplaceChild("tail_back_r1", CubeListBuilder.create().texOffs(16, 48).mirror().addBox(0.1743F, -0.9924F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.79F)).mirror(false), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition tail_back_r2 = tail.addOrReplaceChild("tail_back_r2", CubeListBuilder.create().texOffs(0, 48).addBox(-4.1743F, -0.9924F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.79F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, 0.0F, 0.0873F));

        PartDefinition tail_back = tail.addOrReplaceChild("tail_back", CubeListBuilder.create(), PartPose.offset(0.0F, -1.5F, 0.0F));

        PartDefinition tail_back_r3 = tail_back.addOrReplaceChild("tail_back_r3", CubeListBuilder.create().texOffs(32, 48).addBox(-4.0F, -0.9924F, -1.8257F, 8.0F, 10.0F, 4.0F, new CubeDeformation(0.8F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2182F, 0.0F, 0.0F));

        PartDefinition left_boot_part = left_leg.addOrReplaceChild("left_boot_part", CubeListBuilder.create().texOffs(104, 56).addBox(-3.0F, 3.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(-0.22F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition right_boot_part = right_leg.addOrReplaceChild("right_boot_part", CubeListBuilder.create().texOffs(104, 56).addBox(-3.2F, 3.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(-0.22F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }
    public static LayerDefinition createInnerLayer() {
        MeshDefinition meshdefinition = HumanoidArmorModel.createBodyLayer(new CubeDeformation(0.5F));
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.getChild("body");

        PartDefinition belt = body.addOrReplaceChild("belt", CubeListBuilder.create().texOffs(0, 48).addBox(-7.0F, -2.5F, -3.0F, 10.0F, 2.0F, 6.0F, new CubeDeformation(-0.3F))
                .texOffs(24, 60).addBox(-4.0F, -3.0F, -3.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(-0.27F)), PartPose.offset(1.9F, 10.0F, 0.0F));

        PartDefinition bag = belt.addOrReplaceChild("bag", CubeListBuilder.create().texOffs(0, 56).addBox(-3.0F, -5.0F, 0.0F, 4.0F, 5.0F, 3.0F, new CubeDeformation(-0.75F)), PartPose.offset(-4.0F, 1.75F, -4.0F));

        PartDefinition bottle = belt.addOrReplaceChild("bottle", CubeListBuilder.create().texOffs(36, 60).addBox(-0.5F, 0.0F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(38, 57).addBox(0.0F, -1.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(38, 55).addBox(0.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.1F))
                .texOffs(38, 53).addBox(0.0F, -2.5F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offset(1.0F, -0.75F, -3.75F));

        return LayerDefinition.create(meshdefinition, 128, 64);
    }

}
