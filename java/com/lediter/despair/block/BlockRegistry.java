package com.lediter.despair.block;

import com.lediter.despair.DespairMod;
import com.lediter.despair.block.superBlock.*;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS=DeferredRegister.create(ForgeRegistries.BLOCKS, DespairMod.MOD_ID);
    public static final RegistryObject<Block> DESPAIR_BLOCK=BLOCKS.register("despair_block",()->new DespairBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.BLUE).hardnessAndResistance(6.0F,7.0F).harvestTool(ToolType.PICKAXE).harvestLevel(5)));
    public static final RegistryObject<Block> DESPAIR_BOX=BLOCKS.register("despair_box",()->new DespairBox(AbstractBlock.Properties.create(Material.IRON, MaterialColor.BLUE).hardnessAndResistance(6.0F,7.0F).harvestTool(ToolType.PICKAXE).harvestLevel(5).hardnessAndResistance(1f, 10f).setLightLevel(s -> 0).notSolid()//透明
            .setOpaque((bs, br, bp) -> false)));

    public static final RegistryObject<Block> REPLACEMENT_TABLE=BLOCKS.register("replacement_table",()->new ReplacementTable((AbstractBlock.Properties.create(Material.IRON, MaterialColor.BLUE).hardnessAndResistance(4.0F,4.0F).harvestTool(ToolType.AXE).harvestLevel(4).hardnessAndResistance(1f, 10f).setLightLevel(s -> 0).notSolid()
            .setOpaque((bs, br, bp) -> false))));
    public static final RegistryObject<Block> PILLAR=BLOCKS.register("pillar",()->new Pillar(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BLUE).hardnessAndResistance(4.0F,4.0F).harvestTool(ToolType.AXE).harvestLevel(4).hardnessAndResistance(1f, 10f).setLightLevel(s -> 7).notSolid()
            .setOpaque((bs, br, bp) -> false)));

    public static final RegistryObject<Block> TURNING_STONE=BLOCKS.register("turning_stone",()->new TurningStone(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLUE).hardnessAndResistance(4.0F,4.0F).harvestTool(ToolType.PICKAXE).harvestLevel(4)));
    public static final RegistryObject<Block> ROTARY_CUBE=BLOCKS.register("rotary_cube",()->new RotaryCube(AbstractBlock.Properties.create(Material.IRON, MaterialColor.BLUE).hardnessAndResistance(5.0F,4.0F).harvestTool(ToolType.PICKAXE).harvestLevel(4).hardnessAndResistance(1f, 10f).setLightLevel(s -> 10)
            .setNeedsPostProcessing((bs, br, bp) -> true).setEmmisiveRendering((bs, br, bp) -> true)));
    public static final RegistryObject<Block> ROTARY_BRICKS=BLOCKS.register("rotary_bricks",()->new RotaryBricks(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLUE).hardnessAndResistance(5.0F,4.0F).harvestTool(ToolType.PICKAXE).harvestLevel(4).notSolid()));
    public static final RegistryObject<Block> TURNING_HALFSTONE=BLOCKS.register("turning_halfstone",()->new TurningHalfstone(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLUE).hardnessAndResistance(4.0F,4.0F).harvestTool(ToolType.AXE).harvestLevel(4).setLightLevel(s -> 0).notSolid()
            .setOpaque((bs, br, bp) -> false)));
    public static final RegistryObject<Block> TURNING_STAIRS=BLOCKS.register("turning_stairs",()->new TurningStairs(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLUE).hardnessAndResistance(4.0F,4.0F).harvestTool(ToolType.PICKAXE).harvestLevel(4)));
    public static final RegistryObject<Block> ROTARY_CONTAINER=BLOCKS.register("rotary_container",()->new RotaryContainer(AbstractBlock.Properties.create(Material.GLASS, MaterialColor.BLUE).hardnessAndResistance(6.0F,7.0F).harvestTool(ToolType.PICKAXE).harvestLevel(5).sound(SoundType.GLASS).notSolid()));
    public static final RegistryObject<Block> DESPAIR_STONE=BLOCKS.register("despair_stone",()->new DespairStone(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLUE).hardnessAndResistance(4.0F,4.0F).harvestTool(ToolType.PICKAXE).harvestLevel(4)));
}
