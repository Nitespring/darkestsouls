package github.nitespring.darkestsouls.core.event;


import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.core.init.EntityInit;
import github.nitespring.darkestsouls.core.util.SpawnRules;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = DarkestSouls.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntitySpawnRegistration {
	
	@SuppressWarnings("deprecation")
	@SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SpawnPlacements.register(EntityInit.BEAST_PATIENT.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkBeastPatientSpawnRules);
            SpawnPlacements.register(EntityInit.CLOAKED_BEAST_PATIENT.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkBeastPatientSpawnRules);
            SpawnPlacements.register(EntityInit.ASHEN_BLOOD_BEAST_PATIENT.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkBeastPatientSpawnRules);

            SpawnPlacements.register(EntityInit.HOLLOW_BROKEN_STRAIGHTSWORD.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkHollowSpawnRules);
            SpawnPlacements.register(EntityInit.GRAVETENDER_HOLLOW_BROKEN_STRAIGHTSWORD.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkHollowSpawnRules);
            SpawnPlacements.register(EntityInit.GRAVETENDER_HOLLOW_LONGSWORD.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkHollowSpawnRules);
            SpawnPlacements.register(EntityInit.HOLLOW_LONGSWORD.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkHollowSpawnRules);

            SpawnPlacements.register(EntityInit.SKELETON_FALCHION.get(),
            		SpawnPlacements.Type.ON_GROUND, 
            		Types.MOTION_BLOCKING_NO_LEAVES, 
            		SpawnRules::checkSkeletonSpawnRules);
            SpawnPlacements.register(EntityInit.SKELETON_CURVED_SWORDS.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkSkeletonSpawnRules);
            SpawnPlacements.register(EntityInit.BONEWHEEL.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkSkeletonSpawnRules);

            SpawnPlacements.register(EntityInit.SEWER_CENTIPEDE.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkSewerCentipedeSpawnRules);
            SpawnPlacements.register(EntityInit.LEECH.get(),
                    SpawnPlacements.Type.NO_RESTRICTIONS,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkLeechSpawnRules);
            SpawnPlacements.register(EntityInit.SIN.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkSinSpawnRules);
            
      
        });
    }
	
	
	

}
