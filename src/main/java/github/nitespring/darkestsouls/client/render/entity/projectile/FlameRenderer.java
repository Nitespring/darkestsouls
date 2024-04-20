package github.nitespring.darkestsouls.client.render.entity.projectile;


import github.nitespring.darkestsouls.common.entity.projectile.weapon.Flame;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.ItemSupplier;

public class FlameRenderer<T extends Flame> extends ThrownItemRenderer{

	public FlameRenderer(Context p_174416_) {
		super(p_174416_, 0.6f, true);
		
	}

}
