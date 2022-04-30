package mrsalimoc.ioreborn.common.peripherals.mag_card_reader;

import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import mrsalimoc.ioreborn.common.peripherals.energy_meter.EnergyMeterTileEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MagCardReaderPeripheral implements IPeripheral {

    List<IComputerAccess> connectedComputers = new ArrayList<>();
    private final MagCardReaderTileEntity tileEntity;

    public MagCardReaderPeripheral(MagCardReaderTileEntity tileEntity) {
        this.tileEntity = tileEntity;
    }

    @Nonnull
    @Override
    public String getType() {
        return "magCardReader";
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

    public MagCardReaderTileEntity getTileEntity() {
        return tileEntity;
    }

    @LuaFunction
    public final void setBlockState(int state) {
        getTileEntity().setBlockState(state);
    }
}
