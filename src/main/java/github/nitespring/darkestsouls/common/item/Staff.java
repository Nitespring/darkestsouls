package github.nitespring.darkestsouls.common.item;

import github.nitespring.darkestsouls.core.init.EnchantmentInit;
import github.nitespring.darkestsouls.core.init.ItemInit;
import github.nitespring.darkestsouls.core.util.CustomItemTags;
import github.nitespring.darkestsouls.core.util.MathUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
 import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class Staff extends Item implements ILeftClickItem, IAmmoConsumingItem{


    private final float attackDamage;

    private final int durability;

    private final int tier;


    public Staff(float attackDamage, int durability, int tier, Properties properties) {
        super(properties.durability(durability).stacksTo(1));
        this.attackDamage=attackDamage;
        this.durability=durability;
        this.tier=tier;
    }
    public float getAttackDamage(@Nullable Player playerIn, ItemStack stackIn) {
        return attackDamage
                * (1 + 0.2f * stackIn.getEnchantmentLevel(EnchantmentInit.MOON_BLESSING.get()))
                + 2.0f * stackIn.getEnchantmentLevel(EnchantmentInit.STARPOWER.get());
    }

    public float getAttackDamage( ItemStack stackIn) {
        return this.getAttackDamage(null,stackIn);
    }
    public int getCatalystTier() {return tier;}
    public float getLuck(@org.jetbrains.annotations.Nullable Player playerIn, ItemStack stackIn) {
        int enchantModifier=0;
        if(stackIn.isEnchanted()){
            enchantModifier= EnchantmentHelper.getItemEnchantmentLevel(EnchantmentInit.MISER_SOUL.get(), stackIn);
        }
        return 0.1f*enchantModifier;
    }

    @Override
    public int getDefaultMaxStackSize() {
        return 1;
    }

    @Override
    public void doLeftClickAction(Player playerIn, ItemStack stackIn) {
        doSpellA(playerIn, stackIn, InteractionHand.MAIN_HAND);
    }

    @Override
    public boolean canAttackBlock(BlockState p_43291_, Level p_43292_, BlockPos p_43293_, Player p_43294_) {
        return !p_43294_.isCreative();
    }


    @Override
    public int getEnchantmentValue(ItemStack stack) {
        return 15;
    }
    @Override
    public boolean isEnchantable(ItemStack p_41456_) {
        return true;
    }

    /*@Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment.getSupportedItems() == CustomItemTags.AMMO_CONSUMING ||enchantment.getSupportedItems() == CustomItemTags.MAGIC_ENCHANTABLE || enchantment.getSupportedItems() == ItemTags.DURABILITY_ENCHANTABLE || enchantment.getSupportedItems() == ItemTags.VANISHING_ENCHANTABLE;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return super.isBookEnchantable(stack, book);
    }*/

    @Override
    public InteractionResultHolder<ItemStack> use(Level levelIn, Player playerIn, InteractionHand handIn) {
            this.doSpellB(playerIn, playerIn.getItemInHand(handIn), handIn);

        return super.use(levelIn, playerIn, handIn);
    }

    public void doSpellA(Player playerIn, ItemStack stackIn, InteractionHand HandIn){};
    public void doSpellB(Player playerIn, ItemStack stackIn, InteractionHand handIn){};

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        return true;}


    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag p_41424_) {

        String info = " " + MathUtils.convertToRoman(this.getCatalystTier());
        if(this.getCatalystTier()==0) {
            info = " 0";
        }
        tooltip.add(Component.translatable("translation.darkestsouls.tier").append(Component.literal(info)).withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.DARK_GRAY));
        String info2 = "\u00A78\u00A7oConsumes Small Soul Fragments";
        tooltip.add(Component.translatable("translation.darkestsouls.consumes_small_soul").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
        String info1 = "\u00A78" + this.getAttackDamage(stack) + " Damage";
        tooltip.add(Component.literal(""+this.getAttackDamage(stack)).append(Component.translatable("translation.darkestsouls.damage")).withStyle(ChatFormatting.GRAY));
        if(this.getLuck(null,stack)>0) {
            tooltip.add(Component.literal("+").append(Component.literal(""+(int)(this.getLuck(null,stack)*100))).append(Component.literal("%")).append(Component.translatable("translation.darkestsouls.luck")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
        }


        super.appendHoverText(stack, context, tooltip, p_41424_);
    }
    @Override
    public Predicate<ItemStack> getAmmoType(){return (p_43015_) -> {
        return p_43015_.is(ItemInit.SMALL_SOUL_FRAGMENT.get());
    };}

/*
    public ItemStack getAmmo(Player playerIn){

        ItemStack ammo = null;
        int size = playerIn.getInventory().getContainerSize();
        Predicate<ItemStack> predicate = this.getAmmoType();

        for(int i = 0; i < size; ++i) {
            ItemStack itemstack1 = playerIn.getInventory().getItem(i);
            if (predicate.test(itemstack1)) {
                ammo=itemstack1;
            }
        }

        return ammo;
    }

    public boolean hasAmmo(Player playerIn, int amount){
        int size = playerIn.getInventory().getContainerSize();
        int lastCheckedSlot=0;
        int amountInPossession=0;
        Predicate<ItemStack> predicate = this.getAmmoType();
        while(lastCheckedSlot<size && amountInPossession<amount) {
            for (int i = lastCheckedSlot; i < size; ++i) {
                ItemStack itemstack1 = playerIn.getInventory().getItem(i);
                if (predicate.test(itemstack1)) {
                    if(itemstack1.getCount()>=amount-amountInPossession){
                        amountInPossession=amount;
                    }else{
                         amountInPossession=amountInPossession+itemstack1.getCount();
                    }
                }
                lastCheckedSlot++;
            }
        }
        if(amountInPossession>=amount){return true;}else{return false;}
    }
    public void consumeAmmo(Player playerIn, int amount){
        int size = playerIn.getInventory().getContainerSize();
        int lastCheckedSlot=0;
        int amountInPossession=0;
        Predicate<ItemStack> predicate = this.getAmmoType();
        while(lastCheckedSlot<size && amountInPossession<amount) {
            for (int i = lastCheckedSlot; i < size; ++i) {
                ItemStack itemstack1 = playerIn.getInventory().getItem(i);
                if (predicate.test(itemstack1)) {
                    if(itemstack1.getCount()>=amount-amountInPossession){

                        itemstack1.shrink(amount-amountInPossession);
                        amountInPossession=amount;
                    }else{
                        amountInPossession=amountInPossession+itemstack1.getCount();
                        itemstack1.shrink(itemstack1.getCount());
                    }
                }
                lastCheckedSlot++;
            }
        }
    }
*/


}
