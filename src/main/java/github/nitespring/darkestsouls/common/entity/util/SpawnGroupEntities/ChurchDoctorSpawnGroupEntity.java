package github.nitespring.darkestsouls.common.entity.util.SpawnGroupEntities;

import github.nitespring.darkestsouls.common.entity.mob.church.*;
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

public class ChurchDoctorSpawnGroupEntity extends SpawnGroupEntity{


    public ChurchDoctorSpawnGroupEntity(EntityType<? extends PathfinderMob> mob, Level level) {
        super(mob, level);
    }

    @Override
    public void doMobSpawns(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        Random r = new Random();
        int size = r.nextInt(3);
        if(CommonConfig.spawn_church_doctor.get()||spawnType==MobSpawnType.SPAWN_EGG) {
            for (int i = 0; i <= r.nextInt(1 + size * 2) + r.nextInt(3) - 2; i++) {
                ChurchDoctorStick mob = new ChurchDoctorStick(EntityInit.CHURCH_DOCTOR.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                level.getLevel().addFreshEntity(mob);
            }
        }
        if(CommonConfig.spawn_church_doctor.get()||spawnType==MobSpawnType.SPAWN_EGG) {
            for (int i = 0; i <= r.nextInt(1 + size * 2) + r.nextInt(3) - 2; i++) {
                ChurchDoctorLantern mob = new ChurchDoctorLantern(EntityInit.CHURCH_DOCTOR_LANTERN.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                level.getLevel().addFreshEntity(mob);
            }
        }
        if(CommonConfig.spawn_church_doctor_pistol.get()||spawnType==MobSpawnType.SPAWN_EGG) {
            for (int i = 0; i <= r.nextInt(2+size) + r.nextInt(3) - 2; i++) {
                ChurchDoctorPistol mob = new ChurchDoctorPistol(EntityInit.CHURCH_DOCTOR_PISTOL.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                level.getLevel().addFreshEntity(mob);
            }
        }
        if(CommonConfig.spawn_church_doctor_scythe.get()||spawnType==MobSpawnType.SPAWN_EGG) {
            for (int i = 0; i <= r.nextInt(2+size) + r.nextInt(3) - 2; i++) {
                ChurchDoctorScythe mob = new ChurchDoctorScythe(EntityInit.CHURCH_DOCTOR_SCYTHE.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                level.getLevel().addFreshEntity(mob);
            }
        }
        if(CommonConfig.spawn_church_doctor_flamesprayer.get()||spawnType==MobSpawnType.SPAWN_EGG) {
            for (int i = 0; i <= r.nextInt(1+size) + r.nextInt(3) - 2; i++) {
                ChurchDoctorFlamesprayer mob = new ChurchDoctorFlamesprayer(EntityInit.CHURCH_DOCTOR_FLAMESPRAYER.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                level.getLevel().addFreshEntity(mob);
            }
        }
        if(CommonConfig.spawn_church_doctor_crucifix.get()||spawnType==MobSpawnType.SPAWN_EGG) {
            for (int i = 0; i <= r.nextInt(1+size) + r.nextInt(3) - 2; i++) {
                ChurchDoctorCrucifix mob = new ChurchDoctorCrucifix(EntityInit.CHURCH_DOCTOR_CRUCIFIX.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                level.getLevel().addFreshEntity(mob);
            }
        }

    }

    @Override
    protected int getDSDefaultTeam() {
        return 5;
    }
}
