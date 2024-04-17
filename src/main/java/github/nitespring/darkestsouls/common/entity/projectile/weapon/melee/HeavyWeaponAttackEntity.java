package github.nitespring.darkestsouls.common.entity.projectile.weapon.melee;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.RandomizableContainer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class HeavyWeaponAttackEntity extends WeaponAttackEntity{
    public HeavyWeaponAttackEntity(EntityType<?> e, Level level, Vec3 pos, float rotation) {
        super(e, level, pos, rotation);
    }

    public HeavyWeaponAttackEntity(EntityType<?> e, Level level) {
        super(e, level);
    }
    @Override
    public void playAttackSound(){

        this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), this.getAttackSound(), this.getSoundSource(), this.random.nextFloat() * 0.1F +0.1F, this.random.nextFloat() * 0.3F + 0.05F, false);
    }

    @Override
    public SoundEvent getAttackSound() {
        int r = new Random().nextInt(10);
        if(r<=4) {
            return SoundEvents.ANVIL_PLACE;
        }else{
            return SoundEvents.ANVIL_LAND;
        }
    }
}
