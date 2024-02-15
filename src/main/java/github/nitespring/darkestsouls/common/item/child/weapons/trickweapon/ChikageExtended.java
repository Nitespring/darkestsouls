package github.nitespring.darkestsouls.common.item.child.weapons.trickweapon;

import github.nitespring.darkestsouls.common.entity.projectile.weapon.WeaponAttackEntity;
import github.nitespring.darkestsouls.common.item.TrickWeapon;
import github.nitespring.darkestsouls.core.init.EffectInit;
import github.nitespring.darkestsouls.core.init.EntityInit;
import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

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

    @Override
    public void doLeftClickAction(Player playerIn, ItemStack stackIn) {

        Vec3 pos = playerIn.position().add(playerIn.getLookAngle().x()*1.5, 0.4, playerIn.getLookAngle().z()*1.5);

        Level levelIn = playerIn.level();
        WeaponAttackEntity entity = new WeaponAttackEntity(EntityInit.CHIKAGE.get(), levelIn, pos,(float) Mth.atan2(pos.z - playerIn.getZ(), pos.x - playerIn.getX()));
        entity.setOwner(playerIn);
        entity.setItemStack(stackIn);
        entity.setMaxTargets(this.getMaxTargets(stackIn));
        entity.setDamage(
                this.getAttackDamage(playerIn,stackIn)/2,
                this.getPoiseDamage(playerIn,stackIn),
                this.getFireAttack(stackIn),
                this.getSmiteAttack(stackIn),
                this.getBaneOfArthropodsAttack(stackIn),
                this.getBloodAttack(stackIn),
                this.getPoisonAttack(stackIn),
                this.getRotAttack(stackIn),
                this.getFrostAttack(stackIn),
                this.getDeathAttack(stackIn));
        entity.setHitboxModifications(1.2f,0f, 0.4f, 1.5f);
        entity.configureTicks(6,12,2,3);
        levelIn.addFreshEntity(entity);

    }
    @Override
    public void doRightClickAction(Player playerIn, ItemStack item) {


    }


}
