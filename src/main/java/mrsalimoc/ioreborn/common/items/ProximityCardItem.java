package mrsalimoc.ioreborn.common.items;

import mrsalimoc.ioreborn.IOReborn;
import mrsalimoc.ioreborn.utils.TextComponentUtil;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;

public class ProximityCardItem extends Item {

    public ProximityCardItem(Properties properties) {
        super(properties);
    }

    @Override
    public ITextComponent getName(ItemStack p_200295_1_) {
        return TextComponentUtil.build(TextFormatting.AQUA, super.getName(p_200295_1_));
    }

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> tooltip, ITooltipFlag p_77624_4_) {
        if(Screen.hasControlDown() && Screen.hasShiftDown()) {
            tooltip.add(1, TextComponentUtil.build(TextFormatting.GRAY, new TranslationTextComponent("tooltip.ioreborn.proximity_card.description")));
        } else {
            tooltip.add(1, TextComponentUtil.build(new TranslationTextComponent("tooltip.ioreborn.hint")));
        }
    }

    @Override
    public String getDescriptionId() {
        return TextFormatting.AQUA + new TranslationTextComponent("item.ioreborn.proximity_card").getString();
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        return super.getRGBDurabilityForDisplay(stack);
    }
}
