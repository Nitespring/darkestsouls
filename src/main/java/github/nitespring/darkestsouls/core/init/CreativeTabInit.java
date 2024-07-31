package github.nitespring.darkestsouls.core.init;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.core.util.CustomItemTags;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.IntStream;

public class CreativeTabInit {

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,
            DarkestSouls.MODID);

    public static final RegistryObject<CreativeModeTab> EQUIPMENT =   TABS.register("equipment",
            () ->CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.darkestsouls.equipment"))
                    .icon(ItemInit.DRAGONSLAYER_SPEAR.get()::getDefaultInstance)
                    .withSearchBar().displayItems((displayParams,output)->{
                        output.accept(ItemInit.QUICKSILVER_BULLET.get());
                        output.accept(ItemInit.THROWING_KNIFE.get());
                        output.accept(ItemInit.BONE_KNIFE.get());
                        output.accept(ItemInit.BLOOD_KNIFE.get());
                        output.accept(ItemInit.POISON_KNIFE.get());
                        output.accept(ItemInit.KUKRI.get());
                        output.accept(ItemInit.FIREBOMB.get());
                        output.accept(ItemInit.BLACK_FIREBOMB.get());
                        output.accept(ItemInit.MOLOTOV.get());
                        output.accept(ItemInit.BROKEN_STRAIGHTSWORD.get());
                        output.accept(ItemInit.BANDIT_KNIFE.get());
                        output.accept(ItemInit.LONGSWORD.get());
                        output.accept(ItemInit.DARKSWORD.get());
                        output.accept(ItemInit.SCIMITAR.get());
                        output.accept(ItemInit.FALCHION.get());
                        output.accept(ItemInit.BANDIT_CURVED_SWORD.get());
                        output.accept(ItemInit.SHOTEL.get());
                        output.accept(ItemInit.CARTHUS_SHOTEL.get());
                        output.accept(ItemInit.HUNTSMAN_CUTLASS.get());
                        output.accept(ItemInit.UCHIGATANA.get());
                        output.accept(ItemInit.SHADOW_BLADE.get());
                        output.accept(ItemInit.BATTLE_AXE.get());
                        output.accept(ItemInit.HUNTSMAN_AXE.get());
                        output.accept(ItemInit.CLAYMORE.get());
                        output.accept(ItemInit.FLAMBERGE.get());
                        output.accept(ItemInit.ZWEIHANDER.get());
                        output.accept(ItemInit.CARTHUS_CURVED_GREATSWORD.get());
                        //output.accept(ItemInit.SPEAR.get());
                        output.accept(ItemInit.CRESCENT_MOON_GREATAXE.get());
                        output.accept(ItemInit.EXECUTIONER_GREATAXE.get());
                        output.accept(ItemInit.SPEAR.get());
                        output.accept(ItemInit.HUNTSMAN_PITCHFORK.get());
                        output.accept(ItemInit.GRAVE_SCYTHE.get());
                        output.accept(ItemInit.CHURCH_SCYTHE_UNLIT.get());
                        output.accept(ItemInit.CHURCH_SCYTHE.get());
                        output.accept(ItemInit.SAW_CLEAVER.get());
                        output.accept(ItemInit.HUNTER_AXE.get());
                        output.accept(ItemInit.CHIKAGE_EXTENDED.get());
                        output.accept(ItemInit.HOLY_MOONLIGHT_LIT.get());
                        output.accept(ItemInit.STORM_CURVED_SWORD.get());
                        output.accept(ItemInit.DRAGONSLAYER_SPEAR.get());
                        output.accept(ItemInit.DRAGONSLAYER_SWORDSPEAR.get());
                        output.accept(ItemInit.DRAGONSLAYER_GREATAXE.get());
                        output.accept(ItemInit.FRAYED_BLADE.get());
                        output.accept(ItemInit.HUNTER_PISTOL.get());
                        output.accept(ItemInit.BLUNDERBUSS.get());
                        output.accept(ItemInit.MUSKET.get());
                        output.accept(ItemInit.REPEATING_PISTOL.get());
                        output.accept(ItemInit.EVELYN.get());
                        output.accept(ItemInit.GATLING_GUN.get());
                        output.accept(ItemInit.CHURCH_CANE.get());
                        output.accept(ItemInit.CRUCIFIX.get());
                        output.accept(ItemInit.HUNTER_TORCH.get());
                        output.accept(ItemInit.LANTERN.get());
                        output.accept(ItemInit.FLAMESPRAYER.get());
                        output.accept(ItemInit.SORCERER_STAFF_A.get());
                        output.accept(ItemInit.SORCERER_STAFF_B.get());
                        output.accept(ItemInit.CRYSTAL_STAFF.get());
                        output.accept(ItemInit.CRYSTAL_STAFF_PURPLE.get());
                        output.accept(ItemInit.CRYSTAL_STAFF_BLUE.get());
                        output.accept(ItemInit.CHAOS_STAFF.get());
                        output.accept(ItemInit.HUNTER_HAT.get());
                        output.accept(ItemInit.HUNTER_COAT.get());
                        output.accept(ItemInit.HUNTER_TROUSERS.get());
                        output.accept(ItemInit.HUNTER_BOOTS.get());
                        output.accept(ItemInit.ALCHEMIST_HAT.get());
                        output.accept(ItemInit.ALCHEMIST_COAT.get());
                        output.accept(ItemInit.ALCHEMIST_TROUSERS.get());
                        output.accept(ItemInit.ALCHEMIST_BOOTS.get());
                        output.accept(ItemInit.SPECIALIST_HAT.get());
                        output.accept(ItemInit.SPECIALIST_COAT.get());
                        output.accept(ItemInit.SPECIALIST_TROUSERS.get());
                        output.accept(ItemInit.SPECIALIST_BOOTS.get());
                        output.accept(ItemInit.TATTERED_WIZARD_HAT.get());
                        output.accept(ItemInit.TATTERED_WIZARD_ROBE.get());
                        output.accept(ItemInit.WIZARD_HAT.get());
                        output.accept(ItemInit.WIZARD_ROBE.get());
                        output.accept(ItemInit.WIZARD_PANTS.get());
                        output.accept(ItemInit.WIZARD_BOOTS.get());
                        output.accept(ItemInit.KNIGHT_HELM.get());
                        output.accept(ItemInit.KNIGHT_CHESTPLATE.get());
                        output.accept(ItemInit.KNIGHT_PANTS.get());
                        output.accept(ItemInit.KNIGHT_BOOTS.get());
                        Set<EnchantmentCategory> set = Set.of(
                                EnchantmentInit.GUN,
                                EnchantmentInit.AMMO_CONSUMER,
                                EnchantmentInit.MAGIC,
                                EnchantmentInit.WEAPON
                                /*CustomItemTags.WEAPON_ENCHANTABLE,
                                CustomItemTags.GUN_ENCHANTABLE,
                                CustomItemTags.MAGIC_ENCHANTABLE,
                                CustomItemTags.AMMO_CONSUMING*/
                        );
                        displayParams.holders().lookup(Registries.ENCHANTMENT).ifPresent((p_269984_) -> {
                            generateEnchantmentBookTypesOnlyMaxLevel(output, p_269984_, set, CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
                            generateEnchantmentBookTypesAllLevels(output, p_269984_, set, CreativeModeTab.TabVisibility.SEARCH_TAB_ONLY);
                        });

                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> MATERIALS =   TABS.register("materials",
            () ->CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.darkestsouls.materials"))
                    .icon(ItemInit.DARK_ESSENCE.get()::getDefaultInstance)
                    .withSearchBar().displayItems((displayParams,output)->{
                        output.accept(ItemInit.SMALL_SOUL_FRAGMENT.get());
                        output.accept(ItemInit.SOUL_FRAGMENT.get());
                        output.accept(ItemInit.RUNE_FRAGMENT.get());
                        output.accept(ItemInit.DARK_FRAGMENT.get());
                        output.accept(ItemInit.CHAOS_FRAGMENT.get());
                        output.accept(ItemInit.TITANITE_FRAGMENT.get());
                        output.accept(ItemInit.SIDERITE_FRAGMENT.get());
                        output.accept(ItemInit.DEMON_FRAGMENT.get());
                        output.accept(ItemInit.DEMON_TITANITE.get());
                        output.accept(ItemInit.TWINKLING_FRAGMENT.get());
                        output.accept(ItemInit.TWINKLING_TITANITE.get());
                        output.accept(ItemInit.DRAGON_SCALE_FRAGMENT.get());
                        output.accept(ItemInit.DRAGON_SCALE.get());
                        output.accept(ItemInit.INFUSED_DRAGON_SCALE.get());
                        output.accept(ItemInit.CORRUPTED_DRAGON_SCALE.get());
                        output.accept(ItemInit.BONE_FRAGMENT.get());
                        output.accept(ItemInit.BEAST_BLOOD_CLUMP.get());
                        output.accept(ItemInit.BLOOD_STONE_FRAGMENT.get());
                        output.accept(ItemInit.BLOOD_GEM.get());
                        output.accept(ItemInit.BEWITCHED_BRANCH.get());
                        output.accept(ItemInit.CHAOS_ROOT.get());
                        output.accept(ItemInit.GREEN_CRYSTAL.get());
                        output.accept(ItemInit.CRYSTAL.get());
                        output.accept(ItemInit.PURPLE_CRYSTAL.get());
                        output.accept(ItemInit.BLOOD_CRYSTAL.get());
                        output.accept(ItemInit.CINNABAR.get());
                        output.accept(ItemInit.QUICKSILVER.get());
                        output.accept(ItemInit.TORN_CLOTH_PIECE.get());
                        output.accept(ItemInit.ENCHANTED_TORN_CLOTH_PIECE.get());
                        output.accept(ItemInit.CLOTH_PIECE.get());
                        output.accept(ItemInit.ENCHANTED_CLOTH_PIECE.get());
                        output.accept(ItemInit.TORN_LEATHER_SCRAP.get());
                        output.accept(ItemInit.REINFORCED_LEATHER.get());
                        output.accept(ItemInit.RUSTY_MAIL_SCRAP.get());
                        output.accept(ItemInit.TORN_MAIL_SCRAP.get());
                        output.accept(ItemInit.REINFORCED_MAIL_SCRAP.get());
                        output.accept(ItemInit.RUSTY_METAL_SCRAP.get());
                        output.accept(ItemInit.METAL_PIECE.get());
                        output.accept(ItemInit.REINFORCED_METAL_PIECE.get());
                        output.accept(ItemInit.TITANITE_INGOT.get());
                        output.accept(ItemInit.TITANITE_NUGGET.get());
                        output.accept(ItemInit.GOLDEN_INGOT.get());
                        output.accept(ItemInit.GOLDEN_NUGGET.get());
                        output.accept(ItemInit.STEEL_INGOT.get());
                        output.accept(ItemInit.STEEL_NUGGET.get());
                        output.accept(ItemInit.SIDERITE_INGOT.get());
                        output.accept(ItemInit.SIDERITE_NUGGET.get());
                        output.accept(ItemInit.DEMON_INGOT.get());
                        output.accept(ItemInit.DEMON_NUGGET.get());
                        output.accept(ItemInit.TWINKLING_INGOT.get());
                        output.accept(ItemInit.TWINKLING_NUGGET.get());
                        output.accept(ItemInit.DARK_INGOT.get());
                        output.accept(ItemInit.DARK_NUGGET.get());
                        output.accept(ItemInit.HOLY_INGOT.get());
                        output.accept(ItemInit.HOLY_NUGGET.get());
                        output.accept(ItemInit.MAGIC_INGOT.get());
                        output.accept(ItemInit.MAGIC_NUGGET.get());
                        output.accept(ItemInit.CHAOS_INGOT.get());
                        output.accept(ItemInit.CHAOS_NUGGET.get());
                        output.accept(ItemInit.NIGHTMARE_INGOT.get());
                        output.accept(ItemInit.NIGHTMARE_NUGGET.get());
                        output.accept(ItemInit.BLOOD_INGOT.get());
                        output.accept(ItemInit.BLOOD_NUGGET.get());
                        output.accept(ItemInit.DRAGON_INGOT.get());
                        output.accept(ItemInit.DRAGON_NUGGET.get());
                        output.accept(ItemInit.LIGHTNING_INGOT.get());
                        output.accept(ItemInit.LIGHTNING_NUGGET.get());
                        output.accept(ItemInit.STRAIGHTSWORD_HILT.get());
                        output.accept(ItemInit.CURVED_SWORD_HILT.get());
                        output.accept(ItemInit.GREATSWORD_HILT.get());
                        output.accept(ItemInit.REINFORCED_HANDLE.get());
                        output.accept(ItemInit.CURVED_HANDLE.get());
                        output.accept(ItemInit.REINFORCED_POLE.get());
                        output.accept(ItemInit.GUN_HANDLE.get());
                        output.accept(ItemInit.REINFORCED_GUNMETAL_HANDLE.get());
                        output.accept(ItemInit.GUNMETAL_BARREL.get());
                        output.accept(ItemInit.REINFORCED_GUNMETAL_BARREL.get());
                        output.accept(ItemInit.REINFORCED_GUNMETAL_DOUBLE_BARREL.get());
                        output.accept(ItemInit.WORKSHOP_MECHANISM.get());
                        output.accept(ItemInit.IGNITION_MECHANISM.get());
                        output.accept(ItemInit.SOUL_ESSENCE.get());
                        output.accept(ItemInit.DARK_ESSENCE.get());
                        output.accept(ItemInit.FIRE_ESSENCE.get());
                        output.accept(ItemInit.CHAOS_ESSENCE.get());
                        output.accept(ItemInit.BLOOD_ESSENCE.get());
                        output.accept(ItemInit.POISON_ESSENCE.get());
                        output.accept(ItemInit.FROST_ESSENCE.get());
                        output.accept(ItemInit.ROT_ESSENCE.get());
                        output.accept(ItemInit.LIGHT_ESSENCE.get());
                        output.accept(ItemInit.LIGHTNING_ESSENCE.get());
                        output.accept(ItemInit.BEAST_ESSENCE.get());
                        output.accept(ItemInit.NIGHTMARE_ESSENCE.get());
                        output.accept(ItemInit.EYE.get());
                        output.accept(ItemInit.BEAST_EYE.get());
                        output.accept(ItemInit.BLIND_EYE.get());
                        output.accept(ItemInit.BLOSSOMED_EYE.get());
                        output.accept(ItemInit.BLOOD_STONE_SHARD.get());
                        output.accept(ItemInit.TWIN_BLOOD_STONE_SHARDS.get());
                        output.accept(ItemInit.BLOOD_STONE_CHUNK.get());
                        output.accept(ItemInit.BLOOD_ROCK.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> MOBS = TABS.register("mobs",
            () ->CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.darkestsouls.mobs"))
                    .icon(ItemInit.BONEWHEEL.get()::getDefaultInstance)
                    .withSearchBar().displayItems((displayParams,output)->{
                        output.accept(ItemInit.HOLLOW_BROKEN_STRAIGHTSWORD.get());
                        output.accept(ItemInit.GRAVETENDER_HOLLOW_BROKEN_STRAIGHTSWORD.get());
                        output.accept(ItemInit.GRAVETENDER_HOLLOW_LONGSWORD.get());
                        output.accept(ItemInit.GRAVETENDER_HOLLOW_CROSSBOW.get());
                        output.accept(ItemInit.HOLLOW_LONGSWORD.get());
                        output.accept(ItemInit.HOLLOW_AXE.get());
                        output.accept(ItemInit.HOLLOW_CROSSBOW.get());
                        output.accept(ItemInit.HOLLOW_ASSASSIN.get());
                        output.accept(ItemInit.EGG_HUNTSMAN_AXE.get());
                        output.accept(ItemInit.EGG_HUNTSMAN_CUTLASS.get());
                        output.accept(ItemInit.EGG_HUNTSMAN_PITCHFORK.get());
                        output.accept(ItemInit.EGG_HUNTSMAN_RIFLE.get());
                        output.accept(ItemInit.CHURCH_DOCTOR.get());
                        output.accept(ItemInit.CHURCH_DOCTOR_LANTERN.get());
                        output.accept(ItemInit.CHURCH_DOCTOR_PISTOL.get());
                        output.accept(ItemInit.CHURCH_DOCTOR_FLAMESPRAYER.get());
                        output.accept(ItemInit.CHURCH_DOCTOR_SCYTHE.get());
                        output.accept(ItemInit.CHURCH_DOCTOR_CRUCIFIX.get());
                        output.accept(ItemInit.SKELETON_FALCHION.get());
                        output.accept(ItemInit.SKELETON_SPEAR.get());
                        output.accept(ItemInit.SKELETON_CURVED_SWORDS.get());
                        output.accept(ItemInit.TALL_SKELETON_TWIN_SHOTELS.get());
                        output.accept(ItemInit.BONEWHEEL.get());
                        output.accept(ItemInit.BEAST_PATIENT.get());
                        output.accept(ItemInit.CLOAKED_BEAST_PATIENT.get());
                        output.accept(ItemInit.ASHEN_BLOOD_BEAST_PATIENT.get());
                        output.accept(ItemInit.DARKWRAITH.get());
                        output.accept(ItemInit.LEECH.get());
                        output.accept(ItemInit.SEWER_CENTIPEDE.get());
                        output.accept(ItemInit.SIN.get());
                        output.accept(ItemInit.SPAWN_GROUP_GRAVETENDER_HOLLOW_1.get());
                        output.accept(ItemInit.SPAWN_GROUP_HOLLOW_SOLDIER_1.get());
                        output.accept(ItemInit.SPAWN_GROUP_HUNTSMAN_1.get());
                        output.accept(ItemInit.SPAWN_GROUP_CHURCH_DOCTOR_1.get());
                        output.accept(ItemInit.SPAWN_GROUP_BEAST_PATIENT_1.get());
                        output.accept(ItemInit.SPAWN_GROUP_SKELETON_1.get());
                    })
                    .build());
    public static final RegistryObject<CreativeModeTab> BLOCKS = TABS.register("blocks",
            () ->CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.darkestsouls.blocks"))
                    .icon(ItemInit.CINNABAR_ORE.get()::getDefaultInstance)
                    .withSearchBar().displayItems((displayParams,output)->{
                        output.accept(ItemInit.CINNABAR_ORE.get());
                        output.accept(ItemInit.DEEPSLATE_CINNABAR_ORE.get());
                        output.accept(ItemInit.NETHER_CINNABAR_ORE.get());
                        output.accept(ItemInit.BLACKSTONE_CINNABAR_ORE.get());
                        output.accept(ItemInit.BASALT_CINNABAR_ORE.get());
                        output.accept(ItemInit.ENDER_CINNABAR_ORE.get());
                        output.accept(ItemInit.SIDERITE_ORE.get());
                        output.accept(ItemInit.DEEPSLATE_SIDERITE_ORE.get());
                        output.accept(ItemInit.NETHER_SIDERITE_ORE.get());
                        output.accept(ItemInit.BLACKSTONE_SIDERITE_ORE.get());
                        output.accept(ItemInit.ENDER_SIDERITE_ORE.get());
                        output.accept(ItemInit.SIDERITE_BRICKS.get());
                        output.accept(ItemInit.CRACKED_SIDERITE_BRICKS.get());
                        output.accept(ItemInit.SIDERITE_BRICKS_SLAB.get());
                        output.accept(ItemInit.SIDERITE_BRICKS_STAIRS.get());
                        output.accept(ItemInit.SIDERITE_BRICKS_WALL.get());
                    })
                    .build());





    private static void generateEnchantmentBookTypesOnlyMaxLevel(CreativeModeTab.Output p_270868_, HolderLookup<Enchantment> p_270903_, Set<EnchantmentCategory> p_270380_, CreativeModeTab.TabVisibility p_270407_) {
        p_270903_.listElements().map(Holder::value).filter((p_270008_) -> {
            return p_270008_.allowedInCreativeTab(Items.ENCHANTED_BOOK, p_270380_);
        }).map((p_270038_) -> {
            return EnchantedBookItem.createForEnchantment(new EnchantmentInstance(p_270038_, p_270038_.getMaxLevel()));
        }).forEach((p_269989_) -> {
            p_270868_.accept(p_269989_, p_270407_);
        });
    }

    private static void generateEnchantmentBookTypesAllLevels(CreativeModeTab.Output p_270961_, HolderLookup<Enchantment> p_270628_, Set<EnchantmentCategory> p_271024_, CreativeModeTab.TabVisibility p_270805_) {
        p_270628_.listElements().map(Holder::value).filter((p_269991_) -> {
            return p_269991_.allowedInCreativeTab(Items.ENCHANTED_BOOK, p_271024_);
        }).flatMap((p_270024_) -> {
            return IntStream.rangeClosed(p_270024_.getMinLevel(), p_270024_.getMaxLevel()).mapToObj((p_270006_) -> {
                return EnchantedBookItem.createForEnchantment(new EnchantmentInstance(p_270024_, p_270006_));
            });
        }).forEach((p_270017_) -> {
            p_270961_.accept(p_270017_, p_270805_);
        });
    }


}
