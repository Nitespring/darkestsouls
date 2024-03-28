package github.nitespring.darkestsouls.core.init;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.effects.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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
                        output.accept(ItemInit.LONGSWORD.get());
                        output.accept(ItemInit.SCIMITAR.get());
                        output.accept(ItemInit.FALCHION.get());
                        output.accept(ItemInit.BANDIT_CURVED_SWORD.get());
                        output.accept(ItemInit.SHOTEL.get());
                        output.accept(ItemInit.CARTHUS_SHOTEL.get());
                        output.accept(ItemInit.HUNTSMAN_CUTLASS.get());
                        output.accept(ItemInit.UCHIGATANA.get());
                        output.accept(ItemInit.SHADOW_BLADE.get());
                        output.accept(ItemInit.BATTLE_AXE.get());
                        output.accept(ItemInit.CLAYMORE.get());
                        output.accept(ItemInit.FLAMBERGE.get());
                        output.accept(ItemInit.ZWEIHANDER.get());
                        output.accept(ItemInit.CARTHUS_CURVED_GREATSWORD.get());
                        output.accept(ItemInit.SPEAR.get());
                        output.accept(ItemInit.CRESCENT_MOON_GREATAXE.get());
                        output.accept(ItemInit.EXECUTIONER_GREATAXE.get());
                        output.accept(ItemInit.SPEAR.get());
                        output.accept(ItemInit.HUNTSMAN_PITCHFORK.get());
                        output.accept(ItemInit.HUNTSMAN_AXE.get());
                        output.accept(ItemInit.GRAVE_SCYTHE.get());
                        output.accept(ItemInit.CHURCH_SCYTHE.get());
                        output.accept(ItemInit.SAW_CLEAVER.get());
                        output.accept(ItemInit.HUNTER_AXE.get());
                        output.accept(ItemInit.CHIKAGE.get());
                        output.accept(ItemInit.STORM_CURVED_SWORD.get());
                        output.accept(ItemInit.DRAGONSLAYER_SPEAR.get());
                        output.accept(ItemInit.DRAGONSLAYER_SWORDSPEAR.get());
                        output.accept(ItemInit.DRAGONSLAYER_GREATAXE.get());
                        output.accept(ItemInit.FRAYED_BLADE.get());
                        output.accept(ItemInit.HUNTER_PISTOL.get());
                        output.accept(ItemInit.HUNTER_TORCH.get());
                        output.accept(ItemInit.SORCERER_STAFF_A.get());
                        output.accept(ItemInit.SORCERER_STAFF_B.get());
                        output.accept(ItemInit.CRYSTAL_STAFF.get());
                        output.accept(ItemInit.CRYSTAL_STAFF_PURPLE.get());
                        output.accept(ItemInit.CRYSTAL_STAFF_BLUE.get());
                        output.accept(ItemInit.CHAOS_STAFF.get());
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
                        output.accept(ItemInit.WORKSHOP_MECHANISM.get());
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

    public static final RegistryObject<CreativeModeTab> MOBS =   TABS.register("mobs",
            () ->CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.darkestsouls.mobs"))
                    .icon(ItemInit.BONEWHEEL.get()::getDefaultInstance)
                    .withSearchBar().displayItems((displayParams,output)->{
                        output.accept(ItemInit.HOLLOW_BROKEN_STRAIGHTSWORD.get());
                        output.accept(ItemInit.GRAVETENDER_HOLLOW_BROKEN_STRAIGHTSWORD.get());
                        output.accept(ItemInit.GRAVETENDER_HOLLOW_LONGSWORD.get());
                        output.accept(ItemInit.HOLLOW_LONGSWORD.get());
                        output.accept(ItemInit.HOLLOW_AXE.get());
                        output.accept(ItemInit.SKELETON_FALCHION.get());
                        output.accept(ItemInit.SKELETON_SPEAR.get());
                        output.accept(ItemInit.SKELETON_CURVED_SWORDS.get());
                        output.accept(ItemInit.BONEWHEEL.get());
                        output.accept(ItemInit.BEAST_PATIENT.get());
                        output.accept(ItemInit.CLOAKED_BEAST_PATIENT.get());
                        output.accept(ItemInit.ASHEN_BLOOD_BEAST_PATIENT.get());
                        output.accept(ItemInit.LEECH.get());
                        output.accept(ItemInit.SEWER_CENTIPEDE.get());
                        output.accept(ItemInit.SIN.get());
                    })
                    .build());






}
