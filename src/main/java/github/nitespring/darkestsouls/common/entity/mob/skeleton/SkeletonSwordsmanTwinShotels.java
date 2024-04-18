package github.nitespring.darkestsouls.common.entity.mob.skeleton;

import github.nitespring.darkestsouls.common.entity.mob.hollow.Hollow;
import github.nitespring.darkestsouls.common.entity.projectile.throwable.ThrowingKnifeEntity;
import github.nitespring.darkestsouls.common.entity.projectile.weapon.Flame;
import github.nitespring.darkestsouls.common.entity.util.DamageHitboxEntity;
import github.nitespring.darkestsouls.core.init.EntityInit;
import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
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
import java.util.Random;

public class SkeletonSwordsmanTwinShotels extends Skeleton implements GeoEntity {

    protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
    public Vec3 aimVec;

    private static final EntityDimensions CRAWLING_BB = new EntityDimensions(0.9f, 0.8f, false);


    public SkeletonSwordsmanTwinShotels(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
        this.xpReward=9;
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {return this.factory;}

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<>(this, "main_controller", 4, this::predicate));
        data.add(new AnimationController<>(this, "rotation_controller", 0, this::rotationPredicate));
        data.add(new AnimationController<>(this, "cape_controller", 0, this::capePredicate));
        data.add(new AnimationController<>(this, "stun_controller", 0, this::hitStunPredicate));
    }
    private <E extends GeoAnimatable> PlayState rotationPredicate(AnimationState<E> event) { /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
        }*/

        if(getAnimationState()==13) {
            event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.tall_skeleton.roll_rotation"));
        }else {
            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.tall_skeleton.new"));
        }
        return PlayState.CONTINUE;
    }

    private <E extends GeoAnimatable> PlayState hitStunPredicate(AnimationState<E> event) { /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
        }*/

        if(hitStunTicks>0) {
            event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.tall_skeleton.hit"));
        }else {
            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.tall_skeleton.new"));
        }
        return PlayState.CONTINUE;
    }

    private <E extends GeoAnimatable> PlayState capePredicate(AnimationState<E> event) { /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
        }*/

            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.tall_skeleton.cape"));

        return PlayState.CONTINUE;
    }
    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) { /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
        }*/
        int animState = this.getAnimationState();
        int combatState = this.getCombatState();
        if(this.isDeadOrDying()) {
            event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.tall_skeleton.death"));
        }else {
            switch(animState) {
                case 1:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.tall_skeleton.stun"));
                    break;
                case 11:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.tall_skeleton.dodge_right"));
                    break;
                case 12:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.tall_skeleton.dodge_left"));
                    break;
                case 13:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.tall_skeleton.roll_invisible"));
                    break;
                case 20:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.tall_skeleton.double_shotels.attack1"));
                    break;
                case 21:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.tall_skeleton.double_shotels.attack2"));
                    break;
                case 22:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.tall_skeleton.double_shotels.attack3"));
                    break;
                case 23:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.tall_skeleton.double_shotels.attack4"));
                    break;
                case 24:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.tall_skeleton.double_shotels.attack5"));
                    break;
                case 25:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.tall_skeleton.double_shotels.attack6"));
                    break;
                case 26:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.tall_skeleton.double_shotels.attack7"));
                    break;
                case 27:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.tall_skeleton.double_shotels.attack8"));
                    break;
                case 28:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.tall_skeleton.double_shotels.attack9"));
                    break;
                case 29:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.tall_skeleton.double_shotels.attack10"));
                    break;
                case 31:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.tall_skeleton.throw"));
                    break;
                case 32:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.tall_skeleton.throw1"));
                    break;
                default:
                    if(this.onClimbable()) {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.tall_skeleton.climb"));
                    }else if(!this.onGround()) {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.tall_skeleton.fall"));
                        
                    }else if(!(event.getLimbSwingAmount() > -0.06 && event.getLimbSwingAmount() < 0.06f)){
                        if(this.getCombatState()==1) {
                            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.tall_skeleton.run"));
                        }else{

                            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.tall_skeleton.walk"));
                        }
                    }else {
                        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.tall_skeleton.idle"));
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
        return 1;
    }

    @Override
    public ItemStack getRightHandItem() {
        return ItemInit.CARTHUS_SHOTEL.get().getDefaultInstance();
    }

    @Override
    public ItemStack getLeftHandItem() {
        return ItemInit.CARTHUS_SHOTEL.get().getDefaultInstance();
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, @Nullable SpawnGroupData p_21437_, @Nullable CompoundTag p_21438_) {

        return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
    }
    @Override
    public void populateClothing(){
                this.setRobeType(1);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new BreakDoorGoal(this, (p_34082_) -> {
            return p_34082_ == Difficulty.NORMAL || p_34082_ == Difficulty.HARD;
        }));
        this.goalSelector.addGoal(1, new OpenDoorGoal(this, true));
        this.goalSelector.addGoal(3, new MoveThroughVillageGoal(this, 1.0D, false, 4, ()->true));

        this.goalSelector.addGoal(2, new SkeletonSwordsmanTwinShotels.AttackGoal(this));
        this.goalSelector.addGoal(2, new SkeletonSwordsmanTwinShotels.AttackGoalRunning(this));


        super.registerGoals();

    }



    @Override
    public EntityDimensions getDimensions(Pose p_21047_) {

         if(this.getAnimationState()==1) {
             return CRAWLING_BB;
         }else {
             return this.getType().getDimensions();
         }
    }

    @Override
    public boolean canDrownInFluidType(FluidType type) {return false;}



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
        boolean flag = this.getTarget()!=null && this.distanceTo(this.getTarget())<=2;
        this.getNavigation().stop();
        switch(this.getAnimationState()) {
            case 1:
                if(getAnimationTick()>=85) {
                    this.getNavigation().stop();
                    setAnimationTick(0);
                    this.resetPoiseHealth();
                    setAnimationState(0);
                }
                break;
            case 11:
                if(getAnimationTick()==2) {
                    this.setDeltaMovement(0,0.5,0);
                    double b = Math.PI*4/12;
                    if(this.getTarget()==null) {
                        Vec3 aim = this.getLookAngle();
                        aimVec = new Vec3(aim.x*Math.cos(b)-aim.z*Math.sin(b),
                                            Math.max(0,aim.y),
                                            aim.z*Math.cos(b)+aim.x*Math.sin(b));
                    }else{
                        Vec3 aim = this.getTarget().position().add(pos.scale(-1));
                        aimVec = new Vec3(aim.x*Math.cos(b)-aim.z*Math.sin(b),
                                            Math.max(0,aim.y),
                                            aim.z*Math.cos(b)+aim.x*Math.sin(b));
                    }
                }
                if(getAnimationTick()==2) {
                    this.hurtTime = 13;
                }
                if(getAnimationTick()>=2){
                    for(int i = 0; i<=2; i++) {
                        Random r = new Random();
                        this.level().addParticle(ParticleTypes.WHITE_SMOKE, pos.x+r.nextFloat(-0.5f, 0.5f), pos.y + 0.75f +r.nextFloat(-0.5f, 0.5f), pos.z+r.nextFloat(-0.5f, 0.5f), 0, 0, 0);
                        this.level().addParticle(ParticleTypes.SMOKE, pos.x+r.nextFloat(-0.5f, 0.5f), pos.y + 0.75f+r.nextFloat(-0.5f, 0.5f), pos.z+r.nextFloat(-0.5f, 0.5f), 0, 0, 0);
                        this.level().addParticle(ParticleTypes.LARGE_SMOKE, pos.x+r.nextFloat(-0.5f, 0.5f), pos.y + 0.5f+r.nextFloat(-0.5f, 0.5f), pos.z+r.nextFloat(-0.5f, 0.5f), 0, 0, 0);
                        this.level().addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, pos.x+r.nextFloat(-0.5f, 0.5f), pos.y + 0.75f +r.nextFloat(-0.5f, 0.5f), pos.z+r.nextFloat(-0.5f, 0.5f), 0, 0, 0);
                    }
                }
                if(getAnimationTick()==5) {
                    this.playSound(this.getAttackSound(), 0.2f,1.0f);
                    if(this.aimVec!=null) {
                        this.setDeltaMovement(this.aimVec.normalize().add(0,0.25f,0).scale(0.75));
                    }else {
                        this.setDeltaMovement(this.getLookAngle().normalize().add(0,0.25f,0).scale(0.75));
                    }
                }
                if(getAnimationTick()>=16) {
                    if(flag) {
                        int r = this.getRandom().nextInt(2048);
                        if (r <= 36) {
                            setAnimationTick(0);
                            setAnimationState(21);
                        }
                    }
                }
                if(getAnimationTick()>18) {
                    setAnimationTick(0);
                    int r = this.getRandom().nextInt(2048);
                    if (r <= 960) {
                        setAnimationState(31);
                    } else  {
                        setAnimationState(0);
                    }
                }
                break;
            case 12:
                if(getAnimationTick()==2) {
                    this.setDeltaMovement(0,0.5,0);
                    double b = -Math.PI*4/12;
                    if(this.getTarget()==null) {
                        Vec3 aim = this.getLookAngle();
                        aimVec = new Vec3(aim.x*Math.cos(b)-aim.z*Math.sin(b),
                                Math.max(0,aim.y),
                                aim.z*Math.cos(b)+aim.x*Math.sin(b));
                    }else{
                        Vec3 aim = this.getTarget().position().add(pos.scale(-1));
                        aimVec = new Vec3(aim.x*Math.cos(b)-aim.z*Math.sin(b),
                                Math.max(0,aim.y),
                                aim.z*Math.cos(b)+aim.x*Math.sin(b));
                    }
                }
                if(getAnimationTick()==2) {
                    this.hurtTime = 13;
                }
                if(getAnimationTick()>=2){
                    for(int i = 0; i<=2; i++) {
                        Random r = new Random();
                        this.level().addParticle(ParticleTypes.WHITE_SMOKE, pos.x+r.nextFloat(-0.5f, 0.5f), pos.y + 0.75f +r.nextFloat(-0.5f, 0.5f), pos.z+r.nextFloat(-0.5f, 0.5f), 0, 0, 0);
                        this.level().addParticle(ParticleTypes.SMOKE, pos.x+r.nextFloat(-0.5f, 0.5f), pos.y + 0.75f+r.nextFloat(-0.5f, 0.5f), pos.z+r.nextFloat(-0.5f, 0.5f), 0, 0, 0);
                        this.level().addParticle(ParticleTypes.LARGE_SMOKE, pos.x+r.nextFloat(-0.5f, 0.5f), pos.y + 0.5f+r.nextFloat(-0.5f, 0.5f), pos.z+r.nextFloat(-0.5f, 0.5f), 0, 0, 0);
                        this.level().addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, pos.x+r.nextFloat(-0.5f, 0.5f), pos.y + 0.75f +r.nextFloat(-0.5f, 0.5f), pos.z+r.nextFloat(-0.5f, 0.5f), 0, 0, 0);
                    }
                }
                if(getAnimationTick()==5) {
                    this.playSound(this.getAttackSound(), 0.2f,1.0f);
                    if(this.aimVec!=null) {
                        this.setDeltaMovement(this.aimVec.normalize().add(0,0.25f,0).scale(0.75));
                    }else {
                        this.setDeltaMovement(this.getLookAngle().normalize().add(0,0.25f,0).scale(0.75));
                    }
                }
                if(getAnimationTick()>=16) {
                    if(flag) {
                        int r = this.getRandom().nextInt(2048);
                        if (r <= 36) {
                            setAnimationTick(0);
                            setAnimationState(21);
                        }
                    }
                }
                if(getAnimationTick()>18) {
                    setAnimationTick(0);
                    int r = this.getRandom().nextInt(2048);
                    if (r <= 960) {
                        setAnimationState(31);
                    } else  {
                        setAnimationState(0);
                    }
                }
                break;
            //Attack
            case 21:
                if(getAnimationTick()==6) {
                    this.playSound(this.getAttackSound(), 0.2f,1.0f);
                }
                if(getAnimationTick()==8) {
                    this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.0f)*this.getLookAngle().x,
                                    0.25,
                                    (1.0f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE), 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    h.setHitboxType(4);
                    this.level().addFreshEntity(h);
                }
                /*if(getAnimationTick()>=10&&flag) {
                    int r = this.getRandom().nextInt(2048);
                    if (r <= 480) {
                        setAnimationTick(0);
                        setAnimationState(22);
                    }
                }*/
                if(getAnimationTick()>=16) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 31:
                if(getAnimationTick()==4) {
                    if(this.getTarget()==null) {
                        aimVec = this.getLookAngle().normalize();
                    }else{
                        aimVec = this.getTarget().position().add(pos.scale(-1)).normalize();
                    }
                }
                if(getAnimationTick()==5) {
                    //this.playSound(this.getAttackSound(), 0.2f,1.0f);
                    this.playSound(SoundEvents.FISHING_BOBBER_THROW);
                    this.doRangedAttack();

                }
                if(getAnimationTick()>=8) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 32:
                if(getAnimationTick()==4) {
                    if(this.getTarget()==null) {
                        aimVec = this.getLookAngle().normalize();
                    }else{
                        aimVec = this.getTarget().position().add(pos.scale(-1)).normalize();
                    }
                }
                if(getAnimationTick()==5) {
                    //this.playSound(this.getAttackSound(), 0.2f,1.0f);
                    this.playSound(SoundEvents.FISHING_BOBBER_THROW);
                    this.doRangedAttack();

                }
                if(getAnimationTick()>=8) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
        }
    }

    public void doRangedAttack(){
        Level levelIn = this.level();
        Vec3 pos = this.position();
        Vec3 aim = this.getLookAngle();
        if(this.aimVec!=null) {
            aim = aimVec;
        }
        double a=  1*Math.PI/8;
        for(int i = 0; i<=2; i++) {
            int j = i-1;
            Random r = new Random();
            //float rF = 2*(r.nextFloat()-0.5f);
            float b = (float) (a*j);
            Vec3 aim1 = new Vec3((aim.x*Math.cos(b)-aim.z*Math.sin(b)),aim.y,(aim.z*Math.cos(b)+aim.x*Math.sin(b)));
            float x = (float) (pos.x + 0.4 * aim.x+ 0.4 * aim1.x);
            float y = (float) (pos.y + 0.8 + 0.6 * aim1.y);
            float z = (float) (pos.z + 0.4 * aim.z+ 0.4 * aim1.z);
            ThrowingKnifeEntity entity = new ThrowingKnifeEntity(EntityInit.THROWING_KNIFE.get(), levelIn);
            entity.setPos(x,y,z);
            float flyingPower = 0.15f;
            entity.xPower=flyingPower*(aimVec.x+aim1.x);
            entity.yPower=flyingPower*(aimVec.y+aim1.y);
            entity.zPower=flyingPower*(aimVec.z+aim1.z);
            entity.setOwner(this);
            entity.setItem(ItemInit.KUKRI.get().getDefaultInstance());
            entity.setAttackPower((float) this.getAttributeValue(Attributes.ATTACK_DAMAGE));
            entity.setBloodDamage(3);
            entity.setToRotate(false);
            entity.setPoiseDamage(2);
            entity.setGravPower(0.0005f);
            levelIn.addFreshEntity(entity);
        }
    }
    public void moveToTarget(float speedModifier){
        boolean flag = this.getTarget()!=null;
        if(flag) {
            this.getLookControl().setLookAt(this.getTarget(), 10.0F, 10.0F);
            Path path = this.getNavigation().createPath(this.getTarget(), 0);
            this.getNavigation().moveTo(path, speedModifier);
        }
    }

    public class AttackGoal extends Goal {


        private final double speedModifier = 1.0f;
        private final boolean followingTargetEvenIfNotSeen = true;
        protected final SkeletonSwordsmanTwinShotels mob;
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
        private int lastCanUpdateStateCheck;




        public AttackGoal(SkeletonSwordsmanTwinShotels entityIn) {
            this.mob = entityIn;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }


        @Override
        public boolean canUse() {
            if(this.mob.getAnimationState()==0&&this.mob.getCombatState()!=1) {
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
            if(this.mob.getAnimationState()!=0||this.mob.getCombatState()==1) {
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
            this.ticksUntilNextRangedAttack = 10;
            this.lastCanUpdateStateCheck = 150;
            int r = this.mob.getRandom().nextInt(2048);
            if(r<=1640) {
                this.mob.setCombatState(1);
                this.stop();
            }
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

            this.lastCanUpdateStateCheck = Math.max(this.lastCanUpdateStateCheck-1, 0);
            if(this.lastCanUpdateStateCheck<=0){
                int r = this.mob.getRandom().nextInt(2048);
                if(r<=450) {
                    this.mob.setCombatState(1);
                    this.stop();
                }
                this.lastCanUpdateStateCheck=200;
            }


            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
            this.ticksUntilNextRangedAttack = Math.max(this.ticksUntilNextRangedAttack - 1, 0);
            if(this.ticksUntilNextRangedAttack<=0 && this.ticksUntilNextAttack <= 0){
                    this.ticksUntilNextRangedAttack=5;
            }

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
                if(r<=360) {
                    this.mob.setAnimationState(21);
                }
            }
            if (distance <= reach*16 && this.ticksUntilNextAttack <= 0) {
                int r = this.mob.getRandom().nextInt(2048);
                if(r<=48) {
                    this.mob.setAnimationState(11);
                }else if(r<=96) {
                    this.mob.setAnimationState(12);
                }
            }
            if (this.ticksUntilNextRangedAttack <= 2 && this.ticksUntilNextAttack <= 2) {
                int r = this.mob.getRandom().nextInt(2048);
                if(r<=180) {

                    this.mob.setAnimationState(31);

                }else if(r<=360) {

                    this.mob.setAnimationState(32);

                }
            }
        }



        protected void resetAttackCooldown() {
            this.ticksUntilNextAttack = 20;
        }


        protected double getAttackReachSqr(LivingEntity p_179512_1_) {
            return (double)(this.mob.getBbWidth() * 16.0F * this.mob.getBbWidth());
        }

    }

    public class AttackGoalRunning extends Goal {


        private final double speedModifier = 2.0f;
        private final boolean followingTargetEvenIfNotSeen = true;
        protected final SkeletonSwordsmanTwinShotels mob;
        private Path path;
        private double pathedTargetX;
        private double pathedTargetY;
        private double pathedTargetZ;
        private int ticksUntilNextPathRecalculation;
        private int ticksUntilNextAttack;
        private long lastCanUseCheck;
        private int failedPathFindingPenalty = 0;
        private boolean canPenalize = false;
        private int lastCanUpdateStateCheck;
        private int ticksUntilNextRangedAttack;



        public AttackGoalRunning(SkeletonSwordsmanTwinShotels entityIn) {
            this.mob = entityIn;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }


        @Override
        public boolean canUse() {
            if(this.mob.getAnimationState()==0&&this.mob.getCombatState()==1) {
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
            if(this.mob.getAnimationState()!=0||this.mob.getCombatState()!=1) {
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
            this.lastCanUpdateStateCheck = 666;
            this.ticksUntilNextRangedAttack = 10;
            this.mob.setAnimationState(0);
        }
        @Override
        public void stop() {
            LivingEntity livingentity = this.mob.getTarget();
            if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingentity)) {
                this.mob.setTarget((LivingEntity)null);
            }
            int r = this.mob.getRandom().nextInt(2048);
            if(r<=120 || livingentity==null || !livingentity.isAlive() || livingentity.isSpectator()
                    || (livingentity instanceof Player && ((Player)livingentity).isCreative())) {

                this.mob.setCombatState(0);

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

            this.lastCanUpdateStateCheck = Math.max(this.lastCanUpdateStateCheck-1, 0);
            if(this.lastCanUpdateStateCheck<=0){
                int r = this.mob.getRandom().nextInt(2048);
                if(r<=84) {
                    this.mob.setCombatState(0);
                    this.stop();
                }
                this.lastCanUpdateStateCheck=200;
            }
            this.ticksUntilNextRangedAttack = Math.max(this.ticksUntilNextRangedAttack - 1, 0);
            if(this.ticksUntilNextRangedAttack<=0 && this.ticksUntilNextAttack <= 0){
                this.ticksUntilNextRangedAttack=5;
            }

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
                if(r<=360) {
                    this.mob.setAnimationState(21);
                }
            }
            if (distance <= reach*16 && this.ticksUntilNextAttack <= 0) {
                int r = this.mob.getRandom().nextInt(2048);
                if(r<=48) {
                    this.mob.setAnimationState(11);
                }else if(r<=96) {
                    this.mob.setAnimationState(12);
                }
            }
            if (this.ticksUntilNextRangedAttack <= 2 && this.ticksUntilNextAttack <= 2) {
                int r = this.mob.getRandom().nextInt(2048);
                if(r<=180) {

                    this.mob.setAnimationState(31);

                }else if(r<=360) {

                    this.mob.setAnimationState(32);

                }
            }
        }



        protected void resetAttackCooldown() {
            this.ticksUntilNextAttack = 20;
        }


        protected double getAttackReachSqr(LivingEntity p_179512_1_) {
            return (double)(this.mob.getBbWidth() * 16.0F * this.mob.getBbWidth());
        }

    }


}

