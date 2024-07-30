package github.nitespring.darkestsouls.common.entity.mob.church;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.core.init.SoundInit;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

public abstract class Huntsman extends DarkestSoulsAbstractEntity {

    private static final EntityDataAccessor<Integer> SHIRT_TYPE = SynchedEntityData.defineId(Huntsman.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> HAIR_TYPE = SynchedEntityData.defineId(Huntsman.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ROBE_TYPE = SynchedEntityData.defineId(Huntsman.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> HAT_TYPE = SynchedEntityData.defineId(Huntsman.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<ItemStack> RIGHT_HAND = SynchedEntityData.defineId(Huntsman.class, EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<ItemStack> LEFT_HAND = SynchedEntityData.defineId(Huntsman.class, EntityDataSerializers.ITEM_STACK);

    public Huntsman(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    @Override
    public int getMaxPoise() {return 24;}
    @Override
    public int getBloodResistance() {return 10;}
    @Override
    public boolean isBoss() {return false;}
    @Override
    public int getDSDefaultTeam() {return 3;}
    public int getDefaultShirtType(){return 0;}
    public int getDefaultHairType(){return 0;}
    public int getDefaultRobeType(){return 0;}
    public int getDefaultHatType(){return 0;}

    public int getRobeType(){return this.getEntityData().get(ROBE_TYPE);}
    public void setRobeType(int i){this.getEntityData().set(ROBE_TYPE, i);}
    public int getHatType(){return this.getEntityData().get(HAT_TYPE);}
    public void setHatType(int i){this.getEntityData().set(HAT_TYPE, i);}
    public int getShirtType(){return this.getEntityData().get(SHIRT_TYPE);}
    public void setShirtType(int i){this.getEntityData().set(SHIRT_TYPE, i);}
    public int getHairType(){return this.getEntityData().get(HAIR_TYPE);}
    public void setHairType(int i){this.getEntityData().set(HAIR_TYPE, i);}
    public ItemStack getRightHandItem(){return this.getEntityData().get(RIGHT_HAND);}
    public void setRightHandItem(ItemStack stack){this.getEntityData().set(RIGHT_HAND, stack);}
    public ItemStack getLeftHandItem(){return this.getEntityData().get(LEFT_HAND);}
    public void setLeftHandItem(ItemStack stack){this.getEntityData().set(LEFT_HAND, stack);}



    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
         super.defineSynchedData(builder);
        builder.define(ROBE_TYPE, this.getDefaultRobeType());
        builder.define(HAT_TYPE, this.getDefaultHatType());
        builder.define(HAIR_TYPE, this.getDefaultHairType());
        builder.define(SHIRT_TYPE, this.getDefaultShirtType());
        builder.define(RIGHT_HAND, ItemStack.EMPTY);
        builder.define(LEFT_HAND, ItemStack.EMPTY);
    }

    @Override
    public ParticleOptions getBloodParticles() {
        return super.getBloodParticles();
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setRobeType(tag.getInt("RobeType"));
        this.setHatType(tag.getInt("HatType"));
        this.setHairType(tag.getInt("HairType"));
        this.setShirtType(tag.getInt("ShirtType"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("RobeType", this.getRobeType());
        tag.putInt("HatType", this.getHatType());
        tag.putInt("HairType", this.getHairType());
        tag.putInt("ShirtType", this.getShirtType());
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_21239_) {
        return SoundInit.HOLLOW_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundInit.HOLLOW_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundInit.HOLLOW_IDLE.get();
    }

    @Override
    public float getVoicePitch() {
        return 0.4f;
    }

    @Override
    protected float getSoundVolume() {
        return 0.1f;
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, @Nullable SpawnGroupData p_21437_) {
        this.populateClothing();
        return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_);
    }

    protected SoundEvent getAttackSound() {
        return SoundInit.HOLLOW_ATTACK.get();
    }

    public void populateClothing(){

    }
}
