package github.nitespring.darkestsouls.core.event;



import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod.EventBusSubscriber(modid = DarkestSouls.MODID, bus = EventBusSubscriber.Bus.MOD)
public class CreativeTabsRegistration {
	
	
	 //public static CreativeModeTab ITEM_TAB;
	 //public static CreativeModeTab ENTITY_TAB;

	  @SubscribeEvent
	  public static void addItemsToTabs(BuildCreativeModeTabContentsEvent event)
	    {
	        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS)
	        {
	            event.accept(ItemInit.SIN);
				event.accept(ItemInit.BONEWHEEL);
				event.accept(ItemInit.SEWER_CENTIPEDE);
	            
	        }
	        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS)
	        {
	            event.accept(ItemInit.SOUL_FRAGMENT);
				event.accept(ItemInit.DARK_FRAGMENT);
				event.accept(ItemInit.SIDERITE_FRAGMENT);
				event.accept(ItemInit.BLOOD_STONE_FRAGMENT);
				event.accept(ItemInit.BLOOD_GEM);
				event.accept(ItemInit.BEAST_BLOOD_CLUMP);
				event.accept(ItemInit.QUICKSILVER);
				event.accept(ItemInit.STEEL_NUGGET);
				event.accept(ItemInit.STEEL_INGOT);
				event.accept(ItemInit.SIDERITE_NUGGET);
				event.accept(ItemInit.SIDERITE_INGOT);
				event.accept(ItemInit.SOUL_ESSENCE);
				event.accept(ItemInit.DARK_ESSENCE);
				event.accept(ItemInit.FIRE_ESSENCE);
				event.accept(ItemInit.CHAOS_ESSENCE);
				event.accept(ItemInit.LIGHTNING_ESSENCE);
				event.accept(ItemInit.LIGHT_ESSENCE);
				event.accept(ItemInit.BLOOD_ESSENCE);
				event.accept(ItemInit.POISON_ESSENCE);
				event.accept(ItemInit.FROST_ESSENCE);
				event.accept(ItemInit.ROT_ESSENCE);
				event.accept(ItemInit.BEAST_ESSENCE);
				event.accept(ItemInit.NIGHTMARE_ESSENCE);
				event.accept(ItemInit.EYE);
				event.accept(ItemInit.BEAST_EYE);
				event.accept(ItemInit.BLIND_EYE);
				event.accept(ItemInit.BLOSSOMED_EYE);
				event.accept(ItemInit.BLOOD_STONE_SHARD);
				event.accept(ItemInit.TWIN_BLOOD_STONE_SHARDS);
				event.accept(ItemInit.BLOOD_STONE_CHUNK);
				event.accept(ItemInit.BLOOD_ROCK);
	        }
	        if (event.getTabKey() == CreativeModeTabs.COMBAT)
	        {
				event.accept(ItemInit.FRAYED_BLADE);
	            event.accept(ItemInit.SCIMITAR);
				event.accept(ItemInit.FALCHION);
				event.accept(ItemInit.CLAYMORE);
				event.accept(ItemInit.FLAMBERGE);
				event.accept(ItemInit.ZWEIHANDER);
	            
	        }
	      
	    }
	
	
	

}
