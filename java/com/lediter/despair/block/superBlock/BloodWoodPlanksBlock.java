
package com.lediter.despair.block.superBlock;

import com.lediter.despair.block.BlockRegistry;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.common.ToolType;

import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import java.util.List;
import java.util.Collections;

public class BloodWoodPlanksBlock{
	@ObjectHolder("despair:blood_wood_planks")
	public static final Block block = null;

	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(20f, 18.9287203344058f).setLightLevel(s -> 0)
					.harvestLevel(0).harvestTool(ToolType.AXE).setRequiresTool());
		}

		@Override
		public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
			return 15;
		}

		@Override
		public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
			return 50;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}
	}
}
