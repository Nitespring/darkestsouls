package github.nitespring.darkestsouls.common.enchantment;

import github.nitespring.darkestsouls.core.init.EnchantmentInit;
import github.nitespring.darkestsouls.core.util.CustomMobType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class BeastHunterEnchantment extends Enchantment {


    public BeastHunterEnchantment(Enchantment.Rarity p_44628_) {
        super(p_44628_, EnchantmentCategory.WEAPON, EnchantmentInit.HAND_SLOTS);
    }

    public int getMinCost(int p_44633_) {
        return 5 + (p_44633_ - 1) * 8;
    }

    public int getMaxCost(int p_44646_) {
        return this.getMinCost(p_44646_) + 20;
    }

    public int getMaxLevel() {
        return 5;
    }

    public float getDamageBonus(int i, MobType type) {
        if (type == CustomMobType.BEAST) {
            return (float)i * 2.5F;
        }else{
            return 0;
        }
    }

    public boolean checkCompatibility(Enchantment p_44644_) {
        return !(p_44644_ instanceof BeastHunterEnchantment);
    }

    public boolean canEnchant(ItemStack p_44642_) {
        return p_44642_.getItem() instanceof AxeItem ? true : super.canEnchant(p_44642_);
    }



}