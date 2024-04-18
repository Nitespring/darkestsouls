package github.nitespring.darkestsouls.common.entity.projectile.throwable;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.core.init.EffectInit;
import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import github.nitespring.darkestsouls.core.interfaces.CustomItemSupplier;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class ThrowingKnifeEntity extends AbstractHurtingProjectile implements CustomItemSupplier {

    protected static final EntityDataAccessor<Boolean> SHOULD_ROTATE = SynchedEntityData.defineId(ThrowingKnifeEntity.class, EntityDataSerializers.BOOLEAN);
    public int rotationTick = 0;

    public float gravPower = 0;

    public float attackPower = 4.0f;
    public int bloodDamage = 0;
    public int poisonDamage = 0;
    public int poiseDamage = 6;

    public boolean canBePickedUp = false;

    protected static final EntityDataAccessor<ItemStack> ITEM = SynchedEntityData.defineId(ThrowingKnifeEntity.class, EntityDataSerializers.ITEM_STACK);
    protected static final EntityDataAccessor<Float> SIZE = SynchedEntityData.defineId(ThrowingKnifeEntity.class, EntityDataSerializers.FLOAT);
    protected static final EntityDataAccessor<Integer> ZTILT = SynchedEntityData.defineId(ThrowingKnifeEntity.class, EntityDataSerializers.INT);

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

    @Override
    public int getZTilt() {return entityData.get(ZTILT);}
    public float getAttackPower() {return this.attackPower;}
    public int getBloodDamage() {return this.bloodDamage;}
    public int getPoisonDamage() {return this.poisonDamage;}
    public int getPoiseDamage() {return this.poiseDamage;}
    public float getGravpower() {return this.gravPower;}

    public boolean shouldRotate(){return entityData.get(SHOULD_ROTATE);}
    public void setItem(ItemStack stack){entityData.set(ITEM,stack);}
    public void setSize(float size){entityData.set(SIZE,size);}
    public void setToRotate(boolean shouldRotate){entityData.set(SHOULD_ROTATE, shouldRotate);}
    public void setToPickUp(boolean isPickable){this.canBePickedUp = isPickable;}
    public void setZTilt(int rot) {entityData.set(ZTILT, rot);}

    public void setAttackPower(float f) {this.attackPower=f;}
    public void setBloodDamage(int i) {this.bloodDamage=i;}
    public void setPoisonDamage(int i) {this.poisonDamage=i;}
    public void setPoiseDamage(int i) {this.poiseDamage=i;}
    public void setGravPower(float f) {this.gravPower=f;}

    @Override
    protected void defineSynchedData() {
        this.entityData.define(ITEM, ItemInit.THROWING_KNIFE.get().getDefaultInstance());
        this.entityData.define(SIZE, 0.4f);
        this.entityData.define(SHOULD_ROTATE, false);
        this.entityData.define(ZTILT, 0);

    }

    @Override
    public void tick() {
        super.tick();
        Vec3 mov = this.getDeltaMovement();

        double d0 = mov.horizontalDistance();
        if(mov!=null) {
            //this.setOldPosAndRot();
            this.setYRot((float) (Mth.atan2(mov.x, mov.z) * (double) (180F / (float) Math.PI)));
            this.setXRot(-30*rotationTick+(float) (Mth.atan2(mov.y, d0) * (double) (180F / (float) Math.PI)));
            this.setDeltaMovement(this.getDeltaMovement().add(0,-this.getGravpower()*tickCount,0));
        }
        if(this.tickCount>=1000){
            this.discard();
        }
        if(this.shouldRotate()){
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
        this.setToRotate(false);
        this.rotationTick=0;
        int r = this.level().getRandom().nextInt(1000);
        if(r >= 600){
            //this.discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult hit) {
        if(this.getOwner()==null||this.getOwner() instanceof Player || (hit.getEntity() != this.getOwner()&&!hit.getEntity().isAlliedTo(this.getOwner()))) {
            if (hit.getEntity() instanceof LivingEntity target && this.getDeltaMovement().length() > 0.1) {
                this.setDeltaMovement(this.getDeltaMovement().multiply(-0.25, -1, -0.25));
                this.xPower = -0.05 * this.xPower;
                this.yPower = -0.25;
                this.zPower = -0.05 * this.zPower;
                this.setToRotate(false);
                this.rotationTick = 0;
                target.hurt(target.level().damageSources().mobProjectile(this, (LivingEntity) this.getOwner()), this.attackPower);
                if (target instanceof DarkestSoulsAbstractEntity dsMob) {
                    dsMob.damagePoiseHealth(this.poiseDamage);
                }
                if (this.getPoisonDamage() >= 1) {
                    target.addEffect(new MobEffectInstance(MobEffects.POISON, 60, this.poisonDamage - 1));
                }
                if (this.getBloodDamage() >= 1) {
                    if (target.hasEffect(EffectInit.BLEED.get())) {
                        int amount = target.getEffect(EffectInit.BLEED.get()).getAmplifier();
                        target.removeEffect(EffectInit.BLEED.get());
                        target.addEffect(new MobEffectInstance(EffectInit.BLEED.get(), 120, this.bloodDamage + amount));
                    } else {
                        target.addEffect(new MobEffectInstance(EffectInit.BLEED.get(), 120, this.bloodDamage - 1));
                    }
                }
                int r = this.level().getRandom().nextInt(1000);
                if (r >= 800) {
                    this.discard();
                }
            }
        }
        /*if(this.getDeltaMovement().length()<=0.1 && this.canBePickedUp && hit.getEntity() instanceof Player playerIn){
            if(!playerIn.isCreative()) {
                playerIn.getInventory().add(this.getItem());
            }
            this.discard();

        }*/
    }



}
