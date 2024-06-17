package github.nitespring.darkestsouls.common.item.child.weapons;

import github.nitespring.darkestsouls.common.entity.projectile.weapon.melee.FrayedBladeAttackEntity;
import github.nitespring.darkestsouls.common.entity.projectile.weapon.melee.FrayedBladeFlameEntity;
import github.nitespring.darkestsouls.common.item.Weapon;
import github.nitespring.darkestsouls.core.init.EntityInit;
import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FrayedBlade extends Weapon {


    public FrayedBlade(Tier tier, float attack, float speed, float reach, float knockback, int poise, int blood, int poison, int frost, int rot, int death, int fire, int holy,int serrated, int durability, int enchantability, float movementSpeed, int maxTargets, Properties properties) {
        super(tier, attack, speed, reach, knockback, poise, blood, poison, frost, rot, death, fire, holy, serrated, durability, enchantability, movementSpeed, maxTargets, properties);
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
        if(!playerIn.isUsingItem()) {
            Vec3 pos = playerIn.position().add(playerIn.getLookAngle().x() * 1.5, 0.4, playerIn.getLookAngle().z() * 1.5);

            Level levelIn = playerIn.level();
            FrayedBladeAttackEntity entity = new FrayedBladeAttackEntity(EntityInit.FRAYED_BLADE.get(), levelIn, pos, (float) Mth.atan2(pos.z - playerIn.getZ(), pos.x - playerIn.getX()));
            entity.setOwner(playerIn);
            entity.setItemStack(stackIn);
            entity.setMaxTargets(this.getMaxTargets(playerIn,stackIn));
            entity.setDamage(
                    this.getAttackDamage(playerIn, stackIn) - 4,
                    this.getPoiseDamage(playerIn, stackIn) - 7,
                    this.getFireAttack(playerIn,stackIn),
                    this.getSmiteAttack(playerIn,stackIn),
                    this.getBaneOfArthropodsAttack(playerIn,stackIn),
                    this.getBeastHunterAttack(playerIn,stackIn),
                    this.getBloodAttack(playerIn,stackIn) - 2,
                    this.getPoisonAttack(playerIn,stackIn),
                    this.getToxicAttack(playerIn,stackIn),
                    this.getRotAttack(playerIn,stackIn),
                    this.getFrostAttack(playerIn,stackIn),
                    this.getWitherAttack(playerIn,stackIn));
            entity.setHitboxModifications(1.2f, 0f, 0.4f, 1.5f);
            entity.configureTicks(14, 22, 1, 2);
            levelIn.addFreshEntity(entity);
        }

    }
    @Override
    public void doRightClickAction(Player playerIn, ItemStack stackIn) {


        if(!playerIn.level().isClientSide()) {
            Vec3 posTarget = playerIn.position().add(playerIn.getLookAngle().scale(15.0));
            double d0 = Math.min(posTarget.y, playerIn.getY());
            double d1 = Math.max(posTarget.y, playerIn.getY()) + 1.0D;
            float f = (float) Mth.atan2(posTarget.z - playerIn.getZ(), posTarget.x - playerIn.getX());
            for (int l = 0; l < 18; ++l) {
                double d2 = 0.5D * (double) (l + 1);
                int j = l / 2;
                this.createSpellEntity(playerIn, playerIn.getX() + (double) Mth.cos(f) * d2, playerIn.getZ() + (double) Mth.sin(f) * d2, d0, d1, f, j, stackIn);
            }
        }
        if(stackIn == playerIn.getItemInHand(InteractionHand.MAIN_HAND)) {stackIn.hurtAndBreak(5, playerIn, EquipmentSlot.MAINHAND);}
        if(stackIn == playerIn.getItemInHand(InteractionHand.OFF_HAND)) {stackIn.hurtAndBreak(5, playerIn, EquipmentSlot.OFFHAND);}

    }

    private void createSpellEntity(Player playerIn, double p_32673_, double p_32674_, double p_32675_, double p_32676_, float p_32677_, int p_32678_, ItemStack stackIn) {
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
            FrayedBladeFlameEntity e = new FrayedBladeFlameEntity(playerIn.level(), this.getAttackDamage(playerIn,stackIn), p_32673_, (double)blockpos.getY() + d0, p_32674_, p_32677_, p_32678_, playerIn);
            playerIn.level().addFreshEntity(e);




        }

    }






}
