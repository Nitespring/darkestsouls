package github.nitespring.darkestsouls.common.entity.projectile.spell;

import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.UUID;

public class MagmaEntity extends Entity implements GeoEntity{

    @Nullable
    private LivingEntity owner;
    @Nullable
    private UUID ownerUUID;
    public int lifeTicks = 120;
    private float damage;

    protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);



    public MagmaEntity(EntityType<? extends MagmaEntity> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);

    }

    public MagmaEntity(Level worldIn, float damageIn, double posX, double posY, double posZ, LivingEntity ownerIn) {
        this(EntityInit.MAGMA.get(), worldIn);
        this.setOwner(ownerIn);
        this.setPos(posX, posY, posZ);
        this.damage = damageIn;
    }
    public MagmaEntity(Level worldIn, float damageIn, double posX, double posY, double posZ, float p_36930_,LivingEntity ownerIn) {
        this(worldIn,damageIn,posX,posY,posZ,ownerIn);
        this.setYRot(p_36930_ * (180F / (float) Math.PI));

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {return this.factory;}

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<>(this, "main_controller", 0, this::predicate));
    }

    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
        event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.magma.new"));
        return PlayState.CONTINUE;
    }
    @Override
    protected void defineSynchedData() {


    }

    @Override
    protected void readAdditionalSaveData(CompoundTag p_20052_) {

        if (p_20052_.hasUUID("Owner")) {
            this.ownerUUID = p_20052_.getUUID("Owner");
        }

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag p_20139_) {

        if (this.ownerUUID != null) {
            p_20139_.putUUID("Owner", this.ownerUUID);
        }

    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {

        return new ClientboundAddEntityPacket(this);
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
        //this.lifeTicks--;

        for (LivingEntity livingentity : this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(0.25D, 0.0D, 0.25D))) {
             this.dealDamageTo(livingentity);
        }

        if(this.lifeTicks%40==0&&this.lifeTicks!=0) {
            int r = this.random.nextInt(1024);
            if(r>=600) {
                this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.LAVA_AMBIENT, this.getSoundSource(), 0.2F, this.random.nextFloat() * 0.2F + 0.65F, false);
            }
        }
        if(this.lifeTicks%8==0&&this.lifeTicks!=0) {
            int r = this.random.nextInt(1024);
            if(r>=1000){
                this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.LAVA_POP, this.getSoundSource(), 0.3F, this.random.nextFloat() * 0.4F + 0.85F, false);
            }
        }
        if (--this.lifeTicks < 0) {
            this.discard();
            this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.LAVA_EXTINGUISH, this.getSoundSource(), 0.1F, this.random.nextFloat() * 0.4F + 0.25F, false);
        }

    }

    private void dealDamageTo(LivingEntity p_36945_) {
        LivingEntity livingentity = this.getOwner();
        if (p_36945_.isAlive() && !p_36945_.isInvulnerable() && p_36945_ != livingentity) {
            if (livingentity == null) {
                p_36945_.hurt(this.level().damageSources().inFire(), 6.0F);
                p_36945_.setSecondsOnFire(2);
            } else {
                if (livingentity.isAlliedTo(p_36945_)) {
                    return;
                }

                p_36945_.hurt(this.level().damageSources().inFire(), damage);
                p_36945_.setSecondsOnFire(2);
            }

        }
    }



}
