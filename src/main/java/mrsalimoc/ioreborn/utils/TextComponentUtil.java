package mrsalimoc.ioreborn.utils;

import mrsalimoc.ioreborn.IOReborn;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.text.*;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.fluids.FluidStack;
import org.apache.logging.log4j.Level;

public class TextComponentUtil {

    public static IFormattableTextComponent color(IFormattableTextComponent component, int color) {
        return component.setStyle(component.getStyle()
                .withColor(Color.fromRgb(color)));
    }

    public static int RGBA(int r, int g, int b, int a) {
        return (a << 24) | ((r & 255) << 16) | ((g & 255) << 8) | (b & 255);
    }

    public static IFormattableTextComponent build(Object... components) {
        //TODO: Verify that just appending them to the first text component works properly.
        // My suspicion is we will need to chain downwards and append it that way so that the formatting matches
        // from call to call without resetting back to
        IFormattableTextComponent result = null;
        Style cachedStyle = Style.EMPTY;
        for (Object component : components) {
            if (component == null) {
                //If the component doesn't exist just skip it
                continue;
            }

            IFormattableTextComponent current = null;
            if (component instanceof ITextComponent) {
                //Just append if a text component is being passed
                current = ((ITextComponent) component).copy();
            } else if (component instanceof TextFormatting) {
                cachedStyle = cachedStyle.applyFormat((TextFormatting) component);
            } else if (component instanceof ClickEvent) {
                cachedStyle = cachedStyle.withClickEvent((ClickEvent) component);
            } else if (component instanceof HoverEvent) {
                cachedStyle = cachedStyle.withHoverEvent((HoverEvent) component);
            } else if (component instanceof Block) {
                current = translate(((Block) component).getDescriptionId());
            } else if (component instanceof Item) {
                current = translate(((Item) component).getDescriptionId());
            } else if (component instanceof ItemStack) {
                current = ((ItemStack) component).getHoverName().copy();
            } else if (component instanceof FluidStack) {
                current = translate(((FluidStack) component).getTranslationKey());
            } else if (!(component instanceof Direction)) {
                //Fallback to a generic replacement
                // this handles strings, booleans, numbers, and any type we don't necessarily know about
                current = getString(component.toString());
            }
            if (current == null) {
                //If we don't have a component to add, don't
                continue;
            }
            if (!cachedStyle.isEmpty()) {
                //Apply the style and reset
                current.setStyle(cachedStyle);
                cachedStyle = Style.EMPTY;
            }
            if (result == null) {
                result = current;
            } else {
                result.append(current);
            }
        }
        //TODO: Make this more like smartTranslate? Including back to back formatting where we already have that type of formatting set
        // then convert that
        //Ignores any trailing formatting
        return result;
    }

    public static StringTextComponent getString(String component) {
        return new StringTextComponent(cleanString(component));
    }

    private static String cleanString(String component) {
        return component.replace("\u00A0", " ");
    }

    public static TranslationTextComponent translate(String key, Object... args) {
        return new TranslationTextComponent(key, args);
    }
}
