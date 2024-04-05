package github.nitespring.darkestsouls.common.effect;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ChikageMobEffect extends MobEffect{
    //int damageTick = 0;
	public ChikageMobEffect(MobEffectCategory p_19451_, int p_19452_) {
		super(p_19451_, p_19452_);
		
	}
	@Override
	public void applyEffectTick(LivingEntity entityIn, int id) {
		ParticleOptions blood = new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(Items.NETHER_WART_BLOCK));



		float width = entityIn.getBbWidth() * 0.5f;
	    float height = entityIn.getBbHeight() * 0.5f;
		Vec3 pos = new Vec3(entityIn.getX(), entityIn.getEyeY(), entityIn.getZ());
		Level world = entityIn.level();
		
		RandomSource rng = entityIn.getRandom();
		
		double size = width * height;
		double rand = 1/(1 + (rng.nextInt(10)-4)*0.1);
		for (int i = 0; i < 20; ++i) {
			
			Vec3 off = new Vec3(rng.nextDouble() * width - width / 2, rng.nextDouble() * height - height / 2,
						   rng.nextDouble() * width - width / 2);
			if(world instanceof ServerLevel) {
			((ServerLevel) world).sendParticles( blood, pos.x, pos.y, pos.z, 5,  off.x, off.y + 0.05D, off.z, 0.05D + 5*0.003);

			}
			}
	if(!(entityIn instanceof Player p && p.isCreative())) {
		entityIn.hurt(entityIn.damageSources().fellOutOfWorld(), 1);
	}

	}


	@Override
	public boolean shouldApplyEffectTickThisTick(int a, int b) {

		int k = 24 >> b;
        if (k > 0) {
           return a % k == 0;
        } else {
           return true;
        }
	   }

}
