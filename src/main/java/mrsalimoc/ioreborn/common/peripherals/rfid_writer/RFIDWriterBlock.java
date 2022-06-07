package mrsalimoc.ioreborn.common.peripherals.rfid_writer;

import mrsalimoc.ioreborn.utils.Registration;
import mrsalimoc.ioreborn.utils.ToolTipUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

import javax.annotation.Nullable;
import java.util.List;

public class RFIDWriterBlock extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public RFIDWriterBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    public void appendHoverText(ItemStack p_190948_1_, @Nullable IBlockReader p_190948_2_, List<ITextComponent> tooltip, ITooltipFlag p_190948_4_) {
        ToolTipUtil.getBlockTooltip("rfid_writer", tooltip);
    }

    /*@Override
    public String getDescriptionId() {
        return TextFormatting.YELLOW + new TranslationTextComponent("block.ioreborn.rfid_writer").getString();
    }*/

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        VoxelShape shape = VoxelShapes.empty();
        if(p_220053_1_.getValue(FACING) == Direction.NORTH) {
            shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 0.5, 0.75, 0.25, 0.53125, 0.875), IBooleanFunction.OR);
            shape = VoxelShapes.join(shape, VoxelShapes.box(0.3125, 0.5, 0.75, 0.4375, 0.53125, 0.875), IBooleanFunction.OR);
            shape = VoxelShapes.join(shape, VoxelShapes.box(0.5, 0.5, 0.75, 0.625, 0.53125, 0.875), IBooleanFunction.OR);
            shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0, 0, 1, 0.5, 1), IBooleanFunction.OR);
            shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 0.5, 0.125, 0.625, 0.75, 0.625), IBooleanFunction.OR);
        } else if(p_220053_1_.getValue(FACING) == Direction.SOUTH) {
            shape = VoxelShapes.join(shape, VoxelShapes.box(0.5625, 0.5, 0.125, 0.6875, 0.53125, 0.25), IBooleanFunction.OR);
            shape = VoxelShapes.join(shape, VoxelShapes.box(0.375, 0.5, 0.125, 0.5, 0.53125, 0.25), IBooleanFunction.OR);
            shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0, 0, 1, 0.5, 1), IBooleanFunction.OR);
            shape = VoxelShapes.join(shape, VoxelShapes.box(0.375, 0.5, 0.375, 0.875, 0.75, 0.875), IBooleanFunction.OR);
            shape = VoxelShapes.join(shape, VoxelShapes.box(0.75, 0.5, 0.125, 0.875, 0.53125, 0.25), IBooleanFunction.OR);
        } else if(p_220053_1_.getValue(FACING) == Direction.EAST) {
            shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 0.5, 0.3125, 0.25, 0.53125, 0.4375), IBooleanFunction.OR);
            shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 0.5, 0.5, 0.25, 0.53125, 0.625), IBooleanFunction.OR);
            shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0, 0, 1, 0.5, 1), IBooleanFunction.OR);
            shape = VoxelShapes.join(shape, VoxelShapes.box(0.375, 0.5, 0.125, 0.875, 0.75, 0.625), IBooleanFunction.OR);
            shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 0.5, 0.125, 0.25, 0.53125, 0.25), IBooleanFunction.OR);

        } else if(p_220053_1_.getValue(FACING) == Direction.WEST) {
            shape = VoxelShapes.join(shape, VoxelShapes.box(0.75, 0.5, 0.5625, 0.875, 0.53125, 0.6875), IBooleanFunction.OR);
            shape = VoxelShapes.join(shape, VoxelShapes.box(0.75, 0.5, 0.375, 0.875, 0.53125, 0.5), IBooleanFunction.OR);
            shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0, 0, 1, 0.5, 1), IBooleanFunction.OR);
            shape = VoxelShapes.join(shape, VoxelShapes.box(0.125, 0.5, 0.375, 0.625, 0.75, 0.875), IBooleanFunction.OR);
            shape = VoxelShapes.join(shape, VoxelShapes.box(0.75, 0.5, 0.75, 0.875, 0.53125, 0.875), IBooleanFunction.OR);
        }
        return shape;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState p_220074_1_) {
        return false;
    }

    @Override
    public boolean hasDynamicShape() {
        return super.hasDynamicShape();
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
        return Registration.RFID_WRITER_TILEENTITY.get().create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
