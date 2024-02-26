package github.nitespring.darkestsouls.common.entity.projectile.spell;

import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Random;

public class CrystalRain extends CrystalBallEntity implements GeoEntity {
    public int stopLifeTime=20;
    public int activeTicks=0;

    protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);

    public CrystalRain(EntityType<? extends AbstractHurtingProjectile> e, Level level) {
        super(e, level);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {return this.factory;}

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<>(this, "main_controller", 0, this::predicate));
    }

    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
        event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.crystal_ball.new"));
        return PlayState.CONTINUE;
    }
    public void setStopLifeTime(int i){
        this.stopLifeTime=i;
    }

    @Override
    public void tick() {
        super.tick();

        if(this.lifeTicks<=this.stopLifeTime) {
            Vec3 mov = this.getDeltaMovement();
            if(mov.y-0.01*lifeTicks>=0) {
                this.setDeltaMovement(mov.x, Math.max(0, mov.y - 0.01 * lifeTicks), mov.z);
            }else{this.setDeltaMovement(0, 0, 0);}

        }else if(this.lifeTicks<=this.getMaxLifeTime()){
            this.setDeltaMovement(0,0,0);
            this.activeTicks++;
            if(this.activeTicks%3==0){
                Vec3 pos = this.position();
                for(int i = 0; i<=5; i++) {
                    CrystalShardEntity e = new CrystalShardEntity(EntityInit.CRYSTAL_SHARD.get(), this.level());
                    e.setDamage(this.damage);
                    e.setLifeTicks(120);
                    e.setNoGrav(false);
                    e.setCrystalType(this.getCrystalType());
                    Random r = new Random();
                    double angleVar=Math.PI*1/36;
                    double angle = (r.nextInt(72)+1)*angleVar;

                    double y = -r.nextFloat();

                    double x = (1+y)*Math.sin(angle);

                    double z = (1+y)*Math.cos(angle);


                    Vec3 aim = new Vec3(x,y,z);
                    double d0 = aim.horizontalDistance();
                    e.setYRot((float) (Mth.atan2(aim.x, aim.z) * (double) (180F / (float) Math.PI)));
                    e.setXRot((float) (Mth.atan2(aim.y, d0) * (double) (180F / (float) Math.PI)));
                    e.hurtMarked=true;

                    e.setPos(pos.add(aim.multiply(1.25f, 0.75f, 1.25f)));
                    e.xPower = 0.05 * aim.x;
                    e.yPower = 0.05 * aim.y;
                    e.zPower = 0.05 * aim.z;

                    e.setOwner(this.getOwner());
                    this.level().addFreshEntity(e);
                    this.level().playSound((Player) null, this, SoundEvents.GLASS_BREAK, SoundSource.PLAYERS, 2.0F, 1.0f);

                }
            }

        }else{this.discard();}

    }



}
