package mrsalimoc.ioreborn.common.peripherals.energy_meter;


import mrsalimoc.ioreborn.utils.Registration;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class EnergyMeterBlock extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public EnergyMeterBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));

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


    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return Registration.ENERGY_METER_TILEENTITY.get().create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    /*@Override
    public String getID() {
        return null;
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, PlayerEntity playerEntity, World world, BlockState blockState, IProbeHitData iProbeHitData) {
        TileEntity te = world.getBlockEntity(iProbeHitData.getPos());
        if (te instanceof EnergyMeterTileEntity) {
            EnergyMeterTileEntity energyMeterTileEntity = (EnergyMeterTileEntity) te;

            if(iProbeHitData.getSideHit() != energyMeterTileEntity.getFacingDirection()) {
                iProbeInfo.horizontal().text(TextFormatting.RED + "Output " + TextFormatting.GRAY + "side");
            } else {
                iProbeInfo.horizontal().text(TextFormatting.BLUE + "Output " + TextFormatting.GRAY + "side");
            }
            iProbeInfo.horizontal().text(TextFormatting.GRAY + "Label: " + energyMeterTileEntity.getPeripheralLabel());
            if(energyMeterTileEntity.getAllowOutput() == true) {
                iProbeInfo.horizontal().text(TextFormatting.GRAY + "Output allowed: " + TextFormatting.GREEN + "Yes");
            } else {
                iProbeInfo.horizontal().text(TextFormatting.GRAY + "Output allowed: " + TextFormatting.RED + "No");
            }
            iProbeInfo.horizontal().text(TextFormatting.GRAY + "Transfer Rate: " + TextFormatting.WHITE + energyMeterTileEntity.getTransferRate() + " FE/t");
        }
    }*/


}
