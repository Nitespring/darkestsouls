package github.nitespring.darkestsouls.common.entity.util.SpawnGroupEntities;

import github.nitespring.darkestsouls.common.entity.mob.church.HuntsmanAxe;
import github.nitespring.darkestsouls.common.entity.mob.hollow.HollowAssassin;
import github.nitespring.darkestsouls.common.entity.mob.hollow.HollowSoldierAxe;
import github.nitespring.darkestsouls.common.entity.mob.hollow.HollowSoldierCrossbow;
import github.nitespring.darkestsouls.common.entity.mob.hollow.HollowSoldierLongsword;
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

public class HollowSoldierSpawnGroupEntity extends SpawnGroupEntity{


    public HollowSoldierSpawnGroupEntity(EntityType<? extends PathfinderMob> mob, Level level) {
        super(mob, level);
    }

    @Override
    public void doMobSpawns(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        Random r = new Random();
        int size = r.nextInt(4);
        if(CommonConfig.spawn_hollow_longsword.get()||spawnType==MobSpawnType.SPAWN_EGG) {
            for (int i = 0; i <= r.nextInt(3 + size * 2) + r.nextInt(3) - 2; i++) {
                HollowSoldierLongsword mob = new HollowSoldierLongsword(EntityInit.HOLLOW_LONGSWORD.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                level.getLevel().addFreshEntity(mob);
            }
        }
        if(CommonConfig.spawn_hollow_axe.get()||spawnType==MobSpawnType.SPAWN_EGG) {
            for (int i = 0; i <= r.nextInt(2 + size * 2) + r.nextInt(3) - 2; i++) {
                HollowSoldierAxe mob = new HollowSoldierAxe(EntityInit.HOLLOW_AXE.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                level.getLevel().addFreshEntity(mob);
            }
        }
        if(CommonConfig.spawn_hollow_crossbow.get()||spawnType==MobSpawnType.SPAWN_EGG) {
            for (int i = 0; i <= r.nextInt(3 + size) + r.nextInt(3) - 2; i++) {
                HollowSoldierCrossbow mob = new HollowSoldierCrossbow(EntityInit.HOLLOW_CROSSBOW.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                level.getLevel().addFreshEntity(mob);
            }
        }
        if(CommonConfig.spawn_hollow_assassin.get()||spawnType==MobSpawnType.SPAWN_EGG) {
            for (int i = 0; i <= r.nextInt(1 + size) + r.nextInt(3) - 2; i++) {
                HollowAssassin mob = new HollowAssassin(EntityInit.HOLLOW_ASSASSIN.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                level.getLevel().addFreshEntity(mob);
            }
        }
    }

    @Override
    protected int getDSDefaultTeam() {
        return 2;
    }
}
