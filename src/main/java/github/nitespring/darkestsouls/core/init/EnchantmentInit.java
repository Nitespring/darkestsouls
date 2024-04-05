package github.nitespring.darkestsouls.core.init;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.enchantment.FirepowerEnchantment;
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



}
