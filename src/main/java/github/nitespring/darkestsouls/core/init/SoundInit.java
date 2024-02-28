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

    private static RegistryObject<SoundEvent> build(String id)
    {
        return SOUNDS.register(id, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(DarkestSouls.MODID, id)));

    }

}