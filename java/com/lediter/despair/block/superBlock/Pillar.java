package com.lediter.despair.block.superBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;

import java.util.Collections;
import java.util.List;

public class Pillar extends Block {
    public Pillar(Properties properties) {
        super(properties);
    }
    @Override
    public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 15;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        Vector3d offset = state.getOffset(world, pos);
        return VoxelShapes.or(makeCuboidShape(4, 0, 4, 12, 16, 12), makeCuboidShape(3, 0, 5, 13, 16, 11), makeCuboidShape(5, 0, 3, 11, 16, 13)

                )

                .withOffset(offset.x, offset.y, offset.z);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {

        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(this, 1));
    }
}
