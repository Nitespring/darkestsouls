package github.nitespring.darkestsouls.common.item.child.staves;

import github.nitespring.darkestsouls.common.entity.projectile.spell.SoulDart;
import github.nitespring.darkestsouls.common.item.Staff;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SorcererStaff extends Staff {
    public SorcererStaff(float attackDamage, int durability, Properties properties) {
        super(attackDamage, durability, properties);
    }

    @Override
    public void doSpellA(Player playerIn, ItemStack stackIn, InteractionHand HandIn) {
        if(!playerIn.getCooldowns().isOnCooldown(this)) {
            Level levelIn = playerIn.level();
            Vec3 aim = playerIn.getLookAngle();
            Vec3 pos = playerIn.position();
            Vec3 mov = playerIn.getDeltaMovement();

            SoulDart e = new SoulDart(EntityInit.SOUL_DART.get(), levelIn);
            e.setDamage(this.getAttackDamage(playerIn, stackIn));
            e.setDimensionScale(0.1f);
            e.setMaxLifeTime(12);
            e.setPos(pos.add(0, 1.5, 0).add(aim.normalize().multiply(1.5f, 1.5f, 1.5f)));
            e.xPower = 0.25 * aim.x * (1 + (playerIn.getRandom().nextFloat() - 0.5) * 0.25);
            e.yPower = 0.25 * aim.y;
            e.zPower = 0.25 * aim.z * (1 + (playerIn.getRandom().nextFloat() - 0.5) * 0.25);

            levelIn.addFreshEntity(e);
            playerIn.getCooldowns().addCooldown(this, 5);
        }

    }


    public void doSpellB(Player playerIn, ItemStack stackIn, InteractionHand handIn, int i) {
        Level levelIn = playerIn.level();
        Vec3 aim = playerIn.getLookAngle();
        Vec3 pos = playerIn.position();
        Vec3 mov = playerIn.getDeltaMovement();

        SoulDart e = new SoulDart(EntityInit.SOUL_DART.get(), levelIn);

        e.setPos(pos.add(0,1.5,0).add(aim.normalize().multiply(1.5f,1.5f,1.5f)));

        if(i<getCastingDurationSpellB()) {
            e.setDamage(this.getAttackDamage(playerIn, stackIn) * 2 * (i / getCastingDurationSpellB()));
            e.setMaxLifeTime(10 + 30*i/getCastingDurationSpellB());
            e.setDimensionScale((float) (0.12+0.03*i/getCastingDurationSpellB()));
            e.xPower = 0.1 * aim.x * (1 + (playerIn.getRandom().nextFloat() - 0.5) * 0.05) * (1 + 0.03 * i / getCastingDurationSpellB());
            e.yPower = 0.1 * aim.y * (1 + 0.1 * i / getCastingDurationSpellB());
            e.zPower = 0.1 * aim.z * (1 + (playerIn.getRandom().nextFloat() - 0.5) * 0.05) * (1 + 0.03 * i / getCastingDurationSpellB());
        }else{
            e.setDamage(this.getAttackDamage(playerIn, stackIn) * 2.5f);
            e.setMaxLifeTime(50);
            e.setDimensionScale(0.20f);
            e.xPower = 0.1 * aim.x * (1 + (playerIn.getRandom().nextFloat() - 0.5) * 0.05) * (1 + 0.06);
            e.yPower = 0.1 * aim.y * (1 + 0.12);
            e.zPower = 0.1 * aim.z * (1 + (playerIn.getRandom().nextFloat() - 0.5) * 0.05) * (1 + 0.06);
        }

        levelIn.addFreshEntity(e);
        playerIn.getCooldowns().addCooldown(this, 15);
    }

    public int getCastingDurationSpellB(){return 40;}

    @Override
    public InteractionResultHolder<ItemStack> use(Level levelIn, Player playerIn, InteractionHand handIn) {
        playerIn.startUsingItem(handIn);
        return InteractionResultHolder.pass(playerIn.getItemInHand(handIn));
    }

    @Override
    public boolean useOnRelease(ItemStack stackIn) {
        return super.useOnRelease(stackIn);
    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 72000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.BOW;
    }

    @Override
    public void onUseTick(Level levelIn, LivingEntity entityIn, ItemStack stackIn, int i) {
        super.onUseTick(levelIn, entityIn, stackIn, i);
    }

    @Override
    public void releaseUsing(ItemStack stackIn, Level levelIn, LivingEntity entityIn, int i) {
        super.releaseUsing(stackIn, levelIn, entityIn, i);
        entityIn.stopUsingItem();
        this.doSpellB((Player)entityIn, stackIn,((Player)entityIn).getUsedItemHand(), i);
    }


    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        return true;
    }
}
