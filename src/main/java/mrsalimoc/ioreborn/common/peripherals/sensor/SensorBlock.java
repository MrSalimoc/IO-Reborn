package mrsalimoc.ioreborn.common.peripherals.sensor;

import mrsalimoc.ioreborn.utils.Registration;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class SensorBlock extends Block {


    public SensorBlock(Properties properties) {
        super(properties.noOcclusion().);


    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState p_220074_1_) {
        return false;
    }

    @Override
    public boolean hasDynamicShape() {
        return super.hasDynamicShape();
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return Registration.SENSOR_TILEENTITY.get().create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
