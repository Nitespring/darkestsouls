package github.nitespring.darkestsouls.common.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class ToxicMobEffect extends MobEffect {
    public ToxicMobEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity e, int p_296233_) {



            e.hurt(e.level().damageSources().magic(), 2.0F);

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int p_295368_, int p_294232_) {
        int i = 25 >> p_294232_;
        return i > 0 ? p_295368_ % i == 0 : true;
    }

}
