package github.nitespring.darkestsouls.core.util;

import github.nitespring.darkestsouls.DarkestSouls;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;

public final class CustomEntityTags {
    public static final TagKey<EntityType<?>> HOLLOW = create("hollow");
    public static final TagKey<EntityType<?>> SKELETON = create("skeleton");
    public static final TagKey<EntityType<?>> CHURCH = create("church");
    public static final TagKey<EntityType<?>> HOLY = create("holy");
    public static final TagKey<EntityType<?>> ABYSSAL = create("abyssal");
    public static final TagKey<EntityType<?>> CURSED = create("cursed");
    public static final TagKey<EntityType<?>> BEAST = create("beast");
    public static final TagKey<EntityType<?>> BEASTLY = create("beastly");
    public static final TagKey<EntityType<?>> BLEED_IMMUNE = create("bleed_immune");
    public static final TagKey<EntityType<?>> POISON_IMMUNE = create("poison_immune");
    public static final TagKey<EntityType<?>> ROT_IMMUNE = create("rot_immune");
    public static final TagKey<EntityType<?>> FROST_IMMUNE = create("frost_immune");
    public static final TagKey<EntityType<?>> WITHER_IMMUNE = create("wither_immune");

    private CustomEntityTags() {
    }


    private static TagKey<EntityType<?>> create(String p_203847_) {
        return TagKey.create(BuiltInRegistries.ENTITY_TYPE.key(), new ResourceLocation(DarkestSouls.MODID , p_203847_));
    }
}
