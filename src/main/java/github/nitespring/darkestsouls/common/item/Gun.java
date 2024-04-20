package github.nitespring.darkestsouls.common.item;

import github.nitespring.darkestsouls.common.entity.projectile.throwable.FirebombEntity;
import github.nitespring.darkestsouls.core.init.EnchantmentInit;
import github.nitespring.darkestsouls.core.init.EntityInit;
import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class Gun extends Item implements IAmmoConsumingItem,ILeftClickItem {

    private final float attackDamage;
    private final int useCooldown;
    private final int poiseDamage;
    private final int flyingTime;
    private final float flyingPower;
    private final float size;
    private final int ricochet;
    private final int pierce;
    private final int ammoAmount;
    private final int durability;
    private final int enchantability;

    public Gun(float damage, int cooldown, int poise, float size, float flyingPower, int flyingTime, int ricochet, int pierce, int ammoAmount, int durability, int enchantability, Properties properties) {
        super(properties);
        this.attackDamage = damage;
        this.useCooldown = cooldown;
        this.poiseDamage = poise;
        this.flyingPower = flyingPower;
        this.flyingTime = flyingTime;
        this.size = size;
        this.ricochet = ricochet;
        this.pierce = pierce;
        this.ammoAmount=ammoAmount;
        this.durability=durability;
        this.enchantability=enchantability;
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(hand== InteractionHand.OFF_HAND) {
            ItemStack stackIn = player.getItemInHand(hand);
            int ammoAmount = this.getAmmoAmount();
            if (this.hasAmmo(player, ammoAmount) || player.isCreative()) {
                this.shoot(player, level, stackIn);
                stackIn.hurtAndBreak(1, player, (p_43276_) -> {
                    p_43276_.broadcastBreakEvent(InteractionHand.OFF_HAND);
                });
                if (!player.isCreative()) {
                    this.consumeAmmoApplyLuck(player, ammoAmount, this.getLuck(player, stackIn));
                }
                return InteractionResultHolder.success(stackIn);
            } else {
                return InteractionResultHolder.fail(stackIn);
            }
        }else {
            if(player.getItemInHand(InteractionHand.OFF_HAND)==ItemStack.EMPTY) {
                player.startUsingItem(hand);
            }
            return super.use(level, player, hand);
        }
    }
    public float getAttackDamage(@Nullable Player playerIn, ItemStack stackIn) {
        int flatEnchantModifier=0;
        int percentEnchantModifier=0;
        if(stackIn.isEnchanted()){
            flatEnchantModifier = EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.FIREPOWER.get(), stackIn);
            percentEnchantModifier = EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.GREATER_FIREPOWER.get(), stackIn);

        }
        return (attackDamage+2*flatEnchantModifier)*(1+0.2f*percentEnchantModifier);

    }
    public int getUseCooldown(@Nullable Player playerIn, ItemStack stackIn) {
        int enchantModifier=0;
        if(stackIn.isEnchanted()){
            enchantModifier = EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.GUNSLINGER.get(), stackIn);
        }
        return (int) (useCooldown*(1-0.1*enchantModifier));
    }
    public int getPoiseDamage(Player playerIn, ItemStack stackIn) {
        return poiseDamage;
    }
    public int getFlyingTime(ItemStack stackIn) {
        int enchantModifier=0;
        if(stackIn.isEnchanted()){
            enchantModifier=EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.SHARPSHOOTER.get(), stackIn);
        }
        return (int) (flyingTime*(1+0.1* enchantModifier));

    }
    public float getBaseSize(){
        return size;
    }
    public float flyingPower(Player playerIn, ItemStack stackIn){
        int enchantModifier=0;
        if(stackIn.isEnchanted()){
            enchantModifier=EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.SHARPSHOOTER.get(), stackIn);
        }
        return flyingPower+0.025f* enchantModifier;
    }
    public int getRicochet(@Nullable Player playerIn, ItemStack stackIn) {
        int enchantModifier=0;
        if(stackIn.isEnchanted()){
            enchantModifier=EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.RICOCHET_SHOT.get(), stackIn);
        }
        return ricochet+enchantModifier;
    }
    public int getPierce(@Nullable Player playerIn, ItemStack stackIn) {
        int enchantModifier=0;
        if(stackIn.isEnchanted()){
            enchantModifier=EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.PIERCING_SHOT.get(), stackIn);
        }
        return pierce+enchantModifier;
    }
    public int getPoison(@Nullable Player playerIn, ItemStack stackIn) {
        int enchantModifier=0;
        if(stackIn.isEnchanted()){
            enchantModifier=EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.OPHIDIAN_BITE.get(), stackIn);
        }
        return enchantModifier;
    }
    public int getBlood(@Nullable Player playerIn, ItemStack stackIn) {
        int enchantModifier=0;
        if(stackIn.isEnchanted()){
            enchantModifier=EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.EXPANDING_SHOT.get(), stackIn);
        }
        return enchantModifier;
    }
    public float getLuck(@Nullable Player playerIn, ItemStack stackIn) {
        int enchantModifier=0;
        if(stackIn.isEnchanted()){
            enchantModifier=EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.MISER_SOUL.get(), stackIn);
        }
        return 0.1f*enchantModifier;
    }
    public boolean isFire(Player playerIn, ItemStack stackIn) {
        int enchantModifier=0;
        if(stackIn.isEnchanted()){
            enchantModifier=EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.FLAMING_SHOT.get(), stackIn);
        }
        return enchantModifier>0;
    }
    public boolean isLightning(Player playerIn, ItemStack stackIn) {
        int enchantModifier=0;
        if(stackIn.isEnchanted()){
            enchantModifier=EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.CHILD_OF_THUNDER.get(), stackIn);
        }
        return enchantModifier>0;
    }
    public int getExplosion(ItemStack stackIn) {
        int enchantModifier=0;
        if(stackIn.isEnchanted()){
            enchantModifier=EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.EXPLODING_SHOT.get(), stackIn);
        }
        return enchantModifier;
    }
    public int getAmmoAmount() {
        return ammoAmount;
    }

    @Override
    public Predicate<ItemStack> getAmmoType() {
        return (p_43015_) -> {
            return p_43015_.is(ItemInit.QUICKSILVER_BULLET.get());
        };
    }

    public void shoot(Player player, Level level, ItemStack stackIn) {
    }
    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }
    @Override
    public int getMaxDamage(ItemStack stack) {
        return durability;
    }

    @Override
    public void doLeftClickAction(Player player, ItemStack stackIn) {
        int ammoAmount = this.getAmmoAmount();
        if (!player.getCooldowns().isOnCooldown(this)&&(this.hasAmmo(player, ammoAmount) || player.isCreative())) {
            this.shoot(player, player.level(), stackIn);
            stackIn.hurtAndBreak(1, player, (p_43276_) -> {
                p_43276_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
            if (!player.isCreative()) {
                this.consumeAmmoApplyLuck(player, ammoAmount, this.getLuck(player, stackIn));
            }
        }
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        return true;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 40000;
    }

    @Override
    public boolean canAttackBlock(BlockState p_43291_, Level p_43292_, BlockPos p_43293_, Player p_43294_) {
        return !p_43294_.isCreative();
    }

    @Override
    public boolean isDamageable(ItemStack stack) {

        return true;
    }

    @Override
    public int getEnchantmentValue(ItemStack stack) {
        return this.enchantability;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment.category == EnchantmentInit.GUN || enchantment.category == EnchantmentInit.AMMO_CONSUMER || enchantment.category == EnchantmentCategory.BREAKABLE || enchantment.category == EnchantmentCategory.VANISHABLE;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return super.isBookEnchantable(stack, book);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {



        tooltip.add(Component.literal("+").append(Component.literal(""+this.getAttackDamage(null,stack))).append(Component.translatable("translation.darkestsouls.damage")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.GRAY));

        if(this.getBlood(null,stack)>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+this.getBlood(null,stack))).append(Component.translatable("translation.darkestsouls.blood")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_RED));
        }
        if(this.getPoison(null,stack)>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+this.getPoison(null,stack))).append(Component.translatable("translation.darkestsouls.poison")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GREEN));
        }
        if(this.getRicochet(null,stack)>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+this.getRicochet(null,stack))).append(Component.translatable("translation.darkestsouls.ricochet")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
        }
        if(this.getPierce(null,stack)>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+this.getPierce(null,stack))).append(Component.translatable("translation.darkestsouls.pierce")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
        }
        if(this.getLuck(null,stack)>0) {
            tooltip.add(Component.literal("+").append(Component.literal(""+(int)(this.getLuck(null,stack)*100))).append(Component.literal("%")).append(Component.translatable("translation.darkestsouls.luck")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
        }
        if(stack.isEnchanted()){
            int i=EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.GUNSLINGER.get(), stack);
            if(i>=1) {
                tooltip.add(Component.literal("+").append(Component.literal("" + i * 10)).append(Component.literal("%")).append(Component.translatable("translation.darkestsouls.cooldown")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
            }
        }
        if(this.getAmmoAmount()==1) {
            tooltip.add(Component.translatable("translation.darkestsouls.require_bullet").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
        }else{
            tooltip.add(Component.translatable("translation.darkestsouls.require").append(Component.literal(" " + this.getAmmoAmount())).append(Component.translatable("translation.darkestsouls.bullets")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
        }
        super.appendHoverText(stack, level, tooltip, flag);
    }
}
