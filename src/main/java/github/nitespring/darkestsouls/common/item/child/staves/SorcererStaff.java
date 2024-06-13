package github.nitespring.darkestsouls.common.item.child.staves;

import github.nitespring.darkestsouls.common.entity.projectile.spell.SoulArrow;
import github.nitespring.darkestsouls.common.entity.projectile.spell.SoulDart;
import github.nitespring.darkestsouls.common.item.Staff;
import github.nitespring.darkestsouls.core.init.EntityInit;
import github.nitespring.darkestsouls.core.util.MathUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
 import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class SorcererStaff extends Staff {
    public SorcererStaff(float attackDamage, int durability, int tier, Properties properties) {
        super(attackDamage, durability, tier, properties);
    }

    @Override
    public void doSpellA(Player playerIn, ItemStack stackIn, InteractionHand handIn) {
        int ammoAmount = 1;
        if(!playerIn.getCooldowns().isOnCooldown(this)&&(this.hasAmmo(playerIn,ammoAmount)||playerIn.isCreative())) {
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
            if(handIn == InteractionHand.MAIN_HAND) {stackIn.hurtAndBreak(1, playerIn, EquipmentSlot.MAINHAND);}
            if(handIn == InteractionHand.OFF_HAND) {stackIn.hurtAndBreak(1, playerIn, EquipmentSlot.OFFHAND);}
            levelIn.addFreshEntity(e);
            playerIn.getCooldowns().addCooldown(this, 5);
            playerIn.level().playSound((Player)null, playerIn, SoundEvents.RESPAWN_ANCHOR_CHARGE, SoundSource.PLAYERS, 0.6F, 1.2F);
            if(!playerIn.isCreative()) {
                this.consumeAmmoApplyLuck(playerIn, ammoAmount, this.getLuck(playerIn,stackIn));
            }
        }else if(!playerIn.getCooldowns().isOnCooldown(this)){playerIn.level().playSound((Player)null, playerIn, SoundEvents.BLAZE_HURT, SoundSource.PLAYERS, 0.6F, 0.4F);}

    }


    public void doSpellB(Player playerIn, ItemStack stackIn, InteractionHand handIn, int i) {
        Level levelIn = playerIn.level();
        Vec3 aim = playerIn.getLookAngle();
        Vec3 pos = playerIn.position();
        Vec3 mov = playerIn.getDeltaMovement();

        SoulArrow e = new SoulArrow(EntityInit.SOUL_ARROW.get(), levelIn);

        e.setPos(pos.add(0,1.5,0).add(aim.normalize().multiply(1.5f,1.5f,1.5f)));

        if(i<getCastingDurationSpellB()) {
            e.setDamage(this.getAttackDamage(playerIn, stackIn) * 2 * (i / getCastingDurationSpellB()));
            e.setMaxLifeTime(10 + 30*i/getCastingDurationSpellB());
            e.setDimensionScale((float) (0.12+0.03*i/getCastingDurationSpellB()));
            e.xPower = 0.1 * aim.x * (1 + (playerIn.getRandom().nextFloat() - 0.5) * 0.05) * (1 + 0.03 * i / getCastingDurationSpellB());
            e.yPower = 0.1 * aim.y * (1 + 0.1 * i / getCastingDurationSpellB());
            e.zPower = 0.1 * aim.z * (1 + (playerIn.getRandom().nextFloat() - 0.5) * 0.05) * (1 + 0.03 * i / getCastingDurationSpellB());
        }else{
            e.setDamage(this.getAttackDamage(playerIn, stackIn) * 2.5f);
            e.setMaxLifeTime(50);
            e.setDimensionScale(0.20f);
            e.xPower = 0.1 * aim.x * (1 + (playerIn.getRandom().nextFloat() - 0.5) * 0.05) * (1 + 0.06);
            e.yPower = 0.1 * aim.y * (1 + 0.12);
            e.zPower = 0.1 * aim.z * (1 + (playerIn.getRandom().nextFloat() - 0.5) * 0.05) * (1 + 0.06);
        }

        if(handIn == InteractionHand.MAIN_HAND) {stackIn.hurtAndBreak(2, playerIn, EquipmentSlot.MAINHAND);}
        if(handIn == InteractionHand.OFF_HAND) {stackIn.hurtAndBreak(2, playerIn, EquipmentSlot.OFFHAND);}

        levelIn.addFreshEntity(e);
        playerIn.getCooldowns().addCooldown(this, 15);
        playerIn.level().playSound((Player)null, playerIn, SoundEvents.RESPAWN_ANCHOR_CHARGE, SoundSource.PLAYERS, 0.6F, 1.0F);
    }

    public int getCastingDurationSpellB(){return 40;}

    @Override
    public InteractionResultHolder<ItemStack> use(Level levelIn, Player playerIn, InteractionHand handIn) {
        playerIn.startUsingItem(handIn);
        return InteractionResultHolder.pass(playerIn.getItemInHand(handIn));
    }

    @Override
    public boolean useOnRelease(ItemStack stackIn) {
        return super.useOnRelease(stackIn);
    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 72000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.BOW;
    }

    @Override
    public void onUseTick(Level levelIn, LivingEntity entityIn, ItemStack stackIn, int i) {
        super.onUseTick(levelIn, entityIn, stackIn, i);
    }

    @Override
    public void releaseUsing(ItemStack stackIn, Level levelIn, LivingEntity entityIn, int i) {
        super.releaseUsing(stackIn, levelIn, entityIn, i);
        entityIn.stopUsingItem();
        int ammoAmount = 1;
        if(this.hasAmmo((Player) entityIn,ammoAmount)||((Player)entityIn).isCreative()) {
            this.doSpellB((Player) entityIn, stackIn, ((Player) entityIn).getUsedItemHand(), i);
            if(!((Player)entityIn).isCreative()) {
                this.consumeAmmoApplyLuck((Player)entityIn, ammoAmount,this.getLuck((Player) entityIn, stackIn));
            }
        }else{entityIn.level().playSound((Player)null, entityIn, SoundEvents.BLAZE_HURT, SoundSource.PLAYERS, 0.6F, 0.4F);}
    }


    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        return true;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, TooltipContext p_41422_, List<Component> tooltip, TooltipFlag p_41424_) {
        super.appendHoverText(stack, p_41422_, tooltip, p_41424_);

        String spellA = "\u00A7bSoul Dart (1)";
        //tooltip.add(Component.literal(spellA));

        String spellB = "\u00A7bSoul Arrow (1)";
        //tooltip.add(Component.literal(spellB));

        tooltip.add(Component.translatable("translation.darkestsouls.spell.soul_dart").withStyle(ChatFormatting.AQUA));
        tooltip.add(Component.translatable("translation.darkestsouls.spell.soul_arrow").withStyle(ChatFormatting.AQUA));


    }
}
