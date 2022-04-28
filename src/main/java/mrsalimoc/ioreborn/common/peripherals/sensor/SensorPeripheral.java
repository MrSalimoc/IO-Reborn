package mrsalimoc.ioreborn.common.peripherals.sensor;

import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SensorPeripheral implements IPeripheral {

    List<IComputerAccess> connectedComputers = new ArrayList<>();
    private final SensorTileEntity tileEntity;

    public SensorPeripheral(SensorTileEntity tileEntity) {
        this.tileEntity = tileEntity;
    }

    @Nonnull
    @Override
    public String getType() {
        return "sensor";
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

    public SensorTileEntity getTileEntity() {
        return tileEntity;
    }

    /*@LuaFunction
    public final String[] getEntities() {
        return getTileEntity().getEntitiesInRange();
    }*/

    @LuaFunction
    public final int getMaxRange() {
        return getTileEntity().getMaxRange();
    }

    @LuaFunction
    public final int getRange() {
        return getTileEntity().getRange();
    }

    @LuaFunction
    public final boolean setRange(int range) {
        return getTileEntity().setRange(range);
    }

    @LuaFunction
    public final String[] getTargets() {
        return getTileEntity().getEntitiesInRange();
    }

    @LuaFunction
    public final String getTargetDetails(String key) {
        return "WORK IN PROGRESS LOL";
    }
}
