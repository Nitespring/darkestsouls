package github.nitespring.darkestsouls.core.init;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.item.child.staves.SorcererStaff;
import github.nitespring.darkestsouls.common.item.child.weapons.*;
import github.nitespring.darkestsouls.core.enums.Tiers;
import net.minecraft.world.item.Item;

import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			DarkestSouls.MODID);
	


	//Weapons

	public static final RegistryObject<FrayedBlade> FRAYED_BLADE = ITEMS.register("frayed_blade",
			() -> new FrayedBlade(Tiers.TITANITE, 8.0f, 1.6f, 0.1f, 8, 3,0,0,0,0,0,0,1350,12,0.11f,-1, new Item.Properties()));
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



	//Staves
	public static final RegistryObject<SorcererStaff> SORCERER_STAFF_A = ITEMS.register("sorcerer_staff_a",
			() -> new SorcererStaff(2.0f, 256, new Item.Properties()));



	//Eggs
	public static final RegistryObject<Item> SIN = ITEMS.register("sin_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.SIN, 14283506, 16737400, new Item.Properties()));
	public static final RegistryObject<Item> BONEWHEEL = ITEMS.register("bonewheel_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.BONEWHEEL, 14283506, 16737400, new Item.Properties()));
	public static final RegistryObject<Item> SEWER_CENTIPEDE = ITEMS.register("sewer_centipede_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.SEWER_CENTIPEDE, 14283506, 16737400, new Item.Properties()));


    //Items
	public static final RegistryObject<Item> SOUL_FRAGMENT = ITEMS.register("soul_fragment",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> DARK_FRAGMENT = ITEMS.register("dark_fragment",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> SIDERITE_FRAGMENT = ITEMS.register("siderite_fragment",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> BLOOD_STONE_FRAGMENT = ITEMS.register("blood_stone_fragment",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> BLOOD_GEM = ITEMS.register("blood_gem",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> BEAST_BLOOD_CLUMP = ITEMS.register("beast_blood_clump",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> QUICKSILVER = ITEMS.register("quicksilver",
			() -> new Item(new Item.Properties()));

	public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> SIDERITE_NUGGET = ITEMS.register("siderite_nugget",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> SIDERITE_INGOT = ITEMS.register("siderite_ingot",
			() -> new Item(new Item.Properties()));

	public static final RegistryObject<Item> SOUL_ESSENCE = ITEMS.register("soul_essence",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> DARK_ESSENCE = ITEMS.register("dark_essence",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> FIRE_ESSENCE = ITEMS.register("fire_essence",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> CHAOS_ESSENCE = ITEMS.register("chaos_essence",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> LIGHTNING_ESSENCE = ITEMS.register("lightning_essence",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> LIGHT_ESSENCE = ITEMS.register("light_essence",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> BLOOD_ESSENCE = ITEMS.register("blood_essence",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> POISON_ESSENCE = ITEMS.register("poison_essence",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> FROST_ESSENCE = ITEMS.register("frost_essence",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> ROT_ESSENCE = ITEMS.register("rot_essence",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> BEAST_ESSENCE = ITEMS.register("beast_essence",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> NIGHTMARE_ESSENCE = ITEMS.register("nightmare_essence",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> EYE = ITEMS.register("eye",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> BEAST_EYE = ITEMS.register("beast_eye",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> BLIND_EYE = ITEMS.register("blind_eye",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> BLOSSOMED_EYE = ITEMS.register("blossomed_eye",
			() -> new Item(new Item.Properties()));

	public static final RegistryObject<Item> BLOOD_STONE_SHARD = ITEMS.register("blood_stone_shard",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> TWIN_BLOOD_STONE_SHARDS = ITEMS.register("twin_blood_stone_shards",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> BLOOD_STONE_CHUNK = ITEMS.register("blood_stone_chunk",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> BLOOD_ROCK = ITEMS.register("blood_rock",
			() -> new Item(new Item.Properties()));



}
