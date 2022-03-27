package com.lediter.despair.item.Items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class BloodIngot extends Item {
    public String tooltip;
    public String tooltip2;
    public BloodIngot(String tooltip,String tooltip2,Properties properties) {
        super(properties);
        this.tooltip=tooltip;
        this.tooltip2=tooltip2;
    }
    @Override
    @OnlyIn(Dist.CLIENT)
    @ParametersAreNonnullByDefault
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
            tooltip.add(new TranslationTextComponent(this.tooltip));
            tooltip.add(new TranslationTextComponent(this.tooltip2));
        }
        else {
            tooltip.add(new TranslationTextComponent("tooltip.despair.DC_ToolTip"));
        }
    }
}
