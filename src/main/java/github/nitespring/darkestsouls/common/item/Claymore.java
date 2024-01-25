package github.nitespring.darkestsouls.common.item;

import github.nitespring.darkestsouls.common.entity.projectile.ClaymoreAttackEntity;
import github.nitespring.darkestsouls.common.entity.projectile.ScimitarAttackEntity;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class Claymore extends Weapon{


    public Claymore(Tier tier, int attack, float speed, Properties properties) {
        super(tier, attack, speed, properties);
    }



    @Override
    public void doLeftClickAction(Player playerIn, ItemStack stackIn) {

        Vec3 pos = playerIn.position().add(playerIn.getLookAngle().x()*1.5, 0.4, playerIn.getLookAngle().z()*1.5);

        Level levelIn = playerIn.level();
        ClaymoreAttackEntity entity = new ClaymoreAttackEntity(EntityInit.CLAYMORE.get(),
                levelIn,
                pos,
                7.0f,
                (float) Mth.atan2(pos.z - playerIn.getZ(), pos.x - playerIn.getX()));
        entity.setOwner(playerIn);
        levelIn.addFreshEntity(entity);

    }
    @Override
    public void doRightClickAction(Player playerIn, ItemStack item) {


    }

}
