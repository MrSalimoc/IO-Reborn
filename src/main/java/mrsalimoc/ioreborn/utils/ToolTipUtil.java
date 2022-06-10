package mrsalimoc.ioreborn.utils;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;

public class ToolTipUtil {

    public static List<ITextComponent> getItemTooltip(String itemName, List<ITextComponent> tooltip) {
        if(Screen.hasControlDown() && Screen.hasShiftDown()) {
            tooltip.add(1, TextComponentUtil.build(TextFormatting.GRAY, new TranslationTextComponent("tooltip.ioreborn." + itemName + ".description")));
        } else {
            tooltip.add(1, TextComponentUtil.build(new TranslationTextComponent("tooltip.ioreborn.hint")));
        }
        return tooltip;
    }

    public static List<ITextComponent> getBlockTooltip(String blockName, List<ITextComponent> tooltip) {
        if(Screen.hasControlDown() && Screen.hasShiftDown()) {
            tooltip.add(1, TextComponentUtil.build(TextFormatting.GRAY, new TranslationTextComponent("tooltip.ioreborn." + blockName + ".description")));
        //} else if(Screen.hasAltDown()) {
        //    tooltip.add(1, TextComponentUtil.build(TextFormatting.GRAY, new TranslationTextComponent("tooltip.ioreborn." + blockName + ".methods")));
        } else {
            tooltip.add(1, TextComponentUtil.build(new TranslationTextComponent("tooltip.ioreborn.hint")));
            //tooltip.add(2, TextComponentUtil.build(new TranslationTextComponent("tooltip.ioreborn.methods")));
        }
        return tooltip;
    }
}
