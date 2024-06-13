package github.nitespring.darkestsouls.common.enchantment;

import github.nitespring.darkestsouls.core.init.EnchantmentInit;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public class ChildOfTheThunderGodEnchantment extends Enchantment {
    public ChildOfTheThunderGodEnchantment(EnchantmentDefinition pDefinition) {
        super(pDefinition);
    }

    @Override
    protected boolean checkCompatibility(Enchantment ench) {
        if(ench instanceof ExplosingShotEnchantment){
            return false;
        }else {
            return super.checkCompatibility(ench);
        }
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
