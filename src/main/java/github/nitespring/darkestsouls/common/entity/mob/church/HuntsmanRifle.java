package github.nitespring.darkestsouls.common.entity.mob.church;

import github.nitespring.darkestsouls.common.entity.projectile.throwable.FirebombEntity;
import github.nitespring.darkestsouls.common.entity.projectile.weapon.Bullet;
import github.nitespring.darkestsouls.common.entity.util.DamageHitboxEntity;
import github.nitespring.darkestsouls.core.init.EntityInit;
import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.EnumSet;
import java.util.Random;

public class HuntsmanRifle extends Huntsman implements GeoEntity {
    protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
    public Vec3 aimVec;
    private static final EntityDimensions CRAWLING_BB = new EntityDimensions(0.9f, 0.8f, 0.6f, EntityAttachments.createDefault(0.9f, 0.8f),false);
    public HuntsmanRifle(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
        this.xpReward=10;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {return this.factory;}

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<>(this, "main_controller", 4, this::predicate));
        data.add(new AnimationController<>(this, "cape_controller", 0, this::clothPredicate));
        data.add(new AnimationController<>(this, "stun_controller", 0, this::hitStunPredicate));
    }
    private <E extends GeoAnimatable> PlayState clothPredicate(AnimationState<E> event) {

        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.huntsman.cloth"));

        return PlayState.CONTINUE;
    }


    private <E extends GeoAnimatable> PlayState hitStunPredicate(AnimationState<E> event) { /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
        }*/

        if(hitStunTicks>0) {
            event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.huntsman.hit"));
        }else {
            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.huntsman.new"));
        }
        return PlayState.CONTINUE;
    }

    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) { /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
        }*/
        int animState = this.getAnimationState();
        int combatState = this.getCombatState();
        if(this.isDeadOrDying()) {
            event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.huntsman.death"));
        }else {
            switch(animState) {
                case 1:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.huntsman.stun"));
                    break;
                case 31:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.huntsman.rifle.shoot"));
                    break;
                default:
                    if(this.isInWater()) {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.huntsman.swim"));
                    }else if(this.onClimbable()) {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.huntsman.climb"));
                    }else if(!this.onGround()) {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.huntsman.fall"));

                    }else if(!(event.getLimbSwingAmount() > -0.06 && event.getLimbSwingAmount() < 0.06f)){
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.huntsman.rifle.walk"));
                    }else {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.huntsman.rifle.idle"));
                    }
                    break;
            }
        }
        return PlayState.CONTINUE;
    }

    @Override
    public int getDefaultHatType() {
        return 1;
    }
    @Override
    public int getDefaultRobeType() {
        return 0;
    }

    @Override
    public int getDefaultHairType() {return 0;}
    @Override
    public int getDefaultShirtType() {return 1;}

    @Override
    public ItemStack getRightHandItem() {
        return ItemInit.MUSKET.get().getDefaultInstance();
    }


    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, @Nullable SpawnGroupData p_21437_) {

        return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_);
    }
    @Override
    public void populateClothing(){
        Random rn = new Random();
        int r = rn.nextInt(24) + 1;
        switch(rn.nextInt(24) + 1) {
            case 1,2,3:
                this.setRobeType(2);
                break;
            case 4,5,6:
                this.setRobeType(3);
                break;
            case 7,8,9:
                this.setRobeType(4);
                break;
            case 10,11,12:
                this.setRobeType(5);
                break;
            case 13:
                this.setRobeType(6);
                break;
            case 14:
                this.setRobeType(7);
                break;
            case 15,16:
                this.setRobeType(8);
                break;
            case 17:
                this.setRobeType(1);
                break;
            default:
                this.setRobeType(getDefaultRobeType());
                break;
        }
        switch(rn.nextInt(24) + 1) {
            case 1,2:
                this.setHatType(0);
                break;
            case 3,4,5,6,7,8:
                this.setHatType(2);
                break;
            case 9,10,11:
                this.setHatType(3);
                break;
            default:
                this.setHatType(getDefaultHatType());
                break;
        }
        switch(rn.nextInt(24) + 1) {
            case 1,2,3:
                this.setHairType(1);
                break;
            case 4,5,6,7,8:
                this.setHairType(2);
                break;
            case 9,10,11,12,13,14:
                this.setHairType(3);
                break;
            case 15:
                this.setHairType(4);
                break;
            case 16:
                this.setHairType(5);
                break;
            default:
                this.setHairType(getDefaultHairType());
                break;
        }
        switch(rn.nextInt(24) + 1) {
            case 1,2:
                this.setShirtType(0);
                break;
            case 3,4,5,6:
                this.setShirtType(2);
                break;
            case 7,8,9,10:
                this.setShirtType(3);
                break;
            case 11,12,13,14:
                this.setShirtType(4);
                break;
            case 15,16,17,18:
                this.setShirtType(5);
                break;
            default:
                this.setHairType(getDefaultShirtType());
                break;
        }
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new BreakDoorGoal(this, (p_34082_) -> {
            return p_34082_ == Difficulty.NORMAL || p_34082_ == Difficulty.HARD;
        }));
        this.goalSelector.addGoal(1, new OpenDoorGoal(this, true));
        this.goalSelector.addGoal(3, new MoveThroughVillageGoal(this, 1.0D, false, 4, ()->true));

        this.goalSelector.addGoal(2, new HuntsmanRifle.AttackGoal(this));

        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this,0.2f,1));
        super.registerGoals();

    }

    @Override
    public EntityDimensions getDefaultDimensions(Pose p_21047_) {

        if((this.isInWater()&&this.getAnimationState()==0)) {
            return CRAWLING_BB;
        }else {
            return this.getType().getDimensions();
        }
    }
    @Override
    public void tick() {
        if(this.onClimbable()&&this.getAnimationState()==0){
            if(this.getTarget()!=null&&this.getTarget().position().y<this.position().y){
                self().setDeltaMovement(self().getDeltaMovement().add(0.0D, (double) -0.2F, 0.0D));
            }else {
                self().setDeltaMovement(self().getDeltaMovement().add(0.0D, (double) 0.08F, 0.0D));
            }
        }
        if(this.tickCount%5==0){this.refreshDimensions();}

        if(this.getAnimationState()!=0&&!this.isDeadOrDying()) {
            this.playAnimation();
        }
        super.tick();
    }

    protected void playAnimation() {
        this.increaseAnimationTick(1);
        Level levelIn = this.level();
        Vec3 pos = this.position();
        boolean flag = this.getTarget() != null && this.distanceTo(this.getTarget()) <= 5;
        switch (this.getAnimationState()) {
            case 1:
                this.getNavigation().stop();
                if (getAnimationTick() >= 85) {
                    this.getNavigation().stop();
                    setAnimationTick(0);
                    this.resetPoiseHealth();
                    setAnimationState(0);
                }
                break;
            //Attack
            case 31:
                if(getAnimationTick()<=13){
                    if(this.getTarget()!=null) {
                        this.lookAt(this.getTarget(), 30.0f, 30.0f);
                        Path path = this.getNavigation().createPath(this.getTarget(), 0);
                        this.getNavigation().moveTo(path, 0);
                    }
                }else{
                    this.getNavigation().stop();
                }
                if (getAnimationTick() == 15) {
                    if (this.getTarget() == null) {
                        aimVec = this.getLookAngle().normalize();
                    } else {
                        aimVec = this.getTarget().position().add(0,0.8f,0).add(pos.scale(-1)).normalize();
                    }
                }
                if (getAnimationTick() == 20) {
                    if (aimVec == null) {aimVec = this.getLookAngle().normalize();}
                    //this.playSound(this.getAttackSound(), 0.2f,1.0f);
                    this.playSound(SoundEvents.GENERIC_EXPLODE.value());
                    float x = (float) (pos.x + 0.6 * aimVec.x);
                    float y = (float) (pos.y + 1.4 + 0.6 * aimVec.y);
                    float z = (float) (pos.z + 0.6 * aimVec.z);
                    Bullet entity = new Bullet(EntityInit.BULLET.get(), levelIn);
                    entity.setPos(x,y,z);
                    float flyingPower = 0.25f;
                    entity.setDeltaMovement(aimVec.scale(flyingPower));
                    entity.accelerationPower=flyingPower;
                    entity.setOwner(this);
                    entity.setAttackDamage((float) this.getAttributeValue(Attributes.ATTACK_DAMAGE));
                    entity.setSize(0.6f);
                    entity.setPierce(1);
                    entity.setPoiseDamage(2);
                    levelIn.addFreshEntity(entity);
                }
                if (getAnimationTick() == 65) {
                    this.playSound(SoundEvents.CHAIN_PLACE);
                }
                if (getAnimationTick() >= 80) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
        }
    }
    public void moveToTarget(float speed){
        boolean flag = this.getTarget()!=null;
        if(flag) {
            this.getLookControl().setLookAt(this.getTarget(), 10.0F, 10.0F);
            Path path = this.getNavigation().createPath(this.getTarget(), 0);
            this.getNavigation().moveTo(path, speed);
        }

    }
    public class AttackGoal extends Goal {


        private final double walkingSpeedModifier = 1.0f;
        private final double runningSpeedModifier = 1.8f;
        private final boolean followingTargetEvenIfNotSeen = true;
        protected final Huntsman mob;
        private Path path;
        private double pathedTargetX;
        private double pathedTargetY;
        private double pathedTargetZ;
        private int ticksUntilNextPathRecalculation;
        private int ticksUntilNextAttack;
        private int ticksUntilNextRangedAttack;
        private long lastCanUseCheck;
        private int failedPathFindingPenalty = 0;
        private boolean canPenalize = false;

        //private int lastCanUpdateStateCheck;


        public AttackGoal(Huntsman entityIn) {
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
            this.mob.getNavigation().moveTo(this.path, this.getSpeedModifier());
            this.mob.setAggressive(true);
            this.ticksUntilNextPathRecalculation = 0;
            this.ticksUntilNextAttack = 8 + mob.getRandom().nextInt(60);
            //this.lastCanUpdateStateCheck = getStateUpdateInitialTimer();
            this.mob.setAnimationState(0);
            if(mob.getCombatState()==1){
                int r = this.mob.getRandom().nextInt(2048);
                if(r<=240) {
                    this.mob.setCombatState(0);
                    this.stop();
                }
            }else{
                int r = this.mob.getRandom().nextInt(2048);
                if(r<=840) {
                    this.mob.setCombatState(1);
                    this.stop();
                }
            }
        }
        @Override
        public void stop() {
            LivingEntity livingentity = this.mob.getTarget();
            if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingentity)) {
                this.mob.setTarget((LivingEntity)null);
            }

            this.mob.getNavigation().stop();
        }

        public double getSpeedModifier() {
            return walkingSpeedModifier;
        }


        @Override
        public void tick() {
            LivingEntity target = this.mob.getTarget();
            //double distanceSQR = this.mob.distanceToSqr(target.getX(), target.getY(), target.getZ());
            double reachSQR = this.getAttackReachSqr(target);
            double distance = this.mob.distanceTo(target);
            double reach = this.getAttackReach(target);

            this.doMovement(target, reachSQR);
            this.checkForAttack(distance, reach);
            //this.checkForPreciseAttack();

            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);

        }


        @SuppressWarnings("unused")
        private void checkForPreciseAttack() {
            LivingEntity target = this.mob.getTarget();
            //double distance = this.mob.distanceToSqr(target.getX(), target.getY(), target.getZ());
            //double reach = this.getAttackReachSqr(target);
            double distance = this.mob.distanceTo(target);
            double reach = this.getAttackReach(target);
            if (this.ticksUntilNextAttack <= 0 && distance <= reach) {

                this.mob.setAnimationState(23);
            }

        }


        @SuppressWarnings("unused")
        protected void doMovement(LivingEntity livingentity, Double d0) {
            this.mob.getLookControl().setLookAt(this.mob.getTarget(), 30.0F, 30.0F);
            this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
            if (!this.mob.getSensing().hasLineOfSight(livingentity)||this.mob.distanceTo(livingentity)>=25) {
                this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
                if (this.ticksUntilNextPathRecalculation <= 0 && (this.pathedTargetX == 0.0D && this.pathedTargetY == 0.0D && this.pathedTargetZ == 0.0D || livingentity.distanceToSqr(this.pathedTargetX, this.pathedTargetY, this.pathedTargetZ) >= 1.0D || this.mob.getRandom().nextFloat() < 0.05F)) {
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

                    if (!this.mob.getNavigation().moveTo(livingentity, this.walkingSpeedModifier)) {
                        this.ticksUntilNextPathRecalculation += 15;
                    }
                }
            }else{
                this.mob.lookAt(this.mob.getTarget(), 30.0f, 30.0f);
                Path path = this.mob.getNavigation().createPath(this.mob.getTarget(), 0);
                this.path = path;
                this.mob.getNavigation().moveTo(this.path, 0);
            }

        }







        protected void checkForAttack(double distance, double reach){

            if (this.ticksUntilNextAttack <= 2) {
                int r = this.mob.getRandom().nextInt(2048);
                if(r<=360) {

                    this.mob.setAnimationState(31);

                }
            }
        }



        protected void resetAttackCooldown() {
            this.ticksUntilNextAttack = 20;
        }


        protected double getAttackReachSqr(LivingEntity target) {
            return getAttackReach(target)*getAttackReach(target);
        }
        protected double getAttackReach (LivingEntity target){
            return this.mob.getBbWidth() + target.getBbWidth() + 2.0f;
        }

    }
}
