package github.nitespring.darkestsouls.core.util;

import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;

public class EntityAttributes {

	public static  AttributeSupplier.Builder setHollowSoldierLongswordAttributes(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 24.0D)
				.add(Attributes.ARMOR, 2.0D)
				.add(Attributes.ARMOR_TOUGHNESS, 0.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.21D)
				.add(Attributes.ATTACK_DAMAGE, 2.0D)
				.add(Attributes.ATTACK_SPEED, 1.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.1D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.4D)
				.add(Attributes.FOLLOW_RANGE, 20);
	}
	public static  AttributeSupplier.Builder setMonstruosityOfSinAttributes(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 80.0D)
				.add(Attributes.ARMOR, 8.0D)
				.add(Attributes.ARMOR_TOUGHNESS, 4.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.20D)
				.add(Attributes.ATTACK_DAMAGE, 10.0D)
				.add(Attributes.ATTACK_SPEED, 1.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.4D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 3.0D)
				.add(Attributes.FOLLOW_RANGE, 15);
	  }
	public static  AttributeSupplier.Builder setBonewheelAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 36.0D)
				.add(Attributes.ARMOR, 8.0D)
				.add(Attributes.ARMOR_TOUGHNESS, 2.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.21D)
				.add(Attributes.ATTACK_DAMAGE, 4.0D)
				.add(Attributes.ATTACK_SPEED, 1.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.2D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
				.add(Attributes.FOLLOW_RANGE, 25);
	}
	public static  AttributeSupplier.Builder setSkeletonFalchionAttributes(){
			return Monster.createMonsterAttributes()
					.add(Attributes.MAX_HEALTH, 24.0D)
					.add(Attributes.ARMOR, 6.0D)
					.add(Attributes.ARMOR_TOUGHNESS, 2.0D)
					.add(Attributes.MOVEMENT_SPEED, 0.24D)
					.add(Attributes.ATTACK_DAMAGE, 4.0D)
					.add(Attributes.ATTACK_SPEED, 1.2D)
					.add(Attributes.ATTACK_KNOCKBACK, 0.2D)
					.add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
					.add(Attributes.FOLLOW_RANGE, 25);
	}
	public static  AttributeSupplier.Builder setSkeletonCurvedSwordsAttributes(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 26.0D)
				.add(Attributes.ARMOR, 6.0D)
				.add(Attributes.ARMOR_TOUGHNESS, 2.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.24D)
				.add(Attributes.ATTACK_DAMAGE, 6.0D)
				.add(Attributes.ATTACK_SPEED, 1.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.2D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
				.add(Attributes.FOLLOW_RANGE, 25);
	}
	public static  AttributeSupplier.Builder setSewerCentipedeAttributes(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 48.0D)
				.add(Attributes.ARMOR, 4.0D)
				.add(Attributes.ARMOR_TOUGHNESS, 1.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.24D)
				.add(Attributes.ATTACK_DAMAGE, 5.0D)
				.add(Attributes.ATTACK_SPEED, 1.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.4D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
				.add(Attributes.FOLLOW_RANGE, 25);

	}

}
