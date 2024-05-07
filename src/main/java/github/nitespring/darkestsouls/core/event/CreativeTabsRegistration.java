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

	  //@SubscribeEvent
	  public static void addItemsToTabs(BuildCreativeModeTabContentsEvent event)
	    {
	        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS)
	        {
				event.accept(ItemInit.HOLLOW_BROKEN_STRAIGHTSWORD);
				event.accept(ItemInit.GRAVETENDER_HOLLOW_BROKEN_STRAIGHTSWORD);
				event.accept(ItemInit.GRAVETENDER_HOLLOW_LONGSWORD);
				event.accept(ItemInit.HOLLOW_LONGSWORD);
				event.accept(ItemInit.SKELETON_FALCHION);
				event.accept(ItemInit.SKELETON_CURVED_SWORDS);
				event.accept(ItemInit.BONEWHEEL);
				event.accept(ItemInit.BEAST_PATIENT);
				event.accept(ItemInit.CLOAKED_BEAST_PATIENT);
				event.accept(ItemInit.ASHEN_BLOOD_BEAST_PATIENT);
				event.accept(ItemInit.LEECH);
				event.accept(ItemInit.SEWER_CENTIPEDE);
				event.accept(ItemInit.SIN);
	        }
	        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS)
	        {
				event.accept(ItemInit.SMALL_SOUL_FRAGMENT);
	            event.accept(ItemInit.SOUL_FRAGMENT);
				event.accept(ItemInit.TITANITE_FRAGMENT);
				event.accept(ItemInit.RUNE_FRAGMENT);
				event.accept(ItemInit.DARK_FRAGMENT);
				event.accept(ItemInit.BONE_FRAGMENT);
				event.accept(ItemInit.SIDERITE_FRAGMENT);
				event.accept(ItemInit.BLOOD_STONE_FRAGMENT);
				event.accept(ItemInit.CHAOS_ROOT);
				event.accept(ItemInit.BEWITCHED_BRANCH);
				event.accept(ItemInit.GREEN_CRYSTAL);
				event.accept(ItemInit.CRYSTAL);
				event.accept(ItemInit.PURPLE_CRYSTAL);
				event.accept(ItemInit.BLOOD_CRYSTAL);
				event.accept(ItemInit.DEMON_FRAGMENT);
				event.accept(ItemInit.TWINKLING_FRAGMENT);
				event.accept(ItemInit.DRAGON_SCALE_FRAGMENT);
				event.accept(ItemInit.CINNABAR);
				event.accept(ItemInit.CHAOS_FRAGMENT);
				event.accept(ItemInit.DEMON_TITANITE);
				event.accept(ItemInit.TWINKLING_TITANITE);
				event.accept(ItemInit.DRAGON_SCALE);
				event.accept(ItemInit.BLOOD_GEM);
				event.accept(ItemInit.BEAST_BLOOD_CLUMP);
				event.accept(ItemInit.QUICKSILVER);
				event.accept(ItemInit.TITANITE_INGOT);
				event.accept(ItemInit.TITANITE_NUGGET);
				event.accept(ItemInit.GOLDEN_INGOT);
				event.accept(ItemInit.GOLDEN_NUGGET);
				event.accept(ItemInit.STEEL_INGOT);
				event.accept(ItemInit.STEEL_NUGGET);
				event.accept(ItemInit.SIDERITE_INGOT);
				event.accept(ItemInit.SIDERITE_NUGGET);
				event.accept(ItemInit.DEMON_INGOT);
				event.accept(ItemInit.DEMON_NUGGET);
				event.accept(ItemInit.TWINKLING_INGOT);
				event.accept(ItemInit.TWINKLING_NUGGET);
				event.accept(ItemInit.DARK_INGOT);
				event.accept(ItemInit.DARK_NUGGET);
				event.accept(ItemInit.NIGHTMARE_INGOT);
				event.accept(ItemInit.NIGHTMARE_NUGGET);
				event.accept(ItemInit.BLOOD_INGOT);
				event.accept(ItemInit.BLOOD_NUGGET);
				event.accept(ItemInit.CHAOS_INGOT);
				event.accept(ItemInit.CHAOS_NUGGET);
				event.accept(ItemInit.HOLY_INGOT);
				event.accept(ItemInit.HOLY_NUGGET);
				event.accept(ItemInit.MAGIC_INGOT);
				event.accept(ItemInit.MAGIC_NUGGET);
				event.accept(ItemInit.DRAGON_INGOT);
				event.accept(ItemInit.DRAGON_NUGGET);
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
				event.accept(ItemInit.DRAGONSLAYER_SPEAR);
				event.accept(ItemInit.DRAGONSLAYER_SWORDSPEAR);
				event.accept(ItemInit.STORM_CURVED_SWORD);
				event.accept(ItemInit.SHADOW_BLADE);
				event.accept(ItemInit.SAW_CLEAVER);
				event.accept(ItemInit.HUNTER_AXE);
				event.accept(ItemInit.CHIKAGE);
				event.accept(ItemInit.BROKEN_STRAIGHTSWORD);
				event.accept(ItemInit.LONGSWORD);
	            event.accept(ItemInit.SCIMITAR);
				event.accept(ItemInit.FALCHION);
				event.accept(ItemInit.BANDIT_CURVED_SWORD);
				event.accept(ItemInit.UCHIGATANA);
				event.accept(ItemInit.CLAYMORE);
				event.accept(ItemInit.FLAMBERGE);
				event.accept(ItemInit.ZWEIHANDER);
				event.accept(ItemInit.SPEAR);
				event.accept(ItemInit.GRAVE_SCYTHE);
				event.accept(ItemInit.HUNTSMAN_CUTLASS);
				event.accept(ItemInit.HUNTSMAN_PITCHFORK);
				event.accept(ItemInit.HUNTSMAN_AXE);
				event.accept(ItemInit.HUNTER_TORCH);
				event.accept(ItemInit.CHURCH_SCYTHE);
				event.accept(ItemInit.HUNTER_PISTOL);
				event.accept(ItemInit.SORCERER_STAFF_A);
				event.accept(ItemInit.SORCERER_STAFF_B);
				event.accept(ItemInit.CRYSTAL_STAFF);
				event.accept(ItemInit.CRYSTAL_STAFF_PURPLE);
				event.accept(ItemInit.CRYSTAL_STAFF_BLUE);
				event.accept(ItemInit.CHAOS_STAFF);
	            
	        }
	      
	    }
	
	
	

}
