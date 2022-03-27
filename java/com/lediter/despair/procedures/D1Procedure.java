package com.lediter.despair.procedures;

import com.lediter.despair.DespairMod;
import com.lediter.despair.item.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

public class D1Procedure {

    @Mod.EventBusSubscriber
    private static class GlobalTrigger {
        @SubscribeEvent
        public static void onPlayerRespawned(PlayerEvent.PlayerRespawnEvent event) {
            Entity entity = event.getPlayer();
            Map<String, Object> dependencies = new HashMap<>();
            dependencies.put("x", entity.getPosX());
            dependencies.put("y", entity.getPosY());
            dependencies.put("z", entity.getPosZ());
            dependencies.put("world", entity.world);
            dependencies.put("entity", entity);
            dependencies.put("endconquered", event.isEndConquered());
            dependencies.put("event", event);
            executeProcedure(dependencies);
        }
    }

    public static void executeProcedure(Map<String, Object> dependencies) {
        if (dependencies.get("world") == null) {
            if (!dependencies.containsKey("world"))
                DespairMod.LOGGER.warn("Failed to load dependency world for procedure D1!");
            return;
        }
        if (dependencies.get("x") == null) {
            if (!dependencies.containsKey("x"))
                DespairMod.LOGGER.warn("Failed to load dependency x for procedure D1!");
            return;
        }
        if (dependencies.get("y") == null) {
            if (!dependencies.containsKey("y"))
                DespairMod.LOGGER.warn("Failed to load dependency y for procedure D1!");
            return;
        }
        if (dependencies.get("z") == null) {
            if (!dependencies.containsKey("z"))
                DespairMod.LOGGER.warn("Failed to load dependency z for procedure D1!");
            return;
        }

        IWorld world = (IWorld) dependencies.get("world");
        double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
        double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
        double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");

        if (world instanceof World && !world.isRemote()) {
            ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack(ItemRegistry.DESPAIR_FRAGMENT.get()));
            entityToSpawn.setPickupDelay((int) 10);
            entityToSpawn.setNoDespawn();
            world.addEntity(entityToSpawn);
        }
    }

}