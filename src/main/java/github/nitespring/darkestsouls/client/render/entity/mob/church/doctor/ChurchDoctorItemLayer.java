package github.nitespring.darkestsouls.client.render.entity.mob.church.doctor;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import github.nitespring.darkestsouls.common.entity.mob.church.ChurchDoctor;
import github.nitespring.darkestsouls.common.entity.mob.hollow.*;
import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;

public class ChurchDoctorItemLayer<T extends ChurchDoctor & GeoEntity> extends BlockAndItemGeoLayer<T>{

	public ChurchDoctorItemLayer(GeoRenderer<T> renderer) {
		super(renderer);
		
	}
	
	

	@Override
	protected ItemStack getStackForBone(GeoBone bone, T animatable) {
		 if (bone.getName().equals("itemRight")) {
				 return animatable.getRightHandItem();
		 }else if (bone.getName().equals("itemLeft")) {
			 return animatable.getLeftHandItem();
		 }else{
				 return null;
			 }
		 }
	
	@Override
	protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack, T animatable) {
		if (bone.getName().equals("itemRight")) {
			  return ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
		  }else if (bone.getName().equals("itemLeft")) {
			return ItemDisplayContext.THIRD_PERSON_LEFT_HAND;
		}else {
			return null;
		}

	}
	
	



@Override
protected void renderStackForBone(PoseStack poseStack, GeoBone bone, ItemStack stack, T animatable,
		MultiBufferSource bufferSource, float partialTick, int packedLight, int packedOverlay) {
		poseStack.pushPose();
	if (bone.getName().equals("itemRight")) {
	if(animatable.getRightHandItem().getItem()==ItemInit.CRUCIFIX.get()){
		poseStack.translate(0, -0.12, -0.5);
		poseStack.mulPose(Axis.XP.rotationDegrees(-90));
		poseStack.mulPose(Axis.YP.rotationDegrees(90));
		poseStack.mulPose(Axis.ZP.rotationDegrees(0));
		poseStack.scale(1.2f,1.2f,1.2f);
	}else if(animatable.getRightHandItem().getItem()==ItemInit.CHURCH_SCYTHE.get()){
		poseStack.translate(0, -0.16, -0.1);
		poseStack.mulPose(Axis.XP.rotationDegrees(-90));
		poseStack.mulPose(Axis.YP.rotationDegrees(0));
		poseStack.mulPose(Axis.ZP.rotationDegrees(0));
		poseStack.scale(1.4f,1.4f,1.4f);
	}else{
		poseStack.translate(0, -0.12, 0);
		poseStack.mulPose(Axis.XP.rotationDegrees(-90));
		poseStack.mulPose(Axis.YP.rotationDegrees(0));
		poseStack.mulPose(Axis.ZP.rotationDegrees(0));
		poseStack.scale(1.0f,1.0f,1.0f);
	}

	  }else if (bone.getName().equals("itemLeft")) {
		if(animatable.getLeftHandItem().getItem()==ItemInit.REPEATING_PISTOL.get()) {
			poseStack.translate(0, -0.22, -0.40);
			poseStack.mulPose(Axis.XP.rotationDegrees(-45));
			poseStack.mulPose(Axis.YP.rotationDegrees(180));
			poseStack.mulPose(Axis.ZP.rotationDegrees(0));
		}else if(animatable.getLeftHandItem().getItem()==ItemInit.LANTERN.get()) {
			poseStack.translate(0, -0.12, -0.12);
			poseStack.mulPose(Axis.XP.rotationDegrees(-90));
			poseStack.mulPose(Axis.YP.rotationDegrees(0));
			poseStack.mulPose(Axis.ZP.rotationDegrees(0));
		}else if(animatable.getLeftHandItem().getItem()==ItemInit.FLAMESPRAYER.get()) {
			poseStack.translate(0, 0, -0.12);
			poseStack.mulPose(Axis.XP.rotationDegrees(-60));
			poseStack.mulPose(Axis.YP.rotationDegrees(0));
			poseStack.mulPose(Axis.ZP.rotationDegrees(0));
		}else{
			poseStack.translate(0, -0.12, 0);
			poseStack.mulPose(Axis.XP.rotationDegrees(-90));
			poseStack.mulPose(Axis.YP.rotationDegrees(0));
			poseStack.mulPose(Axis.ZP.rotationDegrees(0));
		}
	}
	super.renderStackForBone(poseStack, bone, stack, animatable, bufferSource, partialTick, packedLight, packedOverlay);
	poseStack.popPose();
}
	


}
