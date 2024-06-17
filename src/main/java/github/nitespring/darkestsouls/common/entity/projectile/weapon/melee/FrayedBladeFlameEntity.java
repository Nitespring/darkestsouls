package github.nitespring.darkestsouls.common.entity.projectile.weapon.melee;

import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.UUID;

public class FrayedBladeFlameEntity extends Entity {

    @Nullable
    private LivingEntity owner;
    @Nullable
    private UUID ownerUUID;
    public int lifeTicks = 22;
    private boolean clientSideAttackStarted;
    private int warmupDelayTicks;
    private boolean summonedFlameEvent;
    private float damage;



    public FrayedBladeFlameEntity(EntityType<? extends FrayedBladeFlameEntity> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);

    }

    public FrayedBladeFlameEntity(Level worldIn, float damageIn, double posX, double posY, double posZ, float p_36930_, int warmUpDelayTicks, LivingEntity ownerIn) {
        this(EntityInit.FRAYED_BLADE_FLAME.get(), worldIn);
        this.warmupDelayTicks = warmUpDelayTicks;
        this.setOwner(ownerIn);
        this.setYRot(p_36930_ * (180F / (float) Math.PI));
        this.setPos(posX, posY, posZ);
        this.damage = damageIn;
    }


    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {


    }

    @Override
    protected void readAdditionalSaveData(CompoundTag p_20052_) {
        this.warmupDelayTicks = p_20052_.getInt("Warmup");
        if (p_20052_.hasUUID("Owner")) {
            this.ownerUUID = p_20052_.getUUID("Owner");
        }

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag p_20139_) {
        p_20139_.putInt("Warmup", this.warmupDelayTicks);
        if (this.ownerUUID != null) {
            p_20139_.putUUID("Owner", this.ownerUUID);
        }

    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity e) {

        return new ClientboundAddEntityPacket(this,e);
    }


    public void setOwner(@Nullable LivingEntity p_36939_) {
        this.owner = p_36939_;
        this.ownerUUID = p_36939_ == null ? null : p_36939_.getUUID();
    }

    @Nullable
    public LivingEntity getOwner() {
        if (this.owner == null && this.ownerUUID != null && this.level() instanceof ServerLevel) {
            Entity entity = ((ServerLevel) this.level()).getEntity(this.ownerUUID);
            if (entity instanceof LivingEntity) {
                this.owner = (LivingEntity) entity;
            }
        }

        return this.owner;
    }

    public float getAnimationProgress(float p_36937_) {
        if (!this.clientSideAttackStarted) {
            return 0.0F;
        } else {
            int i = this.lifeTicks - 2;
            return i <= 0 ? 1.0F : 1.0F - ((float) i - p_36937_) / 20.0F;
        }
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();

        //this.setYRot(new Random().nextInt(360)+1);

    }

    @Override
    public boolean fireImmune() {

        return true;
    }


    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            if (this.clientSideAttackStarted) {
                --this.lifeTicks;
                if (this.lifeTicks == 6) {
                    for (int i = 0; i < 12; ++i) {
                        double d0 = this.getX() + (this.random.nextDouble() * 2.0D - 1.0D) * (double) this.getBbWidth() * 0.5D;
                        double d1 = this.getY() + 0.05D + this.random.nextDouble();
                        double d2 = this.getZ() + (this.random.nextDouble() * 2.0D - 1.0D) * (double) this.getBbWidth() * 0.5D;
                        double d3 = (this.random.nextDouble() * 2.0D - 1.0D) * 0.3D;
                        double d4 = 0.3D + this.random.nextDouble() * 0.3D;
                        double d5 = (this.random.nextDouble() * 2.0D - 1.0D) * 0.3D;
                        //this.level().addParticle(ParticleTypes., d0, d1 + 1.0D, d2, d3, d4, d5);
                    }
                }
            }
        } else if (--this.warmupDelayTicks < 0) {
            if (this.warmupDelayTicks == -4) {
                for (LivingEntity livingentity : this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(0.2D, 0.0D, 0.2D))) {
                    this.dealDamageTo(livingentity);
                }
            }

            if (!this.summonedFlameEvent) {
                this.level().broadcastEntityEvent(this, (byte) 4);
                this.summonedFlameEvent = true;
            }

            if (--this.lifeTicks < 0) {
                this.discard();
            }
        }

    }

    private void dealDamageTo(LivingEntity p_36945_) {
        LivingEntity livingentity = this.getOwner();
        if (p_36945_.isAlive() && !p_36945_.isInvulnerable() && p_36945_ != livingentity) {
            if (livingentity == null) {
                p_36945_.hurt(this.level().damageSources().magic(), 6.0F);
            } else {
                if (livingentity.isAlliedTo(p_36945_)) {
                    return;
                }

                p_36945_.hurt(this.level().damageSources().indirectMagic(this, livingentity), damage);
            }

        }
    }

    @Override
    public void handleEntityEvent(byte p_36935_) {
        super.handleEntityEvent(p_36935_);
        if (p_36935_ == 4) {
            this.clientSideAttackStarted = true;
            if (!this.isSilent()) {
                this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.FIRE_EXTINGUISH, this.getSoundSource(), 1.0F, this.random.nextFloat() * 0.2F + 0.85F, false);
            }
        }

    }

}
