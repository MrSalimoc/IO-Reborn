package mrsalimoc.ioreborn.common.peripherals.sensor;

import dan200.computercraft.api.peripheral.IPeripheral;
import mrsalimoc.ioreborn.utils.Registration;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import java.util.List;

import static dan200.computercraft.shared.Capabilities.CAPABILITY_PERIPHERAL;

public class SensorTileEntity extends TileEntity implements ITickableTileEntity {


    public SensorTileEntity() {
        super(Registration.SENSOR_TILEENTITY.get());

    }

    @Override
    public void tick() {

    }

    protected SensorPeripheral peripheral = new SensorPeripheral(this);
    private LazyOptional<IPeripheral> peripheralCap;
    private int maxRange = 20;
    private int scanRange = 20;

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

    public String[] getEntitiesInRange() {
        BlockPos topCorner = this.worldPosition.offset(scanRange, scanRange, scanRange);
        BlockPos bottomCorner = this.worldPosition.offset(-scanRange, -scanRange, -scanRange);
        AxisAlignedBB box = new AxisAlignedBB(topCorner, bottomCorner);
        List<Entity> entities = this.getTileEntity().getLevel().getEntities(null, box);

        Entity[] result = new Entity[entities.size()];
        result = entities.toArray(result);
        String[] facesArray = new String[entities.size()];
        for (int i = 0; i < result.length; i++) {
            facesArray[i] = String.valueOf(entities.get(i).getDisplayName().getString());
        }
        return facesArray;
    }

    public int getMaxRange() {
        return this.maxRange;
    }

    public int getRange() {
        return this.scanRange;
    }

    public boolean setRange(int range) {
        if(range > this.maxRange || range < 0) {
            return false;
        } else {
            return true;
        }
    }
}
