package com.lediter.despair.item.tool;

import com.lediter.despair.tools.BloodTools;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;

public class BloodAxe extends AxeItem {
    public BloodAxe(Properties properties) {
        super(BloodTools.BLOOD,5,-3F,properties);
    }
}
