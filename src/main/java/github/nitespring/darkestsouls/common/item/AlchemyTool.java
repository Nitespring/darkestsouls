package github.nitespring.darkestsouls.common.item;

import github.nitespring.darkestsouls.core.init.EnchantmentInit;
import github.nitespring.darkestsouls.core.init.ItemInit;
import github.nitespring.darkestsouls.core.util.CustomItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class AlchemyTool extends Item implements IAmmoConsumingItem{
    private final float attackDamage;
    private final int useCooldown;
    private final int poiseDamage;
    private final int ammoAmount;
    private final int durability;
    private final int enchantability;
    public AlchemyTool(float damage, int cooldown, int poise, int ammoAmount, int durability, int enchantability, Properties properties) {
        super(properties);
        this.attackDamage = damage;
        this.useCooldown = cooldown;
        this.poiseDamage = poise;
        this.ammoAmount=ammoAmount;
        this.durability=durability;
        this.enchantability=enchantability;
    }
    public float getAttackDamage(@Nullable Player playerIn, ItemStack stackIn) {
        return attackDamage* (1 + 0.2f * stackIn.getEnchantmentLevel(EnchantmentInit.MOON_BLESSING.get()))
                + 2.0f * stackIn.getEnchantmentLevel(EnchantmentInit.STARPOWER.get());

    }
    public int getUseCooldown(@Nullable Player playerIn, ItemStack stackIn) {
        int enchantModifier=0;
        if(stackIn.isEnchanted()){
            //enchantModifier = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentInit.GUNSLINGER.get(), stackIn);
        }
        return (int) (useCooldown*(1-0.1*enchantModifier));
    }
    public int getPoiseDamage(Player playerIn, ItemStack stackIn) {
        return poiseDamage;
    }
    public float getLuck(@Nullable Player playerIn, ItemStack stackIn) {
        int enchantModifier=0;
        if(stackIn.isEnchanted()){
            enchantModifier=EnchantmentHelper.getItemEnchantmentLevel(EnchantmentInit.MISER_SOUL.get(), stackIn);
        }
        return 0.1f*enchantModifier;
    }

    public int getAmmoAmount() {
        return ammoAmount;
    }

    @Override
    public Predicate<ItemStack> getAmmoType() {
        return (p_43015_) -> {
            return p_43015_.is(ItemInit.QUICKSILVER.get());
        };
    }

    public void shoot(Player player, Level level, ItemStack stackIn) {
    }
    @Override
    public int getDefaultMaxStackSize() {
        return 1;
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
    public int getEnchantmentValue(ItemStack stack) {
        return this.enchantability;
    }
    @Override
    public boolean isEnchantable(ItemStack p_41456_) {
        return true;
    }
    /*@Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment.getSupportedItems() == CustomItemTags.AMMO_CONSUMING ||enchantment.getSupportedItems() == CustomItemTags.MAGIC_ENCHANTABLE|| enchantment.getSupportedItems() == ItemTags.DURABILITY_ENCHANTABLE || enchantment.getSupportedItems() == ItemTags.VANISHING_ENCHANTABLE;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return super.isBookEnchantable(stack, book);
    }*/
    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        return true;
    }
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {

        //tooltip.add(Component.literal("+").append(Component.literal(""+this.getAttackDamage(null,stack))).append(Component.translatable("translation.darkestsouls.damage")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.GRAY));

        if(this.getLuck(null,stack)>0) {
            tooltip.add(Component.literal("+").append(Component.literal(""+(int)(this.getLuck(null,stack)*100))).append(Component.literal("%")).append(Component.translatable("translation.darkestsouls.luck")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
        }
        if(stack.isEnchanted()){
            int i=EnchantmentHelper.getItemEnchantmentLevel(EnchantmentInit.GUNSLINGER.get(), stack);
            if(i>=1) {
                tooltip.add(Component.literal("+").append(Component.literal("" + i * 10)).append(Component.literal("%")).append(Component.translatable("translation.darkestsouls.cooldown")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
            }
        }

        tooltip.add(Component.translatable("translation.darkestsouls.require").append(Component.literal(" " + this.getAmmoAmount())).append(" ").append(Component.translatable("item.darkestsouls.quicksilver")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));

        super.appendHoverText(stack, context, tooltip, flag);
    }
}
