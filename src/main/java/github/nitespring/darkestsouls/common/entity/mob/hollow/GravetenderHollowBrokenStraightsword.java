package github.nitespring.darkestsouls.common.entity.mob.hollow;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class GravetenderHollowBrokenStraightsword extends MadHollowBrokenStraightsword{
    public GravetenderHollowBrokenStraightsword(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
        this.xpReward=3;
    }

    @Override
    public void populateClothing() {
        this.setRobeType(3);
    }
}
