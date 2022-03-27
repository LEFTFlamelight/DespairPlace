package com.lediter.despair.item.consciousness;


import com.lediter.despair.procedures.D3Procedure;
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


public class ZombieConsciousness extends Item {
    public ZombieConsciousness(Properties properties) {
        super(properties);
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
        ActionResult<ItemStack> ar = super.onItemRightClick(world, entity, hand);
        ItemStack itemstack = ar.getResult();
        double x = entity.getPosX();
        double y = entity.getPosY();
        double z = entity.getPosZ();

        D3Procedure.executeProcedure(Stream
                .of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x), new AbstractMap.SimpleEntry<>("y", y),
                        new AbstractMap.SimpleEntry<>("z", z), new AbstractMap.SimpleEntry<>("entity", entity))
                .collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
        return ar;
    }


}
