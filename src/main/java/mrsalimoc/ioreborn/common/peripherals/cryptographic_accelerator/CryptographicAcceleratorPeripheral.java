package mrsalimoc.ioreborn.common.peripherals.cryptographic_accelerator;

import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import mrsalimoc.ioreborn.common.peripherals.energymeter.EnergyMeterTileEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CryptographicAcceleratorPeripheral implements IPeripheral {

    List<IComputerAccess> connectedComputers = new ArrayList<>();
    private final CryptographicAcceleratorTileEntity tileEntity;

    public CryptographicAcceleratorPeripheral(CryptographicAcceleratorTileEntity tileEntity) {
        this.tileEntity = tileEntity;
    }

    @Nonnull
    @Override
    public String getType() {
        return "cryptographicAccelerator";
    }

    @Override
    public void attach(@Nonnull IComputerAccess computer) {
        connectedComputers.add(computer);
    }

    @Override
    public void detach(@Nonnull IComputerAccess computer) {
        connectedComputers.remove(computer);
    }

    @Override
    public boolean equals(@Nullable IPeripheral other) {
        return this == other;
    }

    public CryptographicAcceleratorTileEntity getTileEntity() {
        return tileEntity;
    }

    @LuaFunction
    public final String test() {
        return "pong";
    }
}
