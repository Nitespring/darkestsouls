package github.nitespring.darkestsouls.client.render.item.gun;

import com.mojang.blaze3d.vertex.PoseStack;
import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.Bonewheel;
import github.nitespring.darkestsouls.common.item.child.guns.GatlingGun;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class GatlingGunGeoRenderer<T extends GatlingGun> extends GeoItemRenderer<T> {

	public GatlingGunGeoRenderer()
    {
        super(new GatlingGunModel<T>());

    }

	
	@Override
	public int getPackedOverlay(T animatable, float u) {

		return OverlayTexture.NO_OVERLAY;
	}

	
	 @Override
	public RenderType getRenderType(T animatable, ResourceLocation texture, MultiBufferSource bufferSource,
			float partialTick) {
		 return RenderType.entityCutoutNoCull(texture);
	}



    public static class GatlingGunModel<T extends GatlingGun> extends GeoModel<T> {

        @Override
        public ResourceLocation getAnimationResource(T animatable) {

            return new ResourceLocation(DarkestSouls.MODID, "animations/gatling_gun.animation.json");
        }

        @Override
        public ResourceLocation getModelResource(T object) {

            return new ResourceLocation(DarkestSouls.MODID, "geo/gatling_gun.geo.json");
        }

        @Override
        public ResourceLocation getTextureResource(T object) {

            return new ResourceLocation(DarkestSouls.MODID, "textures/item/weapon/gatling_gun.png");
        }



    }
}