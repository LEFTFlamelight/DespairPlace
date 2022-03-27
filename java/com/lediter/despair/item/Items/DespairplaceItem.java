package com.lediter.despair.item.Items;

import com.lediter.despair.DespairMod;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ActionResultType;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;

import static com.lediter.despair.event.world.dimension.despairPlace.DespairPlaceDimension.portal;


public class DespairplaceItem extends Item {
    @ObjectHolder("despair:despairplace_item")
    public static final Item block = null;

    public DespairplaceItem() {
        super(new Item.Properties().group(DespairMod.ITEMS).maxDamage(64));
    }
    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        PlayerEntity entity = context.getPlayer();
        BlockPos pos = context.getPos().offset(context.getFace());
        ItemStack itemstack = context.getItem();
        World world = context.getWorld();
        if (!entity.canPlayerEdit(pos, context.getFace(), itemstack)) {
            return ActionResultType.FAIL;
        } else {
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
            boolean success = false;
            if (world.isAirBlock(pos)) {
                portal.portalSpawn(world, pos);
                itemstack.damageItem(1, entity, c -> c.sendBreakAnimation(context.getHand()));
                success = true;
            }
            return success ? ActionResultType.SUCCESS : ActionResultType.FAIL;
        }
    }
}
