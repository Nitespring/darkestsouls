package github.nitespring.darkestsouls.core.event;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.core.init.KeybindInit;
import github.nitespring.darkestsouls.networking.DarkestSoulsPacketHandler;
import github.nitespring.darkestsouls.networking.ItemLeftClickAction;
import github.nitespring.darkestsouls.networking.TransformWeaponAction;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = DarkestSouls.MODID, value = Dist.CLIENT)
public class ClientEvents {
	
	private static boolean isAttackKeyDown =false;

	private static boolean isTrickKeyDown =false;
	
	@SubscribeEvent
	 public static void performItemLeftClickAction(ClientTickEvent event) {
	 Minecraft instance = Minecraft.getInstance();
	if(instance.options.keyAttack.isDown()) {
		 if(isAttackKeyDown==false) {
			 //instance.getConnection().send(new ItemLeftClickAction(1));
			 DarkestSoulsPacketHandler.sendToServer(new ItemLeftClickAction());
			 isAttackKeyDown=true;
		 }
	 }else {
		 isAttackKeyDown=false; 
	 }
	 
	 }

	@SubscribeEvent
	public static void trickKeybind(ClientTickEvent event) {
		if(KeybindInit.trickKeybind.isDown()) {
			if(isTrickKeyDown==false) {
				DarkestSoulsPacketHandler.sendToServer(new TransformWeaponAction());
				isTrickKeyDown=true;
			}
		}else {
			isTrickKeyDown=false;
		}

	}
	 
	 
	
	    
	    
	   
	   
	    
	    
	    
	    
	    
	    
}
