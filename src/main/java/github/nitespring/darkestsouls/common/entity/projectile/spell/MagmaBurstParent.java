package github.nitespring.darkestsouls.common.entity.projectile.spell;

import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.joml.Random;

public class MagmaBurstParent extends MagmaBurstEntity{


    public MagmaBurstParent(EntityType<? extends AbstractHurtingProjectile> p_36833_, Level p_36834_) {
        super(p_36833_, p_36834_);
    }

    public MagmaBurstParent(EntityType<? extends AbstractHurtingProjectile> p_310629_, double p_311590_, double p_312782_, double p_309484_, Level p_311660_) {
        super(p_310629_, p_311590_, p_312782_, p_309484_, p_311660_);
    }

    public MagmaBurstParent(EntityType<? extends AbstractHurtingProjectile> p_36826_, LivingEntity p_36827_, double p_36828_, double p_36829_, double p_36830_, Level p_36831_) {
        super(p_36826_, p_36827_, p_36828_, p_36829_, p_36830_, p_36831_);
    }


    @Override
    public void tick() {
        super.tick();
        if(this.lifeTicks%3==0){
            Vec3 pos = this.position().add(0,-0.12,0);
            RandomSource r = this.random;
            MagmaBurstEntity e = new MagmaBurstEntity(EntityInit.MAGMA_BURST_CHILD.get(), pos.x+1.5*(r.nextFloat()-0.5),pos.y,pos.z+1.5*(r.nextFloat()-0.5), this.level());
            //e.yPower=-1.0f;
            e.setDamage(this.getDamage());
            e.setLifeTicks(160);
            e.setOwner(this.getOwner());
            this.level().addFreshEntity(e);
        }
    }

    @Override
    public void doGravity() {
        this.setDeltaMovement(this.getDeltaMovement().add(0,-0.012*lifeTicks,0));
    }

    @Override
    protected void onHitEntity(EntityHitResult p_37259_) {

        if(p_37259_.getEntity() != this.getOwner()&&p_37259_.getEntity() instanceof LivingEntity) {
            this.spawnMagma();




            Entity e = p_37259_.getEntity();
            LivingEntity livingentity = (LivingEntity) this.getOwner();
            if (e.isAlive() && !e.isInvulnerable() && e != livingentity && e instanceof LivingEntity) {
                if (livingentity == null) {
                    e.hurt(this.level().damageSources().inFire(), 6.0F);
                    e.igniteForTicks(40);
                } else {
                    if (livingentity.isAlliedTo(e)) {
                        return;
                    }

                    e.hurt(this.level().damageSources().inFire(), damage);
                    ((LivingEntity)e).setLastHurtByMob(livingentity);
                    e.igniteForTicks(40);
                }
            }
        }


    }
}
