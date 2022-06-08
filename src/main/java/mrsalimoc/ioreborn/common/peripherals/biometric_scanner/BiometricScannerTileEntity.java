package mrsalimoc.ioreborn.common.peripherals.biometric_scanner;

import dan200.computercraft.api.peripheral.IPeripheral;
import mrsalimoc.ioreborn.common.peripherals.rfid_reader.RFIDReaderPeripheral;
import mrsalimoc.ioreborn.utils.Registration;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import static dan200.computercraft.shared.Capabilities.CAPABILITY_PERIPHERAL;

public class BiometricScannerTileEntity extends TileEntity {
    public BiometricScannerTileEntity() {
        super(Registration.BIOMETRIC_SCANNER_TILEENTITY.get());
    }

    protected BiometricScannerPeripheral peripheral = new BiometricScannerPeripheral(this);
    private LazyOptional<IPeripheral> peripheralCap;

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction direction) {
        if (cap == CAPABILITY_PERIPHERAL) {
            if (peripheralCap == null) {
                peripheralCap = LazyOptional.of(() -> peripheral);
            }
            return peripheralCap.cast();
        }

        return super.getCapability(cap, direction);
    }
}

