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
                    SpawnRules::checkCloakedBeastPatientSpawnRules);
            SpawnPlacements.register(EntityInit.ASHEN_BLOOD_BEAST_PATIENT.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkAshenBloodBeastPatientSpawnRules);
            SpawnPlacements.register(EntityInit.HOLLOW_BROKEN_STRAIGHTSWORD.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkMadHollowSpawnRules);
            SpawnPlacements.register(EntityInit.GRAVETENDER_HOLLOW_BROKEN_STRAIGHTSWORD.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkGravetenderHollowSpawnRules);
            SpawnPlacements.register(EntityInit.GRAVETENDER_HOLLOW_LONGSWORD.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkGravetenderHollowSpawnRules);
            SpawnPlacements.register(EntityInit.HOLLOW_LONGSWORD.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkHollowLongswordSpawnRules);
            SpawnPlacements.register(EntityInit.HOLLOW_AXE.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkHollowAxeSpawnRules);
            SpawnPlacements.register(EntityInit.HOLLOW_ASSASSIN.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkHollowAssassinSpawnRules);
            SpawnPlacements.register(EntityInit.SKELETON_FALCHION.get(),
            		SpawnPlacements.Type.ON_GROUND, 
            		Types.MOTION_BLOCKING_NO_LEAVES, 
            		SpawnRules::checkSkeletonFalchionSpawnRules);
            SpawnPlacements.register(EntityInit.SKELETON_CURVED_SWORDS.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkSkeletonCurvedSwordsSpawnRules);
            SpawnPlacements.register(EntityInit.SKELETON_SPEAR.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkSkeletonSpearSpawnRules);
            SpawnPlacements.register(EntityInit.BONEWHEEL.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkBonewheelSpawnRules);
            SpawnPlacements.register(EntityInit.TALL_SKELETON_TWIN_SHOTELS.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkSkeletonSwordsmanTwinShotelsSpawnRules);
            SpawnPlacements.register(EntityInit.CHURCH_DOCTOR.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkChurchDoctorNormalSpawnRules);
            SpawnPlacements.register(EntityInit.CHURCH_DOCTOR_LANTERN.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkChurchDoctorNormalSpawnRules);
            SpawnPlacements.register(EntityInit.CHURCH_DOCTOR_SCYTHE.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkChurchDoctorScytheSpawnRules);
            SpawnPlacements.register(EntityInit.CHURCH_DOCTOR_CRUCIFIX.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkChurchDoctorCrucifixSpawnRules);
            SpawnPlacements.register(EntityInit.CHURCH_DOCTOR_PISTOL.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkChurchDoctorPistolSpawnRules);
            SpawnPlacements.register(EntityInit.CHURCH_DOCTOR_FLAMESPRAYER.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkChurchDoctorFlamesprayerSpawnRules);
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
            SpawnPlacements.register(EntityInit.DARKWRAITH.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Types.MOTION_BLOCKING_NO_LEAVES,
                    SpawnRules::checkDarkwraithSpawnRules);
            
      
        });
    }
	
	
	

}
