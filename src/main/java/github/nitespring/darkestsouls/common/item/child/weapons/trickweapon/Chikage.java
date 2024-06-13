package github.nitespring.darkestsouls.common.item.child.weapons.trickweapon;

import github.nitespring.darkestsouls.common.entity.projectile.weapon.melee.WeaponAttackEntity;
import github.nitespring.darkestsouls.common.item.TrickWeapon;
import github.nitespring.darkestsouls.config.CommonConfig;
import github.nitespring.darkestsouls.core.init.EntityInit;
import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class Chikage extends TrickWeapon {
    public Chikage(Tier tier, float attack, float speed, float reach, float knockback, int poise, int blood, int poison, int frost, int rot, int death, int fire, int holy,int serrated, int durability, int enchantability, float movementSpeed, int maxTargets, Properties properties) {
        super(tier, attack, speed, reach, knockback, poise, blood, poison, frost, rot, death, fire, holy, serrated, durability, enchantability, movementSpeed, maxTargets, properties);
    }

    @Override
    public Item getTransformedWeapon() {
        return ItemInit.CHIKAGE_EXTENDED.get();
    }

    @Override
    public void doLeftClickAction(Player playerIn, ItemStack stackIn) {
        if(CommonConfig.do_special_attacks.get()) {
            Vec3 pos = playerIn.position().add(playerIn.getLookAngle().x() * 1.5, 0.4, playerIn.getLookAngle().z() * 1.5);

            Level levelIn = playerIn.level();
            WeaponAttackEntity entity = new WeaponAttackEntity(EntityInit.UCHIGATANA.get(), levelIn, pos, (float) Mth.atan2(pos.z - playerIn.getZ(), pos.x - playerIn.getX()));
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
            entity.setHitboxModifications(1.2f, 0f, 0.4f, 1.5f);
            entity.configureTicks(6, 12, 2, 3);
            levelIn.addFreshEntity(entity);
        }

    }
    @Override
    public void doRightClickAction(Player playerIn, ItemStack item) {


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
