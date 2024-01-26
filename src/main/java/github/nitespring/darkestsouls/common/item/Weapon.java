package github.nitespring.darkestsouls.common.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

import java.util.List;
import java.util.UUID;

public class Weapon extends Item implements Vanishable {


    private final float attackDamage;
    private final float attackSpeed;
    private final float attackKnockback;
    private final float movementSpeed;
    private final int durability;
    private int maxTargets=-1;
    private final Tier tier;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    protected static final UUID BASE_ATTACK_KNOCKBACK_UUID=UUID.randomUUID();
    protected static final UUID BASE_MOVEMENT_SPEED_UUID=UUID.randomUUID();

    public Weapon(Tier tier, float attack, float speed, float knockback, int durability,float movementSpeed, Properties properties) {
        super(properties);
        this.tier=tier;
        this.attackDamage=attack-1.0f;
        this.attackSpeed=speed-4.0f;
        this.attackKnockback=knockback;
        this.durability=durability;
        this.movementSpeed=movementSpeed-0.1f;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double)this.attackSpeed, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(BASE_ATTACK_KNOCKBACK_UUID, "Weapon modifier", (double)this.attackKnockback, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(BASE_MOVEMENT_SPEED_UUID, "Weapon modifier", (double)this.movementSpeed, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }
    public Weapon(Tier tier, float attack, float speed, float knockback, int durability,float movementSpeed, int maxTargets, Properties properties) {
        this(tier, attack, speed, knockback, durability, movementSpeed, properties);
        this.maxTargets=maxTargets;
    }

    public float getAttackDamage() {return this.attackDamage;}
    public float getAttackDamage(Player playerIn, ItemStack stackIn) {

        float effectModifier = 1;
        float enchantmentsModifier = 0;

        if(playerIn.hasEffect(MobEffects.DAMAGE_BOOST)){
            effectModifier = effectModifier + (1 + playerIn.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier())*0.2f;
        }
        if(playerIn.hasEffect(MobEffects.WEAKNESS)){
            effectModifier = effectModifier - (1 + playerIn.getEffect(MobEffects.WEAKNESS).getAmplifier())*0.2f;
        }

        if(stackIn.isEnchanted()) {

            enchantmentsModifier = enchantmentsModifier + 0.5f*EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, stackIn);

        }

        float f=effectModifier + enchantmentsModifier;


        return f*this.getAttackDamage();

    }
    public float getAttackSpeed() {return this.attackSpeed;}
    public float getAttackKnockback() {return this.attackKnockback;}
    public float getMovementSpeed() {return this.movementSpeed;}
    public int getMaxTargets() {return this.maxTargets;}
    public int getMaxTargets(ItemStack item) {
        if(this.getMaxTargets()>=1&&item.isEnchanted()){
            int i = this.getMaxTargets() + EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SWEEPING_EDGE, item);
            return i;
        }else{
            return this.getMaxTargets();
        }
    }
    public int getDurability() {return this.durability;}

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot p_43274_) {
        return p_43274_ == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(p_43274_);
    }
    @Override
    public int getEnchantmentValue() {
        return this.tier.getEnchantmentValue();
    }
    @Override
    public boolean isValidRepairItem(ItemStack p_43311_, ItemStack p_43312_) {
        return this.tier.getRepairIngredient().test(p_43312_) || super.isValidRepairItem(p_43311_, p_43312_);
    }
    @Override
    public boolean canAttackBlock(BlockState p_43291_, Level p_43292_, BlockPos p_43293_, Player p_43294_) {
        return !p_43294_.isCreative();
    }@Override
    public float getDestroySpeed(ItemStack p_43288_, BlockState p_43289_) {
        if (p_43289_.is(Blocks.COBWEB)) {
            return 15.0F;
        } else {
            return p_43289_.is(BlockTags.SWORD_EFFICIENT) ? 1.5F : 1.0F;
        }
    }
    @Override
    public boolean hurtEnemy(ItemStack p_43278_, LivingEntity p_43279_, LivingEntity p_43280_) {
        p_43278_.hurtAndBreak(1, p_43280_, (p_43296_) -> {
            p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        return true;
    }
    @Override
    public boolean mineBlock(ItemStack p_43282_, Level p_43283_, BlockState p_43284_, BlockPos p_43285_, LivingEntity p_43286_) {
        if (p_43284_.getDestroySpeed(p_43283_, p_43285_) != 0.0F) {
            p_43282_.hurtAndBreak(2, p_43286_, (p_43276_) -> {
                p_43276_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        }
        return true;
    }
    @Override
    public boolean isCorrectToolForDrops(BlockState p_43298_) {
        return p_43298_.is(Blocks.COBWEB);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return ToolActions.SWORD_DIG==toolAction;
    }
    @Override
    public boolean isDamageable(ItemStack stack) {

        return true;
    }
    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return this.durability;
    }


    @Override
    public boolean isEnchantable(ItemStack p_41456_) {
        return true;
    }
    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return true;
    }
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {

        if(enchantment.category== EnchantmentCategory.BREAKABLE||enchantment.category==EnchantmentCategory.VANISHABLE||enchantment.category==EnchantmentCategory.WEAPON) {
            return true;
        }
        return super.canApplyAtEnchantingTable(stack, enchantment);
    }
    @Override
    public boolean isRepairable(ItemStack stack) {

        return true;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Level p_41422_, List<Component> tooltip, TooltipFlag p_41424_) {

        if(this.getMaxTargets()>=1) {
            String info = "\u00A78\u00A7o+ " + this.getMaxTargets(stack) + "\u00A78\u00A7o Max Targets";
            tooltip.add(Component.literal(info));
        }


        super.appendHoverText(stack, p_41422_, tooltip, p_41424_);
    }







    public void doLeftClickAction(Player playerIn, ItemStack stackIn){}

    public void doRightClickAction(Player playerIn, ItemStack stackIn){}

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {

        this.doRightClickAction(p_41433_,p_41433_.getItemInHand(p_41434_));

        return super.use(p_41432_, p_41433_, p_41434_);

    }

}
