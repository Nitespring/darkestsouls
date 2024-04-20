package github.nitespring.darkestsouls.common.entity.projectile.weapon.melee;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class FrayedBladeAttackEntity extends WeaponAttackEntity{


	public FrayedBladeAttackEntity(EntityType<?> e, Level level, Vec3 pos, float rotation) {
		super(e, level, pos, rotation);
	}

	public FrayedBladeAttackEntity(EntityType<?> e, Level level) {
		super(e, level);
	}

	@Override
	public void tickAttack() {
		if(lifeTicks%2==0&&lifeTicks<=14){
			super.attackEntity();
		}

		if (this.lifeTicks >= 22) {
			this.discard();
		}
	}
	@Override
	public void tickAnimation(){
		//if(lifeTicks%updateTickAnimation==0) {
			this.setAnimationState(this.getAnimationState()+1);
		//}
	}

}
