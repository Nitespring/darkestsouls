package github.nitespring.darkestsouls.common.entity.mob.abyss;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.common.entity.util.DamageHitboxEntity;
import github.nitespring.darkestsouls.core.init.EntityInit;
import github.nitespring.darkestsouls.core.init.SoundInit;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.Path;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.EnumSet;

public class MonstruosityOfSin extends DarkestSoulsAbstractEntity implements GeoEntity{

	protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);

	
	public MonstruosityOfSin(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
		super(p_21683_, p_21684_);
		this.setAnimationState(11);
		this.xpReward=20;
	}

	@Override
	public boolean isBoss() {return false;}
	@Override
	public int getDSDefaultTeam() {return 0;}
	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {return this.factory;}
	
	@Override
	public void registerControllers(ControllerRegistrar data) {
		data.add(new AnimationController<>(this, "main_controller", 4, this::predicate));
		data.add(new AnimationController<>(this, "fingers_controller", 4, this::fingersPredicate));
		data.add(new AnimationController<>(this, "stun_controller", 0, this::hitStunPredicate));
		}

	private <E extends GeoAnimatable> PlayState hitStunPredicate(AnimationState<E> event) { /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
        }*/
		
		if(hitStunTicks>0) {
		event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.sin.hit"));
		}else {
		event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.sin.new"));	
		}
		return PlayState.CONTINUE;
	}
	
	private <E extends GeoAnimatable> PlayState fingersPredicate(AnimationState<E> event) { /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
        }*/
		int animState = this.getAnimationState();
		if(this.isDeadOrDying()) {
			event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.sin.fingers_death"));
		}else {
			switch(animState) {
			case 21:
				event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.sin.fingers_punch"));
				break;
			default:
				 
				event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.sin.fingers_idle"));	 
					 
				break;
			}
		}
		
        return PlayState.CONTINUE;
	}
	
	private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) { /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
        }*/
		int animState = this.getAnimationState();
		int combatState = this.getCombatState();
		if(this.isDeadOrDying()) {
			event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.sin.death"));
		}else {
			switch(animState) {
			case 1:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.sin.stun"));
				break;
			case 11:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.sin.crawl"));
				break;
			case 12:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.sin.sit"));
				break;
			case 21:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.sin.attack_punch"));
				break;
			case 22:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.sin.attack_punch_getup"));
				break;
			case 23:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.sin.attack_slam"));
				break;
			default:
				switch(combatState) {
				case 1:
				 if(!(event.getLimbSwingAmount() > -0.012 && event.getLimbSwingAmount() < 0.012f)){
					event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.sin.walk"));	 
					}else {
					event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.sin.idle2"));
					}
				 break;
				default: 
					event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.sin.idle1"));
				}
				break;
				}
			}
        return PlayState.CONTINUE;
	}

	@Override
	protected void registerGoals() {

		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));



		this.targetSelector.addGoal(1, new DarkestSoulsAbstractEntity.CopyOwnerTargetGoal(this));

		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Villager.class, true));

		this.goalSelector.addGoal(1, new MonstruosityOfSin.AttackGoal(this));

		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, LivingEntity.class, 1.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
	}

	@Override
	public int getMaxPoise() {return 46;}

	@Override
	public int getBloodResistance() {return 5;}
	@Override
	public void tick() {

		if(this.hasEffect(MobEffects.POISON)){this.removeEffect(MobEffects.POISON);}

		if(this.getAnimationState()!=0&&!this.isDeadOrDying()) {
			this.playAnimation();
		}
		super.tick();

	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundInit.SIN_IDLE.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundInit.SIN_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource p_21239_) {
		return SoundInit.SIN_HURT.get();
	}

	protected void playAnimation() {
		increaseAnimationTick(1);
		this.getNavigation().stop();
		switch(this.getAnimationState()) {
			case 1:this.getNavigation().stop();
				this.getNavigation().stop();
				if(getAnimationTick()>=50) {
					this.getNavigation().stop();
					setAnimationTick(0);
					this.resetPoiseHealth();
					setAnimationState(0);
				}
				break;
			case 11:
				if(getAnimationTick()>=20) {
					this.playSound(SoundInit.SIN_BOOM.get());
					setAnimationTick(0);
					setCombatState(1);
					setAnimationState(0);
				}
				break;
			case 12:
				if(getAnimationTick()>=25) {
					setAnimationTick(0);
					setCombatState(0);
					setAnimationState(0);
				}
				break;
			//Attack
			case 21:
				if(getAnimationTick()==6) {
					this.playSound(SoundInit.SIN_SCREAM.get());
				}
				if(getAnimationTick()==8) {
					this.playSound(SoundInit.SIN_BOOM.get());
					DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
							this.position().add((1.4f)*this.getLookAngle().x,
									0.25,
									(1.4f)*this.getLookAngle().z),
							(float)this.getAttributeValue(Attributes.ATTACK_DAMAGE), 5, 2.25f, 0.75f,0,this.getTarget());
					h.setOwner(this);
					this.level().addFreshEntity(h);


				}
				if(getAnimationTick()>=18) {
					setAnimationTick(0);
					setAnimationState(22);
				}
				break;
			case 22:
				if(getAnimationTick()>=35) {
					this.playSound(SoundInit.SIN_BOOM.get());
					setAnimationTick(0);
					setAnimationState(0);
				}
				break;
			case 23:
				if(getAnimationTick()==10) {
					this.playSound(SoundInit.SIN_SCREAM.get());
				}

				if(getAnimationTick()==16) {
					this.playSound(SoundInit.SIN_BOOM.get());

					DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX_LARGE.get(), level(),
							this.position().add((1.0f)*this.getLookAngle().x,
									0.25,
									(1.0f)*this.getLookAngle().z),
							(float)this.getAttributeValue(Attributes.ATTACK_DAMAGE)*1.2f, 5);
					h.setOwner(this);
					h.setTarget(this.getTarget());
					this.level().addFreshEntity(h);


				}
				if(getAnimationTick()>=32) {
					setAnimationTick(0);
					setAnimationState(0);
				}
				break;
		}
	}

	public class AttackGoal extends Goal {


		private final double speedModifier = 1.0f;
		private final boolean followingTargetEvenIfNotSeen = true;
		protected final MonstruosityOfSin mob;
		private Path path;
		private double pathedTargetX;
		private double pathedTargetY;
		private double pathedTargetZ;
		private int ticksUntilNextPathRecalculation;
		private int ticksUntilNextAttack;
		private long lastCanUseCheck;
		private int failedPathFindingPenalty = 0;
		private boolean canPenalize = false;




		public AttackGoal(MonstruosityOfSin entityIn) {
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
			this.ticksUntilNextAttack = 60;

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
			if(getCombatState()==1) {
				this.doMovement(target, reach);
				this.checkForAttackFromCrawling(distance, reach);

			}else{
				this.mob.getNavigation().stop();
				int r = this.mob.getRandom().nextInt(2048);
				this.checkForAttackFromSitting(distance, reach);
				if(r<=50) {
					this.mob.setAnimationState(11);
				}
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







		protected void checkForAttackFromCrawling(double distance, double reach){
			if (distance <= reach && this.ticksUntilNextAttack <= 0) {
				int r = this.mob.getRandom().nextInt(2048);
				if(r<=500) {

					this.mob.setAnimationState(21);

				}else if(r<=1000){

					this.mob.setAnimationState(23);

				}
			}
		}

		protected void checkForAttackFromSitting(double distance, double reach){
			if (distance <= reach && this.ticksUntilNextAttack <= 0) {
				int r = this.mob.getRandom().nextInt(2048);
				if(r<=400) {

					this.mob.setAnimationState(23);

				}
			}
		}

		protected void resetAttackCooldown() {
			this.ticksUntilNextAttack = 20;
		}


		protected double getAttackReachSqr(LivingEntity p_179512_1_) {
			return (double)(this.mob.getBbWidth() * 6.0F * this.mob.getBbWidth());
		}

	}



}
