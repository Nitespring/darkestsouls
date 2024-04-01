package github.nitespring.darkestsouls.common.entity.projectile;

import github.nitespring.darkestsouls.common.entity.projectile.throwable.ThrowingKnifeEntity;
import github.nitespring.darkestsouls.common.entity.util.CustomBlockTags;
import github.nitespring.darkestsouls.core.init.ItemInit;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class Bullet extends AbstractHurtingProjectile {

    protected float damage;
    protected int poiseDamage;
    //protected int pierce;
    //protected int ricochet;
    protected int hitEntities;
    public int gravTick;
    protected int hitBlocks;
    protected int flyingTime;
    protected static final EntityDataAccessor<Float> SIZE = SynchedEntityData.defineId(ThrowingKnifeEntity.class, EntityDataSerializers.FLOAT);
    protected static final EntityDataAccessor<Integer> PIERCE = SynchedEntityData.defineId(ThrowingKnifeEntity.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Integer> RICOCHET = SynchedEntityData.defineId(ThrowingKnifeEntity.class, EntityDataSerializers.INT);
    public Bullet(EntityType<? extends AbstractHurtingProjectile> e, Level l) {
        super(e, l);
    }
    public Bullet(EntityType<? extends AbstractHurtingProjectile> e, float damageIn, int flyingTime, int poiseIn, int pierceIn, int ricochetIn, Level l) {
        super(e, l);
        this.damage = damageIn;
        this.poiseDamage = poiseIn;
        this.setPierce(pierceIn);
        this.setRicochet(ricochetIn);
        this.flyingTime = flyingTime;
    }

    public float getAttackDamage() {return damage;}
    public void setAttackDamage(float damage) {this.damage = damage;}
    public int getPoiseDamage() {return poiseDamage;}
    public void setPoiseDamage(int poiseDamage) {this.poiseDamage = poiseDamage;}
    public int getFlyingTime() {return flyingTime;}
    public void setFlyingTime(int flyingTime) {this.flyingTime = flyingTime;}
    public int getPierce() {return entityData.get(PIERCE);}
    public void setPierce(int pierce) {entityData.set(PIERCE,pierce);}
    public float getSize() {return entityData.get(SIZE);}
    public void setSize(float size){entityData.set(SIZE,size);}
    @Override
    protected void defineSynchedData() {
        this.entityData.define(SIZE, 0.4f);
        this.entityData.define(RICOCHET, 0);
        this.entityData.define(PIERCE, 0);
    }
    public int getRicochet() {return entityData.get(RICOCHET);}
    public void setRicochet(int ricochet) {entityData.set(RICOCHET,ricochet);}

    @Override
    protected void onHitEntity(EntityHitResult p_37259_) {
        super.onHitEntity(p_37259_);
        Entity e = p_37259_.getEntity();
        e.hurt(e.level().damageSources().mobProjectile(this, (LivingEntity) this.getOwner()), this.damage);
        if(hitEntities>=getPierce()){
            this.discard();
        }
        hitEntities++;
    }
    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        BlockState block = this.level().getBlockState(result.getBlockPos());
        if(block.is(CustomBlockTags.BOMB_BREAKABLE)){
            this.level().destroyBlock(result.getBlockPos(), true, this.getOwner());
            level().gameEvent(this, GameEvent.BLOCK_DESTROY, result.getBlockPos());
        }else {
            hitBlocks++;
            if (hitBlocks >= getRicochet()) {
                this.discard();
                if(this.getOwner()!=null){this.getOwner().level().playSound((Player)null, this.position().x(),this.position().y(),this.position().z(), SoundEvents.STONE_PLACE, SoundSource.AMBIENT, 0.2F, 1.6f);}
            } else {
                Vec3 mov = this.getDeltaMovement();
                Random r = new Random();
                float r1 = 2 * (r.nextFloat() - 0.5f);
                float r2 = 2 * (r.nextFloat() - 0.5f);
                float r3 = 2 * (r.nextFloat() - 0.5f);
                this.setDeltaMovement(-mov.x * (0.6 - 0.4 * r1), -mov.y * (0.6 - 0.4 * r2), -mov.z * (0.6 - 0.4 * r3));
                this.xPower = -xPower * (0.6 - 0.4 * r1);
                this.yPower = -yPower * (0.6 - 0.4 * r2);
                this.zPower = -zPower * (0.6 - 0.4 * r3);
                this.getOwner().level().playSound((Player)null, this.position().x(),this.position().y(),this.position().z(), SoundEvents.ANVIL_HIT, SoundSource.AMBIENT, 0.2F, 1.6f);
                gravTick=0;
            }
        }
    }
    @Override
    public void tick() {
        super.tick();
        gravTick++;
        this.setDeltaMovement(this.getDeltaMovement().add(0, -0.0001 * Math.pow(gravTick, 9/4), 0));
        if(this.tickCount>=getFlyingTime()){}
        if(this.tickCount % 3 == 0){
            this.level().addParticle(ParticleTypes.SMOKE,this.position().x,this.position().y,this.position().z,0,0,0);
        }
    }
    @Override
    public boolean isOnFire() {
        return false;
    }

    @Nullable
    @Override
    protected ParticleOptions getTrailParticle() {
        return null;
    }
    
}
