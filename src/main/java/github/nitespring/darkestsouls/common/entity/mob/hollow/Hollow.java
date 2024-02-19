package github.nitespring.darkestsouls.common.entity.mob.hollow;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

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
}
