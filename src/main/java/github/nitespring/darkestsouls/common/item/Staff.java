package github.nitespring.darkestsouls.common.item;

import github.nitespring.darkestsouls.core.init.ItemInit;
import github.nitespring.darkestsouls.core.util.MathUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class Staff extends Item implements ILeftClickItem{


    private final float attackDamage;

    private final int durability;

    private final int tier;


    public Staff(float attackDamage, int durability, int tier, Properties properties) {
        super(properties);
        this.attackDamage=attackDamage;
        this.durability=durability;
        this.tier=tier;
    }
    public float getAttackDamage(@Nullable Player playerIn, ItemStack stackIn) {
        return attackDamage;
    }

    public float getAttackDamage( ItemStack stackIn) {
        return this.getAttackDamage(null,stackIn);
    }
    public int getCatalystTier() {return tier;}

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }
    @Override
    public int getMaxDamage(ItemStack stack) {
        return durability;
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
    public boolean isDamageable(ItemStack stack) {

        return true;
    }

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
    public void appendHoverText(ItemStack stack, Level p_41422_, List<Component> tooltip, TooltipFlag p_41424_) {

        String info = "\u00A78\u00A7oTier " + MathUtils.convertToRoman(this.getCatalystTier());
        if(this.getCatalystTier()==0) {
            info = "\u00A78\u00A7oTier 0"; 
        }
        tooltip.add(Component.literal(info));

        String info1 = "\u00A78\u00A7o" + this.getAttackDamage(stack) + " Damage";
        tooltip.add(Component.literal(info1));



        super.appendHoverText(stack, p_41422_, tooltip, p_41424_);
    }

    public Predicate<ItemStack> getAmmoType(){return (p_43015_) -> {
        return p_43015_.is(ItemInit.SMALL_SOUL_FRAGMENT.get());
    };}


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



}
