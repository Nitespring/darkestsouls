package github.nitespring.darkestsouls.common.item;

import github.nitespring.darkestsouls.common.entity.projectile.FrayedBladeAttackEntity;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class FrayedBlade extends Weapon{


    public FrayedBlade(Tier tier, int attack, float speed, Properties properties) {
        super(tier, attack, speed, properties);
    }



    @Override
    public void doLeftClickAction(Player playerIn, ItemStack stackIn) {

        Vec3 pos = playerIn.position().add(playerIn.getLookAngle().x()*2.5, 0.4, playerIn.getLookAngle().z()*2.5);

        Level levelIn = playerIn.level();
        FrayedBladeAttackEntity entity = new FrayedBladeAttackEntity(EntityInit.FRAYED_BLADE.get(),
                levelIn,
                pos,
                4.0f,
                (float) Mth.atan2(pos.z - playerIn.getZ(), pos.x - playerIn.getX()));
        entity.setOwner(playerIn);
        levelIn.addFreshEntity(entity);

    }
}
