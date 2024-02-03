package github.nitespring.darkestsouls.common.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface ILeftClickItem {

    public abstract void doLeftClickAction(Player playerIn, ItemStack stackIn);
}
