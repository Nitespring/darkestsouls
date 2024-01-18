package github.nitespring.darkestsouls.core.init;

import github.nitespring.darkestsouls.DarkestSouls;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			DarkestSouls.MODID);
	
	
	//Items
	/*
	public static final RegistryObject<Item> SNOWMAN = ITEMS.register("evil_snowman_spawn_egg", 
			() -> new ForgeSpawnEggItem(EntityInit.SNOWMAN, 14283506, 16737400, new Item.Properties()));
	*/
	/*
	public static final RegistryObject<Item> SNOWFLAKE = ITEMS.register("snowflake", 
			() -> new Item(new Item.Properties()));
	*/
	/*
	public static final RegistryObject<CandybarGreatsword> CANDY_SWORD = ITEMS.register("candy_sword", 
			() -> new CandybarGreatsword(YuleTiers.CANDY, 3, -2.5F, new Item.Properties()));
	*/

}
