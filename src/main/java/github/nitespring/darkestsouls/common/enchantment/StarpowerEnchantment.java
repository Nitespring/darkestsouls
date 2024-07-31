package github.nitespring.darkestsouls.common.enchantment;

import github.nitespring.darkestsouls.core.init.EnchantmentInit;
import net.minecraft.world.item.enchantment.Enchantment;

public class StarpowerEnchantment extends Enchantment {
    public StarpowerEnchantment(Rarity rarity) {
        super(rarity,  EnchantmentInit.MAGIC, EnchantmentInit.HAND_SLOTS);
    }
    @Override
    public int getMinCost(int i) {
        return 8 * i;
    }

    @Override
    public int getMaxCost(int i) {
        return this.getMinCost(i) + 30;
    }
    @Override
    public int getMaxLevel() {
        return 5;
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
