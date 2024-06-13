package github.nitespring.darkestsouls.common.item.throwing;

import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import github.nitespring.darkestsouls.common.entity.projectile.throwable.ThrowingKnifeEntity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ThrowingKnife extends Item {

    private final float attackDamage;
    private final int useCooldown;
    private final int bloodDamage;
    private final int poisonDamage;
    private final int poiseDamage;
    private final float flyingPower;
    private final float gravPower;
    private final boolean shouldRotate;
    private final int type;



    public ThrowingKnife(float attackDamage, int useCooldown, int bloodDamage, int poisonDamage, int poiseDamage, float flyingPower, float gravPower, boolean shouldRotate, int type, Properties properties) {
        super(properties);
        this.attackDamage=attackDamage;
        this.useCooldown=useCooldown;
        this.bloodDamage=bloodDamage;
        this.poisonDamage=poisonDamage;
        this.poiseDamage=poiseDamage;
        this.flyingPower=flyingPower;
        this.gravPower=gravPower;
        this.shouldRotate=shouldRotate;
        this.type=type;
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level levelIn, Player playerIn, InteractionHand handIn) {
        ItemStack stackIn = playerIn.getItemInHand(handIn);
        Vec3 pos = playerIn.position();
        Vec3 aim = playerIn.getLookAngle();
        if(!levelIn.isClientSide()) {

            float x = (float) (pos.x + 0.6 * aim.x);
            float y = (float) (pos.y + 1.4 + 0.6 * aim.y);
            float z = (float) (pos.z + 0.6 * aim.z);

            ThrowingKnifeEntity entity = new ThrowingKnifeEntity(EntityInit.THROWING_KNIFE.get(), x, y, z, stackIn.copyWithCount(1), 0.4f, levelIn);
            double d0 = aim.horizontalDistance();
            float roty = (float) (Mth.atan2(aim.x, aim.z) * (double) (180F / (float) Math.PI));
            float rotx = (float) (Mth.atan2(aim.y, d0) * (double) (180F / (float) Math.PI));
            entity.setXRot(rotx);
            entity.setYRot(roty);
            //entity.shootFromRotation(playerIn, (float) (0.25f*aim.x), (float) (0.25f*aim.y), (float) (0.25f*aim.z), rotx, roty);
            entity.xPower = flyingPower * aim.x;
            entity.yPower = flyingPower * aim.y;
            entity.zPower = flyingPower * aim.z;
            entity.setOwner(playerIn);
            //entity.setZTilt(90*type);
            //entity.setToRotate(true);
            entity.setToPickUp(true);
            entity.setAttackPower(this.attackDamage);
            entity.setPoisonDamage(this.poisonDamage);
            entity.setBloodDamage(this.bloodDamage);
            entity.setPoiseDamage(this.poiseDamage);
            entity.setGravPower(this.gravPower);
            levelIn.addFreshEntity(entity);
            entity.setToRotate(shouldRotate);
        }
        playerIn.getCooldowns().addCooldown(stackIn.getItem(), useCooldown);
        if(!playerIn.isCreative()){
            stackIn.shrink(1);
        }

      return InteractionResultHolder.success(stackIn);

    }


    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("+").append(Component.literal(""+this.attackDamage)).append(Component.translatable("translation.darkestsouls.damage")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
        if(this.bloodDamage>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+this.bloodDamage)).append(Component.translatable("translation.darkestsouls.blood")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_RED));
        }
        if(this.poisonDamage>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+this.poisonDamage)).append(Component.translatable("translation.darkestsouls.poison")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GREEN));
        }
    }
}
