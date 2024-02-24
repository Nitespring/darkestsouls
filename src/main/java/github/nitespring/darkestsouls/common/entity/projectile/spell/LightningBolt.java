package github.nitespring.darkestsouls.common.entity.projectile.spell;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

public class LightningBolt extends AbstractHurtingProjectile {

    public float damage=2.0f;
    public int maxLifeTime=20;
    public static float dScale=1;
    public int lifeTicks=0;

    public LightningBolt(EntityType<? extends AbstractHurtingProjectile> e, Level level) {
        super(e, level);
    }
    public LightningBolt(EntityType<? extends AbstractHurtingProjectile> e, Level level, float rot) {
        this(e, level);
        this.setYRot(rot * (180F / (float)Math.PI));
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

        double d0 = mov.horizontalDistance();
        //float rotY = (float) Mth.atan2(mov.z, mov.x);
        if(mov!=null) {
            this.setYRot((float) (Mth.atan2(mov.x, mov.z) * (double) (180F / (float) Math.PI)));
            this.setXRot((float) (Mth.atan2(mov.y, d0) * (double) (180F / (float) Math.PI)));
        }
        //this.setYRot((float)(Mth.atan2(-mov.x, -mov.z) * (double)(180F / (float)Math.PI)));



        for(int k=-1; k<=1; k++) {
            this.level().addAlwaysVisibleParticle(ParticleTypes.ELECTRIC_SPARK, this.position().x+k*0.25F*mov.x, this.position().y + 0.6 + k*0.25F*mov.y, this.position().z + k*0.25F*mov.z, 0, 0, 0);
            for (int i = 0; i <= 2; i++) {
                RandomSource r = this.random;
                Vec3 off = new Vec3(r.nextFloat() - 0.5, r.nextFloat() - 0.5, r.nextFloat() - 0.5).multiply(0.75f, 0.75f, 0.75f);
                this.level().addAlwaysVisibleParticle(new DustParticleOptions(new Vector3f(1.0f, 1.0f, 0), 0.5f),
                        this.position().x + 0.5 * this.getDimensionScale() * off.x+k*0.25F*mov.x, this.position().y + 0.6 + 0.5 * this.getDimensionScale() * off.y+k*0.25F*mov.y, this.position().z + 0.5 * this.getDimensionScale() * off.z+k*0.25F*mov.z,
                        off.x * r.nextFloat(), off.y * r.nextFloat(), off.z * r.nextFloat());
            }
        }
    }

    public void doRemoval(){
        this.onRemoval();
        this.remove(RemovalReason.DISCARDED);
    }
    public void onRemoval(){}

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Nullable
    @Override
    protected ParticleOptions getTrailParticle() {
        return new DustParticleOptions(new Vector3f(1.0f, 1.0f, 0), 0.5f);
       //return ParticleTypes.ELECTRIC_SPARK;
        //return ParticleTypes.SCULK_SOUL;
    }

    @Override
    protected void onHitEntity(EntityHitResult p_37259_) {
        super.onHitEntity(p_37259_);
        Entity e = p_37259_.getEntity();
        e.hurt(e.level().damageSources().indirectMagic(this, this.getOwner()),this.damage);
        this.level().playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundEvents.LIGHTNING_BOLT_IMPACT, this.getSoundSource(), 1.0f, 2.0f);
        for(int i=0; i<=6; i++){
            RandomSource r = this.random;
            Vec3 off = new Vec3(r.nextFloat()-0.5, r.nextFloat()-0.5,r.nextFloat()-0.5).multiply(0.5f,0.5f,0.5f);
            //this.level().addAlwaysVisibleParticle(new DustParticleOptions(new Vector3f(1f, 1f, 0), 0.5f),
             //       this.position().x+0.5*this.getDimensionScale()*off.x, this.position().y + 0.5 + 0.5*this.getDimensionScale()*off.y, this.position().z +0.5*this.getDimensionScale()*off.z, off.x*r.nextFloat(), off.y*r.nextFloat(), off.z*r.nextFloat());
            this.level().addAlwaysVisibleParticle(ParticleTypes.ELECTRIC_SPARK,
                    this.position().x + 0.5 * this.getDimensionScale() * off.x, this.position().y + 0.5 +this.getBbHeight()*0.5f * off.y, this.position().z + 0.5 * this.getDimensionScale() * off.z, off.x * r.nextFloat(), off.y * r.nextFloat(), off.z * r.nextFloat());
        }
    }


    @Override
    protected void onHitBlock(BlockHitResult p_37258_) {
        super.onHitBlock(p_37258_);
        this.doDiscard();
    }

    public void doDiscard(){


            this.level().playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundEvents.LIGHTNING_BOLT_IMPACT, this.getSoundSource(), 1.0f, 2.0f);
            for (int i = 0; i <= 8; i++) {
                RandomSource r = this.random;
                Vec3 off = new Vec3(r.nextFloat() - 0.5, r.nextFloat() - 0.5, r.nextFloat() - 0.5).multiply(0.75f, 0.75f, 0.75f);
               // this.level().addAlwaysVisibleParticle(ParticleTypes.ELECTRIC_SPARK,
                //        this.position().x + 0.5 * this.getDimensionScale() * off.x, this.position().y + 0.5 + 0.5 * this.getDimensionScale() * off.y, this.position().z + 0.5 * this.getDimensionScale() * off.z, off.x * r.nextFloat(), off.y * r.nextFloat(), off.z * r.nextFloat());
                if(this.level() instanceof ServerLevel level) {
                    level.sendParticles( ParticleTypes.ELECTRIC_SPARK, this.position().x+off.x, this.position().y +this.getBbHeight()*0.5f +off.y, this.position().z+off.z, 5,  off.x, off.y + 0.05D, off.z, 0.05D + 5*0.003);
                    level.sendParticles( new DustParticleOptions(new Vector3f(1.0f, 1.0f, 0), 0.5f), this.position().x+off.x, this.position().y+this.getBbHeight()*0.5f+off.y, this.position().z+off.z, 5,  off.x, off.y + 0.05D, off.z, 0.05D + 5*0.003);
                }
            }

        super.discard();
    }


}
