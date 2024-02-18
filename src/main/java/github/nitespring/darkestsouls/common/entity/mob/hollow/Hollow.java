package github.nitespring.darkestsouls.common.entity.mob.hollow;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public abstract class Hollow extends DarkestSoulsAbstractEntity {
    public Hollow(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }
    @Override
    public int getMaxPoise() {return 12;}
    @Override
    public int getBloodResistance() {return 6;}
    @Override
    public boolean isBoss() {return false;}
    @Override
    public int getDSDefaultTeam() {return 0;}
}
