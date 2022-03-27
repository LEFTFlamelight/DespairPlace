package com.lediter.despair.fluid;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Rarity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.StateContainer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColors;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.LakesFeature;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import java.util.Random;
import java.util.function.BiFunction;

import static com.lediter.despair.block.BlockRegistry.*;
import static com.lediter.despair.block.superBlock.RotaryContainer.block;
import static com.lediter.despair.item.ItemRegistry.BLOOD_BUCKET;

public class FluidRegistry {

    public static FlowingFluid flowing = null;
    public static FlowingFluid still = null;
    public static final ForgeFlowingFluid.Properties fluidProperties;
static {
    fluidProperties = new ForgeFlowingFluid.Properties(() -> still, () -> flowing,
            FluidAttributes.builder(new ResourceLocation("despair:block/blood"),
                            new ResourceLocation("despair:block/blood"))
                    .luminosity(0).density(1000).viscosity(1000).temperature(300)
                    .rarity(Rarity.COMMON).color(-13083194)).explosionResistance(100f).canMultiply().tickRate(5).levelDecreasePerBlock(1)
            .slopeFindDistance(4).bucket(() -> BLOOD_BUCKET.get()).block(() -> (FlowingFluidBlock) BLLOD_FLUID_BLOCK.get());
    flowing = (FlowingFluid) new CustomFlowingFluid.Flowing(fluidProperties).setRegistryName("blood_flowing");
    still = (FlowingFluid) new CustomFlowingFluid.Source(fluidProperties).setRegistryName("blood");
}
    public static class FluidRegisterHandler {
        @SubscribeEvent
        public void registerFluids(RegistryEvent.Register<Fluid> event) {
            event.getRegistry().register(flowing);
            event.getRegistry().register(still);
        }
    }
    public static abstract class CustomFlowingFluid extends ForgeFlowingFluid {
        public CustomFlowingFluid(Properties properties) {
            super(properties);
        }

        @OnlyIn(Dist.CLIENT)
        @Override
        public IParticleData getDripParticleData() {
            return ParticleTypes.FALLING_WATER;
        }

        public static class Source extends CustomFlowingFluid {
            public Source(Properties properties) {
                super(properties);
            }

            public int getLevel(FluidState state) {
                return 8;
            }

            public boolean isSource(FluidState state) {
                return true;
            }
        }

        public static class Flowing extends CustomFlowingFluid {
            public Flowing(Properties properties) {
                super(properties);
            }

            protected void fillStateContainer(StateContainer.Builder<Fluid, FluidState> builder) {
                super.fillStateContainer(builder);
                builder.add(LEVEL_1_8);
            }

            public int getLevel(FluidState state) {
                return state.get(LEVEL_1_8);
            }

            public boolean isSource(FluidState state) {
                return false;
            }
        }
    }

    public static class CustomFluidAttributes extends FluidAttributes {
        public static class CustomBuilder extends FluidAttributes.Builder {
            protected CustomBuilder(ResourceLocation stillTexture, ResourceLocation flowingTexture,
                                    BiFunction<Builder, Fluid, FluidAttributes> factory) {
                super(stillTexture, flowingTexture, factory);
            }
        }

        protected CustomFluidAttributes(CustomFluidAttributes.Builder builder, Fluid fluid) {
            super(builder, fluid);
        }

        public static CustomBuilder builder(ResourceLocation stillTexture, ResourceLocation flowingTexture) {
            return new CustomBuilder(stillTexture, flowingTexture, CustomFluidAttributes::new);
        }

        @Override
        public int getColor(IBlockDisplayReader world, BlockPos pos) {
            return BiomeColors.getWaterColor(world, pos) | 0xFF000000;
        }
    }

    private static Feature<BlockStateFeatureConfig> feature = null;
    private static ConfiguredFeature<?, ?> configuredFeature = null;

    private static class FeatureRegisterHandler {
        @SubscribeEvent
        public void registerFeature(RegistryEvent.Register<Feature<?>> event) {
            feature = new LakesFeature(BlockStateFeatureConfig.CODEC) {
                @Override
                public boolean generate(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, BlockStateFeatureConfig config) {
                    RegistryKey<World> dimensionType = world.getWorld().getDimensionKey();
                    boolean dimensionCriteria = false;
                    if (dimensionType == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("despair:blood_place")))
                        dimensionCriteria = true;
                    if (!dimensionCriteria)
                        return false;
                    return super.generate(world, generator, rand, pos, config);
                }
            };
            configuredFeature = feature.withConfiguration(new BlockStateFeatureConfig(block.getDefaultState()))
                    .withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(5)));
            event.getRegistry().register(feature.setRegistryName("blood_lakes"));
            Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation("despair:blood_lakes"), configuredFeature);
        }
    }
    @SubscribeEvent
    public void addFeatureToBiomes(BiomeLoadingEvent event) {
        event.getGeneration().getFeatures(GenerationStage.Decoration.LOCAL_MODIFICATIONS).add(() -> configuredFeature);
    }
    }
