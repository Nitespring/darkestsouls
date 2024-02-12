package github.nitespring.darkestsouls.networking;





import github.nitespring.darkestsouls.DarkestSouls;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;


public class DarkestSoulsPacketHandler {
	
	
	
	 private static final SimpleChannel INSTANCE = ChannelBuilder.named(
	            new ResourceLocation(DarkestSouls.MODID, "main"))
	            .serverAcceptedVersions((status, version) -> true)
	            .clientAcceptedVersions((status, version) -> true)
	            .networkProtocolVersion(1)
	            .simpleChannel();
	
	
	 public static void register() {
	        INSTANCE.messageBuilder(ItemLeftClickAction.class, NetworkDirection.PLAY_TO_SERVER)
	                .encoder(ItemLeftClickAction::encode)
	                .decoder(ItemLeftClickAction::new)
	                .consumerMainThread(ItemLeftClickAction::handle)
	                .add();
		 	INSTANCE.messageBuilder(TransformWeaponAction.class, NetworkDirection.PLAY_TO_SERVER)
				 .encoder(TransformWeaponAction::encode)
				 .decoder(TransformWeaponAction::new)
				 .consumerMainThread(TransformWeaponAction::handle)
				 .add();
	    }

	    public static void sendToServer(Object msg) {
	        INSTANCE.send(msg, PacketDistributor.SERVER.noArg());
	    }

	    public static void sendToPlayer(Object msg, ServerPlayer player) {
	        INSTANCE.send(msg, PacketDistributor.PLAYER.with(player));
	    }

	    public static void sendToAllClients(Object msg) {
	        INSTANCE.send(msg, PacketDistributor.ALL.noArg());
	    }
	

	

}
