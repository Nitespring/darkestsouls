package github.nitespring.darkestsouls.common.item.throwing;

import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import github.nitespring.darkestsouls.common.entity.projectile.throwable.ThrowingKnifeEntity;
import net.minecraft.world.phys.Vec3;

public class ThrowingKnife extends Item {
    public ThrowingKnife(Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level levelIn, Player playerIn, InteractionHand handIn) {
        ItemStack stackIn = playerIn.getItemInHand(handIn);
        Vec3 pos = playerIn.position();
        Vec3 aim = playerIn.getLookAngle();


        float x = (float) (pos.x + 0.6 * aim.x);
        float y = (float) (pos.y + 1.4 + 0.6 * aim.y);
        float z = (float) (pos.z + 0.6 * aim.z);

        ThrowingKnifeEntity entity = new ThrowingKnifeEntity(EntityInit.THROWING_KNIFE.get(), x, y, z, stackIn.copy(),0.4f, levelIn);
        double d0 = aim.horizontalDistance();
        float roty = (float) (Mth.atan2(aim.x, aim.z) * (double) (180F / (float) Math.PI));
        float rotx = (float) (Mth.atan2(aim.y, d0) * (double) (180F / (float) Math.PI));
        entity.setXRot(rotx);
        entity.setYRot(roty);
        //entity.shootFromRotation(playerIn, (float) (0.25f*aim.x), (float) (0.25f*aim.y), (float) (0.25f*aim.z), rotx, roty);
        entity.xPower=0.225f*aim.x;
        entity.yPower=0.25f*aim.y;
        entity.zPower=0.25f*aim.z;
        entity.shouldRotate=true;
        levelIn.addFreshEntity(entity);


      return InteractionResultHolder.success(stackIn);

    }
}
