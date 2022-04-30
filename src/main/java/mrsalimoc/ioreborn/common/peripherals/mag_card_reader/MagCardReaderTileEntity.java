package mrsalimoc.ioreborn.common.peripherals.mag_card_reader;

import dan200.computercraft.api.peripheral.IPeripheral;
import mrsalimoc.ioreborn.common.peripherals.energy_meter.EnergyMeterPeripheral;
import mrsalimoc.ioreborn.utils.Registration;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nullable;

import static dan200.computercraft.shared.Capabilities.CAPABILITY_PERIPHERAL;

public class MagCardReaderTileEntity extends TileEntity implements ITickableTileEntity {



    protected MagCardReaderPeripheral peripheral = new MagCardReaderPeripheral(this);
    private LazyOptional<IPeripheral> peripheralCap;
    public static final int STATE_OFF = 0; // no computer connected
    public static final int STATE_IDLE = 1; // computer connected
    public static final int STATE_READ_WAIT = 2; // computer connected, not writing, insert card now
    public static final int STATE_WRITE_WAIT = 3; // computer connected, writing, insert card now
    public static final int STATE_WRITE = 4; // computer connected, writing
    public int state = STATE_OFF;

    public MagCardReaderTileEntity() {
        super(Registration.MAG_CARD_READER_TILEENTITY.get());
    }



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

    @Override
    public void tick() {
        /*if(this.level != null) {
            switch (state) {
                case STATE_OFF:
                    this.level.setBlockAndUpdate(worldPosition, this.getBlockState().setValue(MagCardReaderBlock.STATE, STATE_OFF));
                    break;
                case STATE_IDLE:
                    this.level.setBlockAndUpdate(worldPosition, this.getBlockState().setValue(MagCardReaderBlock.STATE, STATE_IDLE));
                    break;
                case STATE_READ_WAIT:
                    this.level.setBlockAndUpdate(worldPosition, this.getBlockState().setValue(MagCardReaderBlock.STATE, STATE_READ_WAIT));
                    break;
                case STATE_WRITE_WAIT:
                    this.level.setBlockAndUpdate(worldPosition, this.getBlockState().setValue(MagCardReaderBlock.STATE, STATE_WRITE_WAIT));
                    break;
                case STATE_WRITE:
                    this.level.setBlockAndUpdate(worldPosition, this.getBlockState().setValue(MagCardReaderBlock.STATE, STATE_WRITE));
                    break;
            }
        }*/
    }


    public void setBlockState(int state) {
        this.level.setBlockAndUpdate(worldPosition, this.getBlockState().setValue(MagCardReaderBlock.STATE, state));
        this.state = state;
    }
}
