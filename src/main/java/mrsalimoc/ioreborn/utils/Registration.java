package mrsalimoc.ioreborn.utils;

import com.google.common.collect.Sets;
import mrsalimoc.ioreborn.IOReborn;
import mrsalimoc.ioreborn.common.blocks.Blocks;
import mrsalimoc.ioreborn.common.items.Items;
import mrsalimoc.ioreborn.common.peripherals.biometric_scanner.BiometricScannerTileEntity;
import mrsalimoc.ioreborn.common.peripherals.cryptographic_accelerator.CryptographicAcceleratorTileEntity;
import mrsalimoc.ioreborn.common.peripherals.energy_meter.EnergyMeterTileEntity;
import mrsalimoc.ioreborn.common.peripherals.mag_card_reader.MagCardReaderTileEntity;
import mrsalimoc.ioreborn.common.peripherals.rfid_reader.RFIDReaderTileEntity;
import mrsalimoc.ioreborn.common.peripherals.rfid_writer.RFIDWriterTileEntity;
import mrsalimoc.ioreborn.common.peripherals.sensor.SensorTileEntity;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, IOReborn.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, IOReborn.MOD_ID);
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, IOReborn.MOD_ID);
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, IOReborn.MOD_ID);

    public static final RegistryObject<TileEntityType<EnergyMeterTileEntity>> ENERGY_METER_TILEENTITY = Registration.TILE_ENTITIES.register("energy_meter", () -> new TileEntityType<>(EnergyMeterTileEntity::new, Sets.newHashSet(Blocks.ENERGY_METER.get()), null));
    public static final RegistryObject<TileEntityType<SensorTileEntity>> SENSOR_TILEENTITY = Registration.TILE_ENTITIES.register("sensor", () -> new TileEntityType<>(SensorTileEntity::new, Sets.newHashSet(Blocks.SENSOR.get()), null));
    public static final RegistryObject<TileEntityType<CryptographicAcceleratorTileEntity>> CRYPTOGRAPHIC_ACCELERATOR_TILEENTITY = Registration.TILE_ENTITIES.register("cryptographic_accelerator", () -> new TileEntityType<>(CryptographicAcceleratorTileEntity::new, Sets.newHashSet(Blocks.CRYPTOGRAPHIC_ACCELERATOR.get()), null));
    public static final RegistryObject<TileEntityType<MagCardReaderTileEntity>> MAG_CARD_READER_TILEENTITY = Registration.TILE_ENTITIES.register("mag_card_reader", () -> new TileEntityType<>(MagCardReaderTileEntity::new, Sets.newHashSet(Blocks.MAG_CARD_READER.get()), null));
    public static final RegistryObject<TileEntityType<RFIDWriterTileEntity>> RFID_WRITER_TILEENTITY = Registration.TILE_ENTITIES.register("rfid_writer", () -> new TileEntityType<>(RFIDWriterTileEntity::new, Sets.newHashSet(Blocks.RFID_WRITER.get()), null));
    public static final RegistryObject<TileEntityType<RFIDReaderTileEntity>> RFID_READER_TILEENTITY = Registration.TILE_ENTITIES.register("rfid_reader", () -> new TileEntityType<>(RFIDReaderTileEntity::new, Sets.newHashSet(Blocks.RFID_READER.get()), null));
    public static final RegistryObject<TileEntityType<BiometricScannerTileEntity>> BIOMETRIC_SCANNER_TILEENTITY = Registration.TILE_ENTITIES.register("biometric_scanner", () -> new TileEntityType<>(BiometricScannerTileEntity::new, Sets.newHashSet(Blocks.BIOMETRIC_SCANNER.get()), null));


    public static void register() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        TILE_ENTITIES.register(modEventBus);
        CONTAINERS.register(modEventBus);
        Items.register();
        Blocks.register();

    }
}
