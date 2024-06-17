package github.nitespring.darkestsouls.core.event;



import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.client.render.entity.mob.abyss.DarkwraithGeoRenderer;
import github.nitespring.darkestsouls.client.render.entity.mob.abyss.LeechGeoRenderer;
import github.nitespring.darkestsouls.client.render.entity.mob.abyss.MonstruosityOfSinGeoRenderer;
import github.nitespring.darkestsouls.client.render.entity.mob.abyss.SewerCentipedeGeoRenderer;
import github.nitespring.darkestsouls.client.render.entity.mob.beast.BeastPatientGeoRenderer;
import github.nitespring.darkestsouls.client.render.entity.mob.church.doctor.ChurchDoctorGeoRenderer;
import github.nitespring.darkestsouls.client.render.entity.mob.hollow.HollowGeoRenderer;
import github.nitespring.darkestsouls.client.render.entity.mob.skeleton.*;
import github.nitespring.darkestsouls.client.render.entity.projectile.*;
import github.nitespring.darkestsouls.client.render.entity.projectile.bullet.BulletModel;
import github.nitespring.darkestsouls.client.render.entity.projectile.bullet.BulletRenderer;
import github.nitespring.darkestsouls.client.render.entity.projectile.spell.*;
import github.nitespring.darkestsouls.client.render.entity.projectile.throwable.FirebombRenderer;
import github.nitespring.darkestsouls.client.render.entity.projectile.throwable.MolotovCocktailModel;
import github.nitespring.darkestsouls.client.render.entity.projectile.throwable.MolotovCocktailRenderer;
import github.nitespring.darkestsouls.client.render.entity.projectile.weapon.*;
import github.nitespring.darkestsouls.client.render.entity.projectile.weapon.frayedblade.FrayedBladeFlameModel;
import github.nitespring.darkestsouls.client.render.entity.projectile.weapon.frayedblade.FrayedBladeFlameRenderer;
import github.nitespring.darkestsouls.client.render.entity.projectile.weapon.frayedblade.FrayedBladeRenderer;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DarkestSouls.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientListener {


	public static final ModelLayerLocation SQUARE_TEXTURE = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "square_texture"), "main");
	public static final ModelLayerLocation FRAYED_BLADE_FLAME = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "frayed_blade_fire"), "main");
	public static final ModelLayerLocation BULLET = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "bullet"), "main");
	public static final ModelLayerLocation MOLOTOV = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "molotov"), "main");


	@SubscribeEvent
	public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event){

		event.registerLayerDefinition(SQUARE_TEXTURE, SquareTextureEntityModel::createBodyLayer);
		event.registerLayerDefinition(FRAYED_BLADE_FLAME, FrayedBladeFlameModel::createBodyLayer);
		event.registerLayerDefinition(BULLET, BulletModel::createBodyLayer);
		event.registerLayerDefinition(MOLOTOV, MolotovCocktailModel::createBodyLayer);

	}



	 @SubscribeEvent
	 	public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
		 
		 event.registerEntityRenderer(EntityInit.SIN.get(), MonstruosityOfSinGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.BONEWHEEL.get(), BonewheelGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.SEWER_CENTIPEDE.get(), SewerCentipedeGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.SKELETON_FALCHION.get(), SkeletonFalchionGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.SKELETON_CURVED_SWORDS.get(), SkeletonCurvedSwordsGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.SKELETON_SPEAR.get(), SkeletonSpearGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.TALL_SKELETON_TWIN_SHOTELS.get(), TallSkeletonGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.HOLLOW_LONGSWORD.get(), HollowGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.HOLLOW_AXE.get(), HollowGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.HOLLOW_ASSASSIN.get(), HollowGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.HOLLOW_BROKEN_STRAIGHTSWORD.get(), HollowGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.GRAVETENDER_HOLLOW_LONGSWORD.get(), HollowGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.GRAVETENDER_HOLLOW_BROKEN_STRAIGHTSWORD.get(), HollowGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.BEAST_PATIENT.get(), BeastPatientGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.CLOAKED_BEAST_PATIENT.get(), BeastPatientGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.ASHEN_BLOOD_BEAST_PATIENT.get(), BeastPatientGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.LEECH.get(), LeechGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.CHURCH_DOCTOR.get(), ChurchDoctorGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.CHURCH_DOCTOR_LANTERN.get(), ChurchDoctorGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.CHURCH_DOCTOR_PISTOL.get(), ChurchDoctorGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.CHURCH_DOCTOR_FLAMESPRAYER.get(), ChurchDoctorGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.CHURCH_DOCTOR_SCYTHE.get(), ChurchDoctorGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.CHURCH_DOCTOR_CRUCIFIX.get(), ChurchDoctorGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.DARKWRAITH.get(), DarkwraithGeoRenderer::new);

		 event.registerEntityRenderer(EntityInit.HITBOX_SMALL.get(), InvisibleProjectileRenderer::new);
		 event.registerEntityRenderer(EntityInit.HITBOX.get(), InvisibleProjectileRenderer::new);
		 event.registerEntityRenderer(EntityInit.HITBOX_LARGE.get(), InvisibleProjectileRenderer::new);
		 event.registerEntityRenderer(EntityInit.FRAYED_BLADE.get(), FrayedBladeRenderer::new);
		 event.registerEntityRenderer(EntityInit.FRAYED_BLADE_FLAME.get(), FrayedBladeFlameRenderer::new);
		 event.registerEntityRenderer(EntityInit.SCIMITAR.get(), ScimitarRenderer::new);
		 event.registerEntityRenderer(EntityInit.FALCHION.get(), FalchionRenderer::new);
		 event.registerEntityRenderer(EntityInit.CLAYMORE.get(), ClaymoreRenderer::new);
		 event.registerEntityRenderer(EntityInit.FLAMBERGE.get(), FlambergeRenderer::new);
		 event.registerEntityRenderer(EntityInit.ZWEIHANDER.get(), ZweihanderRenderer::new);
		 event.registerEntityRenderer(EntityInit.UCHIGATANA.get(), UchigatanaRenderer::new);
		 event.registerEntityRenderer(EntityInit.LONGSWORD.get(), LongswordRenderer::new);
		 event.registerEntityRenderer(EntityInit.BROKEN_STRAIGHTSWORD.get(), BrokenStraightswordRenderer::new);
		 event.registerEntityRenderer(EntityInit.CHIKAGE.get(), ChikageRenderer::new);
		 event.registerEntityRenderer(EntityInit.SAW_CLEAVER.get(), SawCleaverRenderer::new);
		 event.registerEntityRenderer(EntityInit.SAW_CLEAVER_EXTENDED.get(), SawCleaverExtendedRenderer::new);
		 event.registerEntityRenderer(EntityInit.GRAVE_SCYTHE.get(), GraveScytheRenderer::new);
		 event.registerEntityRenderer(EntityInit.CHURCH_SCYTHE.get(), ChurchScytheRenderer::new);
		 event.registerEntityRenderer(EntityInit.SHADOW_BLADE.get(), ShadowBladeRenderer::new);
		 event.registerEntityRenderer(EntityInit.HUNTER_AXE_EXTENDED.get(), HunterAxeExtendedRenderer::new);
		 event.registerEntityRenderer(EntityInit.SPEAR.get(), SpearRenderer::new);
		 event.registerEntityRenderer(EntityInit.DRAGONSLAYER_SWORDSPEAR.get(), DragonslayerSwordspearRenderer::new);
		 event.registerEntityRenderer(EntityInit.DRAGONSLAYER_SPEAR.get(), DragonslayerSpearRenderer::new);
		 event.registerEntityRenderer(EntityInit.HUNTER_AXE.get(), HunterAxeRenderer::new);
		 event.registerEntityRenderer(EntityInit.HUNTING_AXE.get(), HuntingAxeRenderer::new);
		 event.registerEntityRenderer(EntityInit.GREATAXE.get(), GreataxeRenderer::new);
		 event.registerEntityRenderer(EntityInit.CURVED_GREATSWORD.get(), CurvedGreatswordRenderer::new);
		 event.registerEntityRenderer(EntityInit.SHOTEL.get(), ShotelRenderer::new);
		 event.registerEntityRenderer(EntityInit.BANDIT_KNIFE.get(), BanditKnifeRenderer::new);
		 event.registerEntityRenderer(EntityInit.GREATSWORD.get(), GreatswordRenderer::new);
		 event.registerEntityRenderer(EntityInit.DARKSWORD.get(), DarkSwordRenderer::new);



		 event.registerEntityRenderer(EntityInit.SOUL_DART.get(), SoulDartRenderer::new);
		 event.registerEntityRenderer(EntityInit.SOUL_ARROW.get(), SoulDartRenderer::new);
		 event.registerEntityRenderer(EntityInit.FIREBALL.get(), FireBallRenderer::new);
		 event.registerEntityRenderer(EntityInit.CHAOS_FIREBALL.get(), FireBallRenderer::new);
		 event.registerEntityRenderer(EntityInit.MAGMA.get(), MagmaRenderer::new);
		 event.registerEntityRenderer(EntityInit.MAGMA_BURST.get(), LitItemRenderer::new);
		 event.registerEntityRenderer(EntityInit.MAGMA_BURST_CHILD.get(), LitItemRenderer::new);
		 event.registerEntityRenderer(EntityInit.LIGHTNING_SPEAR.get(), LightningBoltRenderer::new);
		 event.registerEntityRenderer(EntityInit.PARASITES.get(), ThrownItemRenderer::new);
		 event.registerEntityRenderer(EntityInit.VOMIT.get(), ThrownItemRenderer::new);
		 event.registerEntityRenderer(EntityInit.CRYSTAL_SHARD.get(), CrystalShardRenderer::new);
		 event.registerEntityRenderer(EntityInit.CRYSTAL_RAIN.get(), CrystalBallRenderer::new);
		 event.registerEntityRenderer(EntityInit.WIND_SLASH.get(), WindSlashRenderer::new);
		 event.registerEntityRenderer(EntityInit.THROWING_KNIFE.get(), (EntityRendererProvider.Context context) -> new DirectionalAsItemRenderer(context));
		 event.registerEntityRenderer(EntityInit.FIREBOMB.get(), FirebombRenderer::new);
		 event.registerEntityRenderer(EntityInit.MOLOTOV.get(), MolotovCocktailRenderer::new);
		 event.registerEntityRenderer(EntityInit.BULLET.get(), BulletRenderer::new);
		 event.registerEntityRenderer(EntityInit.FLAME.get(), FlameRenderer::new);
		 event.registerEntityRenderer(EntityInit.MOONLIGHT_WAVE.get(), MoonlightSlashRenderer::new);

		 
	 }

}
