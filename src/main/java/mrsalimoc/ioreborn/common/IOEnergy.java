package mrsalimoc.ioreborn.common;

import net.minecraftforge.energy.EnergyStorage;

public class IOEnergy extends EnergyStorage {

    public IOEnergy(int capacity) {
        super(capacity);
    }

    public IOEnergy(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }

    public IOEnergy(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }

    public IOEnergy(int capacity, int maxReceive, int maxExtract, int energy) {
        super(capacity, maxReceive, maxExtract, energy);
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
