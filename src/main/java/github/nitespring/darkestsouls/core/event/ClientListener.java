package github.nitespring.darkestsouls.core.event;



import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.client.render.entity.mob.abyss.MonstruosityOfSinGeoRenderer;
import github.nitespring.darkestsouls.client.render.entity.mob.skeleton.BonewheelGeoRenderer;
import github.nitespring.darkestsouls.client.render.entity.projectile.FrayedBladeRenderer;
import github.nitespring.darkestsouls.client.render.entity.projectile.InvisibleProjectileRenderer;
import github.nitespring.darkestsouls.client.render.entity.projectile.SquareTextureEntityModel;
import github.nitespring.darkestsouls.common.entity.mob.abyss.Bonewheel;
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



	@SubscribeEvent
	public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event){

		event.registerLayerDefinition(SQUARE_TEXTURE, SquareTextureEntityModel::createBodyLayer);

	}



	 @SubscribeEvent
	 	public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
		 
		 event.registerEntityRenderer(EntityInit.SIN.get(), MonstruosityOfSinGeoRenderer::new);
		 event.registerEntityRenderer(EntityInit.BONEWHEEL.get(), BonewheelGeoRenderer::new);

		 event.registerEntityRenderer(EntityInit.HITBOX_SMALL.get(), InvisibleProjectileRenderer::new);
		 event.registerEntityRenderer(EntityInit.HITBOX.get(), InvisibleProjectileRenderer::new);
		 event.registerEntityRenderer(EntityInit.HITBOX_LARGE.get(), InvisibleProjectileRenderer::new);
		 event.registerEntityRenderer(EntityInit.FRAYED_BLADE.get(), FrayedBladeRenderer::new);

		 
	 }

}
