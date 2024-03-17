package github.nitespring.darkestsouls.common.entity.projectile.throwable;

import github.nitespring.darkestsouls.common.entity.projectile.spell.CrystalShardEntity;
import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import github.nitespring.darkestsouls.core.interfaces.CustomItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class ThrowingKnifeEntity extends AbstractHurtingProjectile implements CustomItemSupplier {

    public boolean shouldRotate;
    public int rotationTick;

    protected static final EntityDataAccessor<ItemStack> ITEM = SynchedEntityData.defineId(ThrowingKnifeEntity.class, EntityDataSerializers.ITEM_STACK);
    protected static final EntityDataAccessor<Float> SIZE = SynchedEntityData.defineId(ThrowingKnifeEntity.class, EntityDataSerializers.FLOAT);

    public ThrowingKnifeEntity(EntityType<? extends AbstractHurtingProjectile> entityType, Level level){
        super(entityType,level);
    }
    public ThrowingKnifeEntity(EntityType<? extends AbstractHurtingProjectile> entityType, double x, double y, double z, ItemStack stackIn, double size, Level level) {
        super(entityType, x, y, z, level);
        setItem(stackIn);
        setSize((float) size);
    }
    @Override
    public ItemStack getItem() {return entityData.get(ITEM);}
    @Override
    public double getSize() {return entityData.get(SIZE);}
    public void setItem(ItemStack stack){entityData.set(ITEM,stack);}
    public void setSize(float size){entityData.set(SIZE,size);}

    @Override
    protected void defineSynchedData() {
        this.entityData.define(ITEM, ItemInit.THROWING_KNIFE.get().getDefaultInstance());
        this.entityData.define(SIZE, 0.4f);

    }

    @Override
    public void tick() {
        super.tick();
        Vec3 mov = this.getDeltaMovement();

        double d0 = mov.horizontalDistance();
        if(mov!=null) {
            //this.setOldPosAndRot();
            this.setYRot((float) (Mth.atan2(mov.x, mov.z) * (double) (180F / (float) Math.PI)));
            this.setXRot(0.1f*rotationTick+(float) (Mth.atan2(mov.y, d0) * (double) (180F / (float) Math.PI)));
            this.setDeltaMovement(this.getDeltaMovement().add(0,-0.01f*tickCount,0));
        }

        if(this.shouldRotate){
            this.rotationTick++;
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

    @Override
    protected void onHitBlock(BlockHitResult p_37258_) {
        this.setDeltaMovement(0,0,0);
        this.shouldRotate=false;
        this.rotationTick=0;
    }
}
