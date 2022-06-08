package mrsalimoc.ioreborn.common.peripherals.biometric_scanner;

import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import mrsalimoc.ioreborn.common.peripherals.rfid_reader.RFIDReaderTileEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BiometricScannerPeripheral implements IPeripheral {

    List<IComputerAccess> connectedComputers = new ArrayList<>();
    private final BiometricScannerTileEntity tileEntity;

    public BiometricScannerPeripheral(BiometricScannerTileEntity tileEntity) {
        this.tileEntity = tileEntity;
    }

    @Nonnull
    @Override
    public String getType() {
        return "biometricScanner";
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

    public BiometricScannerTileEntity getTileEntity() {
        return tileEntity;
    }

    @LuaFunction
    public final String test() {
        return "biometricscanner";
    }
}
