package github.nitespring.darkestsouls.common.item.throwing;

import github.nitespring.darkestsouls.common.entity.projectile.throwable.FirebombEntity;
import github.nitespring.darkestsouls.common.entity.projectile.throwable.ThrowingKnifeEntity;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class Firebomb extends Item {
    private final float attackDamage;
    private final int useCooldown;
    private final int poiseDamage;
    private final int type;
    public Firebomb(float attackDamage, int useCooldown, int poiseDamage, int type,Properties properties) {
        super(properties);
        this.attackDamage=attackDamage;
        this.useCooldown=useCooldown;
        this.poiseDamage=poiseDamage;
        this.type=type;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level levelIn, Player playerIn, InteractionHand handIn) {
        ItemStack stackIn = playerIn.getItemInHand(handIn);
        Vec3 pos = playerIn.position();
        Vec3 aim = playerIn.getLookAngle();


        float x = (float) (pos.x + 0.6 * aim.x);
        float y = (float) (pos.y + 1.4 + 0.6 * aim.y);
        float z = (float) (pos.z + 0.6 * aim.z);
        FirebombEntity entity = new FirebombEntity(EntityInit.FIREBOMB.get(), levelIn);
        entity.setPos(x,y,z);
        float flyingPower = 0.25f;
        entity.xPower=flyingPower*aim.x;
        entity.yPower=flyingPower*aim.y;
        entity.zPower=flyingPower*aim.z;
        entity.setOwner(playerIn);
        entity.setAttackDamage(this.attackDamage);
        entity.setPoiseDamage(this.poiseDamage);
        entity.setGravPower(0.0012f);
        entity.setHorizontalSpread(1.25);
        entity.setVerticalSpread(1.25);
        levelIn.addFreshEntity(entity);

        playerIn.getCooldowns().addCooldown(stackIn.getItem(), useCooldown);
        if(!playerIn.isCreative()){
            stackIn.shrink(1);
        }
        return InteractionResultHolder.success(stackIn);
    }
}
