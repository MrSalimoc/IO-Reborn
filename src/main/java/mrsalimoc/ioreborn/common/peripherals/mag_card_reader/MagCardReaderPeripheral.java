package mrsalimoc.ioreborn.common.peripherals.mag_card_reader;

import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;

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
        if(connectedComputers.size() < 1) {
            getTileEntity().setBlockState(MagCardReaderTileEntity.STATE_IDLE);
        }
        connectedComputers.add(computer);
    }

    @Override
    public void detach(@Nonnull IComputerAccess computer) {
        if(connectedComputers.size() == 0) {
            getTileEntity().setBlockState(MagCardReaderTileEntity.STATE_OFF);
        }
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
    public final void beginWrite(String data) {
        getTileEntity().setBlockState(MagCardReaderTileEntity.STATE_WRITE_WAIT);
        getTileEntity().dataToWrite = data;
    }

    @LuaFunction
    public final void setInsertCardLight(boolean bool) {
        switch(getTileEntity().state) {
            case MagCardReaderTileEntity.STATE_IDLE:
                if(bool) {
                    getTileEntity().setBlockState(MagCardReaderTileEntity.STATE_READ_WAIT);
                }
                break;
            case MagCardReaderTileEntity.STATE_READ_WAIT:
                if(!bool) {
                    getTileEntity().setBlockState(MagCardReaderTileEntity.STATE_IDLE);
                }
                break;
            case MagCardReaderTileEntity.STATE_WRITE:
                if(bool) {
                    getTileEntity().setBlockState(MagCardReaderTileEntity.STATE_WRITE_WAIT);
                }
                break;
            case MagCardReaderTileEntity.STATE_WRITE_WAIT:
                if(!bool) {
                    getTileEntity().setBlockState(MagCardReaderTileEntity.STATE_WRITE);
                }
                break;
        }
    }

    @LuaFunction
    public final void cancelWrite() {
        getTileEntity().setBlockState(MagCardReaderTileEntity.STATE_IDLE);
        getTileEntity().dataToWrite = "none";
    }

    @LuaFunction
    public final boolean isWaiting() {
        if(getTileEntity().state == MagCardReaderTileEntity.STATE_WRITE_WAIT || getTileEntity().state == MagCardReaderTileEntity.STATE_READ_WAIT) {
            return true;
        } else {
            return false;
        }
    }
}
