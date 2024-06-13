package github.nitespring.darkestsouls.core.init;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.enchantment.*;
import github.nitespring.darkestsouls.common.entity.mob.beast.AshenBloodBeastPatient;
import github.nitespring.darkestsouls.common.item.Gun;
import github.nitespring.darkestsouls.common.item.IAmmoConsumingItem;
import github.nitespring.darkestsouls.core.util.CustomEntityTags;
import github.nitespring.darkestsouls.core.util.CustomItemTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Optional;

public class EnchantmentInit {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS,
            DarkestSouls.MODID);
    public static final EquipmentSlot[] HAND_SLOTS = new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND};
    public static final RegistryObject<Enchantment> BLOODBLADE = ENCHANTMENTS.register("bloodblade",
            () -> new BloodBladeEnchantment(
                    Enchantment.definition(CustomItemTags.WEAPON_ENCHANTABLE,
                            4, 3,
                            Enchantment.dynamicCost(4,20),
                            Enchantment.dynamicCost(30,24),
                            2, EquipmentSlot.MAINHAND)));
    public static final RegistryObject<Enchantment> FROST_BLADE = ENCHANTMENTS.register("frost_blade",
            () -> new MobEffectInflictEnchantment(
                    Enchantment.definition(CustomItemTags.WEAPON_ENCHANTABLE,
                            2, 2,
                            Enchantment.dynamicCost(4,14),
                            Enchantment.dynamicCost(24,16),
                            2, EquipmentSlot.MAINHAND),
                    EffectInit.FROST.getHolder().get()));
    public static final RegistryObject<Enchantment> POISONED_BLADE = ENCHANTMENTS.register("poisoned_blade",
            () -> new MobEffectInflictEnchantment(
                    Enchantment.definition(CustomItemTags.WEAPON_ENCHANTABLE,
                            4, 2,
                            Enchantment.dynamicCost(4,10),
                            Enchantment.dynamicCost(24,12),
                            2, EquipmentSlot.MAINHAND),
                    MobEffects.POISON));
    public static final RegistryObject<Enchantment> TOXIC_BLADE = ENCHANTMENTS.register("toxic_blade",
            () -> new MobEffectInflictEnchantment(
                    Enchantment.definition(CustomItemTags.WEAPON_ENCHANTABLE,
                            1, 1,
                            Enchantment.constantCost(16),
                            Enchantment.constantCost(56),
                            4, EquipmentSlot.MAINHAND),
                    EffectInit.TOXIC.getHolder().get()));
    public static final RegistryObject<Enchantment> ROTTEN_BLADE = ENCHANTMENTS.register("rotten_blade",
            () -> new MobEffectInflictEnchantment(
                    Enchantment.definition(CustomItemTags.WEAPON_ENCHANTABLE,
                            1, 2,
                            Enchantment.dynamicCost(6,14),
                            Enchantment.dynamicCost(28,16),
                            3, EquipmentSlot.MAINHAND),
                    EffectInit.ROT.getHolder().get()));
    public static final RegistryObject<Enchantment> WITHERED_BLADE = ENCHANTMENTS.register("withered_blade",
            () -> new MobEffectInflictEnchantment(
                    Enchantment.definition(CustomItemTags.WEAPON_ENCHANTABLE,
                            1, 2,
                            Enchantment.dynamicCost(5,12),
                            Enchantment.dynamicCost(25,14),
                            4, EquipmentSlot.MAINHAND),
                    MobEffects.WITHER));
    public static final RegistryObject<Enchantment> SERRATED = ENCHANTMENTS.register("serrated",
            () -> new PercentageDamageEnchantment(
                    Enchantment.definition(CustomItemTags.WEAPON_ENCHANTABLE,
                            3, 2,
                            Enchantment.dynamicCost(5,10),
                            Enchantment.dynamicCost(25,12),
                            2, EquipmentSlot.MAINHAND),
                    CustomEntityTags.BEAST));
    public static final RegistryObject<Enchantment> ABYSS_CLEANSER = ENCHANTMENTS.register("abyss_cleanser",
            () -> new PercentageDamageEnchantment(
                    Enchantment.definition(CustomItemTags.WEAPON_ENCHANTABLE,
                            2, 2,
                            Enchantment.dynamicCost(5,10),
                            Enchantment.dynamicCost(25,12),
                            2, EquipmentSlot.MAINHAND),
                    CustomEntityTags.ABYSSAL));
    public static final RegistryObject<Enchantment> BEAST_HUNTER = ENCHANTMENTS.register("beast_hunter",
            () -> new DamageEnchantment(
                    Enchantment.definition(CustomItemTags.WEAPON_ENCHANTABLE,
                            2, 5,
                            Enchantment.dynamicCost(5,8),
                            Enchantment.dynamicCost(25,8),
                            1, EquipmentSlot.MAINHAND),
                    Optional.of(CustomEntityTags.BEAST)));


    public static final RegistryObject<Enchantment> FIREPOWER = ENCHANTMENTS.register("firepower",
            () -> new FirepowerEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            10, 5,
                            Enchantment.dynamicCost(1,8),
                            Enchantment.dynamicCost(24,10),
                            1, HAND_SLOTS)));
    public static final RegistryObject<Enchantment> GREATER_FIREPOWER = ENCHANTMENTS.register("greater_firepower",
            () -> new GreaterFirepowerEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            2, 5,
                            Enchantment.dynamicCost(6,10),
                            Enchantment.dynamicCost(36,12),
                            1, HAND_SLOTS)));
    public static final RegistryObject<Enchantment> STARPOWER = ENCHANTMENTS.register("starpower",
            () -> new StarpowerEnchantment(
                    Enchantment.definition(CustomItemTags.MAGIC_ENCHANTABLE,
                            10, 5,
                            Enchantment.dynamicCost(1,8),
                            Enchantment.dynamicCost(24,10),
                            1, HAND_SLOTS)));
    public static final RegistryObject<Enchantment> MOON_BLESSING = ENCHANTMENTS.register("moon_blessing",
            () -> new LunarPowerEnchantment(
                    Enchantment.definition(CustomItemTags.MAGIC_ENCHANTABLE,
                            2, 5,
                            Enchantment.dynamicCost(6,10),
                            Enchantment.dynamicCost(36,12),
                            1, HAND_SLOTS)));
    public static final RegistryObject<Enchantment> GUNSLINGER = ENCHANTMENTS.register("gunslinger",
            () -> new GunslingerEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            5, 3,
                            Enchantment.dynamicCost(1,10),
                            Enchantment.dynamicCost(30,12),
                            1, HAND_SLOTS)));
    public static final RegistryObject<Enchantment> SHARPSHOOTER = ENCHANTMENTS.register("sharpshooter",
            () -> new SharpshooterEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            5, 3,
                            Enchantment.dynamicCost(2,10),
                            Enchantment.dynamicCost(30,12),
                            1, HAND_SLOTS)));
    public static final RegistryObject<Enchantment> FLAMING_SHOT = ENCHANTMENTS.register("flaming_shot",
            () -> new FlamingShotEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            4, 1,
                            Enchantment.constantCost(20),
                            Enchantment.constantCost(46),
                            1, HAND_SLOTS)));
    public static final RegistryObject<Enchantment> EXPLODING_SHOT = ENCHANTMENTS.register("exploding_shot",
            () -> new ExplosingShotEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            2, 3,
                            Enchantment.dynamicCost(10,18),
                            Enchantment.dynamicCost(36,20),
                            1, HAND_SLOTS)));
    public static final RegistryObject<Enchantment> OPHIDIAN_BITE = ENCHANTMENTS.register("ophidian_bite",
            () -> new OphidianBiteEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            4, 2,
                            Enchantment.dynamicCost(6,12),
                            Enchantment.dynamicCost(30,12),
                            1, HAND_SLOTS)));
    public static final RegistryObject<Enchantment> EXPANDING_SHOT = ENCHANTMENTS.register("expanding_shot",
            () -> new ExpandingShotEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            5, 3,
                            Enchantment.dynamicCost(6,12),
                            Enchantment.dynamicCost(30,12),
                            1, HAND_SLOTS)));
    public static final RegistryObject<Enchantment> RICOCHET_SHOT = ENCHANTMENTS.register("ricochet_shot",
            () -> new RicochetEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            4, 1,
                            Enchantment.dynamicCost(10,10),
                            Enchantment.dynamicCost(30,10),
                            1, HAND_SLOTS)));
    public static final RegistryObject<Enchantment> PIERCING_SHOT = ENCHANTMENTS.register("piercing_shot",
            () -> new PiercingEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            6, 5,
                            Enchantment.dynamicCost(10,10),
                            Enchantment.dynamicCost(30,10),
                            1, HAND_SLOTS)));
    public static final RegistryObject<Enchantment> MISER_SOUL = ENCHANTMENTS.register("miser_soul",
            () -> new MiserSoulEnchantment(
                    Enchantment.definition(CustomItemTags.AMMO_CONSUMING,
                            6, 6,
                            Enchantment.dynamicCost(10,10),
                            Enchantment.dynamicCost(20,10),
                            1, HAND_SLOTS)));
    public static final RegistryObject<Enchantment> CHILD_OF_THUNDER = ENCHANTMENTS.register("child_of_the_thunder_god",
            () -> new ChildOfTheThunderGodEnchantment(
                    Enchantment.definition(CustomItemTags.GUN_ENCHANTABLE,
                            2, 1,
                            Enchantment.constantCost(25),
                            Enchantment.constantCost(50),
                            1, HAND_SLOTS)));


}
