package mrsalimoc.ioreborn.common.peripherals.sensor;

import mrsalimoc.ioreborn.utils.Registration;
import mrsalimoc.ioreborn.utils.TextComponentUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;
import java.util.List;

public class SensorBlock extends Block {


    public SensorBlock(Properties properties) {
        super(properties.noOcclusion());


    }

    @Override
    public void appendHoverText(ItemStack p_190948_1_, @Nullable IBlockReader p_190948_2_, List<ITextComponent> tooltip, ITooltipFlag p_190948_4_) {
        if(Screen.hasControlDown() && Screen.hasShiftDown()) {
            tooltip.add(1, TextComponentUtil.build(TextFormatting.GRAY, new TranslationTextComponent("tooltip.ioreborn.sensor.description")));
        } else if(Screen.hasAltDown()) {
            tooltip.add(1, TextComponentUtil.build(TextFormatting.GRAY, new TranslationTextComponent("tooltip.ioreborn.sensor.methods")));
        } else {
            tooltip.add(1, TextComponentUtil.build(new TranslationTextComponent("tooltip.ioreborn.hint")));
            tooltip.add(2, TextComponentUtil.build(new TranslationTextComponent("tooltip.ioreborn.methods")));
        }
    }

    @Override
    public String getDescriptionId() {
        return TextFormatting.YELLOW + new TranslationTextComponent("block.ioreborn.sensor").getString();
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.join(shape, VoxelShapes.box(0, 0, 0, 1, 0.25, 1), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.375, 0.25, 0.375, 0.625, 0.5, 0.625), IBooleanFunction.OR);
        shape = VoxelShapes.join(shape, VoxelShapes.box(0.0625, 0.5, 0.0625, 0.9375, 0.875, 0.9375), IBooleanFunction.OR);

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
