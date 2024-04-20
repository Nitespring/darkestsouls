package github.nitespring.darkestsouls.common.item.child.staves;

import github.nitespring.darkestsouls.common.entity.projectile.spell.CrystalRain;
import github.nitespring.darkestsouls.common.entity.projectile.spell.CrystalShardEntity;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.Random;

public class CrystalStaff extends Staff {

    private final int type;
    public CrystalStaff(float attackDamage, int durability, int type, int tier, Properties properties) {
        super(attackDamage, durability, tier, properties);
        this.type=type;
    }

    @Override
    public void doSpellA(Player playerIn, ItemStack stackIn, InteractionHand HandIn) {
        int ammoAmount = 1;
        if(!playerIn.getCooldowns().isOnCooldown(this)&&(this.hasAmmo(playerIn,ammoAmount)||playerIn.isCreative())) {
            Level levelIn = playerIn.level();
            Vec3 aim = playerIn.getLookAngle();
            Vec3 pos = playerIn.position();
            Vec3 mov = playerIn.getDeltaMovement();
            for(int i = 0; i<=8; i++) {
                CrystalShardEntity e = new CrystalShardEntity(EntityInit.CRYSTAL_SHARD.get(), levelIn);
                e.setDamage(this.getAttackDamage(playerIn, stackIn));
                //e.setDimensionScale(0.1f);
                e.setLifeTicks(10);
                e.setCrystalType(this.type);
                Random r = new Random();
                float rx=(r.nextFloat() - 0.5f);
                float ry=(r.nextFloat() - 0.5f);
                float rz=(r.nextFloat() - 0.5f);
                Vec3 aim1 = aim.add(rx* 1.5,ry* 0.75,rz* 1.5);
                e.setPos(pos.add(rx*0.5, ry*0.5+1.5, rz*0.5).add(aim.multiply(0.5f, 0.5f, 0.5f).add(aim1.multiply(0.5f, 0.5f, 0.5f))));
                e.xPower = 0.1 * (0.5f*aim.x+aim1.x);
                e.yPower = 0.1 * (0.5f*aim.y+aim1.y);
                e.zPower = 0.1 * (0.5f*aim.z+aim1.z);
                stackIn.hurtAndBreak(1, playerIn, (p_43276_) -> {
                    p_43276_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                });
                double d0 = aim1.horizontalDistance();
                e.setOwner(playerIn);
                levelIn.addFreshEntity(e);
                e.setYRot((float) (Mth.atan2(aim1.x, aim1.z) * (double) (180F / (float) Math.PI)));
                e.setXRot((float) (Mth.atan2(aim1.y, d0) * (double) (180F / (float) Math.PI)));
                e.hurtMarked=true;
            }
            for(int i = 0; i<=5; i++) {
                CrystalShardEntity e = new CrystalShardEntity(EntityInit.CRYSTAL_SHARD.get(), levelIn);
                e.setDamage(this.getAttackDamage(playerIn, stackIn));
                //e.setDimensionScale(0.1f);
                e.setLifeTicks(6);
                e.setCrystalType(this.type);
                Random r = new Random();
                float rx=(r.nextFloat() - 0.5f);
                float ry=(r.nextFloat() - 0.5f);
                float rz=(r.nextFloat() - 0.5f);
                Vec3 aim1 = aim.add(rx* 0.75,ry* 0.75,rz* 0.75);
                e.setPos(pos.add(rx*0.5, ry*0.5+1.5, rz*0.5).add(aim.multiply(1.0f, 1.0f, 1.0f).add(aim1.multiply(0.5f, 0.5f, 0.5f))));
                e.xPower = 0.05 * (0.5f*aim.x+aim1.x);
                e.yPower = 0.05 * (0.5f*aim.y+aim1.y);
                e.zPower = 0.05 * (0.5f*aim.z+aim1.z);
                stackIn.hurtAndBreak(1, playerIn, (p_43276_) -> {
                    p_43276_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                });
                double d0 = aim1.horizontalDistance();
                e.setOwner(playerIn);
                levelIn.addFreshEntity(e);
                e.setYRot((float) (Mth.atan2(aim1.x, aim1.z) * (double) (180F / (float) Math.PI)));
                e.setXRot((float) (Mth.atan2(aim1.y, d0) * (double) (180F / (float) Math.PI)));
                e.hurtMarked=true;
            }
            playerIn.getCooldowns().addCooldown(this, 12);
            playerIn.level().playSound((Player)null, playerIn, SoundEvents.GLASS_BREAK, SoundSource.PLAYERS, 1.0F, 1.2F);
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

        CrystalRain e = new CrystalRain(EntityInit.CRYSTAL_RAIN.get(), levelIn);

        e.setPos(pos.add(0,2.5,0).add(aim.normalize().multiply(1.5f,0,1.5f)));


        e.setDamage(this.getAttackDamage(playerIn, stackIn));
        e.setCrystalType(this.type);
        e.setOwner(playerIn);
        e.setStopLifeTime(40);
        e.setMaxLifeTime(100);
        e.xPower = 0.1 * aim.x;
        e.yPower = 0.1+0.1 * Math.max(aim.y, 0);
        e.zPower = 0.1 * aim.z;

        stackIn.hurtAndBreak(2, playerIn, (p_43276_) -> {
            p_43276_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });

        levelIn.addFreshEntity(e);
        playerIn.getCooldowns().addCooldown(this, 15);
        playerIn.level().playSound((Player)null, playerIn, SoundEvents.RESPAWN_ANCHOR_DEPLETE.get(), SoundSource.PLAYERS, 0.6F, 1.0F);
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
        int ammoAmount = 2;
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
    public void appendHoverText(ItemStack stack, Level p_41422_, List<Component> tooltip, TooltipFlag p_41424_) {
        super.appendHoverText(stack, p_41422_, tooltip, p_41424_);
        String colour = "§3";

        switch(this.type){
            case 1:
                colour = "§5";
                tooltip.add(Component.translatable("translation.darkestsouls.spell.crystal_burst").withStyle(ChatFormatting.DARK_PURPLE));
                tooltip.add(Component.translatable("translation.darkestsouls.spell.crystal_rain").withStyle(ChatFormatting.DARK_PURPLE));
                break;
            case 2:
                colour = "§9";
                tooltip.add(Component.translatable("translation.darkestsouls.spell.crystal_burst").withStyle(ChatFormatting.BLUE));
                tooltip.add(Component.translatable("translation.darkestsouls.spell.crystal_rain").withStyle(ChatFormatting.BLUE));
                break;
            default:
                colour = "§3";
                tooltip.add(Component.translatable("translation.darkestsouls.spell.crystal_burst").withStyle(ChatFormatting.DARK_AQUA));
                tooltip.add(Component.translatable("translation.darkestsouls.spell.crystal_rain").withStyle(ChatFormatting.DARK_AQUA));
                break;
        }

        String spellA = colour + "Crystal Burst (1)";

        String spellB = colour + "Crystal Rain (2)";



    }
}
