package github.nitespring.darkestsouls.common.item.child.weapons;

import github.nitespring.darkestsouls.common.entity.projectile.spell.WindSlash;
import github.nitespring.darkestsouls.common.item.Weapon;
import github.nitespring.darkestsouls.config.CommonConfig;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class StormCurvedSword extends Weapon {


    public StormCurvedSword(Tier tier, float attack, float speed, float reach, float knockback, int poise, int blood, int poison, int frost, int rot, int death, int fire, int holy,int serrated, int durability, int enchantability, float movementSpeed, int maxTargets, Properties properties) {
        super(tier, attack, speed, reach, knockback, poise, blood, poison, frost, rot, death, fire, holy, serrated, durability, enchantability, movementSpeed, maxTargets, properties);
    }

    @Override
    public void doLeftClickAction(Player playerIn, ItemStack stackIn) {
        if(CommonConfig.do_special_attacks.get()) {
            if (!playerIn.isUsingItem()) {
                Vec3 pos = playerIn.position();
                Vec3 aim = playerIn.getLookAngle();
                Level levelIn = playerIn.level();
                double d0 = aim.horizontalDistance();
                WindSlash e = new WindSlash(EntityInit.WIND_SLASH.get(), levelIn, (float) Mth.atan2(aim.x, aim.z), (float) Mth.atan2(aim.y, d0));
                e.setPos(pos.add(0, 0.75f, 0).add(aim.scale(0.75f)));
                e.setOwner(playerIn);
                e.setDamage(this.getAttackDamage(playerIn, stackIn));
                e.setDeltaMovement(0.2 *aim.x * (1 + (playerIn.getRandom().nextFloat() - 0.5) * 0.05),0.2 *aim.y,0.2 *aim.z * (1 + (playerIn.getRandom().nextFloat() - 0.5) * 0.05));
                e.setMaxLifeTime(16);
                e.accelerationPower=0.2f;
                /*e.xPower = 0.2 * aim.x * (1 + (playerIn.getRandom().nextFloat() - 0.5) * 0.05);
                e.yPower = 0.2 * aim.y;
                e.zPower = 0.2 * aim.z * (1 + (playerIn.getRandom().nextFloat() - 0.5) * 0.05);*/
                if(stackIn == playerIn.getItemInHand(InteractionHand.MAIN_HAND)) {stackIn.hurtAndBreak(1, playerIn, EquipmentSlot.MAINHAND);}
                if(stackIn == playerIn.getItemInHand(InteractionHand.OFF_HAND)) {stackIn.hurtAndBreak(1, playerIn, EquipmentSlot.OFFHAND);}

                levelIn.addFreshEntity(e);
                playerIn.level().playSound((Player) null, playerIn, SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 0.6F, 0.8F);
            }
        }
    }
    @Override
    public void doRightClickAction(Player playerIn, ItemStack item) {


    }

}
