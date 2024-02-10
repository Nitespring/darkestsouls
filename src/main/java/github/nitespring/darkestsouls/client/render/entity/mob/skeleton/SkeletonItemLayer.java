package github.nitespring.darkestsouls.client.render.entity.mob.skeleton;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.Skeleton;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.SkeletonFalchion;
import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.EquipmentSlot;
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
			 if(animatable instanceof SkeletonFalchion) {
				 return ItemInit.FALCHION.get().getDefaultInstance();
			 }else {
				 return null;
			 }
		  }else {
		return null;
		
		  }
	}
	
	@Override
	protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack, T animatable) {
		if (bone.getName().equals("item_right")) {
			  return ItemDisplayContext.THIRD_PERSON_LEFT_HAND;
		  }else {
			return null;
		}
	}
	
	



@Override
protected void renderStackForBone(PoseStack poseStack, GeoBone bone, ItemStack stack, T animatable,
		MultiBufferSource bufferSource, float partialTick, int packedLight, int packedOverlay) {
	if (bone.getName().equals("item_right")) {
		poseStack.translate(0, 0.6, -0.5);
		poseStack.mulPose(Axis.XP.rotationDegrees(0)); 
		poseStack.mulPose(Axis.YP.rotationDegrees(0)); 
		poseStack.mulPose(Axis.ZP.rotationDegrees(180));
   
      
	  }
	super.renderStackForBone(poseStack, bone, stack, animatable, bufferSource, partialTick, packedLight, packedOverlay);
}
	


}
