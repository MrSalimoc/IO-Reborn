package mrsalimoc.ioreborn.common.items;

import mrsalimoc.ioreborn.IOReborn;
import mrsalimoc.ioreborn.utils.TextComponentUtil;
import mrsalimoc.ioreborn.utils.ToolTipUtil;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.*;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import javax.swing.text.AttributeSet;
import java.util.Collection;
import java.util.List;

public class ProximityCardItem extends Item {

    public ProximityCardItem(Properties properties) {
        super(properties);
    }


    @Override
    public Rarity getRarity(ItemStack p_77613_1_) {
        return Rarity.UNCOMMON;
    }

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> tooltip, ITooltipFlag p_77624_4_) {
        ToolTipUtil.getItemTooltip("proximity_card", tooltip);
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        return super.getRGBDurabilityForDisplay(stack);
    }
}
