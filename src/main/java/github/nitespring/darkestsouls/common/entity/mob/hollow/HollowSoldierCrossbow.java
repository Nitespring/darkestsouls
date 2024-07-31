package github.nitespring.darkestsouls.common.entity.mob.hollow;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
 import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.EnumSet;
import java.util.Random;

public class HollowSoldierCrossbow extends Hollow implements GeoEntity {

    protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
    public Vec3 aimVec;
    public int resetPoseTick;

    private static final EntityDimensions CRAWLING_BB = new EntityDimensions(0.9f, 0.8f,  false);


    public HollowSoldierCrossbow(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
        this.xpReward = 10;
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.factory;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<>(this, "main_controller", 4, this::predicate));
        data.add(new AnimationController<>(this, "cape_controller", 0, this::capePredicate));
        data.add(new AnimationController<>(this, "stun_controller", 0, this::hitStunPredicate));
    }


    private <E extends GeoAnimatable> PlayState hitStunPredicate(AnimationState<E> event) { /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
        }*/

        if (hitStunTicks > 0) {
            event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.hollow.hit"));
        } else {
            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.hollow.new"));
        }
        return PlayState.CONTINUE;
    }

    private <E extends GeoAnimatable> PlayState capePredicate(AnimationState<E> event) { /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
        }*/

        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.hollow.cape"));

        return PlayState.CONTINUE;
    }

    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) { /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
        }*/
        int animState = this.getAnimationState();
        int combatState = this.getCombatState();
        if (this.isDeadOrDying()) {
            event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.hollow.death"));
        } else {
            switch (animState) {
                case 1:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.hollow.stun"));
                    break;
                case 11:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.hollow.crossbow.prepare_up"));
                    break;
                case 12:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.hollow.crossbow.prepare_down"));
                    break;
                case 13:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.hollow.crossbow.get_up"));
                    break;
                case 31:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.hollow.crossbow.shoot_up"));
                    break;
                case 32:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.hollow.crossbow.shoot_down"));
                    break;
                case 41:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.hollow.crossbow.reload_up"));
                    break;
                case 42:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.hollow.crossbow.reload_down"));
                    break;
                default:
                    if (this.isInWater()) {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.hollow.swim"));
                    } else if (this.onClimbable()) {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.hollow.climb"));
                    } else if (!this.onGround()) {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.hollow.fall"));

                    } else if (!(event.getLimbSwingAmount() > -0.06 && event.getLimbSwingAmount() < 0.06f) && !(getCombatState() == 3)) {
                        if (this.getCombatState() == 1) {
                            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.hollow.run"));
                        } else {

                            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.hollow.walk"));
                        }
                    } else {
                        switch (getCombatState()) {
                            case 2:
                                event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.hollow.crossbow.aim_up"));
                                break;
                            case 3:
                                event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.hollow.crossbow.aim_down"));
                                break;
                            default:
                                event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.hollow.crossbow.idle"));
                                break;
                        }
                    }
                    break;
            }
        }
        return PlayState.CONTINUE;
    }

    @Override
    public int getDefaultHatType() {
        return 0;
    }

    @Override
    public int getDefaultRobeType() {
        return 5;
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, @Nullable SpawnGroupData p_21437_, CompoundTag tag) {

        return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_,tag);
    }

    @Override
    public void populateClothing() {
        Random rn = new Random();
        int r = rn.nextInt(12) + 1;
        switch (r) {
            case 1, 3, 4:
                this.setRobeType(4);
                break;
            case 5, 6, 7, 8, 9:
                this.setRobeType(6);
                break;
            default:
                this.setRobeType(5);
                break;
        }
        if (this.getRobeType() == 5) {
            int r1 = rn.nextInt(13) + 1;
            switch (r1) {
                case 1, 2:
                    this.setHatType(2);
                    break;
                case 3, 4, 5, 6:
                    this.setHatType(0);
                    break;
                case 7, 8:
                    this.setHatType(3);
                    break;
                default:
                    this.setHatType(1);
                    break;
            }
        }
        if (this.getRobeType() == 6) {
            int r1 = rn.nextInt(12) + 1;
            switch (r1) {
                case 1:
                    this.setHatType(2);
                    break;
                case 2, 3, 4, 5, 6:
                    this.setHatType(0);
                    break;
                case 7:
                    this.setHatType(1);
                    break;
                default:
                    this.setHatType(3);
                    break;
            }
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new BreakDoorGoal(this, (p_34082_) -> {
            return p_34082_ == Difficulty.NORMAL || p_34082_ == Difficulty.HARD;
        }));
        this.goalSelector.addGoal(1, new OpenDoorGoal(this, true));
        this.goalSelector.addGoal(3, new MoveThroughVillageGoal(this, 1.0D, false, 4, () -> true));

        this.goalSelector.addGoal(2, new HollowSoldierCrossbow.RangedAttackGoal(this));
        //this.goalSelector.addGoal(2, new RunAwayGoal(this));


        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 0.2f, 1));
        super.registerGoals();

    }


    @Override
    public EntityDimensions getDimensions(Pose p_21047_) {

        if ((this.isInWater() && this.getAnimationState() == 0) || this.getAnimationState() == 1) {
            return CRAWLING_BB;
        } else {
            return this.getType().getDimensions();
        }
    }

    @Override
    public void sinkInFluid(FluidType type) {
        /*if(this.canSwimInFluidType(type)){
            self().setDeltaMovement(self().getDeltaMovement().add(0.0D, (double)0.02F, 0.0D));
        }*/

        super.sinkInFluid(type);
    }


    @Override
    public boolean canSwimInFluidType(FluidType type) {
        return true;
    }

    @Override
    public void tick() {
        if (this.onClimbable() && this.getAnimationState() == 0) {
            if (this.getTarget() != null && this.getTarget().position().y < this.position().y) {
                self().setDeltaMovement(self().getDeltaMovement().add(0.0D, (double) -0.2F, 0.0D));
            } else {
                self().setDeltaMovement(self().getDeltaMovement().add(0.0D, (double) 0.08F, 0.0D));
            }
        }

        if (this.tickCount % 5 == 0) {
            this.refreshDimensions();
        }


        if (this.getAnimationState() != 0 && !this.isDeadOrDying()) {
            this.playAnimation();
        }
        super.tick();
    }


    protected void playAnimation() {
        this.increaseAnimationTick(1);
        Level levelIn = this.level();
        Vec3 pos = this.position();
        boolean flag = this.getTarget() != null && this.distanceTo(this.getTarget()) <= 2;
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
            case 11:
                this.getNavigation().stop();
                if (getAnimationTick() >= 10) {
                    setAnimationTick(0);
                    setAnimationState(0);
                    setCombatState(2);
                }
                break;
            case 12:
                this.getNavigation().stop();
                if (getAnimationTick() >= 10) {
                    setAnimationTick(0);
                    setAnimationState(0);
                    setCombatState(3);
                }
                break;
            case 13:
                this.getNavigation().stop();
                if (getAnimationTick() >= 10) {
                    setAnimationTick(0);
                    setAnimationState(0);
                    setCombatState(0);
                }
                break;
            case 31:
                if(getAnimationTick()<=5){
                    if(this.getTarget()!=null) {
                        this.lookAt(this.getTarget(), 30.0f, 30.0f);
                        Path path = this.getNavigation().createPath(this.getTarget(), 0);
                        this.getNavigation().moveTo(path, 0);
                    }
                }else{
                    this.getNavigation().stop();
                }
                if (getAnimationTick() == 5) {
                    if (this.getTarget() == null) {
                        aimVec = this.getLookAngle().normalize();
                    } else {
                        aimVec = this.getTarget().position().add(pos.scale(-1)).normalize();
                    }
                }
                if (getAnimationTick() == 7) {
                    //this.playSound(this.getAttackSound(), 0.2f,1.0f);
                    if(aimVec==null){aimVec=this.getLookAngle().normalize();}
                    this.playSound(SoundEvents.CROSSBOW_SHOOT);
                    float x = (float) (pos.x + 0.6 * aimVec.x);
                    float y = (float) (pos.y + 1.8 + 0.6 * aimVec.y);
                    float z = (float) (pos.z + 0.6 * aimVec.z);
                    Arrow entity = new Arrow(EntityType.ARROW, levelIn);
                    entity.setPos(x, y, z);
                    float flyingPower = 2.5f;
                    entity.setDeltaMovement(aimVec.scale(flyingPower).add(0,0.2f,0));
                    //entity.shootFromRotation(this, (float) aimVec.x(), (float) aimVec.y(), (float) aimVec.z(),flyingPower,0.1f);
                    /*entity.xPower=flyingPower*aimVec.x;
                    entity.yPower=flyingPower*aimVec.y;
                    entity.zPower=flyingPower*aimVec.z;*/
                    entity.setOwner(this);

                    levelIn.addFreshEntity(entity);
                    emptyAmmo();
                }
                if (getAnimationTick() >= 15) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 32:
                if(getAnimationTick()<=5){
                    if(this.getTarget()!=null) {
                        this.lookAt(this.getTarget(), 30.0f, 30.0f);
                        Path path = this.getNavigation().createPath(this.getTarget(), 0);
                        this.getNavigation().moveTo(path, 0);
                    }
                }else{
                    this.getNavigation().stop();
                }
                if (getAnimationTick() == 5) {
                    if (this.getTarget() == null) {
                        aimVec = this.getLookAngle().normalize();
                    } else {
                        aimVec = this.getTarget().position().add(pos.scale(-1)).normalize();
                    }
                }
                if (getAnimationTick() == 7) {
                    //this.playSound(this.getAttackSound(), 0.2f,1.0f);
                    if(aimVec==null){aimVec=this.getLookAngle().normalize();}
                    this.playSound(SoundEvents.CROSSBOW_SHOOT);
                    float x = (float) (pos.x + 0.6 * aimVec.x);
                    float y = (float) (pos.y + 1.4 + 0.6 * aimVec.y);
                    float z = (float) (pos.z + 0.6 * aimVec.z);
                    Arrow entity = new Arrow(EntityType.ARROW, levelIn);
                    entity.setPos(x, y, z);
                    float flyingPower = 3.0f;
                    entity.setDeltaMovement(aimVec.scale(flyingPower).add(0,0.2f,0));
                    //entity.shootFromRotation(this, (float) aimVec.x(), (float) aimVec.y(), (float) aimVec.z(),flyingPower,0.1f);
                    /*entity.xPower=flyingPower*aimVec.x;
                    entity.yPower=flyingPower*aimVec.y;
                    entity.zPower=flyingPower*aimVec.z;*/
                    entity.setOwner(this);

                    levelIn.addFreshEntity(entity);
                    emptyAmmo();
                }
                if (getAnimationTick() >= 15) {
                    int r = this.getRandom().nextInt(2000);
                    if(r<=1200){
                        setAnimationTick(0);
                        setAnimationState(13);
                    }else{
                        setAnimationTick(0);
                        setAnimationState(0);
                    }
                }
                break;
            case 41:
                if (getAnimationTick() == 12) {
                    this.playSound(SoundEvents.CROSSBOW_LOADING_MIDDLE);
                }
                if (getAnimationTick() == 20) {
                    loadAmmo();
                }
                if (getAnimationTick() == 25) {
                    this.playSound(SoundEvents.CROSSBOW_LOADING_END);
                }
                if (getAnimationTick() >= 30) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 42:
                if (getAnimationTick() == 12) {
                    this.playSound(SoundEvents.CROSSBOW_LOADING_MIDDLE);
                }
                if (getAnimationTick() == 20) {
                    loadAmmo();
                }
                if (getAnimationTick() == 25) {
                    this.playSound(SoundEvents.CROSSBOW_LOADING_END);
                }
                if (getAnimationTick() >= 30) {
                    int r = this.getRandom().nextInt(2000);
                    if(r<=600){
                        setAnimationTick(0);
                        setAnimationState(13);
                    }else{
                        setAnimationTick(0);
                        setAnimationState(0);
                    }
                }
                break;
        }
    }

    /*public void moveToTarget(){
        boolean flag = this.getTarget()!=null;
        if(flag) {
            this.getLookControl().setLookAt(this.getTarget(), 10.0F, 10.0F);
            Path path = this.getNavigation().createPath(this.getTarget(), 0);
            this.getNavigation().moveTo(path, 1.2f);
        }

    }*/

    public class RangedAttackGoal extends Goal {

        private final double runningSpeedModifier;
        private final double strafingSpeedModifier;
        private final double walkingSpeedModifier;
        private final boolean followingTargetEvenIfNotSeen = true;
        protected final HollowSoldierCrossbow mob;
        private Path path;
        private double pathedTargetX;
        private double pathedTargetY;
        private double pathedTargetZ;
        private int ticksUntilNextPathRecalculation;


        private int ticksUntilNextRangedAttack;
        private long lastCanUseCheck;
        private int failedPathFindingPenalty = 0;
        private boolean canPenalize = false;
        private int seeTime;
        private boolean strafingClockwise;
        private boolean strafingBackwards;
        private int strafingTime = -1;


        public RangedAttackGoal(HollowSoldierCrossbow entityIn) {
            this.mob = entityIn;
            runningSpeedModifier = 1.8f;
            walkingSpeedModifier = 1.2f;
            strafingSpeedModifier = 0.4f;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }


        @Override
        public boolean canUse() {
            if (this.mob.getAnimationState() == 0) {
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
            } else {
                return false;
            }
        }


        @Override
        public boolean canContinueToUse() {
            LivingEntity livingentity = this.mob.getTarget();
            if (this.mob.getAnimationState() != 0) {
                return false;
            } else if (livingentity == null) {
                return false;
            } else if (!livingentity.isAlive()) {
                return false;
            } else if (!this.followingTargetEvenIfNotSeen) {
                return !this.mob.getNavigation().isDone();
            } else if (!this.mob.isWithinRestriction(livingentity.blockPosition())) {
                return false;
            } else {
                return !(livingentity instanceof Player) || !livingentity.isSpectator() && !((Player) livingentity).isCreative();
            }
        }

        @Override
        public void start() {
            //this.mob.getNavigation().moveTo(this.path, this.speedModifier);
            this.mob.setAggressive(true);
            this.ticksUntilNextPathRecalculation = 0;
            this.ticksUntilNextRangedAttack = 20;
        }

        @Override
        public void stop() {
            LivingEntity livingentity = this.mob.getTarget();
            if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingentity)) {
                this.mob.setTarget((LivingEntity) null);
            }
            this.seeTime = 0;
            if (this.mob.getCombatState() == 1) {
                this.mob.setCombatState(0);
            }
            this.path = this.mob.getNavigation().createPath(this.mob, 0);

            this.mob.getNavigation().stop();
        }


        @Override
        public void tick() {
            LivingEntity target = this.mob.getTarget();
            double distance = mob.distanceTo(target);
            double distanceSQR = mob.distanceToSqr(target);
            double reach = getCloseRangeDistanceTo(target);
            double reachSQR = getCloseRangeDistanceTo(target);
            //int animState = this.mob.getAnimationState();

            //if (getCombatState() != 2 && getCombatState() != 3) {
                this.doMovement(target, reachSQR);
            //}
            this.checkForCloseRangeAction(distance, reach);
            this.checkForLongRangeAction(distance, reach);
            //if(distance<=reach) {this.checkForPreciseAttack();}

            //this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
            this.ticksUntilNextRangedAttack = Math.max(this.ticksUntilNextRangedAttack - 1, 0);

        }


        @SuppressWarnings("unused")
        /*private void checkForPreciseAttack() {
            if (this.ticksUntilNextAttack <= 0) {
                this.mob.setAnimationState(22);
            }
        }*/


        protected void doMovement(LivingEntity livingentity, Double d0) {
            if (mob.getCombatState() == 0) {
                this.mob.getLookControl().setLookAt(this.mob.getTarget(), 30.0F, 30.0F);
                if (!this.mob.getSensing().hasLineOfSight(livingentity)) {
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
                } else {
                    if (livingentity != null) {
                        //double d0 = this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
                        boolean flag = this.mob.getSensing().hasLineOfSight(livingentity);
                        boolean flag1 = this.seeTime > 0;
                        if (flag != flag1) {
                            this.seeTime = 0;
                        }

                        if (flag) {
                            this.seeTime++;
                        } else {
                            this.seeTime--;
                        }

                        if (!(d0 > (double) this.getAttackReachSqr(livingentity)) && this.seeTime >= 20) {
                            this.mob.getNavigation().stop();
                            this.strafingTime++;
                        } else {
                            this.mob.getNavigation().moveTo(livingentity, this.strafingSpeedModifier);
                            this.strafingTime = -1;
                        }

                        if (this.strafingTime >= 20) {
                            if ((double) this.mob.getRandom().nextFloat() < 0.3) {
                                this.strafingClockwise = !this.strafingClockwise;
                            }

                            if ((double) this.mob.getRandom().nextFloat() < 0.3) {
                                this.strafingBackwards = !this.strafingBackwards;
                            }

                            this.strafingTime = 0;
                        }

                        if (this.strafingTime > -1) {
                            if (d0 > (double) (this.getAttackReachSqr(livingentity) * 0.75F)) {
                                this.strafingBackwards = false;
                            } else if (d0 < (double) (this.getAttackReachSqr(livingentity) * 0.25F)) {
                                this.strafingBackwards = true;
                            }

                            this.mob.getMoveControl().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
                            if (this.mob.getControlledVehicle() instanceof Mob mob) {
                                mob.lookAt(livingentity, 30.0F, 30.0F);
                            }

                            this.mob.lookAt(livingentity, 30.0F, 30.0F);
                        } else {
                            this.mob.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
                        }

                    }
                }
            } else if (mob.getCombatState() == 1) {
                Vec3 aimAwayFromOpponent = mob.position().add(mob.getTarget().position().scale(-1));
                Vec3 wantedPosition = mob.position().add(aimAwayFromOpponent.scale(12));
                //this.mob.moveControl.setWantedPosition(wantedPosition.x(),wantedPosition.y(),wantedPosition.z(),runningSpeedModifier);
                this.mob.getLookControl().setLookAt(wantedPosition.x(), wantedPosition.y(), wantedPosition.z(), 30.0F, 30.0F);
                this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
                if (this.ticksUntilNextPathRecalculation <= 0 && (this.pathedTargetX == 0.0D && this.pathedTargetY == 0.0D && this.pathedTargetZ == 0.0D || livingentity.distanceToSqr(this.pathedTargetX, this.pathedTargetY, this.pathedTargetZ) <= 8.0D || this.mob.getRandom().nextFloat() < 0.05F)) {
                    this.pathedTargetX = wantedPosition.x();
                    this.pathedTargetY = wantedPosition.y();
                    this.pathedTargetZ = wantedPosition.z();
                    this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
                    if (this.canPenalize) {
                        this.ticksUntilNextPathRecalculation += failedPathFindingPenalty;
                        if (this.mob.getNavigation().getPath() != null) {
                            net.minecraft.world.level.pathfinder.Node finalPathPoint = this.mob.getNavigation().getPath().getEndNode();
                            if (finalPathPoint != null && livingentity.distanceToSqr(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) >= 8)
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

                    if (!this.mob.getNavigation().moveTo(wantedPosition.x(), wantedPosition.y(), wantedPosition.z(), this.runningSpeedModifier)) {
                        this.ticksUntilNextPathRecalculation += 15;
                    }
                }
            } else {
                this.mob.getLookControl().setLookAt(this.mob.getTarget(), 30.0F, 30.0F);
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
                    if (!this.mob.getNavigation().moveTo(livingentity, 0)) {
                        this.ticksUntilNextPathRecalculation += 15;
                    }
                }
            }
        }




            protected void checkForCloseRangeAction ( double distance, double reach){
                if (distance <= reach) {

                    int r = this.mob.getRandom().nextInt(2000);
                    switch (mob.getCombatState()) {
                        case 2:
                            if (r <= 400) {
                                mob.setCombatState(1);
                            }else if (r <= 1200) {
                                this.mob.setAnimationState(41);
                            }
                            break;
                        case 3:
                            if (r <= 200) {
                                mob.setAnimationState(13);
                            }else if (r <= 1200) {
                                this.mob.setAnimationState(42);
                            }
                            break;
                        default:
                            if (r <= 900) {
                                mob.setCombatState(1);
                            }
                            break;
                    }

                }
            }
            protected void checkForLongRangeAction ( double distance, double reach){
                if (!mob.hasLineOfSight(mob.getTarget())) {
                    int r = mob.getRandom().nextInt(2000);
                    if (mob.getCombatState() == 1) {
                        if (r <= 900) {
                            mob.setCombatState(0);
                        }
                    }
                }
                if (mob.hasLineOfSight(mob.getTarget()) && this.ticksUntilNextRangedAttack <= 0) {
                    int r = mob.getRandom().nextInt(2000);
                    switch (mob.getCombatState()) {
                        case 1:
                            if (distance >= 25) {
                                if (r <= 900) {
                                    mob.setCombatState(0);
                                }
                            }
                            break;
                        case 2:
                            if (isAmmoEmpty()) {
                                mob.setAnimationState(41);
                            } else if (r <= 1200) {
                                this.mob.setAnimationState(31);
                            }
                            break;
                        case 3:
                            if (isAmmoEmpty()) {
                                mob.setAnimationState(42);
                            } else if (r <= 1200) {
                                this.mob.setAnimationState(32);
                            }
                            break;
                        default:
                            if (isAmmoEmpty()) {
                                mob.setAnimationState(41);
                            } else if (r <= 900) {
                                this.mob.setAnimationState(11);
                            } else if (r <= 1600) {
                                this.mob.setAnimationState(12);
                            }
                            break;
                    }
                }
            }


            protected void resetAttackCooldown () {
                //this.ticksUntilNextAttack = 10;
                this.ticksUntilNextRangedAttack = 20;
            }


            protected double getAttackReachSqr (LivingEntity p_179512_1_){
                return (double) (this.mob.getBbWidth() * 4.0F * this.mob.getBbWidth() * 4.0F + p_179512_1_.getBbWidth());
            }
            protected double getCloseRangeDistanceTo (LivingEntity target){
                return this.mob.getBbWidth() + target.getBbWidth() + 2.0f;
            }
            protected double getAttackRange (LivingEntity target){
                return this.mob.getBbWidth() + target.getBbWidth() + 24.0f;
            }
        }
}

