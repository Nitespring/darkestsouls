package github.nitespring.darkestsouls.common.entity.mob;


import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public abstract class DarkestSoulsAbstractEntity extends PathfinderMob{

	
	protected int hitStunTicks=0;
	public abstract boolean isBoss();
	
	private static final EntityDataAccessor<Integer> ANIMATION_STATE = SynchedEntityData.defineId(DarkestSoulsAbstractEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> COMBAT_STATE = SynchedEntityData.defineId(DarkestSoulsAbstractEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> ENTITY_PHASE = SynchedEntityData.defineId(DarkestSoulsAbstractEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> TEAM = SynchedEntityData.defineId(DarkestSoulsAbstractEntity.class, EntityDataSerializers.INT);
	private final ServerBossEvent bossEvent = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS));
	
	@Nullable
	private LivingEntity owner;
	@Nullable
	private UUID ownerUUID;
	
	public DarkestSoulsAbstractEntity(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
		super(p_21683_, p_21684_);
	}

	public int getAnimationState() {return this.entityData.get(ANIMATION_STATE);}
	public void setAnimationState(int anim) {this.entityData.set(ANIMATION_STATE, anim);}
	 
	public int getCombatState() {return this.entityData.get(COMBAT_STATE);}
	public void setCombatState(int anim) {this.entityData.set(COMBAT_STATE, anim);}
	 
	public int getEntityState() {return this.entityData.get(ENTITY_PHASE);}
	public void setEntityState(int anim) {this.entityData.set(ENTITY_PHASE, anim);}
	 
	public int getDSTeam() {return this.entityData.get(TEAM);}
	public void setDSTeam(int anim) {this.entityData.set(TEAM, anim);}
	
	@Override
	 protected void defineSynchedData() {
	      super.defineSynchedData();
	      this.entityData.define(ANIMATION_STATE, 0);
	      this.entityData.define(COMBAT_STATE, 0);   
	      this.entityData.define(ENTITY_PHASE, 0);
	      this.entityData.define(TEAM, getDSDefaultTeam());
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
	      this.ownerUUID= p_33995_.getUUID();
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
	 
	          if(hitStunTicks>=-1) {
	     		 hitStunTicks--;
	     	 }
	          super.tick(); 
     		}
     
     @SuppressWarnings("deprecation")
	@Override
     public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, @Nullable SpawnGroupData p_21437_, @Nullable CompoundTag p_21438_) {

	    if(!this.serializeNBT().contains("DSTeam")) {
	    	   this.setDSTeam(this.getDSDefaultTeam());
	       }
	    if(this.owner!=null) {
	    	   if(this.owner.serializeNBT().contains("DSTeam")) {
	    		   this.setDSTeam(this.owner.serializeNBT().getInt("DSTeam"));
	    	   }else if(this.owner instanceof Player) {
	        	   this.setDSTeam(4);
	    	   }
	       }
        return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
     }
     
     @Override
     public boolean isAlliedTo(Entity e) {
     	if(this.getOwner()!=null) {
     		return this.getOwner().isAlliedTo(e);
     	}else {
 	    	if(this.serializeNBT().contains("DSTeam")) {
 	    		int teamOwner = this.serializeNBT().getInt("DSTeam");
 			    	if(e.serializeNBT().contains("DSTeam")) {	
 				    	 int teamTarget = e.serializeNBT().getInt("DSTeam");
 				    	 
 					    		return teamOwner == teamTarget || super.isAlliedTo(e);
 			    	}else {
 			    		
 				    			return super.isAlliedTo(e);
 				    		
 			    	}
 	    	}else {
 	    		return super.isAlliedTo(e);
 	    	}
     	}
     }
     
     @Override
 	public boolean hurt(DamageSource source, float f) {
    	 Entity e = source.getEntity();
 		if(f>0 && (e != null && !(e instanceof DarkestSoulsAbstractEntity && ((DarkestSoulsAbstractEntity)e).getOwner() == this))) {
 		 if(hitStunTicks<=0) {
 		 hitStunTicks=5;
 		 }
 		 }
 		 
 		 
 		return super.hurt(source, f);
 	}
     
     public class CopyOwnerTargetGoal extends TargetGoal {
	      private final TargetingConditions copyOwnerTargeting = TargetingConditions.forNonCombat().ignoreLineOfSight().ignoreInvisibilityTesting();

	      public CopyOwnerTargetGoal(PathfinderMob p_34056_) {
	         super(p_34056_, false);
	      }

	      public boolean canUse() {
	         return DarkestSoulsAbstractEntity.this.getOwner() != null && DarkestSoulsAbstractEntity.this.getOwner() instanceof Mob && ((Mob)DarkestSoulsAbstractEntity.this.getOwner()).getTarget() != null && this.canAttack(((Mob)DarkestSoulsAbstractEntity.this.getOwner()).getTarget(), this.copyOwnerTargeting);
	      }

	      public void start() {
	    	  DarkestSoulsAbstractEntity.this.setTarget(((Mob)DarkestSoulsAbstractEntity.this.getOwner()).getTarget());
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

		    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	     
		    this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.8D));
		      
		}
	
	
}
