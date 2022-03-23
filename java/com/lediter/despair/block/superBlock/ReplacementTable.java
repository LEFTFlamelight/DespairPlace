package com.lediter.despair.block.superBlock;

import com.lediter.despair.procedures.D6Procedure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ReplacementTable extends Block {
    public ReplacementTable(Properties properties) {
        super(properties);
    }
    @Override
    public java.util.List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {

        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(this, 1));
    }
    @Override
    public ActionResultType onBlockActivated(BlockState blockstate, World world, BlockPos pos, PlayerEntity entity, Hand hand,
                                             BlockRayTraceResult hit) {
        super.onBlockActivated(blockstate, world, pos, entity, hand, hit);

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        double hitX = hit.getHitVec().x;
        double hitY = hit.getHitVec().y;
        double hitZ = hit.getHitVec().z;
        Direction direction = hit.getFace();

        D6Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
                (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
        return ActionResultType.SUCCESS;
    }
}
