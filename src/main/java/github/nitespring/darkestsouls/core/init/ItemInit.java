package github.nitespring.darkestsouls.core.init;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.item.FrayedBlade;
import github.nitespring.darkestsouls.common.item.Weapon;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			DarkestSouls.MODID);
	


	//Weapons

	public static final RegistryObject<FrayedBlade> SNOWFLAKE = ITEMS.register("frayed_blade",
			() -> new FrayedBlade(Tiers.DIAMOND,3, -2.8f, new Item.Properties()));
	public static final RegistryObject<Weapon> SCIMITAR = ITEMS.register("scimitar",
			() -> new Weapon(Tiers.DIAMOND,3, -2.8f, new Item.Properties()));
	public static final RegistryObject<Weapon> FALCHION = ITEMS.register("falchion",
			() -> new Weapon(Tiers.DIAMOND,3, -2.8f, new Item.Properties()));
	public static final RegistryObject<Weapon> CLAYMORE = ITEMS.register("claymore",
			() -> new Weapon(Tiers.DIAMOND,3, -2.8f, new Item.Properties()));


	//Items

	//Eggs
	public static final RegistryObject<Item> SIN = ITEMS.register("sin_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.SIN, 14283506, 16737400, new Item.Properties()));
	public static final RegistryObject<Item> BONEWHEEL = ITEMS.register("bonewheel_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.BONEWHEEL, 14283506, 16737400, new Item.Properties()));

	/*
	public static final RegistryObject<Item> SNOWFLAKE = ITEMS.register("snowflake", 
			() -> new Item(new Item.Properties()));
	*/
	/*
	public static final RegistryObject<CandybarGreatsword> CANDY_SWORD = ITEMS.register("candy_sword",
			() -> new CandybarGreatsword(YuleTiers.CANDY, 3, -2.5F, new Item.Properties()));
	*/

}
