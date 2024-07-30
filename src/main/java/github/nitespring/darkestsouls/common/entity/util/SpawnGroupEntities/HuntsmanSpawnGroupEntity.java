package github.nitespring.darkestsouls.common.entity.util.SpawnGroupEntities;

import github.nitespring.darkestsouls.common.entity.mob.church.HuntsmanAxe;
import github.nitespring.darkestsouls.common.entity.mob.church.HuntsmanCutlass;
import github.nitespring.darkestsouls.common.entity.mob.church.HuntsmanPitchfork;
import github.nitespring.darkestsouls.common.entity.mob.church.HuntsmanRifle;
import github.nitespring.darkestsouls.common.entity.util.SpawnGroupEntity;
import github.nitespring.darkestsouls.config.CommonConfig;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class HuntsmanSpawnGroupEntity extends SpawnGroupEntity{


    public HuntsmanSpawnGroupEntity(EntityType<? extends PathfinderMob> mob, Level level) {
        super(mob, level);
    }

    @Override
    public void doMobSpawns(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        Random r = new Random();
        int size = r.nextInt(4);
        if(CommonConfig.spawn_huntsman_axe.get()||spawnType==MobSpawnType.SPAWN_EGG) {
            for (int i = 0; i <= r.nextInt(2 + size * 2) + r.nextInt(3) - 2; i++) {
                HuntsmanAxe mob = new HuntsmanAxe(EntityInit.HUNTSMAN_AXE.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                level.getLevel().addFreshEntity(mob);
            }
        }
        if(CommonConfig.spawn_huntsman_cutlass.get()||spawnType==MobSpawnType.SPAWN_EGG) {
            for (int i = 0; i <= r.nextInt(1 + size * 3) + r.nextInt(3) - 2; i++) {
                HuntsmanCutlass mob = new HuntsmanCutlass(EntityInit.HUNTSMAN_CUTLASS.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                level.getLevel().addFreshEntity(mob);
            }
        }
        if(CommonConfig.spawn_huntsman_pitchfork.get()||spawnType==MobSpawnType.SPAWN_EGG) {
            for (int i = 0; i <= r.nextInt(1 + size * 2) + r.nextInt(3) - 2; i++) {
                HuntsmanPitchfork mob = new HuntsmanPitchfork(EntityInit.HUNTSMAN_PITCHFORK.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                level.getLevel().addFreshEntity(mob);
            }
        }
        if(CommonConfig.spawn_huntsman_rifle.get()||spawnType==MobSpawnType.SPAWN_EGG) {
            for (int i = 0; i <= r.nextInt(2 + size); i++) {
                HuntsmanRifle mob = new HuntsmanRifle(EntityInit.HUNTSMAN_RIFLE.get(), level.getLevel());
                mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
                finalizeMobSpawn(mob);
                level.getLevel().addFreshEntity(mob);
            }
        }
    }

    @Override
    protected int getDSDefaultTeam() {
        return 3;
    }
}
