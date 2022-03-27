package com.lediter.despair.item.tool;

import com.lediter.despair.tools.BloodTools;
import com.lediter.despair.tools.ExampleTools;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;

import java.util.Properties;

public class BloodSword extends SwordItem {
    public BloodSword(Item.Properties properties) {
        super(BloodTools.BLOOD,4,-2F,properties);
    }
}
