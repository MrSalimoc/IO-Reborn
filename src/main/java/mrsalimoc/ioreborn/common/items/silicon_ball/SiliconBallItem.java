package mrsalimoc.ioreborn.common.items.silicon_ball;

import mrsalimoc.ioreborn.IOReborn;
import mrsalimoc.ioreborn.common.peripherals.mag_card_reader.MagCardReaderBlock;
import mrsalimoc.ioreborn.common.peripherals.mag_card_reader.MagCardReaderTileEntity;
import mrsalimoc.ioreborn.utils.TextComponentUtil;
import mrsalimoc.ioreborn.utils.ToolTipUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
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
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand hand) {
        ItemStack itemStackIn = playerIn.getItemInHand(hand);
        CompoundNBT nbtTagCompound = itemStackIn.getTag();
        if(playerIn.isCrouching()) {
            if (nbtTagCompound == null) {
                nbtTagCompound = new CompoundNBT();
                itemStackIn.setTag(nbtTagCompound);
            }
            if(!nbtTagCompound.contains("fistprint")) {
                nbtTagCompound.putString("fistprint", playerIn.getDisplayName().getString());
            }
        }


        return super.use(worldIn, playerIn, hand);
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
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
