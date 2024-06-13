package github.nitespring.darkestsouls.common.item;

import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public interface ITransformableItem {

    public abstract Item getTransformedWeapon();

    default void transform(Player playerIn, Level worldIn) {


        ItemStack itemstack = playerIn.getItemInHand(InteractionHand.MAIN_HAND);
        Component name = itemstack.getDisplayName();
        //CompoundTag compound = itemstack.getComponents();
        DataComponentMap componentMap = itemstack.getComponents();

        Vec3 pos = playerIn.position();
        ItemStack trick = new ItemStack(getTransformedWeapon(), 1);
        trick.set(DataComponents.CUSTOM_NAME, name);
        //trick.set(DataComponents.ENCHANTMENTS)
        //ItemStack trick = new ItemStack(getTransformedWeapon(), 1, compound).setHoverName(name);

        playerIn.setItemInHand(InteractionHand.MAIN_HAND, trick);
        trick.applyComponents(componentMap);
        //trick.setTag(compound);

        playTrickSound(worldIn, pos);
    }

    default void playTrickSound(Level worldIn, Vec3 pos ) {

        worldIn.playSound((Player)null, pos.x, pos.y, pos.z, getEquipSound(), SoundSource.PLAYERS, 0.6f, 0.4f);

    }

    default SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_CHAIN.value();
    }

}
