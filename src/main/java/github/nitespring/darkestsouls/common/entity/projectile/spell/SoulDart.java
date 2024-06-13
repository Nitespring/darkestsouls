package github.nitespring.darkestsouls.common.entity.projectile.spell;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class SoulDart extends SoulDartEntity implements GeoEntity {

    protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);

    public SoulDart(EntityType<? extends AbstractHurtingProjectile> e, Level level) {super(e, level);}

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {return this.factory;}

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<>(this, "main_controller", 4, this::predicate));
    }

    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
        event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.soul_dart.new"));
        return PlayState.CONTINUE;
    }

    @Override
    protected void onHitEntity(EntityHitResult p_37259_) {
        super.onHitEntity(p_37259_);
        doDiscard();
    }
}
