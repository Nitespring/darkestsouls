package github.nitespring.darkestsouls.common.entity.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DamageHitboxEntity extends Entity{
	
	@Nullable
	private LivingEntity owner;
	@Nullable
	private UUID ownerUUID;
	@Nullable
	private LivingEntity target;
	private int maxTargets=0; //if 0 there is no max number of targets
	private int delayTicks = 1;
	private float damage = 4.0f;
	private float hitboxScaleAbsolute=1.0f;
	private float hitboxScaleHeight=1.0f;
	private List<LivingEntity> hitEntities = new ArrayList<LivingEntity>();

	public DamageHitboxEntity(EntityType<?> e, Level level) {
		super(e, level);
	}
	public DamageHitboxEntity(EntityType<?> e, Level level, Vec3 pos, float dmg) {
		this(e, level);
		this.setPos(pos);
		this.damage=dmg;
	}
	public DamageHitboxEntity(EntityType<?> e, Level level, Vec3 pos, float dmg, int delayTicks) {
		this(e, level, pos, dmg);
		this.delayTicks = delayTicks;
	}
	public DamageHitboxEntity(EntityType<?> e, Level level, Vec3 pos, float dmg, int delayTicks, float scale) {
		this(e, level, pos, dmg, delayTicks);
		this.hitboxScaleAbsolute=scale;
	}
	public DamageHitboxEntity(EntityType<?> e, Level level, Vec3 pos, float dmg, int delayTicks, float scale, float height) {
		this(e, level, pos, dmg, delayTicks, scale);
		this.hitboxScaleHeight=height;
	}
	public DamageHitboxEntity(EntityType<?> e, Level level, Vec3 pos, float dmg, int delayTicks, float scale, float height, int maxTargets) {
		this(e, level, pos, dmg, delayTicks, scale, height);
		this.maxTargets=maxTargets;
	}
	public DamageHitboxEntity(EntityType<?> e, Level level, Vec3 pos, float dmg, int delayTicks, float scale, float height, int maxTargets, LivingEntity target) {
		this(e, level, pos, dmg, delayTicks, scale, height, maxTargets);
		this.target=target;
	}
	@Override
	protected void defineSynchedData() {}
	@Override
	protected void readAdditionalSaveData(CompoundTag p_20052_) {
	      if (p_20052_.hasUUID("Owner")) {
	         this.ownerUUID = p_20052_.getUUID("Owner");
	      }
	}
	@Override
	protected void addAdditionalSaveData(CompoundTag p_20139_) {
	      if (this.ownerUUID != null) {
	    	  p_20139_.putUUID("Owner", this.ownerUUID);
	      }
		
	}
	public void setOwner(@Nullable LivingEntity p_36939_) {
	    this.owner = p_36939_;
	    this.ownerUUID = p_36939_ == null ? null : p_36939_.getUUID();
	}

	@Nullable
	public LivingEntity getOwner() {
	   if (this.owner == null && this.ownerUUID != null && this.level() instanceof ServerLevel) {
	      Entity entity = ((ServerLevel)this.level()).getEntity(this.ownerUUID);
	      if (entity instanceof LivingEntity) {
	         this.owner = (LivingEntity)entity;
	      }
	   }

	   return this.owner;
	}
	public void setHitboxScaleAbsolute(float hitboxScaleAbsolute) {
		this.hitboxScaleAbsolute = hitboxScaleAbsolute;
	}
	public void setHitboxScaleHeight(float hitboxScaleHeight) {
		this.hitboxScaleHeight = hitboxScaleHeight;
	}
	public void setTarget(LivingEntity e){this.target=e;}
	public LivingEntity getTarget(){return this.target;}
	public void setMaxTargets(int i){this.maxTargets=i;}
	public int getMaxTargets(){return this.maxTargets;}
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return new ClientboundAddEntityPacket(this);
	}
	@Override
	public boolean fireImmune() {return true;}
	@Override
	public boolean isOnFire() {return false;}
	 
	 @Override
	public void tick() {
		delayTicks--;
		if(delayTicks<=0) {
			
			this.remove(RemovalReason.DISCARDED);
		}
		for(LivingEntity localTarget : this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(hitboxScaleAbsolute, hitboxScaleAbsolute*hitboxScaleHeight, hitboxScaleAbsolute))) {
            if(this.target==null||localTarget==this.target) {
				if (this.getMaxTargets() <= 0 || hitEntities.size() < this.getMaxTargets()) {
					if (this.getOwner() == null || !localTarget.isAlliedTo(this.getOwner()) && localTarget != this.target) {
						if (!hitEntities.contains(localTarget)) {
							this.dealDamageTo(localTarget);
							hitEntities.add(localTarget);
						}
					}
				}
			}
         }
		super.tick();
	}
	 
	 private void dealDamageTo(LivingEntity p_36945_) {
	      LivingEntity livingentity = this.getOwner();
	      if (p_36945_.isAlive() && !p_36945_.isInvulnerable() && p_36945_ != livingentity) {
	         if (livingentity == null) {
	            p_36945_.hurt(this.damageSources().generic(), damage);
	         } else {
	            if (livingentity.isAlliedTo(p_36945_)||livingentity==p_36945_) {
	               return;
	            }else {

	            p_36945_.hurt(this.damageSources().mobAttack(livingentity), damage);
	            }
	         }

	      }
	   }

	 
}
