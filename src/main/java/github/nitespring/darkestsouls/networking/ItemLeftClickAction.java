package github.nitespring.darkestsouls.networking;



import github.nitespring.darkestsouls.common.item.Weapon;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.network.CustomPayloadEvent;


public class ItemLeftClickAction {
	       
	      

	        public ItemLeftClickAction(FriendlyByteBuf buf) {}
	        public ItemLeftClickAction() {}
	        //public void toBytes(FriendlyByteBuf buf) {}
	        public void encode(FriendlyByteBuf buffer) {}

	        public void handle(CustomPayloadEvent.Context ctx) {

	            	Player playerIn = ctx.getSender();
	            	if (playerIn==null)
	            			return;
	            	ItemStack mainHand = playerIn.getMainHandItem();
	    			
	    			if(mainHand.getItem() instanceof Weapon) {
	    				  if (playerIn.getAttackStrengthScale(0)>=0.8) {
	    			((Weapon)mainHand.getItem()).doLeftClickAction(playerIn, mainHand);
	    				  }
	    			}

	        	
	            
	        } 
	      
}
