package github.nitespring.darkestsouls.common.effects;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.Bonewheel;
import github.nitespring.darkestsouls.core.init.EffectInit;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;

public class BleedMobEffect extends MobEffect {


    public BleedMobEffect(MobEffectCategory p_19451_, int p_19452_) {

        super(p_19451_, p_19452_);

    }

    @Override
    public void applyEffectTick(LivingEntity living, int amount) {
        super.applyEffectTick(living, amount);
        if(living instanceof Skeleton || living instanceof Bonewheel){
            living.removeEffect(EffectInit.BLEED.get());
        }
        //int amount = living.getEffect(EffectInit.BLEED.get()).getAmplifier();
        if(living instanceof DarkestSoulsAbstractEntity){
            int res = ((DarkestSoulsAbstractEntity) living).getBloodResistance()-1;
            if(amount>=res){
                applyDamage(living, 12.0f+living.getMaxHealth()*0.05f);
            }
        }else if(living instanceof Player){
            if(amount>=5){
                applyDamage(living, 4.0f+living.getMaxHealth()*0.3f);
            }
        }else if(!(living instanceof Skeleton)){
            if(amount>=5){
                applyDamage(living, 12.0f+living.getMaxHealth()*0.05f);
            }
        }

    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int p_297908_, int p_301085_) {
        return true;
    }

    public void applyDamage(LivingEntity living, float dmg){
        System.out.println("Apply bleed to "+ living.getMobType().toString());
        living.hurt(living.level().damageSources().genericKill(), dmg);
        living.removeEffect(EffectInit.BLEED.get());
    }



}
