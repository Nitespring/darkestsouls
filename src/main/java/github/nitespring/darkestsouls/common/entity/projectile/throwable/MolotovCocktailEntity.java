package github.nitespring.darkestsouls.common.entity.projectile.throwable;

import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class MolotovCocktailEntity extends FirebombEntity{
    public MolotovCocktailEntity(EntityType<? extends AbstractHurtingProjectile> p_36833_, Level p_36834_) {
        super(p_36833_, p_36834_);
    }
    @Override
    public void playExplosionSound(){
        this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.DRAGON_FIREBALL_EXPLODE, this.getSoundSource(), 0.4F+this.random.nextFloat() * 0.5F, this.random.nextFloat() * 0.2F + 1.0F, true);
        this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.SPLASH_POTION_BREAK, this.getSoundSource(), 0.8F+this.random.nextFloat() * 0.5F, this.random.nextFloat() * 0.2F + 1.0F, true);
        if(this.getOwner() instanceof Player) {
            this.level().playLocalSound(this.getOwner().getX(), this.getOwner().getY(), this.getOwner().getZ(), SoundEvents.DRAGON_FIREBALL_EXPLODE, this.getSoundSource(), 0.05F + this.random.nextFloat() * 0.05F, this.random.nextFloat() * 0.2F + 1.0F, true);
            this.level().playLocalSound(this.getOwner().getX(), this.getOwner().getY(), this.getOwner().getZ(), SoundEvents.SPLASH_POTION_BREAK, this.getSoundSource(), 0.2F+this.random.nextFloat() * 0.5F, this.random.nextFloat() * 0.2F + 1.0F, true);
        }
    }
    public ParticleOptions getExplosionParticleII(){
        return new BlockParticleOption(ParticleTypes.BLOCK, Blocks.WATER.defaultBlockState());
    }

}
