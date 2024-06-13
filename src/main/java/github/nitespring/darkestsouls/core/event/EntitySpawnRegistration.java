package github.nitespring.darkestsouls.core.event;


import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.core.init.EntityInit;
import github.nitespring.darkestsouls.core.util.SpawnRules;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = DarkestSouls.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntitySpawnRegistration {
	
	@SuppressWarnings("deprecation")
	@SubscribeEvent
    public static void registerEntitySpawn(SpawnPlacementRegisterEvent event) {
        event.register(EntityInit.BEAST_PATIENT.get(),
                    SpawnPlacementTypes.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkBeastPatientSpawnRules,
                SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(EntityInit.CLOAKED_BEAST_PATIENT.get(),
                    SpawnPlacementTypes.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkCloakedBeastPatientSpawnRules,
                SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(EntityInit.ASHEN_BLOOD_BEAST_PATIENT.get(),
                    SpawnPlacementTypes.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkAshenBloodBeastPatientSpawnRules,
                SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(EntityInit.HOLLOW_BROKEN_STRAIGHTSWORD.get(),
                    SpawnPlacementTypes.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkMadHollowSpawnRules,
                SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(EntityInit.GRAVETENDER_HOLLOW_BROKEN_STRAIGHTSWORD.get(),
                    SpawnPlacementTypes.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkGravetenderHollowSpawnRules,
                SpawnPlacementRegisterEvent.Operation.REPLACE);
            event.register(EntityInit.GRAVETENDER_HOLLOW_LONGSWORD.get(),
                    SpawnPlacementTypes.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkGravetenderHollowSpawnRules,
                    SpawnPlacementRegisterEvent.Operation.REPLACE);
            event.register(EntityInit.HOLLOW_LONGSWORD.get(),
                    SpawnPlacementTypes.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkHollowLongswordSpawnRules,
                    SpawnPlacementRegisterEvent.Operation.REPLACE);
            event.register(EntityInit.HOLLOW_AXE.get(),
                    SpawnPlacementTypes.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkHollowAxeSpawnRules,
                    SpawnPlacementRegisterEvent.Operation.REPLACE);
            event.register(EntityInit.HOLLOW_ASSASSIN.get(),
                    SpawnPlacementTypes.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkHollowAssassinSpawnRules,
                    SpawnPlacementRegisterEvent.Operation.REPLACE);
            event.register(EntityInit.SKELETON_FALCHION.get(),
            		SpawnPlacementTypes.ON_GROUND, 
            		Types.MOTION_BLOCKING_NO_LEAVES, 
            		SpawnRules::checkSkeletonFalchionSpawnRules,
                    SpawnPlacementRegisterEvent.Operation.REPLACE);
            event.register(EntityInit.SKELETON_CURVED_SWORDS.get(),
                    SpawnPlacementTypes.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkSkeletonCurvedSwordsSpawnRules,
                    SpawnPlacementRegisterEvent.Operation.REPLACE);
            event.register(EntityInit.SKELETON_SPEAR.get(),
                    SpawnPlacementTypes.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkSkeletonSpearSpawnRules,
                    SpawnPlacementRegisterEvent.Operation.REPLACE);
            event.register(EntityInit.BONEWHEEL.get(),
                    SpawnPlacementTypes.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkBonewheelSpawnRules,
                    SpawnPlacementRegisterEvent.Operation.REPLACE);
            event.register(EntityInit.TALL_SKELETON_TWIN_SHOTELS.get(),
                    SpawnPlacementTypes.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkSkeletonSwordsmanTwinShotelsSpawnRules,
                    SpawnPlacementRegisterEvent.Operation.REPLACE);
            event.register(EntityInit.CHURCH_DOCTOR.get(),
                    SpawnPlacementTypes.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkChurchDoctorNormalSpawnRules,
                    SpawnPlacementRegisterEvent.Operation.REPLACE);
            event.register(EntityInit.CHURCH_DOCTOR_LANTERN.get(),
                    SpawnPlacementTypes.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkChurchDoctorNormalSpawnRules,
                    SpawnPlacementRegisterEvent.Operation.REPLACE);
            event.register(EntityInit.CHURCH_DOCTOR_SCYTHE.get(),
                    SpawnPlacementTypes.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkChurchDoctorScytheSpawnRules,
                    SpawnPlacementRegisterEvent.Operation.REPLACE);
            event.register(EntityInit.CHURCH_DOCTOR_CRUCIFIX.get(),
                    SpawnPlacementTypes.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkChurchDoctorCrucifixSpawnRules,
                    SpawnPlacementRegisterEvent.Operation.REPLACE);
            event.register(EntityInit.CHURCH_DOCTOR_PISTOL.get(),
                    SpawnPlacementTypes.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkChurchDoctorPistolSpawnRules,
                    SpawnPlacementRegisterEvent.Operation.REPLACE);
            event.register(EntityInit.CHURCH_DOCTOR_FLAMESPRAYER.get(),
                    SpawnPlacementTypes.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkChurchDoctorFlamesprayerSpawnRules,
                    SpawnPlacementRegisterEvent.Operation.REPLACE);
            event.register(EntityInit.SEWER_CENTIPEDE.get(),
                    SpawnPlacementTypes.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkSewerCentipedeSpawnRules,
                    SpawnPlacementRegisterEvent.Operation.REPLACE);
            event.register(EntityInit.LEECH.get(),
                    SpawnPlacementTypes.NO_RESTRICTIONS,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkLeechSpawnRules,
                    SpawnPlacementRegisterEvent.Operation.REPLACE);
            event.register(EntityInit.SIN.get(),
                    SpawnPlacementTypes.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkSinSpawnRules,
                    SpawnPlacementRegisterEvent.Operation.REPLACE);
            event.register(EntityInit.DARKWRAITH.get(),
                    SpawnPlacementTypes.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkDarkwraithSpawnRules,
                    SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
	
	
	

}
