package github.nitespring.darkestsouls.common.entity.mob.skeleton;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.common.entity.util.DamageHitboxEntity;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.EnumSet;

public class Bonewheel extends DarkestSoulsAbstractEntity implements GeoEntity {

    protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
    protected int animationTick = 0;

    protected Vec3 aimVec;

public Bonewheel(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
    super(p_21683_, p_21684_);
    this.xpReward=12;
}

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    @Override
    public boolean isBoss() {return false;}
    @Override
    public int getDSDefaultTeam() {return 0;}
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {return this.factory;}

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<>(this, "main_controller", 4, this::predicate));
        data.add(new AnimationController<>(this, "rotation_controller", 0, this::rotationPredicate));
        data.add(new AnimationController<>(this, "stun_controller", 0, this::hitStunPredicate));
    }

    @Override
    public ParticleOptions getBloodParticles() {
        return new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Items.BONE_MEAL));
    }

    private <E extends GeoAnimatable> PlayState hitStunPredicate(AnimationState<E> event) {

        if(hitStunTicks>0) {
            event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.bonewheel.hit"));
        }else {
            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.bonewheel.new"));
        }
        return PlayState.CONTINUE;
    }

    private <E extends GeoAnimatable> PlayState rotationPredicate(AnimationState<E> event) {
        int animState = this.getAnimationState();
        if(this.isDeadOrDying()) {
            event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.bonewheel.new"));
        }else{
            switch(animState) {
                case 23:
                    event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.bonewheel.attack3_rotation"));
                    break;
                case 24:
                    event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.bonewheel.roll_rotation"));
                    break;
                default:

                    event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.bonewheel.new"));

                    break;
            }

        }

        return PlayState.CONTINUE;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_21239_) {
        return SoundEvents.SKELETON_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SKELETON_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SKELETON_AMBIENT;
    }

    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
        int animState = this.getAnimationState();
        int combatState = this.getCombatState();
        if(this.isDeadOrDying()) {
            event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.bonewheel.death"));
        }else {
            switch(animState) {
                case 1:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.bonewheel.stun"));
                    break;
                case 21:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.bonewheel.attack1"));
                    break;
                case 22:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.bonewheel.attack2"));
                    break;
                case 23:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.bonewheel.attack3"));
                    break;
                case 24:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.bonewheel.roll"));
                    break;
                case 25:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.bonewheel.roll_end"));
                    break;
                default:
                            if(!(event.getLimbSwingAmount() > -0.06 && event.getLimbSwingAmount() < 0.06f)){
                                event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.bonewheel.walk"));
                            }else {
                                event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.bonewheel.idle"));
                            }
                    break;
            }
        }
        return PlayState.CONTINUE;
    }
    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(1, new Bonewheel.AttackGoal(this));
        super.registerGoals();

    }

    @Override
    public boolean canDrownInFluidType(FluidType type) {return false;}

    @Override
    public int getMaxPoise() {return 20;}

    @Override
    public int getBloodResistance() {return 999;}

    @Override
    public void tick() {
        if(this.getAnimationState()!=0&&!this.isDeadOrDying()) {
            this.playAnimation();
        }
        super.tick();
    }

    protected void playAnimation() {
        animationTick++;
        this.getNavigation().stop();
        switch(this.getAnimationState()) {
            case 1:
                if(animationTick>=30) {
                    this.getNavigation().stop();
                    animationTick=0;
                    this.resetPoiseHealth();
                    setAnimationState(0);
                }
                break;
            //Attack
            case 21:
                if(animationTick==5) {
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.5f)*this.getLookAngle().x,
                                    0.25,
                                    (1.5f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE), 5);
                    h.setTarget(this.getTarget());
                    h.setOwner(this);
                    this.level().addFreshEntity(h);


                }
                if(animationTick>=10) {
                    animationTick=0;
                    setAnimationState(0);
                }
                break;
            case 22:
                if(animationTick==8) {
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.5f)*this.getLookAngle().x,
                                                0.25,
                                                (1.5f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE), 5);
                    h.setTarget(this.getTarget());
                    h.setOwner(this);
                    this.level().addFreshEntity(h);


                }
                if(animationTick>=20) {
                    animationTick=0;
                    setAnimationState(0);
                }
                break;
            case 23:
                if(animationTick==10) {


                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.0f)*this.getLookAngle().x,
                                                0.25,
                                                (1.0f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE), 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    this.level().addFreshEntity(h);
                }
                if(animationTick>=10) {
                    if(this.getTarget()!=null) {
                        this.aimVec= this.getTarget().position().add(this.position().scale(-1.0));
                    }else{
                        this.aimVec=this.getLookAngle();
                    }
                    animationTick=0;
                    setAnimationState(24);
                }
                break;
            case 24:
                if(this.aimVec!=null) {
                    this.setDeltaMovement(this.aimVec.normalize().scale(0.4));
                }else {
                    this.setDeltaMovement(this.getLookAngle().normalize().scale(0.4));
                }
                if(this.getBlockStateOn().isAir()){
                    this.setDeltaMovement(this.getDeltaMovement().add(0,-10f,0));
                }

                if(animationTick%5==0) {
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.0f)*this.getLookAngle().x,
                                                0.25,
                                                (1.0f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE)*0.6f, 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    this.level().addFreshEntity(h);
                }

                if(animationTick>=50) {
                    animationTick=0;
                    setAnimationState(25);
                }
                break;
            case 25:
                if(animationTick==5) {
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.0f)*this.getLookAngle().x,
                                    0.25,
                                    (1.0f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE), 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    this.level().addFreshEntity(h);
                }
                if(animationTick>=12) {
                    animationTick=0;
                    setAnimationState(0);
                }
                break;
        }
    }

    public class AttackGoal extends Goal {


        private final double speedModifier = 1.4f;
        private final boolean followingTargetEvenIfNotSeen = true;
        protected final Bonewheel mob;
        private Path path;
        private double pathedTargetX;
        private double pathedTargetY;
        private double pathedTargetZ;
        private int ticksUntilNextPathRecalculation;
        private int ticksUntilNextAttack;
        private long lastCanUseCheck;
        private int failedPathFindingPenalty = 0;
        private boolean canPenalize = false;




        public AttackGoal(Bonewheel entityIn) {
            this.mob = entityIn;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }


        @Override
        public boolean canUse() {
            if(this.mob.getAnimationState()==0) {
                long i = this.mob.level().getGameTime();
                if (i - this.lastCanUseCheck < 20L) {
                    return false;
                } else {
                    this.lastCanUseCheck = i;
                    LivingEntity livingentity = this.mob.getTarget();
                    if (livingentity == null) {
                        return false;
                    } else if (!livingentity.isAlive()) {
                        return false;
                    } else {
                        if (canPenalize) {
                            if (--this.ticksUntilNextPathRecalculation <= 0) {
                                this.path = this.mob.getNavigation().createPath(livingentity, 0);
                                this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
                                return this.path != null;
                            } else {
                                return true;
                            }
                        }
                        this.path = this.mob.getNavigation().createPath(livingentity, 0);
                        if (this.path != null) {
                            return true;
                        } else {
                            return this.getAttackReachSqr(livingentity) >= this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
                        }
                    }
                }
            }else{
                return false;
            }
        }


        @Override
        public boolean canContinueToUse() {
            LivingEntity livingentity = this.mob.getTarget();
            if(this.mob.getAnimationState()!=0) {
                return false;
            }else if (livingentity == null) {
                return false;
            } else if (!livingentity.isAlive()) {
                return false;
            } else if (!this.followingTargetEvenIfNotSeen) {
                return !this.mob.getNavigation().isDone();
            } else if (!this.mob.isWithinRestriction(livingentity.blockPosition())) {
                return false;
            } else {
                return !(livingentity instanceof Player) || !livingentity.isSpectator() && !((Player)livingentity).isCreative();
            }

        }
        @Override
        public void start() {
            this.mob.getNavigation().moveTo(this.path, this.speedModifier);
            this.mob.setAggressive(true);
            this.ticksUntilNextPathRecalculation = 0;
            this.ticksUntilNextAttack = 5;

            this.mob.setAnimationState(0);
        }
        @Override
        public void stop() {
            LivingEntity livingentity = this.mob.getTarget();
            if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingentity)) {
                this.mob.setTarget((LivingEntity)null);
            }

            this.mob.getNavigation().stop();
        }




        @Override
        public void tick() {
            LivingEntity target = this.mob.getTarget();
            double distance = this.mob.distanceToSqr(target.getX(), target.getY(), target.getZ());
            double reach = this.getAttackReachSqr(target);

                this.doMovement(target, reach);
                this.checkForAttack(distance, reach);


            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);

        }


        @SuppressWarnings("unused")
        private void checkForPreciseAttack() {
            if (this.ticksUntilNextAttack <= 0) {

                this.mob.setAnimationState(42);
            }

        }


        @SuppressWarnings("unused")
        protected void doMovement(LivingEntity livingentity, Double d0) {
            this.mob.getLookControl().setLookAt(this.mob.getTarget(), 30.0F, 30.0F);
            this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
            if ((this.followingTargetEvenIfNotSeen || this.mob.getSensing().hasLineOfSight(livingentity)) && this.ticksUntilNextPathRecalculation <= 0 && (this.pathedTargetX == 0.0D && this.pathedTargetY == 0.0D && this.pathedTargetZ == 0.0D || livingentity.distanceToSqr(this.pathedTargetX, this.pathedTargetY, this.pathedTargetZ) >= 1.0D || this.mob.getRandom().nextFloat() < 0.05F)) {
                this.pathedTargetX = livingentity.getX();
                this.pathedTargetY = livingentity.getY();
                this.pathedTargetZ = livingentity.getZ();
                this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
                if (this.canPenalize) {
                    this.ticksUntilNextPathRecalculation += failedPathFindingPenalty;
                    if (this.mob.getNavigation().getPath() != null) {
                        net.minecraft.world.level.pathfinder.Node finalPathPoint = this.mob.getNavigation().getPath().getEndNode();
                        if (finalPathPoint != null && livingentity.distanceToSqr(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
                            failedPathFindingPenalty = 0;
                        else
                            failedPathFindingPenalty += 10;
                    } else {
                        failedPathFindingPenalty += 10;
                    }
                }
                if (d0 > 1024.0D) {
                    this.ticksUntilNextPathRecalculation += 10;
                } else if (d0 > 256.0D) {
                    this.ticksUntilNextPathRecalculation += 5;
                }

                if (!this.mob.getNavigation().moveTo(livingentity, this.speedModifier)) {
                    this.ticksUntilNextPathRecalculation += 15;
                }
            }

        }







        protected void checkForAttack(double distance, double reach){
            if (distance <= reach && this.ticksUntilNextAttack <= 0) {
                int r = this.mob.getRandom().nextInt(2048);
                if(r<=900) {

                    this.mob.setAnimationState(21);

                }else if(r<=1800){

                    this.mob.setAnimationState(22);

                }else{

                    this.mob.setAnimationState(23);
                }
            }
                    int r = this.mob.getRandom().nextInt(2048);
                    if (r <= 40) {

                        this.mob.setAnimationState(23);

                    }

        }



        protected void resetAttackCooldown() {
            this.ticksUntilNextAttack = 20;
        }


        protected double getAttackReachSqr(LivingEntity p_179512_1_) {
            return (double)(this.mob.getBbWidth() * 8.0F * this.mob.getBbWidth());
        }

    }

}
