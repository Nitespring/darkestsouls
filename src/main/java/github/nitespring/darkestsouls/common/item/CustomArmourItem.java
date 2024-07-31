package github.nitespring.darkestsouls.common.item;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.client.render.equipment.armour.*;
import github.nitespring.darkestsouls.common.item.child.armour.WizardRobeItem;
import github.nitespring.darkestsouls.core.event.ClientListener;
import github.nitespring.darkestsouls.core.util.MathUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.naming.Context;
import java.util.EnumMap;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CustomArmourItem extends ArmorItem {

    private static final EnumMap<Type, UUID> ARMOR_MODIFIER_UUID_PER_TYPE = (EnumMap) Util.make(new EnumMap(Type.class), (p_266744_) -> {
        p_266744_.put(ArmorItem.Type.BOOTS, UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"));
        p_266744_.put(ArmorItem.Type.LEGGINGS, UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"));
        p_266744_.put(ArmorItem.Type.CHESTPLATE, UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"));
        p_266744_.put(ArmorItem.Type.HELMET, UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"));
    });

    protected final int armourTier;
    protected final int armourClass;
    protected final int defaultArmourModel;

    protected final int enchantability;
    protected final int bloodResistance;
    protected final int fireDefence;
    protected final int magicDefence;
    private final Multimap<Attribute, AttributeModifier> actualDefaultModifiers;
    protected final float defence;
    protected final float toughness;
    protected final int magicBonus;
    protected final int luckBonus;
    protected final int alchemyBonus;
    protected final int gunBonus;


    public CustomArmourItem(int armourClass, int tier, int defaultArmourModel, float defence, float toughness,float knockbackResistance, float knockbackBonus, float attackSpeedBonus, float meleeBonus, int magicBonus, int alchemyBonus, int gunBonus, int luckBonus, float speedModifier,float jumpModifier, int magicDefence, int fireDefence, int bloodResistance, int durability, int enchantability,  ArmorMaterial materialType, ArmorItem.Type type, Properties properties) {
        super(materialType, type, properties.stacksTo(1).durability(durability));
        this.armourClass = armourClass;
        this.armourTier = tier;
        this.defaultArmourModel = defaultArmourModel;
        this.enchantability = enchantability;
        this.bloodResistance = bloodResistance;
        this.fireDefence = fireDefence;
        this.magicDefence = magicDefence;
        this.defence = defence;
        this.toughness = toughness;
        this.magicBonus = magicBonus;
        this.alchemyBonus = alchemyBonus;
        this.gunBonus = gunBonus;
        this.luckBonus = luckBonus;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        //EquipmentSlot equipmentslotgroup = EquipmentSlot.bySlot(type.getSlot());
        UUID uuid = (UUID)ARMOR_MODIFIER_UUID_PER_TYPE.get(type);
        builder.put(
                Attributes.ARMOR,
                new AttributeModifier(uuid, "Armor modifier", (double) defence, AttributeModifier.Operation.ADDITION));
        builder.put(
                Attributes.ARMOR_TOUGHNESS,
                new AttributeModifier(uuid, "Armor toughness",(double)toughness, AttributeModifier.Operation.ADDITION));
        if (knockbackResistance > 0.0F) {
            builder.put(
                    Attributes.KNOCKBACK_RESISTANCE,
                    new AttributeModifier(uuid, "Armor knockback resistance",(double)knockbackResistance, AttributeModifier.Operation.ADDITION));
        }
        if (speedModifier != 0.0F) {
            builder.put(
                    Attributes.MOVEMENT_SPEED,
                    new AttributeModifier(uuid, "Armor movement speed bonus",speedModifier, AttributeModifier.Operation.ADDITION));
        }
        if (jumpModifier != 0.0F) {
            builder.put(
                    Attributes.JUMP_STRENGTH,
                    new AttributeModifier(uuid, "Armor jump strenght bonus",(double)jumpModifier, AttributeModifier.Operation.ADDITION));
        }
        if (meleeBonus != 0.0F) {
            builder.put(
                    Attributes.ATTACK_DAMAGE,
                    new AttributeModifier(uuid, "Armor attack damage bonus",meleeBonus, AttributeModifier.Operation.ADDITION));
        }
        if (attackSpeedBonus != 0.0F) {
            builder.put(
                    Attributes.ATTACK_SPEED,
                    new AttributeModifier(uuid, "Armor attack speed bonus",attackSpeedBonus, AttributeModifier.Operation.ADDITION));
        }
        if (knockbackBonus != 0.0F) {
            builder.put(
                    Attributes.ATTACK_KNOCKBACK,
                    new AttributeModifier(uuid, "Armor attack knockback bonus",knockbackBonus, AttributeModifier.Operation.ADDITION));
        }
        this.actualDefaultModifiers = builder.build();
    }
    public int getDefense() {return (int)defence;}
    public float getToughness() {
        return toughness;
    }
    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }


    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot p_40390_) {
        return this.actualDefaultModifiers;
    }

    public int getArmourClass() {return armourClass;}
    public int getArmourTier() {return armourTier;}

    public int getMagicBonus() {return magicBonus;}
    public int getAlchemyBonus() {return alchemyBonus;}
    public int getGunBonus() {return gunBonus;}

    public int getBloodResistance() {return bloodResistance;}
    public int getMagicDefence() {return magicDefence;}
    public int getFireDefence() {return fireDefence;}
    public int getLuckBonus() {return luckBonus;}


    @Override
    public void appendHoverText(ItemStack stack, Level context, List<Component> tooltip, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltip, tooltipFlag);
        Component classInfo = Component.translatable("translation.darkestsouls.no_class").withStyle(ChatFormatting.DARK_GRAY);
        switch(getArmourClass()){
            case 0:
                classInfo = Component.translatable("translation.darkestsouls.knight_class").withStyle(ChatFormatting.GRAY);
                break;
            case 1:
                classInfo = Component.translatable("translation.darkestsouls.hunter_class").withStyle(ChatFormatting.DARK_RED);
                break;
            case 2:
                classInfo = Component.translatable("translation.darkestsouls.sorcerer_class").withStyle(ChatFormatting.AQUA);
                break;
            case 3:
                classInfo = Component.translatable("translation.darkestsouls.alchemist_class").withStyle(ChatFormatting.DARK_AQUA);
                break;
            case 4:
                classInfo = Component.translatable("translation.darkestsouls.specialist_class").withStyle(ChatFormatting.DARK_GRAY);
                break;
        }
        tooltip.add(classInfo);

        String info = " " + MathUtils.convertToRoman(getArmourTier());
        if(getArmourTier()==0) {
            info = " 0";
        }
        tooltip.add(Component.translatable("translation.darkestsouls.tier").append(Component.literal(info)).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_GRAY));

        if(this.getBloodResistance()>0){
            tooltip.add(Component.literal("+"+getBloodResistance()+" ").append(Component.translatable("translation.darkestsouls.blood_resistance").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.ITALIC)));
        }
        if(this.getMagicDefence()>0){
            tooltip.add(Component.literal("+"+getMagicDefence()+"% ").append(Component.translatable("translation.darkestsouls.magic_defence").withStyle(ChatFormatting.BLUE).withStyle(ChatFormatting.ITALIC)));
        }
        if(this.getFireDefence()>0){
            tooltip.add(Component.literal("+"+getFireDefence()+"% ").append(Component.translatable("translation.darkestsouls.fire_defence").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC)));
        }
        if(this.getMagicBonus()>0){
            tooltip.add(Component.literal("+"+getMagicBonus()+"% ").append(Component.translatable("translation.darkestsouls.magic_bonus").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.ITALIC)));
        }
        if(this.getAlchemyBonus()>0){
            tooltip.add(Component.literal("+"+getAlchemyBonus()+"% ").append(Component.translatable("translation.darkestsouls.alchemy_bonus").withStyle(ChatFormatting.DARK_AQUA).withStyle(ChatFormatting.ITALIC)));
        }
        if(this.getGunBonus()>0){
            tooltip.add(Component.literal("+"+getGunBonus()+"% ").append(Component.translatable("translation.darkestsouls.gun_bonus").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.ITALIC)));
        }
        if(this.getLuckBonus()>0){
            tooltip.add(Component.literal("+"+getLuckBonus()+"%").append(Component.translatable("translation.darkestsouls.luck").withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.ITALIC)));
        }
    }

    //Render stuff


    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        switch(((CustomArmourItem)stack.getItem()).getDefaultArmourModel()){
            case 10:
                return new String(DarkestSouls.MODID + ":textures/armour/tattered_wizard_robe.png");
            case 11:
                return slot==EquipmentSlot.LEGS ? new String(DarkestSouls.MODID + ":textures/armour/wizard_robes_inner.png") : new String(DarkestSouls.MODID + ":textures/armour/wizard_robes.png");
            case 20:
                return slot==EquipmentSlot.LEGS ? new String(DarkestSouls.MODID + ":textures/armour/elite_knight_set_inner.png") : new String(DarkestSouls.MODID + ":textures/armour/elite_knight_set_outer.png");
            case 50:
                return slot==EquipmentSlot.LEGS ? new String(DarkestSouls.MODID + ":textures/armour/hunter_armour_tricorn_inner.png") : new String(DarkestSouls.MODID + ":textures/armour/hunter_armour_tricorn_outer.png");
            case 70:
                return slot==EquipmentSlot.LEGS ? new String(DarkestSouls.MODID + ":textures/armour/specialist_armour_1_inner.png") : new String(DarkestSouls.MODID + ":textures/armour/specialist_armour_1_outer.png");
            case 80:
                return slot==EquipmentSlot.LEGS ? new String(DarkestSouls.MODID + ":textures/armour/alchemist_armour_top_hat_inner.png") : new String(DarkestSouls.MODID + ":textures/armour/alchemist_armour_top_hat_outer.png");
            default:
                return slot==EquipmentSlot.LEGS ? new String(DarkestSouls.MODID + ":textures/armour/wizard_robes_inner.png") : new String(DarkestSouls.MODID + ":textures/armour/wizard_robes.png");
        }
    }


    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(CustomArmourItem.ArmorRender.INSTANCE);
    }

    public int getDefaultArmourModel() {
        return defaultArmourModel;
    }

    private static final class ArmorRender implements IClientItemExtensions {
        private static final CustomArmourItem.ArmorRender INSTANCE = new CustomArmourItem.ArmorRender();


        @Override
        public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> model) {
            EntityModelSet models = Minecraft.getInstance().getEntityModels();
            ModelPart root = models.bakeLayer(slot == EquipmentSlot.LEGS ? ModelLayers.PLAYER_INNER_ARMOR : ModelLayers.PLAYER_OUTER_ARMOR);
            HumanoidModel<?> aModel = new HumanoidModel<>(root);
            switch(((CustomArmourItem)stack.getItem()).getDefaultArmourModel()){
                case 10:
                    root = models.bakeLayer(ClientListener.TATTERED_WIZARD_ROBE);
                    aModel = new TatteredWizardRobeModel(root);
                    break;
                case 11:
                    root = models.bakeLayer(slot == EquipmentSlot.LEGS ? ClientListener.WIZARD_ROBE_INNER : ClientListener.WIZARD_ROBE);
                    aModel = new WizardRobeModel(root);
                    break;
                case 20:
                    root = models.bakeLayer(slot == EquipmentSlot.LEGS ? ModelLayers.PLAYER_INNER_ARMOR : ClientListener.ELITE_KNIGHT);
                    aModel = new EliteKnightArmourModel(root);
                    break;
                case 50:
                    root = models.bakeLayer(slot == EquipmentSlot.LEGS ? ClientListener.TRICORN_INNER : ClientListener.TRICORN_OUTER);
                    aModel = new HunterTricornModel(root);
                    break;
                case 70:
                    root = models.bakeLayer(slot == EquipmentSlot.LEGS ? ClientListener.SPECIALIST_INNER : ClientListener.SPECIALIST_OUTER);
                    aModel = new SpecialistArmourModel(root);
                    break;
                case 80:
                    root = models.bakeLayer(slot == EquipmentSlot.LEGS ? ClientListener.TOP_HAT_INNER : ClientListener.TOP_HAT_OUTER);
                    aModel = new AlchemistTopHatModel(root);
                    break;

            }
            return aModel;
        }


    }

}
