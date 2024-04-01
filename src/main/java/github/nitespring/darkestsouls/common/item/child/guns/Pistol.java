package github.nitespring.darkestsouls.common.item.child.guns;

import github.nitespring.darkestsouls.common.entity.projectile.Bullet;
import github.nitespring.darkestsouls.common.entity.projectile.throwable.FirebombEntity;
import github.nitespring.darkestsouls.common.item.Gun;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class Pistol extends Gun{


    public Pistol(float damage, int cooldown, int poise, float size, float flyingPower, int flyingTime, int ricochet, int pierce, Properties properties) {
        super(damage, cooldown, poise, size, flyingPower, flyingTime, ricochet, pierce, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stackIn = player.getItemInHand(hand);
        Vec3 pos = player.position();
        Vec3 aim = player.getLookAngle();


        float x = (float) (pos.x + 0.6 * aim.x);
        float y = (float) (pos.y + 1.5 + 0.6 * aim.y);
        float z = (float) (pos.z + 0.6 * aim.z);

        Bullet entity = new Bullet(EntityInit.BULLET.get(), level);
        entity.setPos(x,y,z);
        //float flyingPower = this.flyingPower(player, stackIn);
        float flyingPower = 0.5f;
        entity.xPower=flyingPower*aim.x;
        entity.yPower=flyingPower*aim.y;
        entity.zPower=flyingPower*aim.z;
        entity.setOwner(player);
        entity.setAttackDamage(this.getAttackDamage(player, stackIn));
        entity.setPoiseDamage(this.getPoiseDamage(player, stackIn));
        entity.setSize(this.getBaseSize());
        //entity.setPierce(this.getPierce(player, stackIn));
        entity.setPierce(2);
        //entity.setRicochet(this.getRicochet(player, stackIn));
        entity.setRicochet(10 );
        level.addFreshEntity(entity);

        player.getCooldowns().addCooldown(stackIn.getItem(), this.getUseCooldown(player, stackIn));

        //player.level().playSound((Player)null, player, SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1.0F, 1.0f);

        return super.use(level, player, hand);
    }
}
