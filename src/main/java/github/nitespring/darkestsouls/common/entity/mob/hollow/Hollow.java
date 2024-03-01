package github.nitespring.darkestsouls.common.entity.mob.hollow;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.core.init.SoundInit;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public abstract class Hollow extends DarkestSoulsAbstractEntity {

    private static final EntityDataAccessor<Integer> SKIN_TYPE = SynchedEntityData.defineId(Hollow.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ROBE_TYPE = SynchedEntityData.defineId(Hollow.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> HAT_TYPE = SynchedEntityData.defineId(Hollow.class, EntityDataSerializers.INT);

    public Hollow(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }
    @Override
    public int getMaxPoise() {return 12;}
    @Override
    public int getBloodResistance() {return 6;}
    @Override
    public boolean isBoss() {return false;}
    @Override
    public int getDSDefaultTeam() {return 0;}

    public int getDefaultRobeType(){return 0;}
    public int getDefaultHatType(){return 0;}
    public int getSkinType(){return this.entityData.get(SKIN_TYPE);}
    public void setSkinType(int i){this.entityData.set(SKIN_TYPE, i);}
    public int getRobeType(){return this.entityData.get(ROBE_TYPE);}
    public void setRobeType(int i){this.entityData.set(ROBE_TYPE, i);}
    public int getHatType(){return this.entityData.get(HAT_TYPE);}
    public void setHatType(int i){this.entityData.set(HAT_TYPE, i);}

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SKIN_TYPE, 0);
        this.entityData.define(ROBE_TYPE, this.getDefaultRobeType());
        this.entityData.define(HAT_TYPE, this.getDefaultHatType());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setSkinType(tag.getInt("SkinType"));
        this.setRobeType(tag.getInt("RobeType"));
        this.setHatType(tag.getInt("HatType"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("SkinType", this.getSkinType());
        tag.putInt("RobeType", this.getRobeType());
        tag.putInt("HatType", this.getHatType());
    }


    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, @Nullable SpawnGroupData p_21437_, @Nullable CompoundTag p_21438_) {
        Random rn = new Random();
        int r = rn.nextInt(12) + 1;
        switch(r){
            case 1,2:
                this.setSkinType(1);
                break;
            case 3,4:
                this.setSkinType(2);
                break;
            case 5,6,7:
                this.setSkinType(3);
                break;
            case 8,9:
                this.setSkinType(4);
                break;
            case 10:
                this.setSkinType(5);
                break;
            default:
                this.setSkinType(0);
                break;
        }
        this.populateClothing();
        return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
    }

    public void populateClothing(){

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
    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_21239_) {
        return SoundInit.HOLLOW_HURT.get();
    }

    protected SoundEvent getAttackSound() {
        return SoundInit.HOLLOW_ATTACK.get();
    }

    @Override
    protected float getSoundVolume() {
        return 0.2f;
    }
}
