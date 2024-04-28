package github.nitespring.darkestsouls.common.entity.mob.abyss;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.common.entity.projectile.throwable.ThrowingKnifeEntity;
import github.nitespring.darkestsouls.common.entity.util.DamageHitboxEntity;
import github.nitespring.darkestsouls.core.init.EntityInit;
import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
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

import java.util.Random;

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
    protected SoundEvent getAttackSound() {
        return SoundEvents.SKELETON_AMBIENT;
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
        boolean flag = this.getTarget()!=null && this.distanceTo(this.getTarget())<=2;
        this.getNavigation().stop();
        switch(this.getAnimationState()) {
            case 1:
                if(getAnimationTick()>=55) {
                    this.getNavigation().stop();
                    setAnimationTick(0);
                    this.resetPoiseHealth();
                    setAnimationState(0);
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
                            (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE)*0.9f, 5);
                    h.setOwner(this);
                    h.setTarget(this.getTarget());
                    h.setHitboxType(4);
                    this.level().addFreshEntity(h);
                }
                if(getAnimationTick()>=14&&flag) {
                    int r = this.getRandom().nextInt(2048);
                    if (r <= 480) {
                        setAnimationTick(0);
                        setAnimationState(20);
                    }else if (r <= 720) {
                        setAnimationTick(0);
                        setAnimationState(27);
                    }
                }
                if(getAnimationTick()>=16) {
                    setAnimationTick(0);
                    setAnimationState(0);
                }
                break;
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
}
