package github.nitespring.darkestsouls.core.event;


import github.nitespring.darkestsouls.DarkestSouls;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod.EventBusSubscriber(modid = DarkestSouls.MODID, bus = EventBusSubscriber.Bus.MOD)
public class EntityAttributeRegistration {
	
	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		

		//event.put(EntityInit.SNOWMAN.get(), EvilSnowman.setCustomAttributes().build());
		
	}

}
