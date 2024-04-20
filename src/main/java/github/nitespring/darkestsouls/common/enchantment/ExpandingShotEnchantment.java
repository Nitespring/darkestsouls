package github.nitespring.darkestsouls.common.enchantment;

import github.nitespring.darkestsouls.core.init.EnchantmentInit;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public class ExpandingShotEnchantment extends Enchantment {
    public ExpandingShotEnchantment(Rarity rarity) {
        super(rarity,  EnchantmentInit.GUN, EnchantmentInit.HAND_SLOTS);
    }
    @Override
    public int getMinCost(int i) {
        return 12 * i;
    }
    @Override
    public int getMaxCost(int i) {
        return this.getMinCost(i) + 30;
    }
    @Override
    public int getMaxLevel() {
        return 3;
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
