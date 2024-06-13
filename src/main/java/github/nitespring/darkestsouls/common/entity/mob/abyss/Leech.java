package github.nitespring.darkestsouls.common.entity.mob.abyss;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.common.entity.projectile.TrashParasites;
import github.nitespring.darkestsouls.common.entity.projectile.spell.MagmaBurstParent;
import github.nitespring.darkestsouls.common.entity.util.DamageHitboxEntity;
import github.nitespring.darkestsouls.core.init.EffectInit;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.EnumSet;

public class Leech extends DarkestSoulsAbstractEntity implements GeoEntity{

	protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
	public int lastUpdatedStateTick = 0;
	Vec3 aim;
	private static final EntityDimensions CRAWLING_BB = new EntityDimensions(0.9f, 0.8f,0.6f, EntityAttachments.createDefault(0.9f, 0.8f), false);
	public Leech(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
		super(p_21683_, p_21684_);
		this.xpReward=12;
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
		data.add(new AnimationController<>(this, "stun_controller", 0, this::hitStunPredicate));
		}

	private <E extends GeoAnimatable> PlayState hitStunPredicate(AnimationState<E> event) { /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
        }*/
		if(this.shouldResetAnimation()){
			event.getController().forceAnimationReset();
			this.setResetAnimation(false);
		}
		if(hitStunTicks>0) {
		event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.leech.hit"));
		}else {
		event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.leech.new"));
		}
		return PlayState.CONTINUE;
	}

	
	private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) { /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
        }*/
		int animState = this.getAnimationState();
		int combatState = this.getCombatState();
		if(this.isDeadOrDying()) {
			event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.leech.death"));
		}else {
			switch(animState) {
			case 1:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.leech.stun"));
				break;
			case 21:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.leech.attack1"));
				break;
			case 22:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.leech.attack2"));
				break;
			case 23:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.leech.attack3"));
				break;
			case 24:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.leech.attack4"));
				break;
			case 25:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.leech.attack5"));
				break;
			default:
				if(this.isInWater()) {
					event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.leech.slither"));
				}else if(!this.onGround()) {
					event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.leech.fall"));
				}else if(!(event.getLimbSwingAmount() > -0.012 && event.getLimbSwingAmount() < 0.012f)){
					switch(combatState) {
					case 1:
						event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.leech.slither"));
					 break;
					default:
						event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.leech.walk"));
						break;
					}
				}else{
					event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.leech.idle"));
				}
				break;
				}
			}
        return PlayState.CONTINUE;
	}

	@Override
	protected void registerGoals() {

		//this.goalSelector.addGoal(0, new Leech.FloatGoal(this));
		this.goalSelector.addGoal(1, new Leech.AttackGoal(this));
		this.goalSelector.addGoal(1, new Leech.AttackGoalCrouched(this));


		super.registerGoals();
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.SQUID_AMBIENT;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource p_21239_) {
		return SoundEvents.CAMEL_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.DROWNED_DEATH;
	}

	@Override
	protected boolean isAffectedByFluids() {return true;}
	@Override
	public boolean canDrownInFluidType(FluidType type) {return false;}
	@Override
	public boolean canSwimInFluidType(FluidType type) {return true;}

	@Override
	protected void checkInsideBlocks() {
		super.checkInsideBlocks();
	}


	public boolean checkSpawnObstruction(LevelReader p_30348_) {
		return p_30348_.isUnobstructed(this);
	}

	@Override
	public int getMaxPoise() {return 28;}
	@Override
	public int getBloodResistance() {return 6;}

	@Override
	public EntityDimensions getDefaultDimensions(Pose p_21047_) {

		if(this.getCombatState()==1||this.isInWater()) {
			return CRAWLING_BB;
		}else {
			return this.getType().getDimensions();
		}
	}

	@Override
	public void tick() {

		if(this.isInWater()&&this.getTarget()!=null&&this.getAnimationState()==0){
			this.playSound(SoundEvents.SALMON_FLOP);
			double d = 0.25f*0.75f*this.getAttributeValue(Attributes.MOVEMENT_SPEED)*1.5;
			Vec3 aim = this.getTarget().position().add(this.position().scale(-1));
			this.setDeltaMovement(new Vec3(d*aim.x,d*aim.y+0.05f,d*aim.z));
			//this.mob.getLookControl().setLookAt(this.mob.getTarget().position());
			//Vec3 mov = this.mob.getDeltaMovement();
			//this.mob.setYRot((float) (Mth.atan2(mov.x, mov.z) * (double) (180F / (float) Math.PI)));
			this.setYRot((float) -(Mth.atan2(aim.x, aim.z) * (double) (180F / (float) Math.PI)));
			//this.mob.setYRot((float) (Mth.atan2(aim.x, aim.z) * (double) (180F / (float) Math.PI)));
			//this.mob.setXRot((float) (Mth.atan2(aim.y, aim.horizontalDistance()) * (double) (180F / (float) Math.PI)));
			//this.mob.hurtMarked=true;
		}



		if(this.getAnimationState()!=0&&!this.isDeadOrDying()) {
			this.playAnimation();
		}
		if(this.tickCount%5==0){this.refreshDimensions();}
		super.tick();
	}

	protected void playAnimation() {
		increaseAnimationTick(1);
		//this.getNavigation().stop();
		boolean flag = this.getTarget()!=null;
		boolean flag1 = flag && this.distanceTo(this.getTarget())<=6.0f;

		switch(this.getAnimationState()) {
			case 1:
				this.getNavigation().stop();
				if(getAnimationTick()>=50) {
					this.resetPoiseHealth();
					this.getNavigation().stop();
					setAnimationTick(0);
					setAnimationState(0);
				}
				break;
			//Attack
			case 21:
				this.moveToTarget();
				if(getAnimationTick()==5) {
					this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
					this.playSound(SoundEvents.SQUID_DEATH);
					DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
							this.position().add((1.0f)*this.getLookAngle().x,
									0.25,
									(1.0f)*this.getLookAngle().z),
							(float)this.getAttributeValue(Attributes.ATTACK_DAMAGE), 3, 1.25f, 8f,0,this.getTarget());
					h.setOwner(this);
					h.setTarget(this.getTarget());
					h.setHitboxType(3);
					this.level().addFreshEntity(h);
				}
				if(getAnimationTick()>=10) {
					int r=this.getRandom().nextInt(80);
					if(!flag||!flag1||r>60){
						setAnimationState(0);
						setAnimationTick(0);
					}else{
						if(r<=10) {
							setAnimationState(22);
							setAnimationTick(0);
						}else if(r<=20){
							setAnimationState(23);
							setAnimationTick(0);
						}else if(r<=30){
							setAnimationState(25);
							setAnimationTick(0);
						}else if(r<=40){
							setAnimationState(0);
							setAnimationTick(0);
						}
					}
				}
				break;
			case 22:
				this.moveToTarget();
				if(getAnimationTick()==5) {
					this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
					this.playSound(SoundEvents.SQUID_DEATH);
					DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
							this.position().add((1.0f)*this.getLookAngle().x,
									0.25,
									(1.0f)*this.getLookAngle().z),
							(float)this.getAttributeValue(Attributes.ATTACK_DAMAGE), 3, 1.25f, 8f,0,this.getTarget());
					h.setOwner(this);
					h.setTarget(this.getTarget());
					h.setHitboxType(3);
					this.level().addFreshEntity(h);
				}
				if(getAnimationTick()>=10) {
					int r=this.getRandom().nextInt(80);
					if(!flag||!flag1||r>60){
						setAnimationState(0);
						setAnimationTick(0);
					}else{
						if(r<=10) {
							setAnimationState(21);
							setAnimationTick(0);
						}else if(r<=20){
							setAnimationState(23);
							setAnimationTick(0);
						}else if(r<=30){
							setAnimationState(25);
							setAnimationTick(0);
						}else if(r<=40){
							setAnimationState(0);
							setAnimationTick(0);
						}
					}
				}
				break;
			case 23:
				if(getAnimationTick()==6) {
					this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
					this.playSound(SoundEvents.SQUID_DEATH);
					DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
							this.position().add((1.0f)*this.getLookAngle().x,
									0.25,
									(1.0f)*this.getLookAngle().z),
							(float)this.getAttributeValue(Attributes.ATTACK_DAMAGE)+1, 3, 1.25f, 8f,0,this.getTarget());
					h.setOwner(this);
					h.setTarget(this.getTarget());
					h.setHitboxType(3);
					this.level().addFreshEntity(h);
				}
				if(getAnimationTick()>=12) {
					int r=this.getRandom().nextInt(80);
					if(!flag||!flag1||r>60){
						setAnimationState(0);
						setAnimationTick(0);
					}else{
						if(r<=20){
							setAnimationState(25);
							setAnimationTick(0);
						}else if(r<=40){
							setAnimationState(0);
							setAnimationTick(0);
						}
					}
				}
				break;
			case 24:
				Level levelIn = this.level();
				Vec3 pos = this.position();

				if(getAnimationTick()==10){
					if(this.getTarget()==null) {
						aim = this.getLookAngle().normalize();
					}else{
						aim = this.getTarget().position().add(pos.scale(-1)).normalize();
					}


				}
				if(getAnimationTick()==16) {

					this.playSound(SoundEvents.PLAYER_SPLASH_HIGH_SPEED);
					this.playSound(SoundEvents.SQUID_DEATH);
					for(int i=0; i<=8; i++) {
						double angle = (i-4)*Math.PI/16;

						TrashParasites e = new TrashParasites(EntityInit.PARASITES.get(), levelIn);
						e.setDamage((float)this.getAttributeValue(Attributes.ATTACK_DAMAGE));
						e.setLifeTicks(46);
						double x = (aim.x*Math.cos(angle) - aim.z * Math.sin(angle));
						double y = (this.getRandom().nextFloat()-0.5f);
						double z = (aim.z*Math.cos(angle) + aim.x * Math.sin(angle));
						e.setPos(pos.add(1.5*x, 1.75+1.5*y, 1.5*z));
						e.xPower = 0.15 * (aim.x+x);
						e.yPower = 0.15 * (aim.y+y);
						e.zPower = 0.15 * (aim.z+z);
						e.setDamage(Math.max(1f,(float)this.getAttributeValue(Attributes.ATTACK_DAMAGE)-4));
						e.setOwner(this);
						levelIn.addFreshEntity(e);
					}
				}
				if(getAnimationTick()>=22) {
					setAnimationTick(0);
					setAnimationState(0);
				}
				break;
			case 25:
				if(getAnimationTick()==20) {
					this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
					this.playSound(SoundEvents.SQUID_DEATH);
					DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
							this.position().add((1.0f)*this.getLookAngle().x,
									0.25,
									(1.0f)*this.getLookAngle().z),
							(float)this.getAttributeValue(Attributes.ATTACK_DAMAGE)+2, 3, 1.25f, 8f,0,this.getTarget());
					h.setOwner(this);
					h.setTarget(this.getTarget());
					h.setHitboxType(3);
					this.level().addFreshEntity(h);
				}
				if(getAnimationTick()>=26) {
					setAnimationTick(0);
						setAnimationState(0);
				}
				break;
		}
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


		private final double speedModifier = 1.5f;
		private final boolean followingTargetEvenIfNotSeen = true;
		protected final Leech mob;
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

		public AttackGoal(Leech entityIn) {
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
			this.ticksUntilNextAttack = 8;
			this.lastCanUpdateStateCheck = 200;
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
			this.lastCanUpdateStateCheck = Math.max(this.lastCanUpdateStateCheck-1, 0);
			if(this.lastCanUpdateStateCheck<=0){
				int r = this.mob.getRandom().nextInt(2048);
				if(r<=128) {
					this.mob.setCombatState(1);
				}
				this.lastCanUpdateStateCheck=160;
			}
		}


		@SuppressWarnings("unused")
		private void checkForPreciseAttack() {
			if (this.ticksUntilNextAttack <= 0) {

				this.mob.setAnimationState(24);
			}

		}


		@SuppressWarnings("unused")
		protected void doMovement(LivingEntity livingentity, Double d0) {
			this.mob.getLookControl().setLookAt(this.mob.getTarget(), 10.0F, 15.0F);
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
				if(r<=400) {

					this.mob.setAnimationState(21);

				}else if(r<=800){

					this.mob.setAnimationState(22);

				}else if(r<=1200){

					this.mob.setAnimationState(23);

				}else if(r<=1600){

					this.mob.setAnimationState(25);

				}
			}

			if (distance <= reach*4 && this.ticksUntilNextAttack <= 0) {
				int r = this.mob.getRandom().nextInt(2048);
				if(r<=128) {
					this.mob.setAnimationState(24);
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

	public class AttackGoalCrouched extends Goal {


		private final double speedModifier = 2.0f;
		private final boolean followingTargetEvenIfNotSeen = true;
		protected final Leech mob;
		private Path path;
		private double pathedTargetX;
		private double pathedTargetY;
		private double pathedTargetZ;
		private int ticksUntilNextPathRecalculation;
		private int ticksUntilNextAttack;
		private long lastCanUseCheck;
		private int failedPathFindingPenalty = 0;
		private boolean canPenalize = false;
		private int lastCanUpdateStateCheck = 0;




		public AttackGoalCrouched(Leech entityIn) {
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
			this.ticksUntilNextAttack = 6;
			this.lastCanUpdateStateCheck = 240;
			this.mob.setAnimationState(0);
		}
		@Override
		public void stop() {
			LivingEntity livingentity = this.mob.getTarget();
			if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingentity)) {
				this.mob.setTarget((LivingEntity)null);
			}
			this.mob.setCombatState(0);
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
				if(r<=64) {
					this.mob.setCombatState(0);
				}
				this.lastCanUpdateStateCheck=200;
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
			this.mob.getLookControl().setLookAt(this.mob.getTarget(), 10.0F, 15.0F);
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
				if(r<=400) {

					this.mob.setAnimationState(21);

				}else if(r<=800){

					this.mob.setAnimationState(22);

				}else if(r<=1200){

					this.mob.setAnimationState(23);

				}else if(r<=1600){

					this.mob.setAnimationState(25);

				}
			}
			if (distance <= reach*4 && this.ticksUntilNextAttack <= 0) {
				int r = this.mob.getRandom().nextInt(2048);
				if(r<=128) {
					this.mob.setAnimationState(24);
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
	public class FloatGoal extends Goal {
		private final Mob mob;

		public FloatGoal(Mob p_25230_) {
			this.mob = p_25230_;
			this.setFlags(EnumSet.of(Goal.Flag.JUMP));
			p_25230_.getNavigation().setCanFloat(true);
		}

		public boolean canUse() {
			return this.mob.getTarget()!=null && this.mob.isInWater() && this.mob.getFluidHeight(FluidTags.WATER) > this.mob.getFluidJumpThreshold() || this.mob.isInLava() || this.mob.isInFluidType((fluidType, height) -> this.mob.canSwimInFluidType(fluidType) && height > this.mob.getFluidJumpThreshold());
		}

		public boolean requiresUpdateEveryTick() {
			return true;
		}

		public void tick() {
			if (this.mob.getRandom().nextFloat() < 0.8F && this.mob.getTarget()!=null && this.mob.getTarget().getY()>=this.mob.getY()) {
				this.mob.getJumpControl().jump();
			}

		}
	}

}
