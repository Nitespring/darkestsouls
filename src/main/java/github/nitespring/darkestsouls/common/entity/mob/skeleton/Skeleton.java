package github.nitespring.darkestsouls.common.entity.mob.skeleton;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.common.entity.mob.hollow.Hollow;
import github.nitespring.darkestsouls.core.init.SoundInit;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public abstract class Skeleton extends DarkestSoulsAbstractEntity {
    
    private static final EntityDataAccessor<Integer> ROBE_TYPE = SynchedEntityData.defineId(Skeleton.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> HAT_TYPE = SynchedEntityData.defineId(Skeleton.class, EntityDataSerializers.INT);

    public Skeleton(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    @Override
    public int getMaxPoise() {return 20;}
    @Override
    public int getBloodResistance() {return 999;}
    @Override
    public boolean isBoss() {return false;}
    @Override
    public int getDSDefaultTeam() {return 0;}

    public int getDefaultRobeType(){return 0;}
    public int getDefaultHatType(){return 0;}

    public int getRobeType(){return this.entityData.get(ROBE_TYPE);}
    public void setRobeType(int i){this.entityData.set(ROBE_TYPE, i);}
    public int getHatType(){return this.entityData.get(HAT_TYPE);}
    public void setHatType(int i){this.entityData.set(HAT_TYPE, i);}

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ROBE_TYPE, this.getDefaultRobeType());
        this.entityData.define(HAT_TYPE, this.getDefaultHatType());
    }

    @Override
    public ParticleOptions getBloodParticles() {
        return new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Items.BONE_MEAL));
    }
    public ItemStack getRightHandItem(){return null;}
    public ItemStack getLeftHandItem(){return null;}

    public void populateClothing(){}
    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setRobeType(tag.getInt("RobeType"));
        this.setHatType(tag.getInt("HatType"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("RobeType", this.getRobeType());
        tag.putInt("HatType", this.getHatType());
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_21239_) {
        return SoundEvents.SKELETON_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SKELETON_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SKELETON_AMBIENT;
    }
    protected SoundEvent getAttackSound() {
        return SoundEvents.SKELETON_AMBIENT;
    }
}
