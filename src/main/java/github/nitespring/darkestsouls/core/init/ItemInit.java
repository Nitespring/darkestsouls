package github.nitespring.darkestsouls.core.init;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.item.*;
import github.nitespring.darkestsouls.core.enums.Tiers;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			DarkestSouls.MODID);
	


	//Weapons

	public static final RegistryObject<FrayedBlade> FRAYED_BLADE = ITEMS.register("frayed_blade",
			() -> new FrayedBlade(Tiers.TITANITE, 8.0f, 1.6f, 0.1f, 1, 3,0,0,0,0,0,0,1350,12,0.11f,-1, new Item.Properties()));
	public static final RegistryObject<Scimitar> SCIMITAR = ITEMS.register("scimitar",
			() -> new Scimitar(Tiers.TITANITE, 5.0f, 1.9f, -0.2f, 4, 225,8,0.14f, 2,new Item.Properties()));
	public static final RegistryObject<Falchion> FALCHION = ITEMS.register("falchion",
			() -> new Falchion(Tiers.TITANITE, 5.5f, 1.8f, -0.1f, 5, 275,7,0.13f, 2,new Item.Properties()));
	public static final RegistryObject<Claymore> CLAYMORE = ITEMS.register("claymore",
			() -> new Claymore(Tiers.TITANITE, 9.0f, 1.2f, 0.4f, 12, 500,7,0.09f, 3,new Item.Properties()));
	public static final RegistryObject<Flamberge> FLAMBERGE = ITEMS.register("flamberge",
			() -> new Flamberge(Tiers.TITANITE, 8.5f, 1.2f, 0.3f, 11,4, 0,0,0,0,0,0,400,6,0.09f, 3,new Item.Properties()));
	public static final RegistryObject<Zweihander> ZWEIHANDER = ITEMS.register("zweihander",
			() -> new Zweihander(Tiers.TITANITE, 10.5f, 1.0f, 0.8f, 14, 1024,15,0.07f, 5,new Item.Properties()));


	//Items

	//Eggs
	public static final RegistryObject<Item> SIN = ITEMS.register("sin_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.SIN, 14283506, 16737400, new Item.Properties()));
	public static final RegistryObject<Item> BONEWHEEL = ITEMS.register("bonewheel_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.BONEWHEEL, 14283506, 16737400, new Item.Properties()));
	public static final RegistryObject<Item> SEWER_CENTIPEDE = ITEMS.register("sewer_centipede_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.SEWER_CENTIPEDE, 14283506, 16737400, new Item.Properties()));

	/*
	public static final RegistryObject<Item> SNOWFLAKE = ITEMS.register("snowflake", 
			() -> new Item(new Item.Properties()));
	*/
	/*
	public static final RegistryObject<CandybarGreatsword> CANDY_SWORD = ITEMS.register("candy_sword",
			() -> new CandybarGreatsword(YuleTiers.CANDY, 3, -2.5F, new Item.Properties()));
	*/

}
