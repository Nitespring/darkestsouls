package github.nitespring.darkestsouls.core.init;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.effect.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffectInit {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS,
            DarkestSouls.MODID);

    public static final RegistryObject<MobEffect> BLEED =   EFFECTS.register("hemorragie",
            () -> new BleedMobEffect(MobEffectCategory.HARMFUL,7673883));

    public static final RegistryObject<MobEffect> FROST =   EFFECTS.register("frost",
            () -> new FrostMobEffect(MobEffectCategory.HARMFUL,15072511)
                    .addAttributeModifier(Attributes.ARMOR, ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID,"effect.weakness"),
                            -2.0, AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.ARMOR_TOUGHNESS,ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID,"effect.weakness_toughness"),
                            -1.0, AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.ATTACK_SPEED,ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID,"effect.attack_slowness"),
                            -0.05000000149011612, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED,ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID,"effect.slowness"),
                            -0.15000000596046448, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    );

    public static final RegistryObject<MobEffect> ROT =   EFFECTS.register("rot",
            () -> new RotMobEffect(MobEffectCategory.HARMFUL,7673883));
    public static final RegistryObject<MobEffect> TOXIC = EFFECTS.register("toxic",
            () -> new ToxicMobEffect(MobEffectCategory.HARMFUL, 5377115));
    public static final RegistryObject<MobEffect> PARASITES =   EFFECTS.register("parasites",
            () -> new ParasitesMobEffect(MobEffectCategory.HARMFUL,7673883));
    public static final RegistryObject<MobEffect> CHIKAGE=EFFECTS.register("chikage",
            () -> new ChikageMobEffect(MobEffectCategory.HARMFUL, 7673883));

    public static final RegistryObject<MobEffect> FAKE_POISON=EFFECTS.register("fake_poison",
            () -> new SimpleMobEffect(MobEffectCategory.NEUTRAL, 5149489));




}
