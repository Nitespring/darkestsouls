package github.nitespring.darkestsouls.networking;



import github.nitespring.darkestsouls.common.item.ILeftClickItem;
import github.nitespring.darkestsouls.common.item.ITransformableItem;
import github.nitespring.darkestsouls.common.item.TrickWeapon;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;


public class TransformWeaponAction {

			private final int id;

	        public TransformWeaponAction(FriendlyByteBuf buf) {
				id = buf.readInt();
			}
	        public TransformWeaponAction(int id) {
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

					if(mainHand.getItem() instanceof ITransformableItem) {

						((ITransformableItem)mainHand.getItem()).transform(playerIn, playerIn.level());
						//((TrickWeapon)mainHand.getItem()).doLeftClickAction(playerIn, mainHand);

					}
				});
	        } 
	      
}

