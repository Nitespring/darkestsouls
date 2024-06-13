package github.nitespring.darkestsouls.common.enchantment;

import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;

import javax.annotation.Nullable;
import java.util.Optional;


public class PercentageDamageEnchantment extends Enchantment {

    private final TagKey<EntityType<?>> targets;

    public PercentageDamageEnchantment(Enchantment.EnchantmentDefinition pDefinition, TagKey<EntityType<?>> pTargets) {
        super(pDefinition);
        this.targets = pTargets;
    }
    /*@Override
    public float getDamageBonus(int pLevel, EntityType<?> pCreatureType, ItemStack stackIn) {
            return pCreatureType != null && pCreatureType.is(this.targets.get()) ? (float)pLevel * 2.5F : 0.0F;
    }*/
    @Override
    public boolean checkCompatibility(Enchantment pEnch) {
        return !(pEnch instanceof PercentageDamageEnchantment);
    }
    /*@Override
    public void doPostAttack(LivingEntity pUser, Entity pTarget, int pLevel) {
        if (this.targets.isPresent()
                && pTarget instanceof LivingEntity livingentity
                && this.targets.get() == EntityTypeTags.SENSITIVE_TO_BANE_OF_ARTHROPODS
                && pLevel > 0
                && livingentity.getType().is(this.targets.get())) {
            int i = 20 + pUser.getRandom().nextInt(10 * pLevel);
            livingentity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, i, 3));
        }
    }*/
}

