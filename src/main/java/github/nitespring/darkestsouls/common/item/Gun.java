package github.nitespring.darkestsouls.common.item;

import github.nitespring.darkestsouls.common.entity.projectile.throwable.FirebombEntity;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class Gun extends Item {

    private final float attackDamage;
    private final int useCooldown;
    private final int poiseDamage;
    private final int flyingTime;
    private final float flyingPower;
    private final float size;
    private final int ricochet;
    private final int pierce;

    public Gun(float damage, int cooldown, int poise, float size, float flyingPower, int flyingTime, int ricochet, int pierce, Properties properties) {
        super(properties);
        this.attackDamage = damage;
        this.useCooldown = cooldown;
        this.poiseDamage = poise;
        this.flyingPower = flyingPower;
        this.flyingTime = flyingTime;
        this.size = size;
        this.ricochet = ricochet;
        this.pierce = pierce;
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        return super.use(level, player, hand);
    }

    public float getAttackDamage(Player playerIn, ItemStack stackIn) {
        return attackDamage;
    }
    public int getUseCooldown(Player playerIn, ItemStack stackIn) {
        return useCooldown;
    }
    public int getPoiseDamage(Player playerIn, ItemStack stackIn) {
        return poiseDamage;
    }
    public float getBaseSize(){
        return size;
    }
    public float flyingPower(Player playerIn, ItemStack stackIn){
        return flyingPower;
    }
    public int getRicochet(Player playerIn, ItemStack stackIn) {
        return ricochet;
    }
    public int getPierce(Player playerIn, ItemStack stackIn) {
        return pierce;
    }

}
