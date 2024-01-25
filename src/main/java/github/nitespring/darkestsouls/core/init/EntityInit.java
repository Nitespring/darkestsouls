package github.nitespring.darkestsouls.core.init;



import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.mob.abyss.Bonewheel;
import github.nitespring.darkestsouls.common.entity.mob.abyss.MonstruosityOfSin;
import github.nitespring.darkestsouls.common.entity.projectile.*;
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
	public static final RegistryObject<EntityType<ScimitarAttackEntity>> SCIMITAR = ENTITIES.register("scimitar",
			() -> EntityType.Builder.<ScimitarAttackEntity>of(ScimitarAttackEntity::new, MobCategory.MISC)
					.sized(1.0f, 1.0f)
					.build("scimitar"));
	public static final RegistryObject<EntityType<FalchionAttackEntity>> FALCHION = ENTITIES.register("falchion",
			() -> EntityType.Builder.<FalchionAttackEntity>of(FalchionAttackEntity::new, MobCategory.MISC)
					.sized(1.0f, 1.0f)
					.build("falchion"));
	public static final RegistryObject<EntityType<ClaymoreAttackEntity>> CLAYMORE = ENTITIES.register("claymore",
			() -> EntityType.Builder.<ClaymoreAttackEntity>of(ClaymoreAttackEntity::new, MobCategory.MISC)
					.sized(2.0f, 2.0f)
					.build("claymore"));

}
