package github.nitespring.darkestsouls.core.init;



import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.mob.abyss.SewerCentipede;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.Bonewheel;
import github.nitespring.darkestsouls.common.entity.mob.abyss.MonstruosityOfSin;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.SkeletonCurvedSwords;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.SkeletonFalchion;
import github.nitespring.darkestsouls.common.entity.projectile.spell.*;
import github.nitespring.darkestsouls.common.entity.projectile.weapon.FrayedBladeAttackEntity;
import github.nitespring.darkestsouls.common.entity.projectile.weapon.FrayedBladeFlameEntity;
import github.nitespring.darkestsouls.common.entity.projectile.weapon.WeaponAttackEntity;
import github.nitespring.darkestsouls.common.entity.util.DamageHitboxEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,
			 DarkestSouls.MODID);
	

	public static final RegistryObject<EntityType<MonstruosityOfSin>> SIN = ENTITIES.register("monstruosity_of_sin",
			() -> EntityType.Builder.<MonstruosityOfSin>of(MonstruosityOfSin::new, MobCategory.MONSTER)
			.sized(2.8f, 2.2f)
			.build("monstruosity_of_sin"));
	public static final RegistryObject<EntityType<Bonewheel>> BONEWHEEL = ENTITIES.register("bonewheel",
			() -> EntityType.Builder.<Bonewheel>of(Bonewheel::new, MobCategory.MONSTER)
					.sized(0.6f, 1.8f)
					.build("bonewheel"));
	public static final RegistryObject<EntityType<SkeletonFalchion>> SKELETON_FALCHION = ENTITIES.register("skeleton_falchion",
			() -> EntityType.Builder.<SkeletonFalchion>of(SkeletonFalchion::new, MobCategory.MONSTER)
					.sized(0.6f, 1.8f)
					.build("skeleton_falchion"));
	public static final RegistryObject<EntityType<SkeletonCurvedSwords>> SKELETON_CURVED_SWORDS = ENTITIES.register("skeleton_curved_swords",
			() -> EntityType.Builder.<SkeletonCurvedSwords>of(SkeletonCurvedSwords::new, MobCategory.MONSTER)
					.sized(0.6f, 1.8f)
					.build("skeleton_curved_swords"));
	public static final RegistryObject<EntityType<SewerCentipede>> SEWER_CENTIPEDE = ENTITIES.register("sewer_centipede",
			() -> EntityType.Builder.<SewerCentipede>of(SewerCentipede::new, MobCategory.MONSTER)
					.sized(1.4f, 1.2f)
					.build("sewer_centipede"));


	public static final RegistryObject<EntityType<DamageHitboxEntity>> HITBOX_SMALL = ENTITIES.register("hitbox_small",
			() -> EntityType.Builder.<DamageHitboxEntity>of(DamageHitboxEntity::new, MobCategory.MISC)
					.sized(1.0f, 1.0f)
					.build("hitbox_small"));
	public static final RegistryObject<EntityType<DamageHitboxEntity>> HITBOX = ENTITIES.register("hitbox",
			() -> EntityType.Builder.<DamageHitboxEntity>of(DamageHitboxEntity::new, MobCategory.MISC)
					.sized(2.0f, 2.0f)
					.build("hitbox"));
	public static final RegistryObject<EntityType<DamageHitboxEntity>> HITBOX_LARGE = ENTITIES.register("hitbox_large",
			() -> EntityType.Builder.<DamageHitboxEntity>of(DamageHitboxEntity::new, MobCategory.MISC)
					.sized(4.0f, 4.0f)
					.build("hitbox_large"));

	public static final RegistryObject<EntityType<FrayedBladeAttackEntity>> FRAYED_BLADE = ENTITIES.register("frayed_blade",
			() -> EntityType.Builder.<FrayedBladeAttackEntity>of(FrayedBladeAttackEntity::new, MobCategory.MISC)
					.sized(2.0f, 2.0f)
					.build("frayed_blade"));
	public static final RegistryObject<EntityType<FrayedBladeFlameEntity>> FRAYED_BLADE_FLAME = ENTITIES.register("frayed_blade_flame",
			() -> EntityType.Builder.<FrayedBladeFlameEntity>of(FrayedBladeFlameEntity::new, MobCategory.MISC)
					.sized(1.0f, 1.5f)
					.build("frayed_blade_flame"));
	public static final RegistryObject<EntityType<WeaponAttackEntity>> SCIMITAR = ENTITIES.register("scimitar",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(1.0f, 1.0f)
					.build("scimitar"));
	public static final RegistryObject<EntityType<WeaponAttackEntity>> FALCHION = ENTITIES.register("falchion",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(1.1f, 1.1f)
					.build("falchion"));
	public static final RegistryObject<EntityType<WeaponAttackEntity>> CLAYMORE = ENTITIES.register("claymore",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(2.0f, 1.75f)
					.build("claymore"));
	public static final RegistryObject<EntityType<WeaponAttackEntity>> FLAMBERGE = ENTITIES.register("flamberge",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(2.0f, 1.75f)
					.build("flamberge"));
	public static final RegistryObject<EntityType<WeaponAttackEntity>> ZWEIHANDER = ENTITIES.register("zweihander",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(2.5f, 1.75f)
					.build("zweihander"));
	public static final RegistryObject<EntityType<WeaponAttackEntity>> UCHIGATANA = ENTITIES.register("uchigatana",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(2.0f, 1.5f)
					.build("uchigatana"));
	public static final RegistryObject<EntityType<WeaponAttackEntity>> LONGSWORD = ENTITIES.register("longsword",
			() -> EntityType.Builder.<WeaponAttackEntity>of(WeaponAttackEntity::new, MobCategory.MISC)
					.sized(1.5f, 1.25f)
					.build("uchigatana"));

	public static final RegistryObject<EntityType<SoulDart>> SOUL_DART = ENTITIES.register("soul_dart",
			() -> EntityType.Builder.<SoulDart>of(SoulDart::new, MobCategory.MISC)
					.sized(1.0f, 1.0f)
					.build("soul_dart"));
	public static final RegistryObject<EntityType<Fireball>> FIREBALL = ENTITIES.register("fireball",
			() -> EntityType.Builder.<Fireball>of(Fireball::new, MobCategory.MISC)
					.sized(1.25f, 1.25f)
					.build("fireball"));
	public static final RegistryObject<EntityType<ChaosFireball>> CHAOS_FIREBALL = ENTITIES.register("chaos_fireball",
			() -> EntityType.Builder.<ChaosFireball>of(ChaosFireball::new, MobCategory.MISC)
					.sized(2.0f, 2.0f)
					.build("chaos_fireball"));

	public static final RegistryObject<EntityType<MagmaEntity>> MAGMA = ENTITIES.register("magma",
			() -> EntityType.Builder.<MagmaEntity>of(MagmaEntity::new, MobCategory.MISC)
					.sized(1.0f, 0.4f)
					.build("magma"));

	public static final RegistryObject<EntityType<MagmaBurstParent>> MAGMA_BURST = ENTITIES.register("magma_burst",
			() -> EntityType.Builder.<MagmaBurstParent>of(MagmaBurstParent::new, MobCategory.MISC)
					.sized(0.8f, 0.8f)
					.build("magma_burst"));

	public static final RegistryObject<EntityType<MagmaBurstEntity>> MAGMA_BURST_CHILD = ENTITIES.register("magma_burst_child",
			() -> EntityType.Builder.<MagmaBurstEntity>of(MagmaBurstEntity::new, MobCategory.MISC)
					.sized(0.6f, 0.6f)
					.build("magma_burst_child"));



}
