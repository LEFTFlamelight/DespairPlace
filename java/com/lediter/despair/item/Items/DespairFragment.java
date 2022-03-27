package com.lediter.despair.item.Items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class DespairFragment extends Item {
    public String tooltip;
    public String tooltip2;
    public DespairFragment(String tooltip,String tooltip2,Properties properties) {
        super(properties);
        this.tooltip=tooltip;
        this.tooltip2=tooltip2;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    @ParametersAreNonnullByDefault
    public void addInformation(ItemStack itemstack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.addInformation(itemstack, world, tooltip, flag);
        tooltip.add(new TranslationTextComponent(this.tooltip));
        tooltip.add(new TranslationTextComponent(this.tooltip2));
    }
}
