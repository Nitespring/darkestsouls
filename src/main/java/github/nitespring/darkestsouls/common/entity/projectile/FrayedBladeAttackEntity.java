package github.nitespring.darkestsouls.common.entity.projectile;

import net.minecraft.core.particles.BlockParticleOption;
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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.UUID;

public class FrayedBladeAttackEntity extends Entity{
	@Nullable
	private LivingEntity owner;
	@Nullable
	private UUID ownerUUID;
	private int lifeTicks = 0;
	private float damage = 1.5f;
	
	
	private static final EntityDataAccessor<Integer> ANIMATIONSTATE = SynchedEntityData.defineId(FrayedBladeAttackEntity.class, EntityDataSerializers.INT);

	public FrayedBladeAttackEntity(EntityType<?> p_19870_, Level p_19871_) {
		super(p_19870_, p_19871_);

	}
	public FrayedBladeAttackEntity(EntityType<?> p_19870_, Level p_19871_, Vec3 pos, float dmg, float rot) {
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
			 this.setAnimationState(this.getAnimationState()+1);
			if(lifeTicks%2==0&&lifeTicks<=14){
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
					this.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.NETHER_PORTAL.defaultBlockState()), d0, d1 + 1.0D, d2, d3, d4, d5);
				}
			}


		      if (this.lifeTicks >= 20) {
		    	  this.discard();
		      }

		   }

		  private void dealDamageTo(LivingEntity p_36945_) {
		      LivingEntity livingentity = this.getOwner();
		      if (p_36945_.isAlive() && !p_36945_.isInvulnerable() && p_36945_ != livingentity) {
		         if (livingentity == null) {
		            p_36945_.hurt(this.level().damageSources().generic(), damage);
		         } else {
		            if (livingentity.isAlliedTo(p_36945_)) {
		               return;
		            }

		            p_36945_.hurt(this.level().damageSources().mobAttack(livingentity), damage);
		         }

		      }
		   }
		  
		 

}
