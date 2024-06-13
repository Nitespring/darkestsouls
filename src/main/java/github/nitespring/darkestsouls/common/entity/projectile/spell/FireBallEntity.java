package github.nitespring.darkestsouls.common.entity.projectile.spell;

import github.nitespring.darkestsouls.core.util.CustomBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

public class FireBallEntity extends AbstractHurtingProjectile {

    public float damage=2.0f;
    public int maxLifeTime=60;
    public static float dScale=1;
    public int lifeTicks=0;

    public FireBallEntity(EntityType<? extends AbstractHurtingProjectile> e, Level level) {
        super(e, level);
    }

    public void setDamage(float f){
        this.damage=f;
    }
    public void setMaxLifeTime(int i){
        this.maxLifeTime=i;
    }
    public void setDimensionScale(float f){
        this.dScale=f;
    }
    public float getDimensionScale(){
        return dScale;
    }

    @Override
    public void tick() {
        super.tick();
        this.lifeTicks++;
        if(lifeTicks>=this.maxLifeTime){
            this.doRemoval();
        }
        Vec3 mov = this.getDeltaMovement();
        this.level().addAlwaysVisibleParticle(ParticleTypes.FLAME, this.position().x, this.position().y + 0.6, this.position().z, 0, 0, 0);
        this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.FIRE_AMBIENT, this.getSoundSource(), 0.6F, this.random.nextFloat() * 0.2F + 0.85F, false);
        for(int i=0; i<=1+20*this.getDimensionScale(); i++){
            RandomSource r = this.random;
            Vec3 off = new Vec3(r.nextFloat()-0.5, r.nextFloat()-0.5,r.nextFloat()-0.5).multiply(0.25f,0.25f,0.25f);
            this.level().addAlwaysVisibleParticle(ParticleTypes.FLAME,
                    this.position().x+0.5*this.getDimensionScale()*off.x, this.position().y + 0.5 + 0.5*this.getDimensionScale()*off.y, this.position().z +0.5*this.getDimensionScale()*off.z, off.x*r.nextFloat(), off.y*r.nextFloat(), off.z*r.nextFloat());
        }
    }

    public void doRemoval(){
        this.onRemoval();
        //this.doExplosion();
        this.remove(RemovalReason.DISCARDED);
    }
    public void onRemoval(){}

    public int getFireballType(){return 0;}

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Nullable
    @Override
    protected ParticleOptions getTrailParticle() {
       //return new DustParticleOptions(new Vector3f(0.0f,0.8f,1), 1.0f);
        return ParticleTypes.FLAME;
    }

    @Override
    protected void onHitBlock(BlockHitResult p_37258_) {
        super.onHitBlock(p_37258_);

        this.doExplosion();
        this.level().playSound((Player) null, this, SoundEvents.GENERIC_EXPLODE.value(), SoundSource.PLAYERS, 2.0F, 1.0f);
    }

    @Override
    protected void onHitEntity(EntityHitResult p_37259_) {
        super.onHitEntity(p_37259_);
        Entity e = p_37259_.getEntity();
       //e.hurt(e.level().damageSources().explosion(this, this.getOwner()),this.damage);
        if(e!=this.getOwner()&&!(this.getOwner()!=null&&e.isAlliedTo(this.getOwner()))) {
            this.doExplosion();
            this.level().playSound((Player) null, this, SoundEvents.GENERIC_EXPLODE.value(), SoundSource.PLAYERS, 2.0F, 1.0f);
        }
    }

    public void doExplosion(){
        for (LivingEntity e : this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(1.5D, 1.5D, 1.5D))) {
            if(e!=this.getOwner()&&!(this.getOwner()!=null&&e.isAlliedTo(this.getOwner()))) {
                e.hurt(e.level().damageSources().explosion(this, this.getOwner()), this.damage);
                e.igniteForTicks(80);
            }
        }
        this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.FIRE_EXTINGUISH, this.getSoundSource(), 1.0F, this.random.nextFloat() * 0.2F + 0.85F, false);
        this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.GENERIC_EXPLODE.value(), this.getSoundSource(), 1.0F, this.random.nextFloat() * 0.2F + 0.85F, false);
        for(int i=0; i<=1+50*this.getDimensionScale(); i++){
            RandomSource r = this.random;
            Vec3 off = new Vec3(r.nextFloat()-0.5, r.nextFloat()-0.5,r.nextFloat()-0.5).multiply(0.75f,0.75f,0.75f);
            this.level().addAlwaysVisibleParticle(ParticleTypes.FLAME,
                    this.position().x+0.5*this.getDimensionScale()*off.x, this.position().y + 0.5 + 0.5*this.getDimensionScale()*off.y, this.position().z +0.5*this.getDimensionScale()*off.z, off.x*r.nextFloat(), off.y*r.nextFloat(), off.z*r.nextFloat());
        }





        int xSpread = Math.toIntExact((long) (this.getBoundingBox().getXsize() * 1.75));
        int zSpread = Math.toIntExact((long) (this.getBoundingBox().getZsize() * 1.75));
        int ySpread = Math.toIntExact((long) (this.getBoundingBox().getYsize() * 1.75));
        int x0 = this.blockPosition().getX();
        int y0 = this.blockPosition().getY();
        int z0 = this.blockPosition().getZ();
        for(int i = 0; i<=24; i++) {
            for(int j = 0;  j<=zSpread; j++) {
                for(int k = -ySpread; k<=ySpread; k++) {
                    double a=  Math.PI/12;
                    double d = j;
                    int xVar = (int) (d*Math.sin(i*a));
                    int yVar = k;
                    int zVar = (int) (d*Math.cos(i*a));;
                    int x= x0+xVar;
                    int z= z0+zVar;
                    int y = y0+yVar;

                    BlockPos blockPos = new BlockPos(x,y,z);

                    if(level().getBlockState(blockPos).is(CustomBlockTags.BOMB_BREAKABLE)){
                        level().destroyBlock(blockPos, true, this.getOwner());
                        level().gameEvent(this, GameEvent.BLOCK_DESTROY, blockPos);
                    }

                    if(BaseFireBlock.canBePlacedAt(level(),blockPos, Direction.getNearest(x0,y0,z0))) {
                        BlockState blockstate1 = BaseFireBlock.getState(level(), blockPos);
                        level().setBlock(blockPos, blockstate1, 11);
                        level().gameEvent(this, GameEvent.BLOCK_PLACE, blockPos);
                    }
                }
            }
        }






        this.doRemoval();
    }


}
