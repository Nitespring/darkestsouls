package github.nitespring.darkestsouls.common.item.child.weapons.trickweapon;

import github.nitespring.darkestsouls.common.entity.projectile.spell.LightningSpear;
import github.nitespring.darkestsouls.common.entity.projectile.spell.WindSlash;
import github.nitespring.darkestsouls.common.entity.projectile.weapon.MoonlightSlash;
import github.nitespring.darkestsouls.common.entity.projectile.weapon.melee.WeaponAttackEntity;
import github.nitespring.darkestsouls.common.item.TrickWeapon;
import github.nitespring.darkestsouls.config.CommonConfig;
import github.nitespring.darkestsouls.core.init.EntityInit;
import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class HolyMoonlightSwordLit extends TrickWeapon {
    public HolyMoonlightSwordLit(Tier tier, float attack, float speed, float reach, float knockback, int poise, int blood, int poison, int frost, int rot, int death, int fire, int holy,int serrated, int durability, int enchantability, float movementSpeed, int maxTargets, Properties properties) {
        super(tier, attack, speed, reach, knockback, poise, blood, poison, frost, rot, death, fire, holy, serrated, durability, enchantability, movementSpeed, maxTargets, properties);
    }

    @Override
    public Item getTransformedWeapon() {
        return ItemInit.HOLY_MOONLIGHT.get();
    }

    @Override
    public void doLeftClickAction(Player playerIn, ItemStack stackIn) {
        if(CommonConfig.do_special_weapon_attacks_left_click.get()) {
            if (!playerIn.isUsingItem()) {
                Vec3 pos = playerIn.position();
                Vec3 aim = playerIn.getLookAngle();
                Level levelIn = playerIn.level();
                double d0 = aim.horizontalDistance();
                MoonlightSlash e = new MoonlightSlash(EntityInit.MOONLIGHT_WAVE.get(), levelIn);//, (float) Mth.atan2(aim.x, aim.z), (float) Mth.atan2(aim.y, d0));
                e.setPos(pos.add(0, 0.75f, 0).add(aim.scale(0.75f)));
                e.setOwner(playerIn);
                e.setDamage(this.getAttackDamage(playerIn, stackIn));
                e.setMaxLifeTime(16);
                e.setDeltaMovement(0.2 *aim.x * (1 + (playerIn.getRandom().nextFloat() - 0.5) * 0.05),0.2 *aim.y,0.2 *aim.z * (1 + (playerIn.getRandom().nextFloat() - 0.5) * 0.05));
                e.accelerationPower=0.2f;
                /*e.xPower = 0.2 * aim.x * (1 + (playerIn.getRandom().nextFloat() - 0.5) * 0.05);
                e.yPower = 0.2 * aim.y;
                e.zPower = 0.2 * aim.z * (1 + (playerIn.getRandom().nextFloat() - 0.5) * 0.05);*/
                if(stackIn == playerIn.getItemInHand(InteractionHand.MAIN_HAND)) {stackIn.hurtAndBreak(1, playerIn, EquipmentSlot.MAINHAND);}
                if(stackIn == playerIn.getItemInHand(InteractionHand.OFF_HAND)) {stackIn.hurtAndBreak(1, playerIn, EquipmentSlot.OFFHAND);}

                levelIn.addFreshEntity(e);
                playerIn.level().playSound((Player) null, playerIn, SoundEvents.BELL_RESONATE, SoundSource.PLAYERS, 0.6F, 0.2F);
            }
        }
        /*if(CommonConfig.do_special_attacks.get()) {
            if (!playerIn.isUsingItem()) {
                Vec3 pos = playerIn.position().add(playerIn.getLookAngle().x() * 1.75, 0.4, playerIn.getLookAngle().z() * 1.75);

                Level levelIn = playerIn.level();
                WeaponAttackEntity entity = new WeaponAttackEntity(EntityInit.CHURCH_SCYTHE.get(), levelIn, pos, (float) Mth.atan2(pos.z - playerIn.getZ(), pos.x - playerIn.getX()));
                entity.setOwner(playerIn);
                entity.setItemStack(stackIn);
                entity.setMaxTargets(this.getMaxTargets(playerIn, stackIn));
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
                entity.setHitboxModifications(1.2f, 0f, 0.4f, 1.75f);
                entity.configureTicks(6, 12, 2, 3);
                levelIn.addFreshEntity(entity);
            }
        }*/
    }

    public boolean isFoil(ItemStack p_41172_) {
        return true;
    }

    @Override
    public void playTrickSound(Level worldIn, Vec3 pos) {
        float r = worldIn.getRandom().nextFloat();
        worldIn.playSound((Player)null, pos.x, pos.y, pos.z, getEquipSound(), SoundSource.PLAYERS, 0.6f, 0.2f+0.4f*r);

    }
    @Override
     public SoundEvent getEquipSound() {
        return SoundEvents.PLAYER_SPLASH_HIGH_SPEED;
    }

}
