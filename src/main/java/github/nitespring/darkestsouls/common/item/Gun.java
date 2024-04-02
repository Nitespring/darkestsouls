package github.nitespring.darkestsouls.common.item;

import github.nitespring.darkestsouls.common.entity.projectile.throwable.FirebombEntity;
import github.nitespring.darkestsouls.core.init.EntityInit;
import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.function.Predicate;

public class Gun extends Item implements IAmmoConsumingItem,ILeftClickItem {

    private final float attackDamage;
    private final int useCooldown;
    private final int poiseDamage;
    private final int flyingTime;
    private final float flyingPower;
    private final float size;
    private final int ricochet;
    private final int pierce;
    private final int ammoAmount;
    private final int durability;

    public Gun(float damage, int cooldown, int poise, float size, float flyingPower, int flyingTime, int ricochet, int pierce, int ammoAmount, int durability, Properties properties) {
        super(properties);
        this.attackDamage = damage;
        this.useCooldown = cooldown;
        this.poiseDamage = poise;
        this.flyingPower = flyingPower;
        this.flyingTime = flyingTime;
        this.size = size;
        this.ricochet = ricochet;
        this.pierce = pierce;
        this.ammoAmount=ammoAmount;
        this.durability=durability;
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(hand== InteractionHand.OFF_HAND) {
            ItemStack stackIn = player.getItemInHand(hand);
            int ammoAmount = this.getAmmoAmount();
            if (this.hasAmmo(player, ammoAmount) || player.isCreative()) {
                this.shoot(player, level, stackIn);
                stackIn.hurtAndBreak(1, player, (p_43276_) -> {
                    p_43276_.broadcastBreakEvent(InteractionHand.OFF_HAND);
                });
                if (!player.isCreative()) {
                    this.consumeAmmo(player, ammoAmount);
                }
                return InteractionResultHolder.success(stackIn);
            } else {
                return InteractionResultHolder.fail(stackIn);
            }
        }else {
            if(player.getItemInHand(InteractionHand.OFF_HAND)==ItemStack.EMPTY) {
                player.startUsingItem(hand);
            }
            return super.use(level, player, hand);
        }
    }
    public float getAttackDamage(Player playerIn, ItemStack stackIn) {
        return attackDamage;
    }
    public int getUseCooldown(Player playerIn, ItemStack stackIn) {
        return useCooldown;
    }
    public int getPoiseDamage(Player playerIn, ItemStack stackIn) {
        return poiseDamage;
    }
    public int getFlyingTime() {
        return flyingTime;
    }
    public float getBaseSize(){
        return size;
    }
    public float flyingPower(Player playerIn, ItemStack stackIn){
        return flyingPower;
    }
    public int getRicochet(Player playerIn, ItemStack stackIn) {
        return ricochet;
    }
    public int getPierce(Player playerIn, ItemStack stackIn) {
        return pierce;
    }
    public int getAmmoAmount() {
        return ammoAmount;
    }

    @Override
    public Predicate<ItemStack> getAmmoType() {
        return (p_43015_) -> {
            return p_43015_.is(ItemInit.QUICKSILVER_BULLET.get());
        };
    }

    public void shoot(Player player, Level level, ItemStack stackIn) {
    }
    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }
    @Override
    public int getMaxDamage(ItemStack stack) {
        return durability;
    }
    @Override
    public void doLeftClickAction(Player player, ItemStack stackIn) {
        int ammoAmount = this.getAmmoAmount();
        if (!player.getCooldowns().isOnCooldown(this)&&(this.hasAmmo(player, ammoAmount) || player.isCreative())) {
            this.shoot(player, player.level(), stackIn);
            stackIn.hurtAndBreak(1, player, (p_43276_) -> {
                p_43276_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
            if (!player.isCreative()) {
                this.consumeAmmo(player, ammoAmount);
            }
        }
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        return true;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 40000;
    }

    @Override
    public boolean canAttackBlock(BlockState p_43291_, Level p_43292_, BlockPos p_43293_, Player p_43294_) {
        return !p_43294_.isCreative();
    }

    @Override
    public boolean isDamageable(ItemStack stack) {

        return true;
    }
}
