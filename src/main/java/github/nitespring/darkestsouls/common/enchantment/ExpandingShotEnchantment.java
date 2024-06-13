package github.nitespring.darkestsouls.common.enchantment;

import github.nitespring.darkestsouls.core.init.EnchantmentInit;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;

public class ExpandingShotEnchantment extends Enchantment {
    public ExpandingShotEnchantment(EnchantmentDefinition pDefinition) {
        super(pDefinition);
    }
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
