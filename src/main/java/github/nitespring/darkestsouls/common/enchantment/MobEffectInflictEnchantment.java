package github.nitespring.darkestsouls.common.enchantment;

import github.nitespring.darkestsouls.common.item.Weapon;
import github.nitespring.darkestsouls.core.init.EffectInit;
import github.nitespring.darkestsouls.core.util.CustomEntityTags;
import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;

public class MobEffectInflictEnchantment extends Enchantment {
    private final Holder<MobEffect> effect;
    public MobEffectInflictEnchantment(EnchantmentDefinition pDefinition, Holder<MobEffect> pEffect) {
        super(pDefinition);
        this.effect=pEffect;
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
                if(effect==MobEffects.POISON||effect==EffectInit.TOXIC.getHolder().get()) {
                    if (!type.is(CustomEntityTags.POISON_IMMUNE)) {
                        target.addEffect(new MobEffectInstance(effect, 90 + level * 45, level - 1), attacker);
                    }
                }else if(effect==MobEffects.WITHER) {
                    if (!type.is(CustomEntityTags.WITHER_IMMUNE)) {
                        target.addEffect(new MobEffectInstance(MobEffects.WITHER, 90 + level * 45, level - 1), attacker);
                    }
                }else if(effect==EffectInit.FROST.getHolder().get()) {
                    if (!type.is(CustomEntityTags.FROST_IMMUNE)) {
                        target.addEffect(new MobEffectInstance(EffectInit.FROST.getHolder().get(), 90 + level * 45, level - 1), attacker);
                    }
                }else if(effect==EffectInit.ROT.getHolder().get()) {
                    if (!type.is(CustomEntityTags.ROT_IMMUNE)) {
                        target.addEffect(new MobEffectInstance(EffectInit.ROT.getHolder().get(), 90 + level * 45, level - 1), attacker);
                    }
                }else{
                    target.addEffect(new MobEffectInstance(effect, 90 + level * 45, level - 1), attacker);
                }
            }
        }
    }
}
