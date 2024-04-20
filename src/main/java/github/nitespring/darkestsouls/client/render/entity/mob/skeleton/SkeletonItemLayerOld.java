package github.nitespring.darkestsouls.client.render.entity.mob.skeleton;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.Skeleton;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.SkeletonCurvedSwords;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.SkeletonFalchion;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.SkeletonSpear;
import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;

public class SkeletonItemLayerOld<T extends Skeleton & GeoEntity> extends BlockAndItemGeoLayer<T>{

	public SkeletonItemLayerOld(GeoRenderer<T> renderer) {
		super(renderer);
		
	}
	
	

	@Override
	protected ItemStack getStackForBone(GeoBone bone, T animatable) {
		 if (bone.getName().equals("item_right")) {
			 if(animatable instanceof SkeletonFalchion) {
				 return ItemInit.FALCHION.get().getDefaultInstance();
			 }else if(animatable instanceof SkeletonCurvedSwords) {
				 return ItemInit.BANDIT_CURVED_SWORD.get().getDefaultInstance();
			 }else if(animatable instanceof SkeletonSpear) {
				 return ItemInit.SPEAR.get().getDefaultInstance();
			 }else{
				 return null;
			 }
		  }else if (bone.getName().equals("item_left")) {
			 if(animatable instanceof SkeletonCurvedSwords) {
				 return ItemInit.BANDIT_CURVED_SWORD.get().getDefaultInstance();
			 }else{
				 return null;
			 }
		 }else{
		return null;
		
		  }
	}
	
	@Override
	protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack, T animatable) {
		if (bone.getName().equals("item_right")) {
			  return ItemDisplayContext.THIRD_PERSON_LEFT_HAND;
		  }else if (bone.getName().equals("item_left")) {
			return ItemDisplayContext.THIRD_PERSON_LEFT_HAND;
		}else {
			return null;
		}

	}
	
	



@Override
protected void renderStackForBone(PoseStack poseStack, GeoBone bone, ItemStack stack, T animatable,
		MultiBufferSource bufferSource, float partialTick, int packedLight, int packedOverlay) {
	if (bone.getName().equals("item_right")) {
		if(animatable instanceof SkeletonCurvedSwords) {
			poseStack.translate(0.05, 0.76, -0.71);
			poseStack.mulPose(Axis.XP.rotationDegrees(0));
			poseStack.mulPose(Axis.YP.rotationDegrees(-15));
			poseStack.mulPose(Axis.ZP.rotationDegrees(180));
		}else if(animatable instanceof SkeletonSpear) {
			poseStack.translate(-0.0, 0.25, -0.7);
			poseStack.mulPose(Axis.XP.rotationDegrees(0));
			poseStack.mulPose(Axis.YP.rotationDegrees(0));
			poseStack.mulPose(Axis.ZP.rotationDegrees(180));
		}else{
			poseStack.translate(0.00, 0.6, -0.5);
			poseStack.mulPose(Axis.XP.rotationDegrees(0));
			poseStack.mulPose(Axis.YP.rotationDegrees(0));
			poseStack.mulPose(Axis.ZP.rotationDegrees(180));
		}
      
	  }
	if (bone.getName().equals("item_left")) {
		poseStack.translate(0.05, 0.76, -0.71);
		poseStack.mulPose(Axis.XP.rotationDegrees(0));
		poseStack.mulPose(Axis.YP.rotationDegrees(-15));
		poseStack.mulPose(Axis.ZP.rotationDegrees(180));


	}
	super.renderStackForBone(poseStack, bone, stack, animatable, bufferSource, partialTick, packedLight, packedOverlay);
}
	


}
