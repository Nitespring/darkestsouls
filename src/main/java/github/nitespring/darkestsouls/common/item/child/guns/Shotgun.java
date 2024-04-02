package github.nitespring.darkestsouls.common.item.child.guns;

import github.nitespring.darkestsouls.common.entity.projectile.Bullet;
import github.nitespring.darkestsouls.common.item.Gun;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class Shotgun extends Gun{

    private final float horizontalSpread;
    private final float verticalSpread;

    public Shotgun(float damage, int cooldown, int poise, float size, float flyingPower, int flyingTime, int ricochet, int pierce, int ammoAmount, int durability, float horizontalSpread, float verticalSpread, Properties properties) {
        super(damage, cooldown, poise, size, flyingPower, flyingTime, ricochet, pierce, ammoAmount, durability, properties);
        this.horizontalSpread=horizontalSpread;
        this.verticalSpread=verticalSpread;
    }


    @Override
    public void shoot(Player player, Level level, ItemStack stackIn) {
        Vec3 pos = player.position();
        Vec3 aim = player.getLookAngle();
        double x0 = pos.x + aim.x * 0.6f;
        double y0 = pos.y + 1.4f + aim.y*0.6f;
        double z0 = pos.z + aim.z * 0.6f;

        for(int i = 0; i<=4; i++) {
                for(int k = -1; k<=1; k++) {
                    Random r = new Random();
                    float rF = 2*(r.nextFloat()-0.5f);
                    float rF1 = 2*(r.nextFloat()-0.5f);
                    float rF2 = 2*(r.nextFloat()-0.5f);
                    float rY = 2*(r.nextFloat()-0.5f);
                    float rD = 2*(r.nextFloat()-0.5f);
                    double a=  Math.PI/3;

                    double b = rF*a;
                    double by = verticalSpread*rY;
                    double d = horizontalSpread+by;
                    Vec3 aim1 = new Vec3(d*(aim.x*Math.cos(b)-aim.z*Math.sin(b))+1.5*d*by*rF1,by*k*aim.y,d*(aim.z*Math.cos(b)+aim.x*Math.sin(b))+1.5*d*by*rF2);
                    double xVar = aim1.x;
                    double yVar = aim1.y;
                    double zVar = aim1.z;
                    double x = x0+xVar;
                    double z = z0+zVar;
                    double y = y0+yVar;
                    Bullet entity = new Bullet(EntityInit.BULLET.get(), level);
                    entity.setPos(x, y, z);
                    float flyingPower = this.flyingPower(player,stackIn);
                    entity.xPower = flyingPower * (0.4f*aim.x+aim1.x);
                    entity.yPower = flyingPower * (0.4f*aim.y+aim1.y);
                    entity.zPower = flyingPower * (0.4f*aim.z+aim1.z);
                    entity.setOwner(player);
                    entity.setAttackDamage(this.getAttackDamage(player, stackIn));
                    entity.setPoiseDamage(this.getPoiseDamage(player, stackIn));
                    entity.setFlyingTime(this.getFlyingTime());
                    entity.setSize((float) (this.getBaseSize()*(1+0.8*rD)));
                    entity.setPierce(this.getPierce(player, stackIn));
                    entity.setRicochet(this.getRicochet(player, stackIn));
                    level.addFreshEntity(entity);
                }
        }

        player.getCooldowns().addCooldown(stackIn.getItem(), this.getUseCooldown(player, stackIn));

        player.level().playSound((Player) null, player, SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1.0F, 1.0f);
    }


}
