package github.nitespring.darkestsouls.client.render.entity.mob.church.huntsman;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.mob.church.ChurchDoctor;
import github.nitespring.darkestsouls.common.entity.mob.church.Huntsman;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class HuntsmanModel<T extends Huntsman & GeoEntity> extends GeoModel<T> {

    public static final ResourceLocation GREEN = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/church/huntsman/base/huntsman_green.png");
    public static final ResourceLocation ORANGE = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/church/huntsman/base/huntsman_orange.png");
    public static final ResourceLocation BROWN = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/church/huntsman/base/huntsman_brown.png");
    public static final ResourceLocation RED = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/church/huntsman/base/huntsman_red.png");
    public static final ResourceLocation YELLOW = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/church/huntsman/base/huntsman_yellow.png");
    public static final ResourceLocation WHITE_SHIRT = ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "textures/entity/church/huntsman/base/huntsman_shirt.png");

    @Override
    public ResourceLocation getAnimationResource(T animatable) {

        return ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "animations/huntsman.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(T object) {

        return ResourceLocation.fromNamespaceAndPath(DarkestSouls.MODID, "geo/huntsman.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T object) {
        switch(object.getShirtType()) {
            case 1:
                return GREEN;
            case 2:
                return ORANGE;
            case 3:
                return BROWN;
            case 4:
                return RED;
            case 5:
                return YELLOW;
            default:
                return WHITE_SHIRT;
        }
    }

    @Override
    public void setCustomAnimations(T entity, long uniqueID, AnimationState<T> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        GeoBone head = this.getAnimationProcessor().getBone("head_rotation");
        assert customPredicate != null;
        EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(extraData.headPitch() * ((float) Math.PI / 180F));
        head.setRotY(extraData.netHeadYaw() *0.5f* ((float) Math.PI / 180F));
        GeoBone hair = this.getAnimationProcessor().getBone("hair");
        GeoBone hood = this.getAnimationProcessor().getBone("hood");
        if(entity.getHatType()==3){
            hair.setHidden(true);
            hood.setHidden(false);
        }else{
            hair.setHidden(false);
            hood.setHidden(true);
        }


    }

}