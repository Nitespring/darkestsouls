package github.nitespring.darkestsouls.core.event;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.core.init.KeybindInit;
import github.nitespring.darkestsouls.networking.DarkestSoulsPacketHandler;
import github.nitespring.darkestsouls.networking.ItemLeftClickAction;
import github.nitespring.darkestsouls.networking.TransformWeaponAction;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod;


@EventBusSubscriber(modid = DarkestSouls.MODID, value = Dist.CLIENT)
public class ClientEvents {
	
	private static boolean isAttackKeyDown =false;

	private static boolean isTrickKeyDown =false;
	
	@SubscribeEvent
	 public static void performItemLeftClickAction(TickEvent.ClientTickEvent event) {
	 Minecraft instance = Minecraft.getInstance();
	if(instance.options.keyAttack.isDown()) {
		 if(isAttackKeyDown==false) {
			 //instance.getConnection().send(new ItemLeftClickAction(1));
			 DarkestSoulsPacketHandler.sendToServer(new ItemLeftClickAction(1));
			 isAttackKeyDown=true;
		 }
	 }else {
		 isAttackKeyDown=false; 
	 }
	 
	 }

	@SubscribeEvent
	public static void trickKeybind(TickEvent.ClientTickEvent event) {
		if(KeybindInit.TRICK_KEYBIND.isDown()) {
			if(isTrickKeyDown==false) {
				DarkestSoulsPacketHandler.sendToServer(new TransformWeaponAction(1));
				isTrickKeyDown=true;
			}
		}else {
			isTrickKeyDown=false;
		}

	}


	    
	    
	   
	   
	    
	    
	    
	    
	    
	    
}
