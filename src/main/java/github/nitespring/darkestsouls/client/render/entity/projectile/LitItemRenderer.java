package github.nitespring.darkestsouls.client.render.entity.projectile;


import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.ItemSupplier;

public class LitItemRenderer<T extends AbstractHurtingProjectile & ItemSupplier> extends ThrownItemRenderer{

	public LitItemRenderer(Context p_174416_) {
		super(p_174416_, 0.4f, true);
		
	}

}
