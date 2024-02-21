package github.nitespring.darkestsouls.client.render.entity.mob.hollow;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.mob.hollow.Hollow;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.Skeleton;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class HollowModel<T extends Hollow & GeoEntity> extends GeoModel<T> {

    @Override
    public ResourceLocation getAnimationResource(T animatable) {

        return new ResourceLocation(DarkestSouls.MODID, "animations/hollow.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(T object) {

        return new ResourceLocation(DarkestSouls.MODID, "geo/hollow.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T object) {
        switch(object.getSkinType()) {
            case 1:
                return new ResourceLocation(DarkestSouls.MODID, "textures/entity/hollow/hollow_yellow.png");
            case 2:
                return new ResourceLocation(DarkestSouls.MODID, "textures/entity/hollow/hollow_gray.png");
            case 3:
                return new ResourceLocation(DarkestSouls.MODID, "textures/entity/hollow/hollow_brown.png");
            case 4:
                return new ResourceLocation(DarkestSouls.MODID, "textures/entity/hollow/hollow_red.png");
            case 5:
                return new ResourceLocation(DarkestSouls.MODID, "textures/entity/hollow/hollow_green.png");
            default:
                return new ResourceLocation(DarkestSouls.MODID, "textures/entity/hollow/hollow.png");
        }
    }

    @Override
    public void setCustomAnimations(T entity, long uniqueID, AnimationState<T> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        CoreGeoBone head = this.getAnimationProcessor().getBone("head_rotation");
        assert customPredicate != null;
        EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(extraData.headPitch() * ((float) Math.PI / 180F));
        head.setRotY(extraData.netHeadYaw() *0.5f* ((float) Math.PI / 180F));
        CoreGeoBone scarf = this.getAnimationProcessor().getBone("scarf");
        //scarf.setRotX(extraData.headPitch() * ((float) Math.PI / 180F));
        //scarf.setRotY(extraData.netHeadYaw() *0.5f* ((float) Math.PI / 180F));
        CoreGeoBone cloak = this.getAnimationProcessor().getBone("cloak");
        if(entity.getRobeType()==4){
            scarf.setHidden(true);
            cloak.setHidden(true);
        }else{
            scarf.setHidden(false);
            cloak.setHidden(false);
        }


    }

}