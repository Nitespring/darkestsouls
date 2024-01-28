package github.nitespring.darkestsouls.core.event;



import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.client.render.entity.mob.abyss.MonstruosityOfSinGeoRenderer;
import github.nitespring.darkestsouls.client.render.entity.mob.skeleton.BonewheelGeoRenderer;
import github.nitespring.darkestsouls.client.render.entity.projectile.weapon.*;
import github.nitespring.darkestsouls.client.render.entity.projectile.weapon.frayedblade.FrayedBladeFlameModel;
import github.nitespring.darkestsouls.client.render.entity.projectile.weapon.frayedblade.FrayedBladeFlameRenderer;
import github.nitespring.darkestsouls.client.render.entity.projectile.weapon.frayedblade.FrayedBladeRenderer;
import github.nitespring.darkestsouls.client.render.entity.projectile.InvisibleProjectileRenderer;
import github.nitespring.darkestsouls.client.render.entity.projectile.SquareTextureEntityModel;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DarkestSouls.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientListener {


	public static final ModelLayerLocation SQUARE_TEXTURE = new ModelLayerLocation(new ResourceLocation(DarkestSouls.MODID, "square_texture"), "main");
	public static final ModelLayerLocation FRAYED_BLADE_FLAME = new ModelLayerLocation(new ResourceLocation(DarkestSouls.MODID, "frayed_blade_fire"), "main");


	@SubscribeEvent
	public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event){

		event.registerLayerDefinition(SQUARE_TEXTURE, SquareTextureEntityModel::createBodyLayer);
		event.registerLayerDefinition(FRAYED_BLADE_FLAME, FrayedBladeFlameModel::createBodyLayer);

	}



	 @SubscribeEvent
	 	public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
		 
		 event.registerEntityRenderer(EntityInit.SIN.get(), MonstruosityOfSinGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.BONEWHEEL.get(), BonewheelGeoRenderer::new);

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

		 
	 }

}
