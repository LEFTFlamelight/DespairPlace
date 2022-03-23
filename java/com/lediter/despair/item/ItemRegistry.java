package com.lediter.despair.item;

import com.lediter.despair.DespairMod;
import com.lediter.despair.block.BlockRegistry;
import com.lediter.despair.item.superItem.*;
import com.lediter.despair.tools.ExampleArmor;
import com.lediter.despair.tools.ExampleTools;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ItemRegistry {
    //物品
    public static final DeferredRegister<Item> ITEMS;
    //盔甲添加注册
    public static final RegistryObject<Item> DESPAIR_HELMET;
    public static final RegistryObject<Item> DESPAIR_CHESTPLATE;
    public static final RegistryObject<Item> DESPAIR_LEGGINGS;
    public static final RegistryObject<Item> DESPAIR_BOOTS;
    //物品添加注册
    public static final RegistryObject<Item> DESPAIR_CRYSTAL;
    public static final RegistryObject<Item> DESPAIR_FRAGMENT;
    public static final RegistryObject<Item> CONSCIOUSNESS_STONE;
    public static final RegistryObject<Item> EMPTY_CONSCIOUSNESS_TEMPLATE;
    public static final RegistryObject<Item> ZOMBIE_CONSCIOUSNESS;
    public static final RegistryObject<Item> WITHER_CONSCIOUSNESS;




    //工具添加注册
    public static final RegistryObject<Item> DESPAIR_AXE;
    public static final RegistryObject<Item> DESPAIR_SWORD;
    public static final RegistryObject<Item> ABOVE_DESPAIR;
    public static final RegistryObject<Item> DESPAIR_PICKAXE;
    //方块添加注册
    public static final RegistryObject<Item> DESPAIR_BLOCK;
    public static final RegistryObject<Item> DESPAIR_BOX;
    public static final RegistryObject<Item> REPLACEMENT_TABLE;
    public static final RegistryObject<Item> PILLAR;
    public static final RegistryObject<Item> ROTARY_CUBE;
    public static final RegistryObject<Item> TURNING_STONE;
    public static final RegistryObject<Item> ROTARY_BRICKS;
    public static final RegistryObject<Item> TURNING_HALFSTONE;
    public static final RegistryObject<Item> TURNING_STAIRS;
    public static final RegistryObject<Item> ROTARY_CONTAINER;
    public static final RegistryObject<Item> DESPAIR_STONE;

static{
    //物品
     ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DespairMod.MOD_ID);
    //盔甲添加注册
    DESPAIR_HELMET=ITEMS.register("despair_helmet",() -> new ArmorItem(ExampleArmor.DESPAIR, EquipmentSlotType.HEAD,new Item.Properties().group(DespairMod.ITEMS)));
    DESPAIR_CHESTPLATE=ITEMS.register("despair_chestplate",() -> new ArmorItem(ExampleArmor.DESPAIR, EquipmentSlotType.CHEST,new Item.Properties().group(DespairMod.ITEMS)));
    DESPAIR_LEGGINGS=ITEMS.register("despair_leggings",() -> new ArmorItem(ExampleArmor.DESPAIR, EquipmentSlotType.LEGS,new Item.Properties().group(DespairMod.ITEMS)));
    DESPAIR_BOOTS=ITEMS.register("despair_boots",() -> new ArmorItem(ExampleArmor.DESPAIR, EquipmentSlotType.FEET,new Item.Properties().group(DespairMod.ITEMS)));
    //物品添加注册
    DESPAIR_CRYSTAL=ITEMS.register("despair_crystal",() -> new DespairCrystal("tooltip.despair.shift","tooltip.despair.shift2",new Item.Properties().group(DespairMod.ITEMS)));
    DESPAIR_FRAGMENT=ITEMS.register("despair_fragment",() -> new DespairFragment("stc.1","stc.2",new Item.Properties().group(DespairMod.ITEMS)));
    CONSCIOUSNESS_STONE=ITEMS.register("consciousness_stone",() -> new Item(new Item.Properties().group(DespairMod.ITEMS)));
    EMPTY_CONSCIOUSNESS_TEMPLATE=ITEMS.register("empty_consciousness_template",() -> new EmptyConsciousnessTemplate(new Item.Properties().group(DespairMod.ITEMS)));
    ZOMBIE_CONSCIOUSNESS=ITEMS.register("zombie_consciousness",() -> new ZombieConsciousness(new Item.Properties().group(DespairMod.ITEMS)));
    WITHER_CONSCIOUSNESS=ITEMS.register("wither_consciousness",() -> new WitherConsciousness(new Item.Properties().group(DespairMod.ITEMS)));




    //工具添加注册
    DESPAIR_AXE=ITEMS.register("despair_axe",() -> new AxeItem(ExampleTools.DESPAIR,5,-3F,new Item.Properties().group(DespairMod.ITEMS)));
    DESPAIR_SWORD=ITEMS.register("despair_sword",() -> new DespairSword(new Item.Properties().group(DespairMod.ITEMS)));
    ABOVE_DESPAIR=ITEMS.register("above_despair",() -> new SwordItem(ExampleTools.DESPAIR,10,-2F,new Item.Properties().group(DespairMod.ITEMS)));


    DESPAIR_PICKAXE=ITEMS.register("despair_pickaxe",() -> new PickaxeItem(ExampleTools.DESPAIR,-7,-2.4F,new Item.Properties().group(DespairMod.ITEMS)));
    //方块添加注册
    DESPAIR_BLOCK=ITEMS.register("despair_block",()->new BlockItem(BlockRegistry.DESPAIR_BLOCK.get(),new Item.Properties().group(DespairMod.ITEMS)));
    DESPAIR_BOX=ITEMS.register("despair_box",()->new BlockItem(BlockRegistry.DESPAIR_BOX.get(),new Item.Properties().group(DespairMod.ITEMS)));
    REPLACEMENT_TABLE=ITEMS.register("replacement_table",()->new BlockItem(BlockRegistry.REPLACEMENT_TABLE.get(),new Item.Properties().group(DespairMod.ITEMS)));
    PILLAR=ITEMS.register("pillar",()->new BlockItem(BlockRegistry.PILLAR.get(),new Item.Properties().group(DespairMod.ITEMS)));
    ROTARY_CUBE=ITEMS.register("rotary_cube",()->new BlockItem(BlockRegistry.ROTARY_CUBE.get(),new Item.Properties().group(DespairMod.ITEMS)));
    TURNING_STONE=ITEMS.register("turning_stone",()->new BlockItem(BlockRegistry.TURNING_STONE.get(),new Item.Properties().group(DespairMod.ITEMS)));
    ROTARY_BRICKS=ITEMS.register("rotary_bricks",()->new BlockItem(BlockRegistry.ROTARY_BRICKS.get(),new Item.Properties().group(DespairMod.ITEMS)));
    TURNING_HALFSTONE=ITEMS.register("turning_halfstone",()->new BlockItem(BlockRegistry.TURNING_HALFSTONE.get(),new Item.Properties().group(DespairMod.ITEMS)));
    TURNING_STAIRS=ITEMS.register("turning_stairs",()->new BlockItem(BlockRegistry.TURNING_STAIRS.get(),new Item.Properties().group(DespairMod.ITEMS)));
    ROTARY_CONTAINER=ITEMS.register("rotary_container",()->new BlockItem(BlockRegistry.ROTARY_CONTAINER.get(),new Item.Properties().group(DespairMod.ITEMS)));
    DESPAIR_STONE=ITEMS.register("despair_stone",()->new BlockItem(BlockRegistry.DESPAIR_STONE.get(),new Item.Properties().group(DespairMod.ITEMS)));
}

}