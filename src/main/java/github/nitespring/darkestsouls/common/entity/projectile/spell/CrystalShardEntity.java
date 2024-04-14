package github.nitespring.darkestsouls.common.entity.projectile.spell;

import net.minecraft.core.particles.ParticleOptions;
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
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.UUID;

public class CrystalShardEntity extends AbstractHurtingProjectile{

    protected int lifeTicks = 0;
    protected int maxLifeTicks = 120;

    protected float damage = 2.0f;
    protected boolean noGrav = true;
    protected int crystalType = 0;

    protected float zRot;

    @Nullable
    private LivingEntity owner;
    @Nullable
    private UUID ownerUUID;

    protected static final EntityDataAccessor<Integer> CRYSTAL_TYPE = SynchedEntityData.defineId(CrystalShardEntity.class, EntityDataSerializers.INT);

    public CrystalShardEntity(EntityType<? extends AbstractHurtingProjectile> p_36833_, Level p_36834_) {
        super(p_36833_, p_36834_);
    }

    public CrystalShardEntity(EntityType<? extends AbstractHurtingProjectile> p_310629_, double p_311590_, double p_312782_, double p_309484_, Level p_311660_) {
        super(p_310629_, p_311590_, p_312782_, p_309484_, p_311660_);
    }

    public CrystalShardEntity(EntityType<? extends AbstractHurtingProjectile> p_36826_, LivingEntity p_36827_, double p_36828_, double p_36829_, double p_36830_, Level p_36831_) {
        super(p_36826_, p_36827_, p_36828_, p_36829_, p_36830_, p_36831_);
    }


    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_20052_) {

        if (p_20052_.hasUUID("Owner")) {
            this.ownerUUID = p_20052_.getUUID("Owner");
        }

    }

    @Override
    public void addAdditionalSaveData(CompoundTag p_20139_) {

        if (this.ownerUUID != null) {
            p_20139_.putUUID("Owner", this.ownerUUID);
        }

    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(CRYSTAL_TYPE, 0);

    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        this.zRot = 2*(this.random.nextFloat()-0.5f);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {

        return new ClientboundAddEntityPacket(this);
    }

    @Override
    protected ParticleOptions getTrailParticle() {
        return ParticleTypes.PORTAL;
    }

    @Override
    public void tick() {
        super.tick();
        Vec3 mov = this.getDeltaMovement();

        double d0 = mov.horizontalDistance();
        if(mov!=null) {
            this.setYRot((float) (Mth.atan2(mov.x, mov.z) * (double) (180F / (float) Math.PI)));
            this.setXRot((float) (Mth.atan2(mov.y, d0) * (double) (180F / (float) Math.PI)));
        }
        if(++lifeTicks>=maxLifeTicks){
            this.doRemoval();
        }
        if(!this.isNoGrav()){this.doGravity();}

    }

    public void doGravity(){
        this.setDeltaMovement(this.getDeltaMovement().add(0,-0.01*lifeTicks,0));
    }
    @Override
    protected void onHitEntity(EntityHitResult p_37259_) {

        if(p_37259_.getEntity() != this.getOwner() && !(p_37259_.getEntity() instanceof CrystalShardEntity)) {
            this.doRemoval();
            Entity e = p_37259_.getEntity();
            e.hurt(e.level().damageSources().indirectMagic(this, this.getOwner()),this.damage);
            this.level().playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundEvents.RESPAWN_ANCHOR_DEPLETE.get(), this.getSoundSource(), 1.0f, 1.4f);
            for(int i=0; i<=2; i++){
                RandomSource r = this.random;
                Vec3 off = new Vec3(r.nextFloat() - 0.5, r.nextFloat() - 0.5, r.nextFloat() - 0.5).multiply(0.25f, 0.25f, 0.25f);
                if(this.level() instanceof ServerLevel level) {
                    level.sendParticles(ParticleTypes.ENCHANT, this.position().x+off.x, this.position().y+this.getBbHeight()*0.5f+off.y, this.position().z+off.z, 5,  off.x, off.y + 0.05D, off.z, 0.05D + 5*0.003);
                }
            }
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult p_37258_) {
        this.discard();
        for(int i=0; i<=2; i++){
            RandomSource r = this.random;
            Vec3 off = new Vec3(r.nextFloat() - 0.5, r.nextFloat() - 0.5, r.nextFloat() - 0.5).multiply(0.25f, 0.25f, 0.25f);
            if(this.level() instanceof ServerLevel level) {
                level.sendParticles(ParticleTypes.ENCHANT, this.position().x+off.x, this.position().y+this.getBbHeight()*0.5f+off.y, this.position().z+off.z, 5,  off.x, off.y + 0.05D, off.z, 0.05D + 5*0.003);
            }
        }
        this.level().playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundEvents.RESPAWN_ANCHOR_DEPLETE.get(), this.getSoundSource(), 1.0f, 1.4f);
    }

    public int getLifeTicks() {
        return maxLifeTicks;
    }

    public void setLifeTicks(int lifeTicks) {
        this.maxLifeTicks = lifeTicks;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getzRot() {
        return zRot;
    }

    public void setzRot(float rot) {
        this.zRot = rot;
    }

    public void doRemoval(){
        this.discard();
    }
    public int getCrystalType() {
        return this.getEntityData().get(CRYSTAL_TYPE);
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    public void setCrystalType(int type) {
        this.getEntityData().set(CRYSTAL_TYPE, type);
    }

    public boolean isNoGrav() {
        return this.noGrav;
    }

    public void setNoGrav(boolean type) {
        this.noGrav=type;    }


}
