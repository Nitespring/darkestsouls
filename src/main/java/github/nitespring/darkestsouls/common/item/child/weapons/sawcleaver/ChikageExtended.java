package github.nitespring.darkestsouls.common.item.child.weapons.sawcleaver;

import github.nitespring.darkestsouls.common.item.TrickWeapon;
import github.nitespring.darkestsouls.core.init.EffectInit;
import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

public class ChikageExtended extends TrickWeapon {
    public ChikageExtended(Tier tier, float attack, float speed, float knockback, int poise, int blood, int poison, int frost, int rot, int death, int fire, int holy, int durability, int enchantability, float movementSpeed, int maxTargets, Properties properties) {
        super(tier, attack, speed, knockback, poise, blood, poison, frost, rot, death, fire, holy, durability, enchantability, movementSpeed, maxTargets, properties);
    }

    @Override
    public Item getTransformedWeapon() {
        return ItemInit.CHIKAGE.get();
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int i, boolean b) {

        if (entity instanceof LivingEntity living && !living.hasEffect(EffectInit.CHIKAGE.get())) {
            if(stack==living.getItemInHand(InteractionHand.MAIN_HAND)||stack==living.getItemInHand(InteractionHand.OFF_HAND))
                if(!level.isClientSide()) {
                    living.addEffect(new MobEffectInstance(EffectInit.CHIKAGE.get(), 40));
            }

        }

    }


}
