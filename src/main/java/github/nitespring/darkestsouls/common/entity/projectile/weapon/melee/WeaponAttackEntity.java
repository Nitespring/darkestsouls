package github.nitespring.darkestsouls.common.entity.projectile.weapon.melee;

import github.nitespring.darkestsouls.common.entity.mob.DarkestSoulsAbstractEntity;
import github.nitespring.darkestsouls.core.init.EffectInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.UUID;

public class WeaponAttackEntity extends Entity {
    @Nullable
    public LivingEntity owner;
    @Nullable
    public UUID ownerUUID;
    public int lifeTicks = 0;
    public float damage = 1.5f;
    @Nullable
    public ItemStack itemStack;
    public int hitEntities = 0;
    public int maxTargets=-1;

    public int poiseDmg=5;
    public int bleed=0;
    public int poison=0;
    public int rot=0;
    public int frost=0;
    public int death=0;
    public  int fire=0;
    public float baneOfArthropods=0;
    public float smite=0;
    public int beastHunter=0;
    public int demonSlayer=0;
    public int kinHunter=0;
    public int abyssCleanser=0;
    public int godSlayer=0;
    public int hollowSlayer=0;


    public double inflateX=1.2;
    public double inflateY=0;
    public double inflateZ=1.2;

    public double playerDistance=1.5;
    public double hitboxHeight=0.4;

    public int finalTick=11;

    public int updateTickAnimation=2;
    public int updateTickPosition=2;

    public int attackTick=6;


    protected static final EntityDataAccessor<Integer> ANIMATIONSTATE = SynchedEntityData.defineId(WeaponAttackEntity.class, EntityDataSerializers.INT);

   /* public WeaponAttackEntity(EntityType<?> e, Level level, Vec3 pos, float dmg, int poisedmg, int fire, float smite, float bane,int bleed, int poison, int rot, int frost, int death , int updateTickAnimation, int updateTickPosition, int attackTick, int finalTick, double playerDistance, double hitboxHeight, double inflatex,double inflatey,double inflatez,float rotation) {
        this(e, level);
        this.setPos(pos);
        this.damage=dmg;
        this.setYRot(rotation * (180F / (float)Math.PI));
        this.poiseDmg=poisedmg;
        this.fire=fire;
        this.smite=smite;
        this.baneOfArthropods=bane;
        this.bleed=bleed;
        this.poison=poison;
        this.rot=rot;
        this.frost=frost;
        this.death=death;
        this.attackTick=attackTick;
        this.finalTick=finalTick;
        this.updateTickAnimation=updateTickAnimation;
        this.updateTickPosition=updateTickPosition;
        this.playerDistance=playerDistance;
        this.hitboxHeight=hitboxHeight;
        this.inflateX=inflatex;
        this.inflateY=inflatey;
        this.inflateZ=inflatez;
    }*/

    public WeaponAttackEntity(EntityType<?> e, Level level,Vec3 pos,float rotation) {
        this(e, level);
        this.setPos(pos);
        this.setYRot(rotation * (180F / (float)Math.PI));

    }
    public WeaponAttackEntity(EntityType<?> e, Level level) {
        super(e, level);
    }


    public int getAnimationState() {
        return this.entityData.get(ANIMATIONSTATE);
    }

    public void setAnimationState(int anim) {
        this.entityData.set(ANIMATIONSTATE, anim);
    }


    @Override
    protected void defineSynchedData() {
        this.entityData.define(ANIMATIONSTATE, 0);

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag p_20052_) {
        if (p_20052_.hasUUID("Owner")) {
            this.ownerUUID = p_20052_.getUUID("Owner");
        }

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag p_20139_) {
        if (this.ownerUUID != null) {
            p_20139_.putUUID("Owner", this.ownerUUID);
        }

    }

    public void setOwner(@Nullable LivingEntity p_36939_) {
        this.owner = p_36939_;
        this.ownerUUID = p_36939_ == null ? null : p_36939_.getUUID();
    }

    @Nullable
    public LivingEntity getOwner() {
        if (this.owner == null && this.ownerUUID != null && this.getCommandSenderWorld() instanceof ServerLevel) {
            Entity entity = ((ServerLevel)this.getCommandSenderWorld()).getEntity(this.ownerUUID);
            if (entity instanceof LivingEntity) {
                this.owner = (LivingEntity)entity;
            }
        }

        return this.owner;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }



    @Override
    public boolean fireImmune() {

        return true;
    }


    @Override
    public void tick() {
        super.tick();
        this.doTick();
    }
    public void doTick(){
        this.lifeTicks++;
        this.tickAttack();
        this.tickAnimation();
        this.tickPosition();
    }
    public void tickAttack(){
        if(lifeTicks==attackTick){

            this.attackEntity();
        }
        if (this.lifeTicks >= finalTick) {
            this.discard();
        }
    }
    public void tickAnimation(){
        if(lifeTicks%updateTickAnimation==0) {
            this.setAnimationState(this.getAnimationState()+1);
        }
    }
    public void tickPosition(){
        if(this.owner!=null&&lifeTicks%2==0) {
            Vec3 pos = this.owner.position().add(this.owner.getLookAngle().x() * playerDistance, hitboxHeight, this.owner.getLookAngle().z() * playerDistance);
            this.setPos(pos);
            float rot = (float) Mth.atan2(pos.z - this.owner.getZ(), pos.x - this.owner.getX());
            this.setYRot(rot * (180F / (float) Math.PI));
        }
    }



    public SoundEvent getAttackSound(){

          return SoundEvents.PLAYER_ATTACK_SWEEP;
    }
    public void playAttackSound(){

        this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), this.getAttackSound(), this.getSoundSource(), 0.25F, this.random.nextFloat() * 0.2F + 1.0F, false);
    }

    public void attackEntity(){

        this.playAttackSound();
        for(LivingEntity livingentity : level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(inflateX, inflateY, inflateZ))) {
            if(livingentity.hurtTime<=0) {
                this.dealDamageTo(livingentity);
            }
        }
        /*for(int i = 0; i < 6; ++i) {
            double d0 = this.getX() + (this.random.nextDouble() * 2.0D - 1.0D) * (double)this.getBbWidth() * 0.5D;
            double d1 = this.getY() -0.75 + this.random.nextDouble()*1.5;
            double d2 = this.getZ() + (this.random.nextDouble() * 2.0D - 1.0D) * (double)this.getBbWidth() * 0.5D;
            double d3 = (this.random.nextDouble() * 2.0D - 1.0D) * 0.75D;
            double d4 = 0.15D + this.random.nextDouble() * 0.6D;
            double d5 = (this.random.nextDouble() * 2.0D - 1.0D) * 0.75D;
            this.level().addParticle(ParticleTypes.AMBIENT_ENTITY_EFFECT, d0, d1 + 1.0D, d2, d3, d4, d5);
        }*/


    }



    public void dealDamageTo(LivingEntity target) {
        LivingEntity livingentity = this.getOwner();
        if (target.isAlive() && !target.isInvulnerable() && target != livingentity) {
            if(this.maxTargets<=0||this.hitEntities<=maxTargets) {
                float mobTypeBonus = 0;
                if(this.baneOfArthropods>=1&&target.getMobType()== MobType.ARTHROPOD){
                    mobTypeBonus=mobTypeBonus+this.baneOfArthropods;
                    target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, (int) (10+this.baneOfArthropods*8),2), this.getOwner());
                }

                if(this.smite>=1&&target.getMobType()== MobType.UNDEAD){
                    mobTypeBonus=mobTypeBonus+this.smite;
                }

                if (livingentity == null) {
                    target.hurt(this.level().damageSources().generic(), damage+mobTypeBonus);
                } else {
                    if (livingentity.isAlliedTo(target)) {
                        return;
                    }

                    target.hurt(this.level().damageSources().playerAttack((Player) livingentity), damage+mobTypeBonus);
                    this.damageWeapon();
                }

                if(this.fire>=1){
                    target.setSecondsOnFire(40*fire);
                }

                if(this.poison>=1){
                    target.addEffect(new MobEffectInstance(MobEffects.POISON,40+this.poison*40,this.poison-1), this.getOwner());
                }

                if(this.bleed>=1){
                    if(target.hasEffect(EffectInit.BLEED.get())){
                        int amount= target.getEffect(EffectInit.BLEED.get()).getAmplifier()+ this.bleed;
                        target.removeEffect(EffectInit.BLEED.get());
                        target.addEffect(new MobEffectInstance(EffectInit.BLEED.get(), 180, amount));
                    }else{
                        int amount = this.bleed-1;
                        target.addEffect(new MobEffectInstance(EffectInit.BLEED.get(), 180, amount));
                    }
                }
                if(this.rot>=1){
                    target.addEffect(new MobEffectInstance(EffectInit.ROT.get(),40+this.rot*40,this.poison-1), this.getOwner());
                }

                if (target instanceof DarkestSoulsAbstractEntity /*&& this.itemStack!=null && this.getOwner()!=null*/){
                    ((DarkestSoulsAbstractEntity) target).damagePoiseHealth(this.poiseDmg);
                }
                //System.out.println("entity damage " + damage+ mobTypeBonus);
                this.hitEntities++;
            }

        }
    }
    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }
    public void damageWeapon(){
        if(this.getItemStack()!=null&&this.getOwner()!=null) {
            this.getItemStack().hurtAndBreak(1, this.getOwner(), (p_43296_) -> {
                if(getItemStack().getEquipmentSlot()!=null) {
                    p_43296_.broadcastBreakEvent(getItemStack().getEquipmentSlot());
                }
            });
        }
    }
    public int getMaxTargets() {
        return maxTargets;
    }

    public void setMaxTargets(int maxTargets) {
        this.maxTargets = maxTargets;
    }

    public void configureTicks(int atk, int end, int anim, int pos){
        this.attackTick=atk;
        this.finalTick=end;
        this.updateTickAnimation=anim;
        this.updateTickPosition=pos;
    }
    public void setHitboxModifications(double width, double height, double groundDistance, double distance){
        this.inflateX=width;
        this.inflateZ=width;
        this.inflateY=height;
        this.hitboxHeight=groundDistance;
        this.playerDistance=distance;
    }

    public void setDamage(float dmg, int poisedmg, int fire, float smite, float bane,int bleed, int poison, int rot, int frost, int death){
        this.damage=dmg;
        //System.out.println(dmg);
        this.poiseDmg=poisedmg;
        this.fire=fire;
        this.smite=smite;
        this.baneOfArthropods=bane;
        this.bleed=bleed;
        this.poison=poison;
        this.rot=rot;
        this.frost=frost;
        this.death=death;
    }
}
