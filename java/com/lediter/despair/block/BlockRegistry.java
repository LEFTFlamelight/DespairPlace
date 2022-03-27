package com.lediter.despair.block;

import com.lediter.despair.DespairMod;
import com.lediter.despair.block.superBlock.*;
import com.lediter.despair.event.world.dimension.despairPlace.DespairPlaceDimension;
import com.lediter.despair.fluid.FluidRegistry;
import com.lediter.despair.procedures.B1Procedure;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static com.lediter.despair.fluid.FluidRegistry.still;

public class BlockRegistry {
    //方块添加注册
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DespairMod.MOD_ID);
    public static final RegistryObject<Block> DESPAIR_BLOCK = BLOCKS.register("despair_block", () -> new DespairBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.BLUE).hardnessAndResistance(6.0F, 7.0F).harvestTool(ToolType.PICKAXE).harvestLevel(5).sound(SoundType.NETHER_GOLD)));
    public static final RegistryObject<Block> DESPAIR_BOX = BLOCKS.register("despair_box", () -> new DespairBox(AbstractBlock.Properties.create(Material.IRON, MaterialColor.BLUE).hardnessAndResistance(6.0F, 7.0F).harvestTool(ToolType.PICKAXE).harvestLevel(5).hardnessAndResistance(1f, 10f).setLightLevel(s -> 0).notSolid()//透明
            .setOpaque((bs, br, bp) -> false)));

    public static final RegistryObject<Block> REPLACEMENT_TABLE = BLOCKS.register("replacement_table", () -> new ReplacementTable((AbstractBlock.Properties.create(Material.IRON, MaterialColor.BLUE).hardnessAndResistance(4.0F, 4.0F).harvestTool(ToolType.AXE).harvestLevel(4).hardnessAndResistance(1f, 10f).setLightLevel(s -> 0).notSolid()
            .setOpaque((bs, br, bp) -> false))));
    public static final RegistryObject<Block> PILLAR = BLOCKS.register("pillar", () -> new Pillar(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BLUE).hardnessAndResistance(4.0F, 4.0F).harvestTool(ToolType.AXE).harvestLevel(4).hardnessAndResistance(1f, 10f).setLightLevel(s -> 7).notSolid()
            .setOpaque((bs, br, bp) -> false)));

    public static final RegistryObject<Block> TURNING_STONE = BLOCKS.register("turning_stone", () -> new TurningStone(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLUE).hardnessAndResistance(4.0F, 4.0F).harvestTool(ToolType.PICKAXE).harvestLevel(4)));
    public static final RegistryObject<Block> ROTARY_CUBE = BLOCKS.register("rotary_cube", () -> new RotaryCube(AbstractBlock.Properties.create(Material.IRON, MaterialColor.BLUE).hardnessAndResistance(5.0F, 4.0F).harvestTool(ToolType.PICKAXE).harvestLevel(4).hardnessAndResistance(1f, 10f).setLightLevel(s -> 10)
            .setNeedsPostProcessing((bs, br, bp) -> true).setEmmisiveRendering((bs, br, bp) -> true)));
    public static final RegistryObject<Block> ROTARY_BRICKS = BLOCKS.register("rotary_bricks", () -> new RotaryBricks(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLUE).hardnessAndResistance(5.0F, 4.0F).harvestTool(ToolType.PICKAXE).harvestLevel(4).notSolid()));
    public static final RegistryObject<Block> TURNING_HALFSTONE = BLOCKS.register("turning_halfstone", () -> new TurningHalfstone(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLUE).hardnessAndResistance(4.0F, 4.0F).harvestTool(ToolType.AXE).harvestLevel(4).setLightLevel(s -> 0).notSolid()
            .setOpaque((bs, br, bp) -> false)));
    public static final RegistryObject<Block> TURNING_STAIRS = BLOCKS.register("turning_stairs", () -> new TurningStairs(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLUE).hardnessAndResistance(4.0F, 4.0F).harvestTool(ToolType.PICKAXE).harvestLevel(4)));
    public static final RegistryObject<Block> ROTARY_CONTAINER = BLOCKS.register("rotary_container", () -> new RotaryContainer(AbstractBlock.Properties.create(Material.GLASS, MaterialColor.BLUE).hardnessAndResistance(6.0F, 7.0F).harvestTool(ToolType.PICKAXE).harvestLevel(5).sound(SoundType.GLASS).notSolid()));
    public static final RegistryObject<Block> DESPAIR_STONE = BLOCKS.register("despair_stone", () -> new DespairStone(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLUE).hardnessAndResistance(4.0F, 4.0F).harvestTool(ToolType.PICKAXE).harvestLevel(4)));
    public static final RegistryObject<Block> BLOOD_DIRT = BLOCKS.register("blood_dirt", () -> new BloodDirt(AbstractBlock.Properties.create(Material.GOURD, MaterialColor.RED).hardnessAndResistance(2.0F, 2.0F).harvestTool(ToolType.SHOVEL).harvestLevel(2).sound(SoundType.GROUND)));
    public static final RegistryObject<Block> BLOOD_STONE = BLOCKS.register("blood_stone", () -> new BloodStone(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.RED).hardnessAndResistance(4.0F, 4.0F).harvestTool(ToolType.PICKAXE).harvestLevel(4)));
    public static final RegistryObject<Block> BLOOD_COBBLESTONE = BLOCKS.register("blood_cobblestone", () -> new BloodCobblestone(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.RED).hardnessAndResistance(4.0F, 4.0F).harvestTool(ToolType.PICKAXE).harvestLevel(4)));
    public static final RegistryObject<Block> BLOOD_ORE = BLOCKS.register("blood_ore", () -> new BloodOre(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.RED).hardnessAndResistance(4.0F, 4.0F).harvestTool(ToolType.PICKAXE).harvestLevel(4)));

    //流体添加注册
    public static final RegistryObject<Block> BLLOD_FLUID_BLOCK = BLOCKS.register("blood_fluid_block",
            ()->new FlowingFluidBlock(still, Block.Properties.create(Material.WATER).hardnessAndResistance(100f).setLightLevel(s -> 0)) {
        @Override
        @ParametersAreNonnullByDefault
        public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
            return true;
        }
                @Override
                @ParametersAreNonnullByDefault
                public void onEntityCollision(BlockState blockstate, World world, BlockPos pos, Entity entity) {
                    super.onEntityCollision(blockstate, world, pos, entity);
                    int x = pos.getX();
                    int y = pos.getY();
                    int z = pos.getZ();

                    B1Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
                            (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
                }
            }
            );
    //植物添加注册
    public static final RegistryObject<Block> BLOOD_GRASS = BLOCKS.register("blood_grass", BloodGrassBlock.BlockCustomFlower::new);
    public static final RegistryObject<Block> BLOOD_WOOD_LOG = BLOCKS.register("blood_wood_log", BloodWoodLogBlock.CustomBlock::new);
    public static final RegistryObject<Block> BLOOD_WOOD_LEAVES = BLOCKS.register("blood_wood_leaves", BloodWoodLeavesBlock.CustomBlock::new);
    public static final RegistryObject<Block> BLOOD_WOOD_PLANKS = BLOCKS.register("blood_wood_planks", BloodWoodPlanksBlock.CustomBlock::new);





    //传送门方块
    public static final RegistryObject<DespairPlaceDimension.CustomPortalBlock> PORTAL_BLOCK = BLOCKS.register("portal_block",()->new DespairPlaceDimension.CustomPortalBlock());
}