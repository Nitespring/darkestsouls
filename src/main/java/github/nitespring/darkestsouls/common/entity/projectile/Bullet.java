package github.nitespring.darkestsouls.common.entity.projectile;

import github.nitespring.darkestsouls.common.entity.projectile.throwable.ThrowingKnifeEntity;
import github.nitespring.darkestsouls.common.entity.util.CustomBlockTags;
import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class Bullet extends AbstractHurtingProjectile {

    protected float damage;
    protected int poiseDamage;
    protected int pierce;
    protected int ricochet;
    protected int flyingTime;
    protected static final EntityDataAccessor<Float> SIZE = SynchedEntityData.defineId(ThrowingKnifeEntity.class, EntityDataSerializers.FLOAT);
    public Bullet(EntityType<? extends AbstractHurtingProjectile> e, Level l) {
        super(e, l);
    }
    public Bullet(EntityType<? extends AbstractHurtingProjectile> e, float damageIn, int flyingTime, int poiseIn, int pierceIn, int ricochetIn, Level l) {
        super(e, l);
        this.damage = damageIn;
        this.poiseDamage = poiseIn;
        this.pierce = pierceIn;
        this.ricochet = ricochetIn;
        this.flyingTime = flyingTime;
    }

    public float getAttackDamage() {return damage;}
    public void setAttackDamage(float damage) {this.damage = damage;}
    public int getPoiseDamage() {return poiseDamage;}
    public void setPoiseDamage(int poiseDamage) {this.poiseDamage = poiseDamage;}
    public int getFlyingTime() {return flyingTime;}
    public void setFlyingTime(int flyingTime) {this.flyingTime = flyingTime;}
    public int getPierce() {return pierce;}
    public void setPierce(int pierce) {this.pierce = pierce;}
    public float getSize() {return entityData.get(SIZE);}
    public void setSize(float size){entityData.set(SIZE,size);}
    @Override
    protected void defineSynchedData() {this.entityData.define(SIZE, 0.4f);}
    public int getRicochet() {return ricochet;}
    public void setRicochet(int ricochet) {this.ricochet = ricochet;}

    @Override
    protected void onHitEntity(EntityHitResult p_37259_) {
        super.onHitEntity(p_37259_);
        this.discard();
    }
    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        BlockState block = this.level().getBlockState(result.getBlockPos());
        if(block.is(CustomBlockTags.BOMB_BREAKABLE)){
            this.level().destroyBlock(result.getBlockPos(), true, this.getOwner());
            level().gameEvent(this, GameEvent.BLOCK_DESTROY, result.getBlockPos());
        }else {
            this.discard();
        }
    }
    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public boolean isOnFire() {
        return false;
    }
}
