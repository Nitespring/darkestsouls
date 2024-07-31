package github.nitespring.darkestsouls.config;


import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {


		public static ForgeConfigSpec.BooleanValue do_special_attacks;
		public static ForgeConfigSpec.BooleanValue do_special_weapon_attacks_left_click;
		public static ForgeConfigSpec.BooleanValue do_special_weapon_attacks_right_click;
		public static ForgeConfigSpec.BooleanValue do_blood_particles;
		public static ForgeConfigSpec.BooleanValue spawn_darkwraith;
		public static ForgeConfigSpec.BooleanValue spawn_mad_hollow;
		public static ForgeConfigSpec.BooleanValue spawn_gravetender_hollow;
		public static ForgeConfigSpec.BooleanValue spawn_hollow_longsword;
		public static ForgeConfigSpec.BooleanValue spawn_hollow_crossbow;
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
		public static ForgeConfigSpec.BooleanValue spawn_huntsman_axe;
		public static ForgeConfigSpec.BooleanValue spawn_huntsman_cutlass;
		public static ForgeConfigSpec.BooleanValue spawn_huntsman_pitchfork;
		public static ForgeConfigSpec.BooleanValue spawn_huntsman_rifle;
		public static ForgeConfigSpec.BooleanValue spawn_gravetender_hollow_group;
		public static ForgeConfigSpec.BooleanValue spawn_hollow_soldier_group;
		public static ForgeConfigSpec.BooleanValue spawn_huntsman_group;
		public static ForgeConfigSpec.BooleanValue spawn_church_doctor_group;
		public static ForgeConfigSpec.BooleanValue spawn_beast_patient_group;
		public static ForgeConfigSpec.BooleanValue spawn_skeleton_group;
		
		
		
	public CommonConfig(final ForgeConfigSpec.Builder server) {
			

		do_special_attacks = server
				.comment("Set to false to disable special attacks on common weapons")
				.define("config.do_special_attacks", true);

		do_special_weapon_attacks_left_click = server
				.comment("Set to false to disable special attackson left click on special weapons")
				.define("config.do_special_weapon_attacks_left_click", true);

		do_special_weapon_attacks_right_click = server
				.comment("Set to false to disable special attacks on right click on special weapons")
				.define("config.do_special_weapon_attacks_right_click", true);

		do_blood_particles = server
				.comment("Set to false to disable blood particles")
				.define("config.do_blood_particles", true);

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
		spawn_hollow_crossbow = server
				.comment("Set to false to disable Hollow Soldier (Crossbow)")
				.define("spawnconfig.spawn_hollow_crossbow", true);
		spawn_hollow_axe = server
				.comment("Set to false to disable Hollow Soldier (Axe)")
				.define("spawnconfig.spawn_hollow_axe", true);
		spawn_hollow_assassin = server
				.comment("Set to false to disable Hollow Assassin")
				.define("spawnconfig.spawn_hollow_assassin", true);
		spawn_huntsman_axe = server
				.comment("Set to false to disable Huntsman (Axe)")
				.define("spawnconfig.spawn_huntsman_axe", true);
		spawn_huntsman_cutlass = server
				.comment("Set to false to disable Huntsman (Cutlass)")
				.define("spawnconfig.spawn_huntsman_cutlass", true);
		spawn_huntsman_pitchfork = server
				.comment("Set to false to disable Huntsman (Pitchfork)")
				.define("spawnconfig.spawn_huntsman_pitchfork", true);
		spawn_huntsman_rifle = server
				.comment("Set to false to disable Huntsman (Rifle)")
				.define("spawnconfig.spawn_huntsman_rifle", true);
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
		spawn_darkwraith = server
				.comment("Set to false to disable Darkwraith")
				.define("spawnconfig.spawn_darkwraith", true);
		spawn_gravetender_hollow_group = server
				.comment("Set to false to disable Gravetender Hollow Groups")
				.define("spawnconfig.spawn_gravetender_hollow_group", true);
		spawn_hollow_soldier_group = server
				.comment("Set to false to disable Hollow Soldier Groups")
				.define("spawnconfig.spawn_hollow_soldier_group", true);
		spawn_huntsman_group = server
				.comment("Set to false to disable Huntsman Groups")
				.define("spawnconfig.spawn_huntsman_group", true);
		spawn_church_doctor_group = server
				.comment("Set to false to disable Church Doctor Groups")
				.define("spawnconfig.spawn_church_doctor_group", true);
		spawn_beast_patient_group = server
				.comment("Set to false to disable Beast Patient Groups")
				.define("spawnconfig.spawn_beast_patient_group", true);
		spawn_skeleton_group = server
				.comment("Set to false to disable Skeleton Groups")
				.define("spawnconfig.spawn_skeleton_group", true);

		}

	
}
