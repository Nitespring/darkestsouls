package github.nitespring.darkestsouls.core.init;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.item.Weapon;
import github.nitespring.darkestsouls.common.item.child.alchemy.Flamesprayer;
import github.nitespring.darkestsouls.common.item.child.alchemy.LanternNormal;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			DarkestSouls.MODID);
	


	//Special Weapons

	public static final RegistryObject<Block> CINNABAR_ORE = BLOCKS.register("cinnabar_ore",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final RegistryObject<Block> SIDERITE_ORE = BLOCKS.register("siderite_ore",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f)/*.lightLevel((p_50872_) -> {
				return 1;
			})*/.pushReaction(PushReaction.BLOCK)));
	public static final RegistryObject<Block> DEEPSLATE_CINNABAR_ORE = BLOCKS.register("deepslate_cinnabar_ore",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(3.25f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final RegistryObject<Block> DEEPSLATE_SIDERITE_ORE = BLOCKS.register("deepslate_siderite_ore",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(3.25f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final RegistryObject<Block> NETHER_CINNABAR_ORE = BLOCKS.register("nether_cinnabar_ore",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final RegistryObject<Block> NETHER_SIDERITE_ORE = BLOCKS.register("nether_siderite_ore",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final RegistryObject<Block> BLACKSTONE_CINNABAR_ORE = BLOCKS.register("blackstone_cinnabar_ore",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final RegistryObject<Block> BLACKSTONE_SIDERITE_ORE = BLOCKS.register("blackstone_siderite_ore",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final RegistryObject<Block> ENDER_CINNABAR_ORE = BLOCKS.register("ender_cinnabar_ore",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(3.25f,9.5f).pushReaction(PushReaction.BLOCK)));
	public static final RegistryObject<Block> ENDER_SIDERITE_ORE = BLOCKS.register("ender_siderite_ore",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(3.25f,9.5f).pushReaction(PushReaction.BLOCK)));


}
