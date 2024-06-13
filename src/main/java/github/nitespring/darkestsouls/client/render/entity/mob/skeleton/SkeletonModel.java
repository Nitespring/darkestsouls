package github.nitespring.darkestsouls.client.render.entity.mob.skeleton;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.Skeleton;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class SkeletonModel<T extends Skeleton & GeoEntity> extends GeoModel<T> {

    @Override
    public ResourceLocation getAnimationResource(T animatable) {

        return new ResourceLocation(DarkestSouls.MODID, "animations/skeleton.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(T object) {

        return new ResourceLocation(DarkestSouls.MODID, "geo/skeleton.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T object) {

        return new ResourceLocation(DarkestSouls.MODID, "textures/entity/skeleton/skeleton.png");
    }

    @Override
    public void setCustomAnimations(T entity, long uniqueID, AnimationState<T> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        GeoBone head = this.getAnimationProcessor().getBone("head_rotation");
        assert customPredicate != null;
        EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(extraData.headPitch() * ((float) Math.PI / 180F));
        head.setRotY(extraData.netHeadYaw() *0.5f* ((float) Math.PI / 180F));


    }

}