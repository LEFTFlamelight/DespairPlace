package com.lediter.despair.entity;

import com.lediter.despair.item.ItemRegistry;
import com.lediter.despair.procedures.K1Procedure;
import com.lediter.despair.procedures.K2Procedure;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class KiriaEntity extends MonsterEntity {
    public KiriaEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        experienceValue = 5;
        setNoAI(false);
        enablePersistence();
        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ItemRegistry.DESPAIR_SWORD.get()));
    }
    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
    public static class EntityAttributesRegisterHandler {
        @SubscribeEvent
        public void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
            AttributeModifierMap.MutableAttribute ammma = MobEntity.func_233666_p_();
            ammma = ammma.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3);
            ammma = ammma.createMutableAttribute(Attributes.MAX_HEALTH, 250);
            ammma = ammma.createMutableAttribute(Attributes.ARMOR, 5);
            ammma = ammma.createMutableAttribute(Attributes.ATTACK_DAMAGE, 1);
            event.put(EntityTypeRegister.KIRIA_ENTITY.get(), ammma.create());
        }
    }
    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false) {
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                return (double) (4.0 + entity.getWidth() * entity.getWidth());
            }
        });
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, PlayerEntity.class, false, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, ZombieEntity.class, false, false));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, SkeletonEntity.class, false, false));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, SpiderEntity.class, false, false));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, WitchEntity.class, false, false));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, EndermanEntity.class, false, false));
        this.goalSelector.addGoal(6, new RandomWalkingGoal(this, 1));
        this.targetSelector.addGoal(7, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(9, new SwimGoal(this));
        this.goalSelector.addGoal(10, new ReturnToVillageGoal(this, 0.6, false));
        this.goalSelector.addGoal(11, new OpenDoorGoal(this, true));
        this.goalSelector.addGoal(12, new OpenDoorGoal(this, false));
        this.goalSelector.addGoal(13, new LookAtGoal(this, PlayerEntity.class, (float) 6));
    }

    @Override
    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.UNDEAD;
    }

    @Override
    public boolean canDespawn(double distanceToClosestPlayer) {
        return false;
    }

    protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropSpecialItems(source, looting, recentlyHitIn);
        this.entityDropItem(new ItemStack(ItemRegistry.DESPAIR_STONE.get()));
    }
    @Override
    public net.minecraft.util.SoundEvent getAmbientSound() {
        return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.parrot.imitate.creeper"));
    }
    @Override
    public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
        return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.creeper.hurt"));
    }

    @Override
    public net.minecraft.util.SoundEvent getDeathSound() {
        return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.creeper.death"));
    }

    public boolean isNonBoss() {
        return false;
    }

    private final ServerBossInfo bossInfo = new ServerBossInfo(this.getDisplayName(), BossInfo.Color.PINK, BossInfo.Overlay.PROGRESS);

    @Override
    public void addTrackingPlayer(ServerPlayerEntity player) {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    @Override
    public void removeTrackingPlayer(ServerPlayerEntity player) {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }
    @Override
    public void updateAITasks() {
        super.updateAITasks();
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    }
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        double x = this.getPosX();
        double y = this.getPosY();
        double z = this.getPosZ();
        Entity entity = this;
        Entity sourceentity = source.getTrueSource();

        K2Procedure.executeProcedure(Stream
                .of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x), new AbstractMap.SimpleEntry<>("y", y),
                        new AbstractMap.SimpleEntry<>("z", z))
                .collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
        return super.attackEntityFrom(source, amount);
    }
    @Override
    public void onDeath(DamageSource source) {
        super.onDeath(source);
        double x = this.getPosX();
        double y = this.getPosY();
        double z = this.getPosZ();
        Entity sourceentity = source.getTrueSource();
        Entity entity = this;
        K1Procedure.executeProcedure(Stream
                .of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x), new AbstractMap.SimpleEntry<>("y", y),
                        new AbstractMap.SimpleEntry<>("z", z))
                .collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
    }
}
