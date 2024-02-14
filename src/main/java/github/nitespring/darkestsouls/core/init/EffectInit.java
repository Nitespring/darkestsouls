package github.nitespring.darkestsouls.core.init;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.effects.BleedMobEffect;
import github.nitespring.darkestsouls.common.effects.ChikageMobEffect;
import github.nitespring.darkestsouls.common.effects.RotMobEffect;
import github.nitespring.darkestsouls.common.effects.SimpleMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffectInit {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS,
            DarkestSouls.MODID);

    public static final RegistryObject<BleedMobEffect> BLEED =   EFFECTS.register("hemorragie",
            () -> new BleedMobEffect(MobEffectCategory.HARMFUL,7673883));

    public static final RegistryObject<MobEffect> FROST =   EFFECTS.register("frost",
            () -> new SimpleMobEffect(MobEffectCategory.HARMFUL,15072511)
                    .addAttributeModifier(Attributes.ARMOR,"648d7064-6A60-4F59-8ABE-C2C23A6DD7A9",
                            -2.0, AttributeModifier.Operation.ADDITION)
                    .addAttributeModifier(Attributes.ARMOR_TOUGHNESS,"648d7064-6A60-4F59-8ABE-C2C23A6DD7A9",
                            -1.0, AttributeModifier.Operation.ADDITION)
                    .addAttributeModifier(Attributes.ATTACK_SPEED,"55FCED67-E92A-486E-9800-B47F202C4386",
                            -0.05000000149011612, AttributeModifier.Operation.MULTIPLY_TOTAL)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED,"7107DE5E-7CE8-4030-940E-514C1F160890",
                            -0.15000000596046448, AttributeModifier.Operation.MULTIPLY_TOTAL)
    );

    public static final RegistryObject<RotMobEffect> ROT =   EFFECTS.register("rot",
            () -> new RotMobEffect(MobEffectCategory.HARMFUL,7673883));
    public static final RegistryObject<ChikageMobEffect> CHIKAGE=EFFECTS.register("chikage",
            () -> new ChikageMobEffect(MobEffectCategory.HARMFUL, 7673883));

    public static final RegistryObject<MobEffect> FAKE_POISON=EFFECTS.register("fake_poison",
            () -> new SimpleMobEffect(MobEffectCategory.NEUTRAL, 5149489));




}
