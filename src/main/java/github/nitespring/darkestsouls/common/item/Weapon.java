package github.nitespring.darkestsouls.common.item;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.core.init.EffectInit;
import github.nitespring.darkestsouls.core.init.EnchantmentInit;
import github.nitespring.darkestsouls.core.util.CustomEntityTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
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

public class Weapon extends Item implements ILeftClickItem {
    private float attackDamage;
    private float attackSpeed;
    private float attackKnockback;
    private float movementSpeed;
    private int durability;
    private int maxTargets;

    public int poisedmg;
    private int bloodAttack;
    private int poisonAttack;
    private int rotAttack;
    private int darkAttack;
    private int frostAttack;
    private int fire;
    private int holy;
    private final int enchantability;
    private int serrated;
    private final Tier tier;
    //private final Multimap<Attribute, AttributeModifier> defaultModifiers;
    protected static final ResourceLocation BASE_ATTACK_KNOCKBACK_ID = ResourceLocation.withDefaultNamespace("base_attack_knockback");
    protected static final ResourceLocation BASE_MOVEMENT_SPEED_ID = ResourceLocation.withDefaultNamespace("base_attack_speed");
    protected static final ResourceLocation BASE_ATTACK_REACH_ID = ResourceLocation.withDefaultNamespace("base_attack_reach");

    public Weapon(Tier tier, float attack, float speed,float reach, float knockback, int poise, int blood, int poison, int frost, int rot, int death, int fire, int holy,int serrated, int durability, int enchantability, float movementSpeed, int maxTargets, Properties properties) {
        super(properties.stacksTo(1).durability(durability).attributes(
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

    public static ItemAttributeModifiers createAttributes(double attackDamage, double reach, double attackSpeed, double attackKnockback, double movementSpeed) {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                BASE_ATTACK_DAMAGE_ID,
                                attackDamage,
                                AttributeModifier.Operation.ADD_VALUE
                        ), EquipmentSlotGroup.MAINHAND
                ).add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(
                                BASE_ATTACK_SPEED_ID,
                                attackSpeed,
                                AttributeModifier.Operation.ADD_VALUE
                        ), EquipmentSlotGroup.MAINHAND
                ).add(
                        Attributes.ENTITY_INTERACTION_RANGE,
                        new AttributeModifier(
                                BASE_ATTACK_REACH_ID,
                                reach,
                                AttributeModifier.Operation.ADD_VALUE
                        ), EquipmentSlotGroup.MAINHAND
                ).add(Attributes.ATTACK_KNOCKBACK,
                        new AttributeModifier(BASE_ATTACK_KNOCKBACK_ID,
                                attackKnockback,
                                AttributeModifier.Operation.ADD_VALUE
                        ), EquipmentSlotGroup.MAINHAND
                ).add(
                        Attributes.MOVEMENT_SPEED,
                        new AttributeModifier(BASE_MOVEMENT_SPEED_ID,
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
                enchantmentsModifier = enchantmentsModifier + 0.5f * (1 + EnchantmentHelper.getItemEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(Enchantments.SHARPNESS).get(),stackIn));
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
    public int getMaxTargets(Player playerIn, ItemStack item ) {
        if(this.getMaxTargets()>=1&&item.isEnchanted()){
            int i = this.getMaxTargets() + EnchantmentHelper.getItemEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(Enchantments.SWEEPING_EDGE).get(),item);
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
    public int getBloodAttack(Player playerIn, ItemStack item){
        if(item.isEnchanted()&&EnchantmentHelper.getItemEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.BLOODBLADE).get(),item)>=1) {
            //if(item.getComponents().has(DataComponents.ENCHANTMENTS))
                return bloodAttack + 1 + 2 * EnchantmentHelper.getItemEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.BLOODBLADE).get(),item);
        }else{

            return bloodAttack;
        }
    }
    public int getPoisonAttack(Player playerIn,ItemStack item){return poisonAttack+EnchantmentHelper.getItemEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.POISONED_BLADE).get(),item);}
    public int getFrostAttack(Player playerIn,ItemStack item){return frostAttack+EnchantmentHelper.getItemEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.FROST_BLADE).get(),item);}
    public int getRotAttack(Player playerIn,ItemStack item){return rotAttack+EnchantmentHelper.getItemEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.ROTTEN_BLADE).get(),item);}
    public int getToxicAttack(Player playerIn,ItemStack item){return EnchantmentHelper.getItemEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.TOXIC_BLADE).get(),item);}
    public int getWitherAttack(Player playerIn,ItemStack item){return EnchantmentHelper.getItemEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.WITHERED_BLADE).get(),item);}
    public int getDarkAttack(Player playerIn,ItemStack item){return darkAttack;}
    public int getFireAttack(Player playerIn,ItemStack item){return fire + EnchantmentHelper.getItemEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(Enchantments.FIRE_ASPECT).get(),item);}
    public int getSmiteAttack(Player playerIn,ItemStack item){return holy + EnchantmentHelper.getItemEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(Enchantments.SMITE).get(),item);}
    public int getBaneOfArthropodsAttack(Player playerIn,ItemStack item){return EnchantmentHelper.getItemEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(Enchantments.BANE_OF_ARTHROPODS).get(),item);}
    public int getBeastHunterAttack(Player playerIn,ItemStack item){return EnchantmentHelper.getItemEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.BEAST_HUNTER).get(),item);}
    public int getHolyLevel(Player playerIn,ItemStack item){return holy + EnchantmentHelper.getItemEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.ABYSS_CLEANSER).get(),item);}

    public int getSerratedLevel(Player playerIn,ItemStack item){
        if(item.isEnchanted()&&EnchantmentHelper.getItemEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.SERRATED).get(),item)>=1) {

            return serrated + EnchantmentHelper.getItemEnchantmentLevel(playerIn.level().registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(EnchantmentInit.SERRATED).get(),item);

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
    public boolean hurtEnemy(ItemStack stackIn, LivingEntity target, LivingEntity entityIn) {
        /*stackIn.hurtAndBreak(1, playerIn, (p_43296_) -> {
            p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });*/
        stackIn.hurtAndBreak(1, entityIn, EquipmentSlot.MAINHAND);
        if(entityIn instanceof Player playerIn) {
            if (target instanceof DarkestSoulsAbstractEntity) {
                ((DarkestSoulsAbstractEntity) target).damagePoiseHealth(this.getPoiseDamage(playerIn, stackIn));
            }
            if (!target.fireImmune()) {
                if (this.getFireAttack(playerIn, stackIn) >= 1) {
                    target.igniteForTicks(40 * this.getFireAttack(playerIn,stackIn));
                }
            }
            if (!target.getType().is(CustomEntityTags.POISON_IMMUNE)) {
                if (this.getPoisonAttack(playerIn,stackIn) >= 1) {
                    target.addEffect(new MobEffectInstance(MobEffects.POISON, 90 + this.getPoisonAttack(playerIn,stackIn) * 45, this.getPoisonAttack(playerIn,stackIn) - 1), playerIn);
                }
                if (this.getToxicAttack(playerIn,stackIn) >= 1) {
                    target.addEffect(new MobEffectInstance(EffectInit.TOXIC.getHolder().get(), 90 + this.getToxicAttack(playerIn,stackIn) * 45, this.getToxicAttack(playerIn,stackIn) - 1), playerIn);
                }
            }
            if (!target.getType().is(CustomEntityTags.ROT_IMMUNE)) {
                if (this.getToxicAttack(playerIn,stackIn) >= 1) {
                    target.addEffect(new MobEffectInstance(EffectInit.ROT.getHolder().get(), 90 + this.getRotAttack(playerIn,stackIn) * 45, this.getRotAttack(playerIn,stackIn) - 1), playerIn);
                }
            }
            if (!target.getType().is(CustomEntityTags.FROST_IMMUNE)) {
                if (this.getToxicAttack(playerIn,stackIn) >= 1) {
                    target.addEffect(new MobEffectInstance(EffectInit.FROST.getHolder().get(), 90 + this.getFrostAttack(playerIn,stackIn) * 45, this.getFrostAttack(playerIn,stackIn) - 1), playerIn);
                }
            }
            if (!target.getType().is(CustomEntityTags.WITHER_IMMUNE)) {
                if (this.getPoisonAttack(playerIn,stackIn) >= 1) {
                    target.addEffect(new MobEffectInstance(MobEffects.WITHER, 90 + this.getWitherAttack(playerIn,stackIn) * 45, this.getWitherAttack(playerIn,stackIn) - 1), playerIn);
                }
            }
            if (!target.getType().is(CustomEntityTags.BLEED_IMMUNE)) {
                if (this.getBloodAttack(playerIn,stackIn) >= 1) {
                    if (target.hasEffect(EffectInit.ROT.getHolder().get())) {
                        int amount = target.getEffect(EffectInit.ROT.getHolder().get()).getAmplifier() + this.getBloodAttack(playerIn,stackIn);
                        target.removeEffect(EffectInit.ROT.getHolder().get());
                        target.addEffect(new MobEffectInstance(EffectInit.ROT.getHolder().get(), 240, amount));
                    } else {
                        int amount = this.getBloodAttack(playerIn,stackIn) - 1;
                        target.addEffect(new MobEffectInstance(EffectInit.ROT.getHolder().get(), 240, amount));
                    }
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
    public boolean canPerformAction(ItemStack stack, ToolAction itemAbility) {
        return ToolActions.SWORD_DIG==itemAbility;
    }

 
    public boolean isDamageable(ItemStack stack) {

        return true;
    }

    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }

    public int getMaxDamage(ItemStack stack) {
        return this.durability;
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
    

    public boolean isRepairable(ItemStack stack) {

        return true;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, TooltipContext pContext, List<Component> tooltip, TooltipFlag p_41424_) {
        int maxTargets = getMaxTargets() + EnchantmentHelper.getItemEnchantmentLevel(pContext.registries().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWEEPING_EDGE),stack);
        int fire = this.fire + EnchantmentHelper.getItemEnchantmentLevel(pContext.registries().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FIRE_ASPECT),stack);
        int holy = this.holy + EnchantmentHelper.getItemEnchantmentLevel(pContext.registries().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentInit.ABYSS_CLEANSER),stack);
        int serrated = this.serrated + EnchantmentHelper.getItemEnchantmentLevel(pContext.registries().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentInit.SERRATED),stack);
        int blood = this.bloodAttack;
        if(EnchantmentHelper.getItemEnchantmentLevel(pContext.registries().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentInit.BLOODBLADE),stack)>=1){
           blood = blood+1+2*EnchantmentHelper.getItemEnchantmentLevel(pContext.registries().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentInit.BLOODBLADE),stack);
        }
        int poison = poisonAttack + EnchantmentHelper.getItemEnchantmentLevel(pContext.registries().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentInit.POISONED_BLADE),stack);
        int toxic = EnchantmentHelper.getItemEnchantmentLevel(pContext.registries().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentInit.TOXIC_BLADE),stack);
        int frost = frostAttack + EnchantmentHelper.getItemEnchantmentLevel(pContext.registries().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentInit.FROST_BLADE),stack);
        int rot = rotAttack + EnchantmentHelper.getItemEnchantmentLevel(pContext.registries().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentInit.ROTTEN_BLADE),stack);
        int wither = EnchantmentHelper.getItemEnchantmentLevel(pContext.registries().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentInit.WITHERED_BLADE),stack);

        if(maxTargets>=1) {
            String info = "" + maxTargets;
            tooltip.add(Component.literal("+").append(Component.literal(info)).append(Component.translatable("translation.darkestsouls.targets")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GRAY));
        }
        if(serrated>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+serrated)).append(Component.translatable("translation.darkestsouls.serrated")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.GRAY));
        }
        if(holy>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+holy)).append(Component.translatable("translation.darkestsouls.holy")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.YELLOW));
        }
        if(fire>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+fire)).append(Component.translatable("translation.darkestsouls.fire")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.RED));
        }
        if(blood>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+blood)).append(Component.translatable("translation.darkestsouls.blood")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_RED));
        }
        if(poison>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+poison)).append(Component.translatable("translation.darkestsouls.poison")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_GREEN));
        }
        if(toxic>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+toxic)).append(Component.translatable("translation.darkestsouls.toxic")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.DARK_PURPLE));
        }
        if(frost>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+frost)).append(Component.translatable("translation.darkestsouls.frost")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.AQUA));
        }
        if(rot>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+rot)).append(Component.translatable("translation.darkestsouls.rot")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.YELLOW));
        }
        if(wither>=1) {
            tooltip.add(Component.literal("+").append(Component.literal(""+wither)).append(Component.translatable("translation.darkestsouls.wither")).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.BLACK));
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
