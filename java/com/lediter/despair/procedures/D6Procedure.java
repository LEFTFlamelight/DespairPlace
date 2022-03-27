package com.lediter.despair.procedures;

import com.lediter.despair.DespairMod;
import com.lediter.despair.item.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Map;

public class D6Procedure {

    public static void executeProcedure(Map<String, Object> dependencies) {
        if (dependencies.get("entity") == null) {
            if (!dependencies.containsKey("entity"))
                DespairMod.LOGGER.warn("Failed to load dependency entity for procedure D6!");
            return;
        }

        Entity entity = (Entity) dependencies.get("entity");

        if ((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(ItemRegistry.CONSCIOUSNESS_STONE.get())) : false) {
            if (entity instanceof PlayerEntity) {
                ItemStack _stktoremove = new ItemStack(ItemRegistry.CONSCIOUSNESS_STONE.get());
                ((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
                        ((PlayerEntity) entity).container.func_234641_j_());
            }
            if (entity instanceof PlayerEntity) {
                ItemStack _setstack = new ItemStack(Items.DIAMOND);
                _setstack.setCount((int) 2);
                ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
            }
        }
        if ((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(ItemRegistry.DESPAIR_CRYSTAL.get())) : false) {
            if (entity instanceof PlayerEntity) {
                ItemStack _stktoremove = new ItemStack(ItemRegistry.DESPAIR_CRYSTAL.get());
                ((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 256,
                        ((PlayerEntity) entity).container.func_234641_j_());
            }
            if (entity instanceof PlayerEntity) {
                ItemStack _setstack = new ItemStack(Items.NETHERITE_INGOT);
                _setstack.setCount((int) 2);
                ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
            }
            if (((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(ItemRegistry.DESPAIR_FRAGMENT.get())) : false)
                    ? ((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(Items.BLAZE_ROD)) : false)
                    : ((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(Items.DIAMOND)) : false)) {
                if (entity instanceof PlayerEntity) {
                    ItemStack _stktoremove = new ItemStack(ItemRegistry.DESPAIR_FRAGMENT.get());
                    ((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
                            ((PlayerEntity) entity).container.func_234641_j_());
                }
                if (entity instanceof PlayerEntity) {
                    ItemStack _stktoremove = new ItemStack(Items.BLAZE_ROD);
                    ((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
                            ((PlayerEntity) entity).container.func_234641_j_());
                }
                if (entity instanceof PlayerEntity) {
                    ItemStack _stktoremove = new ItemStack(Items.DIAMOND);
                    ((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
                            ((PlayerEntity) entity).container.func_234641_j_());
                }
                if (entity instanceof PlayerEntity) {
                    ItemStack _setstack = new ItemStack(ItemRegistry.DESPAIR_CRYSTAL.get());
                    _setstack.setCount((int) 1);
                    ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
                }
                if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
                    ((PlayerEntity) entity).sendStatusMessage(
                            new StringTextComponent("\u5DF2\u5728\u60A8\u7684\u4F4D\u7F6E\u6210\u529F\u751F\u6210\u51CB\u96F6\u610F\u8BC6\u4F53\uFF01"),
                            (false));
                }
            }
        }
    }
}