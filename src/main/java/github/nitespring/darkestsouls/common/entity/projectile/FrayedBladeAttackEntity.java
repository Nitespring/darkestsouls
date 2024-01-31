package github.nitespring.darkestsouls.common.entity.projectile;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.common.item.Weapon;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.UUID;

public class FrayedBladeAttackEntity extends WeaponAttackEntity{


	public FrayedBladeAttackEntity(EntityType<?> e, Level level, Vec3 pos, float rotation) {
		super(e, level, pos, rotation);
	}

	public FrayedBladeAttackEntity(EntityType<?> e, Level level) {
		super(e, level);
	}

	@Override
	public void tickAttack() {
		if(lifeTicks%2==0&&lifeTicks<=14){
			super.attackEntity();
		}

		if (this.lifeTicks >= 22) {
			this.discard();
		}
	}
	@Override
	public void tickAnimation(){
		//if(lifeTicks%updateTickAnimation==0) {
			this.setAnimationState(this.getAnimationState()+1);
		//}
	}

}
