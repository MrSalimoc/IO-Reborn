package mrsalimoc.ioreborn.common.blocks;

import mrsalimoc.ioreborn.IOReborn;
import mrsalimoc.ioreborn.common.peripherals.biometric_scanner.BiometricScannerBlock;
import mrsalimoc.ioreborn.common.peripherals.cryptographic_accelerator.CryptographicAcceleratorBlock;
import mrsalimoc.ioreborn.common.peripherals.energy_meter.EnergyMeterBlock;
import mrsalimoc.ioreborn.common.peripherals.mag_card_reader.MagCardReaderBlock;
import mrsalimoc.ioreborn.common.peripherals.rfid_reader.RFIDReaderBlock;
import mrsalimoc.ioreborn.common.peripherals.rfid_writer.RFIDWriterBlock;
import mrsalimoc.ioreborn.common.peripherals.sensor.SensorBlock;
import mrsalimoc.ioreborn.utils.Registration;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class Blocks {

    public static final RegistryObject<Block> ENERGY_METER = register("energy_meter", () -> new EnergyMeterBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.0f)));
    public static final RegistryObject<Block> SENSOR = register("sensor", () -> new SensorBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.0f)));
    public static final RegistryObject<Block> CRYPTOGRAPHIC_ACCELERATOR = register("cryptographic_accelerator", () -> new CryptographicAcceleratorBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.0f)));
    public static final RegistryObject<Block> MAG_CARD_READER = register("mag_card_reader", () -> new MagCardReaderBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.0f)));
    public static final RegistryObject<Block> RFID_WRITER = register("rfid_writer", () -> new RFIDWriterBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.0f)));
    public static final RegistryObject<Block> RFID_READER = register("rfid_reader", () -> new RFIDReaderBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.0f)));
    public static final RegistryObject<Block> BIOMETRIC_SCANNER = register("biometric_scanner", () -> new BiometricScannerBlock(AbstractBlock.Properties.of(Material.STONE).strength(1.0f)));


    public static void register() {

    }

    private static <T extends Block>RegistryObject<T> registerNoItem(String name, Supplier<T> block) {
        return Registration.BLOCKS.register(name, block);
    }

    private static <T extends Block>RegistryObject<T> register(String name, Supplier<T> block) {
        RegistryObject<T> ret = registerNoItem(name, block);
        Registration.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties().tab(IOReborn.IO_TAB).rarity(Rarity.UNCOMMON)));
        return ret;
    }
}
