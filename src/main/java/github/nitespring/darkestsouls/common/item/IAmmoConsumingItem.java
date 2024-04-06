package github.nitespring.darkestsouls.common.item;

import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.Random;
import java.util.SplittableRandom;
import java.util.function.Predicate;

public interface IAmmoConsumingItem{
    public abstract Predicate<ItemStack> getAmmoType();


    public default ItemStack getAmmo(Player playerIn){

        ItemStack ammo = null;
        int size = playerIn.getInventory().getContainerSize();
        Predicate<ItemStack> predicate = this.getAmmoType();

        for(int i = 0; i < size; ++i) {
            ItemStack itemstack1 = playerIn.getInventory().getItem(i);
            if (predicate.test(itemstack1)) {
                ammo=itemstack1;
            }
        }

        return ammo;
    }

    public default boolean hasAmmo(Player playerIn, int amount){
        int size = playerIn.getInventory().getContainerSize();
        int lastCheckedSlot=0;
        int amountInPossession=0;
        Predicate<ItemStack> predicate = this.getAmmoType();
        while(lastCheckedSlot<size && amountInPossession<amount) {
            for (int i = lastCheckedSlot; i < size; ++i) {
                ItemStack itemstack1 = playerIn.getInventory().getItem(i);
                if (predicate.test(itemstack1)) {
                    if(itemstack1.getCount()>=amount-amountInPossession){
                        amountInPossession=amount;
                    }else{
                        amountInPossession=amountInPossession+itemstack1.getCount();
                    }
                }
                lastCheckedSlot++;
            }
        }
        if(amountInPossession>=amount){return true;}else{return false;}
    }
    public default void consumeAmmo(Player playerIn, int amount){
        int size = playerIn.getInventory().getContainerSize();
        int lastCheckedSlot=0;
        int amountInPossession=0;
        Predicate<ItemStack> predicate = this.getAmmoType();
        while(lastCheckedSlot<size && amountInPossession<amount) {
            for (int i = lastCheckedSlot; i < size; ++i) {
                ItemStack itemstack1 = playerIn.getInventory().getItem(i);
                if (predicate.test(itemstack1)) {
                    if(itemstack1.getCount()>=amount-amountInPossession){

                        itemstack1.shrink(amount-amountInPossession);
                        amountInPossession=amount;
                    }else{
                        amountInPossession=amountInPossession+itemstack1.getCount();
                        itemstack1.shrink(itemstack1.getCount());
                    }
                }
                lastCheckedSlot++;
            }
        }
    }
    public default void consumeAmmoApplyLuck(Player playerIn, int amount, float luck) {
        int size = playerIn.getInventory().getContainerSize();
        int lastCheckedSlot = 0;
        int amountInPossession = 0;
        int discount = 0;
        for (int i = 0; i < amount; i++) {

            float r = new SplittableRandom().nextFloat();

            if(r<=luck){
               discount++;
            }
            //System.out.println("work");
        }
        amount = amount - discount;
        if (amount > 0) {
            Predicate<ItemStack> predicate = this.getAmmoType();
            while (lastCheckedSlot < size && amountInPossession < amount) {
                for (int i = lastCheckedSlot; i < size; ++i) {
                    ItemStack itemstack1 = playerIn.getInventory().getItem(i);
                    if (predicate.test(itemstack1)) {
                        if (itemstack1.getCount() >= amount - amountInPossession) {

                            itemstack1.shrink(amount - amountInPossession);
                            amountInPossession = amount;
                        } else {
                            amountInPossession = amountInPossession + itemstack1.getCount();
                            itemstack1.shrink(itemstack1.getCount());
                        }
                    }
                    lastCheckedSlot++;
                }
            }
        }
    }
}
