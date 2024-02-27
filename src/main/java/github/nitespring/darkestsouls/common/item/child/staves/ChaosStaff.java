package github.nitespring.darkestsouls.common.item.child.staves;

import github.nitespring.darkestsouls.common.entity.projectile.spell.ChaosFireball;
import github.nitespring.darkestsouls.common.entity.projectile.spell.Fireball;
import github.nitespring.darkestsouls.common.entity.projectile.spell.MagmaBurstParent;
import github.nitespring.darkestsouls.common.entity.projectile.spell.SoulDart;
import github.nitespring.darkestsouls.common.item.Staff;
import github.nitespring.darkestsouls.core.init.EntityInit;
import github.nitespring.darkestsouls.core.util.MathUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class ChaosStaff extends Staff {
    public ChaosStaff(float attackDamage, int durability, int tier, Properties properties) {
        super(attackDamage, durability, tier, properties);
    }

    @Override
    public void doSpellA(Player playerIn, ItemStack stackIn, InteractionHand HandIn) {
        int ammoAmount = 1;
        if(!playerIn.getCooldowns().isOnCooldown(this)&&(this.hasAmmo(playerIn,ammoAmount)||playerIn.isCreative())) {
            SoundEvent soundevent = SoundEvents.FIRECHARGE_USE;
            playerIn.level().playSound(playerIn, playerIn, soundevent, SoundSource.PLAYERS, 0.75F, 1.25F);
            //playerIn.level().playLocalSound(playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.FIRECHARGE_USE, playerIn.getSoundSource(), 0.5F, playerIn.getRandom().nextFloat() * 0.2F + 0.65F, false);
            Level levelIn = playerIn.level();
            Vec3 aim = playerIn.getLookAngle();
            Vec3 pos = playerIn.position();
            Vec3 mov = playerIn.getDeltaMovement();

            MagmaBurstParent e = new MagmaBurstParent(EntityInit.MAGMA_BURST.get(), levelIn);
            e.setDamage(this.getAttackDamage(playerIn, stackIn));
            e.setLifeTicks(36);
            e.setPos(pos.add(0, 1.25, 0).add(aim.normalize().multiply(1.5f, 1.5f, 1.5f)));
            e.xPower = 0.05 * aim.x * (1 + ((playerIn.getRandom().nextFloat() - 0.5) * 0.4));
            e.yPower = 0.05 * aim.y + 0.08f;
            e.zPower = 0.05 * aim.z * (1 + (playerIn.getRandom().nextFloat() - 0.5) * 0.4);
            e.setDamage(this.getAttackDamage(playerIn,stackIn));
            e.setOwner(playerIn);
                stackIn.hurtAndBreak(1, playerIn, (p_43276_) -> {
                    p_43276_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                });
            levelIn.addFreshEntity(e);
            playerIn.getCooldowns().addCooldown(this, 48);
            playerIn.level().playSound((Player)null, playerIn, SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 0.6F, 1.2F);
            if(!playerIn.isCreative()) {
                this.consumeAmmo(playerIn, ammoAmount);
            }
        }else if(!playerIn.getCooldowns().isOnCooldown(this)){playerIn.level().playSound((Player)null, playerIn, SoundEvents.BLAZE_HURT, SoundSource.PLAYERS, 0.6F, 0.4F);}

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
            stackIn.hurtAndBreak(2, playerIn, (p_43276_) -> {
                p_43276_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });

        levelIn.addFreshEntity(e);
        playerIn.level().playSound((Player)null, playerIn, SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 0.6F, 1.2F);

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
            int ammoAmount = 3;
            if(this.hasAmmo((Player) entityIn,ammoAmount)||((Player)entityIn).isCreative()) {
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
                if(!((Player)entityIn).isCreative()) {
                    this.consumeAmmo((Player)entityIn, ammoAmount);
                }
            }else{entityIn.level().playSound((Player)null, entityIn, SoundEvents.BLAZE_HURT, SoundSource.PLAYERS, 0.6F, 0.4F);}
        }
    }


    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        return true;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Level p_41422_, List<Component> tooltip, TooltipFlag p_41424_) {
        super.appendHoverText(stack, p_41422_, tooltip, p_41424_);
        String spellA = "\u00A76Magma Burst (1)";
        tooltip.add(Component.literal(spellA));

        String spellB = "\u00A76Great Chaos Fireball (3)";
        tooltip.add(Component.literal(spellB));


    }
}
