package github.nitespring.darkestsouls.common.entity.projectile.spell;

import github.nitespring.darkestsouls.core.util.CustomBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ChaosFireball extends FireBallEntity implements GeoEntity {

    protected AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);

    public ChaosFireball(EntityType<? extends FireBallEntity> e, Level level) {super(e, level);}

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {return this.factory;}

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<>(this, "main_controller", 0, this::predicate));
    }

    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
        event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.fireball.new"));
        return PlayState.CONTINUE;
    }

    @Override
    public int getFireballType() {
        return 1;
    }

    @Override
    public void tick() {
        super.tick();
        this.setDeltaMovement(this.getDeltaMovement().add(0,-(0.005+0.25*this.lifeTicks/this.maxLifeTime),0));
    }

    @Override
    public void doExplosion(){
        for (LivingEntity e : this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(3.5D, 1.5D, 3.5D))) {
            if(e!=this.getOwner()&&!(this.getOwner()!=null&&e.isAlliedTo(this.getOwner()))) {
                e.hurt(e.level().damageSources().explosion(this, this.getOwner()), this.damage);
                e.igniteForTicks(80);
                for(int i=0; i<=5; i++){
                    RandomSource r = this.random;
                    Vec3 off = new Vec3(r.nextFloat()-0.5, r.nextFloat()-0.5,r.nextFloat()-0.5).multiply(0.25f,0.25f,0.25f);
                    e.level().addAlwaysVisibleParticle(ParticleTypes.FLAME,
                            e.position().x+0.5*e.getBbWidth()*off.x, e.position().y + 0.5 + 0.5*e.getBbHeight()*off.y, e.position().z +0.5*e.getBbWidth()*off.z, off.x*r.nextFloat(), off.y*r.nextFloat(), off.z*r.nextFloat());
                }
            }
        }
        if(!this.level().isClientSide()) {
            this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.FIRE_EXTINGUISH, this.getSoundSource(), 1.0F, this.random.nextFloat() * 0.2F + 0.85F, false);
            this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.GENERIC_EXPLODE.value(), this.getSoundSource(), 2.5F, this.random.nextFloat() * 0.2F + 0.85F, false);
            this.level().playSound(this, this.blockPosition(), SoundEvents.GENERIC_EXPLODE.value(), SoundSource.NEUTRAL, 4.5F, 1.0f);
        }

        for (int i = 0; i <= 20*this.getDimensionScale(); i++) {
            RandomSource r = this.random;
            Vec3 off = new Vec3(r.nextFloat() - 0.5, r.nextFloat() - 0.5, r.nextFloat() - 0.5).multiply(1.75f, 1.75f, 1.75f);
            this.level().addParticle(ParticleTypes.FLAME,
                    this.position().x + 1.5 * this.getDimensionScale() * off.x, this.position().y + 1.5 + 0.5 * this.getDimensionScale() * off.y, this.position().z + 1.5 * this.getDimensionScale() * off.z, off.x * 2.0f*r.nextFloat(), off.y *2.0f* r.nextFloat(), off.z *2.0f* r.nextFloat());
        }
        for (int k = 0; k <= 1; k++) {
            for (int i = 0; i <= 12; i++) {
                RandomSource r = this.random;
                double a=  Math.PI/6;
                double d = 1+1.25*k;
                Vec3 pos = this.position();
                Vec3 pos1 = new Vec3(pos.x+d*Math.sin(i*a)+(r.nextFloat()-0.5)*0.25, pos.y-0.15f, pos.z+d*Math.cos(i*a)+(r.nextFloat()-0.5)*0.25);
                MagmaEntity magma = new MagmaEntity(this.level(), this.damage/2,pos1.x,pos1.y,pos1.z, (LivingEntity) this.getOwner());
                magma.lifeTicks=160;
                this.level().addFreshEntity(magma);
            }
        }
        this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.LAVA_AMBIENT, this.getSoundSource(), 0.2F, this.random.nextFloat() * 0.2F + 0.65F, false);

        this.doRemoval();
        Vec3 pos = this.position();
        Level world = this.level();
        int xSpread = Math.toIntExact((long) (this.getBoundingBox().getXsize() * 1.0));
        int zSpread = Math.toIntExact((long) (this.getBoundingBox().getZsize() * 1.0));
        int ySpread = Math.toIntExact((long) (this.getBoundingBox().getYsize() * 1.0));
        int x0 = this.blockPosition().getX();
        int y0 = this.blockPosition().getY();
        int z0 = this.blockPosition().getZ();
        for(int i = 0; i<=24; i++) {
            for (int j = 0; j <= zSpread; j++) {
                for (int k = -ySpread; k <= ySpread; k++) {
                    double a = Math.PI / 12;
                    double d = j;
                    int xVar = (int) (d * Math.sin(i * a));
                    int yVar = k;
                    int zVar = (int) (d * Math.cos(i * a));
                    ;
                    int x = x0 + xVar;
                    int z = z0 + zVar;
                    int y = y0 + yVar;

                    BlockPos blockPos = new BlockPos(x, y, z);
                    if (level().getBlockState(blockPos).is(CustomBlockTags.BOMB_BREAKABLE)) {
                        level().destroyBlock(blockPos, true, this.getOwner());
                        level().gameEvent(this, GameEvent.BLOCK_DESTROY, blockPos);
                    }
                    if(BaseFireBlock.canBePlacedAt(level(),blockPos, Direction.getNearest(pos.x,pos.y,pos.z))) {
                        BlockState blockstate = BaseFireBlock.getState(level(), blockPos);
                        level().setBlock(blockPos, blockstate, 11);
                        level().gameEvent(this, GameEvent.BLOCK_PLACE, blockPos);
                    }
                }
            }
        }
    }
}
