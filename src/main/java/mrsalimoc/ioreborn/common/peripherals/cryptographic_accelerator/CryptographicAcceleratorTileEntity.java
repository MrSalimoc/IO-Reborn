package mrsalimoc.ioreborn.common.peripherals.cryptographic_accelerator;

import dan200.computercraft.api.peripheral.IPeripheral;
import mrsalimoc.ioreborn.utils.Registration;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import static dan200.computercraft.shared.Capabilities.CAPABILITY_PERIPHERAL;

public class CryptographicAcceleratorTileEntity extends TileEntity {
    public CryptographicAcceleratorTileEntity() {
        super(Registration.CRYPTOGRAPHIC_ACCELERATOR_TILEENTITY.get());
    }

    protected CryptographicAcceleratorPeripheral peripheral = new CryptographicAcceleratorPeripheral(this);
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
