package github.nitespring.darkestsouls.common.item;

import github.nitespring.darkestsouls.common.entity.projectile.FrayedBladeAttackEntity;
import github.nitespring.darkestsouls.common.entity.projectile.FrayedBladeFlameEntity;
import github.nitespring.darkestsouls.core.init.EntityInit;
import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class FrayedBlade extends Weapon{


    public FrayedBlade(Tier tier, int attack, float speed, Properties properties) {
        super(tier, attack, speed, properties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player playerIn, InteractionHand handIn) {
        playerIn.swing(handIn);
        playerIn.getCooldowns().addCooldown(ItemInit.FRAYED_BLADE.get(), 40);
        playerIn.level().playLocalSound(playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.BLAZE_SHOOT, playerIn.getSoundSource(), 1.0F, playerIn.getRandom().nextFloat() * 0.2F + 0.85F, false);
        return super.use(p_41432_, playerIn, handIn);
    }

    @Override
    public void doLeftClickAction(Player playerIn, ItemStack stackIn) {

        Vec3 pos = playerIn.position().add(playerIn.getLookAngle().x()*1.5, 0.4, playerIn.getLookAngle().z()*1.5);

        Level levelIn = playerIn.level();
        FrayedBladeAttackEntity entity = new FrayedBladeAttackEntity(EntityInit.FRAYED_BLADE.get(),
                levelIn,
                pos,
                3.0f,
                (float) Mth.atan2(pos.z - playerIn.getZ(), pos.x - playerIn.getX()));
        entity.setOwner(playerIn);
        levelIn.addFreshEntity(entity);

    }
    @Override
    public void doRightClickAction(Player playerIn, ItemStack item) {



        Vec3 posTarget = playerIn.position().add(playerIn.getLookAngle().scale(15.0));
        double d0 = Math.min(posTarget.y, playerIn.getY());
        double d1 = Math.max(posTarget.y, playerIn.getY()) + 1.0D;
        float f = (float)Mth.atan2(posTarget.z - playerIn.getZ(), posTarget.x - playerIn.getX());
        for(int l = 0; l < 18; ++l) {
            double d2 = 0.5D * (double)(l + 1);
            int j = l/2;
            this.createSpellEntity(playerIn, playerIn.getX() + (double)Mth.cos(f) * d2, playerIn.getZ() + (double)Mth.sin(f) * d2, d0, d1, f, j);
        }

    }

    private void createSpellEntity(Player playerIn, double p_32673_, double p_32674_, double p_32675_, double p_32676_, float p_32677_, int p_32678_) {
        BlockPos blockpos = new BlockPos((int)p_32673_, (int)p_32676_, (int)p_32674_);
        boolean flag = false;
        double d0 = 0.0D;

        do {
            BlockPos blockpos1 = blockpos.below();
            BlockState blockstate = playerIn.level().getBlockState(blockpos1);
            if (blockstate.isFaceSturdy(playerIn.level(), blockpos1, Direction.UP)) {
                if (!playerIn.level().isEmptyBlock(blockpos)) {
                    BlockState blockstate1 = playerIn.level().getBlockState(blockpos);
                    VoxelShape voxelshape = blockstate1.getCollisionShape(playerIn.level(), blockpos);
                    if (!voxelshape.isEmpty()) {
                        d0 = voxelshape.max(Direction.Axis.Y);
                    }
                }

                flag = true;
                break;
            }

            blockpos = blockpos.below();
        } while(blockpos.getY() >= Mth.floor(p_32675_) - 1);

        if (flag) {
            FrayedBladeFlameEntity e = new FrayedBladeFlameEntity(playerIn.level(), 4.0f, p_32673_, (double)blockpos.getY() + d0, p_32674_, p_32677_, p_32678_, playerIn);
            playerIn.level().addFreshEntity(e);




        }

    }






}
