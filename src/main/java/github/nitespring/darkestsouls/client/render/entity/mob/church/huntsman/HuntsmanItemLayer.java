package github.nitespring.darkestsouls.client.render.entity.mob.church.huntsman;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import github.nitespring.darkestsouls.common.entity.mob.church.ChurchDoctor;
import github.nitespring.darkestsouls.common.entity.mob.church.Huntsman;
import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;

public class HuntsmanItemLayer<T extends Huntsman & GeoEntity> extends BlockAndItemGeoLayer<T>{

	public HuntsmanItemLayer(GeoRenderer<T> renderer) {
		super(renderer);
		
	}
	
	

	@Override
	protected ItemStack getStackForBone(GeoBone bone, T animatable) {
		 if (bone.getName().equals("right_item")) {
				 return animatable.getRightHandItem();
		 }else if (bone.getName().equals("left_item")) {
			 return animatable.getLeftHandItem();
		 }else{
				 return null;
			 }
		 }
	
	@Override
	protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack, T animatable) {
		if (bone.getName().equals("right_item")) {
			  return ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
		  }else if (bone.getName().equals("left_item")) {
			return ItemDisplayContext.THIRD_PERSON_LEFT_HAND;
		}else {
			return null;
		}

	}
	
	



@Override
protected void renderStackForBone(PoseStack poseStack, GeoBone bone, ItemStack stack, T animatable,
		MultiBufferSource bufferSource, float partialTick, int packedLight, int packedOverlay) {
		poseStack.pushPose();
	if (bone.getName().equals("right_item")) {
		if(animatable.getRightHandItem().getItem()==ItemInit.HUNTER_TORCH.get()){
			poseStack.translate(0, -0.075, -0.05);
			poseStack.mulPose(Axis.XP.rotationDegrees(-80));
			poseStack.mulPose(Axis.YP.rotationDegrees(0));
			poseStack.mulPose(Axis.ZP.rotationDegrees(0));
			poseStack.scale(1.0f,1.0f,1.0f);
		}else if(animatable.getRightHandItem().getItem()==ItemInit.HUNTSMAN_PITCHFORK.get()){
			poseStack.translate(0, -0.025, -0.5);
			poseStack.mulPose(Axis.XP.rotationDegrees(-80));
			poseStack.mulPose(Axis.YP.rotationDegrees(0));
			poseStack.mulPose(Axis.ZP.rotationDegrees(0));
			poseStack.scale(1.0f,1.0f,1.0f);
		}else if(animatable.getRightHandItem().getItem()==ItemInit.MUSKET.get()){
			poseStack.translate(0, -0.06, 0);
			poseStack.mulPose(Axis.XP.rotationDegrees(0));
			poseStack.mulPose(Axis.YP.rotationDegrees(0));
			poseStack.mulPose(Axis.ZP.rotationDegrees(0));
			poseStack.scale(1.0f,1.0f,1.0f);
		}else if(animatable.getRightHandItem().getItem()==ItemInit.HUNTSMAN_CUTLASS.get()){
			poseStack.translate(0, -0.16, -0.13);
			poseStack.mulPose(Axis.XP.rotationDegrees(-90));
			poseStack.mulPose(Axis.YP.rotationDegrees(0));
			poseStack.mulPose(Axis.ZP.rotationDegrees(0));
			poseStack.scale(1.0f,1.0f,1.0f);
		}else{
			poseStack.translate(0, -0.12, 0);
			poseStack.mulPose(Axis.XP.rotationDegrees(-90));
			poseStack.mulPose(Axis.YP.rotationDegrees(0));
			poseStack.mulPose(Axis.ZP.rotationDegrees(0));
			poseStack.scale(1.0f,1.0f,1.0f);
		}

	}else if (bone.getName().equals("left_item")) {
		if(animatable.getLeftHandItem().getItem()==ItemInit.HUNTSMAN_AXE.get()) {
			poseStack.translate(0, 0.32, -0.52);
			poseStack.mulPose(Axis.XP.rotationDegrees(-10));
			poseStack.mulPose(Axis.YP.rotationDegrees(0));
			poseStack.mulPose(Axis.ZP.rotationDegrees(180));
		}else{
			poseStack.translate(0, 0.4, -0.5);
			poseStack.mulPose(Axis.XP.rotationDegrees(0));
			poseStack.mulPose(Axis.YP.rotationDegrees(0));
			poseStack.mulPose(Axis.ZP.rotationDegrees(180));
		}
	}
	super.renderStackForBone(poseStack, bone, stack, animatable, bufferSource, partialTick, packedLight, packedOverlay);
	poseStack.popPose();
}
	


}
