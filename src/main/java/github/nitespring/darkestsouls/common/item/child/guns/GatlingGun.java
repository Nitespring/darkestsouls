package github.nitespring.darkestsouls.common.item.child.guns;

import github.nitespring.darkestsouls.client.render.item.gun.GatlingGunGeoRenderer;
import github.nitespring.darkestsouls.common.entity.projectile.weapon.Bullet;
import github.nitespring.darkestsouls.common.item.Gun;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class GatlingGun extends Gun implements GeoItem {
    protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
    private static final RawAnimation SHOOT_ANIM = RawAnimation.begin().thenLoop("animation.gatling_gun.shoot");
    private static final RawAnimation STOP = RawAnimation.begin().thenLoop("animation.gatling_gun.new");

    public GatlingGun(float damage, int cooldown, int poise, float size, float flyingPower, int flyingTime, int ricochet, int pierce, int ammoAmount, int durability, int enchantability, Properties properties) {
        super(damage, cooldown, poise, size, flyingPower, flyingTime, ricochet, pierce, ammoAmount, durability, enchantability, properties);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<>(this, "Shoot", 0, state -> PlayState.STOP)
                .triggerableAnim("shoot", SHOOT_ANIM)
                .triggerableAnim("stop", STOP));
    }
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.factory;
    }
    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private GatlingGunGeoRenderer renderer;
            // Don't instantiate until ready. This prevents race conditions breaking things
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null)
                    this.renderer = new GatlingGunGeoRenderer();

                return renderer;
            }

        });
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        int ammoAmount = this.getAmmoAmount();
        if (this.hasAmmo(player, ammoAmount) || player.isCreative()) {
            player.startUsingItem(hand);
            return InteractionResultHolder.success(player.getItemInHand(hand));
        }else {
            return InteractionResultHolder.fail(player.getItemInHand(hand));
        }
    }
    @Override
    public void doLeftClickAction(Player player, ItemStack stackIn) {
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.NONE;
    }

    @Override
    public void onUseTick(Level level, LivingEntity e, ItemStack stack, int useTick) {
        super.onUseTick(level, e, stack, useTick);
        if (e instanceof Player player) {
            int ammoAmount = this.getAmmoAmount();
            if (this.hasAmmo(player, ammoAmount) || player.isCreative()) {
                if (level instanceof ServerLevel serverLevel) {
                    triggerAnim(player, GeoItem.getOrAssignId(stack, serverLevel), "Shoot", "shoot");
                }
                int startingTick = 25;
                if(useTick>=startingTick) {
                    if ((useTick-startingTick) % 3 == 0) {
                        this.shoot(player, level, stack);
                        if(player.getItemInHand(InteractionHand.MAIN_HAND)==stack) {
                            stack.hurtAndBreak(1, player, (p_43276_) -> {
                                p_43276_.broadcastBreakEvent(InteractionHand.MAIN_HAND);
                            });
                        }
                        if(player.getItemInHand(InteractionHand.OFF_HAND)==stack) {
                            stack.hurtAndBreak(1, player, (p_43276_) -> {
                                p_43276_.broadcastBreakEvent(InteractionHand.OFF_HAND);
                            });
                        }
                    }
                    if ((useTick-startingTick) % 12 == 0) {
                        if (!player.isCreative()) {
                            this.consumeAmmoApplyLuck(player, ammoAmount, this.getLuck(player, stack));
                        }
                    }
                }
            }else{
                player.stopUsingItem();
            }
        }

    }

    @Override
    public void shoot(Player player, Level level, ItemStack stackIn) {
        Vec3 pos = player.position();
        Vec3 aim = player.getLookAngle();


        float x = (float) (pos.x + 0.6 * aim.x);
        float y = (float) (pos.y + 0.8 + 0.6 * aim.y);
        float z = (float) (pos.z + 0.6 * aim.z);

        Bullet entity = new Bullet(EntityInit.BULLET.get(), level);
        entity.setPos(x, y, z);
        float flyingPower = this.flyingPower(player, stackIn);
        //float flyingPower = 0.5f;
        entity.xPower = flyingPower * aim.x;
        entity.yPower = flyingPower * aim.y;
        entity.zPower = flyingPower * aim.z;
        entity.setOwner(player);
        entity.setAttackDamage(this.getAttackDamage(player, stackIn));
        entity.setPoiseDamage(this.getPoiseDamage(player, stackIn));
        entity.setFlyingTime(this.getFlyingTime(stackIn));
        entity.setBlood(this.getBlood(player,stackIn));
        entity.setPoison(this.getPoison(player,stackIn));
        entity.setFire(this.isFire(player,stackIn));
        entity.setExplosion(this.getExplosion(stackIn));
        entity.setThunder(this.isLightning(player,stackIn));
        entity.setSize(this.getBaseSize());
        entity.setPierce(this.getPierce(player, stackIn));
        entity.setRicochet(this.getRicochet(player, stackIn));
        level.addFreshEntity(entity);



        player.level().playSound((Player) null, player, SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1.0F, 1.0f);
    }

    @Override
    public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
        super.onStopUsing(stack, entity, count);
        if(entity instanceof Player player) {
            if (player.level() instanceof ServerLevel serverLevel) {
                triggerAnim(player, GeoItem.getOrAssignId(stack, serverLevel), "Shoot", "stop");
            }
            player.getCooldowns().addCooldown(stack.getItem(), this.getUseCooldown(player, stack));
        }

    }
}
