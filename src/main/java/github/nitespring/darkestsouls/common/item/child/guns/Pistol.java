package github.nitespring.darkestsouls.common.item.child.guns;

import github.nitespring.darkestsouls.common.entity.projectile.Bullet;
import github.nitespring.darkestsouls.common.entity.projectile.throwable.FirebombEntity;
import github.nitespring.darkestsouls.common.item.Gun;
import github.nitespring.darkestsouls.common.item.ILeftClickItem;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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

public class Pistol extends Gun implements ILeftClickItem {


    public Pistol(float damage, int cooldown, int poise, float size, float flyingPower, int flyingTime, int ricochet, int pierce, int ammoAmount, int durability, Properties properties) {
        super(damage, cooldown, poise, size, flyingPower, flyingTime, ricochet, pierce, ammoAmount, durability, properties);
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
    @Override
    public void shoot(Player player, Level level, ItemStack stackIn) {
        Vec3 pos = player.position();
        Vec3 aim = player.getLookAngle();


        float x = (float) (pos.x + 0.6 * aim.x);
        float y = (float) (pos.y + 1.5 + 0.6 * aim.y);
        float z = (float) (pos.z + 0.6 * aim.z);

        Bullet entity = new Bullet(EntityInit.BULLET.get(), level);
        entity.setPos(x, y, z);
        float flyingPower = this.flyingPower(player, stackIn);
        //float flyingPower = 0.5f;
        entity.xPower = flyingPower * aim.x;
        entity.yPower = flyingPower * aim.y;
        entity.zPower = flyingPower * aim.z;
        entity.setOwner(player);
        entity.setAttackDamage(this.getAttackDamage(player, stackIn));
        entity.setPoiseDamage(this.getPoiseDamage(player, stackIn));
        entity.setFlyingTime(this.getFlyingTime());
        entity.setSize(this.getBaseSize());
        entity.setPierce(this.getPierce(player, stackIn));
        entity.setRicochet(this.getRicochet(player, stackIn));
        level.addFreshEntity(entity);

        player.getCooldowns().addCooldown(stackIn.getItem(), this.getUseCooldown(player, stackIn));

        player.level().playSound((Player) null, player, SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1.0F, 1.0f);
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
