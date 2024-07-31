package github.nitespring.darkestsouls.core.init;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.enchantment.*;
import github.nitespring.darkestsouls.common.entity.mob.beast.AshenBloodBeastPatient;
import github.nitespring.darkestsouls.common.item.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EnchantmentInit {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS,
            DarkestSouls.MODID);
    public static final EquipmentSlot[] HAND_SLOTS = new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND};

    public static final EnchantmentCategory GUN = EnchantmentCategory.create("gun", item -> item instanceof Gun);
    public static final EnchantmentCategory MAGIC = EnchantmentCategory.create("magic", item -> item instanceof AlchemyTool||item instanceof Staff);
    public static final EnchantmentCategory AMMO_CONSUMER = EnchantmentCategory.create("ammo_consumer", item -> item instanceof IAmmoConsumingItem);
    public static final EnchantmentCategory WEAPON = EnchantmentCategory.create("weapon_custom", item -> item instanceof SwordItem || item instanceof Weapon);

    public static final RegistryObject<Enchantment> MOON_BLESSING = ENCHANTMENTS.register("moon_blessing",
            () -> new MoonBlessingEnchantment(Enchantment.Rarity.COMMON));
    public static final RegistryObject<Enchantment> STARPOWER = ENCHANTMENTS.register("starpower",
            () -> new StarpowerEnchantment(Enchantment.Rarity.COMMON));
    public static final RegistryObject<Enchantment> BLOODBLADE = ENCHANTMENTS.register("bloodblade",
            () -> new BloodBladeEnchantment(Enchantment.Rarity.COMMON));

    public static final RegistryObject<Enchantment> TOXIC_BLADE = ENCHANTMENTS.register("toxic_blade",
            () -> new InflictMobEffectEnchantment(1, EffectInit.TOXIC.get(),Enchantment.Rarity.COMMON));
    public static final RegistryObject<Enchantment> ROTTEN_BLADE = ENCHANTMENTS.register("rotten_blade",
            () -> new InflictMobEffectEnchantment(1, EffectInit.ROT.get(),Enchantment.Rarity.COMMON));
    public static final RegistryObject<Enchantment> FROST_BLADE = ENCHANTMENTS.register("frost_blade",
            () -> new InflictMobEffectEnchantment(3, EffectInit.FROST.get(),Enchantment.Rarity.COMMON));
    public static final RegistryObject<Enchantment> POISONED_BLADE = ENCHANTMENTS.register("poisoned_blade",
            () -> new InflictMobEffectEnchantment(2, MobEffects.POISON,Enchantment.Rarity.COMMON));
    public static final RegistryObject<Enchantment> WITHERED_BLADE = ENCHANTMENTS.register("withered_blade",
            () -> new InflictMobEffectEnchantment(1, MobEffects.WITHER,Enchantment.Rarity.COMMON));

    public static final RegistryObject<Enchantment> BEAST_HUNTER = ENCHANTMENTS.register("beast_hunter",
            () -> new BeastHunterEnchantment(Enchantment.Rarity.COMMON));
    public static final RegistryObject<Enchantment> ABYSS_CLEANSER = ENCHANTMENTS.register("abyss_cleanser",
            () -> new AbyssCleanserEnchantment(Enchantment.Rarity.COMMON));
    public static final RegistryObject<Enchantment> SERRATED = ENCHANTMENTS.register("serrated",
            () -> new SerratedEnchantment(Enchantment.Rarity.COMMON));

    public static final RegistryObject<FirepowerEnchantment> FIREPOWER = ENCHANTMENTS.register("firepower",
            () -> new FirepowerEnchantment(Enchantment.Rarity.COMMON));
    public static final RegistryObject<GreaterFirepowerEnchantment> GREATER_FIREPOWER = ENCHANTMENTS.register("greater_firepower",
            () -> new GreaterFirepowerEnchantment(Enchantment.Rarity.RARE));
    public static final RegistryObject<GunslingerEnchantment> GUNSLINGER = ENCHANTMENTS.register("gunslinger",
            () -> new GunslingerEnchantment(Enchantment.Rarity.UNCOMMON));
    public static final RegistryObject<SharpshooterEnchantment> SHARPSHOOTER = ENCHANTMENTS.register("sharpshooter",
            () -> new SharpshooterEnchantment(Enchantment.Rarity.RARE));
    public static final RegistryObject<FlamingShotEnchantment> FLAMING_SHOT = ENCHANTMENTS.register("flaming_shot",
            () -> new FlamingShotEnchantment(Enchantment.Rarity.RARE));
    public static final RegistryObject<ExplosingShotEnchantment> EXPLODING_SHOT = ENCHANTMENTS.register("exploding_shot",
            () -> new ExplosingShotEnchantment(Enchantment.Rarity.VERY_RARE));
    public static final RegistryObject<OphidianBiteEnchantment> OPHIDIAN_BITE = ENCHANTMENTS.register("ophidian_bite",
            () -> new OphidianBiteEnchantment(Enchantment.Rarity.RARE));
    public static final RegistryObject<ExpandingShotEnchantment> EXPANDING_SHOT = ENCHANTMENTS.register("expanding_shot",
            () -> new ExpandingShotEnchantment(Enchantment.Rarity.RARE));
    public static final RegistryObject<RicochetEnchantment> RICOCHET_SHOT = ENCHANTMENTS.register("ricochet_shot",
            () -> new RicochetEnchantment(Enchantment.Rarity.UNCOMMON));
    public static final RegistryObject<PiercingEnchantment> PIERCING_SHOT = ENCHANTMENTS.register("piercing_shot",
            () -> new PiercingEnchantment(Enchantment.Rarity.UNCOMMON));
    public static final RegistryObject<MiserSoulEnchantment> MISER_SOUL = ENCHANTMENTS.register("miser_soul",
            () -> new MiserSoulEnchantment(Enchantment.Rarity.UNCOMMON));
    public static final RegistryObject<ChildOfTheThunderGodEnchantment> CHILD_OF_THUNDER = ENCHANTMENTS.register("child_of_the_thunder_god",
            () -> new ChildOfTheThunderGodEnchantment(Enchantment.Rarity.UNCOMMON));



}
