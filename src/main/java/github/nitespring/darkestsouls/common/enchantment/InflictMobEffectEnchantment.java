package github.nitespring.darkestsouls.common.enchantment;

import github.nitespring.darkestsouls.common.item.Weapon;
import github.nitespring.darkestsouls.core.init.EffectInit;
import github.nitespring.darkestsouls.core.init.EnchantmentInit;
import github.nitespring.darkestsouls.core.util.CustomEntityTags;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;

public class InflictMobEffectEnchantment extends Enchantment {
    private int maxLevel;
    private MobEffect mobEffect;
    public InflictMobEffectEnchantment(int maxLevel, MobEffect effect, Rarity rarity) {
        super(rarity,  EnchantmentInit.WEAPON, EnchantmentInit.HAND_SLOTS);
        this.maxLevel=maxLevel;
        this.mobEffect=effect;

    }

    public MobEffect getMobEffect() {
        return mobEffect;
    }
    @Override
    public int getMinCost(int i) {
        return 8 * i;
    }

    @Override
    public int getMaxCost(int i) {
        return this.getMinCost(i) + 30;
    }
    @Override
    public int getMaxLevel() {
        return maxLevel;
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
    /*@Override
    public void doPostAttack(LivingEntity attacker, Entity pTarget, int level) {
        if(!(attacker.getMainHandItem().getItem() instanceof Weapon)) {
            if(pTarget instanceof LivingEntity target) {
                EntityType<?> type = target.getType();
                if(getMobEffect()== MobEffects.POISON||getMobEffect()== EffectInit.TOXIC.getHolder().get()) {
                    if (!type.is(CustomEntityTags.POISON_IMMUNE)) {
                        target.addEffect(new MobEffectInstance(getMobEffect().get(), 90 + level * 45, level - 1), attacker);
                    }
                }else if(getMobEffect()==MobEffects.WITHER) {
                    if (!type.is(CustomEntityTags.WITHER_IMMUNE)) {
                        target.addEffect(new MobEffectInstance(getMobEffect().get(), 90 + level * 45, level - 1), attacker);
                    }
                }else if(getMobEffect()==EffectInit.FROST.getHolder().get()) {
                    if (!type.is(CustomEntityTags.FROST_IMMUNE)) {
                        target.addEffect(new MobEffectInstance(getMobEffect().get(), 90 + level * 45, level - 1), attacker);
                    }
                }else if(getMobEffect().get()==EffectInit.ROT.getHolder().get()) {
                    if (!type.is(CustomEntityTags.ROT_IMMUNE)) {
                        target.addEffect(new MobEffectInstance(getMobEffect().get(), 90 + level * 45, level - 1), attacker);
                    }
                }else{
                    target.addEffect(new MobEffectInstance(getMobEffect().get(), 90 + level * 45, level - 1), attacker);
                }
            }
        }
    }*/

}
