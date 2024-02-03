package github.nitespring.darkestsouls.common.item.child.staves;

import github.nitespring.darkestsouls.common.entity.projectile.spell.SoulDart;
import github.nitespring.darkestsouls.common.item.Staff;
import github.nitespring.darkestsouls.core.init.EntityInit;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SorcererStaff extends Staff {
    public SorcererStaff(float attackDamage, int durability, Properties properties) {
        super(attackDamage, durability, properties);
    }

    @Override
    public void doSpellA(Player playerIn, ItemStack stackIn, InteractionHand HandIn) {
        Level levelIn = playerIn.level();
        Vec3 aim = playerIn.getLookAngle();

        SoulDart e = new SoulDart(EntityInit.SOUL_DART.get(), levelIn);
        e.setDamage(this.getAttackDamage(playerIn,stackIn));
        e.setMaxLifeTime(80);
        e.setDeltaMovement(0.5*aim.x, 0.5*aim.y, 0.5*aim.z);
        levelIn.addFreshEntity(e);

    }

    @Override
    public void doSpellB(Player playerIn, ItemStack stackIn, InteractionHand handIn) {




    }
}
