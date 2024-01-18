package github.nitespring.darkestsouls.core.init;



import github.nitespring.darkestsouls.DarkestSouls;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityInit {
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,
			 DarkestSouls.MODID);
	
	/*
	public static final RegistryObject<EntityType<EvilSnowman>> SNOWMAN = ENTITIES.register("evil_snowman",
			() -> EntityType.Builder.<EvilSnowman>of(EvilSnowman::new, MobCategory.MONSTER)
			.sized(0.8f, 1.9f)
			.build("evil_snowman"));
	*/

}
