package github.nitespring.darkestsouls.client.render.entity.mob.church.doctor;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.mob.church.ChurchDoctor;
import github.nitespring.darkestsouls.common.entity.mob.hollow.Hollow;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class ChurchDoctorModel<T extends ChurchDoctor & GeoEntity> extends GeoModel<T> {

    public static final ResourceLocation WHITE = new ResourceLocation(DarkestSouls.MODID, "textures/entity/church/church_doctor_white.png");
    public static final ResourceLocation BLUE = new ResourceLocation(DarkestSouls.MODID, "textures/entity/church/church_doctor_blue.png");
    public static final ResourceLocation SILVER = new ResourceLocation(DarkestSouls.MODID, "textures/entity/church/church_doctor_silver.png");
    public static final ResourceLocation LIGHT_GRAY = new ResourceLocation(DarkestSouls.MODID, "textures/entity/church/church_doctor_light_gray.png");
    public static final ResourceLocation GRAY = new ResourceLocation(DarkestSouls.MODID, "textures/entity/church/church_doctor_gray.png");
    public static final ResourceLocation DARK_GRAY = new ResourceLocation(DarkestSouls.MODID, "textures/entity/church/church_doctor_dark_gray.png");
    public static final ResourceLocation BLACK = new ResourceLocation(DarkestSouls.MODID, "textures/entity/church/church_doctor_black.png");
    @Override
    public ResourceLocation getAnimationResource(T animatable) {

        return new ResourceLocation(DarkestSouls.MODID, "animations/church_doctor.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(T object) {

        return new ResourceLocation(DarkestSouls.MODID, "geo/church_doctor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T object) {
        switch(object.getRobeType()) {
            case 1:
                return WHITE;
            case 2:
                return BLUE;
            case 3:
                return SILVER;
            case 4:
                return GRAY;
            case 5:
                return DARK_GRAY;
            case 6:
                return BLACK;
            default:
                return LIGHT_GRAY;
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
        CoreGeoBone hat = this.getAnimationProcessor().getBone("hat");
        CoreGeoBone overhang = this.getAnimationProcessor().getBone("hatPart");
        if(entity.getHatType()==0){
            hat.setHidden(false);
            overhang.setHidden(true);
        }else if(entity.getHatType()==1){
            hat.setHidden(false);
            overhang.setHidden(false);
        }else{
            hat.setHidden(true);
            hat.setHidden(true);
        }


    }

}