package github.nitespring.darkestsouls.common.entity.mob.abyss;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.common.entity.projectile.TrashParasites;
import github.nitespring.darkestsouls.common.entity.projectile.TrashPoison;
import github.nitespring.darkestsouls.common.entity.util.DamageHitboxEntity;
import github.nitespring.darkestsouls.core.init.EntityInit;
import github.nitespring.darkestsouls.core.init.SoundInit;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
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

public class SewerCentipede extends DarkestSoulsAbstractEntity implements GeoEntity{

	protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);


	Vec3 aim;

	public SewerCentipede(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {

		super(p_21683_, p_21684_);
		this.xpReward=16;
	}

	@Override
	public boolean isBoss() {return false;}
	@Override
	public int getDSDefaultTeam() {return 0;}
	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {return this.factory;}
	
	@Override
	public void registerControllers(ControllerRegistrar data) {
		data.add(new AnimationController<>(this, "main_controller", 5, this::predicate));
		data.add(new AnimationController<>(this, "limbs_controller", 2, this::limbsPredicate));
		data.add(new AnimationController<>(this, "stun_controller", 0, this::hitStunPredicate));
		}

	private <E extends GeoAnimatable> PlayState hitStunPredicate(AnimationState<E> event) { /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
        }*/

		
		if(hitStunTicks>0) {
		event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.sewer_centipede.hit"));
		}else {
		event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.sewer_centipede.new"));
		}
		return PlayState.CONTINUE;
	}
	
	private <E extends GeoAnimatable> PlayState limbsPredicate(AnimationState<E> event) { /*if(this.shouldResetAnimation()){
            event.getController().forceAnimationReset();
        }*/
		int animState = this.getAnimationState();
		if(this.isDeadOrDying()) {
			event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.sewer_centipede.limbs_death"));
		}else {
			switch(animState) {
			case 99:
				event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.sewer_centipede.limbs"));
				break;
			default:
				 
				event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.sewer_centipede.limbs_idle"));
					 
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
			event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.sewer_centipede.death"));
		}else {
			switch(animState) {
			case 1:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.sewer_centipede.stun"));
				break;
			case 21:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.sewer_centipede.attack1"));
				break;
			case 22:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.sewer_centipede.attack2"));
				break;
			case 23:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.sewer_centipede.attack3"));
				break;
			case 24:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.sewer_centipede.attack4"));
				break;
			case 25:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.sewer_centipede.attack5"));
				break;
			case 26:
					event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.sewer_centipede.attack6"));
					break;
			case 27:
					event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.sewer_centipede.attack7"));
					break;
			case 28:
					event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.sewer_centipede.attack8"));
					break;
			case 29:
					event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.sewer_centipede.attack9"));
					break;
			default:
				switch(combatState) {
				case 1:
				 if(!(event.getLimbSwingAmount() > -0.012 && event.getLimbSwingAmount() < 0.012f)){
					event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.sewer_centipede.walk2"));
					}else {
					event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.sewer_centipede.idle2"));
					}
				 break;
				default:
					if(!(event.getLimbSwingAmount() > -0.012 && event.getLimbSwingAmount() < 0.012f)){
						event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.sewer_centipede.walk"));
					}else {
						event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.sewer_centipede.idle"));
					}
				}
				break;
				}
			}
        return PlayState.CONTINUE;
	}

	@Override
	protected void registerGoals() {

		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));



		this.targetSelector.addGoal(1, new CopyOwnerTargetGoal(this));

		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Villager.class, true));

		this.goalSelector.addGoal(1, new SewerCentipede.AttackGoal(this));

		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, LivingEntity.class, 1.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(3, new DarkestSoulsAbstractEntity.RandomStrollGoal(this, 0.8D));
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource p_21239_) {
		return SoundInit.SEWER_CENTIPEDE_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundInit.SEWER_CENTIPEDE_IDLE.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundInit.SEWER_CENTIPEDE_DEATH.get();
	}
	@Override
	protected void playStepSound(BlockPos p_33804_, BlockState p_33805_) {
		this.playSound(SoundInit.SEWER_CENTIPEDE_STEP.get(), 0.15F, 1.0F);
	}

	@Override
	public void makeStuckInBlock(BlockState p_33796_, Vec3 p_33797_) {
		//if (!(p_33796_.is(Blocks.COBWEB)||p_33796_.is(Blocks.WATER))) {
		if (!p_33796_.is(Blocks.COBWEB)) {
			super.makeStuckInBlock(p_33796_, p_33797_);
		}

	}

	@Override
	public double getFluidMotionScale(FluidType type) {
		return 0.0f;
	}

	@Override
	protected float getWaterSlowDown() {
		return 1.0f;
	}

	@Override
	protected boolean isAffectedByFluids() {return true;}
	@Override
	public boolean canDrownInFluidType(FluidType type) {return false;}
	@Override
	public boolean canSwimInFluidType(FluidType type) {return true;}


	@Override
	public int getMaxPoise() {return 28;}

	@Override
	public int getBloodResistance() {return 12;}

	@Override
	public void tick() {

		if(this.hasEffect(MobEffects.POISON)){this.removeEffect(MobEffects.POISON);}

		if(this.getAnimationState()!=0&&!this.isDeadOrDying()) {this.playAnimation();}

		super.tick();

	}
	protected void playAnimation() {
		increaseAnimationTick(1);
		//this.getNavigation().stop();
		boolean flag = this.getTarget()!=null;
		boolean flag1 = flag && this.distanceTo(this.getTarget())<=6.0f;

		switch(this.getAnimationState()) {
			case 1:this.getNavigation().stop();
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
				if(getAnimationTick()==6) {
					this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
					DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
							this.position().add((1.0f)*this.getLookAngle().x,
									0.25,
									(1.0f)*this.getLookAngle().z),
							(float)this.getAttributeValue(Attributes.ATTACK_DAMAGE), 3, 1.25f, 8f,0,this.getTarget());
					h.setOwner(this);
					h.setTarget(this.getTarget());
					this.level().addFreshEntity(h);
				}
				if(getAnimationTick()>=12) {
					setAnimationTick(0);
					int r=this.getRandom().nextInt(80);
					if(!flag||!flag1||r>60){
						setAnimationState(0);
					}else{
						if(r<=10) {
							setAnimationState(25);
						}else if(r<=20){
							setAnimationState(24);
						}else if(r<=30){
							setAnimationState(23);
						}else if(r<=40){
							setAnimationState(26);
						}else if(r<=50){
							setAnimationState(22);
						}else{
							setAnimationState(27);
						}
					}
				}
				break;
			case 22:
				this.moveToTarget();
				if(getAnimationTick()==6) {
					this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
					DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
							this.position().add((1.0f)*this.getLookAngle().x,
									0.25,
									(1.0f)*this.getLookAngle().z),
							(float)this.getAttributeValue(Attributes.ATTACK_DAMAGE), 3, 1.25f, 8f,0,this.getTarget());
					h.setOwner(this);
					h.setTarget(this.getTarget());
					this.level().addFreshEntity(h);
				}
				if(getAnimationTick()>=12) {
					setAnimationTick(0);
					int r=this.getRandom().nextInt(80);
					if(!flag||!flag1||r>60){
						setAnimationState(0);
					}else{
						if(r<=10) {
							setAnimationState(25);
						}else if(r<=20){
							setAnimationState(24);
						}else if(r<=30){
							setAnimationState(23);
						}else if(r<=40){
							setAnimationState(26);
						}else if(r<=50){
							setAnimationState(21);
						}else{
							setAnimationState(27);
						}
					}
				}
				break;
			case 23:
				this.moveToTarget();
				if(getAnimationTick()==6) {
					this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
					DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
							this.position().add((1.0f)*this.getLookAngle().x,
									0.25,
									(1.0f)*this.getLookAngle().z),
							(float)this.getAttributeValue(Attributes.ATTACK_DAMAGE), 3, 1.25f, 8f,0,this.getTarget());
					h.setOwner(this);
					h.setTarget(this.getTarget());
					this.level().addFreshEntity(h);
				}
				if(getAnimationTick()>=12) {
					setAnimationTick(0);
					int r=this.getRandom().nextInt(80);
					if(!flag||!flag1||r>60){
						setAnimationState(0);
					}else{
						if(r<=10) {
							setAnimationState(25);
						}else if(r<=20){
							setAnimationState(24);
						}else if(r<=30){
							setAnimationState(21);
						}else if(r<=40){
							setAnimationState(26);
						}else if(r<=50){
							setAnimationState(22);
						}else{
							setAnimationState(27);
						}
					}
				}
				break;
			case 24:
				this.moveToTarget();
				if(getAnimationTick()==6) {
					this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
					DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
							this.position().add((1.0f)*this.getLookAngle().x,
									0.25,
									(1.0f)*this.getLookAngle().z),
							(float)this.getAttributeValue(Attributes.ATTACK_DAMAGE), 3, 1.25f, 8f,0,this.getTarget());
					h.setOwner(this);
					h.setTarget(this.getTarget());
					this.level().addFreshEntity(h);
				}
				if(getAnimationTick()>=12) {
					setAnimationTick(0);
					int r=this.getRandom().nextInt(80);
					if(!flag||!flag1||r>60){
						setAnimationState(0);
					}else{
						if(r<=10) {
							setAnimationState(25);
						}else if(r<=20){
							setAnimationState(21);
						}else if(r<=30){
							setAnimationState(23);
						}else if(r<=40){
							setAnimationState(26);
						}else if(r<=50){
							setAnimationState(22);
						}else{
							setAnimationState(27);
						}
					}
				}
				break;
			case 25:
				this.moveToTarget();
				if(getAnimationTick()==6) {
					this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
					DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
							this.position().add((1.0f)*this.getLookAngle().x,
									0.25,
									(1.0f)*this.getLookAngle().z),
							(float)this.getAttributeValue(Attributes.ATTACK_DAMAGE), 3, 1.25f, 8f,0,this.getTarget());
					h.setOwner(this);
					h.setTarget(this.getTarget());
					this.level().addFreshEntity(h);
				}
				if(getAnimationTick()>=12) {
					setAnimationTick(0);
					int r=this.getRandom().nextInt(80);
					if(!flag||!flag1||r>60){
						setAnimationState(0);
					}else{
						if(r<=10) {
							setAnimationState(21);
						}else if(r<=20){
							setAnimationState(24);
						}else if(r<=30){
							setAnimationState(23);
						}else if(r<=40){
							setAnimationState(26);
						}else if(r<=50){
							setAnimationState(22);
						}else{
							setAnimationState(27);
						}
					}
				}
				break;
			case 26:
				this.moveToTarget();
				if(getAnimationTick()==6) {
					this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
					DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
							this.position().add((1.0f)*this.getLookAngle().x,
									0.25,
									(1.0f)*this.getLookAngle().z),
							(float)this.getAttributeValue(Attributes.ATTACK_DAMAGE), 3, 1.25f, 8f,0,this.getTarget());
					h.setOwner(this);
					h.setTarget(this.getTarget());
					this.level().addFreshEntity(h);
				}
				if(getAnimationTick()>=12) {
					setAnimationTick(0);
					int r=this.getRandom().nextInt(80);
					if(!flag||!flag1||r>60){
						setAnimationState(0);
					}else{
						if(r<=10) {
							setAnimationState(25);
						}else if(r<=20){
							setAnimationState(24);
						}else if(r<=30){
							setAnimationState(23);
						}else if(r<=40){
							setAnimationState(26);
						}else if(r<=50){
							setAnimationState(22);
						}else{
							setAnimationState(27);
						}
					}
				}
				break;
			case 27:
				this.moveToTarget();
				if(getAnimationTick()==5) {
					this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
					DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
							this.position().add((1.0f)*this.getLookAngle().x,
									0.25,
									(1.0f)*this.getLookAngle().z),
							(float)this.getAttributeValue(Attributes.ATTACK_DAMAGE), 3, 1.25f, 8f,0,this.getTarget());
					h.setOwner(this);
					h.setTarget(this.getTarget());
					this.level().addFreshEntity(h);
				}
				if(getAnimationTick()>=15) {
					setAnimationTick(0);
					int r=this.getRandom().nextInt(280);
					if(!flag||!flag1||r>60){
						setAnimationState(0);
					}else{
						if(r<=10) {
							setAnimationState(25);
						}else if(r<=20){
							setAnimationState(24);
						}else if(r<=30){
							setAnimationState(23);
						}else if(r<=40){
							setAnimationState(26);
						}else if(r<=50){
							setAnimationState(22);
						}else{
							setAnimationState(21);
						}
					}
				}
				break;
			case 28:
				this.getNavigation().stop();
				if(getAnimationTick()==6) {
					this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);
					DamageHitboxEntity h = new DamageHitboxEntity(EntityInit.HITBOX.get(), level(),
							this.position().add((1.0f)*this.getLookAngle().x,
									0.25,
									(1.0f)*this.getLookAngle().z),
							(float)this.getAttributeValue(Attributes.ATTACK_DAMAGE), 3, 1.25f, 8f,0,this.getTarget());
					h.setOwner(this);
					h.setTarget(this.getTarget());
					this.level().addFreshEntity(h);
				}
				if(getAnimationTick()>=12) {
					setAnimationTick(0);
						setAnimationState(0);
				}
				break;
			case 29:
				Level levelIn = this.level();
				Vec3 pos = this.position();
				if(getAnimationTick()<=3){this.moveToTarget();}

				if(getAnimationTick()==4){
					if(this.getTarget()==null) {
						aim = this.getLookAngle().normalize();
					}else{
						aim = this.getTarget().position().add(pos.scale(-1)).normalize();
					}


				}
				if(getAnimationTick()==6) {

					this.playSound(SoundEvents.PLAYER_SPLASH_HIGH_SPEED);
					this.playSound(SoundEvents.SQUID_DEATH);
					for(int i=0; i<=8; i++) {
						double angle = (i-4)*Math.PI/16;

						TrashPoison e = new TrashPoison(EntityInit.VOMIT.get(), levelIn);
						e.setDamage((float)this.getAttributeValue(Attributes.ATTACK_DAMAGE));
						e.setLifeTicks(46);
						double x = (aim.x*Math.cos(angle) - aim.z * Math.sin(angle));
						double y = (this.getRandom().nextFloat()-0.5f);
						double z = (aim.z*Math.cos(angle) + aim.x * Math.sin(angle));
						e.setPos(pos.add(1.5*x, 1.25+1.5*y, 1.5*z));
						e.xPower = 0.15 * (aim.x+x);
						e.yPower = 0.15 * (aim.y+y);
						e.zPower = 0.15 * (aim.z+z);
						e.setDamage(Math.max(1f,(float)this.getAttributeValue(Attributes.ATTACK_DAMAGE)-4));
						e.setOwner(this);
						levelIn.addFreshEntity(e);
					}
				}
				if(getAnimationTick()>=12) {
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


		private final double speedModifier = 1.2f;
		private final boolean followingTargetEvenIfNotSeen = true;
		protected final SewerCentipede mob;
		private Path path;
		private double pathedTargetX;
		private double pathedTargetY;
		private double pathedTargetZ;
		private int ticksUntilNextPathRecalculation;
		private int ticksUntilNextAttack;
		private long lastCanUseCheck;
		private int failedPathFindingPenalty = 0;
		private boolean canPenalize = false;




		public AttackGoal(SewerCentipede entityIn) {
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
				int r = this.mob.getRandom().nextInt(3072);
				if(r<=200) {

					this.mob.setAnimationState(26);

				}else if(r<=500){

					this.mob.setAnimationState(24);

				}else if(r<=900){

					this.mob.setAnimationState(21);

				}else if(r<=1100){

					this.mob.setAnimationState(22);

				}else if(r<=1300){

					this.mob.setAnimationState(23);

				}else if(r<=1900){

					this.mob.setAnimationState(25);

				}else if(r<=2400){

					this.mob.setAnimationState(27);

				}else{

					this.mob.setAnimationState(28);

				}
			}
			if (distance <= reach*4 && this.ticksUntilNextAttack <= 0) {
				int r = this.mob.getRandom().nextInt(2048);
				if(r<=128) {
					this.mob.setAnimationState(29);
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
