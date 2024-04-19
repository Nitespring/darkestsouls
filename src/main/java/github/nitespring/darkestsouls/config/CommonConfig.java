package github.nitespring.darkestsouls.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {


		public static ForgeConfigSpec.BooleanValue do_special_attacks;
		public static ForgeConfigSpec.BooleanValue spawn_mad_hollow;
		public static ForgeConfigSpec.BooleanValue spawn_gravetender_hollow;
		public static ForgeConfigSpec.BooleanValue spawn_hollow_longsword;
		public static ForgeConfigSpec.BooleanValue spawn_hollow_axe;
		public static ForgeConfigSpec.BooleanValue spawn_hollow_assassin;
		public static ForgeConfigSpec.BooleanValue spawn_skeleton_falchion;
		public static ForgeConfigSpec.BooleanValue spawn_skeleton_curved_swords;
		public static ForgeConfigSpec.BooleanValue spawn_skeleton_spear;
		public static ForgeConfigSpec.BooleanValue spawn_skeleton_swordsman_twin_shotels;
		public static ForgeConfigSpec.BooleanValue spawn_bonewheel;
		public static ForgeConfigSpec.BooleanValue spawn_beast_patient;
		public static ForgeConfigSpec.BooleanValue spawn_cloaked_beast_patient;
		public static ForgeConfigSpec.BooleanValue spawn_ashen_blood_beast_patient;
		public static ForgeConfigSpec.BooleanValue spawn_church_doctor;
		public static ForgeConfigSpec.BooleanValue spawn_church_doctor_scythe;
		public static ForgeConfigSpec.BooleanValue spawn_church_doctor_pistol;
		public static ForgeConfigSpec.BooleanValue spawn_church_doctor_flamesprayer;
		public static ForgeConfigSpec.BooleanValue spawn_church_doctor_crucifix;
		public static ForgeConfigSpec.BooleanValue spawn_monstruosity_of_sin;
		public static ForgeConfigSpec.BooleanValue spawn_sewer_centipede;
		public static ForgeConfigSpec.BooleanValue spawn_leech;

		
		
		
	public CommonConfig(final ForgeConfigSpec.Builder server) {
			

		do_special_attacks = server
				.comment("Set to false to disable special attacks on common weapons")
				.define("config.do_special_attacks", true);

		server.comment("Spawn configs:");

		spawn_mad_hollow = server
				.comment("Set to false to disable Mad Hollow")
				.define("spawnconfig.spawn_mad_hollow", true);
		spawn_gravetender_hollow = server
				.comment("Set to false to disable Gravetender Hollow (All Types)")
				.define("spawnconfig.spawn_gravetender_hollow", true);
		spawn_hollow_longsword = server
				.comment("Set to false to disable Hollow Soldier (Longsword)")
				.define("spawnconfig.spawn_hollow_longsword", true);
		spawn_hollow_axe = server
				.comment("Set to false to disable Hollow Soldier (Axe)")
				.define("spawnconfig.spawn_hollow_axe", true);
		spawn_hollow_assassin = server
				.comment("Set to false to disable Hollow Assassin")
				.define("spawnconfig.spawn_hollow_assassin", true);
		spawn_skeleton_falchion = server
				.comment("Set to false to disable Skeleton (Falchion)")
				.define("spawnconfig.spawn_skeleton_falchion", true);
		spawn_skeleton_spear = server
				.comment("Set to false to disable Skeleton (Spear)")
				.define("spawnconfig.spawn_skeleton_spear", true);
		spawn_skeleton_curved_swords = server
				.comment("Set to false to disable Skeleton (Curved Swords)")
				.define("spawnconfig.spawn_skeleton_curved_swords", true);
		spawn_skeleton_swordsman_twin_shotels = server
				.comment("Set to false to disable Skeleton Swordsman (Twin Shotels)")
				.define("spawnconfig.spawn_skeleton_swordsman_twin_shotels", true);
		spawn_bonewheel = server
				.comment("Set to false to disable Bonewheel")
				.define("spawnconfig.spawn_bonewheel", true);
		spawn_beast_patient = server
				.comment("Set to false to disable Beast Patient (Naked)")
				.define("spawnconfig.spawn_beast_patient", true);
		spawn_cloaked_beast_patient = server
				.comment("Set to false to disable Beast Patient (Cloaked)")
				.define("spawnconfig.spawn_cloaked_beast_patient", true);
		spawn_ashen_blood_beast_patient = server
				.comment("Set to false to disable Beast Patient (Powerful)")
				.define("spawnconfig.spawn_ashen_blood_beast_patient", true);
		spawn_church_doctor = server
				.comment("Set to false to disable Church Doctor (Normal Variants)")
				.define("spawnconfig.spawn_church_doctor", true);
		spawn_church_doctor_scythe = server
				.comment("Set to false to disable Church Doctor (Scythe)")
				.define("spawnconfig.spawn_church_doctor_scythe", true);
		spawn_church_doctor_pistol = server
				.comment("Set to false to disable Church Doctor (Pistol)")
				.define("spawnconfig.spawn_church_doctor_scythe", true);
		spawn_church_doctor_flamesprayer = server
				.comment("Set to false to disable Church Doctor (Flamesprayer)")
				.define("spawnconfig.spawn_church_doctor_scythe", true);
		spawn_church_doctor_crucifix = server
				.comment("Set to false to disable Church Doctor (Crucifix)")
				.define("spawnconfig.spawn_church_doctor_scythe", true);
		spawn_sewer_centipede = server
				.comment("Set to false to disable Sewer Centipede")
				.define("spawnconfig.spawn_sewer_centipede", true);
		spawn_monstruosity_of_sin = server
				.comment("Set to false to disable Monstruosity of Sin")
				.define("spawnconfig.spawn_monstruosity_of_sin", true);
		spawn_leech = server
				.comment("Set to false to disable Leech")
				.define("spawnconfig.spawn_leech", true);

		}

	
}
