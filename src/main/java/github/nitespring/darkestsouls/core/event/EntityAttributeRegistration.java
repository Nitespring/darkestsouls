package github.nitespring.darkestsouls.core.event;


import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.core.init.EntityInit;
import github.nitespring.darkestsouls.core.util.EntityAttributes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod.EventBusSubscriber(modid = DarkestSouls.MODID, bus = EventBusSubscriber.Bus.MOD)
public class EntityAttributeRegistration {
	
	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		

		event.put(EntityInit.SIN.get(), EntityAttributes.setMonstruosityOfSinAttributes().build());
		event.put(EntityInit.BONEWHEEL.get(), EntityAttributes.setBonewheelAttributes().build());
		event.put(EntityInit.SEWER_CENTIPEDE.get(), EntityAttributes.setSewerCentipedeAttributes().build());
		event.put(EntityInit.SKELETON_FALCHION.get(), EntityAttributes.setSkeletonFalchionAttributes().build());
		event.put(EntityInit.SKELETON_CURVED_SWORDS.get(), EntityAttributes.setSkeletonCurvedSwordsAttributes().build());
		event.put(EntityInit.HOLLOW_LONGSWORD.get(), EntityAttributes.setHollowSoldierLongswordAttributes().build());
		event.put(EntityInit.HOLLOW_BROKEN_STRAIGHTSWORD.get(), EntityAttributes.setMadHollowBrokenStraightswordAttributes().build());
		event.put(EntityInit.GRAVETENDER_HOLLOW_LONGSWORD.get(), EntityAttributes.setGravetenderHollowLongswordAttributes().build());
		event.put(EntityInit.GRAVETENDER_HOLLOW_BROKEN_STRAIGHTSWORD.get(), EntityAttributes.setGravetenderHollowBrokenStraightswordAttributes().build());
		event.put(EntityInit.BEAST_PATIENT.get(), EntityAttributes.setBeastPatientAttributes().build());
		event.put(EntityInit.CLOAKED_BEAST_PATIENT.get(), EntityAttributes.setCloakedBeastPatientAttributes().build());
		event.put(EntityInit.ASHEN_BLOOD_BEAST_PATIENT.get(), EntityAttributes.setAshenBloodBeastPatientAttributes().build());
		event.put(EntityInit.LEECH.get(), EntityAttributes.setLeechAttributes().build());


	}

}
