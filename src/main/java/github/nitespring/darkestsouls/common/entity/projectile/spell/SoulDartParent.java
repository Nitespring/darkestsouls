package github.nitespring.darkestsouls.common.entity.projectile.spell;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;

public class SoulDartParent extends Projectile {

    public float damage=2.0f;
    public int maxLifeTime=20;
    public float dScale=1;
    public int lifeTicks;

    public SoulDartParent(EntityType<? extends Projectile> e, Level level) {
        super(e, level);
    }
    @Override
    protected void defineSynchedData() {
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
    }

    public void doRemoval(){
        this.onRemoval();
        this.remove(RemovalReason.DISCARDED);
    }
    public void onRemoval(){}
}
