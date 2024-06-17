package github.nitespring.darkestsouls.core.init;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.mob.beast.AshenBloodBeastPatient;
import github.nitespring.darkestsouls.common.item.Gun;
import github.nitespring.darkestsouls.common.item.IAmmoConsumingItem;
import github.nitespring.darkestsouls.core.util.CustomEntityTags;
import github.nitespring.darkestsouls.core.util.CustomItemTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Optional;

public class EnchantmentInit {

    private static ResourceKey<Enchantment> key(String string) {
        return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID,string));
    }

    public static final ResourceKey<Enchantment> BLOODBLADE = key("bloodblade");
    public static final ResourceKey<Enchantment> FROST_BLADE = key("frost_blade");
    public static final ResourceKey<Enchantment> POISONED_BLADE = key("poisoned_blade");
    public static final ResourceKey<Enchantment> TOXIC_BLADE = key("toxic_blade");
    public static final ResourceKey<Enchantment> ROTTEN_BLADE = key("rotten_blade");
    public static final ResourceKey<Enchantment> WITHERED_BLADE = key("withered_blade");
    public static final ResourceKey<Enchantment> SERRATED = key("serrated");
    public static final ResourceKey<Enchantment> ABYSS_CLEANSER = key("abyss_cleanser");
    public static final ResourceKey<Enchantment> BEAST_HUNTER = key("beast_hunter");
    public static final ResourceKey<Enchantment> FIREPOWER = key("firepower");
    public static final ResourceKey<Enchantment> GREATER_FIREPOWER = key("greater_firepower");
    public static final ResourceKey<Enchantment> STARPOWER = key("starpower");
    public static final ResourceKey<Enchantment> MOON_BLESSING = key("moon_blessing");
    public static final ResourceKey<Enchantment> GUNSLINGER = key("gunslinger");
    public static final ResourceKey<Enchantment> SHARPSHOOTER = key("sharpshooter");
    public static final ResourceKey<Enchantment> FLAMING_SHOT = key("flaming_shot");
    public static final ResourceKey<Enchantment> EXPLODING_SHOT = key("exploding_shot");
    public static final ResourceKey<Enchantment> OPHIDIAN_BITE = key("ophidian_bite");
    public static final ResourceKey<Enchantment> EXPANDING_SHOT = key("expanding_shot");
    public static final ResourceKey<Enchantment> RICOCHET_SHOT = key("ricochet_shot");
    public static final ResourceKey<Enchantment> PIERCING_SHOT = key("piercing_shot");
    public static final ResourceKey<Enchantment> MISER_SOUL = key("miser_soul");
    public static final ResourceKey<Enchantment> CHILD_OF_THUNDER = key("child_of_the_thunder_god");


}
