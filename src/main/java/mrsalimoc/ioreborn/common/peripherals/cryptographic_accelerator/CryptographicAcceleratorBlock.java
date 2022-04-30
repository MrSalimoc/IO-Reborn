package mrsalimoc.ioreborn.common.peripherals.cryptographic_accelerator;

import mrsalimoc.ioreborn.utils.Registration;
import mrsalimoc.ioreborn.utils.TextComponentUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

import javax.annotation.Nullable;
import java.util.List;

public class CryptographicAcceleratorBlock extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public CryptographicAcceleratorBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    public void appendHoverText(ItemStack p_190948_1_, @Nullable IBlockReader p_190948_2_, List<ITextComponent> tooltip, ITooltipFlag p_190948_4_) {
        if(Screen.hasControlDown() && Screen.hasShiftDown()) {
            tooltip.add(1, TextComponentUtil.build(TextFormatting.GRAY, new TranslationTextComponent("tooltip.ioreborn.cryptographic_accelerator.description")));
        } else if(Screen.hasAltDown()) {
            tooltip.add(1, TextComponentUtil.build(TextFormatting.GRAY, new TranslationTextComponent("tooltip.ioreborn.cryptographic_accelerator.methods")));
        } else {
            tooltip.add(1, TextComponentUtil.build(new TranslationTextComponent("tooltip.ioreborn.hint")));
            tooltip.add(2, TextComponentUtil.build(new TranslationTextComponent("tooltip.ioreborn.methods")));
        }
    }

    @Override
    public String getDescriptionId() {
        return TextFormatting.YELLOW + new TranslationTextComponent("block.ioreborn.cryptographic_accelerator").getString();
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }


    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return Registration.CRYPTOGRAPHIC_ACCELERATOR_TILEENTITY.get().create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
