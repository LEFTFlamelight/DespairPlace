package com.lediter.despair.block.superBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.IRuleTestType;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.lediter.despair.block.superBlock.RotaryContainer.block;

public class BloodOre extends Block {
    public BloodOre(Properties properties) {
        super(properties);
    }
    @Override
    @ParametersAreNonnullByDefault
    @Nonnull
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(this, 1));
    }

    private static ConfiguredFeature<?, ?> configuredFeature = null;

    private static IRuleTestType<CustomRuleTest> CUSTOM_MATCH = null;

    private static class CustomRuleTest extends RuleTest {

        static final CustomRuleTest INSTANCE = new CustomRuleTest();
        static final com.mojang.serialization.Codec<CustomRuleTest> codec = com.mojang.serialization.Codec.unit(() -> INSTANCE);
        @ParametersAreNonnullByDefault
        public boolean test(BlockState blockAt, Random random) {

    return blockAt.getBlock() == Blocks.STONE;
        }
        @Nonnull
        protected IRuleTestType<?> getType() {
            return CUSTOM_MATCH;
        }

    }

    public static class FeatureRegisterHandler {

        @SubscribeEvent
        public void registerFeature(RegistryEvent.Register<Feature<?>> event) {
            CUSTOM_MATCH = Registry.register(Registry.RULE_TEST, new ResourceLocation("despair:blood_ore_match"), () -> CustomRuleTest.codec);

            Feature<OreFeatureConfig> feature = new OreFeature(OreFeatureConfig.CODEC) {
                @Override
                @ParametersAreNonnullByDefault
                public boolean generate(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, OreFeatureConfig config) {
                    RegistryKey<World> dimensionType = world.getWorld().getDimensionKey();
                    boolean dimensionCriteria = dimensionType == World.OVERWORLD;

                    if (!dimensionCriteria)
                        return false;

                    return super.generate(world, generator, rand, pos, config);
                }
            };

            assert false;
            configuredFeature = feature.withConfiguration(new OreFeatureConfig(CustomRuleTest.INSTANCE, block.getDefaultState(), 6)).range(30)
                    .square().count(5);

            event.getRegistry().register(feature.setRegistryName("blood_ore"));
            Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation("despair:blood_ore"), configuredFeature);
        }
    }
    @SubscribeEvent
    public void addFeatureToBiomes(BiomeLoadingEvent event) {
        event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).add(() -> configuredFeature);
    }
}
