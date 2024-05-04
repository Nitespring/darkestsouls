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
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			DarkestSouls.MODID);

	//Ores

	public static final RegistryObject<DropExperienceBlock> CINNABAR_ORE = BLOCKS.register("cinnabar_ore",
			() -> new DropExperienceBlock(UniformInt.of(1, 5),BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final RegistryObject<DropExperienceBlock> SIDERITE_ORE = BLOCKS.register("siderite_ore",
			() -> new DropExperienceBlock(UniformInt.of(2, 8),BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f)/*.lightLevel((p_50872_) -> {
				return 1;
			})*/.pushReaction(PushReaction.BLOCK)));
	public static final RegistryObject<DropExperienceBlock> DEEPSLATE_CINNABAR_ORE = BLOCKS.register("deepslate_cinnabar_ore",
			() -> new DropExperienceBlock(UniformInt.of(1, 5),BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(3.25f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final RegistryObject<DropExperienceBlock> DEEPSLATE_SIDERITE_ORE = BLOCKS.register("deepslate_siderite_ore",
			() -> new DropExperienceBlock(UniformInt.of(2, 8),BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(3.25f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final RegistryObject<DropExperienceBlock> NETHER_CINNABAR_ORE = BLOCKS.register("nether_cinnabar_ore",
			() -> new DropExperienceBlock(UniformInt.of(1, 5),BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.NETHER_ORE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final RegistryObject<DropExperienceBlock> NETHER_SIDERITE_ORE = BLOCKS.register("nether_siderite_ore",
			() -> new DropExperienceBlock(UniformInt.of(2, 8),BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.NETHER_ORE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final RegistryObject<DropExperienceBlock> BLACKSTONE_CINNABAR_ORE = BLOCKS.register("blackstone_cinnabar_ore",
			() -> new DropExperienceBlock(UniformInt.of(1, 5),BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final RegistryObject<DropExperienceBlock> BLACKSTONE_SIDERITE_ORE = BLOCKS.register("blackstone_siderite_ore",
			() -> new DropExperienceBlock(UniformInt.of(2, 8),BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final RegistryObject<DropExperienceBlock> ENDER_CINNABAR_ORE = BLOCKS.register("ender_cinnabar_ore",
			() -> new DropExperienceBlock(UniformInt.of(1, 5),BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(3.25f,9.5f).pushReaction(PushReaction.BLOCK)));
	public static final RegistryObject<DropExperienceBlock> ENDER_SIDERITE_ORE = BLOCKS.register("ender_siderite_ore",
			() -> new DropExperienceBlock(UniformInt.of(2, 8),BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(3.25f,9.5f).pushReaction(PushReaction.BLOCK)));
	public static final RegistryObject<DropExperienceBlock> BASALT_CINNABAR_ORE = BLOCKS.register("basalt_cinnabar_ore",
			() -> new DropExperienceBlock(UniformInt.of(1, 5),BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.BASALT).requiresCorrectToolForDrops().strength(2.0f,7.0f).pushReaction(PushReaction.BLOCK)));

	public static final RegistryObject<Block> SIDERITE_BRICKS = BLOCKS.register("siderite_bricks",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final RegistryObject<Block> CRACKED_SIDERITE_BRICKS = BLOCKS.register("cracked_siderite_bricks",
			() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final RegistryObject<SlabBlock> SIDERITE_BRICKS_SLAB = BLOCKS.register("siderite_brick_slab",
			() -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final RegistryObject<StairBlock> SIDERITE_BRICKS_STAIRS = BLOCKS.register("siderite_brick_stairs",
			() -> new StairBlock(SIDERITE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK)));
	public static final RegistryObject<WallBlock> SIDERITE_BRICKS_WALL = BLOCKS.register("siderite_brick_wall",
			() -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.CHIME).sound(SoundType.STONE).requiresCorrectToolForDrops().strength(1.75f,6.5f).pushReaction(PushReaction.BLOCK).forceSolidOn()));

}
