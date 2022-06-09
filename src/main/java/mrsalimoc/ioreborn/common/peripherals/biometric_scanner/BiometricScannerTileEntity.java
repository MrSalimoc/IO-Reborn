package mrsalimoc.ioreborn.common.peripherals.biometric_scanner;

import dan200.computercraft.api.peripheral.IPeripheral;
import mrsalimoc.ioreborn.IOReborn;
import mrsalimoc.ioreborn.common.peripherals.mag_card_reader.MagCardReaderBlock;
import mrsalimoc.ioreborn.common.peripherals.rfid_reader.RFIDReaderPeripheral;
import mrsalimoc.ioreborn.utils.Registration;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
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
    private String dataBuffer = "";
    public boolean isValidFistPrint = false;
    public boolean scanning = false;

    public int scanCurrentTime;
    public final int maxScanTime = 10;

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
        boolean dirty = false;
        if(!this.level.isClientSide) {
            if(scanning) {
                if(this.scanCurrentTime < this.maxScanTime) {
                    this.level.setBlockAndUpdate(worldPosition, this.getBlockState().setValue(BiometricScannerBlock.STATE, STATE_SCANNING));
                    this.scanCurrentTime++;
                    dirty = true;
                } else {
                    if(this.scanCurrentTime < 20) {
                        this.scanCurrentTime++;
                        if (isValidFistPrint) {
                            this.level.setBlockAndUpdate(worldPosition, this.getBlockState().setValue(BiometricScannerBlock.STATE, STATE_SUCCESS));
                        } else {
                            this.level.setBlockAndUpdate(worldPosition, this.getBlockState().setValue(BiometricScannerBlock.STATE, STATE_ERROR));
                        }
                        if(this.scanCurrentTime == 15 && isValidFistPrint) {
                            this.peripheral.connectedComputers.forEach((c) -> c.queueEvent("biometric_result", dataBuffer));
                        }
                    } else {
                        this.level.setBlockAndUpdate(worldPosition, this.getBlockState().setValue(BiometricScannerBlock.STATE, STATE_OFF));
                        this.scanning = false;
                        this.scanCurrentTime = 0;
                    }
                    dirty = true;
                }
            }
        }

        if(dirty) {
            this.level.sendBlockUpdated(worldPosition, this.getBlockState(), this.getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
        }

        //while(scanning && scanAnimationCounter <= 9000) {
        //    scanAnimationCounter++;
        //    if(scanAnimationCounter == 9000) {
        //        getScanResult();
        //        scanAnimationCounter = 0;
        //    }
        //}

    }

    public void scan(PlayerEntity p_225533_4_, Hand p_225533_5_) {
        this.scanning = true;
        if(p_225533_4_.getItemInHand(p_225533_5_) != ItemStack.EMPTY) {
            isValidFistPrint = false;
        } else {
            dataBuffer = p_225533_4_.getDisplayName().getString();
            isValidFistPrint = true;
        }
    }

    public void getScanResult() {
        //this.scanning = false;
        //if(isValidFistPrint) {
        //    this.level.setBlockAndUpdate(worldPosition, this.getBlockState().setValue(BiometricScannerBlock.STATE, STATE_SUCCESS));
        //
        //    this.dataBuffer = "";
        //} else {
        //    this.level.setBlockAndUpdate(worldPosition, this.getBlockState().setValue(BiometricScannerBlock.STATE, STATE_ERROR));
        //}

    }
}

