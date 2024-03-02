package github.nitespring.darkestsouls.core.init;

import github.nitespring.darkestsouls.DarkestSouls;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import software.bernie.example.registry.SoundRegistry;

@Mod.EventBusSubscriber(modid = DarkestSouls.MODID)
public class SoundInit {


    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS,
            DarkestSouls.MODID);


    public static final RegistryObject<SoundEvent> BEAST_PATIENT_IDLE = build("entity.beast_patient_idle");
    public static final RegistryObject<SoundEvent> BEAST_PATIENT_HURT = build("entity.beast_patient_hurt");
    public static final RegistryObject<SoundEvent> BEAST_PATIENT_ATTACK = build("entity.beast_patient_attack");
    public static final RegistryObject<SoundEvent> BEAST_PATIENT_SCREAM = build("entity.beast_patient_scream");

    public static final RegistryObject<SoundEvent> SIN_IDLE = build("entity.monstruosity_of_sin_idle");
    public static final RegistryObject<SoundEvent> SIN_HURT = build("entity.monstruosity_of_sin_hurt");
    public static final RegistryObject<SoundEvent> SIN_SCREAM = build("entity.monstruosity_of_sin_scream");
    public static final RegistryObject<SoundEvent> SIN_BOOM = build("entity.monstruosity_of_sin_boom");
    public static final RegistryObject<SoundEvent> SIN_DEATH = build("entity.monstruosity_of_sin_death");

    public static final RegistryObject<SoundEvent> HOLLOW_IDLE = build("entity.hollow_idle");
    public static final RegistryObject<SoundEvent> HOLLOW_HURT = build("entity.hollow_hurt");
    public static final RegistryObject<SoundEvent> HOLLOW_ATTACK = build("entity.hollow_attack");
    public static final RegistryObject<SoundEvent> HOLLOW_DEATH = build("entity.hollow_death");

    public static final RegistryObject<SoundEvent> SEWER_CENTIPEDE_IDLE = build("entity.sewer_centipede_idle");
    public static final RegistryObject<SoundEvent> SEWER_CENTIPEDE_DEATH = build("entity.sewer_centipede_death");
    public static final RegistryObject<SoundEvent> SEWER_CENTIPEDE_HURT = build("entity.sewer_centipede_hurt");
    public static final RegistryObject<SoundEvent> SEWER_CENTIPEDE_STEP = build("entity.sewer_centipede_step");


    private static RegistryObject<SoundEvent> build(String id)
    {
        return SOUNDS.register(id, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(DarkestSouls.MODID, id)));

    }

}