package github.nitespring.darkestsouls.common.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.core.init.EffectInit;
import github.nitespring.darkestsouls.core.init.EnchantmentInit;
import github.nitespring.darkestsouls.core.init.KeybindInit;
import github.nitespring.darkestsouls.core.util.CustomEntityTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
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

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class Weapon extends Item implements Vanishable,ILeftClickItem {


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
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    protected static final UUID BASE_ATTACK_KNOCKBACK_UUID=UUID.randomUUID();
    protected static final UUID BASE_MOVEMENT_SPEED_UUID=UUID.randomUUID();

    public Weapon(Tier tier, float attack, float speed, float reach, float knockback, int poise, int blood, int poison, int frost, int rot, int death, int fire, int holy, int serrated, int durability, int enchantability, float movementSpeed, int maxTargets, Properties properties) {
        super(properties.stacksTo(1).durability(durability));
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

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", this.attackSpeed, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(BASE_ATTACK_KNOCKBACK_UUID, "Weapon modifier", this.attackKnockback, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(BASE_MOVEMENT_SPEED_UUID, "Weapon modifier", this.movementSpeed, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }



    public float getAttackDamage() {return this.attackDamage;}
    public float getAttackDamage(Player playerIn, ItemStack stackIn) {

        float enchantmentsModifier = 0;

        double strengthModifier = playerIn.getAttribute(Attributes.ATTACK_DAMAGE).getValue();

        if(stackIn.isEnchanted()) {
            if(stackIn.getAllEnchantments().containsKey(Enchantments.SHARPNESS)) {
                enchantmentsModifier = enchantmentsModifier + 0.5f * (1 + EnchantmentHelper.getTagEnchantmentLevel(Enchantments.SHARPNESS, stackIn));
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
    public int getMaxTargets(@Nullable Player playerIn, ItemStack item) {
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
    public int getBloodAttack(Player playerIn, ItemStack item){
        if(item.isEnchanted()&&EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.BLOODBLADE.get(),item)>=1) {
            //if(item.getComponents().has(DataComponents.ENCHANTMENTS))
            return bloodAttack + 1 + 2 * EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.BLOODBLADE.get(),item);
        }else{

            return bloodAttack;
        }
    }
    public int getPoisonAttack(Player playerIn,ItemStack item){return poisonAttack+EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.POISONED_BLADE.get(),item);}
    public int getFrostAttack(Player playerIn,ItemStack item){return frostAttack+EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.FROST_BLADE.get(),item);}
    public int getRotAttack(Player playerIn,ItemStack item){return rotAttack+EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.ROTTEN_BLADE.get(),item);}
    public int getToxicAttack(Player playerIn,ItemStack item){return EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.TOXIC_BLADE.get(),item);}
    public int getWitherAttack(Player playerIn,ItemStack item){return EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.WITHERED_BLADE.get(),item);}
    public int getDarkAttack(Player playerIn,ItemStack item){return darkAttack;}
    public int getFireAttack(Player playerIn,ItemStack item){return fire + EnchantmentHelper.getTagEnchantmentLevel(Enchantments.FIRE_ASPECT,item);}
    public int getSmiteAttack(Player playerIn,ItemStack item){return holy + EnchantmentHelper.getTagEnchantmentLevel(Enchantments.SMITE,item);}
    public int getBaneOfArthropodsAttack(Player playerIn,ItemStack item){return EnchantmentHelper.getTagEnchantmentLevel(Enchantments.BANE_OF_ARTHROPODS,item);}
    public int getBeastHunterAttack(Player playerIn,ItemStack item){return EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.BEAST_HUNTER.get(),item);}
    public int getHolyLevel(Player playerIn,ItemStack item){return holy + EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.ABYSS_CLEANSER.get(),item);}

    public int getSerratedLevel(Player playerIn,ItemStack item){
        if(item.isEnchanted()&&EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.SERRATED.get(),item)>=1) {

            return serrated + EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.SERRATED.get(),item);

        }else{
            return serrated;}}

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot p_43274_) {
        return p_43274_ == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(p_43274_);
    }
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
        stackIn.hurtAndBreak(1, entityIn, (p_43296_) -> {
            p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        if(entityIn instanceof Player playerIn) {
            if (target instanceof DarkestSoulsAbstractEntity) {
                ((DarkestSoulsAbstractEntity) target).damagePoiseHealth(this.getPoiseDamage(playerIn, stackIn));
            }
            if (!target.fireImmune()) {
                if (this.getFireAttack(playerIn, stackIn) >= 1) {
                    target.setRemainingFireTicks(target.getRemainingFireTicks()+40 * this.getFireAttack(playerIn,stackIn));
                }
            }
            if (!target.getType().is(CustomEntityTags.POISON_IMMUNE)) {
                if (this.getPoisonAttack(playerIn,stackIn) >= 1) {
                    target.addEffect(new MobEffectInstance(MobEffects.POISON, 90 + this.getPoisonAttack(playerIn,stackIn) * 45, this.getPoisonAttack(playerIn,stackIn) - 1), playerIn);
                }
                if (this.getToxicAttack(playerIn,stackIn) >= 1) {
                    target.addEffect(new MobEffectInstance(EffectInit.TOXIC.get(), 90 + this.getToxicAttack(playerIn,stackIn) * 45, this.getToxicAttack(playerIn,stackIn) - 1), playerIn);
                }
            }
            if (!target.getType().is(CustomEntityTags.ROT_IMMUNE)) {
                if (this.getRotAttack(playerIn,stackIn) >= 1) {
                    target.addEffect(new MobEffectInstance(EffectInit.ROT.get(), 90 + this.getRotAttack(playerIn,stackIn) * 45, this.getRotAttack(playerIn,stackIn) - 1), playerIn);
                }
            }
            if (!target.getType().is(CustomEntityTags.FROST_IMMUNE)) {
                if (this.getFrostAttack(playerIn,stackIn) >= 1) {
                    target.addEffect(new MobEffectInstance(EffectInit.FROST.get(), 90 + this.getFrostAttack(playerIn,stackIn) * 45, this.getFrostAttack(playerIn,stackIn) - 1), playerIn);
                }
            }
            if (!target.getType().is(CustomEntityTags.WITHER_IMMUNE)) {
                if (this.getWitherAttack(playerIn,stackIn) >= 1) {
                    target.addEffect(new MobEffectInstance(MobEffects.WITHER, 90 + this.getWitherAttack(playerIn,stackIn) * 45, this.getWitherAttack(playerIn,stackIn) - 1), playerIn);
                }
            }
            if (!target.getType().is(CustomEntityTags.BLEED_IMMUNE)) {
                if (this.getBloodAttack(playerIn,stackIn) >= 1) {
                    if (target.hasEffect(EffectInit.BLEED.get())) {
                        int amount = target.getEffect(EffectInit.BLEED.get()).getAmplifier() + this.getBloodAttack(playerIn,stackIn);
                        target.removeEffect(EffectInit.BLEED.get());
                        target.addEffect(new MobEffectInstance(EffectInit.BLEED.get(), 240, amount));
                    } else {
                        int amount = this.getBloodAttack(playerIn,stackIn) - 1;
                        target.addEffect(new MobEffectInstance(EffectInit.BLEED.get(), 240, amount));
                    }
                }
            }
        }
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
    public void appendHoverText(ItemStack stack, Level pContext, List<Component> tooltip, TooltipFlag p_41424_) {
        int maxTargets = getMaxTargets() + EnchantmentHelper.getTagEnchantmentLevel(Enchantments.SWEEPING_EDGE,stack);
        int fire = this.fire + EnchantmentHelper.getTagEnchantmentLevel(Enchantments.FIRE_ASPECT,stack);
        int holy = this.holy + EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.ABYSS_CLEANSER.get(),stack);
        int serrated = this.serrated + EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.SERRATED.get(),stack);
        int blood = this.bloodAttack;
        if(EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.BLOODBLADE.get(),stack)>=1){
            blood = blood+1+2*EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.BLOODBLADE.get(),stack);
        }
        int poison = poisonAttack + EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.POISONED_BLADE.get(),stack);
        int toxic = EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.TOXIC_BLADE.get(),stack);
        int frost = frostAttack + EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.FROST_BLADE.get(),stack);
        int rot = rotAttack + EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.ROTTEN_BLADE.get(),stack);
        int wither = EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.WITHERED_BLADE.get(),stack);

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
