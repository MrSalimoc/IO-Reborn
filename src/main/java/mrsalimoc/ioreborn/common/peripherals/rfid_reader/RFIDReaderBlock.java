package mrsalimoc.ioreborn.common.peripherals.rfid_reader;

import mrsalimoc.ioreborn.utils.Registration;
import mrsalimoc.ioreborn.utils.ToolTipUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;
import java.util.List;

public class RFIDReaderBlock extends Block {


    public RFIDReaderBlock(Properties properties) {
        super(properties.noOcclusion());


    }

    @Override
    public void appendHoverText(ItemStack p_190948_1_, @Nullable IBlockReader p_190948_2_, List<ITextComponent> tooltip, ITooltipFlag p_190948_4_) {
        ToolTipUtil.getBlockTooltip("rfid_reader", tooltip);
    }

    /*@Override
    public String getDescriptionId() {
        return TextFormatting.YELLOW + new TranslationTextComponent("block.ioreborn.rfid_reader").getString();
    }*/


    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return Registration.RFID_READER_TILEENTITY.get().create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
