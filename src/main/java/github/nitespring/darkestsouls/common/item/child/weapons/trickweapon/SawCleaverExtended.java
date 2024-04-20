package github.nitespring.darkestsouls.common.item.child.weapons.trickweapon;

import github.nitespring.darkestsouls.common.entity.projectile.weapon.melee.WeaponAttackEntity;
import github.nitespring.darkestsouls.common.item.TrickWeapon;
import github.nitespring.darkestsouls.config.CommonConfig;
import github.nitespring.darkestsouls.core.init.EntityInit;
import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SawCleaverExtended extends TrickWeapon {
    public SawCleaverExtended(Tier tier, float attack, float speed, float knockback, int poise, int blood, int poison, int frost, int rot, int death, int fire, int holy, int durability, int enchantability, float movementSpeed, int maxTargets, Properties properties) {
        super(tier, attack, speed, knockback, poise, blood, poison, frost, rot, death, fire, holy, durability, enchantability, movementSpeed, maxTargets, properties);
    }

    @Override
    public Item getTransformedWeapon() {
        return ItemInit.SAW_CLEAVER.get();
    }


    @Override
    public void doLeftClickAction(Player playerIn, ItemStack stackIn) {
        if(CommonConfig.do_special_attacks.get()) {
            Vec3 pos = playerIn.position().add(playerIn.getLookAngle().x() * 1.5, 0.4, playerIn.getLookAngle().z() * 1.5);

            Level levelIn = playerIn.level();
            WeaponAttackEntity entity = new WeaponAttackEntity(EntityInit.SAW_CLEAVER_EXTENDED.get(), levelIn, pos, (float) Mth.atan2(pos.z - playerIn.getZ(), pos.x - playerIn.getX()));
            entity.setOwner(playerIn);
            entity.setItemStack(stackIn);
            entity.setMaxTargets(this.getMaxTargets(stackIn));
            entity.setDamage(
                    this.getAttackDamage(playerIn, stackIn),
                    this.getPoiseDamage(playerIn, stackIn),
                    this.getFireAttack(stackIn),
                    this.getSmiteAttack(stackIn),
                    this.getBaneOfArthropodsAttack(stackIn),
                    this.getBloodAttack(stackIn),
                    this.getPoisonAttack(stackIn),
                    this.getRotAttack(stackIn),
                    this.getFrostAttack(stackIn),
                    this.getDeathAttack(stackIn));
            entity.setHitboxModifications(1.2f, 0f, 0.4f, 1.5f);
            entity.configureTicks(6, 12, 2, 3);
            levelIn.addFreshEntity(entity);
        }
    }
}
