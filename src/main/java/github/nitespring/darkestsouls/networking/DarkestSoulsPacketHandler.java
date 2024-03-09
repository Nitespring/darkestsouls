package github.nitespring.darkestsouls.networking;





import github.nitespring.darkestsouls.DarkestSouls;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;


public class DarkestSoulsPacketHandler {



	public static SimpleChannel INSTANCE;
	private static final String PROTOCOL_VERSION = "1";
	private static int ID = 0;
	private static int nextID() {
		return ID++;
	}
	
	
	 public static void register() {
		 INSTANCE = NetworkRegistry.newSimpleChannel(
				 new ResourceLocation(DarkestSouls.MODID, "main"),
				 () -> PROTOCOL_VERSION,
				 PROTOCOL_VERSION::equals,
				 PROTOCOL_VERSION::equals
		 );

		 INSTANCE.messageBuilder(ItemLeftClickAction.class, nextID())
				 .encoder(ItemLeftClickAction::encode)
				 .decoder(ItemLeftClickAction::new)
				 .consumerMainThread(ItemLeftClickAction::handle)
				 .add();
		 INSTANCE.messageBuilder(TransformWeaponAction.class, nextID())
				 .encoder(TransformWeaponAction::encode)
				 .decoder(TransformWeaponAction::new)
				 .consumerMainThread(TransformWeaponAction::handle)
				 .add();


	    }

	public static void sendToServer(Object packet) {
		INSTANCE.sendToServer(packet);
	}
	public static void sendToClient(Object packet, ServerPlayer player) {
		INSTANCE.sendTo(packet, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
	}
	

}
