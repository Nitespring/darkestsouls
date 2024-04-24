package github.nitespring.darkestsouls.networking;



import github.nitespring.darkestsouls.common.item.ILeftClickItem;
import github.nitespring.darkestsouls.common.item.ITransformableItem;
import github.nitespring.darkestsouls.common.item.TrickWeapon;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.network.CustomPayloadEvent;


public class TransformWeaponAction {



	        public TransformWeaponAction(FriendlyByteBuf buf) {}
	        public TransformWeaponAction() {}
	        //public void toBytes(FriendlyByteBuf buf) {}
	        public void encode(FriendlyByteBuf buffer) {}

	        public void handle(CustomPayloadEvent.Context ctx) {


				ctx.enqueueWork(() -> {
					Player playerIn = ctx.getSender();
					if (playerIn==null)
						return;

					ItemStack mainHand = playerIn.getMainHandItem();

					if(mainHand.getItem() instanceof ITransformableItem) {

						((ITransformableItem)mainHand.getItem()).transform(playerIn, playerIn.level());
						//((TrickWeapon)mainHand.getItem()).doLeftClickAction(playerIn, mainHand);

					}
				});
	        } 
	      
}

