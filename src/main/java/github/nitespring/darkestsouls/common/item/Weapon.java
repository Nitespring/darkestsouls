package github.nitespring.darkestsouls.common.item;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.core.init.EffectInit;
import github.nitespring.darkestsouls.core.init.EnchantmentInit;
import github.nitespring.darkestsouls.core.util.CustomEntityTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
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

public class Weapon extends Item implements ILeftClickItem {
    private final float attackDamage;
    private final float attackSpeed;
    private final float attackKnockback;
    private final float movementSpeed;
    private final int durability;
    private int maxTargets=-1;

    public final int poisedmg;
    private int bloodAttack=0;
    private int poisonAttack=0;
    private int rotAttack=0;
    private int darkAttack =0;
    private int frostAttack=0;
    private int fire=0;
    private int holy=0;
    private final int enchantability;
    private int serrated = 0;
    private final Tier tier;
    //private final Multimap<Attribute, AttributeModifier> defaultModifiers;
    protected static final UUID BASE_ATTACK_KNOCKBACK_UUID=UUID.randomUUID();
    protected static final UUID BASE_MOVEMENT_SPEED_UUID=UUID.randomUUID();
    protected static final UUID BASE_ATTACK_REACH_UUID=UUID.randomUUID();

    public Weapon(Tier tier, float attack, float speed,float reach, float knockback, int poise, int blood, int poison, int frost, int rot, int death, int fire, int holy,int serrated, int durability, int enchantability, float movementSpeed, int maxTargets, Properties properties) {
        super(properties.durability(durability).stacksTo(1)
                .attributes(
                Weapon.createAttributes(
                        attack-1.0f, reach-3.0, speed-4.0f, knockback, movementSpeed-0.1f)));
        this.tier=tier;
        this.attackDamage=attack-1.0f;
        this.attackSpeed=speed-4.0f;
        this.attackKnockback=knockback;
        this.poisedmg=poise;
        this.durability=durability;
        this.movementSpeed=movementSpeed-0.1f;
        this.enchantability=enchantability;
        this.maxTargets=maxTargets;
        this.bloodAttack=blood;
        this.poisonAttack=poison;
        this.frostAttack=frost;
        this.rotAttack=rot;
        this.darkAttack =death;
        this.fire=fire;
        this.holy=holy;
        this.serrated=serrated;
    }
    private static Tool createToolProperties() {
        return new Tool(List.of(Tool.Rule.minesAndDrops(List.of(Blocks.COBWEB), 15.0F), Tool.Rule.overrideSpeed(BlockTags.SWORD_EFFICIENT, 1.5F)), 1.0F, 2);
    }
    public static ItemAttributeModifiers createAttributes(double attackDamage, double reach, double attackSpeed, double attackKnockback, double movementSpeed) {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                BASE_ATTACK_DAMAGE_UUID,
                                "Weapon modifier",
                                attackDamage,
                                AttributeModifier.Operation.ADD_VALUE
                        ), EquipmentSlotGroup.MAINHAND
                ).add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(
                                BASE_ATTACK_SPEED_UUID,
                                "Weapon modifier",
                                attackSpeed,
                                AttributeModifier.Operation.ADD_VALUE
                        ), EquipmentSlotGroup.MAINHAND
                ).add(
                        Attributes.ENTITY_INTERACTION_RANGE,
                        new AttributeModifier(
                                BASE_ATTACK_REACH_UUID,
                                "Weapon modifier",
                                reach,
                                AttributeModifier.Operation.ADD_VALUE
                        ), EquipmentSlotGroup.MAINHAND
                ).add(Attributes.ATTACK_KNOCKBACK,
                        new AttributeModifier(BASE_ATTACK_KNOCKBACK_UUID,
                                "Weapon modifier",
                                attackKnockback,
                                AttributeModifier.Operation.ADD_VALUE
                        ), EquipmentSlotGroup.MAINHAND
                ).add(
                        Attributes.MOVEMENT_SPEED,
                        new AttributeModifier(BASE_MOVEMENT_SPEED_UUID,
                                "Weapon modifier",
                                movementSpeed,
                                AttributeModifier.Operation.ADD_VALUE
                        ), EquipmentSlotGroup.MAINHAND
                ).build();
    }

    public float getAttackDamage() {return this.attackDamage;}
    public float getAttackDamage(Player playerIn, ItemStack stackIn) {

        float enchantmentsModifier = 0;
        //playerIn.getAttackStrengthScale();
        double strengthModifier = playerIn.getAttribute(Attributes.ATTACK_DAMAGE).getValue();
        /*
        if(playerIn.hasEffect(MobEffects.DAMAGE_BOOST)){effectModifier = effectModifier + (1 + playerIn.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier())*0.2f;}
        if(playerIn.hasEffect(MobEffects.WEAKNESS)){effectModifier = effectModifier - (1 + playerIn.getEffect(MobEffects.WEAKNESS).getAmplifier())*0.2f;}
        */
        if(stackIn.isEnchanted()) {
            //if(stackIn.getAllEnchantments().containsKey(Enchantments.SHARPNESS)) {
            if(stackIn.isEnchanted()) {
                enchantmentsModifier = enchantmentsModifier + 0.5f * (1 + EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, stackIn));
            }
        }
        float f = (float) (strengthModifier + enchantmentsModifier);
        //System.out.println(strengthModifier);
        //return f+this.getAttackDamage();
        //System.out.println(f);
        return f;
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

    public int getPoiseDamage(Player playerIn, ItemStack item) {
        if (playerIn.hasEffect(MobEffects.DAMAGE_BOOST)) {
            return this.poisedmg + (int) 2 * playerIn.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier();
        }else{
            return this.poisedmg;
        }
    }
    public int getDurability() {return this.durability;}
    public int getBloodAttack(ItemStack item){
        if(item.isEnchanted()&&item.getEnchantmentLevel(EnchantmentInit.BLOODBLADE.get())>=1) {
            //if(item.getComponents().has(DataComponents.ENCHANTMENTS))
                return bloodAttack + 1 + 2 * EnchantmentHelper.getItemEnchantmentLevel(EnchantmentInit.BLOODBLADE.get(), item);

        }else{
            return bloodAttack;
        }
    }
    public int getPoisonAttack(ItemStack item){return poisonAttack+item.getEnchantmentLevel(EnchantmentInit.POISONED_BLADE.get());}
    public int getFrostAttack(ItemStack item){return frostAttack+item.getEnchantmentLevel(EnchantmentInit.FROST_BLADE.get());}
    public int getRotAttack(ItemStack item){return rotAttack+item.getEnchantmentLevel(EnchantmentInit.ROTTEN_BLADE.get());}
    public int getToxicAttack(ItemStack item){return item.getEnchantmentLevel(EnchantmentInit.TOXIC_BLADE.get());}
    public int getWitherAttack(ItemStack item){return item.getEnchantmentLevel(EnchantmentInit.WITHERED_BLADE.get());}
    public int getDarkAttack(ItemStack item){return darkAttack;}
    public int getFireAttack(ItemStack item){return fire + EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FIRE_ASPECT, item);}
    public float getSmiteAttack(ItemStack item){return holy + 2.5f*EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SMITE, item);}
    public float getBaneOfArthropodsAttack(ItemStack item){return 2.5f*EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BANE_OF_ARTHROPODS, item);}
    public float getBeastHunterAttack(ItemStack item){return 2.5f*item.getEnchantmentLevel(EnchantmentInit.BEAST_HUNTER.get());}
    public int getHolyLevel(ItemStack item){return holy + item.getEnchantmentLevel(EnchantmentInit.ABYSS_CLEANSER.get());}

    public int getSerratedLevel(ItemStack item){
        if(item.isEnchanted()&&item.getEnchantmentLevel(EnchantmentInit.SERRATED.get())>=1) {

            return serrated + item.getEnchantmentLevel(EnchantmentInit.SERRATED.get());

        }else{
            return serrated;}}


    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
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
    public boolean hurtEnemy(ItemStack stackIn, LivingEntity target, LivingEntity playerIn) {
        /*stackIn.hurtAndBreak(1, playerIn, (p_43296_) -> {
            p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });*/
        stackIn.hurtAndBreak(1, playerIn, EquipmentSlot.MAINHAND);

        if (target instanceof DarkestSoulsAbstractEntity && playerIn instanceof Player){
            ((DarkestSoulsAbstractEntity) target).damagePoiseHealth(this.getPoiseDamage((Player) playerIn, stackIn));
        }
        if(!target.fireImmune()) {
            if (this.getFireAttack(stackIn) >= 1) {
                target.igniteForTicks(40*this.getFireAttack(stackIn));
            }
        }
        if(!target.getType().is(CustomEntityTags.POISON_IMMUNE)) {
            if (this.getPoisonAttack(stackIn) >= 1) {
                target.addEffect(new MobEffectInstance(MobEffects.POISON, 90 + this.getPoisonAttack(stackIn) * 45, this.getPoisonAttack(stackIn) - 1), playerIn);
            }
            if (this.getToxicAttack(stackIn) >= 1) {
                target.addEffect(new MobEffectInstance(EffectInit.TOXIC.getHolder().get(), 90 + this.getToxicAttack(stackIn) * 45, this.getToxicAttack(stackIn) - 1), playerIn);
            }
        }
        if(!target.getType().is(CustomEntityTags.ROT_IMMUNE)) {
            if (this.getToxicAttack(stackIn) >= 1) {
                target.addEffect(new MobEffectInstance(EffectInit.ROT.getHolder().get(), 90 + this.getRotAttack(stackIn) * 45, this.getRotAttack(stackIn) - 1), playerIn);
            }
        }
        if(!target.getType().is(CustomEntityTags.FROST_IMMUNE)) {
            if (this.getToxicAttack(stackIn) >= 1) {
                target.addEffect(new MobEffectInstance(EffectInit.FROST.getHolder().get(), 90 + this.getFrostAttack(stackIn) * 45, this.getFrostAttack(stackIn) - 1), playerIn);
            }
        }
        if(!target.getType().is(CustomEntityTags.WITHER_IMMUNE)) {
            if (this.getPoisonAttack(stackIn) >= 1) {
                target.addEffect(new MobEffectInstance(MobEffects.WITHER, 90 + this.getWitherAttack(stackIn) * 45, this.getWitherAttack(stackIn) - 1), playerIn);
            }
        }
        if(!target.getType().is(CustomEntityTags.BLEED_IMMUNE)) {
            if (this.getBloodAttack(stackIn) >= 1) {
                if (target.hasEffect(EffectInit.BLEED.getHolder().get())) {
                    int amount = target.getEffect(EffectInit.BLEED.getHolder().get()).getAmplifier() + this.getBloodAttack(stackIn);
                    target.removeEffect(EffectInit.BLEED.getHolder().get());
                    target.addEffect(new MobEffectInstance(EffectInit.BLEED.getHolder().get(), 240, amount));
                } else {
                    int amount = this.getBloodAttack(stackIn) - 1;
                    target.addEffect(new MobEffectInstance(EffectInit.BLEED.getHolder().get(), 240, amount));
                }
            }
        }

        return true;
    }

    @Override
    public boolean mineBlock(ItemStack stackIn, Level p_43283_, BlockState state, BlockPos pos, LivingEntity playerIn) {
        if (state.getDestroySpeed(p_43283_, pos) != 0.0F) {
            /*p_43282_.hurtAndBreak(2, p_43286_, (p_43276_) -> {
                p_43276_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });*/
            stackIn.hurtAndBreak(1, playerIn, EquipmentSlot.MAINHAND);
        }
        return true;
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack itemStack, BlockState p_43298_) {
        return p_43298_.is(Blocks.COBWEB);
    }


    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return ToolActions.SWORD_DIG==toolAction;
    }
    /*@Override
    public boolean isDamageable(ItemStack stack) {

        return true;
    }


    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }

    @Override
    public int getDefaultMaxDamage(ItemStack stack) {
        return this.durability;
    }*/

    @Override
    public int getDefaultMaxStackSize() {
        return 1;
    }


    @Override
    public boolean isEnchantable(ItemStack p_41456_) {
        return true;
    }
    /*
    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return true;
    }
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {

        if(enchantment.getSupportedItems() == ItemTags.WEAPON_ENCHANTABLE ||enchantment.getSupportedItems() == ItemTags.SHARP_WEAPON_ENCHANTABLE  || enchantment.getSupportedItems() == ItemTags.DURABILITY_ENCHANTABLE || enchantment.getSupportedItems() == ItemTags.VANISHING_ENCHANTABLE) {
            return true;
        }
        return super.canApplyAtEnchantingTable(stack, enchantment);
    }
*/
    
   /* @Override
    public boolean isRepairable(ItemStack stack) {

        return true;
    }*/

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, TooltipContext pContext, List<Component> tooltip, TooltipFlag p_41424_) {

        if(this.getMaxTargets()>=1) {
            String info = "" + this.getMaxTargets(stack);
            //tooltip.add(Component.literal(info));
            tooltip.add(Component.literal("+").append(Component.literal(info)).append(Component.translatable("translation.darkestsouls.targets")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
        }
        if(this.getSerratedLevel(stack)>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+this.getSerratedLevel(stack))).append(Component.translatable("translation.darkestsouls.serrated")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.GRAY));
        }
        if(this.getHolyLevel(stack)>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+this.getHolyLevel(stack))).append(Component.translatable("translation.darkestsouls.holy")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.YELLOW));
        }
        if(this.getFireAttack(stack)>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+this.getFireAttack(stack))).append(Component.translatable("translation.darkestsouls.fire")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.RED));
        }
        if(this.getBloodAttack(stack)>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+this.getBloodAttack(stack))).append(Component.translatable("translation.darkestsouls.blood")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_RED));
        }
        if(this.getPoisonAttack(stack)>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+this.getPoisonAttack(stack))).append(Component.translatable("translation.darkestsouls.poison")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GREEN));
        }
        if(this.getToxicAttack(stack)>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+this.getToxicAttack(stack))).append(Component.translatable("translation.darkestsouls.toxic")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_PURPLE));
        }
        if(this.getFrostAttack(stack)>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+this.getFrostAttack(stack))).append(Component.translatable("translation.darkestsouls.frost")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.AQUA));
        }
        if(this.getRotAttack(stack)>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+this.getRotAttack(stack))).append(Component.translatable("translation.darkestsouls.rot")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.YELLOW));
        }
        if(this.getWitherAttack(stack)>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+this.getWitherAttack(stack))).append(Component.translatable("translation.darkestsouls.wither")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.BLACK));
        }

        super.appendHoverText(stack, pContext, tooltip, p_41424_);
    }



    @Override
    public void doLeftClickAction(Player playerIn, ItemStack stackIn){}




    public void doRightClickAction(Player playerIn, ItemStack stackIn){}

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {

        this.doRightClickAction(p_41433_,p_41433_.getItemInHand(p_41434_));

        return super.use(p_41432_, p_41433_, p_41434_);

    }



}
