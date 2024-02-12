package github.nitespring.darkestsouls.common.item.child.staves;

import github.nitespring.darkestsouls.common.entity.projectile.spell.ChaosFireball;
import github.nitespring.darkestsouls.common.entity.projectile.spell.Fireball;
import github.nitespring.darkestsouls.common.entity.projectile.spell.SoulDart;
import github.nitespring.darkestsouls.common.item.Staff;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class ChaosStaff extends Staff {
    public ChaosStaff(float attackDamage, int durability, Properties properties) {
        super(attackDamage, durability, properties);
    }

    @Override
    public void doSpellA(Player playerIn, ItemStack stackIn, InteractionHand HandIn) {
        if(!playerIn.getCooldowns().isOnCooldown(this)) {
            Level levelIn = playerIn.level();
            Vec3 aim = playerIn.getLookAngle();
            Vec3 pos = playerIn.position();
            Vec3 mov = playerIn.getDeltaMovement();

            SoulDart e = new SoulDart(EntityInit.SOUL_DART.get(), levelIn);
            e.setDamage(this.getAttackDamage(playerIn, stackIn));
            e.setDimensionScale(0.1f);
            e.setMaxLifeTime(12);
            e.setPos(pos.add(0, 1.5, 0).add(aim.normalize().multiply(1.5f, 1.5f, 1.5f)));
            e.xPower = 0.25 * aim.x * (1 + (playerIn.getRandom().nextFloat() - 0.5) * 0.25);
            e.yPower = 0.25 * aim.y;
            e.zPower = 0.25 * aim.z * (1 + (playerIn.getRandom().nextFloat() - 0.5) * 0.25);

            levelIn.addFreshEntity(e);
            playerIn.getCooldowns().addCooldown(this, 5);
        }

    }


    public void doSpellB(Player playerIn, ItemStack stackIn, InteractionHand handIn, int i) {
        Level levelIn = playerIn.level();
        Vec3 aim = playerIn.getLookAngle();
        Vec3 pos = playerIn.position();
        Vec3 mov = playerIn.getDeltaMovement();

        ChaosFireball e = new ChaosFireball(EntityInit.CHAOS_FIREBALL.get(), levelIn);

        e.setPos(pos.add(0,2.75,0).add(aim.normalize().multiply(1.0f,1.25f,1.0f)));
        if (i >= 80) {
            e.setDamage(this.getAttackDamage(playerIn, stackIn) * 2.5f);
            e.setMaxLifeTime(100);
            e.setDimensionScale(1.5f);
            e.setOwner(playerIn);
            e.xPower = 0.045 * aim.x * (1 + (playerIn.getRandom().nextFloat() - 0.5) * 0.025) * (1 + 0.1);
            e.yPower = 0.035 * aim.y + 0.035;
            e.zPower = 0.045 * aim.z * (1 + (playerIn.getRandom().nextFloat() - 0.5) * 0.025) * (1 + 0.1);


        }else{

            e.setDamage(this.getAttackDamage(playerIn, stackIn) * 2.5f);
            e.setMaxLifeTime(80);
            e.setDimensionScale(1.25f);
            e.setOwner(playerIn);
            e.xPower = 0.045 * aim.x * (1 + (playerIn.getRandom().nextFloat() - 0.5) * 0.025) * (1 + 0.1);
            e.yPower = 0.035 * aim.y + 0.035;
            e.zPower = 0.045 * aim.z * (1 + (playerIn.getRandom().nextFloat() - 0.5) * 0.025) * (1 + 0.1);
        }

        levelIn.addFreshEntity(e);

    }
//TridentItem
    @Override
    public boolean canAttackBlock(BlockState p_43409_, Level p_43410_, BlockPos p_43411_, Player p_43412_) {
        return !p_43412_.isCreative();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level levelIn, Player playerIn, InteractionHand handIn) {
        playerIn.startUsingItem(handIn);
        return InteractionResultHolder.pass(playerIn.getItemInHand(handIn));
    }



    @Override
    public int getUseDuration(ItemStack p_43419_) {
        return 72000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.BOW;
    }

    @Override
    public void onUseTick(Level levelIn, LivingEntity entityIn, ItemStack stackIn, int n) {
        super.onUseTick(levelIn, entityIn, stackIn, n);
        SoundEvent soundevent = SoundEvents.FIRE_AMBIENT;
        SoundEvent soundevent1 = SoundEvents.FIRE_EXTINGUISH;
        int i = this.getUseDuration(stackIn) - n;
        if(i==10||i==80){
            levelIn.playSound((Player) null, entityIn, soundevent, SoundSource.PLAYERS, 0.5f, 1.25F);
        }
        if(i%4==0) {
            if (i >= 80) {
                levelIn.playSound((Player) null, entityIn, soundevent1, SoundSource.PLAYERS, 0.75F, 1.25F);
            } else if (i >= 10) {
                levelIn.playSound((Player) null, entityIn, soundevent1, SoundSource.PLAYERS, 0.5F, 1.5F);
            } else {
                levelIn.playSound((Player) null, entityIn, soundevent1, SoundSource.PLAYERS, 0.25F, 1.75F);
            }
        }

    }

    @Override
    public void releaseUsing(ItemStack stackIn, Level levelIn, LivingEntity entityIn, int n) {
        super.releaseUsing(stackIn, levelIn, entityIn, n);
        if (entityIn instanceof Player player) {
            int i = this.getUseDuration(stackIn) - n;
            if (i >= 10) {
                SoundEvent soundevent = SoundEvents.FIRECHARGE_USE;
                SoundEvent soundevent1 = SoundEvents.FIREWORK_ROCKET_LARGE_BLAST;
                this.doSpellB(player, stackIn, ((Player) entityIn).getUsedItemHand(), i);
                player.awardStat(Stats.ITEM_USED.get(this));
                player.getCooldowns().addCooldown(this, 70);
                levelIn.playSound((Player) null, player, soundevent, SoundSource.PLAYERS, 1.0F, 1.0F);
                levelIn.playSound((Player) null, player, soundevent1, SoundSource.PLAYERS, 1.0F, 1.0F);
            }
        }
    }


    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        return true;
    }
}
