package github.nitespring.darkestsouls.common.entity.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class CustomBlockTags{

    public static final TagKey<Block> BOMB_BREAKABLE = create("darkestsouls:bomb_breakable");
    public static final TagKey<Block> FLAME_BREAKABLE = create("darkestsouls:flame_breakable");

    private CustomBlockTags() {
    }


    private static TagKey<Block> create(String p_203847_) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation(p_203847_));
    }

    public static TagKey<Block> create(ResourceLocation name) {
        return TagKey.create(Registries.BLOCK, name);
    }


}
