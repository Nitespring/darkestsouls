package github.nitespring.darkestsouls.common.entity.projectile.throwable;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.core.util.CustomBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
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
import org.jetbrains.annotations.Nullable;

public class FirebombEntity extends AbstractHurtingProjectile{

    public float gravPower = 0;
    public float attackDamage = 4.0f;
    public int poiseDamage = 6;
    protected static final EntityDataAccessor<Integer> BOMB_TYPE = SynchedEntityData.defineId(FirebombEntity.class, EntityDataSerializers.INT);
    public double verticalSpread = 2.0f;
    public double horizontalSpread = 2.0f;
    public int explodingTick = 0;
    public boolean isExploding = false;
    public FirebombEntity(EntityType<? extends AbstractHurtingProjectile> p_36833_, Level p_36834_) {
        super(p_36833_, p_36834_);
    }


    public int getBombType() {return entityData.get(BOMB_TYPE);}
    public float getAttackDamage() {return this.attackDamage;}
    public int getPoiseDamage() {return this.poiseDamage;}
    public float getGravpower() {return this.gravPower;}

    public void setAttackDamage(float f) {this.attackDamage=f;}
    public void setPoiseDamage(int i) {this.poiseDamage=i;}
    public void setGravPower(float f) {this.gravPower=f;}
    public void setBombType(int type) {entityData.set(BOMB_TYPE, type);}
    public void setVerticalSpread(double f) {this.verticalSpread=f;}
    public void setHorizontalSpread(double f) {this.horizontalSpread=f;}

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(BOMB_TYPE, 0);
    }
    @Override
    public boolean isOnFire() {
        return false;
    }

    @Nullable
    @Override
    protected ParticleOptions getTrailParticle() {
        return ParticleTypes.SMOKE;
    }

    @Override
    public void tick() {
        super.tick();
        if(!this.isExploding) {
            this.setDeltaMovement(this.getDeltaMovement().add(0, -this.getGravpower() * Math.pow(tickCount, 9/4), 0));
        }
        if(this.tickCount>=1000){
            this.discard();
        }
        if(this.isExploding){
            explodingTick++;
            this.firebombExplosion();
        }
        if(this.explodingTick>4){
            this.discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult p_37259_) {
        super.onHitEntity(p_37259_);
        if(p_37259_.getEntity()!=this.getOwner()){
            doOnHit();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult p_37258_) {
        super.onHitBlock(p_37258_);
       doOnHit();

    }

    public void doOnHit(){
        this.isExploding=true;
        /*this.xPower=0;
        this.yPower=0;
        this.zPower=0;*/
        this.accelerationPower=0;
        this.setDeltaMovement(0,0,0);
    }

    public void playExplosionSound(){
        this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.DRAGON_FIREBALL_EXPLODE, this.getSoundSource(), 0.8F+this.random.nextFloat() * 0.5F, this.random.nextFloat() * 0.2F + 1.0F, true);
           if(this.getOwner() instanceof Player) {
               this.level().playLocalSound(this.getOwner().getX(), this.getOwner().getY(), this.getOwner().getZ(), SoundEvents.DRAGON_FIREBALL_EXPLODE, this.getSoundSource(), 0.1F + this.random.nextFloat() * 0.05F, this.random.nextFloat() * 0.2F + 1.0F, true);
           }
    }
    public void firebombExplosion(){

        this.playExplosionSound();
        for(LivingEntity livingentity : level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(this.horizontalSpread+0.5, this.verticalSpread+0.25, this.horizontalSpread+0.5))) {
            if(((livingentity instanceof Player||livingentity!=this.getOwner())&&!(this.getOwner().isAlliedTo(livingentity)))) {
                livingentity.igniteForTicks(60);
                if(livingentity.hurtTime<=0) {
                    if (livingentity instanceof DarkestSoulsAbstractEntity) {
                        ((DarkestSoulsAbstractEntity) livingentity).damagePoiseHealth(this.poiseDamage);
                    }
                    livingentity.hurt(this.level().damageSources().mobProjectile(this, (LivingEntity) this.getOwner()), this.getAttackDamage());
                }
            }

        }

        /*List<BlockPos> blockList= BlockPos.betweenClosedStream(this.getBoundingBox().inflate(this.horizontalSpread, this.verticalSpread, this.horizontalSpread)).toList();
        for(int i = 0; i<blockList.size(); i++) {
            BlockPos blockPos = blockList.get(i);
            BlockPos blockPos1 = blockPos.relative(Direction.getNearest(position().x(),position().y(),position().z()));
            if(BaseFireBlock.canBePlacedAt(level(),blockPos, Direction.getNearest(position().x(),position().y(),position().z())));
            BlockState blockstate1 = BaseFireBlock.getState(level(), blockPos1);
            level().setBlock(blockPos1, blockstate1, 11);
            level().gameEvent(this, GameEvent.BLOCK_PLACE, blockPos);
        }*/
        int xSpread = Math.toIntExact((long) (this.getBoundingBox().getXsize() * horizontalSpread));
        int zSpread = Math.toIntExact((long) (this.getBoundingBox().getZsize() * horizontalSpread));
        int ySpread = Math.toIntExact((long) (this.getBoundingBox().getYsize() * verticalSpread));
        int x0 = this.blockPosition().getX();
        int y0 = this.blockPosition().getY();
        int z0 = this.blockPosition().getZ();
        /*
        for(int i = -xSpread; i<=xSpread; i++) {
            for(int j = -zSpread;  j<=zSpread; j++) {
                for(int k = -ySpread; k<=ySpread; k++) {
                    int xVar = i;
                    int yVar = k;
                    int zVar = j;
                    int x= x0+xVar;
                    int z= z0+zVar;
                    int y = y0+yVar;

                    BlockPos blockPos = new BlockPos(x,y,z);
                    BlockPos blockPos1 = blockPos.relative(Direction.getNearest(position().x(),position().y(),position().z()));
                    if(BaseFireBlock.canBePlacedAt(level(),blockPos, Direction.getNearest(position().x(),position().y(),position().z()))) {
                        BlockState blockstate1 = BaseFireBlock.getState(level(), blockPos);
                        level().setBlock(blockPos, blockstate1, 11);
                        level().gameEvent(this, GameEvent.BLOCK_PLACE, blockPos);
                    }
                    level().addParticle(ParticleTypes.FLAME,x,y,z,0,0,0);
                }
            }
        }
        */
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
                    for(int n = 0; n<=1; n++) {
                        double xVar1 = d*Math.sin(i*a);
                        float yVar1 = k/2;
                        double zVar1 = d*Math.cos(i*a);;
                        level().addParticle(getExplosionParticleI(),
                                x0+0.6*(1+2.5*n)*xVar1 + 0.25 * (random.nextFloat() -0.5),
                                y0+0.05*(1+2.5*n)*yVar1 + 0.25 * (random.nextFloat() -0.5),
                                z0+0.6*(1+2.5*n)*zVar1 + 0.25 * (random.nextFloat() -0.5),
                                0.1*xVar1 + 0.25 * (random.nextFloat() -0.5),
                                0.02f*yVar1 + 0.15 * (random.nextFloat() -0.5),
                                0.1*zVar1 + 0.25 * (random.nextFloat() -0.5));
                        level().addParticle(getExplosionParticleII(),
                                x0+0.5*(1+2.5*n)*xVar1 + 0.25 * (random.nextFloat() -0.5),
                                y0+0.15*(1+2.5*n)*yVar1 + 0.25 * (random.nextFloat() -0.5),
                                z0+0.5*(1+2.5*n)*zVar1 + 0.25 * (random.nextFloat() -0.5),
                                0.15*xVar1 + 0.25 * (random.nextFloat() -0.5),
                                0.1f*yVar1 + 0.05 * (random.nextFloat() -0.5),
                                0.15*zVar1 + 0.25 * (random.nextFloat() -0.5));
                    }
                }
            }
        }
    }
    public ParticleOptions getExplosionParticleI(){
        return ParticleTypes.FLAME;
    }
    public ParticleOptions getExplosionParticleII(){
        return ParticleTypes.SMOKE;
    }
}
