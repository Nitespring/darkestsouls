package github.nitespring.darkestsouls.common.item.throwing;

import github.nitespring.darkestsouls.common.entity.projectile.throwable.FirebombEntity;
import github.nitespring.darkestsouls.common.entity.projectile.throwable.MolotovCocktailEntity;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MolotovCocktail extends Item {
    private final float attackDamage;
    private final int useCooldown;
    private final int poiseDamage;
    public MolotovCocktail(float attackDamage, int useCooldown, int poiseDamage, Properties properties) {
        super(properties);
        this.attackDamage=attackDamage;
        this.useCooldown=useCooldown;
        this.poiseDamage=poiseDamage;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level levelIn, Player playerIn, InteractionHand handIn) {
        ItemStack stackIn = playerIn.getItemInHand(handIn);
        Vec3 pos = playerIn.position();
        Vec3 aim = playerIn.getLookAngle();


        float x = (float) (pos.x + 0.6 * aim.x);
        float y = (float) (pos.y + 1.4 + 0.6 * aim.y);
        float z = (float) (pos.z + 0.6 * aim.z);
        MolotovCocktailEntity entity = new MolotovCocktailEntity(EntityInit.MOLOTOV.get(), levelIn);
        entity.setPos(x,y,z);
        float flyingPower = 0.30f;
        entity.xPower=flyingPower*aim.x;
        entity.yPower=flyingPower*aim.y;
        entity.zPower=flyingPower*aim.z;
        entity.setOwner(playerIn);
        entity.setAttackDamage(this.attackDamage);
        entity.setPoiseDamage(this.poiseDamage);
        entity.setGravPower(0.001f);
        entity.setHorizontalSpread(4.0);
        entity.setVerticalSpread(3.5);
        levelIn.addFreshEntity(entity);

        playerIn.getCooldowns().addCooldown(stackIn.getItem(), useCooldown);
        if(!playerIn.isCreative()){
            stackIn.shrink(1);
        }
        return InteractionResultHolder.success(stackIn);
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {

        tooltip.add(Component.literal("+").append(Component.literal(""+this.attackDamage)).append(Component.translatable("translation.darkestsouls.fire_damage")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.RED));
        tooltip.add(Component.translatable("translation.darkestsouls.molotov").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.RED));

        super.appendHoverText(stack, level, tooltip, flag);
    }

}
