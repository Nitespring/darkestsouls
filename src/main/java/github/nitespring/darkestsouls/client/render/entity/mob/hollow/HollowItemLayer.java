package github.nitespring.darkestsouls.client.render.entity.mob.hollow;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import github.nitespring.darkestsouls.common.entity.mob.hollow.Hollow;
import github.nitespring.darkestsouls.common.entity.mob.hollow.HollowSoldierLongsword;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.Skeleton;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.SkeletonCurvedSwords;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.SkeletonFalchion;
import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;

public class HollowItemLayer<T extends Hollow & GeoEntity> extends BlockAndItemGeoLayer<T>{

	public HollowItemLayer(GeoRenderer<T> renderer) {
		super(renderer);
		
	}
	
	

	@Override
	protected ItemStack getStackForBone(GeoBone bone, T animatable) {
		 if (bone.getName().equals("right_item")) {
			 if(animatable instanceof HollowSoldierLongsword) {
				 return ItemInit.LONGSWORD.get().getDefaultInstance();
			 }else{
				 return null;
			 }
		 }else{
		return null;
		
		  }
	}
	
	@Override
	protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack, T animatable) {
		if (bone.getName().equals("right_item")) {
			  return ItemDisplayContext.THIRD_PERSON_LEFT_HAND;
		  }else if (bone.getName().equals("left_item")) {
			return ItemDisplayContext.THIRD_PERSON_LEFT_HAND;
		}else {
			return null;
		}

	}
	
	



@Override
protected void renderStackForBone(PoseStack poseStack, GeoBone bone, ItemStack stack, T animatable,
		MultiBufferSource bufferSource, float partialTick, int packedLight, int packedOverlay) {
	poseStack.scale(0.9f,0.9f,0.9f);
	if (bone.getName().equals("right_item")) {
		poseStack.translate(0.00, 0.6, -0.5);
		poseStack.mulPose(Axis.XP.rotationDegrees(0));
		poseStack.mulPose(Axis.YP.rotationDegrees(0));
		poseStack.mulPose(Axis.ZP.rotationDegrees(180));
      
	  }
	if (bone.getName().equals("left_item")) {
		poseStack.translate(0.05, 0.76, -0.71);
		poseStack.mulPose(Axis.XP.rotationDegrees(0));
		poseStack.mulPose(Axis.YP.rotationDegrees(-15));
		poseStack.mulPose(Axis.ZP.rotationDegrees(180));


	}
	super.renderStackForBone(poseStack, bone, stack, animatable, bufferSource, partialTick, packedLight, packedOverlay);
}
	


}
