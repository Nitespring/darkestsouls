package github.nitespring.darkestsouls.common.item;

import github.nitespring.darkestsouls.core.init.KeybindInit;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public abstract class TrickWeapon extends Weapon implements ITransformableItem{



    public TrickWeapon(Tier tier, float attack, float speed, float knockback, int poise, int blood, int poison, int frost, int rot, int death, int fire, int holy, int durability, int enchantability, float movementSpeed, int maxTargets, Properties properties) {
        super(tier, attack, speed, knockback, poise, blood, poison, frost, rot, death, fire, holy, durability, enchantability, movementSpeed, maxTargets, properties);
    }




    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Level p_41422_, List<Component> tooltip, TooltipFlag p_41424_) {


        String colour = "\u00A78\u00A7o";

        tooltip.add(Component.translatable("translation.darkestsouls.trick1").append(KeybindInit.trickKeybind.getKey().getDisplayName()).append(Component.translatable("translation.darkestsouls.trick2")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));



        super.appendHoverText(stack, p_41422_, tooltip, p_41424_);
    }

}
