package com.lediter.despair.item.tool;

import com.lediter.despair.tools.BloodTools;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;

public class BloodPickaxe extends PickaxeItem {
    public BloodPickaxe(Properties properties) {
        super(BloodTools.BLOOD,-7,-2.4F,properties);
    }
}
