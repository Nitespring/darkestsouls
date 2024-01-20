package github.nitespring.darkestsouls.core.util;

import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;

public class EntityAttributes {
	
	
	public static  AttributeSupplier.Builder setMonstruosityOfSinAttributes(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 70.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.20D)
				.add(Attributes.ATTACK_DAMAGE, 10.0D)
				.add(Attributes.ATTACK_SPEED, 1.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.1D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 3.0D)
				.add(Attributes.FOLLOW_RANGE, 25);

	  }

}
