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

public class SkeletonItemLayer<T extends Skeleton & GeoEntity> extends BlockAndItemGeoLayer<T>{

	public SkeletonItemLayer(GeoRenderer<T> renderer) {
		super(renderer);
		
	}
	
	

	@Override
	protected ItemStack getStackForBone(GeoBone bone, T animatable) {
		 if (bone.getName().equals("item_right")) {
			 return animatable.getRightHandItem();
		  }else if (bone.getName().equals("item_left")) {
			 return animatable.getLeftHandItem();
		 }else{
		return null;
		
		  }
	}
	
	@Override
	protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack, T animatable) {
		if (bone.getName().equals("item_right")) {
			  return ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
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
		poseStack.translate(0, 0, -0.1);
		poseStack.mulPose(Axis.XP.rotationDegrees(-120));
		poseStack.mulPose(Axis.YP.rotationDegrees(180));
		poseStack.mulPose(Axis.ZP.rotationDegrees(0));
	  }
	if (bone.getName().equals("item_left")) {
		poseStack.translate(0, -0.7, -0.6);
		poseStack.mulPose(Axis.XP.rotationDegrees(0));
		poseStack.mulPose(Axis.YP.rotationDegrees(0));
		poseStack.mulPose(Axis.ZP.rotationDegrees(0));


	}
	super.renderStackForBone(poseStack, bone, stack, animatable, bufferSource, partialTick, packedLight, packedOverlay);
}
	


}
