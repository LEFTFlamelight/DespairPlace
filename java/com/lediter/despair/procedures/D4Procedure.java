package com.lediter.despair.procedures;

import com.lediter.despair.DespairMod;
import com.lediter.despair.item.ItemRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Map;

public class D4Procedure {

    public static void executeProcedure(Map<String, Object> dependencies) {
        if (dependencies.get("world") == null) {
            if (!dependencies.containsKey("world"))
                DespairMod.LOGGER.warn("Failed to load dependency world for procedure D4!");
            return;
        }
        if (dependencies.get("x") == null) {
            if (!dependencies.containsKey("x"))
                DespairMod.LOGGER.warn("Failed to load dependency x for procedure D4!");
            return;
        }
        if (dependencies.get("y") == null) {
            if (!dependencies.containsKey("y"))
                DespairMod.LOGGER.warn("Failed to load dependency y for procedure D4!");
            return;
        }
        if (dependencies.get("z") == null) {
            if (!dependencies.containsKey("z"))
                DespairMod.LOGGER.warn("Failed to load dependency z for procedure D4!");
            return;
        }
        if (dependencies.get("entity") == null) {
            if (!dependencies.containsKey("entity"))
                DespairMod.LOGGER.warn("Failed to load dependency entity for procedure D4!");
            return;
        }

        IWorld world = (IWorld) dependencies.get("world");
        double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
        double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
        double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
        Entity entity = (Entity) dependencies.get("entity");

        if (world instanceof ServerWorld) {
            Entity entityToSpawn = new WitherEntity(EntityType.WITHER, (World) world);
            entityToSpawn.setLocationAndAngles(x, y, z, world.getRandom().nextFloat() * 360F, 0);

            if (entityToSpawn instanceof MobEntity)
                ((MobEntity) entityToSpawn).onInitialSpawn((ServerWorld) world, world.getDifficultyForLocation(entityToSpawn.getPosition()),
                        SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);

            world.addEntity(entityToSpawn);
        }
        if (entity instanceof PlayerEntity) {
            ItemStack _stktoremove = new ItemStack(ItemRegistry.WITHER_CONSCIOUSNESS.get());
            ((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
                    ((PlayerEntity) entity).container.func_234641_j_());
        }
    }

}