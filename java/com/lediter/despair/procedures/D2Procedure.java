package com.lediter.despair.procedures;

import com.lediter.despair.DespairMod;
import com.lediter.despair.block.BlockRegistry;
import com.lediter.despair.item.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.GameType;
import net.minecraft.world.IWorld;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Map;

public class D2Procedure {

    public static void executeProcedure(Map<String, Object> dependencies) {
        if (dependencies.get("world") == null) {
            if (!dependencies.containsKey("world"))
                DespairMod.LOGGER.warn("Failed to load dependency world for procedure D2!");
            return;
        }
        if (dependencies.get("x") == null) {
            if (!dependencies.containsKey("x"))
                DespairMod.LOGGER.warn("Failed to load dependency x for procedure D2!");
            return;
        }
        if (dependencies.get("y") == null) {
            if (!dependencies.containsKey("y"))
                DespairMod.LOGGER.warn("Failed to load dependency y for procedure D2!");
            return;
        }
        if (dependencies.get("z") == null) {
            if (!dependencies.containsKey("z"))
                DespairMod.LOGGER.warn("Failed to load dependency z for procedure D2!");
            return;
        }
        if (dependencies.get("entity") == null) {
            if (!dependencies.containsKey("entity"))
                DespairMod.LOGGER.warn("Failed to load dependency entity for procedure D2!");
            return;
        }

        IWorld world = (IWorld) dependencies.get("world");
        double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
        double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
        double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
        Entity entity = (Entity) dependencies.get("entity");

        if (((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(BlockRegistry.DESPAIR_BLOCK.get())) : false)
                ? ((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(ItemRegistry.CONSCIOUSNESS_STONE.get())) : false)
                : new Object() {
            public boolean checkGamemode(Entity _ent) {
                if (_ent instanceof ServerPlayerEntity) {
                    return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.NOT_SET;
                } else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
                    NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
                            .getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
                    return _npi != null && _npi.getGameType() == GameType.NOT_SET;
                }
                return false;
            }
        }.checkGamemode(entity)) {
            if (entity instanceof PlayerEntity) {
                ItemStack _stktoremove = new ItemStack(BlockRegistry.DESPAIR_BLOCK.get());
                ((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
                        ((PlayerEntity) entity).container.func_234641_j_());
            }
            if (entity instanceof PlayerEntity) {
                ItemStack _stktoremove = new ItemStack(ItemRegistry.CONSCIOUSNESS_STONE.get());
                ((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
                        ((PlayerEntity) entity).container.func_234641_j_());
            }
            if (entity instanceof PlayerEntity) {
                ItemStack _setstack = new ItemStack(ItemRegistry.EMPTY_CONSCIOUSNESS_TEMPLATE.get());
                _setstack.setCount((int) 1);
                ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
            }
            if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
                ((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u610F\u8BC6\u4F53\u5DF2\u6210\u529F\u8F6C\u6362\uFF01"), (false));
            }
            world.playEvent(2001, new BlockPos((int) x, (int) y, (int) z), Block.getStateId(BlockRegistry.DESPAIR_BLOCK.get().getDefaultState()));
        }
    }
}