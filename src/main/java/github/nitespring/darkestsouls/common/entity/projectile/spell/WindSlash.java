package github.nitespring.darkestsouls.common.entity.projectile.spell;

import github.nitespring.darkestsouls.core.util.CustomBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

public class WindSlash extends AbstractHurtingProjectile {

    public float damage=2.0f;
    public int maxLifeTime=20;
    public int lifeTicks=0;

    //protected static final EntityDataAccessor<Integer> INITIAL_X_ROT = SynchedEntityData.defineId(WindSlash.class, EntityDataSerializers.INT);
    //protected static final EntityDataAccessor<Integer> INITIAL_Y_ROT = SynchedEntityData.defineId(WindSlash.class, EntityDataSerializers.INT);

    public WindSlash(EntityType<? extends AbstractHurtingProjectile> e, Level level) {
        super(e, level);
    }
    public WindSlash(EntityType<? extends AbstractHurtingProjectile> e, Level level, float rot,float rot1) {
        this(e, level);
        this.setYRot(rot * (180F / (float)Math.PI));
        this.setXRot((rot1 *(180F / (float) Math.PI)));
    }

    public void setDamage(float f){
        this.damage=f;
    }
    public void setMaxLifeTime(int i){
        this.maxLifeTime=i;
    }

    @Override
    public void tick() {
        super.tick();
        this.lifeTicks++;
        if(lifeTicks>=this.maxLifeTime){
            this.doRemoval();
        }
        Vec3 mov = this.getDeltaMovement();

        double d0 = mov.horizontalDistance();
        if(mov!=null) {
            this.setYRot((float) (Mth.atan2(mov.x, mov.z) * (double) (180F / (float) Math.PI)));
            this.setXRot((float) (Mth.atan2(mov.y, d0) * (double) (180F / (float) Math.PI)));
        }
        for(int i=0; i<=3; i++){
            RandomSource r = this.random;
            Vec3 off = new Vec3(r.nextFloat()-0.5, r.nextFloat()-0.5,r.nextFloat()-0.5).multiply(0.5f,0.5f,0.5f);
            //this.level().addAlwaysVisibleParticle(new DustParticleOptions(new Vector3f(1f, 1f, 0), 0.5f),
            //       this.position().x+0.5*this.getDimensionScale()*off.x, this.position().y + 0.5 + 0.5*this.getDimensionScale()*off.y, this.position().z +0.5*this.getDimensionScale()*off.z, off.x*r.nextFloat(), off.y*r.nextFloat(), off.z*r.nextFloat());
            this.level().addAlwaysVisibleParticle(ParticleTypes.WHITE_SMOKE,
                    this.position().x + 1.5  * off.x, this.position().y + 0.5 +this.getBbHeight()*1.5f * off.y, this.position().z + 1.5  * off.z, off.x * r.nextFloat(), off.y * r.nextFloat(), off.z * r.nextFloat());
        }
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
                    if (level().getBlockState(blockPos).is(CustomBlockTags.FLAME_BREAKABLE)) {
                        level().destroyBlock(blockPos, true, this.getOwner());
                        level().gameEvent(this, GameEvent.BLOCK_DESTROY, blockPos);
                    }
                }
            }
        }

    }

    public void doRemoval(){
        this.onRemoval();
        this.remove(RemovalReason.DISCARDED);
    }
    public void onRemoval(){}


    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity e) {
        return new ClientboundAddEntityPacket(this,e);
    }
    @Override
    public boolean isOnFire() {
        return false;
    }

    @Nullable
    @Override
    protected ParticleOptions getTrailParticle() {
        return ParticleTypes.WHITE_SMOKE;
       //return ParticleTypes.ELECTRIC_SPARK;
        //return ParticleTypes.SCULK_SOUL;
    }

    @Override
    protected void onHitEntity(EntityHitResult p_37259_) {
        super.onHitEntity(p_37259_);
        Entity e = p_37259_.getEntity();
        e.hurt(e.level().damageSources().mobProjectile(this, (LivingEntity) this.getOwner()),this.damage);
        //this.level().playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundEvents.WOOL_BREAK, this.getSoundSource(), 1.0f, 2.0f);
        for(int i=0; i<=12; i++){
            RandomSource r = this.random;
            Vec3 off = new Vec3(r.nextFloat()-0.5, r.nextFloat()-0.5,r.nextFloat()-0.5).multiply(0.5f,0.5f,0.5f);
            //this.level().addAlwaysVisibleParticle(new DustParticleOptions(new Vector3f(1f, 1f, 0), 0.5f),
             //       this.position().x+0.5*this.getDimensionScale()*off.x, this.position().y + 0.5 + 0.5*this.getDimensionScale()*off.y, this.position().z +0.5*this.getDimensionScale()*off.z, off.x*r.nextFloat(), off.y*r.nextFloat(), off.z*r.nextFloat());
            this.level().addAlwaysVisibleParticle(ParticleTypes.WHITE_SMOKE,
                    this.position().x + 0.5  * off.x, this.position().y + 0.5 +this.getBbHeight()*0.5f * off.y, this.position().z + 0.5  * off.z, off.x * r.nextFloat(), off.y * r.nextFloat(), off.z * r.nextFloat());
        }
        this.level().addAlwaysVisibleParticle(ParticleTypes.EXPLOSION,
                this.position().x, this.position().y + 0.5, this.position().z, 0, 0, 0);
        this.doDiscard();
    }


    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        BlockState block = this.level().getBlockState(result.getBlockPos());
        if(block.is(CustomBlockTags.BOMB_BREAKABLE)){
            this.level().destroyBlock(result.getBlockPos(), true, this.getOwner());
            level().gameEvent(this, GameEvent.BLOCK_DESTROY, result.getBlockPos());
        }
        this.doDiscard();
        this.level().addAlwaysVisibleParticle(ParticleTypes.EXPLOSION,
                this.position().x, this.position().y + 0.5, this.position().z, 0, 0, 0);
    }

    public void doDiscard(){


            this.level().playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundEvents.WOOL_BREAK, this.getSoundSource(), 1.0f, 2.0f);
            for (int i = 0; i <= 8; i++) {
                RandomSource r = this.random;
                Vec3 off = new Vec3(r.nextFloat() - 0.5, r.nextFloat() - 0.5, r.nextFloat() - 0.5).multiply(0.75f, 0.75f, 0.75f);
               // this.level().addAlwaysVisibleParticle(ParticleTypes.ELECTRIC_SPARK,
                //        this.position().x + 0.5 * this.getDimensionScale() * off.x, this.position().y + 0.5 + 0.5 * this.getDimensionScale() * off.y, this.position().z + 0.5 * this.getDimensionScale() * off.z, off.x * r.nextFloat(), off.y * r.nextFloat(), off.z * r.nextFloat());
                if(this.level() instanceof ServerLevel level) {
                    //level.sendParticles( ParticleTypes.ELECTRIC_SPARK, this.position().x+off.x, this.position().y +this.getBbHeight()*0.5f +off.y, this.position().z+off.z, 5,  off.x, off.y + 0.05D, off.z, 0.05D + 5*0.003);
                    level.sendParticles( ParticleTypes.WHITE_SMOKE, this.position().x+off.x, this.position().y+this.getBbHeight()*0.5f+off.y, this.position().z+off.z, 5,  off.x, off.y + 0.05D, off.z, 0.05D + 5*0.003);
                }
            }
        if(this.level() instanceof ServerLevel level) {
            level.addAlwaysVisibleParticle(ParticleTypes.EXPLOSION,
                    this.position().x, this.position().y + 0.5, this.position().z, 0, 0, 0);
        }
        super.discard();
    }
}
