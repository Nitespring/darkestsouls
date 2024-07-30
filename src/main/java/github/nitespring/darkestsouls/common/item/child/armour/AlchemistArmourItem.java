package github.nitespring.darkestsouls.common.item.child.armour;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.client.render.equipment.armour.AlchemistTopHatModel;

import github.nitespring.darkestsouls.client.render.equipment.armour.HunterTricornModel;
import github.nitespring.darkestsouls.common.item.CustomArmourItem;
import github.nitespring.darkestsouls.core.event.ClientListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;


import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class AlchemistArmourItem extends CustomArmourItem {
    public AlchemistArmourItem(int armourClass, int tier, int defaultArmourModel, float defence, float toughness, float knockbackResistance, float knockbackBonus, float attackSpeedBonus, float meleeBonus, int magicBonus, int alchemyBonus, int gunBonus, int luckBonus, float speedModifier, float jumpModifier, int magicDefence, int fireDefence, int bloodResistance, int durability, int enchantability, Holder<ArmorMaterial> materialType, Type type, Properties properties) {
        super(armourClass, tier, defaultArmourModel, defence, toughness, knockbackResistance, knockbackBonus, attackSpeedBonus, meleeBonus, magicBonus, alchemyBonus, gunBonus, luckBonus, speedModifier, jumpModifier, magicDefence, fireDefence, bloodResistance, durability, enchantability, materialType, type, properties);
    }



    /*
    @Override
    public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {

        return innerModel ? ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID , "textures/armour/alchemist_armour_top_hat_inner.png") : ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID , "textures/armour/alchemist_armour_top_hat_outer.png");
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(ArmorRender.INSTANCE);
    }



    private static final class ArmorRender implements IClientItemExtensions {
        private static final ArmorRender INSTANCE = new ArmorRender();


        @Override
        public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> model) {
            EntityModelSet models = Minecraft.getInstance().getEntityModels();
            ModelPart root = models.bakeLayer(slot == EquipmentSlot.LEGS ? ClientListener.TOP_HAT_INNER : ClientListener.TOP_HAT_OUTER);
            AlchemistTopHatModel aModel = new AlchemistTopHatModel(root);
            return aModel;
        }


    }
    */

}
