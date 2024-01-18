package github.nitespring.darkestsouls.core.event;



import github.nitespring.darkestsouls.DarkestSouls;
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
	            //event.accept(ItemInit.SNOWMAN);
	            
	        }
	        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS)
	        {
	            //event.accept(ItemInit.CANDY_CLUMP);
	            
	        }
	        if (event.getTabKey() == CreativeModeTabs.COMBAT)
	        {
	            //event.accept(ItemInit.CANDY_SWORD);
	            
	        }
	      
	    }
	
	
	

}
