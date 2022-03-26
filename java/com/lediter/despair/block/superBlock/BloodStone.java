package com.lediter.despair.block.superBlock;

import com.lediter.despair.item.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;

import java.util.Collections;
import java.util.List;

public class BloodStone extends Block {
    public BloodStone(Properties properties) {
        super(properties);
    }
    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(ItemRegistry.BLOOD_COBBLESTONE.get(), 1));
    }
}
