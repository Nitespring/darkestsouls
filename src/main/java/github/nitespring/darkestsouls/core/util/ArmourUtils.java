package github.nitespring.darkestsouls.core.util;

import github.nitespring.darkestsouls.common.item.CustomArmourItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;

public class ArmourUtils {


    public static final int getMagicDefence(Player player){

        int i = 0;
        if(player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof CustomArmourItem head){
            i=i+head.getMagicDefence();
        }
        if(player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof CustomArmourItem chest){
            i=i+chest.getMagicDefence();
        }
        if(player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof CustomArmourItem legs){
            i=i+legs.getMagicDefence();
        }
        if(player.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof CustomArmourItem feet){
            i=i+feet.getMagicDefence();
        }
        return i;
    }
    public static final int getFireResistance(Player player){

        int i = 0;
        if(player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof CustomArmourItem head){
            i=i+head.getFireDefence();
        }
        if(player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof CustomArmourItem chest){
            i=i+chest.getFireDefence();
        }
        if(player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof CustomArmourItem legs){
            i=i+legs.getFireDefence();
        }
        if(player.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof CustomArmourItem feet){
            i=i+feet.getFireDefence();
        }
        return i;
    }
    public static final int getBleedResistance(Player player){

        int i = 0;
        if(player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof CustomArmourItem head){
            i=i+head.getBloodResistance();
        }
        if(player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof CustomArmourItem chest){
            i=i+chest.getBloodResistance();
        }
        if(player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof CustomArmourItem legs){
            i=i+legs.getBloodResistance();
        }
        if(player.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof CustomArmourItem feet){
            i=i+feet.getBloodResistance();
        }
        return i;
    }
    public static final int getLuckBonus(Player player){

        int i = 0;
        if(player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof CustomArmourItem head){
            i=i+head.getLuckBonus();
        }
        if(player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof CustomArmourItem chest){
            i=i+chest.getLuckBonus();
        }
        if(player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof CustomArmourItem legs){
            i=i+legs.getLuckBonus();
        }
        if(player.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof CustomArmourItem feet){
            i=i+feet.getLuckBonus();
        }
        return i;
    }
    public static final int getMagicBonus(Player player){

        int i = 0;
        if(player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof CustomArmourItem head){
            i=i+head.getMagicBonus();
        }
        if(player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof CustomArmourItem chest){
            i=i+chest.getMagicBonus();
        }
        if(player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof CustomArmourItem legs){
            i=i+legs.getMagicBonus();
        }
        if(player.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof CustomArmourItem feet){
            i=i+feet.getMagicBonus();
        }
        return i;
    }
    public static final int getAlchemyBonus(Player player){

        int i = 0;
        if(player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof CustomArmourItem head){
            i=i+head.getAlchemyBonus();
        }
        if(player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof CustomArmourItem chest){
            i=i+chest.getAlchemyBonus();
        }
        if(player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof CustomArmourItem legs){
            i=i+legs.getAlchemyBonus();
        }
        if(player.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof CustomArmourItem feet){
            i=i+feet.getAlchemyBonus();
        }
        return i;
    }
    public static final int getGunBonus(Player player){

        int i = 0;
        if(player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof CustomArmourItem head){
            i=i+head.getGunBonus();
        }
        if(player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof CustomArmourItem chest){
            i=i+chest.getGunBonus();
        }
        if(player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof CustomArmourItem legs){
            i=i+legs.getGunBonus();
        }
        if(player.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof CustomArmourItem feet){
            i=i+feet.getGunBonus();
        }
        return i;
    }
}
