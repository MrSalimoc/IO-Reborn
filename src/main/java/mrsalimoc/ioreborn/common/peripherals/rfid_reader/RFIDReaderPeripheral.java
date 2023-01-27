package mrsalimoc.ioreborn.common.peripherals.rfid_reader;

import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class RFIDReaderPeripheral implements IPeripheral {

    List<IComputerAccess> connectedComputers = new ArrayList<>();
    private final RFIDReaderTileEntity tileEntity;

    public RFIDReaderPeripheral(RFIDReaderTileEntity tileEntity) {
        this.tileEntity = tileEntity;
    }

    @Nonnull
    @Override
    public String getType() {
        return "rfidReader";
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

    public RFIDReaderTileEntity getTileEntity() {
        return tileEntity;
    }

    @LuaFunction
    public final String test() {
        return "rfidreader";
    }
}
