package github.nitespring.darkestsouls.core.init;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.enchantment.*;
import github.nitespring.darkestsouls.common.entity.mob.beast.AshenBloodBeastPatient;
import github.nitespring.darkestsouls.common.item.Gun;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
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




}
