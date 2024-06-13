package github.nitespring.darkestsouls.common.item.child.weapons;

import github.nitespring.darkestsouls.common.entity.projectile.spell.LightningSpear;
import github.nitespring.darkestsouls.common.entity.projectile.weapon.melee.WeaponAttackEntity;
import github.nitespring.darkestsouls.common.item.Weapon;
import github.nitespring.darkestsouls.config.CommonConfig;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class DragonslayerSpear extends Weapon {


    public DragonslayerSpear(Tier tier, float attack, float speed, float reach, float knockback, int poise, int blood, int poison, int frost, int rot, int death, int fire, int holy,int serrated, int durability, int enchantability, float movementSpeed, int maxTargets, Properties properties) {
        super(tier, attack, speed, reach, knockback, poise, blood, poison, frost, rot, death, fire, holy, serrated, durability, enchantability, movementSpeed, maxTargets, properties);
    }



    @Override
    public void doLeftClickAction(Player playerIn, ItemStack stackIn) {



        if(playerIn.isUsingItem()&&playerIn.getUseItem().getItem() instanceof ShieldItem && !playerIn.getCooldowns().isOnCooldown(stackIn.getItem())){
            if(CommonConfig.do_special_attacks.get()) {
                Vec3 pos = playerIn.position().add(playerIn.getLookAngle().x() * 2.0, 0.4, playerIn.getLookAngle().z() * 2.0);

                Level levelIn = playerIn.level();
                WeaponAttackEntity entity = new WeaponAttackEntity(EntityInit.DRAGONSLAYER_SPEAR.get(), levelIn, pos, (float) Mth.atan2(pos.z - playerIn.getZ(), pos.x - playerIn.getX()));
                entity.setOwner(playerIn);
                entity.setItemStack(stackIn);
                entity.setMaxTargets(this.getMaxTargets(stackIn));
                entity.setDamage(
                        (this.getAttackDamage(playerIn, stackIn)) - 2.0f,
                        this.getPoiseDamage(playerIn, stackIn),
                        this.getFireAttack(stackIn),
                        this.getSmiteAttack(stackIn),
                        this.getBaneOfArthropodsAttack(stackIn),
                        this.getBeastHunterAttack(stackIn),
                        this.getBloodAttack(stackIn),
                        this.getPoisonAttack(stackIn),
                        this.getToxicAttack(stackIn),
                        this.getRotAttack(stackIn),
                        this.getFrostAttack(stackIn),
                        this.getWitherAttack(stackIn));
                entity.setHitboxModifications(1.2f, 0f, 0.4f, 2.0f);
                entity.configureTicks(4, 10, 1, 2);
                levelIn.addFreshEntity(entity);
                playerIn.getCooldowns().addCooldown(stackIn.getItem(), 16);
                playerIn.swing(InteractionHand.MAIN_HAND);
            }

        } else  if(!playerIn.isUsingItem() /*&& !playerIn.getCooldowns().isOnCooldown(stackIn.getItem())*/){
            Vec3 pos = playerIn.position().add(playerIn.getLookAngle().x() * 2.1, 0.4, playerIn.getLookAngle().z() * 2.1);
            Vec3 aim = playerIn.getLookAngle();

            Level levelIn = playerIn.level();
            WeaponAttackEntity entity = new WeaponAttackEntity(EntityInit.DRAGONSLAYER_SPEAR.get(), levelIn, pos, (float) Mth.atan2(pos.z - playerIn.getZ(), pos.x - playerIn.getX()));
            entity.setOwner(playerIn);
            entity.setItemStack(stackIn);
            entity.setMaxTargets(this.getMaxTargets(stackIn));
            entity.setDamage(
                    this.getAttackDamage(playerIn, stackIn),
                    this.getPoiseDamage(playerIn, stackIn),
                    this.getFireAttack(stackIn),
                    this.getSmiteAttack(stackIn),
                    this.getBaneOfArthropodsAttack(stackIn),
                    this.getBeastHunterAttack(stackIn),
                    this.getBloodAttack(stackIn),
                    this.getPoisonAttack(stackIn),
                    this.getToxicAttack(stackIn),
                    this.getRotAttack(stackIn),
                    this.getFrostAttack(stackIn),
                    this.getWitherAttack(stackIn));
            entity.setHitboxModifications(1.2f, 0f, 0.4f, 2.1f);
            entity.configureTicks(4, 10, 1, 2);
            //levelIn.addFreshEntity(entity);

            LightningSpear e = new LightningSpear(EntityInit.LIGHTNING_SPEAR.get(), levelIn, (float)Mth.atan2(aim.x, aim.z));
            e.setDamage(this.getAttackDamage(playerIn, stackIn));
            e.setDimensionScale(1.25f);
            e.setMaxLifeTime(16);
            e.setPos(pos.add(0, 0.75, 0).add(aim.normalize().multiply(0.75f, 0.75f, 0.75f)));
            e.xPower = 0.35 * aim.x * (1 + (playerIn.getRandom().nextFloat() - 0.5) * 0.05);
            e.yPower = 0.35 * aim.y;
            e.zPower = 0.35 * aim.z * (1 + (playerIn.getRandom().nextFloat() - 0.5) * 0.05);
            if(stackIn == playerIn.getItemInHand(InteractionHand.MAIN_HAND)) {stackIn.hurtAndBreak(1, playerIn, EquipmentSlot.MAINHAND);}
            if(stackIn == playerIn.getItemInHand(InteractionHand.OFF_HAND)) {stackIn.hurtAndBreak(1, playerIn, EquipmentSlot.OFFHAND);}
            levelIn.addFreshEntity(e);
            playerIn.getCooldowns().addCooldown(this, 5);

            playerIn.level().playSound((Player)null, playerIn, SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.PLAYERS, 0.6F, 24.2F);

        }
    }
    @Override
    public void doRightClickAction(Player playerIn, ItemStack stackIn) {
        playerIn.push(playerIn.getLookAngle().x*1.5f,Math.max(playerIn.getLookAngle().y*1.25f,0)+0.25,playerIn.getLookAngle().z*1.5f);
        playerIn.awardStat(Stats.ITEM_USED.get(this));
        playerIn.startAutoSpinAttack(8);
        playerIn.level().playSound((Player)null, playerIn, SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.PLAYERS, 0.6F, 1.8F);
        playerIn.getCooldowns().addCooldown(stackIn.getItem(), 32);
        Vec3 pos = playerIn.position().add(playerIn.getLookAngle().x() * 2.0, 0.4, playerIn.getLookAngle().z() * 2.0);
        if (playerIn.onGround()) {
            float f6 = 1.1999999F;
            playerIn.move(MoverType.SELF, new Vec3(0.0D, (double)1.1999999F, 0.0D));
        }

        Level levelIn = playerIn.level();
        WeaponAttackEntity entity = new WeaponAttackEntity(EntityInit.DRAGONSLAYER_SPEAR.get(), levelIn, pos, (float) Mth.atan2(pos.z - playerIn.getZ(), pos.x - playerIn.getX()));
        entity.setOwner(playerIn);
        entity.setItemStack(stackIn);
        entity.setMaxTargets(this.getMaxTargets(stackIn));
        entity.setDamage(
                this.getAttackDamage(playerIn, stackIn),
                this.getPoiseDamage(playerIn, stackIn),
                this.getFireAttack(stackIn),
                this.getSmiteAttack(stackIn),
                this.getBaneOfArthropodsAttack(stackIn),
                this.getBeastHunterAttack(stackIn),
                this.getBloodAttack(stackIn),
                this.getPoisonAttack(stackIn),
                this.getToxicAttack(stackIn),
                this.getRotAttack(stackIn),
                this.getFrostAttack(stackIn),
                this.getWitherAttack(stackIn));
        entity.setHitboxModifications(1.2f, 0f, 0.4f, 2.0f);
        entity.configureTicks(4, 10, 1, 2);
        levelIn.addFreshEntity(entity);
    }

}
