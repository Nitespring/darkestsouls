package github.nitespring.darkestsouls.common.entity.projectile.spell;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
import org.jetbrains.annotations.Nullable;

public class CrystalBallEntity extends AbstractHurtingProjectile {

    public float damage=2.0f;
    //public int maxLifeTime=60;

    public int lifeTicks=0;

    protected static final EntityDataAccessor<Integer> MAX_LIFETIME = SynchedEntityData.defineId(CrystalBallEntity.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Integer> CRYSTAL_TYPE = SynchedEntityData.defineId(CrystalBallEntity.class, EntityDataSerializers.INT);

    public CrystalBallEntity(EntityType<? extends AbstractHurtingProjectile> e, Level level) {
        super(e, level);
    }

    public void setDamage(float f){
        this.damage=f;
    }
    //public void setMaxLifeTime(int i){this.maxLifeTime=i;}


    @Override
    public void tick() {
        super.tick();
        this.lifeTicks++;
        if(lifeTicks>=this.getMaxLifeTime()){
            this.doRemoval();
        }
    }

    public void doRemoval(){
        this.onRemoval();
        //this.doExplosion();
        this.remove(RemovalReason.DISCARDED);
    }
    public void onRemoval(){}


    @Override
    public boolean isOnFire() {
        return false;
    }

    @Nullable
    @Override
    protected ParticleOptions getTrailParticle() {
       //return new DustParticleOptions(new Vector3f(0.0f,0.8f,1), 1.0f);
        return ParticleTypes.PORTAL;
    }

    @Override
    protected void onHitBlock(BlockHitResult p_37258_) {
        super.onHitBlock(p_37258_);

        this.setDeltaMovement(0,0,0);

    }

    @Override
    protected void onHitEntity(EntityHitResult p_37259_) {
        super.onHitEntity(p_37259_);
        Entity e = p_37259_.getEntity();
       //e.hurt(e.level().damageSources().explosion(this, this.getOwner()),this.damage);
        if(e!=this.getOwner()&&!(this.getOwner()!=null&&e.isAlliedTo(this.getOwner()))) {
            this.level().playSound((Player) null, this, SoundEvents.GLASS_BREAK, SoundSource.PLAYERS, 2.0F, 1.0f);
        }
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(CRYSTAL_TYPE, 0);
        this.entityData.define(MAX_LIFETIME, 60);

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

    public int getMaxLifeTime() {
        return this.getEntityData().get(MAX_LIFETIME);
    }


    public void setMaxLifeTime(int type) {
        this.getEntityData().set(MAX_LIFETIME, type);
    }


}
