package github.nitespring.darkestsouls.common.entity.util.SpawnGroupEntities;

import github.nitespring.darkestsouls.common.entity.mob.church.HuntsmanAxe;
import github.nitespring.darkestsouls.common.entity.mob.hollow.GravetenderHollowBrokenStraightsword;
import github.nitespring.darkestsouls.common.entity.mob.hollow.GravetenderHollowCrossbow;
import github.nitespring.darkestsouls.common.entity.mob.hollow.GravetenderHollowLongsword;
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

public class GravetenderHollowSpawnGroupEntity extends SpawnGroupEntity{


    public GravetenderHollowSpawnGroupEntity(EntityType<? extends PathfinderMob> mob, Level level) {
        super(mob, level);
    }

    @Override
    public void doMobSpawns(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        Random r = new Random();
        int size = r.nextInt(4);
        if(CommonConfig.spawn_gravetender_hollow.get()||spawnType==MobSpawnType.SPAWN_EGG){
        for(int i=0; i<=r.nextInt(3+size*2)+r.nextInt(3)-2;i++){
            GravetenderHollowBrokenStraightsword mob = new GravetenderHollowBrokenStraightsword(EntityInit.GRAVETENDER_HOLLOW_BROKEN_STRAIGHTSWORD.get(), level.getLevel());
            mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
            finalizeMobSpawn(mob);
            level.getLevel().addFreshEntity(mob);
        }
        for(int i=0; i<=r.nextInt(3+size*2)+r.nextInt(3)-2;i++){
            GravetenderHollowLongsword mob = new GravetenderHollowLongsword(EntityInit.GRAVETENDER_HOLLOW_LONGSWORD.get(), level.getLevel());
            mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
            finalizeMobSpawn(mob);
            level.getLevel().addFreshEntity(mob);
        }
        for(int i=0; i<=r.nextInt(3+size)+r.nextInt(3)-2;i++){
            GravetenderHollowCrossbow mob = new GravetenderHollowCrossbow(EntityInit.GRAVETENDER_HOLLOW_CROSSBOW.get(), level.getLevel());
            mob.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
            finalizeMobSpawn(mob);
            level.getLevel().addFreshEntity(mob);
        }}
    }

    @Override
    protected int getDSDefaultTeam() {
        return 2;
    }
}
