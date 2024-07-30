package github.nitespring.darkestsouls.common.entity.util;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public abstract class SpawnGroupEntity extends DarkestSoulsAbstractEntity{


    public SpawnGroupEntity(EntityType<? extends PathfinderMob> mob, Level level) {
        super(mob, level);
    }

    @Override
    public boolean isBoss() {return false;}
    @Override
    public boolean isOnFire() {return false;}
    @Override
    public boolean canBeCollidedWith() {return false;}
    @Override
    public boolean canBeHitByProjectile() {return false;}
    @Override
    protected void registerGoals() {}
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        spawnGroupData=super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
        this.doMobSpawns(level, difficulty, spawnType, spawnGroupData);
        //this.discard();
        return spawnGroupData;
    }

    @Override
    public void tick() {
        super.tick();
        if(tickCount>=3){
            discard();
        }
    }

    public abstract void doMobSpawns(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData);
    public void finalizeMobSpawn(DarkestSoulsAbstractEntity mob){
        Random r = new Random();
        double x = position().x+r.nextFloat()*6-3;
        double z = position().z+r.nextFloat()*6-3;
        double y = position().y+1;
        Vec3 pos = new Vec3(x,y,z);
        mob.setPos(pos);
        mob.setDSTeam(getDSTeam());
        if(getOwner()!=null){mob.setOwner(getOwner());}
    }


    /*public AttributeMap getAttributes() {
        return new AttributeMap(new AttributeSupplier.Builder()
                .add(Attributes.MAX_HEALTH,1)
                .add(Attributes.MOVEMENT_SPEED,0)
                .add(Attributes.FOLLOW_RANGE,0)
                .add(Attributes.GRAVITY,0)
                .build());
    }*/
    public static  AttributeSupplier.Builder setAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 1.0D)
                .add(Attributes.ARMOR, 0.0D)
                .add(Attributes.ARMOR_TOUGHNESS, 0.0D)
                .add(Attributes.MOVEMENT_SPEED, 0D)
                .add(Attributes.ATTACK_DAMAGE, 0D)
                .add(Attributes.ATTACK_SPEED, 0D)
                .add(Attributes.ATTACK_KNOCKBACK, 0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0D)
                .add(Attributes.FOLLOW_RANGE, 0)
                .add(Attributes.STEP_HEIGHT, 0)
                .add(Attributes.GRAVITY,0);
    }
}
