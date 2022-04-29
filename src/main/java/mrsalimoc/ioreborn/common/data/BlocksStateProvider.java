package mrsalimoc.ioreborn.common.data;

import mrsalimoc.ioreborn.IOReborn;
import mrsalimoc.ioreborn.common.blocks.Blocks;
import mrsalimoc.ioreborn.common.peripherals.cryptographic_accelerator.CryptographicAcceleratorBlock;
import mrsalimoc.ioreborn.common.peripherals.energymeter.EnergyMeterBlock;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlocksStateProvider extends BlockStateProvider {

    public BlocksStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, IOReborn.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        registerEnergyMeter((EnergyMeterBlock) Blocks.ENERGY_METER.get());
        //registerCryptographicAccelerator((CryptographicAcceleratorBlock) Blocks.CRYPTOGRAPHIC_ACCELERATOR.get());
        simpleBlock(Blocks.SENSOR.get());
    }

    private void registerEnergyMeter(EnergyMeterBlock block) {
        VariantBlockStateBuilder builder = getVariantBuilder(block);
        BlockModelBuilder model = models().orientable(
                "energy_meter",
                blockTexture(block, "_side"),
                blockTexture(block, "_front"),
                blockTexture(block, "_top")
        );

        for(Direction facing : EnergyMeterBlock.FACING.getPossibleValues()) {
            builder.partialState()
                    .with(EnergyMeterBlock.FACING, facing)
                    .addModels(new ConfiguredModel(model, 0, toYAngle(facing), false));
        }

        simpleBlockItem( block, models().getBuilder("energy_meter") );
    }

    private void registerCryptographicAccelerator(CryptographicAcceleratorBlock block) {
        VariantBlockStateBuilder builder = getVariantBuilder(block);
        BlockModelBuilder model = models().orientable(
                "cryptographic_accelerator",
                blockTexture(block, ""),
                blockTexture(block, ""),
                blockTexture(block, "")
        );

        for(Direction facing : CryptographicAcceleratorBlock.FACING.getPossibleValues()) {
            builder.partialState()
                    .with(CryptographicAcceleratorBlock.FACING, facing)
                    .addModels(new ConfiguredModel(model, 0, toYAngle(facing), false));
        }

        simpleBlockItem( block, models().getBuilder("cryptographic_accelerator") );
    }

    private static ResourceLocation blockTexture(Block block, String suffix )
    {
        ResourceLocation id = block.getRegistryName();
        return new ResourceLocation( id.getNamespace(), "block/" + id.getPath() + suffix );
    }

    private static int toYAngle( Direction direction )
    {
        return ((int) direction.toYRot() + 180) % 360;
    }
}
