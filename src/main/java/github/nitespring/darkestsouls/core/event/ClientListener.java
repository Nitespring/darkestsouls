package github.nitespring.darkestsouls.core.event;



import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.client.render.entity.mob.abyss.MonstruosityOfSinGeoRenderer;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DarkestSouls.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientListener {
	
	 @SubscribeEvent
	 	public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
		 
		 event.registerEntityRenderer(EntityInit.SIN.get(), MonstruosityOfSinGeoRenderer::new);

		 
	 }

}
