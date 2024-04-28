package github.nitespring.darkestsouls.core.util;

import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraftforge.common.ForgeMod;

public class EntityAttributes {

	public static  AttributeSupplier.Builder setHollowSoldierLongswordAttributes(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 32.0D)
				.add(Attributes.ARMOR, 3.0D)
				.add(Attributes.ARMOR_TOUGHNESS, 1.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.18D)
				.add(Attributes.ATTACK_DAMAGE, 5.0D)
				.add(Attributes.ATTACK_SPEED, 1.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.1D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
				.add(Attributes.FOLLOW_RANGE, 20);
	}
	public static  AttributeSupplier.Builder setHollowSoldierAxeAttributes(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 36.0D)
				.add(Attributes.ARMOR, 4.0D)
				.add(Attributes.ARMOR_TOUGHNESS, 1.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.18D)
				.add(Attributes.ATTACK_DAMAGE, 6.0D)
				.add(Attributes.ATTACK_SPEED, 1.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.25D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
				.add(Attributes.FOLLOW_RANGE, 20);
	}
	public static  AttributeSupplier.Builder setHollowAssassinAttributes(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 26.0D)
				.add(Attributes.ARMOR, 1.0D)
				.add(Attributes.ARMOR_TOUGHNESS, 1.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.18D)
				.add(Attributes.ATTACK_DAMAGE, 2.5D)
				.add(Attributes.ATTACK_SPEED, 1.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.1D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.8D)
				.add(Attributes.FOLLOW_RANGE, 22);
	}
	public static  AttributeSupplier.Builder setMadHollowBrokenStraightswordAttributes(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 22.0D)
				.add(Attributes.ARMOR, 0.0D)
				.add(Attributes.ARMOR_TOUGHNESS, 0.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.18D)
				.add(Attributes.ATTACK_DAMAGE, 2.0D)
				.add(Attributes.ATTACK_SPEED, 1.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.0D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.4D)
				.add(Attributes.FOLLOW_RANGE, 8);
	}
	public static  AttributeSupplier.Builder setGravetenderHollowLongswordAttributes(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 28.0D)
				.add(Attributes.ARMOR, 1.0D)
				.add(Attributes.ARMOR_TOUGHNESS, 0.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.18D)
				.add(Attributes.ATTACK_DAMAGE, 4.5D)
				.add(Attributes.ATTACK_SPEED, 1.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.1D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.8D)
				.add(Attributes.FOLLOW_RANGE, 20);
	}
	public static  AttributeSupplier.Builder setGravetenderHollowBrokenStraightswordAttributes(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 26.0D)
				.add(Attributes.ARMOR, 3.5D)
				.add(Attributes.ARMOR_TOUGHNESS, 0.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.18D)
				.add(Attributes.ATTACK_DAMAGE, 3.0D)
				.add(Attributes.ATTACK_SPEED, 1.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.0D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.8D)
				.add(Attributes.FOLLOW_RANGE, 8);
	}
	public static  AttributeSupplier.Builder setMonstruosityOfSinAttributes(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 100.0D)
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
				.add(Attributes.MAX_HEALTH, 56.0D)
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
					.add(Attributes.MAX_HEALTH, 36.0D)
					.add(Attributes.ARMOR, 6.0D)
					.add(Attributes.ARMOR_TOUGHNESS, 2.0D)
					.add(Attributes.MOVEMENT_SPEED, 0.24D)
					.add(Attributes.ATTACK_DAMAGE, 4.0D)
					.add(Attributes.ATTACK_SPEED, 1.2D)
					.add(Attributes.ATTACK_KNOCKBACK, 0.2D)
					.add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
					.add(Attributes.FOLLOW_RANGE, 25);
	}
	public static  AttributeSupplier.Builder setSkeletonCurvedSwordsAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 38.0D)
				.add(Attributes.ARMOR, 6.0D)
				.add(Attributes.ARMOR_TOUGHNESS, 2.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.24D)
				.add(Attributes.ATTACK_DAMAGE, 6.0D)
				.add(Attributes.ATTACK_SPEED, 1.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.2D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
				.add(Attributes.FOLLOW_RANGE, 25);
	}
		public static  AttributeSupplier.Builder setSkeletonSpearAttributes(){
			return Monster.createMonsterAttributes()
					.add(Attributes.MAX_HEALTH, 34.0D)
					.add(Attributes.ARMOR, 4.0D)
					.add(Attributes.ARMOR_TOUGHNESS, 1.0D)
					.add(Attributes.MOVEMENT_SPEED, 0.24D)
					.add(Attributes.ATTACK_DAMAGE, 3.0D)
					.add(Attributes.ATTACK_SPEED, 1.2D)
					.add(Attributes.ATTACK_KNOCKBACK, 0.2D)
					.add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
					.add(Attributes.FOLLOW_RANGE, 25);
	}
	public static  AttributeSupplier.Builder setSkeletonSwordsmanTwinShotelsAttributes(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 54.0D)
				.add(Attributes.ARMOR, 6.0D)
				.add(Attributes.ARMOR_TOUGHNESS, 2.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.21D)
				.add(Attributes.ATTACK_DAMAGE, 4.0D)
				.add(Attributes.ATTACK_SPEED, 1.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.3D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1.4D)
				.add(Attributes.FOLLOW_RANGE, 25);
	}
	public static  AttributeSupplier.Builder setSewerCentipedeAttributes(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 58.0D)
				.add(Attributes.ARMOR, 4.0D)
				.add(Attributes.ARMOR_TOUGHNESS, 1.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.24D)
				.add(Attributes.ATTACK_DAMAGE, 5.0D)
				.add(Attributes.ATTACK_SPEED, 1.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.4D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
				.add(Attributes.FOLLOW_RANGE, 25);

	}
	public static  AttributeSupplier.Builder setBeastPatientAttributes(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 38.0D)
				.add(Attributes.ARMOR, 5.0D)
				.add(Attributes.ARMOR_TOUGHNESS, 1.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.22D)
				.add(Attributes.ATTACK_DAMAGE, 6.0D)
				.add(Attributes.ATTACK_SPEED, 1.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.4D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1.2D)
				.add(Attributes.FOLLOW_RANGE, 30);
	}
	public static  AttributeSupplier.Builder setCloakedBeastPatientAttributes(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 46.0D)
				.add(Attributes.ARMOR, 6.0D)
				.add(Attributes.ARMOR_TOUGHNESS, 2.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.21D)
				.add(Attributes.ATTACK_DAMAGE, 7.0D)
				.add(Attributes.ATTACK_SPEED, 1.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.5D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1.2D)
				.add(Attributes.FOLLOW_RANGE, 30);
	}
	public static  AttributeSupplier.Builder setAshenBloodBeastPatientAttributes(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 80.0D)
				.add(Attributes.ARMOR, 8.0D)
				.add(Attributes.ARMOR_TOUGHNESS, 2.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.21D)
				.add(Attributes.ATTACK_DAMAGE, 10.0D)
				.add(Attributes.ATTACK_SPEED, 1.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.8D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1.2D)
				.add(Attributes.FOLLOW_RANGE, 50);
	}
	public static  AttributeSupplier.Builder setLeechAttributes(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 52.0D)
				.add(Attributes.ARMOR, 4.0D)
				.add(Attributes.ARMOR_TOUGHNESS, 0.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.24D)
				.add(Attributes.ATTACK_DAMAGE, 3.0D)
				.add(Attributes.ATTACK_SPEED, 1.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.8D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1.2D)
				.add(Attributes.FOLLOW_RANGE, 30)
				.add(ForgeMod.SWIM_SPEED.get(),2.0f);
	}
	public static  AttributeSupplier.Builder setChurchDoctorAttributes(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 64.0D)
				.add(Attributes.ARMOR, 5.0D)
				.add(Attributes.ARMOR_TOUGHNESS, 2.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.2D)
				.add(Attributes.ATTACK_DAMAGE, 7.0D)
				.add(Attributes.ATTACK_SPEED, 1.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.3D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1.8D)
				.add(Attributes.FOLLOW_RANGE, 20);
	}
	public static  AttributeSupplier.Builder setChurchDoctorLanternAttributes(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 68.0D)
				.add(Attributes.ARMOR, 5.0D)
				.add(Attributes.ARMOR_TOUGHNESS, 2.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.2D)
				.add(Attributes.ATTACK_DAMAGE, 7.0D)
				.add(Attributes.ATTACK_SPEED, 1.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.3D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1.8D)
				.add(Attributes.FOLLOW_RANGE, 20);
	}
	public static  AttributeSupplier.Builder setChurchDoctorPistolAttributes(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 60.0D)
				.add(Attributes.ARMOR, 5.0D)
				.add(Attributes.ARMOR_TOUGHNESS, 2.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.2D)
				.add(Attributes.ATTACK_DAMAGE, 7.0D)
				.add(Attributes.ATTACK_SPEED, 1.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.3D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1.8D)
				.add(Attributes.FOLLOW_RANGE, 20);
	}
	public static  AttributeSupplier.Builder setChurchDoctorFlamesprayerAttributes(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 76.0D)
				.add(Attributes.ARMOR, 5.0D)
				.add(Attributes.ARMOR_TOUGHNESS, 2.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.2D)
				.add(Attributes.ATTACK_DAMAGE, 7.0D)
				.add(Attributes.ATTACK_SPEED, 1.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.3D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1.8D)
				.add(Attributes.FOLLOW_RANGE, 20);
	}
	public static  AttributeSupplier.Builder setChurchDoctorScytheAttributes(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 80.0D)
				.add(Attributes.ARMOR, 5.0D)
				.add(Attributes.ARMOR_TOUGHNESS, 2.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.2D)
				.add(Attributes.ATTACK_DAMAGE, 10.0D)
				.add(Attributes.ATTACK_SPEED, 1.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.3D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1.8D)
				.add(Attributes.FOLLOW_RANGE, 20);
	}
	public static  AttributeSupplier.Builder setChurchDoctorCrucifixAttributes(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 80.0D)
				.add(Attributes.ARMOR, 5.0D)
				.add(Attributes.ARMOR_TOUGHNESS, 2.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.2D)
				.add(Attributes.ATTACK_DAMAGE, 9.0D)
				.add(Attributes.ATTACK_SPEED, 1.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.3D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1.8D)
				.add(Attributes.FOLLOW_RANGE, 20);
	}
	public static  AttributeSupplier.Builder setDarkwraithAttributes(){
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 80.0D)
				.add(Attributes.ARMOR, 5.0D)
				.add(Attributes.ARMOR_TOUGHNESS, 3.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.2D)
				.add(Attributes.ATTACK_DAMAGE, 6.0D)
				.add(Attributes.ATTACK_SPEED, 1.2D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.1D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1.6D)
				.add(Attributes.FOLLOW_RANGE, 20);
	}

}
