package github.nitespring.darkestsouls.networking;



import github.nitespring.darkestsouls.common.item.ILeftClickItem;
import github.nitespring.darkestsouls.common.item.Weapon;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;


public class ItemLeftClickAction {

			private final int id;
	      

	        public ItemLeftClickAction(FriendlyByteBuf buf) {
				id = buf.readInt();
			}
	        public ItemLeftClickAction(int id) {
				this.id = id;
			}
	        //public void toBytes(FriendlyByteBuf buf) {}
	        public void encode(FriendlyByteBuf buf) {
				buf.writeInt(id);
			}

	        public void handle(Supplier<NetworkEvent.Context> ctx) {
				ctx.get().enqueueWork(() -> {
	            	Player playerIn = ctx.get().getSender();
	            	if (playerIn==null)
	            			return;
	            	ItemStack mainHand = playerIn.getMainHandItem();
	    			
	    			if(mainHand.getItem() instanceof ILeftClickItem) {
	    				  if (playerIn.getAttackStrengthScale(0)>=0.8) {
	    			((ILeftClickItem)mainHand.getItem()).doLeftClickAction(playerIn, mainHand);
	    				  }
	    			}

				});
	            
	        } 
	      
}

