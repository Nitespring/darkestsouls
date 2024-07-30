package github.nitespring.darkestsouls.common.entity.util.SpawnGroupEntities;

import github.nitespring.darkestsouls.common.entity.mob.hollow.HollowAssassin;
import github.nitespring.darkestsouls.common.entity.mob.hollow.HollowSoldierAxe;
import github.nitespring.darkestsouls.common.entity.mob.hollow.HollowSoldierCrossbow;
import github.nitespring.darkestsouls.common.entity.mob.hollow.HollowSoldierLongsword;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.SkeletonCurvedSwords;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.SkeletonFalchion;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.SkeletonSpear;
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

public class SkeletonSpawnGroupEntity extends SpawnGroupEntity{


    public SkeletonSpawnGroupEntity(EntityType<? extends PathfinderMob> mob, Level level) {
        super(mob, level);
    }

    @Override
    public void doMobSpawns(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        Random r = new Random();
        int size = r.nextInt(4);
        if(CommonConfig.spawn_skeleton_falchion.get()||spawnType==MobSpawnType.SPAWN_EGG) {
            for (int i = 0; i <= r.nextInt(3 + size); i++) {
                SkeletonFalchion mob = new SkeletonFalchion(EntityInit.SKELETON_FALCHION.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                level.getLevel().addFreshEntity(mob);
            }
        }
        if(CommonConfig.spawn_skeleton_spear.get()||spawnType==MobSpawnType.SPAWN_EGG) {
            for (int i = 0; i <= r.nextInt(2 + size); i++) {
                SkeletonSpear mob = new SkeletonSpear(EntityInit.SKELETON_SPEAR.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                level.getLevel().addFreshEntity(mob);
            }
        }
        if(CommonConfig.spawn_skeleton_curved_swords.get()||spawnType==MobSpawnType.SPAWN_EGG) {
            for (int i = 0; i <= r.nextInt(1 + size); i++) {
                SkeletonCurvedSwords mob = new SkeletonCurvedSwords(EntityInit.SKELETON_CURVED_SWORDS.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                level.getLevel().addFreshEntity(mob);
            }
        }
    }

    @Override
    protected int getDSDefaultTeam() {
        return 6;
    }
}
