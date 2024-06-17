package github.nitespring.darkestsouls.core.util;

import github.nitespring.darkestsouls.DarkestSouls;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import software.bernie.geckolib.event.GeoRenderEvent;

public final class CustomItemTags {

    public static final TagKey<Item> GUN_ENCHANTABLE = create("enchantable/gun");
    public static final TagKey<Item> WEAPON_ENCHANTABLE = create("enchantable/weapon_extended");
    public static final TagKey<Item> AMMO_CONSUMING = create("enchantable/ammo_consuming");
    public static final TagKey<Item> MAGIC_ENCHANTABLE = create("enchantable/magic");
    public static final TagKey<Item> MELEE_ENCHANTABLE = create("enchantable/melee");
    public static final TagKey<Item> GUNS = create("guns");
    public static final TagKey<Item> MELEE = create("melee_weapons");
    public static final TagKey<Item> STAVES = create("staves");
    private CustomItemTags() {
    }


    private static TagKey<Item> create(String p_203847_) {
        return TagKey.create(BuiltInRegistries.ITEM.key(), ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID , p_203847_));
    }

   /* public static TagKey<Item> create(ResourceLocation name) {
        return TagKey.create(BuiltInRegistries.ITEM.key(), name);
    }*/


}
