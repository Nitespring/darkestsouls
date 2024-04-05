package github.nitespring.darkestsouls.common.effect;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.Bonewheel;
import github.nitespring.darkestsouls.core.init.EffectInit;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

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
                living.invulnerableTime = 0;
                applyDamage(living, 12.0f+living.getMaxHealth()*0.05f);
                living.playSound(SoundEvents.PLAYER_SPLASH_HIGH_SPEED,1.0f,3.6f);
            }
        }else if(living instanceof Player p){
            if(amount>=8){
                living.invulnerableTime = 0;
                applyDamage(living, 2.0f+living.getMaxHealth()*0.3f);
                p.level().playSound((Player) p, p.getX(), p.getY(), p.getZ(), SoundEvents.PLAYER_SPLASH_HIGH_SPEED, p.getSoundSource(), 1.0f, 1.0f);

                //p.playSound(SoundEvents.PLAYER_SPLASH_HIGH_SPEED,1.0f,3.6f);
                ParticleOptions blood = new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Items.NETHER_WART_BLOCK));
                float width = living.getBbWidth() * 0.75f;
                float height = living.getBbHeight() * 0.75f;
                Vec3 pos = new Vec3(living.getX(), living.getEyeY(), living.getZ());
                Level world = living.level();
                RandomSource rng = living.getRandom();
                for (int i = 0; i < 24; ++i) {
                    Vec3 off = new Vec3(rng.nextDouble() * width - width / 2, rng.nextDouble() * height - height / 2,
                                rng.nextDouble() * width - width / 2);
                    if(world instanceof ServerLevel) {
                        ((ServerLevel) world).sendParticles( blood, pos.x+off.x, pos.y+off.y, pos.z+off.z, 5,  off.x, off.y + 0.05D, off.z, 0.05D + 5*0.003);
                    }
                }
            }
        }else if(!(living instanceof Skeleton)){
            if(amount>=5){
                living.invulnerableTime = 0;
                applyDamage(living, 12.0f+living.getMaxHealth()*0.05f);
                living.playSound(SoundEvents.PLAYER_SPLASH_HIGH_SPEED,1.0f,3.6f);
                    ParticleOptions blood = new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Items.NETHER_WART_BLOCK));
                    float width = living.getBbWidth() * 0.5f;
                    float height = living.getBbHeight() * 0.5f;
                    Vec3 pos = new Vec3(living.getX(), living.getEyeY(), living.getZ());
                    Level world = living.level();
                    RandomSource rng = living.getRandom();
                    for (int i = 0; i < 20; ++i) {
                        Vec3 off = new Vec3(rng.nextDouble() * width - width / 2, rng.nextDouble() * height - height / 2,
                                rng.nextDouble() * width - width / 2);
                        if(world instanceof ServerLevel) {
                            ((ServerLevel) world).sendParticles( blood, pos.x, pos.y, pos.z, 5,  off.x, off.y + 0.05D, off.z, 0.05D + 5*0.003);
                        }
                    }
            }
        }



    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int p_297908_, int p_301085_) {
        return true;
    }

    public void applyDamage(LivingEntity living, float dmg){
        //System.out.println("Apply bleed to "+ living.getMobType().toString());
        if(!(living instanceof Player p && p.isCreative())) {
            living.hurt(living.level().damageSources().genericKill(), dmg);
        }
        living.removeEffect(EffectInit.BLEED.get());

        ParticleOptions blood = new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Items.NETHER_WART_BLOCK));
        float width = living.getBbWidth() * 0.5f;
        float height = living.getBbHeight() * 0.5f;
        Vec3 pos = new Vec3(living.getX(), living.getEyeY(), living.getZ());
        Level world = living.level();

        RandomSource rng = living.getRandom();

        double size = width * height;

        for (int i = 0; i < 20+2*size; ++i) {

            Vec3 off = new Vec3(rng.nextDouble() * width - width / 2, rng.nextDouble() * height - height / 2,
                    rng.nextDouble() * width - width / 2);
            if(world instanceof ServerLevel) {
                ((ServerLevel) world).sendParticles( blood, pos.x+off.x, pos.y+off.y, pos.z+off.z, 5,  off.x, off.y + 0.05D, off.z, 0.05D + 5*0.003);

            }
        }

    }



}
