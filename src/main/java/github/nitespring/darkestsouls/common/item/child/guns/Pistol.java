package github.nitespring.darkestsouls.common.item.child.guns;

import github.nitespring.darkestsouls.common.entity.projectile.Bullet;
import github.nitespring.darkestsouls.common.entity.projectile.throwable.FirebombEntity;
import github.nitespring.darkestsouls.common.item.Gun;
import github.nitespring.darkestsouls.common.item.ILeftClickItem;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class Pistol extends Gun{


    public Pistol(float damage, int cooldown, int poise, float size, float flyingPower, int flyingTime, int ricochet, int pierce, int ammoAmount, int durability, int enchantability, Properties properties) {
        super(damage, cooldown, poise, size, flyingPower, flyingTime, ricochet, pierce, ammoAmount, durability, enchantability, properties);
    }


    @Override
    public void shoot(Player player, Level level, ItemStack stackIn) {
        Vec3 pos = player.position();
        Vec3 aim = player.getLookAngle();


        float x = (float) (pos.x + 0.6 * aim.x);
        float y = (float) (pos.y + 1.5 + 0.6 * aim.y);
        float z = (float) (pos.z + 0.6 * aim.z);

        Bullet entity = new Bullet(EntityInit.BULLET.get(), level);
        entity.setPos(x, y, z);
        float flyingPower = this.flyingPower(player, stackIn);
        //float flyingPower = 0.5f;
        entity.xPower = flyingPower * aim.x;
        entity.yPower = flyingPower * aim.y;
        entity.zPower = flyingPower * aim.z;
        entity.setOwner(player);
        entity.setAttackDamage(this.getAttackDamage(player, stackIn));
        entity.setPoiseDamage(this.getPoiseDamage(player, stackIn));
        entity.setFlyingTime(this.getFlyingTime(stackIn));
        entity.setBlood(this.getBlood(player,stackIn));
        entity.setPoison(this.getPoison(player,stackIn));
        entity.setFire(this.isFire(player,stackIn));
        entity.setExplosion(this.getExplosion(stackIn));
        entity.setThunder(this.isLightning(player,stackIn));
        entity.setSize(this.getBaseSize());
        entity.setPierce(this.getPierce(player, stackIn));
        entity.setRicochet(this.getRicochet(player, stackIn));
        level.addFreshEntity(entity);

        player.getCooldowns().addCooldown(stackIn.getItem(), this.getUseCooldown(player, stackIn));

        player.level().playSound((Player) null, player, SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1.0F, 1.0f);
    }


}
