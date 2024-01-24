package github.nitespring.darkestsouls.common.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class Weapon extends SwordItem {


    public Weapon(Tier tier, int attack, float speed, Properties properties) {
        super(tier, attack, speed, properties);
    }

    public void doLeftClickAction(Player playerIn, ItemStack stackIn){



    }


}
