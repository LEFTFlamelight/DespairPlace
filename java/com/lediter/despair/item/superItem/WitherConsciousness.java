package com.lediter.despair.item.superItem;

import com.lediter.despair.procedures.D4Procedure;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class WitherConsciousness extends Item {
    public WitherConsciousness(Properties properties) {
        super(properties);
    }
    @Override
    public int getItemEnchantability() {
        return 0;
    }

    @Override
    public int getUseDuration(ItemStack itemstack) {
        return 0;
    }

    @Override
    public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
        return 1F;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
        ActionResult<ItemStack> ar = super.onItemRightClick(world, entity, hand);
        ItemStack itemstack = ar.getResult();
        double x = entity.getPosX();
        double y = entity.getPosY();
        double z = entity.getPosZ();

        D4Procedure.executeProcedure(Stream
                .of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x), new AbstractMap.SimpleEntry<>("y", y),
                        new AbstractMap.SimpleEntry<>("z", z), new AbstractMap.SimpleEntry<>("entity", entity))
                .collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
        return ar;
    }
}
