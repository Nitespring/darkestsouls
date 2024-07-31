package github.nitespring.darkestsouls.core.enums;


import github.nitespring.darkestsouls.core.init.ItemInit;
import net.minecraft.tags.TagKey;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public enum Tiers implements Tier {
   TITANITE(2, 250, 9.0F, 0.0F, 15, () -> {
      return Ingredient.of(ItemInit.TITANITE_INGOT.get());
   });

   private final int level;
   private final int uses;
   private final float speed;
   private final float damage;
   private final int enchantmentValue;
   private final LazyLoadedValue<Ingredient> repairIngredient;

   private Tiers(int p_43332_, int p_43333_, float p_43334_, float p_43335_, int p_43336_, Supplier<Ingredient> p_43337_) {
      this.level = p_43332_;
      this.uses = p_43333_;
      this.speed = p_43334_;
      this.damage = p_43335_;
      this.enchantmentValue = p_43336_;
      this.repairIngredient = new LazyLoadedValue<>(p_43337_);
   }

   public int getUses() {
      return this.uses;
   }

   public float getSpeed() {
      return this.speed;
   }

   public float getAttackDamageBonus() {
      return this.damage;
   }


   public TagKey<Block> getIncorrectBlocksForDrops() {
      return null;
   }



   public int getLevel() {
      return this.level;
   }

   public int getEnchantmentValue() {
      return this.enchantmentValue;
   }

   public Ingredient getRepairIngredient() {
      return this.repairIngredient.get();
   }

   
}