package mrsalimoc.ioreborn.common.items.silicon_ball;

import mrsalimoc.ioreborn.IOReborn;
import mrsalimoc.ioreborn.utils.TextComponentUtil;
import mrsalimoc.ioreborn.utils.ToolTipUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import java.util.List;

public class SiliconBallItem extends Item {

    public SiliconBallItem(Properties properties) {
        super(properties);
    }

    @Override
    public ITextComponent getName(ItemStack p_200295_1_) {
        return TextComponentUtil.build(TextFormatting.AQUA, super.getName(p_200295_1_));
    }

    @Override
    public ActionResultType useOn(ItemUseContext p_195939_1_) {
        IOReborn.LOGGER.log(Level.DEBUG, p_195939_1_.getClickedPos().toString() + " " + p_195939_1_.getClickedFace().toString());
        return super.useOn(p_195939_1_);
    }

    @Override
    public Rarity getRarity(ItemStack p_77613_1_) {
        return Rarity.UNCOMMON;
    }

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> tooltip, ITooltipFlag p_77624_4_) {
        ToolTipUtil.getItemTooltip("silicon_ball", tooltip);
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        return super.getRGBDurabilityForDisplay(stack);
    }
}
