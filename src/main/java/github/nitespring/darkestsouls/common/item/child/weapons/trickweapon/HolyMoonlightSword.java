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

public class HolyMoonlightSword extends TrickWeapon {
    public HolyMoonlightSword(Tier tier, float attack, float speed, float reach, float knockback, int poise, int blood, int poison, int frost, int rot, int death, int fire, int holy,int serrated, int durability, int enchantability, float movementSpeed, int maxTargets, Properties properties) {
        super(tier, attack, speed, reach, knockback, poise, blood, poison, frost, rot, death, fire, holy, serrated, durability, enchantability, movementSpeed, maxTargets, properties);
    }

    @Override
    public Item getTransformedWeapon() {
        return ItemInit.HOLY_MOONLIGHT_LIT.get();
    }

    @Override
    public void doLeftClickAction(Player playerIn, ItemStack stackIn) {
        if(CommonConfig.do_special_attacks.get()) {
            if (!playerIn.isUsingItem()) {
                Vec3 pos = playerIn.position().add(playerIn.getLookAngle().x() * 1.75, 0.4, playerIn.getLookAngle().z() * 1.75);

                Level levelIn = playerIn.level();
                WeaponAttackEntity entity = new WeaponAttackEntity(EntityInit.GREATSWORD.get(), levelIn, pos, (float) Mth.atan2(pos.z - playerIn.getZ(), pos.x - playerIn.getX()));
                entity.setOwner(playerIn);
                entity.setItemStack(stackIn);
                entity.setMaxTargets(this.getMaxTargets(playerIn, stackIn));
                entity.setDamage(
                        this.getAttackDamage(playerIn, stackIn),
                        this.getPoiseDamage(playerIn, stackIn),
                        this.getFireAttack(playerIn,stackIn),
                        this.getSmiteAttack(playerIn,stackIn),
                        this.getBaneOfArthropodsAttack(playerIn,stackIn),
                        this.getBeastHunterAttack(playerIn,stackIn),
                        this.getBloodAttack(playerIn,stackIn),
                        this.getPoisonAttack(playerIn,stackIn),
                        this.getToxicAttack(playerIn,stackIn),
                        this.getRotAttack(playerIn,stackIn),
                        this.getFrostAttack(playerIn,stackIn),
                        this.getWitherAttack(playerIn,stackIn));
                entity.setHitboxModifications(1.2f, 0f, 0.4f, 1.75f);
                entity.configureTicks(6, 12, 2, 3);
                levelIn.addFreshEntity(entity);
            }
        }
    }
    @Override
    public void playTrickSound(Level worldIn, Vec3 pos) {
        float r = worldIn.getRandom().nextFloat();
        worldIn.playSound((Player)null, pos.x, pos.y, pos.z, getEquipSound(), SoundSource.PLAYERS, 0.6f, 0.2f+0.4f*r);
        float r1 = worldIn.getRandom().nextFloat();
        worldIn.playSound((Player)null, pos.x, pos.y, pos.z, SoundEvents.BELL_RESONATE, SoundSource.PLAYERS, 0.6f, 0.2f+0.4f*r1);

    }
    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.PLAYER_SPLASH_HIGH_SPEED;
    }
}
