package github.nitespring.darkestsouls.common.item.child.weapons.sawcleaver;

import github.nitespring.darkestsouls.common.item.TrickWeapon;
import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;

public class HunterAxeExtended extends TrickWeapon {
    public HunterAxeExtended(Tier tier, float attack, float speed, float knockback, int poise, int blood, int poison, int frost, int rot, int death, int fire, int holy, int durability, int enchantability, float movementSpeed, int maxTargets, Properties properties) {
        super(tier, attack, speed, knockback, poise, blood, poison, frost, rot, death, fire, holy, durability, enchantability, movementSpeed, maxTargets, properties);
    }

    @Override
    public Item getTransformedWeapon() {
        return ItemInit.HUNTER_AXE.get();
    }
}
