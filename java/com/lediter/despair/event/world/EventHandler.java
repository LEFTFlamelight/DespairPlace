package com.lediter.despair.event.world;

import com.lediter.despair.block.BlockRegistry;
import com.lediter.despair.event.structures.Str1;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class EventHandler {
    public static void worldGenRuns(final BiomeLoadingEvent event){
        if(!event.getCategory().equals(Biome.Category.NETHER)||!event.getCategory().equals(Biome.Category.THEEND));{
    oreGenSet(event.getGeneration(),
            OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
            BlockRegistry.DESPAIR_BLOCK.get().getDefaultState(),
            9,1,32,30,7);
    //矿石区块生成多少，矿石生成最低高度，矿石偏移量，矿石生成最高高度，矿石堆大小
        }
    }
    public static void oreGenSet(BiomeGenerationSettingsBuilder settings, RuleTest fillerType, BlockState state,int side,int bottomOffset,int topOffset,int maximum,int amount){
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.withConfiguration(new OreFeatureConfig(fillerType,state,side))
                        .withPlacement(Placement.RANGE.configure
                                (new TopSolidRangeConfig(bottomOffset,topOffset,maximum)))
                        .square().count(amount));
    }
    }
