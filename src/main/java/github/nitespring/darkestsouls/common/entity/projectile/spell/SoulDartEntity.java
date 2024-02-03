package github.nitespring.darkestsouls.common.entity.projectile.spell;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.joml.Random;
import org.joml.Vector3f;

public class SoulDartEntity extends AbstractHurtingProjectile {

    public float damage=2.0f;
    public int maxLifeTime=20;
    public static float dScale=1;
    public int lifeTicks=0;

    public SoulDartEntity(EntityType<? extends AbstractHurtingProjectile> e, Level level) {
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
        float rotY = (float) Mth.atan2(mov.z, mov.x);
        this.setYRot((float)(Mth.atan2(-mov.x, -mov.z) * (double)(180F / (float)Math.PI)));


        this.level().addAlwaysVisibleParticle(ParticleTypes.SOUL_FIRE_FLAME, this.position().x, this.position().y + 0.6, this.position().z, 0, 0, 0);
        for(int i=0; i<=1+20*this.getDimensionScale(); i++){
            RandomSource r = this.random;
            Vec3 off = new Vec3(r.nextFloat()-0.5, r.nextFloat()-0.5,r.nextFloat()-0.5).multiply(0.5f,0.5f,0.5f);
            this.level().addAlwaysVisibleParticle(new DustParticleOptions(new Vector3f(0.2f, 0.8f, 1), 0.5f),
                    this.position().x+0.5*this.getDimensionScale()*off.x, this.position().y + 0.5 + 0.5*this.getDimensionScale()*off.y, this.position().z +0.5*this.getDimensionScale()*off.z, off.x*r.nextFloat(), off.y*r.nextFloat(), off.z*r.nextFloat());
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
       return new DustParticleOptions(new Vector3f(0.0f,0.8f,1), 1.0f);
        //return ParticleTypes.SCULK_SOUL;
    }

    @Override
    protected void onHitEntity(EntityHitResult p_37259_) {
        super.onHitEntity(p_37259_);
        Entity e = p_37259_.getEntity();
        e.hurt(e.level().damageSources().indirectMagic(this, this.getOwner()),this.damage);
    }
}
