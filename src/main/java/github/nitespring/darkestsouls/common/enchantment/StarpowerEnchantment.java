package github.nitespring.darkestsouls.common.enchantment;

import net.minecraft.world.item.enchantment.Enchantment;

public class StarpowerEnchantment extends Enchantment {
    public StarpowerEnchantment(EnchantmentDefinition pDefinition) {
        super(pDefinition);
    }
    @Override
    public boolean isTradeable() {
        return true;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }

    @Override
    public boolean isCurse() {
        return false;
    }

    @Override
    public boolean isTreasureOnly() {
        return false;
    }

    @Override
    public boolean isDiscoverable() {
        return true;
    }

}
