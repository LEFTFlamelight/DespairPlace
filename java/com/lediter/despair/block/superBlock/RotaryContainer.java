package com.lediter.despair.block.superBlock;

import com.lediter.despair.procedures.D5Procedure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ObjectHolder;

import java.util.*;
import java.util.stream.Stream;

public class RotaryContainer extends Block {
    @ObjectHolder("despair:rotary_container")
    public static final Block block = null;

    @OnlyIn(Dist.CLIENT)
    public void clientLoad(FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(block, RenderType.getCutout());
    }



    public RotaryContainer(Properties properties) {
        super(properties);
    }
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        Vector3d offset = state.getOffset(world, pos);
        return VoxelShapes.or(makeCuboidShape(3, 0, 3, 13, 16, 13),makeCuboidShape(2, 0, 4, 3, 12, 12)

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

        D5Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
                (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
        return ActionResultType.SUCCESS;
    }
}