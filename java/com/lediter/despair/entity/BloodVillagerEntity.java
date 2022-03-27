
package com.lediter.despair.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.network.IPacket;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.block.material.Material;
import net.minecraft.block.Blocks;

	public class BloodVillagerEntity extends VillagerEntity {
		public BloodVillagerEntity(EntityType<? extends VillagerEntity> type, World worldIn) {
			super(type, worldIn);
			experienceValue = 0;
			setNoAI(false);
		}

		@SubscribeEvent
		public void addFeatureToBiomes(BiomeLoadingEvent event) {
			boolean biomeCriteria = false;
			if (new ResourceLocation("despair:glass_place").equals(event.getName()))
				biomeCriteria = true;
			if (!biomeCriteria)
				return;
			event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(EntityTypeRegister.BLOOD_VILLAGER_ENTITY.get(), 20, 4, 4));
		}
		public static class EntityAttributesRegisterHandler {
			@SubscribeEvent
			public void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
				AttributeModifierMap.MutableAttribute ammma = MobEntity.func_233666_p_();
				ammma = ammma.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3);
				ammma = ammma.createMutableAttribute(Attributes.MAX_HEALTH, 10);
				ammma = ammma.createMutableAttribute(Attributes.ARMOR, 0);
				ammma = ammma.createMutableAttribute(Attributes.ATTACK_DAMAGE, 3);
				event.put(EntityTypeRegister.BLOOD_VILLAGER_ENTITY.get(), ammma.create());
			}
		}
		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, ZombieEntity.class, false, false));
			this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, false) {
				@Override
				protected double getAttackReachSqr(LivingEntity entity) {
					return (double) (4.0 + entity.getWidth() * entity.getWidth());
				}
			});
			this.goalSelector.addGoal(3, new RandomWalkingGoal(this, 1));
			this.targetSelector.addGoal(4, new HurtByTargetGoal(this));
			this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(6, new SwimGoal(this));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.villager.ambient"));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.villager.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.villager.death"));
		}
	}