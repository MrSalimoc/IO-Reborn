package mrsalimoc.ioreborn.common.peripherals.mag_card_reader;

import mrsalimoc.ioreborn.common.blocks.Blocks;
import mrsalimoc.ioreborn.utils.Registration;
import mrsalimoc.ioreborn.utils.TextComponentUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.BeaconTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.*;
import net.minecraft.util.text.Color;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.List;

public class MagCardReaderBlock extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final IntegerProperty STATE = IntegerProperty.create("reader_state", 0,4);

    public MagCardReaderBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
        registerDefaultState(defaultBlockState().setValue(STATE, 0));
    }

    @Override
    public void appendHoverText(ItemStack p_190948_1_, @Nullable IBlockReader p_190948_2_, List<ITextComponent> tooltip, ITooltipFlag p_190948_4_) {
        if(Screen.hasControlDown() && Screen.hasShiftDown()) {
            tooltip.add(1, TextComponentUtil.build(TextFormatting.GRAY, new TranslationTextComponent("tooltip.ioreborn.mag_card_reader.description")));
        } else if(Screen.hasAltDown()) {
            tooltip.add(1, TextComponentUtil.build(TextFormatting.GRAY, new TranslationTextComponent("tooltip.ioreborn.mag_card_reader.methods")));
        } else {
            tooltip.add(1, TextComponentUtil.build(new TranslationTextComponent("tooltip.ioreborn.hint")));
            tooltip.add(2, TextComponentUtil.build(new TranslationTextComponent("tooltip.ioreborn.methods")));
        }
    }

    @Override
    public String getDescriptionId() {
        return TextFormatting.YELLOW + new TranslationTextComponent("block.ioreborn.mag_card_reader").getString();
    }



    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(STATE);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }


    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return Registration.MAG_CARD_READER_TILEENTITY.get().create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

}
