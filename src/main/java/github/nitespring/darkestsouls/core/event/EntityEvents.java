package github.nitespring.darkestsouls.core.event;

import github.nitespring.darkestsouls.DarkestSouls;
import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.Bonewheel;
import github.nitespring.darkestsouls.common.entity.mob.skeleton.Skeleton;
import github.nitespring.darkestsouls.common.item.Weapon;
import github.nitespring.darkestsouls.core.init.EffectInit;
import github.nitespring.darkestsouls.core.init.EnchantmentInit;
import github.nitespring.darkestsouls.core.util.ArmourUtils;
import github.nitespring.darkestsouls.core.util.CustomEntityTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.monster.hoglin.HoglinBase;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

//@Mod.EventBusSubscriber(modid = DarkestSouls.MODID)
@Mod.EventBusSubscriber(modid = DarkestSouls.MODID)
public class EntityEvents {
	@SubscribeEvent
	public static void applyDamageEvent(final LivingDamageEvent event){
		DamageSource source = event.getSource();
		//System.out.println(event.getAmount());
		//System.out.println(event.getEntity().getType());
		//System.out.println(event.getEntity().getType().getTags().count());
		if(event.getEntity() instanceof Player player){
			if(source.is(DamageTypeTags.IS_FIRE)) {
				event.setAmount((float) (event.getAmount()*(1-0.01* ArmourUtils.getFireResistance(player))));
			}
			if(source.is(DamageTypeTags.BYPASSES_RESISTANCE)){
				event.setAmount((float) (event.getAmount()*(1-0.01*ArmourUtils.getMagicDefence(player))));
			}
		}
		if(event.getEntity().getType().is(CustomEntityTags.BEAST)){
			if(source.is(DamageTypeTags.IS_FIRE)) {
				event.setAmount(event.getAmount()*2.5f);
			}else if (event.getEntity().isOnFire()){
				event.setAmount(event.getAmount()*1.2f);
			}
		}
		if(source.is(DamageTypes.PLAYER_ATTACK)&&source.getEntity()!=null&&source.getEntity() instanceof Player attacker){

			if(attacker.hasItemInSlot(EquipmentSlot.MAINHAND)){
				ItemStack itemStack = attacker.getItemInHand(InteractionHand.MAIN_HAND);

				if (itemStack.getItem() instanceof Weapon weapon) {
					if(event.getEntity().getType().is(CustomEntityTags.BEAST)) {
						event.setAmount((float) (event.getAmount()*(1+0.1*weapon.getSerratedLevel(attacker,itemStack))));
						//System.out.println(event.getAmount());
					}
					if(event.getEntity().getType().is(CustomEntityTags.BEASTLY)) {
						event.setAmount((float) (event.getAmount()*(1+0.05*weapon.getSerratedLevel(attacker,itemStack))));
						//System.out.println(event.getAmount());
					}
					if(event.getEntity().getType().is(CustomEntityTags.ABYSSAL)) {
						event.setAmount((float) (event.getAmount()*(1+0.1*weapon.getHolyLevel(attacker,itemStack))));
						//System.out.println(event.getAmount());
					}
				} else {
					if(itemStack.isEnchanted()) {
						int serratedLevel = EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.SERRATED.get(),itemStack);
						int holyLevel = EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.ABYSS_CLEANSER.get(),itemStack);
						int bloodLevel = EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.BLOODBLADE.get(),itemStack);
						int poisonLevel = EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.POISONED_BLADE.get(),itemStack);
						int toxicLevel = EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.TOXIC_BLADE.get(),itemStack);
						int frostLevel = EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.FROST_BLADE.get(),itemStack);
						int rotLevel = EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.ROTTEN_BLADE.get(),itemStack);
						int witherLevel = EnchantmentHelper.getTagEnchantmentLevel(EnchantmentInit.WITHERED_BLADE.get(),itemStack);

						if(serratedLevel>=1){
							if(event.getEntity().getType().is(CustomEntityTags.BEAST)) {
								event.setAmount((float) (event.getAmount() * (1 + (0.1 * serratedLevel))));
								//System.out.println(event.getAmount());
							}
							if(event.getEntity().getType().is(CustomEntityTags.BEASTLY)) {
								event.setAmount((float) (event.getAmount() * (1 + (0.05 * serratedLevel))));
								//System.out.println(event.getAmount());
							}
						}
						if(holyLevel>=1){
							if(event.getEntity().getType().is(CustomEntityTags.ABYSSAL)) {
								event.setAmount((float) (event.getAmount() * (1 + (0.1 * holyLevel))));
								//System.out.println(event.getAmount());
							}
						}
						if (!event.getEntity().getType().is(CustomEntityTags.POISON_IMMUNE)) {
							if (poisonLevel >= 1) {
								event.getEntity().addEffect(new MobEffectInstance(MobEffects.POISON, 90 + poisonLevel * 45, poisonLevel-1), source.getEntity());
							}
							if (toxicLevel >= 1) {
								event.getEntity().addEffect(new MobEffectInstance(EffectInit.TOXIC.get(), 90 +toxicLevel * 45, toxicLevel-1), source.getEntity());
							}
						}
						if (!event.getEntity().getType().is(CustomEntityTags.ROT_IMMUNE)) {
							if (rotLevel >= 1) {
								event.getEntity().addEffect(new MobEffectInstance(EffectInit.ROT.get(), 90 + rotLevel * 45, rotLevel - 1), source.getEntity());
							}
						}
						if (!event.getEntity().getType().is(CustomEntityTags.FROST_IMMUNE)) {
							if (frostLevel >= 1) {
								event.getEntity().addEffect(new MobEffectInstance(EffectInit.FROST.get(), 90 + frostLevel * 45, frostLevel - 1), source.getEntity());
							}
						}
						if (!event.getEntity().getType().is(CustomEntityTags.WITHER_IMMUNE)) {
							if (witherLevel >= 1) {
								event.getEntity().addEffect(new MobEffectInstance(MobEffects.WITHER, 90 + witherLevel * 45, witherLevel - 1), source.getEntity());
							}
						}
						if (!event.getEntity().getType().is(CustomEntityTags.BLEED_IMMUNE)) {
							if (bloodLevel >= 1) {
								int bloodLevelFinalized=1+bloodLevel*2;
								if (event.getEntity().hasEffect(EffectInit.BLEED.get())) {
									int amount = event.getEntity().getEffect(EffectInit.BLEED.get()).getAmplifier() + bloodLevelFinalized;
									event.getEntity().removeEffect(EffectInit.BLEED.get());
									event.getEntity().addEffect(new MobEffectInstance(EffectInit.BLEED.get(), 240, amount));
									System.out.println(amount);
								} else {
									int amount = bloodLevelFinalized - 1;
									event.getEntity().addEffect(new MobEffectInstance(EffectInit.BLEED.get(), 240, amount));
									System.out.println(amount);
								}
								System.out.println(bloodLevelFinalized);
							}
						}
					}
				}

			}

		}

	}

	@SubscribeEvent
	public static void removeStatusesEvent(final LivingEvent.LivingTickEvent event){
		LivingEntity entity = event.getEntity();
		//if(event.getEntity() instanceof LivingEntity entity){
			if(entity.getType().is(CustomEntityTags.FROST_IMMUNE)&&entity.hasEffect(EffectInit.FROST.get())){
				entity.removeEffect(EffectInit.FROST.get());
			}
			if(entity.getType().is(CustomEntityTags.WITHER_IMMUNE)&&entity.hasEffect(MobEffects.WITHER)){
				entity.removeEffect(MobEffects.WITHER);
			}
			if(entity.getType().is(CustomEntityTags.ROT_IMMUNE)&&entity.hasEffect(EffectInit.ROT.get())){
				entity.removeEffect(EffectInit.ROT.get());
			}
			if(entity.getType().is(CustomEntityTags.POISON_IMMUNE)){
				if(entity.hasEffect(MobEffects.POISON)){
					entity.removeEffect(MobEffects.POISON);
				}
				if(entity.hasEffect(EffectInit.TOXIC.get())){
					entity.removeEffect(EffectInit.TOXIC.get());
				}
			}
			if(entity.getType().is(CustomEntityTags.BLEED_IMMUNE)&&entity.hasEffect(EffectInit.BLEED.get())){
				entity.removeEffect(EffectInit.BLEED.get());
			}


		//}
	}

	@SubscribeEvent
	public static void entityJoin(EntityJoinLevelEvent event) {

		if(event.getEntity() instanceof Mob mob) {
			if (mob instanceof IronGolem || mob instanceof SnowGolem) {
				mob.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(mob, Mob.class, true, predicate -> predicate instanceof DarkestSoulsAbstractEntity mob1 && (mob1.getDSTeam() == 1 || mob1.getDSTeam() == 4 || mob1.getDSTeam() == 6)));
			}
			if (mob instanceof Wolf) {
				mob.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(mob, Skeleton.class, true));
				mob.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(mob, Bonewheel.class, true));
			}
			if (mob instanceof Villager villager) {
				villager.targetSelector.addGoal(2, new AvoidEntityGoal<>(villager, Mob.class, 12.0F, 1.0, 1.0, predicate -> predicate instanceof DarkestSoulsAbstractEntity mob1 && (mob1.getDSTeam() == 1 || mob1.getDSTeam() == 4 || mob1.getDSTeam() == 6)));
			}
			if (mob instanceof WanderingTrader villager) {
				villager.targetSelector.addGoal(2, new AvoidEntityGoal<>(villager, Mob.class, 12.0F, 1.0, 1.0, predicate -> predicate instanceof DarkestSoulsAbstractEntity mob1 && (mob1.getDSTeam() == 1 || mob1.getDSTeam() == 4 || mob1.getDSTeam() == 6)));
			}
			if (mob instanceof Animal animal && !(mob instanceof Wolf || mob instanceof HoglinBase)) {
				animal.targetSelector.addGoal(2, new AvoidEntityGoal<>(animal, Mob.class, 12.0F, 1.2, 1.4, predicate -> predicate instanceof DarkestSoulsAbstractEntity mob1 && (mob1.getDSTeam() == 4)));
			}
		}

	}

}
	 
