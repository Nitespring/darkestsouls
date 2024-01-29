package github.nitespring.darkestsouls.common.entity.projectile;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.UUID;

public class FlambergeAttackEntity extends Entity{
	@Nullable
	private LivingEntity owner;
	@Nullable
	private UUID ownerUUID;
	private int lifeTicks = 0;
	private float damage = 1.5f;
	@Nullable
	private ItemStack itemStack;
	private int hitEntities = 0;
	private int maxTargets;

	private static final EntityDataAccessor<Integer> ANIMATIONSTATE = SynchedEntityData.defineId(FlambergeAttackEntity.class, EntityDataSerializers.INT);

	public FlambergeAttackEntity(EntityType<?> p_19870_, Level p_19871_) {
		super(p_19870_, p_19871_);

	}
	public FlambergeAttackEntity(EntityType<?> p_19870_, Level p_19871_, Vec3 pos, float dmg, float rot) {
		this(p_19870_, p_19871_);
		this.setPos(pos);
		this.damage=dmg;
		this.setYRot(rot * (180F / (float)Math.PI));
	}

	 public int getAnimationState() {
	      return this.entityData.get(ANIMATIONSTATE);
	   }

	 public void setAnimationState(int anim) {
	      this.entityData.set(ANIMATIONSTATE, anim);
	   }
	
	
	@Override
	protected void defineSynchedData() {
		this.entityData.define(ANIMATIONSTATE, 0);
		
	}

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
	   if (this.owner == null && this.ownerUUID != null && this.getCommandSenderWorld() instanceof ServerLevel) {
	      Entity entity = ((ServerLevel)this.getCommandSenderWorld()).getEntity(this.ownerUUID);
	      if (entity instanceof LivingEntity) {
	         this.owner = (LivingEntity)entity;
	      }
	   }

	   return this.owner;
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return new ClientboundAddEntityPacket(this);
	}
	
	
	
	 @Override
		public boolean fireImmune() {
			
			return true;
		}

		 
		 @Override
		 public void tick() {
		      super.tick();
		      this.lifeTicks++;

			if(lifeTicks%2==0) {
				this.setAnimationState(this.getAnimationState()+1);
			}

			 if(lifeTicks==6){
				this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, this.getSoundSource(), 0.25F, this.random.nextFloat() * 0.2F + 1.0F, false);
				for(LivingEntity livingentity : level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(1.2D, 0.0D, 1.2D))) {
					this.dealDamageTo(livingentity);
				}
				for(int i = 0; i < 6; ++i) {
					double d0 = this.getX() + (this.random.nextDouble() * 2.0D - 1.0D) * (double)this.getBbWidth() * 0.5D;
					double d1 = this.getY() -0.75 + this.random.nextDouble()*1.5;
					double d2 = this.getZ() + (this.random.nextDouble() * 2.0D - 1.0D) * (double)this.getBbWidth() * 0.5D;
					double d3 = (this.random.nextDouble() * 2.0D - 1.0D) * 0.75D;
					double d4 = 0.15D + this.random.nextDouble() * 0.6D;
					double d5 = (this.random.nextDouble() * 2.0D - 1.0D) * 0.75D;
					this.level().addParticle(ParticleTypes.AMBIENT_ENTITY_EFFECT, d0, d1 + 1.0D, d2, d3, d4, d5);
				}
			}


		      if (this.lifeTicks >= 11) {
		    	  this.discard();
		      }
			 if(this.owner!=null&&lifeTicks%3==0) {
				 Vec3 pos = this.owner.position().add(this.owner.getLookAngle().x() * 1.5, 0.4, this.owner.getLookAngle().z() * 1.5);
				 this.setPos(pos);
				 float rot = (float) Mth.atan2(pos.z - this.owner.getZ(), pos.x - this.owner.getX());
				 this.setYRot(rot * (180F / (float) Math.PI));
			 }
		   }

		  private void dealDamageTo(LivingEntity p_36945_) {
		      LivingEntity livingentity = this.getOwner();
		      if (p_36945_.isAlive() && !p_36945_.isInvulnerable() && p_36945_ != livingentity) {
				  if(this.hitEntities<=maxTargets) {
						 if (livingentity == null) {
							p_36945_.hurt(this.level().damageSources().generic(), damage);
						 } else {
							if (livingentity.isAlliedTo(p_36945_)) {
							   return;
							}

							p_36945_.hurt(this.level().damageSources().mobAttack(livingentity), damage);
							 this.damageWeapon();
						 }
					  this.hitEntities++;
				  }

		      }
		   }
	public ItemStack getItemStack() {
		return itemStack;
	}

	public void setItemStack(ItemStack itemStack) {
		this.itemStack = itemStack;
	}
	private void damageWeapon(){
		if(this.getItemStack()!=null&&this.getOwner()!=null) {
			this.getItemStack().hurtAndBreak(1, this.getOwner(), (p_43296_) -> {
				p_43296_.broadcastBreakEvent(getItemStack().getEquipmentSlot());
			});
		}
	}
	public int getMaxTargets() {
		return maxTargets;
	}

	public void setMaxTargets(int maxTargets) {
		this.maxTargets = maxTargets;
	}

}