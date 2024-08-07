package github.nitespring.darkestsouls.core.init;


import cpw.mods.util.Lazy;
import github.nitespring.darkestsouls.DarkestSouls;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = DarkestSouls.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KeybindInit {
	//public static KeyMapping trickKeybind;
	//public static KeyMapping parryKeyBind;

	/*public static void register()
    {

        trickKeybind = new KeyMapping("key.trick", GLFW.GLFW_KEY_LEFT_ALT, "key.categories.misc");
       //.registerKeyBinding(trickKeybind);
     reloadKeybind = new KeyMapping("key.reload", GLFW.GLFW_KEY_R, "key.categories.misc");
    //.registerKeyBinding(reloadKeybind);

    }*/
	public static final Lazy<KeyMapping> TRICK_KEYBIND = Lazy.of(()->new KeyMapping("key.transform", GLFW.GLFW_KEY_LEFT_ALT, "key.categories.misc"));

	@SubscribeEvent
	public static void registerKeyBinds(RegisterKeyMappingsEvent event) {

		event.register(TRICK_KEYBIND.get());

		//event.register(parryKeyBind = new KeyMapping("key.parry", GLFW.GLFW_KEY_R, "key.categories.misc"));

	}
	
}
