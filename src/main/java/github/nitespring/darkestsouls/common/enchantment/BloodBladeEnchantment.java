package github.nitespring.darkestsouls.common.enchantment;

import github.nitespring.darkestsouls.common.item.Weapon;
import github.nitespring.darkestsouls.core.init.EffectInit;
import github.nitespring.darkestsouls.core.util.CustomEntityTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

public class BloodBladeEnchantment extends Enchantment {
    public BloodBladeEnchantment(EnchantmentDefinition pDefinition) {
        super(pDefinition);
    }
    @Override
    public boolean isTradeable() {
        return true;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }

    @Override
    public boolean isCurse() {
        return false;
    }

    @Override
    public boolean isTreasureOnly() {
        return false;
    }

    @Override
    public boolean isDiscoverable() {
        return true;
    }

    @Override
    public void doPostAttack(LivingEntity attacker, Entity pTarget, int level) {
        if(!(attacker.getMainHandItem().getItem() instanceof Weapon)) {
            if(pTarget instanceof LivingEntity target) {
                EntityType<?> type = target.getType();
                if (!type.is(CustomEntityTags.BLEED_IMMUNE)) {
                    if (target.hasEffect(EffectInit.BLEED.getHolder().get())) {
                        int amount = target.getEffect(EffectInit.BLEED.getHolder().get()).getAmplifier() + 1 + 2 * level;
                        target.removeEffect(EffectInit.BLEED.getHolder().get());
                        target.addEffect(new MobEffectInstance(EffectInit.BLEED.getHolder().get(), 240, amount));
                    } else {
                        int amount = 1+2*level;
                        target.addEffect(new MobEffectInstance(EffectInit.BLEED.getHolder().get(), 240, amount));
                    }
                }
            }
        }
    }
}
