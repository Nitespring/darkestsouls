package github.nitespring.darkestsouls.core.event;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.core.init.EffectInit;
import github.nitespring.darkestsouls.networking.DarkestSoulsPacketHandler;
import github.nitespring.darkestsouls.networking.ItemLeftClickAction;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

//@Mod.EventBusSubscriber(modid = DarkestSouls.MODID)
public class CommonEvents {



	public static void damagePoiseEvent(LivingEvent.LivingTickEvent e){
       LivingEntity living = e.getEntity();

	   if(living.hasEffect(EffectInit.BLEED.get())){

	   }

	}
	 
	 
	
	    
	    
	   
	   
	    
	    
	    
	    
	    
	    
}
