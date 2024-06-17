package github.nitespring.darkestsouls.common.entity.mob.abyss;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.common.entity.mob.hollow.HollowAssassin;
import github.nitespring.darkestsouls.common.entity.projectile.throwable.ThrowingKnifeEntity;
import github.nitespring.darkestsouls.common.entity.util.DamageHitboxEntity;
import github.nitespring.darkestsouls.core.init.EntityInit;
import github.nitespring.darkestsouls.core.init.ItemInit;
import github.nitespring.darkestsouls.core.init.SoundInit;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;
import org.joml.Random;
import org.joml.Vector3f;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;

import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.EnumSet;

public class Darkwraith extends DarkestSoulsAbstractEntity implements GeoEntity{
    protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
    public Vec3 aimVec;
    public Darkwraith(EntityType<? extends PathfinderMob> mob, Level level) {
        super(mob, level);
        this.xpReward=20;
    }

    @Override
    public boolean isBoss() {
        return false;
    }

    @Override
    protected int getDSDefaultTeam() {
        return 0;
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<>(this, "main_controller", 4, this::predicate));
        data.add(new AnimationController<>(this, "shield_controller", 1, this::shieldPredicate));
        data.add(new AnimationController<>(this, "stun_controller", 0, this::hitStunPredicate));
    }

    private <E extends GeoAnimatable> PlayState hitStunPredicate(AnimationState<E> event) { /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
        }*/

        if(hitStunTicks>0) {
            event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.darkwraith.hit"));
        }else {
            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.darkwraith.new"));
        }
        return PlayState.CONTINUE;
    }

    private <E extends GeoAnimatable> PlayState shieldPredicate(AnimationState<E> event) {

        if(getAnimationState()==1||this.isDeadOrDying()) {
            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.darkwraith.new"));
        }else {
            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.darkwraith.shield_rotation"));
        }

        return PlayState.CONTINUE;
    }
    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
        int animState = this.getAnimationState();
        int combatState = this.getCombatState();
        if(this.isDeadOrDying()) {
            event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.darkwraith.death"));
        }else {
            switch(animState) {
                case 1:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.darkwraith.stun"));
                    break;
                case 11:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.darkwraith.shield.arm"));
                    break;
                case 12:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.darkwraith.shield.unarm"));
                    break;
                case 21:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.darkwraith.attack1"));
                    break;
                case 22:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.darkwraith.attack2"));
                    break;
                case 23:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.darkwraith.attack3"));
                    break;
                case 24:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.darkwraith.attack4"));
                    break;
                case 25:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.darkwraith.attack5"));
                    break;
                case 26:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.darkwraith.attack6"));
                    break;
                case 27:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.darkwraith.attack7"));
                    break;
                case 28:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.darkwraith.attack8"));
                    break;
                case 29:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.darkwraith.attack9"));
                    break;
                case 30:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.darkwraith.attack10"));
                    break;
                case 31:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.darkwraith.attack11"));
                    break;
                case 32:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.darkwraith.attack12"));
                    break;
                case 33:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.darkwraith.attack13"));
                    break;
                case 34:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.darkwraith.attack_grab"));
                    break;
                case 35:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.darkwraith.attack_grab_continue"));
                    break;
                case 36:
                    event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.darkwraith.attack_grab_finish"));
                    break;
                default:
                    if(this.getEntityState()==1) {
                        if (!this.onGround()) {
                            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.darkwraith.shield.fall"));

                        } else if (!(event.getLimbSwingAmount() > -0.06 && event.getLimbSwingAmount() < 0.06f)) {
                            if (this.getCombatState() == 1) {
                                event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.darkwraith.shield.run"));
                            } else {

                                event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.darkwraith.shield.walk"));
                            }
                        } else {
                            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.darkwraith.shield.idle"));
                        }
                        break;
                    }else {
                        if (!this.onGround()) {
                            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.darkwraith.fall"));

                        } else if (!(event.getLimbSwingAmount() > -0.06 && event.getLimbSwingAmount() < 0.06f)) {
                            if (this.getCombatState() == 1) {
                                event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.darkwraith.run"));
                            } else {

                                event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.darkwraith.walk"));
                            }
                        } else {
                            event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.darkwraith.idle"));
                        }
                        break;
                    }
            }
        }
        return PlayState.CONTINUE;
    }
    @Override
    protected void registerGoals() {
        //this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new BreakDoorGoal(this, (p_34082_) -> {
            return p_34082_ == Difficulty.NORMAL || p_34082_ == Difficulty.HARD;
        }));
        this.goalSelector.addGoal(1, new OpenDoorGoal(this, true));
        //this.goalSelector.addGoal(3, new MoveThroughVillageGoal(this, 1.0D, false, 4, ()->true));

        this.goalSelector.addGoal(2, new Darkwraith.AttackGoal(this));
        this.goalSelector.addGoal(2, new Darkwraith.AttackGoalRunning(this));


        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this,0.2f,1));
        super.registerGoals();

    }
    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_21239_) {return SoundInit.DARKWRAITH_HURT.get();}
    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundInit.DARKWRAITH_DEATH.get();
    }
    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {return SoundInit.DARKWRAITH_IDLE.get();}
    protected SoundEvent getAttackSound() {
        return SoundInit.DARKWRAITH_ATTACK.get();
    }

    @Override
    protected float getSoundVolume() {
        return 0.2f;
    }

    @Override
    public int getMaxPoise() {return 36;}
    @Override
    public boolean canDrownInFluidType(FluidType type) {return false;}
    @Override
    public int getBloodResistance() {return 12;}
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.factory;
    }
    @Override
    public void tick() {

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
        Vec3 aim = this.getLookAngle();
        boolean flag = this.getTarget()!=null && this.distanceTo(this.getTarget())<=2;
        this.getNavigation().stop();
        switch (this.getAnimationState()) {
            case 1:
                this.getNavigation().stop();
                this.getNavigation().stop();
                if(getAnimationTick()>=55) {
                    setAnimationTick(0);
                    this.resetPoiseHealth();
                    setAnimationState(0);
                }
                break;
            case 11:
                this.getNavigation().stop();
                if(getAnimationTick()>=15) {
                    setAnimationTick(0);
                    setAnimationState(0);
                    setEntityState(1);
                }
                break;
            case 12:
                this.getNavigation().stop();
                if(getAnimationTick()>=15) {
                    setAnimationTick(0);
                    setAnimationState(0);
                    setEntityState(0);
                }
                break;
            //Attack
            case 21:
                if(getAnimationTick()<=4){
                    this.moveToTarget(0.8f);
                }else{
                    this.getNavigation().stop();
                }
                if(getAnimationTick()==6) {
                    this.playSound(this.getAttackSound(), 0.2f,1.0f);
                }
                if(getAnimationTick()==14) {
                    this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.2f)*this.getLookAngle().x,
                                    0.25,
                                    (1.2f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE)*0.8f, 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    this.level().addFreshEntity(h);
                }
                if(getAnimationTick()>=18&&flag) {
                    int r = new Random().nextInt(1024);
                    if(r<=360) {
                        setAnimationTick(0);
                        setAnimationState(28);
                    }else if (r<=540) {
                        setAnimationTick(0);
                        setAnimationState(22);
                    }
                }
                if(getAnimationTick()>=20) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 22:
                if(getAnimationTick()<=4){
                    this.moveToTarget(0.8f);
                }else{
                    this.getNavigation().stop();
                }
                if(getAnimationTick()==6) {
                    this.playSound(this.getAttackSound(), 0.2f,1.0f);
                }
                if(getAnimationTick()==12) {
                    this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.2f)*this.getLookAngle().x,
                                    0.25,
                                    (1.2f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE)*0.8f, 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    this.level().addFreshEntity(h);
                }
                if(getAnimationTick()>=17&&flag) {
                    setAnimationTick(0);
                    setAnimationState(23);
                }
                if(getAnimationTick()>=19) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 23:
                if(getAnimationTick()<=4){
                    this.moveToTarget(0.8f);
                }else{
                    this.getNavigation().stop();
                }
                if(getAnimationTick()==6) {
                    this.playSound(this.getAttackSound(), 0.2f,1.0f);
                }
                if(getAnimationTick()==14) {
                    this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.2f)*this.getLookAngle().x,
                                    0.25,
                                    (1.2f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE)*0.9f, 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    this.level().addFreshEntity(h);
                }
                if(getAnimationTick()>=20) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 24:
                if(getAnimationTick()<=4){
                    this.moveToTarget(0.8f);
                }else{
                    this.getNavigation().stop();
                }
                if(getAnimationTick()==6) {
                    this.playSound(this.getAttackSound(), 0.2f,1.0f);
                }
                if(getAnimationTick()==14) {
                    this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.2f)*this.getLookAngle().x,
                                    0.25,
                                    (1.2f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE)*0.8f, 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    this.level().addFreshEntity(h);
                }
                if(getAnimationTick()>=17&&flag) {
                    setAnimationTick(0);
                    setAnimationState(23);
                }
                if(getAnimationTick()>=19) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 25:
                if(getAnimationTick()<=4){
                    this.moveToTarget(0.8f);
                }else{
                    this.getNavigation().stop();
                }
                if(getAnimationTick()==6) {
                    this.playSound(this.getAttackSound(), 0.2f,1.0f);
                }
                if(getAnimationTick()==14) {
                    this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.2f)*this.getLookAngle().x,
                                    0.25,
                                    (1.2f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE)*0.8f, 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    this.level().addFreshEntity(h);
                }
                if(getAnimationTick()>=17&&flag) {
                    int r = new Random().nextInt(1024);
                    if(r<=360) {
                        setAnimationTick(0);
                        setAnimationState(21);
                    }else if (r<=540) {
                        setAnimationTick(0);
                        setAnimationState(23);
                    }
                }
                if(getAnimationTick()>=19) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 26:
                if(getAnimationTick()<=4){
                    this.moveToTarget(0.8f);
                }else{
                    this.getNavigation().stop();
                }
                if(getAnimationTick()==6) {
                    this.playSound(this.getAttackSound(), 0.2f,1.0f);
                }
                if(getAnimationTick()==14) {
                    this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.2f)*this.getLookAngle().x,
                                    0.25,
                                    (1.2f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE)*1.0f, 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    this.level().addFreshEntity(h);
                }
                if(getAnimationTick()>=16&&flag) {
                    int r = new Random().nextInt(1024);
                    if(r<=360) {
                        setAnimationTick(0);
                        setAnimationState(24);
                    }else if (r<=540) {
                        setAnimationTick(0);
                        setAnimationState(25);
                    }
                }
                if(getAnimationTick()>=18) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 27:
                if(getAnimationTick()<=4){
                    this.moveToTarget(0.8f);
                }else{
                    this.getNavigation().stop();
                }
                if(getAnimationTick()==6) {
                    this.playSound(this.getAttackSound(), 0.2f,1.0f);
                }
                if(getAnimationTick()==17) {
                    this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.2f)*this.getLookAngle().x,
                                    0.25,
                                    (1.2f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE)*1.0f, 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    this.level().addFreshEntity(h);
                }
                if(getAnimationTick()>=21&&flag) {
                    setAnimationTick(0);
                    setAnimationState(29);
                }
                if(getAnimationTick()>=24) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 28:
                if(getAnimationTick()<=4){
                    this.moveToTarget(0.8f);
                }else{
                    this.getNavigation().stop();
                }
                if(getAnimationTick()==6) {
                    this.playSound(this.getAttackSound(), 0.2f,1.0f);
                }
                if(getAnimationTick()==14) {
                    this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.2f)*this.getLookAngle().x,
                                    0.25,
                                    (1.2f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE)*1.0f, 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    this.level().addFreshEntity(h);
                }
                if(getAnimationTick()>=16&&flag) {
                    setAnimationTick(0);
                    setAnimationState(23);
                }
                if(getAnimationTick()>=18) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 29:
                if(getAnimationTick()<=4){
                    this.moveToTarget(0.8f);
                }else{
                    this.getNavigation().stop();
                }
                if(getAnimationTick()==6) {
                    this.playSound(this.getAttackSound(), 0.2f,1.0f);
                }
                if(getAnimationTick()==14) {
                    this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.2f)*this.getLookAngle().x,
                                    0.25,
                                    (1.2f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE)*1.0f, 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    this.level().addFreshEntity(h);
                }
                if(getAnimationTick()>=16&&flag) {
                    setAnimationTick(0);
                    setAnimationState(23);
                }
                if(getAnimationTick()>=18) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 30:
                if(getAnimationTick()<=4){
                    this.moveToTarget(0.8f);
                }else{
                    this.getNavigation().stop();
                }
                if(getAnimationTick()==6) {
                    this.playSound(this.getAttackSound(), 0.2f,1.0f);
                }
                if(getAnimationTick()==14) {
                    this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.2f)*this.getLookAngle().x,
                                    0.25,
                                    (1.2f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE)*1.1f, 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    this.level().addFreshEntity(h);
                }
                if(getAnimationTick()>=22) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 31:
                if(getAnimationTick()<=4){
                    this.moveToTarget(0.8f);
                }else{
                    this.getNavigation().stop();
                }
                if(getAnimationTick()==6) {
                    this.playSound(this.getAttackSound(), 0.2f,1.0f);
                }
                if(getAnimationTick()==16) {
                    this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.2f)*this.getLookAngle().x,
                                    0.25,
                                    (1.2f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE)*1.2f, 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    this.level().addFreshEntity(h);
                }
                if(getAnimationTick()>=24) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 32:
                if(getAnimationTick()<=1){
                    this.moveToTarget(0.8f);
                }else{
                    this.getNavigation().stop();
                }
                if(getAnimationTick()==6) {
                    this.playSound(this.getAttackSound(), 0.2f,1.0f);
                }
                if(getAnimationTick()==8) {
                    //this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.2f)*this.getLookAngle().x,
                                    0.25,
                                    (1.2f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE)*0.4f, 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    this.level().addFreshEntity(h);
                }
                if(getAnimationTick()>=14&&flag) {
                    int r = new Random().nextInt(1024);
                    if(r<=120) {
                        setAnimationTick(0);
                        setAnimationState(21);
                    }else if (r<=240) {
                        setAnimationTick(0);
                        setAnimationState(23);
                    }else if (r<=360) {
                        setAnimationTick(0);
                        setAnimationState(24);
                    }else if (r<=480) {
                        setAnimationTick(0);
                        setAnimationState(27);
                    }else if (r<=600) {
                        setAnimationTick(0);
                        setAnimationState(31);
                    } else if (r<=720) {
                        setAnimationTick(0);
                        setAnimationState(33);
                    }
                }
                if(getAnimationTick()>=16) {
                    int r = new Random().nextInt(1024);
                    if(r<=120) {
                        setAnimationTick(0);
                        setAnimationState(21);
                    }else if (r<=240) {
                        setAnimationTick(0);
                        setAnimationState(23);
                    }else if (r<=360) {
                        setAnimationTick(0);
                        setAnimationState(24);
                    }else if (r<=480) {
                        setAnimationTick(0);
                        setAnimationState(27);
                    }else if (r<=600) {
                        setAnimationTick(0);
                        setAnimationState(31);
                    } else if (r<=720) {
                        setAnimationTick(0);
                        setAnimationState(33);
                    }else {
                        setAnimationTick(0);
                        setAnimationState(0);
                    }
                }
                break;
            case 33:
                if(getAnimationTick()==1) {
                    this.setDeltaMovement(0,1.0,0);
                    if(this.getTarget()==null) {
                        aimVec = this.getLookAngle().normalize();
                    }else{
                        aimVec = this.getTarget().position().add(pos.scale(-1)).normalize();
                    }
                }
                if(getAnimationTick()==2) {
                    this.playSound(this.getAttackSound(), 0.2f,1.0f);
                    if(this.aimVec!=null) {
                        this.setDeltaMovement(this.aimVec.normalize().add(0,0.05f,0).scale(0.5));
                    }else {
                        this.setDeltaMovement(this.getLookAngle().normalize().add(0,0.05f,0).scale(0.5));
                    }
                }
                if(getAnimationTick()==12) {
                    this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.2f)*this.getLookAngle().x,
                                    0.25,
                                    (1.2f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE)+2.0f, 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    this.level().addFreshEntity(h);
                }
                if(getAnimationTick()>18) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 34:
                if(getAnimationTick()<=1){
                    this.moveToTarget(0.8f);
                }else{
                    this.getNavigation().stop();
                }
                if(getAnimationTick()==6) {
                    if(this.getTarget()==null) {
                        aimVec = this.getLookAngle().normalize();
                    }else{
                        aimVec = this.getTarget().position().add(pos.scale(-1)).normalize();
                    }
                }
                if(getAnimationTick()==16) {
                    this.playSound(this.getAttackSound(), 0.2f,1.0f);
                }
                if(getAnimationTick()==18) {
                    //this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
                    DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
                            this.position().add((1.2f)*this.getLookAngle().x,
                                    0.25,
                                    (1.2f)*this.getLookAngle().z),
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE)*0.4f, 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    this.level().addFreshEntity(h);
                }
                if(getAnimationTick()>=18&&getAnimationTick()<=22) {
                    for (LivingEntity localTarget : this.level().getEntitiesOfClass(LivingEntity.class, new AABB(pos.x(), pos.y() - 0.5f, pos.z(), pos.x() + aim.x() * 2.5f, pos.y() + 2.5f, pos.z() + aim.z() * 2.5f))) {
                        if ((this.getTarget() != null) && (localTarget == this.getTarget())) {
                            localTarget.startRiding(this);
                            this.setTarget(localTarget);
                            setAnimationTick(0);
                            setAnimationState(35);
                        }
                    }
                }
                if(getAnimationTick()>=30) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
            case 35:
                this.getNavigation().stop();
                if(this.getTarget()!=null&&!this.getTarget().isDeadOrDying()){
                    this.getTarget().startRiding(this);
                }
                if(getAnimationTick()%10==0) {
                    //this.hurtTime = 22;
                    //this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
                    if(this.getFirstPassenger()!=null){
                        this.getFirstPassenger().hurt(this.level().damageSources().magic(),(float)this.getAttributeValue(Attributes.ATTACK_DAMAGE)*0.1f+1);
                        if(this.getFirstPassenger() instanceof Player player){
                            if(player.totalExperience>0) {
                                player.giveExperiencePoints(-10);
                            }
                        }
                    }
                }
                if(this.getTarget()!=null&&(this.getTarget().isDeadOrDying()||this.getTarget().distanceTo(this)>=4)){
                    setAnimationTick(0);
                    setAnimationState(36);
                }
                if(getAnimationTick()>=120) {
                    setAnimationTick(0);
                    setAnimationState(36);
                }
                break;
            case 36:
                this.getNavigation().stop();
                /*if(this.getAnimationTick()<=8) {
                    if (this.getTarget() != null&&!this.getTarget().isDeadOrDying()&&this.getTarget().distanceTo(this)<=4) {
                        this.getTarget().startRiding(this);
                    }
                }else{*/
                if(this.getAnimationTick()==8) {
                    if (this.getFirstPassenger() != null) {
                        Entity e = this.getFirstPassenger();
                        //this.getFirstPassenger().hurt(this.level().damageSources().magic(),(float)this.getAttributeValue(Attributes.ATTACK_DAMAGE)*0.2f);
                        this.getFirstPassenger().stopRiding();
                        if(e instanceof LivingEntity liv){
                            if(this.aimVec!=null) {
                                liv.knockback(5.6f,aimVec.x()+0.25f, aimVec.z()+0.25f);

                            }else{
                                liv.knockback(5.6f,aim.x()+0.25f, aim.z()+0.25f);
                            }


                        }

                    }
                }
                //}
                if(getAnimationTick()>=15) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
        }
    }

    @Override
    public Vec3 getPassengerRidingPosition(Entity p_299288_) {
        return super.getPassengerRidingPosition(p_299288_);
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(Entity e, EntityDimensions dim, float f) {
        if(this.aimVec!=null) {
            return new Vec3((float) (-aimVec.normalize().x()*0.6f), dim.height()*0.5f, (float) (-aimVec.normalize().z()*0.6f));
        }else {
            return new Vec3((float) (-this.getLookAngle().normalize().x()*0.6f), dim.height()*0.5f, (float) (-this.getLookAngle().normalize().z()*0.6f));
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

    protected void checkForAttack(double distance, double reach, Darkwraith mob, int ticksUntilNextAttack){
        if (distance <= reach && ticksUntilNextAttack <= 0) {
            int r = new Random().nextInt(3600);
            if(r<=360) {
                mob.setAnimationState(21);
            }else if(r<=520){
                mob.setAnimationState(22);
            }else if(r<=840){
                mob.setAnimationState(23);
            }else if(r<=1024){
                mob.setAnimationState(24);
            }else if(r<=1200){
                mob.setAnimationState(25);
            }else if(r<=1440){
                mob.setAnimationState(26);
            }else if(r<=1800){
                mob.setAnimationState(27);
            }else if(r<=2000){
                mob.setAnimationState(28);
            }else if(r<=2100){
                mob.setAnimationState(29);
            }else if(r<=2400){
                mob.setAnimationState(30);
            }else if(r<=2700){
                mob.setAnimationState(31);
            }else if(r<=3000){
                mob.setAnimationState(32);
            }else if(r<=3200){
                mob.setAnimationState(33);
            }else{
                mob.setAnimationState(34);
            }
        }
        if (distance <= reach*4 && ticksUntilNextAttack <= 0) {
            int r = new Random().nextInt(2048);
            if(r<=36) {
                mob.setAnimationState(33);
            }
        }
    }

    protected void updateShieldState(){
        if(this.getEntityState()==0) {
            this.setAnimationState(11);
        }else{
            this.setAnimationState(12);
        }
    }


    @Override
    public boolean isBlocking() {
        return this.getEntityState()!=0&&this.getAnimationState()==0;
    }

    public class AttackGoal extends Goal {


        private final double speedModifier = 1.0f;
        private final boolean followingTargetEvenIfNotSeen = true;
        protected final Darkwraith mob;
        private Path path;
        private double pathedTargetX;
        private double pathedTargetY;
        private double pathedTargetZ;
        private int ticksUntilNextPathRecalculation;
        private int ticksUntilNextAttack;
        private int ticksUntilShieldUpdateCheck;
        private long lastCanUseCheck;
        private int failedPathFindingPenalty = 0;
        private boolean canPenalize = false;
        private int lastCanUpdateStateCheck;




        public AttackGoal(Darkwraith entityIn) {
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
            this.ticksUntilShieldUpdateCheck = 20;
            this.lastCanUpdateStateCheck = 150;
            int r = this.mob.getRandom().nextInt(2048);
            if(r<=1040) {
                this.mob.setCombatState(1);
                this.stop();
            }
            this.mob.setAnimationState(0);
            int r1 = this.mob.getRandom().nextInt(2048);
            if(r1<=360) {
                if(this.mob.getEntityState()==0||r1<=120){
                    this.mob.updateShieldState();
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


        @Override
        public void tick() {
            LivingEntity target = this.mob.getTarget();
            double distance = this.mob.distanceToSqr(target.getX(), target.getY(), target.getZ());
            double reach = this.getAttackReachSqr(target);

            this.doMovement(target, reach);
            this.mob.checkForAttack(distance, reach, this.mob, this.ticksUntilNextAttack);

            this.lastCanUpdateStateCheck = Math.max(this.lastCanUpdateStateCheck-1, 0);
            if(this.lastCanUpdateStateCheck<=0){
                int r = this.mob.getRandom().nextInt(2048);
                if(r<=450) {
                    this.mob.setCombatState(1);
                    this.stop();
                }
                this.lastCanUpdateStateCheck=200;
            }
            this.ticksUntilShieldUpdateCheck = Math.max(this.ticksUntilShieldUpdateCheck-1, 0);
            if(this.ticksUntilShieldUpdateCheck<=0){
                int r = new Random().nextInt(2048);
                if(r<=60) {
                    this.mob.updateShieldState();
                }
                this.ticksUntilShieldUpdateCheck=200;
            }

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
        protected final Darkwraith mob;
        private Path path;
        private double pathedTargetX;
        private double pathedTargetY;
        private double pathedTargetZ;
        private int ticksUntilNextPathRecalculation;
        private int ticksUntilNextAttack;
        private int ticksUntilShieldUpdateCheck;
        private long lastCanUseCheck;
        private int failedPathFindingPenalty = 0;
        private boolean canPenalize = false;

        private int lastCanUpdateStateCheck;




        public AttackGoalRunning(Darkwraith entityIn) {
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
            this.ticksUntilShieldUpdateCheck = 20;
            this.lastCanUpdateStateCheck = 666;
            this.mob.setAnimationState(0);
            int r1 = this.mob.getRandom().nextInt(2048);
            if(r1<=240) {
                if(this.mob.getEntityState()==0||r1<=80){
                    this.mob.updateShieldState();
                }
            }
        }
        @Override
        public void stop() {
            LivingEntity livingentity = this.mob.getTarget();
            if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingentity)) {
                this.mob.setTarget((LivingEntity)null);
            }
            int r = this.mob.getRandom().nextInt(2048);
            if(r<=650 || livingentity==null || !livingentity.isAlive() || livingentity.isSpectator()
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
            this.mob.checkForAttack(distance, reach, this.mob, ticksUntilNextAttack);


            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
            this.lastCanUpdateStateCheck = Math.max(this.lastCanUpdateStateCheck-1, 0);
            if(this.lastCanUpdateStateCheck<=0){
                int r = this.mob.getRandom().nextInt(2048);
                if(r<=480) {
                    this.mob.setCombatState(0);
                    this.stop();
                }
                this.lastCanUpdateStateCheck=200;
            }
            this.ticksUntilShieldUpdateCheck = Math.max(this.ticksUntilShieldUpdateCheck-1, 0);
            if(this.ticksUntilShieldUpdateCheck<=0){
                int r = new Random().nextInt(2048);
                if(r<=60) {
                    this.mob.updateShieldState();
                }
                this.ticksUntilShieldUpdateCheck=200;
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
        protected void resetAttackCooldown() {
            this.ticksUntilNextAttack = 20;
        }
        protected double getAttackReachSqr(LivingEntity p_179512_1_) {
            return (double)(this.mob.getBbWidth() * 16.0F * this.mob.getBbWidth());
        }
    }
}


