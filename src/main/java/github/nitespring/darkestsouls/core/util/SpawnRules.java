package github.nitespring.darkestsouls.core.util;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.config.CommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.common.Tags;

import java.util.function.Predicate;

public class SpawnRules{


    public static boolean checkBeastPatientSpawnRules(EntityType<? extends DarkestSoulsAbstractEntity> mob, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return checkGeneralBeastPatientSpawnRules(mob, levelAccessor, spawnType, pos, random) && CommonConfig.spawn_beast_patient.get();
    }
    public static boolean checkCloakedBeastPatientSpawnRules(EntityType<? extends DarkestSoulsAbstractEntity> mob, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return checkGeneralBeastPatientSpawnRules(mob, levelAccessor, spawnType, pos, random) && CommonConfig.spawn_cloaked_beast_patient.get();
    }
    public static boolean checkAshenBloodBeastPatientSpawnRules(EntityType<? extends DarkestSoulsAbstractEntity> mob, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return checkGeneralBeastPatientSpawnRules(mob, levelAccessor, spawnType, pos, random) && CommonConfig.spawn_ashen_blood_beast_patient.get();
    }
    public static boolean checkGeneralBeastPatientSpawnRules(EntityType<? extends DarkestSoulsAbstractEntity> mob, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return checkVanillaMonsterSpawnRules(mob, levelAccessor, spawnType, pos, random);
    }
    public static boolean checkMadHollowSpawnRules(EntityType<? extends DarkestSoulsAbstractEntity> mob, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return CommonConfig.spawn_mad_hollow.get() && levelAccessor.getDifficulty() != Difficulty.PEACEFUL && levelAccessor.canSeeSky(pos) && !levelAccessor.getBiome(pos).containsTag(Tags.Biomes.IS_DESERT)
                && ((levelAccessor.getBiome(pos).containsTag(Tags.Biomes.IS_DENSE))
                || isDarkEnoughToSpawnForVanilla(levelAccessor, pos, random))
                && checkVanillaMobSpawnRules(mob, levelAccessor, spawnType, pos, random);
    }
    public static boolean checkHollowSoldierSpawnRules(EntityType<? extends DarkestSoulsAbstractEntity> mob, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return checkHollowSpawnRules(mob, levelAccessor, spawnType, pos, random) && CommonConfig.spawn_hollow_longsword.get();
    }
    public static boolean checkGravetenderHollowSpawnRules(EntityType<? extends DarkestSoulsAbstractEntity> mob, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return checkHollowSpawnRules(mob, levelAccessor, spawnType, pos, random) && CommonConfig.spawn_gravetender_hollow.get();
    }

    public static boolean checkHollowSpawnRules(EntityType<? extends DarkestSoulsAbstractEntity> mob, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return levelAccessor.getDifficulty() != Difficulty.PEACEFUL && levelAccessor.canSeeSky(pos) && !levelAccessor.getBiome(pos).containsTag(Tags.Biomes.IS_DESERT)
                && ((levelAccessor.getBiome(pos).containsTag(Tags.Biomes.IS_DENSE)&&isDarkEnoughToSpawnLowLight(levelAccessor, pos, random))
                || isDarkEnoughToSpawnForVanilla(levelAccessor, pos, random))
                && checkVanillaMobSpawnRules(mob, levelAccessor, spawnType, pos, random);
    }

    public static boolean checkSewerCentipedeSpawnRules(EntityType<? extends DarkestSoulsAbstractEntity> mob, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return (isDeepEnoughForDeepMob(pos)||levelAccessor.getBiome(pos).containsTag(Tags.Biomes.IS_SWAMP))
                &&checkVanillaAnyLightMonsterSpawnRules(mob, levelAccessor, spawnType, pos, random) && CommonConfig.spawn_sewer_centipede.get();
    }
    public static boolean checkLeechSpawnRules(EntityType<? extends DarkestSoulsAbstractEntity> mob, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return (isDeepEnoughForDeepMob(pos)||(levelAccessor.getBiome(pos).containsTag(Tags.Biomes.IS_SWAMP)||levelAccessor.getBiome(pos).containsTag(Tags.Biomes.IS_WATER)))
                &&checkVanillaAnyLightMonsterSpawnRules(mob, levelAccessor, spawnType, pos, random) && CommonConfig.spawn_leech.get();
    }
    public static boolean checkSinSpawnRules(EntityType<? extends DarkestSoulsAbstractEntity> mob, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return isDeepEnoughForDeepMob(pos)
                &&checkVanillaMonsterSpawnRules(mob, levelAccessor, spawnType, pos, random) && CommonConfig.spawn_monstruosity_of_sin.get();
    }
    public static boolean checkSkeletonFalchionSpawnRules(EntityType<? extends DarkestSoulsAbstractEntity> mob, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return checkSkeletonSpawnRules(mob, levelAccessor, spawnType, pos, random) && CommonConfig.spawn_skeleton_falchion.get();
    }
    public static boolean checkSkeletonSpearSpawnRules(EntityType<? extends DarkestSoulsAbstractEntity> mob, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return checkSkeletonSpawnRules(mob, levelAccessor, spawnType, pos, random) && CommonConfig.spawn_skeleton_spear.get();
    }
    public static boolean checkSkeletonCurvedSwordsSpawnRules(EntityType<? extends DarkestSoulsAbstractEntity> mob, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return checkSkeletonSpawnRules(mob, levelAccessor, spawnType, pos, random) && CommonConfig.spawn_skeleton_curved_swords.get();
    }
    public static boolean checkBonewheelSpawnRules(EntityType<? extends DarkestSoulsAbstractEntity> mob, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return checkSkeletonSpawnRules(mob, levelAccessor, spawnType, pos, random) && CommonConfig.spawn_bonewheel.get();
    }
    public static boolean checkSkeletonSpawnRules(EntityType<? extends DarkestSoulsAbstractEntity> mob, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return isDeepEnoughForSkeleton(pos)
                &&checkLowLightMonsterSpawnRules(mob, levelAccessor, spawnType, pos, random);
    }
    public static boolean isDeepEnoughForSkeleton(BlockPos pos){
        return pos.getY()<=48;
    }

    public static boolean isDeepEnoughForDeepMob(BlockPos pos){
        return pos.getY()<=8;
    }

    public static boolean checkSurfaceMonsterSpawnRules(EntityType<? extends DarkestSoulsAbstractEntity> mob, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return levelAccessor.getDifficulty() != Difficulty.PEACEFUL && levelAccessor.canSeeSky(pos)
                && isDarkEnoughToSpawnForVanilla(levelAccessor, pos, random)
                && checkVanillaMobSpawnRules(mob, levelAccessor, spawnType, pos, random);
    }
    public static boolean checkLowLightMonsterSpawnRules(EntityType<? extends DarkestSoulsAbstractEntity> mob, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return levelAccessor.getDifficulty() != Difficulty.PEACEFUL
                && isDarkEnoughToSpawnLowLight(levelAccessor, pos, random)
                && checkVanillaMobSpawnRules(mob, levelAccessor, spawnType, pos, random);
    }
    public static boolean checkVanillaMonsterSpawnRules(EntityType<? extends DarkestSoulsAbstractEntity> mob, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return levelAccessor.getDifficulty() != Difficulty.PEACEFUL
                && isDarkEnoughToSpawnForVanilla(levelAccessor, pos, random)
                && checkVanillaMobSpawnRules(mob, levelAccessor, spawnType, pos, random);
    }
    public static boolean checkVanillaAnyLightMonsterSpawnRules(EntityType<? extends DarkestSoulsAbstractEntity> mob, LevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return levelAccessor.getDifficulty() != Difficulty.PEACEFUL
                && checkVanillaMobSpawnRules(mob, levelAccessor, spawnType, pos, random);
    }
    public static boolean checkVanillaMobSpawnRules(EntityType<? extends DarkestSoulsAbstractEntity> mob, LevelAccessor p_217059_, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        BlockPos blockpos = pos.below();
        return spawnType == MobSpawnType.SPAWNER
                || p_217059_.getBlockState(blockpos).isValidSpawn(p_217059_, blockpos, mob);
    }

    public static boolean isDarkEnoughToSpawnLowLight(ServerLevelAccessor levelAccessor, BlockPos pos, RandomSource random) {
        if (levelAccessor.getBrightness(LightLayer.SKY, pos) > random.nextInt(32)) {
            return false;
        } else {
            DimensionType dimensiontype = levelAccessor.dimensionType();
            int i = dimensiontype.monsterSpawnBlockLightLimit()+5;
            if (i < 15 && levelAccessor.getBrightness(LightLayer.BLOCK, pos) > i) {
                return false;
            } else {
                int j = levelAccessor.getLevel().isThundering() ? levelAccessor.getMaxLocalRawBrightness(pos, 13) : levelAccessor.getMaxLocalRawBrightness(pos);
                return j <= dimensiontype.monsterSpawnLightTest().sample(random)+5;
            }
        }
    }
    public static boolean isDarkEnoughToSpawnForVanilla(ServerLevelAccessor levelAccessor, BlockPos pos, RandomSource random) {
        if (levelAccessor.getBrightness(LightLayer.SKY, pos) > random.nextInt(32)) {
            return false;
        } else {
            DimensionType dimensiontype = levelAccessor.dimensionType();
            int i = dimensiontype.monsterSpawnBlockLightLimit();
            if (i < 15 && levelAccessor.getBrightness(LightLayer.BLOCK, pos) > i) {
                return false;
            } else {
                int j = levelAccessor.getLevel().isThundering() ? levelAccessor.getMaxLocalRawBrightness(pos, 10) : levelAccessor.getMaxLocalRawBrightness(pos);
                return j <= dimensiontype.monsterSpawnLightTest().sample(random);
            }
        }
    }

}
