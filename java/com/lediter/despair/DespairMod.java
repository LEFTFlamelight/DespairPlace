package com.lediter.despair;

import com.google.common.collect.ImmutableSet;
import com.lediter.despair.block.BlockRegistry;
import com.lediter.despair.block.superBlock.BloodGrassBlock;
import com.lediter.despair.block.superBlock.BloodOre;
import com.lediter.despair.block.superBlock.RotaryContainer;
import com.lediter.despair.entity.BloodVillagerEntity;
import com.lediter.despair.entity.EntityTypeRegister;
import com.lediter.despair.entity.KiriaEntity;
import com.lediter.despair.entity.renderer.BloodVillagerRenderer;
import com.lediter.despair.entity.renderer.KiriaEntityRenderer;
import com.lediter.despair.event.world.biome.BloodPlaceBiome;
import com.lediter.despair.event.world.dimension.despairPlace.DespairPlaceDimension;
import com.lediter.despair.fluid.FluidRegistry;
import com.lediter.despair.item.ItemRegistry;
import com.lediter.despair.sound.SoundRegistry;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.lediter.despair.block.superBlock.BloodGrassBlock.block;
import static com.lediter.despair.event.world.biome.BloodPlaceBiome.biome;
import static com.lediter.despair.event.world.dimension.despairPlace.DespairPlaceDimension.portal;
import static com.lediter.despair.fluid.FluidRegistry.flowing;
import static com.lediter.despair.fluid.FluidRegistry.still;
import static org.apache.http.params.CoreProtocolPNames.PROTOCOL_VERSION;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("despair")
public class DespairMod
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
public static final String MOD_ID="despair";
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation("despair", "despair"),
            () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
    public DespairMod() {
        // Register the setup method for modloading





        FMLJavaModLoadingContext.get().getModEventBus().register(new BloodGrassBlock.FeatureRegisterHandler());

        FMLJavaModLoadingContext.get().getModEventBus().register(new DespairPlaceDimension.POIRegisterHandler());

        FMLJavaModLoadingContext.get().getModEventBus().register(new FluidRegistry.FluidRegisterHandler());

        FMLJavaModLoadingContext.get().getModEventBus().register(new BloodVillagerRenderer.ModelRegisterHandler());

        FMLJavaModLoadingContext.get().getModEventBus().register(new BloodVillagerEntity.EntityAttributesRegisterHandler());

        FMLJavaModLoadingContext.get().getModEventBus().register(new BloodOre.FeatureRegisterHandler());

        FMLJavaModLoadingContext.get().getModEventBus().register(new BloodPlaceBiome.BiomeRegisterHandler());

        FMLJavaModLoadingContext.get().getModEventBus().register(new KiriaEntityRenderer.ModelRegisterHandler());

        FMLJavaModLoadingContext.get().getModEventBus().register(new KiriaEntity.EntityAttributesRegisterHandler());

        FMLJavaModLoadingContext.get().getModEventBus().register(new RotaryContainer.TileEntityRegisterHandler());

        ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

        BlockRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());

        SoundRegistry.SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());

        EntityTypeRegister.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);

        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
    private void setup(final FMLCommonSetupEvent event)
    {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM,
                new BiomeManager.BiomeEntry(RegistryKey.getOrCreateKey(Registry.BIOME_KEY,
                        Objects.requireNonNull(WorldGenRegistries.BIOME.getKey(biome))), 10));
        // some pre-init code
        //dimension
        Set<Block> replaceableBlocks = new HashSet<>();
        replaceableBlocks.add(BlockRegistry.DESPAIR_STONE.get());
        replaceableBlocks.add(ForgeRegistries.BIOMES.getValue(new ResourceLocation("despair:blood_place")).getGenerationSettings()
                .getSurfaceBuilder().get().getConfig().getTop().getBlock());
        replaceableBlocks.add(ForgeRegistries.BIOMES.getValue(new ResourceLocation("despair:blood_place")).getGenerationSettings()
                .getSurfaceBuilder().get().getConfig().getUnder().getBlock());
        DeferredWorkQueue.runLater(() -> {
            try {
                ObfuscationReflectionHelper.setPrivateValue(WorldCarver.class, WorldCarver.CAVE, new ImmutableSet.Builder<Block>()
                        .addAll((Set<Block>) ObfuscationReflectionHelper.getPrivateValue(WorldCarver.class, WorldCarver.CAVE, "field_222718_j"))
                        .addAll(replaceableBlocks).build(), "field_222718_j");
                ObfuscationReflectionHelper.setPrivateValue(WorldCarver.class, WorldCarver.CANYON, new ImmutableSet.Builder<Block>()
                        .addAll((Set<Block>) ObfuscationReflectionHelper.getPrivateValue(WorldCarver.class, WorldCarver.CANYON, "field_222718_j"))
                        .addAll(replaceableBlocks).build(), "field_222718_j");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        LOGGER.info("FORGE I LOVE YOU!!!!!!");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        //fluid
        event.enqueueWork(()->{
            RenderTypeLookup.setRenderLayer(still, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(flowing, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(block, RenderType.getCutout());
            //dimension
            DimensionRenderInfo customEffect = new DimensionRenderInfo(Float.NaN, true, DimensionRenderInfo.FogType.NONE, false, false) {
                @Override
                @ParametersAreNonnullByDefault
                @Nonnull
                public Vector3d func_230494_a_(Vector3d color, float sunHeight) {
                    return new Vector3d(0.2, 0, 0);
                }

                @Override
                public boolean func_230493_a_(int x, int y) {
                    return true;
                }
            };
            DeferredWorkQueue.runLater(() -> {
                try {
                    Object2ObjectMap<ResourceLocation, DimensionRenderInfo> effectsRegistry = (Object2ObjectMap<ResourceLocation, DimensionRenderInfo>) ObfuscationReflectionHelper
                            .getPrivateValue(DimensionRenderInfo.class, null, "field_239208_a_");
                    effectsRegistry.put(new ResourceLocation("despairrealm:despair_place"), customEffect);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            RenderTypeLookup.setRenderLayer(portal, RenderType.getTranslucent());
        });
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get());
    }
    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("despair", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");

    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
    public static final ItemGroup ITEMS=new ItemGroup("items"){
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemRegistry.DESPAIR_SWORD.get());
        }
    };
}
