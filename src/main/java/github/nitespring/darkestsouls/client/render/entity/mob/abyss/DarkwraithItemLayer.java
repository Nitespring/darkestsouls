package github.nitespring.darkestsouls.client.render.entity.mob.abyss;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import github.nitespring.darkestsouls.common.entity.mob.abyss.Darkwraith;
import github.nitespring.darkestsouls.common.entity.mob.church.ChurchDoctor;
import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;

public class DarkwraithItemLayer<T extends Darkwraith & GeoEntity> extends BlockAndItemGeoLayer<T>{

	public DarkwraithItemLayer(GeoRenderer<T> renderer) {
		super(renderer);
		
	}
	
	

	@Override
	protected ItemStack getStackForBone(GeoBone bone, T animatable) {
		 if (bone.getName().equals("right_item")) {
				 return ItemInit.DARKSWORD.get().getDefaultInstance();
		 }else{
				 return null;
			 }
		 }
	
	@Override
	protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack, T animatable) {
		if (bone.getName().equals("right_item")) {
			  return ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
		  }else{
			return null;
		}
	}
	
	



@Override
protected void renderStackForBone(PoseStack poseStack, GeoBone bone, ItemStack stack, T animatable,
		MultiBufferSource bufferSource, float partialTick, int packedLight, int packedOverlay) {
		poseStack.pushPose();
	if (bone.getName().equals("right_item")) {

		poseStack.translate(0, -0.06, -0.1);
		poseStack.mulPose(Axis.XP.rotationDegrees(-90));
		poseStack.mulPose(Axis.YP.rotationDegrees(0));
		poseStack.mulPose(Axis.ZP.rotationDegrees(0));
		poseStack.scale(0.8f,0.8f,0.8f);

	}
	super.renderStackForBone(poseStack, bone, stack, animatable, bufferSource, partialTick, packedLight, packedOverlay);
	poseStack.popPose();
}
	


}
