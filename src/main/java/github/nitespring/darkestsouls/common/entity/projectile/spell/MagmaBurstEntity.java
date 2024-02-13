package github.nitespring.darkestsouls.common.entity.projectile.spell;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class MagmaBurstEntity extends AbstractHurtingProjectile implements ItemSupplier {



    protected MagmaBurstEntity(EntityType<? extends AbstractHurtingProjectile> p_36833_, Level p_36834_) {
        super(p_36833_, p_36834_);
    }

    protected MagmaBurstEntity(EntityType<? extends AbstractHurtingProjectile> p_310629_, double p_311590_, double p_312782_, double p_309484_, Level p_311660_) {
        super(p_310629_, p_311590_, p_312782_, p_309484_, p_311660_);
    }

    public MagmaBurstEntity(EntityType<? extends AbstractHurtingProjectile> p_36826_, LivingEntity p_36827_, double p_36828_, double p_36829_, double p_36830_, Level p_36831_) {
        super(p_36826_, p_36827_, p_36828_, p_36829_, p_36830_, p_36831_);
    }

    @Override
    public ItemStack getItem() {
        return Items.BLAZE_POWDER.getDefaultInstance();
    }

    @Override
    public boolean fireImmune() {
        return true;
    }


    @Override
    protected void onHitEntity(EntityHitResult p_37259_) {
        super.onHitEntity(p_37259_);
    }

    @Override
    protected void onHitBlock(BlockHitResult p_37258_) {

    }


    public void spawnMagma(){
        Vec3 pos = this.position().add(0,-0.12,0);


    }
}
