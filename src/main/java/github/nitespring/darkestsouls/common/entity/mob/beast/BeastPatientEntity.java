package github.nitespring.darkestsouls.common.entity.mob.beast;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public abstract class BeastPatientEntity extends Beast{


    public BeastPatientEntity(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    public int getBeastPatientType() {return 0;}
    @Override
    public int getMaxPoise() {return 24;}
    @Override
    public int getBloodResistance() {return 8;}





}
