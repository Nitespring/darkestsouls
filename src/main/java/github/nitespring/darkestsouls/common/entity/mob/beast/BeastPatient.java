package github.nitespring.darkestsouls.common.entity.mob.beast;

import github.nitespring.darkestsouls.common.entity.mob.skeleton.SkeletonCurvedSwords;
import github.nitespring.darkestsouls.common.entity.util.DamageHitboxEntity;
import github.nitespring.darkestsouls.core.init.EffectInit;
import github.nitespring.darkestsouls.core.init.EntityInit;
import github.nitespring.darkestsouls.core.init.SoundInit;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreakDoorGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
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

public class BeastPatient extends BeastPatientEntity implements GeoEntity, IBuffableBeast{

    protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
    private static final EntityDimensions CRAWLING_BB = new EntityDimensions(0.9f, 0.8f, false);

    protected Vec3 aimVec;
    public BeastPatient(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
        this.xpReward=10;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {return this.factory;}

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<>(this, "main_controller", 2, this::predicate));
        data.add(new AnimationController<>(this, "stun_controller", 0, this::hitStunPredicate));
    }

    private <E extends GeoAnimatable> PlayState hitStunPredicate(AnimationState<E> event) { /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
        }*/

        if(hitStunTicks>0) {
            event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.beast_patient.hit"));
        }else {
            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.beast_patient.new"));
        }
        return PlayState.CONTINUE;
    }



    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) { /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
        }*/
        int animState = this.getAnimationState();
        int combatState = this.getCombatState();
        if(this.isDeadOrDying()) {
            event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.beast_patient.death"));
        }else {
            switch(animState) {
                case 1:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.beast_patient.stun"));
                    break;
                case 11:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.beast_patient.scream"));
                    break;
                case 21:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.beast_patient.atk_r1"));
                    break;
                case 22:
                    event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.beast_patient.atk_r2"));
                    break;
                case 23:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.beast_patient.atk_r3"));
                    break;
                case 24:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.beast_patient.atk_l1"));
                    break;
                case 25:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.beast_patient.atk_l2"));
                    break;
                case 26:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.beast_patient.atk_l3"));
                    break;
                case 27:
                    event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.beast_patient.atk_downswing1"));
                    break;
                case 28:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.beast_patient.atk_cross1"));
                    break;
                case 29:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.beast_patient.attack_jump1"));
                    break;
                default:
                    if(this.isInWater()) {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.beast_patient.swim"));
                    }else if(!this.onGround()) {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.beast_patient.fall"));
                    }else if(!(event.getLimbSwingAmount() > -0.06 && event.getLimbSwingAmount() < 0.06f)){
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.beast_patient.walk1"));
                    }else {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.beast_patient.idle"));
                    }
                    break;
            }
        }
        return PlayState.CONTINUE;
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new BreakDoorGoal(this, (d) -> {return d == Difficulty.EASY || d == Difficulty.NORMAL || d == Difficulty.HARD;}));
        this.goalSelector.addGoal(1, new BeastPatient.AttackGoal(this));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this,0.2f,1));
        super.registerGoals();

    }
    @Override
    public int getBeastPatientType() {return 0;}
    @Override
    public int getMaxPoise() {return 24;}
    @Override
    public int getBloodResistance() {return 8;}

    @Override
    public EntityDimensions getDimensions(Pose p_21047_) {

        if(this.getAnimationState()==1||this.isInWater()) {
            return CRAWLING_BB;
        }else {
            return this.getType().getDimensions();
        }
    }

    @Override
    public void activateBuff() {
        this.setEntityState(1);
    }

    @Override
    public void tick() {
        if(this.getAnimationState()!=0&&!this.isDeadOrDying()) {
            this.playAnimation();
        }
        if(this.getEntityState()!=0&&!this.hasEffect(EffectInit.FAKE_POISON.get())){this.addEffect(new MobEffectInstance(EffectInit.FAKE_POISON.get(),40));}
        if(this.tickCount%5==0){this.refreshDimensions();}
        super.tick();
    }

    protected void doAttack(float damageModifier, double range){
        this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
        if(this.getEntityState()==0) {
            DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                    this.position().add((1.0f+range) * this.getLookAngle().x,
                            0.25,
                            (1.0f+range) * this.getLookAngle().z),
                    (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE)+damageModifier, 5);
            h.setOwner(this);
            h.setTarget(this.getTarget());
            h.setHitboxType(0);
            this.level().addFreshEntity(h);
        }else{
            DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                    this.position().add((1.0f+range) * this.getLookAngle().x,
                            0.25,
                            (1.0f+range) * this.getLookAngle().z),
                    (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE)+damageModifier+1.0f, 5);
            h.setOwner(this);
            h.setTarget(this.getTarget());
            h.setHitboxType(1);
            this.level().addFreshEntity(h);
        }
    }

    protected void playAnimation() {
        this.increaseAnimationTick(1);
        boolean flag = this.getTarget()!=null && this.distanceTo(this.getTarget())<=4;

        switch (this.getAnimationState()) {
            case 1:
                this.getNavigation().stop();
                this.getNavigation().stop();
                if(getAnimationTick()==1){
                    this.playSound(SoundEvents.BLAZE_HURT);
                }
                if(getAnimationTick()>=30) {
                    this.getNavigation().stop();
                    setAnimationTick(0);
                    this.resetPoiseHealth();
                    setAnimationState(0);
                }
                break;
            //Attack
            case 21:
                this.getNavigation().stop();
                if(getAnimationTick()==4) {
                    this.playAttackSound();
                    this.doAttack(0.0f, -0.2f);
                }
                if(getAnimationTick()>=7&&flag) {
                    setAnimationTick(0);
                    this.setAnimationState(25);
                }
                if(getAnimationTick()>=12) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 22:
                if(getAnimationTick()>=2) {this.getNavigation().stop();}
                else{this.moveToTarget();}
                if(getAnimationTick()==4) {
                    this.playAttackSound();
                    this.doAttack(0.0f, -0.2f);
                }
                if(getAnimationTick()>=12) {
                    this.getNavigation().stop();
                    int r = this.getRandom().nextInt(2048);
                    if(r<=400)      {setAnimationTick(0);this.setAnimationState(25);}
                    else if(r<=800) {setAnimationTick(0);this.setAnimationState(26);}
                    else if(r<=1200){setAnimationTick(0);this.setAnimationState(27);}
                    else if(r<=1600){setAnimationTick(0);this.setAnimationState(28);}
                    else if(r<=2000){setAnimationTick(0);this.setAnimationState(29);}
                }
                if(getAnimationTick()>=16) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 23:
                if(getAnimationTick()>=2) {this.getNavigation().stop();}
                else{this.moveToTarget();}

                if(getAnimationTick()==2) {
                    this.setDeltaMovement(0,0.3,0);
                    if (this.getTarget() != null) {
                        this.aimVec = this.getTarget().position().add(this.position().scale(-1.0));
                    } else {
                        this.aimVec = this.getLookAngle();
                    }
                }
                if(getAnimationTick()==3){
                    this.playAttackSound();
                    if(this.aimVec!=null) {
                        this.setDeltaMovement(this.aimVec.normalize().add(0,0.01f,0).scale(0.35));
                    }else {
                        this.setDeltaMovement(this.getLookAngle().normalize().add(0,0.01f,0).scale(0.35));
                    }

                }
                if(getAnimationTick()==7) {
                    this.doAttack(1.0f, 0.0f);
                }
                if(getAnimationTick()>=16) {
                    this.getNavigation().stop();
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 24:
                this.getNavigation().stop();

                if(getAnimationTick()==4) {
                    this.playAttackSound();
                    this.doAttack(0.0f, -0.2f);
                }
                if(getAnimationTick()>=7&&flag) {
                    setAnimationTick(0);
                    this.setAnimationState(22);
                }
                if(getAnimationTick()>=12) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 25:
                if(getAnimationTick()>=2) {this.getNavigation().stop();}
                else{this.moveToTarget();}
                if(getAnimationTick()==4) {
                    this.playAttackSound();
                    this.doAttack(0.0f, -0.2f);
                }
                if(getAnimationTick()>=12) {
                this.getNavigation().stop();
                int r = this.getRandom().nextInt(2048);
                if(r<=400)      {setAnimationTick(0);this.setAnimationState(22);}
                else if(r<=800) {setAnimationTick(0);this.setAnimationState(23);}
                else if(r<=1200){setAnimationTick(0);this.setAnimationState(27);}
                else if(r<=1600){setAnimationTick(0);this.setAnimationState(28);}
                else if(r<=2000){setAnimationTick(0);this.setAnimationState(29);}
                }
                if(getAnimationTick()>=16) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 26:
                if(getAnimationTick()>=2) {this.getNavigation().stop();}
                else{this.moveToTarget();}

                if(getAnimationTick()==2) {
                    this.setDeltaMovement(0,0.3,0);
                    if (this.getTarget() != null) {
                        this.aimVec = this.getTarget().position().add(this.position().scale(-1.0));
                    } else {
                        this.aimVec = this.getLookAngle();
                    }
                }
                if(getAnimationTick()==3){
                    this.playAttackSound();
                    if(this.aimVec!=null) {
                        this.setDeltaMovement(this.aimVec.normalize().add(0,0.01f,0).scale(0.35));
                    }else {
                        this.setDeltaMovement(this.getLookAngle().normalize().add(0,0.01f,0).scale(0.35));
                    }

                }
                if(getAnimationTick()==7) {
                    this.doAttack(1.0f, 0.0f);
                }
                if(getAnimationTick()>=16) {
                    this.getNavigation().stop();
    setAnimationTick(0);

                    setAnimationState(0);
                }
                break;
            case 27:
                this.getNavigation().stop();

                if(getAnimationTick()==4) {
                    this.setDeltaMovement(0,0.2,0);
                    if (this.getTarget() != null) {
                        this.aimVec = this.getTarget().position().add(this.position().scale(-1.0));
                    } else {
                        this.aimVec = this.getLookAngle();
                    }
                }
                if(getAnimationTick()==5){
                    this.playAttackSound();
                    if(this.aimVec!=null) {
                        this.setDeltaMovement(this.aimVec.normalize().add(0,0.005f,0).scale(0.25));
                    }else {
                        this.setDeltaMovement(this.getLookAngle().normalize().add(0,0.005f,0).scale(0.25));
                    }

                }
                if(getAnimationTick()==8) {
                    this.doAttack(4.0f, 0.0f);
                }
                if(getAnimationTick()>=15) {
                    this.getNavigation().stop();
                    setAnimationTick(0);;

                    setAnimationState(0);
                }
                break;
            case 28:
                if(getAnimationTick()>=1) {this.getNavigation().stop();}
                else{this.moveToTarget();}

                if(getAnimationTick()==2) {
                    this.setDeltaMovement(0,0.5,0);
                    if (this.getTarget() != null) {
                        this.aimVec = this.getTarget().position().add(this.position().scale(-1.0));
                    } else {
                        this.aimVec = this.getLookAngle();
                    }
                }
                if(getAnimationTick()==3){
                    this.playAttackSound();
                    if(this.aimVec!=null) {
                        this.setDeltaMovement(this.aimVec.normalize().add(0,0.01f,0).scale(0.35));
                    }else {
                        this.setDeltaMovement(this.getLookAngle().normalize().add(0,0.01f,0).scale(0.35));
                    }

                }
                if(getAnimationTick()==5) {
                    this.doAttack(2.0f, 0.0f);
                }
                if(getAnimationTick()>=12) {
                    this.getNavigation().stop();
                    setAnimationTick(0);

                    setAnimationState(0);
                }
                break;
            case 29:
                if(getAnimationTick()>=1) {this.getNavigation().stop();}
                else{this.moveToTarget();}

                if(getAnimationTick()==7) {
                    this.setDeltaMovement(0,1,0);
                    if (this.getTarget() != null) {
                        this.aimVec = this.getTarget().position().add(this.position().scale(-1.0));
                    } else {
                        this.aimVec = this.getLookAngle();
                    }
                }
                if(getAnimationTick()==8){
                    this.playAttackSound();
                    if(this.aimVec!=null) {
                        this.setDeltaMovement(this.aimVec.normalize().add(0,0.05f,0).scale(0.5));
                    }else {
                        this.setDeltaMovement(this.getLookAngle().normalize().add(0,0.05f,0).scale(0.5));
                    }

                }
                if(getAnimationTick()==17) {
                    this.doAttack(4.0f, 0.0f);
                }
                if(getAnimationTick()>=22) {
                    this.getNavigation().stop();
                    setAnimationTick(0);

                    setAnimationState(0);
                }
                break;
        }
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundInit.BEAST_PATIENT_IDLE.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_21239_) {
        return SoundInit.BEAST_PATIENT_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundInit.BEAST_PATIENT_ATTACK.get();
    }

    public void playAttackSound(){
        this.playSound(SoundInit.BEAST_PATIENT_ATTACK.get(),0.2f,1.4f);
    }

    @Override
    public float getVoicePitch() {
        return 1.4f;
    }
    @Override
    protected float getSoundVolume() {
        return 0.2f;
    }

    @Override
    protected void playHurtSound(DamageSource p_21493_) {
        super.playHurtSound(p_21493_);
    }

    @Override
    public void playAmbientSound() {
        super.playAmbientSound();
    }

    public void moveToTarget(){
        boolean flag = this.getTarget()!=null;
        if(flag) {
            this.getLookControl().setLookAt(this.getTarget(), 10.0F, 10.0F);
            Path path = this.getNavigation().createPath(this.getTarget(), 0);
            this.getNavigation().moveTo(path, 1.1f);
        }

    }

    public class AttackGoal extends Goal {


        private final double speedModifier = 1.4f;
        private final boolean followingTargetEvenIfNotSeen = true;
        protected final BeastPatient mob;
        private Path path;
        private double pathedTargetX;
        private double pathedTargetY;
        private double pathedTargetZ;
        private int ticksUntilNextPathRecalculation;
        private int ticksUntilNextAttack;
        private long lastCanUseCheck;
        private int failedPathFindingPenalty = 0;
        private boolean canPenalize = false;




        public AttackGoal(BeastPatient entityIn) {
            this.mob = entityIn;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
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
            this.ticksUntilNextAttack = 8;

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
            //this.checkForPreciseAttack();


            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);

        }


        @SuppressWarnings("unused")
        private void checkForPreciseAttack() {
            LivingEntity target = this.mob.getTarget();
            double distance = this.mob.distanceToSqr(target.getX(), target.getY(), target.getZ());
            double reach = this.getAttackReachSqr(target);
            if (this.ticksUntilNextAttack <= 0 && distance <= reach) {

                this.mob.setAnimationState(23);
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
                if(r<=400)      {this.mob.setAnimationState(21);}
                else if(r<=600) {this.mob.setAnimationState(24);}
                else if(r<=1000){this.mob.setAnimationState(23);}
                else if(r<=1200){this.mob.setAnimationState(26);}
                else if(r<=1300){this.mob.setAnimationState(27);}
                else if(r<=1600){this.mob.setAnimationState(28);}
                else if(r<=1800){this.mob.setAnimationState(29);}
            }

            if (distance <= 2*reach && this.ticksUntilNextAttack <= 0) {
                int r = this.mob.getRandom().nextInt(2048);
                if (r <= 40) {
                    this.mob.setAnimationState(29);
                }
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
