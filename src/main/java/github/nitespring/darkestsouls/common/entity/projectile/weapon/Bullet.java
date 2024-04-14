package github.nitespring.darkestsouls.common.entity.projectile.weapon;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.common.entity.projectile.throwable.ThrowingKnifeEntity;
import github.nitespring.darkestsouls.common.entity.util.CustomBlockTags;
import github.nitespring.darkestsouls.core.init.EffectInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class Bullet extends AbstractHurtingProjectile {

    protected float damage;
    protected int poiseDamage;
    protected int blood;
    protected int poison;
    protected int explosion;
    //protected int pierce;
    //protected int ricochet;
    protected int hitEntities;
    protected boolean isThunder;
    public int gravTick;
    protected int hitBlocks;
    protected static final EntityDataAccessor<Integer> FLYING_TIME = SynchedEntityData.defineId(ThrowingKnifeEntity.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Float> SIZE = SynchedEntityData.defineId(ThrowingKnifeEntity.class, EntityDataSerializers.FLOAT);
    protected static final EntityDataAccessor<Integer> PIERCE = SynchedEntityData.defineId(ThrowingKnifeEntity.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Integer> RICOCHET = SynchedEntityData.defineId(ThrowingKnifeEntity.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Boolean> FIRE = SynchedEntityData.defineId(ThrowingKnifeEntity.class, EntityDataSerializers.BOOLEAN);
    public Bullet(EntityType<? extends AbstractHurtingProjectile> e, Level l) {
        super(e, l);
    }
    public Bullet(EntityType<? extends AbstractHurtingProjectile> e, float damageIn, int flyingTime, int poiseIn, int pierceIn, int ricochetIn, Level l) {
        super(e, l);
        this.damage = damageIn;
        this.poiseDamage = poiseIn;
        this.setPierce(pierceIn);
        this.setRicochet(ricochetIn);
        this.setFlyingTime(flyingTime);
    }

    public float getAttackDamage() {return damage;}
    public void setAttackDamage(float damage) {this.damage = damage;}
    public int getPoiseDamage() {return poiseDamage;}
    public void setPoiseDamage(int poiseDamage) {this.poiseDamage = poiseDamage;}
    public int getPoison() {return poison;}
    public void setPoison(int i) {this.poison = i;}
    public int getBlood() {return blood;}
    public void setBlood(int i) {this.blood = i;}
    public boolean isThunder() {return isThunder;}
    public void setThunder(boolean i) {this.isThunder = i;}
    public int getExplosion() {return explosion;}
    public void setExplosion(int i) {this.explosion = i;}
    public boolean isFire() {return entityData.get(FIRE);}
    public void setFire(boolean b) {entityData.set(FIRE, b);}

    public int getFlyingTime() {return entityData.get(FLYING_TIME);}
    public void setFlyingTime(int flyingTime) {entityData.set(FLYING_TIME, flyingTime);}
    public int getPierce() {return entityData.get(PIERCE);}
    public void setPierce(int pierce) {entityData.set(PIERCE,pierce);}
    public float getSize() {return entityData.get(SIZE);}
    public void setSize(float size){entityData.set(SIZE,size);}
    @Override
    protected void defineSynchedData() {
        this.entityData.define(SIZE, 0.4f);
        this.entityData.define(RICOCHET, 0);
        this.entityData.define(PIERCE, 0);
        this.entityData.define(FLYING_TIME, 100);
        this.entityData.define(FIRE, false);
    }
    public int getRicochet() {return entityData.get(RICOCHET);}
    public void setRicochet(int ricochet) {entityData.set(RICOCHET,ricochet);}

    @Override
    protected void onHitEntity(EntityHitResult p_37259_) {
        Entity e = p_37259_.getEntity();
        if(!(e instanceof Bullet)&&!(this.getOwner()!=null && e.isAlliedTo(this.getOwner()))) {
            super.onHitEntity(p_37259_);

            e.hurt(e.level().damageSources().mobProjectile(this, (LivingEntity) this.getOwner()), this.damage);
            if(e instanceof Mob mob) {
                if (mob instanceof DarkestSoulsAbstractEntity dsMob) {
                    if(mob.hurtTime<=0) {
                        dsMob.damagePoiseHealth(this.getPoiseDamage());
                    }
                }
                if (this.getPoison() >= 1) {
                    mob.addEffect(new MobEffectInstance(MobEffects.POISON, 60, this.getPoison() - 1));
                }
                if (this.getBlood() >= 1) {
                    if (mob.hasEffect(EffectInit.BLEED.get())) {
                        int amount = mob.getEffect(EffectInit.BLEED.get()).getAmplifier();
                        mob.removeEffect(EffectInit.BLEED.get());
                        mob.addEffect(new MobEffectInstance(EffectInit.BLEED.get(), 120, this.getBlood() + amount));
                    } else {
                        mob.addEffect(new MobEffectInstance(EffectInit.BLEED.get(), 120, this.getBlood() - 1));
                    }
                }
            }
            if(isFire()){
                e.setSecondsOnFire(3);
            }
            if(isThunder()){
               this.spawnThunder();
            }
            if (hitEntities >= getPierce()) {
                this.discard();
                if(this.getExplosion()>=1){
                    this.explode(this.getExplosion());
                }
            }else{
                if(this.getExplosion()>=3){
                    this.explode(this.getExplosion()-2);
                }
            }
            hitEntities++;
        }
    }
    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        BlockState block = this.level().getBlockState(result.getBlockPos());
        if(isThunder()){
            this.spawnThunder();
        }
        if(block.is(CustomBlockTags.BOMB_BREAKABLE)){
            this.level().destroyBlock(result.getBlockPos(), true, this.getOwner());
            level().gameEvent(this, GameEvent.BLOCK_DESTROY, result.getBlockPos());
            if(this.getExplosion()>=3){
                this.explode(this.getExplosion()-2);
            }
        }else {
            hitBlocks++;
            if (hitBlocks >= getRicochet()) {
                this.discard();
                if(this.getOwner()!=null){this.getOwner().level().playSound((Player)null, this.position().x(),this.position().y(),this.position().z(), SoundEvents.STONE_PLACE, SoundSource.AMBIENT, 0.2F, 1.6f);}
                if(this.getExplosion()>=1){
                    this.explode(this.getExplosion());
                }
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
                if(this.getExplosion()>=3){
                    this.explode(this.getExplosion()-2);
                }
            }
        }
        if(isFire()){
            BlockPos blockPos = result.getBlockPos().relative(result.getDirection());
            if(level().getBlockState(blockPos).is(CustomBlockTags.BOMB_BREAKABLE)){
                level().destroyBlock(blockPos, true, this.getOwner());
                level().gameEvent(this, GameEvent.BLOCK_DESTROY, blockPos);
            }
            if(BaseFireBlock.canBePlacedAt(level(),blockPos, result.getDirection())) {
                BlockState blockstate = BaseFireBlock.getState(level(), blockPos);
                level().setBlock(blockPos, blockstate, 11);
                level().gameEvent(this, GameEvent.BLOCK_PLACE, blockPos);
            }
        }
    }
    @Override
    public void tick() {
        super.tick();
        gravTick++;
        this.setDeltaMovement(this.getDeltaMovement().add(0, -0.0001 * Math.pow(gravTick, 9/4), 0));
        if(this.tickCount>=getFlyingTime()){
            this.discard();
            if(this.getExplosion()>=1){
                this.explode(this.getExplosion()-1);
            }
            if(isThunder()){
                this.spawnThunder();
            }
        }
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
        if(isFire()){
            return ParticleTypes.FLAME;
        }else {
            return null;
        }
    }

    @Override
    public boolean canBeHitByProjectile() {
        return false;
    }

    @Override
    protected boolean canHitEntity(Entity p_36842_) {
        if(p_36842_ instanceof Bullet){
            return false;
        }else {
            return super.canHitEntity(p_36842_);
        }
    }

    protected void explode(int i){
        this.level().explode(this,
                this.position().x(),
                this.position().y(),
                this.position().z(),
                0.5f+0.5f*Math.max(0,i),
                isFire(),
                Level.ExplosionInteraction.MOB);
    }
    protected void spawnThunder(){
        LightningBolt entity = new LightningBolt(EntityType.LIGHTNING_BOLT,this.level());
        entity.setPos(this.position());
        entity.setDamage(this.getAttackDamage());
        if(this.getOwner() instanceof ServerPlayer player){
            entity.setCause(player);
        }
        this.level().addFreshEntity(entity);
    }
}
