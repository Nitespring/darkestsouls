package github.nitespring.darkestsouls.common.entity.mob;


import java.util.EnumSet;
import java.util.UUID;

import javax.annotation.Nullable;

import github.nitespring.darkestsouls.config.CommonConfig;
import net.minecraft.core.particles.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;

import org.joml.Vector3f;

public abstract class DarkestSoulsAbstractEntity extends PathfinderMob {


	protected int hitStunTicks = 0;

	protected final WaterBoundPathNavigation waterNavigation;
	protected final GroundPathNavigation groundNavigation;

	public abstract boolean isBoss();
	//protected int poiseHealth;

	private static final EntityDataAccessor<Integer> ANIMATION_STATE = SynchedEntityData.defineId(DarkestSoulsAbstractEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> ANIMATION_TICK = SynchedEntityData.defineId(DarkestSoulsAbstractEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Boolean> SHOULD_RESET_ANIMATION = SynchedEntityData.defineId(DarkestSoulsAbstractEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Integer> COMBAT_STATE = SynchedEntityData.defineId(DarkestSoulsAbstractEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> ENTITY_PHASE = SynchedEntityData.defineId(DarkestSoulsAbstractEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> TEAM = SynchedEntityData.defineId(DarkestSoulsAbstractEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> POISE_HEALTH = SynchedEntityData.defineId(DarkestSoulsAbstractEntity.class, EntityDataSerializers.INT);
	private final ServerBossEvent bossEvent = (ServerBossEvent) (new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS));


	@Nullable
	private LivingEntity owner;
	@Nullable
	private UUID ownerUUID;

	public DarkestSoulsAbstractEntity(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
		super(p_21683_, p_21684_);
		this.waterNavigation = new WaterBoundPathNavigation(this, p_21684_);
		this.groundNavigation = new GroundPathNavigation(this, p_21684_);
	}

	public int getAnimationState() {return this.getEntityData().get(ANIMATION_STATE);}

	public void setAnimationState(int anim) {
		this.getEntityData().set(ANIMATION_STATE, anim);
	}
	public int getAnimationTick() {return this.getEntityData().get(ANIMATION_TICK);}
	public void setAnimationTick(int anim) {
		this.getEntityData().set(ANIMATION_TICK, anim);
	}

	public boolean shouldResetAnimation() {return this.getEntityData().get(SHOULD_RESET_ANIMATION);}
	public void setResetAnimation(boolean anim) {
		this.getEntityData().set(SHOULD_RESET_ANIMATION, anim);
	}

	public void increaseAnimationTick(int amount) {
		this.getEntityData().set(ANIMATION_TICK, this.getAnimationTick()+amount);
	}

	public int getCombatState() {
		return this.getEntityData().get(COMBAT_STATE);
	}

	public void setCombatState(int anim) {
		this.getEntityData().set(COMBAT_STATE, anim);
	}

	public int getEntityState() {
		return this.getEntityData().get(ENTITY_PHASE);
	}

	public void setEntityState(int anim) {
		this.getEntityData().set(ENTITY_PHASE, anim);
	}

	public int getDSTeam() {
		return this.getEntityData().get(TEAM);
	}

	public void setDSTeam(int anim) {
		this.getEntityData().set(TEAM, anim);
	}

	public int getBloodResistance() {
		return 10;
	}

	public int getMaxPoise() {
		return 20;
	}

	public int getPoiseHealth() {
		return this.getEntityData().get(POISE_HEALTH);
	}

	public void setPoiseHealth(int i) {
		this.getEntityData().set(POISE_HEALTH, i);
		//System.out.println("Poise: " + i + "/" + this.getMaxPoise());
	}

	public void damagePoiseHealth(int i) {
		this.setPoiseHealth(this.getPoiseHealth() - i);
		//System.out.println("damaged poise " + i);
	}

	public void healPoiseHealth(int i) {
		this.setPoiseHealth(this.getPoiseHealth() + i);
	}

	public void resetPoiseHealth() {
		this.setPoiseHealth(this.getMaxPoise());
	}

	public int getStunAnimation() {
		return 1;
	}

	public void setStunAnimation() {
		this.setAnimationState(this.getStunAnimation());
	}


	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		 super.defineSynchedData(builder);
		builder.define(ANIMATION_STATE, 0);
		builder.define(ANIMATION_TICK, 0);
		builder.define(SHOULD_RESET_ANIMATION, false);
		builder.define(COMBAT_STATE, 0);
		builder.define(ENTITY_PHASE, 0);
		builder.define(TEAM, getDSDefaultTeam());
		builder.define(POISE_HEALTH, getMaxPoise());
	}

	protected abstract int getDSDefaultTeam();

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.setAnimationState(tag.getInt("AnimationId"));
		this.setCombatState(tag.getInt("CombatStateId"));
		this.setEntityState(tag.getInt("EntityStateId"));
		this.setDSTeam(tag.getInt("DSTeam"));
		if (tag.hasUUID("Owner")) {
			this.ownerUUID = tag.getUUID("Owner");
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("AnimationId", this.getAnimationState());
		tag.putInt("CombatStateId", this.getCombatState());
		tag.putInt("EntityStateId", this.getEntityState());
		tag.putInt("DSTeam", this.getDSTeam());
		if (this.ownerUUID != null) {
			tag.putUUID("Owner", this.ownerUUID);
		}
	}

	@Nullable
	public LivingEntity getOwner() {
		return this.owner;
	}


	public void setOwner(LivingEntity p_33995_) {
		this.owner = p_33995_;
		this.ownerUUID = p_33995_.getUUID();
	}

	@Override
	protected boolean shouldDespawnInPeaceful() {
		return true;
	}

	@Override
	public void startSeenByPlayer(ServerPlayer p_184178_1_) {
		super.startSeenByPlayer(p_184178_1_);
		if (this.isBoss()) {
			this.bossEvent.addPlayer(p_184178_1_);
		}
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer p_184203_1_) {
		super.stopSeenByPlayer(p_184203_1_);
		this.bossEvent.removePlayer(p_184203_1_);
	}

	@Override
	public void tick() {
		float h = this.getHealth();
		this.bossEvent.setProgress(h / this.getMaxHealth());

		if (hitStunTicks >= -1) {
			hitStunTicks--;
		}

		if (this.getEntityData().get(POISE_HEALTH) <= -1) {
			this.setStunAnimation();

			//System.out.println("should Stun");
		}

		super.tick();
	}

	@SuppressWarnings("deprecation")
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
		spawnGroupData=super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
		if (!this.getPersistentData().contains("DSTeam")) {
			this.setDSTeam(this.getDSDefaultTeam());
		}
		if (this.owner != null) {
			if (this.owner.getPersistentData().contains("DSTeam")) {
				this.setDSTeam(this.owner.getPersistentData().getInt("DSTeam"));
			} else if (this.owner instanceof Player) {
				this.setDSTeam(4);
			}
		}
		return spawnGroupData;
	}

	@Override
	public boolean isAlliedTo(Entity e) {
		if (this.getOwner() != null) {
			return this.getOwner().isAlliedTo(e);
		} else {
			if (this.getPersistentData().contains("DSTeam")) {
				int teamOwner = this.getPersistentData().getInt("DSTeam");
				if (e.getPersistentData().contains("DSTeam")) {
					int teamTarget = e.getPersistentData().getInt("DSTeam");

					return teamOwner == teamTarget || super.isAlliedTo(e);
				} else {

					return super.isAlliedTo(e);

				}
			} else {
				return super.isAlliedTo(e);
			}
		}
	}

	@Override
	public boolean hurt(DamageSource source, float f) {
		Entity e = source.getEntity();
		if(!(this.isBlocking()&&this.isDamageSourceBlocked(source))) {
			if (f > 0 && (e != null && !(e instanceof DarkestSoulsAbstractEntity && ((DarkestSoulsAbstractEntity) e).getOwner() == this))) {
				if (hitStunTicks <= 0) {
					hitStunTicks = 3;
				}
				if(this.getAnimationState()==this.getStunAnimation()){
					this.playSound(SoundEvents.BLAZE_HURT, 0.4f, 1.0f);
				}

				/*
				if(!source.is(DamageTypes.PLAYER_ATTACK)&&!(source.getEntity()!=null && source.getEntity() instanceof Player)) {
					float poiseDmgMod = 1;

					if (source.is(DamageTypes.ARROW) || source.is(DamageTypes.MAGIC)) {
						poiseDmgMod = poiseDmgMod / 2;
					}

					if (source.is(DamageTypes.EXPLOSION)) {
						poiseDmgMod = poiseDmgMod * 2;
					}

					if (source.is(DamageTypes.DRY_OUT) || source.is(DamageTypes.DRY_OUT)) {
						poiseDmgMod = 0;
					}

					int finalPoiseDmg = (int) (f * poiseDmgMod);

					this.damagePoiseHealth(finalPoiseDmg);
				}
				*/
				if(CommonConfig.do_blood_particles.get()) {
					this.spawnBloodParticles(source, f);
				}
			}


		}
		return super.hurt(source, f);
	}

	public void spawnBloodParticles(DamageSource source, float f){
		ParticleOptions blood = this.getBloodParticles();
		if(source.is(DamageTypes.DROWN)||source.is(DamageTypes.IN_WALL)){blood=ParticleTypes.BUBBLE;}
		if(source.is(DamageTypes.FREEZE)||source.is(DamageTypes.STARVE)||source.is(DamageTypes.DRY_OUT)){blood=ParticleTypes.DAMAGE_INDICATOR;}
		if(source.is(DamageTypes.MAGIC)||source.is(DamageTypes.INDIRECT_MAGIC)){blood=ParticleTypes.SOUL_FIRE_FLAME;}
		if(source.is(DamageTypes.FIREBALL)||source.is(DamageTypes.IN_FIRE)||source.is(DamageTypes.ON_FIRE)||source.is(DamageTypes.EXPLOSION)||source.is(DamageTypes.LIGHTNING_BOLT)||source.is(DamageTypes.LAVA)||source.is(DamageTypes.HOT_FLOOR)||source.is(DamageTypes.DRAGON_BREATH)||source.is(DamageTypes.UNATTRIBUTED_FIREBALL)){blood=ParticleTypes.SMOKE;}
		if(source.is(DamageTypes.WITHER)||source.is(DamageTypes.WITHER_SKULL)){blood=new DustParticleOptions(new Vector3f(0f,0f,0f),0.5f);}

		float width = this.getBbWidth() * 0.5f;
		float height = this.getBbHeight() * 0.5f;
		float size = width*height*0.5f;
		Vec3 pos = new Vec3(this.getX(), this.getY() + height, this.getZ());
		Level world = this.level();

		RandomSource rng = this.getRandom();
		for (int i = 0; i < 5+20*size*Math.sqrt(f+4)/10; ++i) {

			Vec3 off = new Vec3((rng.nextDouble() * width - width / 2)*f/4, (rng.nextDouble() * height - height / 2)*f/4,
					(rng.nextDouble() * width - width / 2)*f/4);
			if(world instanceof ServerLevel) {
				((ServerLevel) world).sendParticles( blood, pos.x+off.x, pos.y+0.5f+off.y, pos.z+off.z, 5,  off.x, off.y + 0.05D, off.z, 0.05D + size*0.006);

			}
		}

	}
	public ParticleOptions getBloodParticles(){
		return new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Items.NETHER_WART_BLOCK));
	}

	@Override
	public float maxUpStep() {
		return 1.0f;
	}

	public class CopyOwnerTargetGoal extends TargetGoal {
		private final TargetingConditions copyOwnerTargeting = TargetingConditions.forNonCombat().ignoreLineOfSight().ignoreInvisibilityTesting();

		public CopyOwnerTargetGoal(PathfinderMob p_34056_) {
			super(p_34056_, false);
		}

		public boolean canUse() {
			return DarkestSoulsAbstractEntity.this.getOwner() != null && DarkestSoulsAbstractEntity.this.getOwner() instanceof Mob && ((Mob) DarkestSoulsAbstractEntity.this.getOwner()).getTarget() != null && this.canAttack(((Mob) DarkestSoulsAbstractEntity.this.getOwner()).getTarget(), this.copyOwnerTargeting);
		}

		public void start() {
			DarkestSoulsAbstractEntity.this.setTarget(((Mob) DarkestSoulsAbstractEntity.this.getOwner()).getTarget());
			super.start();
		}
	}

	@Override
	protected void registerGoals() {

		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Villager.class, true));

		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, LivingEntity.class, 1.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));

		this.targetSelector.addGoal(2, new DarkestSoulsAbstractEntity.CopyOwnerTargetGoal(this));


		this.goalSelector.addGoal(3, new DarkestSoulsAbstractEntity.WaterAvoidingRandomStrollGoal(this, 0.8D));

	}

	public class RandomStrollGoal extends Goal {
		public static final int DEFAULT_INTERVAL = 120;
		protected final DarkestSoulsAbstractEntity mob;
		protected double wantedX;
		protected double wantedY;
		protected double wantedZ;
		protected final double speedModifier;
		protected int interval;
		protected boolean forceTrigger;
		private final boolean checkNoActionTime;

		public RandomStrollGoal(DarkestSoulsAbstractEntity p_25734_, double p_25735_) {
			this(p_25734_, p_25735_, 120);
		}

		public RandomStrollGoal(DarkestSoulsAbstractEntity p_25737_, double p_25738_, int p_25739_) {
			this(p_25737_, p_25738_, p_25739_, true);
		}

		public RandomStrollGoal(DarkestSoulsAbstractEntity p_25741_, double p_25742_, int p_25743_, boolean p_25744_) {
			this.mob = p_25741_;
			this.speedModifier = p_25742_;
			this.interval = p_25743_;
			this.checkNoActionTime = p_25744_;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		public boolean canUse() {
			if(this.mob.getAnimationState()!=0) {
				return false;
			} else {
				if (this.mob.hasControllingPassenger()) {
					return false;
				} else {
					if (!this.forceTrigger) {
						if (this.checkNoActionTime && this.mob.getNoActionTime() >= 100) {
							return false;
						}

						if (this.mob.getRandom().nextInt(reducedTickDelay(this.interval)) != 0) {
							return false;
						}
					}

					Vec3 vec3 = this.getPosition();
					if (vec3 == null) {
						return false;
					} else {
						this.wantedX = vec3.x;
						this.wantedY = vec3.y;
						this.wantedZ = vec3.z;
						this.forceTrigger = false;
						return true;
					}
				}
			}
		}

		@Nullable
		protected Vec3 getPosition() {
			return DefaultRandomPos.getPos(this.mob, 10, 7);
		}

		public boolean canContinueToUse() {
			return !this.mob.getNavigation().isDone() && !this.mob.hasControllingPassenger();
		}

		public void start() {
			this.mob.getNavigation().moveTo(this.wantedX, this.wantedY, this.wantedZ, this.speedModifier);
		}

		public void stop() {
			this.mob.getNavigation().stop();
			super.stop();
		}

		public void trigger() {
			this.forceTrigger = true;
		}

		public void setInterval(int p_25747_) {
			this.interval = p_25747_;
		}
	}
	public class WaterAvoidingRandomStrollGoal extends DarkestSoulsAbstractEntity.RandomStrollGoal {
		public static final float PROBABILITY = 0.001F;
		protected final float probability;

		public WaterAvoidingRandomStrollGoal(DarkestSoulsAbstractEntity p_25987_, double p_25988_) {
			this(p_25987_, p_25988_, 0.001F);
		}

		public WaterAvoidingRandomStrollGoal(DarkestSoulsAbstractEntity p_25990_, double p_25991_, float p_25992_) {
			super(p_25990_, p_25991_);
			this.probability = p_25992_;
		}

		@Nullable
		protected Vec3 getPosition() {
			if (this.mob.isInWaterOrBubble()) {
				Vec3 vec3 = LandRandomPos.getPos(this.mob, 15, 7);
				return vec3 == null ? super.getPosition() : vec3;
			} else {
				return this.mob.getRandom().nextFloat() >= this.probability ? LandRandomPos.getPos(this.mob, 10, 7) : super.getPosition();
			}
		}
	}
}
