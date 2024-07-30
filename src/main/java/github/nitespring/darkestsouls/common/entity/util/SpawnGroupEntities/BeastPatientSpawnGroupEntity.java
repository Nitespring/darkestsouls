package github.nitespring.darkestsouls.common.entity.util.SpawnGroupEntities;

import github.nitespring.darkestsouls.common.entity.mob.beast.AshenBloodBeastPatient;
import github.nitespring.darkestsouls.common.entity.mob.beast.BeastPatient;
import github.nitespring.darkestsouls.common.entity.mob.beast.CloakedBeastPatient;
import github.nitespring.darkestsouls.common.entity.mob.church.HuntsmanAxe;
import github.nitespring.darkestsouls.common.entity.util.SpawnGroupEntity;
import github.nitespring.darkestsouls.config.CommonConfig;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class BeastPatientSpawnGroupEntity extends SpawnGroupEntity{


    public BeastPatientSpawnGroupEntity(EntityType<? extends PathfinderMob> mob, Level level) {
        super(mob, level);
    }

    @Override
    public void doMobSpawns(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        Random r = new Random();
        int size = r.nextInt(3);
        if(CommonConfig.spawn_beast_patient.get()||spawnType==MobSpawnType.SPAWN_EGG){
        for(int i=0; i<=r.nextInt(2+size*2);i++){
            BeastPatient mob = new BeastPatient(EntityInit.BEAST_PATIENT.get(), level.getLevel());
            mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
            finalizeMobSpawn(mob);
            level.getLevel().addFreshEntity(mob);
        }}
        if(CommonConfig.spawn_cloaked_beast_patient.get()||spawnType==MobSpawnType.SPAWN_EGG){
        for(int i=0; i<=r.nextInt(1+size*2);i++){
            CloakedBeastPatient mob = new CloakedBeastPatient(EntityInit.CLOAKED_BEAST_PATIENT.get(), level.getLevel());
            mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
            finalizeMobSpawn(mob);
            level.getLevel().addFreshEntity(mob);
        }}
        if(CommonConfig.spawn_ashen_blood_beast_patient.get()||spawnType==MobSpawnType.SPAWN_EGG) {
            for (int i = 0; i <= r.nextInt(2)+r.nextInt(3)-2; i++) {
                AshenBloodBeastPatient mob = new AshenBloodBeastPatient(EntityInit.ASHEN_BLOOD_BEAST_PATIENT.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                level.getLevel().addFreshEntity(mob);
            }
        }
    }

    @Override
    protected int getDSDefaultTeam() {
        return 4;
    }
}
