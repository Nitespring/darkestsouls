package github.nitespring.darkestsouls.common.entity.mob.abyss;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class MonstruosityOfSin extends DarkestSoulsAbstractEntity implements GeoEntity{

	protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
	protected int animationTick = 0;
	
	public MonstruosityOfSin(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
		super(p_21683_, p_21684_);
	}

	@Override
	public boolean isBoss() {return false;}
	@Override
	public int getDSDefaultTeam() {return 0;}
	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {return this.factory;}
	
	@Override
	public void registerControllers(ControllerRegistrar data) {
		data.add(new AnimationController<>(this, "main_controller", 8, this::predicate));
		data.add(new AnimationController<>(this, "fingers_controller", 8, this::fingersPredicate));
		data.add(new AnimationController<>(this, "stun_controller", 2, this::hitStunPredicate));
		}

	private <E extends GeoAnimatable> PlayState hitStunPredicate(AnimationState<E> event) {
		
		if(hitStunTicks>0) {
		event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.sin.hit"));
		}else {
		event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.sin.new"));	
		}
		return PlayState.CONTINUE;
	}
	
	private <E extends GeoAnimatable> PlayState fingersPredicate(AnimationState<E> event) {
		int animState = this.getAnimationState();
		if(this.isDeadOrDying()) {
			event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.sin.fingers_death"));
		}else {
			switch(animState) {
			case 21:
				event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.sin.fingers_punch"));
				break;
			default:
				 
				event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.sin.fingers_idle"));	 
					 
				break;
			}
		}
		
        return PlayState.CONTINUE;
	}
	
	private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
		int animState = this.getAnimationState();
		int combatState = this.getCombatState();
		if(this.isDeadOrDying()) {
			event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.sin.death"));
		}else {
			switch(animState) {
			case 11:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.sin.crawl"));
				break;
			case 12:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.sin.sit"));
				break;
			case 21:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.sin.attack_punch"));
				break;
			case 22:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.sin.attack_punch_getup"));
				break;
			case 23:
				event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.sin.attack_slam"));
				break;
			default:
				switch(combatState) {
				case 1:
				 if(!(event.getLimbSwingAmount() > -0.06F && event.getLimbSwingAmount() < 0.06F)){
					event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.sin.walk"));	 
					}else {
					event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.sin.idle2"));
					}
				default: 
					event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.sin.idle1"));
				}
				break;
				}
			}
        return PlayState.CONTINUE;
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, LivingEntity.class, 1.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
	}
}
