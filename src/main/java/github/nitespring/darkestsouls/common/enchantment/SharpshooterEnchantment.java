package github.nitespring.darkestsouls.common.enchantment;

import github.nitespring.darkestsouls.core.init.EnchantmentInit;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public class SharpshooterEnchantment extends Enchantment {
    public SharpshooterEnchantment(Rarity rarity) {
        super(rarity,  EnchantmentInit.GUN, EnchantmentInit.HAND_SLOTS);
    }
    public int getMinCost(int i) {
        return 12 * i;
    }

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

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean canEnchant(ItemStack p_44689_) {
        return true;
    }
}
