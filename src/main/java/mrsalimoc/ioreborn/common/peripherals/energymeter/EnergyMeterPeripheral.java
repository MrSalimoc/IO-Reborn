package mrsalimoc.ioreborn.common.peripherals.energymeter;

import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class EnergyMeterPeripheral implements IPeripheral {

    List<IComputerAccess> connectedComputers = new ArrayList<>();
    private final EnergyMeterTileEntity tileEntity;

    public EnergyMeterPeripheral(EnergyMeterTileEntity tileEntity) {
        this.tileEntity = tileEntity;
    }

    @Nonnull
    @Override
    public String getType() {
        return "energyMeter";
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

    public EnergyMeterTileEntity getTileEntity() {
        return tileEntity;
    }


    /*@LuaFunction
    public final int getEnergy() {
        return getTileEntity().getEnergyStored();
    }

    @LuaFunction
    public final String getFacing() {
        return getTileEntity().getFacingDirection().toString();
    }

    @LuaFunction
    public final int getEnergyCapacity() {
        return getTileEntity().getMaxEnergyStored();
    }*/

    @LuaFunction
    public final String getEnergyFaces() {
        return getTileEntity().getEnergyFaces();
    }

    @LuaFunction
    public final String[] getConnectedFaces() {
        return getTileEntity().getConnectedFaces();
    }

    @LuaFunction
    public final boolean isOutputAllowed() {
        return getTileEntity().getAllowOutput();
    }

    @LuaFunction
    public final void setAllowOutput(boolean bool) {
        getTileEntity().setAllowOutput(bool);
    }

    @LuaFunction
    public final void setLabel(String label) {
        getTileEntity().setPeripheralLabel(label);
    }

    @LuaFunction
    public final int getCount() {
        return getTileEntity().getTransferCount();
    }

    @LuaFunction
    public final int getCountAndReset() {
        return getTileEntity().getTransferCountAndReset();
    }

    @LuaFunction
    public final int getTransferRate() {
        return getTileEntity().getTransferRate();
    }

    @LuaFunction
    public final String getLabel() {
        return getTileEntity().getPeripheralLabel();
    }
}
