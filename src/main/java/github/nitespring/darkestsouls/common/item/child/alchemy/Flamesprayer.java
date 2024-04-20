package github.nitespring.darkestsouls.common.item.child.alchemy;

import github.nitespring.darkestsouls.common.entity.projectile.weapon.Flame;
import github.nitespring.darkestsouls.common.item.AlchemyTool;
import github.nitespring.darkestsouls.core.init.EnchantmentInit;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class Flamesprayer extends AlchemyTool {
    private final int flyingTime;
    private final float flyingPower;
    private final float size;
    private final int ricochet;
    public Flamesprayer(float damage, int cooldown, int flyingTime, float flyingPower, int ricochet, float size,int poise, int ammoAmount, int durability, int enchantability, Properties properties) {
        super(damage, cooldown, poise, ammoAmount, durability, enchantability, properties);
        this.flyingPower = flyingPower;
        this.flyingTime = flyingTime;
        this.size = size;
        this.ricochet = ricochet;
    }

    public int getFlyingTime(ItemStack stackIn) {
        int enchantModifier=0;
        if(stackIn.isEnchanted()){
            //enchantModifier= EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.SHARPSHOOTER.get(), stackIn);
        }
        return (int) (flyingTime*(1+0.1* enchantModifier));
    }
    public float getBaseSize(){
        return size;
    }
    public float flyingPower(Player playerIn, ItemStack stackIn){
        int enchantModifier=0;
        if(stackIn.isEnchanted()){
            //enchantModifier=EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.SHARPSHOOTER.get(), stackIn);
        }
        return flyingPower+0.025f* enchantModifier;
    }
    public int getRicochet(@Nullable Player playerIn, ItemStack stackIn) {
        int enchantModifier=0;
        if(stackIn.isEnchanted()){
            //enchantModifier=EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.RICOCHET_SHOT.get(), stackIn);
        }
        return ricochet+enchantModifier;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.BLOCK;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        int ammoAmount = this.getAmmoAmount();
        if (this.hasAmmo(player, ammoAmount) || player.isCreative()) {
            player.startUsingItem(hand);
            return InteractionResultHolder.success(player.getItemInHand(hand));
        }else {
            return InteractionResultHolder.fail(player.getItemInHand(hand));
        }
    }

    @Override
    public void onUseTick(Level level, LivingEntity e, ItemStack stack, int useTick) {
        super.onUseTick(level, e, stack, useTick);
        if (e instanceof Player player) {
            int ammoAmount = this.getAmmoAmount();
            if (this.hasAmmo(player, ammoAmount) || player.isCreative()) {
                int startingTick = 25;
                if(useTick>=startingTick) {
                    if ((useTick-startingTick) % 7 == 0) {
                        this.shoot(player, level, stack);
                        if(player.getItemInHand(InteractionHand.MAIN_HAND)==stack) {
                            stack.hurtAndBreak(1, player, (p_43276_) -> {
                                p_43276_.broadcastBreakEvent(InteractionHand.MAIN_HAND);
                            });
                        }
                        if(player.getItemInHand(InteractionHand.OFF_HAND)==stack) {
                            stack.hurtAndBreak(1, player, (p_43276_) -> {
                                p_43276_.broadcastBreakEvent(InteractionHand.OFF_HAND);
                            });
                        }
                    }
                    if ((useTick-startingTick) % 18 == 0) {
                        if (!player.isCreative()) {
                            this.consumeAmmoApplyLuck(player, ammoAmount, this.getLuck(player, stack));
                        }
                    }
                }
            }else{
                player.stopUsingItem();
            }
        }
    }
    @Override
    public void shoot(Player player, Level level, ItemStack stackIn) {
        Vec3 pos = player.position();
        Vec3 aim = player.getLookAngle();
        double a=  5*Math.PI/18;


        for(int i = 0; i<=4; i++) {

            Random r = new Random();
            float rF = 2*(r.nextFloat()-0.5f);
            float b = (float) (a*rF);
            Vec3 aim1 = new Vec3((aim.x*Math.cos(b)-aim.z*Math.sin(b)),aim.y,(aim.z*Math.cos(b)+aim.x*Math.sin(b)));
            float x = (float) (pos.x + 0.4 * aim.x+ 0.4 * aim1.x);
            float y = (float) (pos.y + 0.8 + 0.6 * aim1.y);
            float z = (float) (pos.z + 0.4 * aim.z+ 0.4 * aim1.z);
            Flame entity = new Flame(EntityInit.FLAME.get(), level);
            entity.setPos(x, y, z);
            float flyingPower = this.flyingPower(player, stackIn);
            //float flyingPower = 0.1f;
            entity.xPower = flyingPower * aim1.x;
            entity.yPower = flyingPower * aim1.y;
            entity.zPower = flyingPower * aim1.z;
            entity.setOwner(player);
            entity.setAttackDamage(this.getAttackDamage(player, stackIn));
            entity.setPoiseDamage(this.getPoiseDamage(player, stackIn));
            //entity.setFlyingTime(10);
            entity.setFlyingTime(this.getFlyingTime(stackIn));
            entity.setSize(this.getBaseSize());
            //entity.setViewScale(this.getBaseSize());
            //entity.setSize(1.0f);
            //entity.setViewScale(0.8f);
            //entity.setRicochet(1);
            entity.setRicochet(this.getRicochet(player, stackIn));
            level.addFreshEntity(entity);
        }


        player.level().playSound((Player) null, player, SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.0f);
    }
    @Override
    public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
        super.onStopUsing(stack, entity, count);
        if(entity instanceof Player player) {
            player.getCooldowns().addCooldown(stack.getItem(), this.getUseCooldown(player, stack));
        }

    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {

        tooltip.add(Component.literal("+").append(Component.literal(""+this.getAttackDamage(null,stack))).append(Component.translatable("translation.darkestsouls.fire_damage")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.RED));

        super.appendHoverText(stack, level, tooltip, flag);
    }
}
