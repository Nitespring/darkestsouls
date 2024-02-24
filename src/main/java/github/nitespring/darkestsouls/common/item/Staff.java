package github.nitespring.darkestsouls.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class Staff extends Item implements ILeftClickItem{


    private final float attackDamage;

    private final int durability;



    public Staff(float attackDamage, int durability, Properties properties) {
        super(properties);
        this.attackDamage=attackDamage;
        this.durability=durability;
    }
    public float getAttackDamage(Player playerIn, ItemStack stackIn) {
        return attackDamage;
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

}
