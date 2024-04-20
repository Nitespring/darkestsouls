package github.nitespring.darkestsouls.common.entity.projectile.weapon;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.common.entity.projectile.throwable.ThrowingKnifeEntity;
import github.nitespring.darkestsouls.common.entity.util.CustomBlockTags;
import github.nitespring.darkestsouls.core.init.EffectInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class Flame extends AbstractHurtingProjectile implements ItemSupplier{

    protected float damage;
    protected int poiseDamage;
    //protected int pierce;
    //protected int ricochet;
    public int gravTick;
    public int hitBlocks;
    protected static final EntityDataAccessor<Integer> FLYING_TIME = SynchedEntityData.defineId(Flame.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Float> SIZE = SynchedEntityData.defineId(Flame.class, EntityDataSerializers.FLOAT);
    protected static final EntityDataAccessor<Integer> RICOCHET = SynchedEntityData.defineId(Flame.class, EntityDataSerializers.INT);
    public Flame(EntityType<? extends AbstractHurtingProjectile> e, Level l) {
        super(e, l);
    }
    public Flame(EntityType<? extends AbstractHurtingProjectile> e, float damageIn, int flyingTime, int poiseIn, int ricochetIn, Level l) {
        super(e, l);
        this.damage = damageIn;
        this.poiseDamage = poiseIn;
        this.setRicochet(ricochetIn);
        this.setFlyingTime(flyingTime);
    }

    public float getAttackDamage() {return damage;}
    public void setAttackDamage(float damage) {this.damage = damage;}
    public int getPoiseDamage() {return poiseDamage;}
    public void setPoiseDamage(int poiseDamage) {this.poiseDamage = poiseDamage;}
    public int getFlyingTime() {return entityData.get(FLYING_TIME);}
    public void setFlyingTime(int flyingTime) {entityData.set(FLYING_TIME, flyingTime);}
    public float getSize() {return entityData.get(SIZE);}
    public void setSize(float size){entityData.set(SIZE,size);}
    @Override
    protected void defineSynchedData() {
        this.entityData.define(SIZE, 0.4f);
        this.entityData.define(RICOCHET, 0);
        this.entityData.define(FLYING_TIME, 60);
    }
    public int getRicochet() {return entityData.get(RICOCHET);}
    public void setRicochet(int ricochet) {entityData.set(RICOCHET,ricochet);}

    @Override
    protected void onHitEntity(EntityHitResult p_37259_) {
        Entity e = p_37259_.getEntity();
        if(!(e instanceof Flame)&&!(this.getOwner()!=null && e.isAlliedTo(this.getOwner()))) {
            super.onHitEntity(p_37259_);

            e.hurt(e.level().damageSources().mobProjectile(this, (LivingEntity) this.getOwner()), this.damage);
            if(e instanceof Mob mob) {
                if (mob instanceof DarkestSoulsAbstractEntity dsMob) {
                    if(mob.hurtTime<=0) {
                        dsMob.damagePoiseHealth(this.getPoiseDamage());
                    }
                }
            }
                e.setSecondsOnFire(5);

            }
        }
    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        BlockState block = this.level().getBlockState(result.getBlockPos());

        if(block.is(CustomBlockTags.FLAME_BREAKABLE)){
            this.level().destroyBlock(result.getBlockPos(), true, this.getOwner());
            level().gameEvent(this, GameEvent.BLOCK_DESTROY, result.getBlockPos());

        }else {
            hitBlocks++;
            if (hitBlocks >= getRicochet()) {
                this.discard();
                if (this.getOwner() != null) {
                    this.getOwner().level().playSound((Player) null, this.position().x(), this.position().y(), this.position().z(), SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.AMBIENT, 0.1F, 1.6f);
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
                this.getOwner().level().playSound((Player) null, this.position().x(), this.position().y(), this.position().z(), SoundEvents.ANVIL_HIT, SoundSource.AMBIENT, 0.2F, 1.6f);
                gravTick = 0;

            }
        }

            BlockPos blockPos = result.getBlockPos().relative(result.getDirection());
            if(level().getBlockState(blockPos).is(CustomBlockTags.FLAME_BREAKABLE)){
                level().destroyBlock(blockPos, true, this.getOwner());
                level().gameEvent(this, GameEvent.BLOCK_DESTROY, blockPos);
            }
            if(BaseFireBlock.canBePlacedAt(level(),blockPos, result.getDirection())) {
                BlockState blockstate = BaseFireBlock.getState(level(), blockPos);
                level().setBlock(blockPos, blockstate, 11);
                level().gameEvent(this, GameEvent.BLOCK_PLACE, blockPos);
            }
    }

    @Override
    public void tick() {
        setViewScale(this.getSize());
        super.tick();
        gravTick++;
        this.setDeltaMovement(this.getDeltaMovement().add(0, -0.0001 * Math.pow(gravTick, 9/4), 0));
        if(this.tickCount>=getFlyingTime()){
            this.discard();
        }
        /*if(this.tickCount % 3 == 0){
            this.level().addParticle(ParticleTypes.SMOKE,this.position().x,this.position().y,this.position().z,0,0,0);
        }*/
        ParticleOptions particle = ParticleTypes.FLAME;
        RandomSource rng = this.random;
        float width = this.getBbWidth() * 0.5f;
        float height = this.getBbHeight() * 0.5f;
        Vec3 pos = this.position();
        Level world = this.level();
        BlockPos blockPos = new BlockPos((int) pos.x, (int) pos.y, (int) pos.z);

        if(level().getBlockState(blockPos).is(CustomBlockTags.FLAME_BREAKABLE)){
            level().destroyBlock(blockPos, true, this.getOwner());
            level().gameEvent(this, GameEvent.BLOCK_DESTROY, blockPos);
            if(BaseFireBlock.canBePlacedAt(level(),blockPos, Direction.getNearest(pos.x,pos.y,pos.z))) {
                BlockState blockstate = BaseFireBlock.getState(level(), blockPos);
                level().setBlock(blockPos, blockstate, 11);
                level().gameEvent(this, GameEvent.BLOCK_PLACE, blockPos);
            }
        }
        //if (tickCount % 2 == 0) {
            for (int i = 0; i < 1; ++i) {

                Vec3 off = new Vec3(rng.nextDouble() * width - width / 2, rng.nextDouble() * height - height / 2,
                        rng.nextDouble() * width - width / 2);
                if (world instanceof ServerLevel) {
                    ((ServerLevel) world).sendParticles(particle, pos.x + 0.2*off.x, pos.y + height / 2 + 0.2 + 0.2*off.y, pos.z + + 0.2*off.z, 5, 0.1 * off.x, 0.05 * off.y + 0.1f, 0.1 * off.z, 0.025D);
                }
            }
        //}
    }
    @Override
    public boolean isOnFire() {
        return false;
    }


    @Override
    public boolean fireImmune() {
        return true;
    }
    @Nullable
    @Override
    protected ParticleOptions getTrailParticle() {
            return ParticleTypes.FLAME;
    }

    @Override
    public boolean canBeHitByProjectile() {
        return false;
    }

    @Override
    protected boolean canHitEntity(Entity p_36842_) {
        if(p_36842_ instanceof Flame){
            return false;
        }else {
            return super.canHitEntity(p_36842_);
        }
    }



    @Override
    public ItemStack getItem() {
        return Items.BLAZE_POWDER.getDefaultInstance();
    }
}
