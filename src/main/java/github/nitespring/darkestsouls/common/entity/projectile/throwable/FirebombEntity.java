package github.nitespring.darkestsouls.common.entity.projectile.throwable;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;

public class FirebombEntity extends AbstractHurtingProjectile{

    public float gravPower = 0;
    public float attackDamage = 4.0f;
    public int poiseDamage = 6;
    protected static final EntityDataAccessor<Integer> BOMB_TYPE = SynchedEntityData.defineId(FirebombEntity.class, EntityDataSerializers.INT);
    public double verticalSpread = 2.0f;
    public double horizontalSpread = 2.0f;
    public int explodingTick = 0;
    public boolean isExploding = false;
    public FirebombEntity(EntityType<? extends AbstractHurtingProjectile> p_36833_, Level p_36834_) {
        super(p_36833_, p_36834_);
    }


    public int getBombType() {return entityData.get(BOMB_TYPE);}
    public float getAttackDamage() {return this.attackDamage;}
    public int getPoiseDamage() {return this.poiseDamage;}
    public float getGravpower() {return this.gravPower;}

    public void setAttackDamage(float f) {this.attackDamage=f;}
    public void setPoiseDamage(int i) {this.poiseDamage=i;}
    public void setGravPower(float f) {this.gravPower=f;}
    public void setBombType(int type) {entityData.set(BOMB_TYPE, type);}
    public void setVerticalSpread(double f) {this.verticalSpread=f;}
    public void setHorizontalSpread(double f) {this.horizontalSpread=f;}

    @Override
    protected void defineSynchedData() {
        this.entityData.define(BOMB_TYPE, 0);
    }
    @Override
    public boolean isOnFire() {
        return false;
    }

    @Nullable
    @Override
    protected ParticleOptions getTrailParticle() {
        return ParticleTypes.SMOKE;
    }

    @Override
    public void tick() {
        super.tick();
        if(!this.isExploding) {
            this.setDeltaMovement(this.getDeltaMovement().add(0, -this.getGravpower() * tickCount, 0));
        }
        if(this.tickCount>=1000){
            this.discard();
        }
        if(this.isExploding){
            explodingTick++;
            this.firebombExplosion();
        }
        if(this.explodingTick>4){
            this.discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult p_37259_) {
        super.onHitEntity(p_37259_);
        if(p_37259_.getEntity()!=this.getOwner()){
            doOnHit();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult p_37258_) {
        super.onHitBlock(p_37258_);
       doOnHit();

    }

    public void doOnHit(){
        this.isExploding=true;
        this.xPower=0;
        this.yPower=0;
        this.zPower=0;
        this.setDeltaMovement(0,0,0);
    }

    public void firebombExplosion(){

        this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.DRAGON_FIREBALL_EXPLODE, this.getSoundSource(), 0.25F, this.random.nextFloat() * 0.2F + 1.0F, false);
        for(LivingEntity livingentity : level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(this.horizontalSpread, this.verticalSpread, this.horizontalSpread))) {
            livingentity.setSecondsOnFire(80);
            if(livingentity.hurtTime<=0) {
                if (livingentity instanceof DarkestSoulsAbstractEntity) {
                    ((DarkestSoulsAbstractEntity) livingentity).damagePoiseHealth(this.poiseDamage);
                }
                livingentity.hurt(this.level().damageSources().mobProjectile(this,(LivingEntity)this.getOwner()),this.getAttackDamage());
            }

        }

        for(BlockPos blockPos : BlockPos.betweenClosedStream(this.getBoundingBox().inflate(this.horizontalSpread, this.verticalSpread, this.horizontalSpread)).toList()) {

            

        }


    }
}
