package mrsalimoc.ioreborn.common.peripherals.energymeter;

import dan200.computercraft.api.peripheral.IPeripheral;
import mrsalimoc.ioreborn.common.IOEnergy;
import mrsalimoc.ioreborn.utils.Registration;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.ArrayList;
import java.util.HashMap;

import static dan200.computercraft.shared.Capabilities.CAPABILITY_PERIPHERAL;

public class EnergyMeterTileEntity extends TileEntity implements ITickableTileEntity {

    private IOEnergy storage;

    public EnergyMeterTileEntity() {
        super(Registration.ENERGY_METER_TILEENTITY.get());
        storage = new IOEnergy(0, 1000000, 1000000);
    }

    protected EnergyMeterPeripheral peripheral = new EnergyMeterPeripheral(this);
    private LazyOptional<IPeripheral> peripheralCap;
    private LazyOptional<EnergyStorage> energy;
    private int transferRate = 0;
    private ArrayList<Direction> directionArray = new ArrayList<Direction>();
    private boolean allowOutput = true;
    private int energyCount = 0;
    private String peripheralLabel = "Energy Meter";
    private int countBuffer = 0;
    private HashMap<Direction, Float> facesEnergy = new HashMap<Direction, Float>();

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction direction) {
        if (cap == CAPABILITY_PERIPHERAL) {
            if (peripheralCap == null) {
                peripheralCap = LazyOptional.of(() -> peripheral);
            }
            return peripheralCap.cast();
        }
        if (cap == CapabilityEnergy.ENERGY) {
            if (energy == null) {
                energy = LazyOptional.of(() -> storage);
            }
            return energy.cast();
        }

        return super.getCapability(cap, direction);
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        nbt.putString("peripheralLabel", this.peripheralLabel);
        nbt.putBoolean("allowOutput", this.allowOutput);
        nbt.putInt("energyCount", this.energyCount);
        return super.save(nbt);
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        this.peripheralLabel = nbt.getString("peripheralLabel");
        this.allowOutput = nbt.getBoolean("allowOutput");
        this.energyCount = nbt.getInt("energyCount");
        super.load(state, nbt);
    }

    public int getEnergyStored() {
        return storage.getEnergyStored();
    }

    @Override
    public void tick() {
        letEnergyFlow();
    }

    private void letEnergyFlow() {
        final TileEntity inputBlockEntity = this.level.getBlockEntity(this.worldPosition.relative(getFacingDirection()));

        for (final Direction direction: Direction.values()) {
            if (direction == getFacingDirection()) continue;
            final TileEntity outputBlockEntity = this.level.getBlockEntity(this.worldPosition.relative(direction));
            if (outputBlockEntity == null) {
                if (directionArray.contains(direction)) directionArray.remove(directionArray.indexOf(direction));
                if (facesEnergy.containsKey(direction)) facesEnergy.remove(direction);
                //this.transferRate = 0;
                continue;
            }
            outputBlockEntity.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite()).ifPresent(outputStorage -> {
                if(!directionArray.contains(direction)) {
                    directionArray.add(direction);
                }
                if(!facesEnergy.containsKey(direction)) {
                    facesEnergy.put(direction, 0.0f);
                }
                if(inputBlockEntity != null) {
                    inputBlockEntity.getCapability(CapabilityEnergy.ENERGY, direction).ifPresent(inputStorage -> {
                        if(outputStorage.getEnergyStored() < outputStorage.getMaxEnergyStored() && allowOutput == true) {
                            /*int energyToTransfer = outputStorage.getMaxEnergyStored() - outputStorage.getEnergyStored();
                            inputStorage.extractEnergy(energyToTransfer / directionArray.size(), false);
                            outputStorage.receiveEnergy(energyToTransfer / directionArray.size(), false);
                            this.energyCount += energyToTransfer;
                            this.transferRate = energyToTransfer;*/
                            transferEnergy(inputStorage, outputStorage, direction);
                        }
                    });
                }
            });

        }
    }

    private void transferEnergy(IEnergyStorage input, IEnergyStorage output, Direction direction) {
        this.countBuffer = 0;
        float energyBuffer, maxTransfer, outputEnergySpaceLeft, outputEnergyStored, inputEnergyStored;
        outputEnergyStored = output.getEnergyStored();
        inputEnergyStored = input.getEnergyStored();
        maxTransfer = input.getMaxEnergyStored();
        outputEnergySpaceLeft = output.getMaxEnergyStored() - output.getEnergyStored();

        if(outputEnergySpaceLeft <= 0.0f || inputEnergyStored <= 0.0f || allowOutput == false) {
            //Pas de passage
        } else {
            if (inputEnergyStored >= outputEnergySpaceLeft) {
                float energyToTransfer = outputEnergySpaceLeft / directionArray.size();
                input.extractEnergy((int) energyToTransfer, false);
                output.receiveEnergy((int) energyToTransfer, false);
                this.energyCount += energyToTransfer;
                this.facesEnergy.put(direction, energyToTransfer);
                //this.transferRate = energyToTransfer;//A régler pour calculer toutes les faces
            } else {
                float energyToTransfer = inputEnergyStored / directionArray.size();
                input.extractEnergy((int) energyToTransfer, false);
                output.receiveEnergy((int) energyToTransfer, false);
                this.energyCount += energyToTransfer;
                this.facesEnergy.put(direction, energyToTransfer);
                //this.transferRate = energyToTransfer;//A régler pour calculer toutes les faces
            }
            facesEnergy.forEach((directionKey, floatVal) -> {
                this.countBuffer += floatVal;

            });
            this.transferRate = this.countBuffer;
        }
    }

    public Direction getFacingDirection() {
        return this.getBlockState().getValue(EnergyMeterBlock.FACING);
    }

    public int getMaxEnergyStored() {
        return this.storage.getMaxEnergyStored();
    }

    public String[] getConnectedFaces() {
        Direction[] result = new Direction[directionArray.size()];
        result = directionArray.toArray(result);
        String[] facesArray = new String[directionArray.size()];
        for (int i = 0; i < result.length; i++) {
            facesArray[i] = String.valueOf(directionArray.get(i));
        }
        return facesArray;
    }

    public String getEnergyFaces() {
        return facesEnergy.toString();
    }

    public boolean getAllowOutput() {
        return this.allowOutput;
    }

    public void setAllowOutput(boolean bool) {
        this.allowOutput = bool;
    }

    public int getTransferCount() {
        return this.energyCount;
    }

    public int getTransferCountAndReset() {
        int count = this.energyCount;
        this.energyCount = 0;
        return count;

    }

    public int getTransferRate() {
        return this.transferRate;
    }

    public String getPeripheralLabel() {
        return this.peripheralLabel;
    }

    public void setPeripheralLabel(String label) {
        this.peripheralLabel = label;
    }
}
