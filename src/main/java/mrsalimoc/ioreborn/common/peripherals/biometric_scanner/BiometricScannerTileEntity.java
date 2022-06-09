package mrsalimoc.ioreborn.common.peripherals.biometric_scanner;

import dan200.computercraft.api.peripheral.IPeripheral;
import mrsalimoc.ioreborn.IOReborn;
import mrsalimoc.ioreborn.common.peripherals.mag_card_reader.MagCardReaderBlock;
import mrsalimoc.ioreborn.common.peripherals.rfid_reader.RFIDReaderPeripheral;
import mrsalimoc.ioreborn.utils.Registration;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;

import static dan200.computercraft.shared.Capabilities.CAPABILITY_PERIPHERAL;

public class BiometricScannerTileEntity extends TileEntity implements ITickableTileEntity {
    public BiometricScannerTileEntity() {
        super(Registration.BIOMETRIC_SCANNER_TILEENTITY.get());
    }

    protected BiometricScannerPeripheral peripheral = new BiometricScannerPeripheral(this);
    private LazyOptional<IPeripheral> peripheralCap;
    public static final int STATE_OFF = 0;
    public static final int STATE_SCANNING = 1;
    public static final int STATE_SUCCESS = 2;
    public static final int STATE_ERROR = 3;
    private int scanAnimationCounter = 0;
    public boolean scanning = false;

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
    public CompoundNBT getUpdateTag() {
        return this.save(new CompoundNBT());
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.worldPosition, -1, this.save(new CompoundNBT()));
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        this.setPosition(pkt.getPos());
        this.load(this.getBlockState(), pkt.getTag());
    }

    @Override
    public void tick() {
        while(scanning && scanAnimationCounter <= 5000) {
            scanAnimationCounter++;
            IOReborn.LOGGER.log(Level.DEBUG, "counter on: " + scanAnimationCounter);
            if(scanAnimationCounter == 5000) {
                getScanResult();
                scanAnimationCounter = 0;
                IOReborn.LOGGER.log(Level.DEBUG, "counter off: 0" + scanAnimationCounter);
            }
        }
    }

    public void scan() {
        this.scanning = true;
        this.level.setBlockAndUpdate(worldPosition, this.getBlockState().setValue(BiometricScannerBlock.STATE, STATE_SCANNING));
    }

    public void getScanResult() {
        this.scanning = false;
        this.level.setBlockAndUpdate(worldPosition, this.getBlockState().setValue(BiometricScannerBlock.STATE, STATE_SUCCESS));
    }
}

