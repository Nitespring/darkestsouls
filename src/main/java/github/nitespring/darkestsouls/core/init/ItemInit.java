package github.nitespring.darkestsouls.core.init;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.item.Weapon;
import github.nitespring.darkestsouls.common.item.child.alchemy.Flamesprayer;
import github.nitespring.darkestsouls.common.item.child.alchemy.LanternNormal;
import github.nitespring.darkestsouls.common.item.child.armour.*;
import github.nitespring.darkestsouls.common.item.child.guns.GatlingGun;
import github.nitespring.darkestsouls.common.item.child.guns.Pistol;
import github.nitespring.darkestsouls.common.item.child.guns.Shotgun;
import github.nitespring.darkestsouls.common.item.child.staves.ChaosStaff;
import github.nitespring.darkestsouls.common.item.child.staves.CrystalStaff;
import github.nitespring.darkestsouls.common.item.child.staves.SorcererStaff;
import github.nitespring.darkestsouls.common.item.child.weapons.*;
import github.nitespring.darkestsouls.common.item.child.weapons.trickweapon.*;
import github.nitespring.darkestsouls.common.item.throwing.Firebomb;
import github.nitespring.darkestsouls.common.item.throwing.MolotovCocktail;
import github.nitespring.darkestsouls.common.item.throwing.ThrowingKnife;
import github.nitespring.darkestsouls.core.enums.Tiers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


import java.util.function.Supplier;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			DarkestSouls.MODID);
	


	//Special Weapons

	public static final RegistryObject<FrayedBlade> FRAYED_BLADE = ITEMS.register("frayed_blade",
			() -> new FrayedBlade(Tiers.TITANITE, 8.0f, 1.6f, 3.5f,0.1f, 8, 3,0,0,0,0,0,0,0,1350,12,0.11f,-1, new Item.Properties().rarity(Rarity.EPIC)));
	public static final RegistryObject<ShadowBlade> SHADOW_BLADE = ITEMS.register("shadow_blade",
			() -> new ShadowBlade(Tiers.TITANITE, 8.0f, 1.6f,3.5f, 0.2f, 8,1,0,0,0,0,1,0,1, 1350,15,0.11f, 2,new Item.Properties().rarity(Rarity.EPIC)));
	public static final RegistryObject<DragonslayerSpear> DRAGONSLAYER_SPEAR = ITEMS.register("dragonslayer_spear",
			() -> new DragonslayerSpear(Tiers.TITANITE, 6.0f, 2.0f, 4.25f, -0.1f,5, 0,0,0,0,0,0,0, 0,1350,10,0.15f, -1,new Item.Properties().rarity(Rarity.EPIC)));
	public static final RegistryObject<Weapon> DRAGONSLAYER_SWORDSPEAR = ITEMS.register("dragonslayer_swordspear",
			() -> new DragonslayerSwordspear(Tiers.TITANITE, 7.0f, 1.8f,4.0f, 0.1f, 7, 0,0,0,0,0,0,0,0,1350,10,0.12f, 2,new Item.Properties().rarity(Rarity.EPIC)));
	public static final RegistryObject<StormCurvedSword> STORM_CURVED_SWORD = ITEMS.register("storm_curved_sword",
			() -> new StormCurvedSword(Tiers.TITANITE, 6.0f, 2.1f, 2.75f,0.1f, 5, 0,0,0,0,0,0,0, 0,1350,10,0.14f, 2,new Item.Properties().rarity(Rarity.EPIC)));
	public static final RegistryObject<DragonslayerGreataxe> DRAGONSLAYER_GREATAXE = ITEMS.register("dragonslayer_greataxe",
			() -> new DragonslayerGreataxe(Tiers.TITANITE, 10.0f, 1.0f, 3.5f,0.4f, 10, 0,0,0,0,0,0,0,0,225,8,0.07f, 3,new Item.Properties()));
	//Trick Weapons
	public static final RegistryObject<SawCleaver> SAW_CLEAVER = ITEMS.register("saw_cleaver",
			() -> new SawCleaver(Tiers.TITANITE, 6.0f, 1.7f, 3.0f,0.1f, 6, 0,0,0,0,0,0,0,3,1350,15,0.108f, 2,new Item.Properties().rarity(Rarity.RARE)));
	public static final RegistryObject<SawCleaverExtended> SAW_CLEAVER_EXTENDED = ITEMS.register("saw_cleaver_extended",
			() -> new SawCleaverExtended(Tiers.TITANITE, 7.5f, 1.5f,3.5f, 0.2f, 8, 0,0,0,0,0,0,0,0,1350,15,0.104f, 3,new Item.Properties().rarity(Rarity.RARE)));
	public static final RegistryObject<HunterAxe> HUNTER_AXE = ITEMS.register("hunter_axe",
			() -> new HunterAxe(Tiers.TITANITE, 7.0f, 1.4f, 3.0f,0.4f, 8, 0,0,0,0,0,0,0,0,1350,15,0.11f, 2,new Item.Properties().rarity(Rarity.RARE)));
	public static final RegistryObject<HunterAxeExtended> HUNTER_AXE_EXTENDED = ITEMS.register("hunter_axe_extended",
			() -> new HunterAxeExtended(Tiers.TITANITE, 8.0f, 1.2f, 3.75f,0.6f, 12,0,0,0,0,0,0,0, 0,1350,15,0.11f, 3,new Item.Properties().rarity(Rarity.RARE)));
	public static final RegistryObject<Chikage> CHIKAGE = ITEMS.register("chikage",
			() -> new Chikage(Tiers.TITANITE, 6.0f, 1.9f, 3.5f,0.1f, 5,1,0,0,0,0,0,0, 0,1350,15,0.12f, 2,new Item.Properties().rarity(Rarity.RARE)));
	public static final RegistryObject<ChikageExtended> CHIKAGE_EXTENDED = ITEMS.register("chikage_extended",
			() -> new ChikageExtended(Tiers.TITANITE, 12.0f, 1.7f,3.7f, 0.3f, 8,4,0,0,0,0,0,0, 0,1350,15,0.112f, 2,new Item.Properties().rarity(Rarity.RARE)));
	public static final RegistryObject<HolyMoonlightSword> HOLY_MOONLIGHT = ITEMS.register("holy_moonlight_sword",
			() -> new HolyMoonlightSword(Tiers.TITANITE, 7.0f, 1.4f,3.6f, 0.2f, 6, 0,0,0,0,0,0,0,0,1350,17,0.1f, 3,new Item.Properties().rarity(Rarity.RARE)));
	public static final RegistryObject<HolyMoonlightSwordLit> HOLY_MOONLIGHT_LIT = ITEMS.register("holy_moonlight_sword_lit",
			() -> new HolyMoonlightSwordLit(Tiers.TITANITE, 8.0f, 1.2f, 3.75f,0.2f, 6, 0,0,0,0,0,0,0,0,1350,17,0.1f, -1,new Item.Properties().rarity(Rarity.RARE)));

	public static final RegistryObject<BrokenStraightsword> BROKEN_STRAIGHTSWORD = ITEMS.register("broken_straightsword",
			() -> new BrokenStraightsword(Tiers.TITANITE, 3.0f, 1.6f,1.75f, 0.0f, 2, 0,0,0,0,0,0,0,0,127,8,0.1f, 1,new Item.Properties()));
	public static final RegistryObject<BanditKnife> BANDIT_KNIFE = ITEMS.register("bandit_knife",
			() -> new BanditKnife(Tiers.TITANITE, 3.0f, 2.4f, 2.25f,0.0f, 2, 3,0,0,0,0,0,0,0,63,6,0.18f, 1,new Item.Properties()));
	public static final RegistryObject<Longsword> LONGSWORD = ITEMS.register("longsword",
			() -> new Longsword(Tiers.TITANITE, 7.0f, 1.6f, 3.25f,0.0f, 6, 0,0,0,0,0,0,0,0,225,8,0.1f, 2,new Item.Properties()));
	public static final RegistryObject<DarkSword> DARKSWORD = ITEMS.register("dark_sword",
			() -> new DarkSword(Tiers.TITANITE, 7.5f, 1.5f, 3.0f,0.05f, 6, 0,0,0,0,0,0,0,0,1024,10,0.0975f, 2,new Item.Properties()));
	public static final RegistryObject<Scimitar> SCIMITAR = ITEMS.register("scimitar",
			() -> new Scimitar(Tiers.TITANITE, 5.0f, 1.9f,3.0f, -0.2f, 4, 0,0,0,0,0,0,0,0,225,8,0.14f, 2,new Item.Properties()));
	public static final RegistryObject<Falchion> FALCHION = ITEMS.register("falchion",
			() -> new Falchion(Tiers.TITANITE, 6.0f, 1.7f,3.2f, -0.1f, 5,0,0,0,0,0,0,0, 0,275,7,0.13f, 2,new Item.Properties()));
	public static final RegistryObject<Shotel> SHOTEL = ITEMS.register("shotel",
			() -> new Shotel(Tiers.TITANITE, 5.5f, 1.8f,3.0f, -0.2f, 4,0,0,0,0,0,0,0, 0,275,7,0.14f, 2,new Item.Properties()));
	public static final RegistryObject<Shotel> CARTHUS_SHOTEL = ITEMS.register("carthus_shotel",
			() -> new Shotel(Tiers.TITANITE, 5.5f, 1.8f,3.1f, -0.2f, 5, 1,0,0,0,0,0,0,0,275,7,0.14f, 2,new Item.Properties()));
	public static final RegistryObject<CurvedGreatsword> CARTHUS_CURVED_GREATSWORD= ITEMS.register("carthus_curved_greatsword",
			() -> new CurvedGreatsword(Tiers.TITANITE, 7.0f, 1.6f,3.75f, 0.2f, 7, 2,0,0,0,0,0,0,0,225,8,0.105f, 5,new Item.Properties()));
	public static final RegistryObject<Claymore> CLAYMORE = ITEMS.register("claymore",
			() -> new Claymore(Tiers.TITANITE, 9.0f, 1.2f, 3.6f, 0.4f, 12,0,0,0,0,0,0,0, 0,500,7,0.09f, 3,new Item.Properties()));
	public static final RegistryObject<Flamberge> FLAMBERGE = ITEMS.register("flamberge",
			() -> new Flamberge(Tiers.TITANITE, 8.5f, 1.2f, 3.6f,0.3f, 11,4, 0,0,0,0,0,0,1,400,6,0.09f, 3,new Item.Properties()));
	public static final RegistryObject<Zweihander> ZWEIHANDER = ITEMS.register("zweihander",
			() -> new Zweihander(Tiers.TITANITE, 10.5f, 1.0f, 4.0f,0.8f, 14, 0,0,0,0,0,0,0,0,1024,15,0.07f, 5,new Item.Properties()));
	public static final RegistryObject<Scimitar> BANDIT_CURVED_SWORD = ITEMS.register("bandit_curved_sword",
			() -> new Scimitar(Tiers.TITANITE, 6.0f, 1.6f, 3.15f,-0.2f, 4, 0,0,0,0,0,0,0,0,250,8,0.14f, 2,new Item.Properties()));
	public static final RegistryObject<Spear> SPEAR = ITEMS.register("spear",
			() -> new Spear(Tiers.TITANITE, 5.0f, 2.0f,4.5f, -0.1f, 4, 0,0,0,0,0,0,0,0,1350,10,0.13f, 1,new Item.Properties()));
	public static final RegistryObject<GraveScythe> GRAVE_SCYTHE= ITEMS.register("grave_scythe",
			() -> new GraveScythe(Tiers.TITANITE, 7.0f, 1.6f,4.6f, 0.2f, 7, 2,0,0,0,0,0,0,0,225,8,0.105f, 5,new Item.Properties()));
	public static final RegistryObject<Uchigatana> UCHIGATANA = ITEMS.register("uchigatana",
			() -> new Uchigatana(Tiers.TITANITE, 6.0f, 1.7f,3.5f, 0.1f, 5,2,0,0,0,0,0,0, 0,200,15,0.11f, 2,new Item.Properties()));
	public static final RegistryObject<Weapon> BATTLE_AXE = ITEMS.register("battle_axe",
			() -> new Axe(Tiers.TITANITE, 6.0f, 1.4f,3.0f, 0.1f, 6, 0,0,0,0,0,0,0,0,225,8,0.09f, 2,new Item.Properties()));
	public static final RegistryObject<Greataxe> EXECUTIONER_GREATAXE = ITEMS.register("executioner_greataxe",
			() -> new Greataxe(Tiers.TITANITE, 10.0f, 1.0f, 4.0f,0.4f, 10, 0,0,0,0,0,0,0,0,225,8,0.07f, 3,new Item.Properties()));
	public static final RegistryObject<Greataxe> CRESCENT_MOON_GREATAXE = ITEMS.register("crescent_moon_greataxe",
			() -> new Greataxe(Tiers.TITANITE, 9.0f, 1.2f, 4.25f,0.4f, 10, 0,0,0,0,0,0,0,0,225,8,0.08f, 3,new Item.Properties()));


	public static final RegistryObject<Weapon> HUNTSMAN_AXE = ITEMS.register("hunting_axe",
			() -> new Axe(Tiers.TITANITE, 6.0f, 1.4f,3.0f, 0.1f, 6, 0,0,0,0,0,0,0,0,225,8,0.09f, 2,new Item.Properties()));
	public static final RegistryObject<Scimitar> HUNTSMAN_CUTLASS = ITEMS.register("huntsman_cutlass",
			() -> new Scimitar(Tiers.TITANITE, 5.0f, 1.9f,3.0f, -0.2f, 4, 0,0,0,0,0,0,0,0,225,8,0.11f, 2,new Item.Properties()));
	public static final RegistryObject<Weapon> HUNTSMAN_PITCHFORK = ITEMS.register("huntsman_pitchfork",
			() -> new Spear(Tiers.TITANITE, 5.0f, 1.9f,3.8f, -0.2f, 4, 225,0,0,0,0,0,0,0,0,8,0.12f, 2,new Item.Properties()));
	public static final RegistryObject<Weapon> CHURCH_SCYTHE= ITEMS.register("church_scythe",
			() -> new ChurchScythe(Tiers.TITANITE, 7.5f, 1.6f,4.5f, 0.2f, 7, 0,0,0,0,0,0,1,0,325,12,0.105f, 5,new Item.Properties()));
	public static final RegistryObject<Weapon> CHURCH_SCYTHE_UNLIT = ITEMS.register("church_scythe_unlit",
			() -> new ChurchScythe(Tiers.TITANITE, 7.5f, 1.6f,4.5f, 0.2f, 7, 0,0,0,0,0,0,1,0,325,12,0.105f, 5,new Item.Properties()));
	public static final RegistryObject<Weapon> CHURCH_CANE = ITEMS.register("church_cane",
			() -> new Weapon(Tiers.TITANITE, 4.0f, 1.4f, 2.5f,0.1f, 6, 0,0,0,0,0,0,0,0,255,8,0.1f, 0,new Item.Properties()));
	public static final RegistryObject<Spear> CRUCIFIX = ITEMS.register("crucifix",
			() -> new Spear(Tiers.TITANITE, 6.0f, 1.2f,4.0f, 0.2f, 6, 0,0,0,0,1, 0,1,0,1350,10,0.08f, 2,new Item.Properties()));
	//Staves
	public static final RegistryObject<SorcererStaff> SORCERER_STAFF_A = ITEMS.register("sorcerer_staff_a",
			() -> new SorcererStaff(2.0f, 128, 0, new Item.Properties().rarity(Rarity.COMMON)));
	public static final RegistryObject<SorcererStaff> SORCERER_STAFF_B = ITEMS.register("sorcerer_staff_b",
			() -> new SorcererStaff(4.0f, 256, 1, new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<CrystalStaff> CRYSTAL_STAFF = ITEMS.register("crystal_staff",
			() -> new CrystalStaff(4.0f, 256, 0, 1, new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<CrystalStaff> CRYSTAL_STAFF_PURPLE = ITEMS.register("crystal_staff_purple",
			() -> new CrystalStaff(8.0f, 384, 1, 2, new Item.Properties().rarity(Rarity.RARE)));
	public static final RegistryObject<CrystalStaff> CRYSTAL_STAFF_BLUE = ITEMS.register("crystal_staff_blue",
			() -> new CrystalStaff(12.0f, 512, 2, 3, new Item.Properties().rarity(Rarity.RARE)));
	public static final RegistryObject<ChaosStaff> CHAOS_STAFF = ITEMS.register("chaos_staff",
			() -> new ChaosStaff(10.0f, 1024, 2, new Item.Properties().rarity(Rarity.EPIC)));

	//Guns
	public static final RegistryObject<Item> QUICKSILVER_BULLET = ITEMS.register("quicksilver_bullet",
			() -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(24)));
	public static final RegistryObject<Pistol> HUNTER_PISTOL = ITEMS.register("hunter_pistol",
			() -> new Pistol(6.0f, 18,2,0.4f,0.5f, 8, 0,0,1, 255, 5, new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Shotgun> BLUNDERBUSS = ITEMS.register("blunderbuss",
			() -> new Shotgun(10.0f, 24,2,0.3f,0.25f, 10, 0,0,1, 127, 0.4f, 0.4f, 5, new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Pistol> EVELYN = ITEMS.register("evelyn",
			() -> new Pistol(6.5f, 12,2,0.3f,0.5f, 10, 0,0,1, 511, 10, new Item.Properties().rarity(Rarity.RARE)));
	public static final RegistryObject<Pistol> REPEATING_PISTOL = ITEMS.register("repeating_pistol",
			() -> new Pistol(16.0f, 28,6,0.5f,0.5f, 12, 0,0,2, 511, 8, new Item.Properties().rarity(Rarity.RARE)));
	public static final RegistryObject<GatlingGun> GATLING_GUN = ITEMS.register("gatling_gun",
			() -> new GatlingGun(2.0f, 60,0,0.5f,0.5f, 12, 0,0,1, 511, 5, new Item.Properties().rarity(Rarity.RARE)));
	public static final RegistryObject<Pistol> MUSKET = ITEMS.register("musket",
			() -> new Pistol(8.0f, 24,2,0.4f,0.5f, 16, 0,0,1, 511, 8, new Item.Properties().rarity(Rarity.UNCOMMON)));
	//Alchemy
	public static final RegistryObject<Weapon> HUNTER_TORCH= ITEMS.register("hunter_torch",
			() -> new Weapon(Tiers.TITANITE, 1.0f, 1.9f, 2.5f,-0.2f, 4, 0,0,0,0,0,2,0,0,225,8,0.10f, 2,new Item.Properties()));
	public static final RegistryObject<LanternNormal> LANTERN = ITEMS.register("lantern",
			() -> new LanternNormal(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Flamesprayer> FLAMESPRAYER = ITEMS.register("flamesprayer",
			() -> new Flamesprayer(4.0f, 30, 10, 0.1f, 1, 0.8f, 1, 1, 256, 15, new Item.Properties().rarity(Rarity.UNCOMMON)));

	//Throwing
	public static final RegistryObject<ThrowingKnife> THROWING_KNIFE = ITEMS.register("throwing_knife",
			() -> new ThrowingKnife(4.0f, 18,0,0,6, 0.28f, 0.01f, true,0, new Item.Properties().stacksTo(20)));
	public static final RegistryObject<ThrowingKnife> BONE_KNIFE = ITEMS.register("bone_knife",
			() -> new ThrowingKnife(2.0f, 12,0,0,3,0.22f, 0.012f, true,0,  new Item.Properties().stacksTo(24)));
	public static final RegistryObject<ThrowingKnife> BLOOD_KNIFE = ITEMS.register("blood_knife",
			() -> new ThrowingKnife(2.0f, 18,2,0,4, 0.28f, 0.01f, true,0, new Item.Properties().stacksTo(20)));
	public static final RegistryObject<ThrowingKnife> POISON_KNIFE = ITEMS.register("poison_knife",
			() -> new ThrowingKnife(2.0f, 18,0,2,4, 0.28f, 0.01f, true,0, new Item.Properties().stacksTo(20)));
	public static final RegistryObject<ThrowingKnife> KUKRI = ITEMS.register("kukri",
			() -> new ThrowingKnife(6.0f, 24,4,0,8, 0.3f, 0.008f, false,1,new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(16)));
	public static final RegistryObject<Firebomb> FIREBOMB = ITEMS.register("firebomb",
			() -> new Firebomb(6.0f, 24, 8, 0, new Item.Properties().stacksTo(20)));
	public static final RegistryObject<Firebomb> BLACK_FIREBOMB = ITEMS.register("black_firebomb",
			() -> new Firebomb(10.0f, 24, 8, 1, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(12)));
	public static final RegistryObject<MolotovCocktail> MOLOTOV = ITEMS.register("molotov",
			() -> new MolotovCocktail(6.0f, 28, 4, new Item.Properties().stacksTo(16)));

	//Armour
	public static final RegistryObject< HunterArmourItem> HUNTER_HAT = ITEMS.register("hunter_hat",
			() -> new HunterArmourItem(1,1,50,3,1,0,0,0.1f,0.3f, 0,0,0,0,0.005f,0.01f,0,0,2,1025,12, ArmorMaterials.LEATHER, ArmorItem.Type.HELMET,new Item.Properties()));
	public static final RegistryObject< HunterArmourItem> HUNTER_COAT = ITEMS.register("hunter_coat",
			() -> new HunterArmourItem(1,1,50,5,1,0.1f,0,0.1f,0.3f,0,0,0,0,0.01f,0.02f,0,2,3,1025,12, ArmorMaterials.LEATHER, ArmorItem.Type.CHESTPLATE,new Item.Properties()));
	public static final RegistryObject< HunterArmourItem> HUNTER_TROUSERS = ITEMS.register("hunter_trousers",
			() -> new HunterArmourItem(1,1,50,4,0,0,0,0.1f,0.2f,0,0,0,0,0.02f,0.04f, 0,1,2,1025,12, ArmorMaterials.LEATHER, ArmorItem.Type.LEGGINGS,new Item.Properties()));
	public static final RegistryObject< HunterArmourItem> HUNTER_BOOTS = ITEMS.register("hunter_boots",
			() -> new HunterArmourItem(1,1,50,2,0,0, 0,0.1f,0.2f,0,0,0,0,0.015f,0.03f,0,0,1, 1025,12, ArmorMaterials.LEATHER, ArmorItem.Type.BOOTS,new Item.Properties()));
	public static final RegistryObject< AlchemistArmourItem> ALCHEMIST_HAT = ITEMS.register("alchemist_hat",
			() -> new AlchemistArmourItem(3,1,80,2,0,0,0,0,0,0,10,0,5,0,0,0,0,1,1024,12,ArmorMaterials.LEATHER, ArmorItem.Type.HELMET,new Item.Properties()));
	public static final RegistryObject< AlchemistArmourItem> ALCHEMIST_COAT = ITEMS.register("alchemist_coat",
			() -> new AlchemistArmourItem(3,1,80,4,1,0,0,0,0,0,10,0,5,0,0,0,0,2,1024,12,ArmorMaterials.LEATHER, ArmorItem.Type.CHESTPLATE,new Item.Properties()));
	public static final RegistryObject< AlchemistArmourItem> ALCHEMIST_TROUSERS = ITEMS.register("alchemist_trousers",
			() -> new AlchemistArmourItem(3,1,80,2,0,0,0,0,0,0,5,0,0,0,0,0,0,1,1024,12,ArmorMaterials.LEATHER, ArmorItem.Type.LEGGINGS,new Item.Properties()));
	public static final RegistryObject< AlchemistArmourItem> ALCHEMIST_BOOTS = ITEMS.register("alchemist_boots",
			() -> new AlchemistArmourItem(3,1,80,1,0,0,0,0,0,0,5,0,0,0,0,0,0,1,1024,12,ArmorMaterials.LEATHER, ArmorItem.Type.BOOTS,new Item.Properties()));
	public static final RegistryObject< SpecialistArmourItem> SPECIALIST_HAT = ITEMS.register("specialist_hat",
			() -> new SpecialistArmourItem(4,1,70,2,1,0,0,0,0,0,0,10,7,0,0,0,0,2,1024,12,ArmorMaterials.LEATHER, ArmorItem.Type.HELMET,new Item.Properties()));
	public static final RegistryObject< SpecialistArmourItem> SPECIALIST_COAT = ITEMS.register("specialist_coat",
			() -> new SpecialistArmourItem(4,1,70,4,1,0,0,0,0,0,0,10,0,0,0,0,0,2,1024,12,ArmorMaterials.LEATHER, ArmorItem.Type.CHESTPLATE,new Item.Properties()));
	public static final RegistryObject< SpecialistArmourItem> SPECIALIST_TROUSERS = ITEMS.register("specialist_trousers",
			() -> new SpecialistArmourItem(4,1,70,3,1,0,0,0,0,0,0,5,0,0,0,0,0,1,1024,12,ArmorMaterials.LEATHER, ArmorItem.Type.LEGGINGS,new Item.Properties()));
	public static final RegistryObject< SpecialistArmourItem> SPECIALIST_BOOTS = ITEMS.register("specialist_boots",
			() -> new SpecialistArmourItem(4,1,70,2,1,0,0,0,0,0,0,5,0,0,0,0,0,1,1024,12,ArmorMaterials.LEATHER, ArmorItem.Type.BOOTS,new Item.Properties()));
	public static final RegistryObject< TatteredWizardRobeItem> TATTERED_WIZARD_HAT = ITEMS.register("tattered_wizard_hat",
			() -> new TatteredWizardRobeItem(2,0,10,1,0,0,0,0,0,5,0,0,5,0,0,2,0,0,256,8,ArmorMaterials.LEATHER, ArmorItem.Type.HELMET,new Item.Properties()));
	public static final RegistryObject< TatteredWizardRobeItem> TATTERED_WIZARD_ROBE = ITEMS.register("tattered_wizard_robe",
			() -> new TatteredWizardRobeItem(2,0,10,2,0,0,0,0,0,5,0,0,5,0,0,3,0,1,256,8,ArmorMaterials.LEATHER, ArmorItem.Type.CHESTPLATE,new Item.Properties()));
	public static final RegistryObject< WizardRobeItem> WIZARD_HAT = ITEMS.register("wizard_hat",
			() -> new WizardRobeItem(2,1,11,2,0,0,0,0,0,10,0,0,10,0,0,5,0,1,1024,12,ArmorMaterials.LEATHER, ArmorItem.Type.HELMET,new Item.Properties()));
	public static final RegistryObject< WizardRobeItem> WIZARD_ROBE = ITEMS.register("wizard_robe",
			() -> new WizardRobeItem(2,1,11,3,0,0,0,0,0,10,0,0,10,0,0,5,0,2,1024,12,ArmorMaterials.LEATHER, ArmorItem.Type.CHESTPLATE,new Item.Properties()));
	public static final RegistryObject< WizardRobeItem> WIZARD_PANTS = ITEMS.register("wizard_pants",
			() -> new WizardRobeItem(2,1,11,2,0,0,0,0,0,5,0,0,5,0,0,2,0,1,1024,12,ArmorMaterials.LEATHER, ArmorItem.Type.LEGGINGS,new Item.Properties()));
	public static final RegistryObject< WizardRobeItem> WIZARD_BOOTS = ITEMS.register("wizard_boots",
			() -> new WizardRobeItem(2,1,11,1,0,0,0,0,0,5,0,0,5,0,0,3,0,1,1024,12,ArmorMaterials.LEATHER, ArmorItem.Type.BOOTS,new Item.Properties()));
	public static final RegistryObject< KnightArmourItem> KNIGHT_HELM = ITEMS.register("knight_helm",
			() -> new KnightArmourItem(0,1,20,4,3,0.1f,0.05f,0,0.5f,0,0,0,0,-0.005f,0,0,2,3,1024,12,ArmorMaterials.IRON, ArmorItem.Type.HELMET,new Item.Properties()));
	public static final RegistryObject< KnightArmourItem> KNIGHT_CHESTPLATE = ITEMS.register("knight_chestplate",
			() -> new KnightArmourItem(0,1,20,7,3,0.2f,0.1f,-0.1f,0.5f,0,0,0,0,-0.01f,0,0,3,4,1024,12,ArmorMaterials.IRON, ArmorItem.Type.CHESTPLATE,new Item.Properties()));
	public static final RegistryObject< KnightArmourItem> KNIGHT_PANTS = ITEMS.register("knight_pants",
			() -> new KnightArmourItem(0,1,20,5,2,0.05f,0,0,0.5f,0,0,0,0,-0.002f,0,0,2,2,1024,12,ArmorMaterials.IRON, ArmorItem.Type.LEGGINGS,new Item.Properties()));
	public static final RegistryObject< KnightArmourItem> KNIGHT_BOOTS = ITEMS.register("knight_boots",
			() -> new KnightArmourItem(0,1,20,3,1,0.05f,0.05f,0,0.5f,0,0,0,0,-0.001f,0,0,1,3,1024,12,ArmorMaterials.IRON, ArmorItem.Type.BOOTS,new Item.Properties()));
	//Eggs
	public static final RegistryObject<Item> SIN = ITEMS.register("sin_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.SIN, 1318437, 16449279, new Item.Properties()));
	public static final RegistryObject<Item> BONEWHEEL = ITEMS.register("bonewheel_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.BONEWHEEL, 13684684, 11432504, new Item.Properties()));
	public static final RegistryObject<Item> SEWER_CENTIPEDE = ITEMS.register("sewer_centipede_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.SEWER_CENTIPEDE, 13686464, 7373164, new Item.Properties()));
	public static final RegistryObject<Item> SKELETON_FALCHION = ITEMS.register("skeleton_falchion_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.SKELETON_FALCHION, 13684684, 14079971, new Item.Properties()));
	public static final RegistryObject<Item> SKELETON_CURVED_SWORDS = ITEMS.register("skeleton_curved_swords_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.SKELETON_CURVED_SWORDS, 13684684, 7367532, new Item.Properties()));
	public static final RegistryObject<Item> SKELETON_SPEAR = ITEMS.register("skeleton_spear_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.SKELETON_SPEAR, 13684684, 8618640, new Item.Properties()));
	public static final RegistryObject<Item> TALL_SKELETON_TWIN_SHOTELS = ITEMS.register("skeleton_swordsman_twin_shotels_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.TALL_SKELETON_TWIN_SHOTELS, 13684684, 2239044, new Item.Properties()));
	public static final RegistryObject<Item> HOLLOW_LONGSWORD = ITEMS.register("hollow_longsword_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.HOLLOW_LONGSWORD, 13945528, 5202790, new Item.Properties()));
	public static final RegistryObject<Item> HOLLOW_CROSSBOW = ITEMS.register("hollow_crossbow_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.HOLLOW_CROSSBOW, 13945528, 6633254, new Item.Properties()));
	public static final RegistryObject<Item> GRAVETENDER_HOLLOW_CROSSBOW = ITEMS.register("gravetender_hollow_crossbow_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.GRAVETENDER_HOLLOW_CROSSBOW, 13945528, 2962739, new Item.Properties()));
	public static final RegistryObject<Item> HOLLOW_AXE = ITEMS.register("hollow_axe_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.HOLLOW_AXE, 13945528, 6448753, new Item.Properties()));
	public static final RegistryObject<Item> HOLLOW_BROKEN_STRAIGHTSWORD = ITEMS.register("hollow_broken_straightsword_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.HOLLOW_BROKEN_STRAIGHTSWORD, 13945528, 12630442, new Item.Properties()));
	public static final RegistryObject<Item> GRAVETENDER_HOLLOW_LONGSWORD = ITEMS.register("gravetender_hollow_longsword_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.GRAVETENDER_HOLLOW_LONGSWORD, 13945528, 2962739, new Item.Properties()));
	public static final RegistryObject<Item> HOLLOW_ASSASSIN = ITEMS.register("hollow_assassin_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.HOLLOW_ASSASSIN, 13945528, 2304301, new Item.Properties()));
	public static final RegistryObject<Item> GRAVETENDER_HOLLOW_BROKEN_STRAIGHTSWORD = ITEMS.register("gravetender_hollow_broken_straightsword_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.GRAVETENDER_HOLLOW_BROKEN_STRAIGHTSWORD, 13945528, 2962739, new Item.Properties()));
	public static final RegistryObject<Item> BEAST_PATIENT = ITEMS.register("beast_patient_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.BEAST_PATIENT, 2962739, 11432504, new Item.Properties()));
	public static final RegistryObject<Item> CLOAKED_BEAST_PATIENT = ITEMS.register("cloaked_beast_patient_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.CLOAKED_BEAST_PATIENT, 2962739, 11432504, new Item.Properties()));
	public static final RegistryObject<Item> ASHEN_BLOOD_BEAST_PATIENT = ITEMS.register("ashen_blood_beast_patient_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.ASHEN_BLOOD_BEAST_PATIENT, 2962739, 11432504, new Item.Properties()));
	public static final RegistryObject<Item> LEECH = ITEMS.register("leech_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.LEECH, 1318437, 7373164, new Item.Properties()));
	public static final RegistryObject<Item> CHURCH_DOCTOR = ITEMS.register("church_doctor_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.CHURCH_DOCTOR, 4475990, 14804204, new Item.Properties()));
	public static final RegistryObject<Item> CHURCH_DOCTOR_LANTERN = ITEMS.register("church_doctor_lantern_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.CHURCH_DOCTOR_LANTERN, 4475990, 16777204, new Item.Properties()));
	public static final RegistryObject<Item> CHURCH_DOCTOR_SCYTHE = ITEMS.register("church_doctor_scythe_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.CHURCH_DOCTOR_SCYTHE, 4475990, 11588863, new Item.Properties()));
	public static final RegistryObject<Item> CHURCH_DOCTOR_PISTOL = ITEMS.register("church_doctor_pistol_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.CHURCH_DOCTOR_PISTOL, 4475990, 11250609, new Item.Properties()));
	public static final RegistryObject<Item> CHURCH_DOCTOR_FLAMESPRAYER = ITEMS.register("church_doctor_flamesprayer_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.CHURCH_DOCTOR_FLAMESPRAYER, 4475990, 16736256, new Item.Properties()));
	public static final RegistryObject<Item> CHURCH_DOCTOR_CRUCIFIX = ITEMS.register("church_doctor_crucifix_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.CHURCH_DOCTOR_CRUCIFIX, 4475990, 2097152, new Item.Properties()));
	public static final RegistryObject<Item> DARKWRAITH = ITEMS.register("darkwraith_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.DARKWRAITH, 987415, 6750208, new Item.Properties()));
	public static final RegistryObject<Item> EGG_HUNTSMAN_AXE = ITEMS.register("huntsman_axe_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.HUNTSMAN_AXE, 2237490, 11556644, new Item.Properties()));
	public static final RegistryObject<Item> EGG_HUNTSMAN_CUTLASS = ITEMS.register("huntsman_cutlass_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.HUNTSMAN_CUTLASS, 2237490, 7106425, new Item.Properties()));
	public static final RegistryObject<Item> EGG_HUNTSMAN_PITCHFORK = ITEMS.register("huntsman_pitchfork_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.HUNTSMAN_PITCHFORK, 2237490, 3947592, new Item.Properties()));
	public static final RegistryObject<Item> EGG_HUNTSMAN_RIFLE = ITEMS.register("huntsman_rifle_spawn_egg",
			() -> new ForgeSpawnEggItem(EntityInit.HUNTSMAN_RIFLE, 2237490, 6633254, new Item.Properties()));


	public static final RegistryObject<Item> SPAWN_GROUP_GRAVETENDER_HOLLOW_1 = ITEMS.register("gravetender_hollow_spawn_group_1",
			() -> new ForgeSpawnEggItem(EntityInit.GRAVETENDER_HOLLOW_GROUP1, 13945528, 2962739, new Item.Properties()));
	public static final RegistryObject<Item> SPAWN_GROUP_HOLLOW_SOLDIER_1 = ITEMS.register("hollow_soldier_spawn_group_1",
			() -> new ForgeSpawnEggItem(EntityInit.HOLLOW_SOLDIER_GROUP1, 13945528, 5202790, new Item.Properties()));
	public static final RegistryObject<Item> SPAWN_GROUP_HUNTSMAN_1 = ITEMS.register("huntsman_spawn_group_1",
			() -> new ForgeSpawnEggItem(EntityInit.HUNTSMAN_GROUP1, 2237490, 6633254, new Item.Properties()));
	public static final RegistryObject<Item> SPAWN_GROUP_CHURCH_DOCTOR_1 = ITEMS.register("church_doctor_spawn_group_1",
			() -> new ForgeSpawnEggItem(EntityInit.CHURCH_DOCTOR_GROUP1, 4475990, 11588863, new Item.Properties()));
	public static final RegistryObject<Item> SPAWN_GROUP_BEAST_PATIENT_1 = ITEMS.register("beast_patient_spawn_group_1",
			() -> new ForgeSpawnEggItem(EntityInit.BEAST_PATIENT_GROUP1, 2962739, 11432504, new Item.Properties()));
	public static final RegistryObject<Item> SPAWN_GROUP_SKELETON_1 = ITEMS.register("skeleton_spawn_group_1",
			() -> new ForgeSpawnEggItem(EntityInit.SKELETON_GROUP1, 13684684, 14079971, new Item.Properties()));

	//Items
	public static final RegistryObject<Item> TITANITE_FRAGMENT = ITEMS.register("titanite_fragment",
			() -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> RUNE_FRAGMENT = ITEMS.register("rune_fragment",
			() -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> SOUL_FRAGMENT = ITEMS.register("soul_fragment",
			() -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> SMALL_SOUL_FRAGMENT = ITEMS.register("small_soul_fragment",
			() -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> BONE_FRAGMENT = ITEMS.register("bone_fragment",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> DEMON_FRAGMENT = ITEMS.register("demon_fragment",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final RegistryObject<Item> TWINKLING_FRAGMENT = ITEMS.register("twinkling_fragment",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final RegistryObject<Item> DRAGON_SCALE_FRAGMENT = ITEMS.register("dragon_scale_fragment",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final RegistryObject<Item> DARK_FRAGMENT = ITEMS.register("dark_fragment",
			() -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> SIDERITE_FRAGMENT = ITEMS.register("siderite_fragment",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> CHAOS_FRAGMENT = ITEMS.register("chaos_fragment",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> BLOOD_STONE_FRAGMENT = ITEMS.register("blood_stone_fragment",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> BLOOD_GEM = ITEMS.register("blood_gem",
			() -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> BEAST_BLOOD_CLUMP = ITEMS.register("beast_blood_clump",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> QUICKSILVER = ITEMS.register("quicksilver",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> DEMON_TITANITE = ITEMS.register("demon_titanite",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final RegistryObject<Item> TWINKLING_TITANITE = ITEMS.register("twinkling_titanite",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final RegistryObject<Item> DRAGON_SCALE = ITEMS.register("dragon_scale",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final RegistryObject<Item> INFUSED_DRAGON_SCALE = ITEMS.register("infused_dragon_scale",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final RegistryObject<Item> CORRUPTED_DRAGON_SCALE = ITEMS.register("corrupted_dragon_scale",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final RegistryObject<Item> BEWITCHED_BRANCH = ITEMS.register("bewitched_branch",
			() -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> CHAOS_ROOT = ITEMS.register("chaos_root",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> CINNABAR = ITEMS.register("cinnabar",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> CRYSTAL = ITEMS.register("crystal",
			() -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> GREEN_CRYSTAL = ITEMS.register("green_crystal",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> PURPLE_CRYSTAL = ITEMS.register("purple_crystal",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));
	public static final RegistryObject<Item> BLOOD_CRYSTAL = ITEMS.register("blood_crystal",
			() -> new Item(new Item.Properties().rarity(Rarity.RARE)));

	public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> SIDERITE_NUGGET = ITEMS.register("siderite_nugget",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> TITANITE_NUGGET = ITEMS.register("titanite_nugget",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> GOLDEN_NUGGET = ITEMS.register("golden_alloy_nugget",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> TWINKLING_NUGGET = ITEMS.register("twinkling_nugget",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> DEMON_NUGGET = ITEMS.register("demon_nugget",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> CHAOS_NUGGET = ITEMS.register("chaos_nugget",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> DARK_NUGGET = ITEMS.register("dark_nugget",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> NIGHTMARE_NUGGET = ITEMS.register("nightmare_nugget",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> BLOOD_NUGGET = ITEMS.register("blood_nugget",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> HOLY_NUGGET = ITEMS.register("holy_nugget",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> MAGIC_NUGGET = ITEMS.register("magic_nugget",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> DRAGON_NUGGET = ITEMS.register("dragon_nugget",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> LIGHTNING_NUGGET = ITEMS.register("lightning_nugget",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> SIDERITE_INGOT = ITEMS.register("siderite_ingot",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> TITANITE_INGOT = ITEMS.register("titanite_ingot",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> GOLDEN_INGOT = ITEMS.register("golden_alloy_ingot",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> TWINKLING_INGOT = ITEMS.register("twinkling_ingot",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> DEMON_INGOT = ITEMS.register("demon_ingot",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> CHAOS_INGOT = ITEMS.register("chaos_ingot",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> DARK_INGOT = ITEMS.register("dark_ingot",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> NIGHTMARE_INGOT = ITEMS.register("nightmare_ingot",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> BLOOD_INGOT = ITEMS.register("blood_ingot",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> HOLY_INGOT = ITEMS.register("holy_ingot",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> MAGIC_INGOT = ITEMS.register("magic_ingot",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> DRAGON_INGOT = ITEMS.register("dragon_ingot",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> LIGHTNING_INGOT = ITEMS.register("lightning_ingot",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> STRAIGHTSWORD_HILT = ITEMS.register("straightsword_hilt",
			() -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
	public static final RegistryObject<Item> CURVED_SWORD_HILT = ITEMS.register("curved_sword_hilt",
			() -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
	public static final RegistryObject<Item> GREATSWORD_HILT = ITEMS.register("greatsword_hilt",
			() -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
	public static final RegistryObject<Item> REINFORCED_POLE = ITEMS.register("reinforced_pole",
			() -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
	public static final RegistryObject<Item> REINFORCED_HANDLE = ITEMS.register("reinforced_handle",
			() -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
	public static final RegistryObject<Item> CURVED_HANDLE = ITEMS.register("reinforced_curved_handle",
			() -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
	public static final RegistryObject<Item> GUN_HANDLE = ITEMS.register("gun_handle",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> REINFORCED_GUNMETAL_HANDLE = ITEMS.register("reinforced_gunmetal_handle",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> GUNMETAL_BARREL = ITEMS.register("gunmetal_barrel",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> REINFORCED_GUNMETAL_BARREL = ITEMS.register("reinforced_gunmetal_barrel",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> REINFORCED_GUNMETAL_DOUBLE_BARREL = ITEMS.register("reinforced_gunmetal_double_barrel",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> IGNITION_MECHANISM = ITEMS.register("ignition_mechanism",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> WORKSHOP_MECHANISM = ITEMS.register("workshop_mechanism",
			() -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
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

	public static final RegistryObject<Item> TORN_LEATHER_SCRAP = ITEMS.register("torn_leather_scrap",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> REINFORCED_LEATHER = ITEMS.register("reinforced_leather",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> RUSTY_MAIL_SCRAP = ITEMS.register("rusty_mail_scrap",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> TORN_MAIL_SCRAP = ITEMS.register("torn_mail_scrap",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> REINFORCED_MAIL_SCRAP = ITEMS.register("reinforced_mail_scrap",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> RUSTY_METAL_SCRAP = ITEMS.register("rusty_metal_scrap",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> METAL_PIECE = ITEMS.register("metal_piece",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> REINFORCED_METAL_PIECE = ITEMS.register("reinforced_metal_piece",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> TORN_CLOTH_PIECE = ITEMS.register("torn_cloth_piece",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> ENCHANTED_TORN_CLOTH_PIECE = ITEMS.register("enchanted_torn_cloth_piece",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> CLOTH_PIECE = ITEMS.register("cloth_piece",
			() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> ENCHANTED_CLOTH_PIECE = ITEMS.register("enchanted_cloth_piece",
			() -> new Item(new Item.Properties()));

	//Blocks

	public static final RegistryObject<BlockItem> CINNABAR_ORE = ITEMS.register("cinnabar_ore",
			() -> new BlockItem(BlockInit.CINNABAR_ORE.get(),new Item.Properties()));
	public static final RegistryObject<BlockItem> SIDERITE_ORE = ITEMS.register("siderite_ore",
			() -> new BlockItem(BlockInit.SIDERITE_ORE.get(),new Item.Properties()));
	public static final RegistryObject<BlockItem> DEEPSLATE_CINNABAR_ORE = ITEMS.register("deepslate_cinnabar_ore",
			() -> new BlockItem(BlockInit.DEEPSLATE_CINNABAR_ORE.get(),new Item.Properties()));
	public static final RegistryObject<BlockItem> DEEPSLATE_SIDERITE_ORE = ITEMS.register("deepslate_siderite_ore",
			() -> new BlockItem(BlockInit.DEEPSLATE_SIDERITE_ORE.get(),new Item.Properties()));
	public static final RegistryObject<BlockItem> NETHER_CINNABAR_ORE = ITEMS.register("nether_cinnabar_ore",
			() -> new BlockItem(BlockInit.NETHER_CINNABAR_ORE.get(),new Item.Properties()));
	public static final RegistryObject<BlockItem> NETHER_SIDERITE_ORE = ITEMS.register("nether_siderite_ore",
			() -> new BlockItem(BlockInit.NETHER_SIDERITE_ORE.get(),new Item.Properties()));
	public static final RegistryObject<BlockItem> BLACKSTONE_CINNABAR_ORE = ITEMS.register("blackstone_cinnabar_ore",
			() -> new BlockItem(BlockInit.BLACKSTONE_CINNABAR_ORE.get(),new Item.Properties()));
	public static final RegistryObject<BlockItem> BLACKSTONE_SIDERITE_ORE = ITEMS.register("blackstone_siderite_ore",
			() -> new BlockItem(BlockInit.BLACKSTONE_SIDERITE_ORE.get(),new Item.Properties()));
	public static final RegistryObject<BlockItem> ENDER_CINNABAR_ORE = ITEMS.register("ender_cinnabar_ore",
			() -> new BlockItem(BlockInit.ENDER_CINNABAR_ORE.get(),new Item.Properties()));
	public static final RegistryObject<BlockItem> ENDER_SIDERITE_ORE = ITEMS.register("ender_siderite_ore",
			() -> new BlockItem(BlockInit.ENDER_SIDERITE_ORE.get(),new Item.Properties()));
	public static final RegistryObject<BlockItem> BASALT_CINNABAR_ORE = ITEMS.register("basalt_cinnabar_ore",
			() -> new BlockItem(BlockInit.BASALT_CINNABAR_ORE.get(),new Item.Properties()));
	public static final RegistryObject<BlockItem> SIDERITE_BRICKS = ITEMS.register("siderite_bricks",
			() -> new BlockItem(BlockInit.SIDERITE_BRICKS.get(),new Item.Properties()));
	public static final RegistryObject<BlockItem> CRACKED_SIDERITE_BRICKS = ITEMS.register("cracked_siderite_bricks",
			() -> new BlockItem(BlockInit.CRACKED_SIDERITE_BRICKS.get(),new Item.Properties()));
	public static final RegistryObject<BlockItem> SIDERITE_BRICKS_SLAB = ITEMS.register("siderite_brick_slab",
			() -> new BlockItem(BlockInit.SIDERITE_BRICKS_SLAB.get(),new Item.Properties()));
	public static final RegistryObject<BlockItem> SIDERITE_BRICKS_STAIRS = ITEMS.register("siderite_brick_stairs",
			() -> new BlockItem(BlockInit.SIDERITE_BRICKS_STAIRS.get(),new Item.Properties()));
	public static final RegistryObject<BlockItem> SIDERITE_BRICKS_WALL = ITEMS.register("siderite_brick_wall",
			() -> new BlockItem(BlockInit.SIDERITE_BRICKS_WALL.get(),new Item.Properties()));
}
