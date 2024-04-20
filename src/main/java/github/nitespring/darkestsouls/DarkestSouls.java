package github.nitespring.darkestsouls;

import com.mojang.logging.LogUtils;
import github.nitespring.darkestsouls.config.Config;
import github.nitespring.darkestsouls.core.init.*;
import github.nitespring.darkestsouls.networking.DarkestSoulsPacketHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

@Mod(DarkestSouls.MODID)
public class DarkestSouls
{
    public static final String MODID = "darkestsouls";

    public static final Logger LOGGER = LogUtils.getLogger();

    public DarkestSouls()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.common_config);
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::setup);
        GeckoLib.initialize();
        SoundInit.SOUNDS.register(modEventBus);
        ItemInit.ITEMS.register(modEventBus);
        EntityInit.ENTITIES.register(modEventBus);
        EffectInit.EFFECTS.register(modEventBus);
        CreativeTabInit.TABS.register(modEventBus);
        EnchantmentInit.ENCHANTMENTS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);

        
        //ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void setup(final FMLCommonSetupEvent event)
    {

        DarkestSoulsPacketHandler.register();

    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
           
        }
    }
}
