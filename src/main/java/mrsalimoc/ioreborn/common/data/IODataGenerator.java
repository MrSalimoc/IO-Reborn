package mrsalimoc.ioreborn.common.data;

import mrsalimoc.ioreborn.IOReborn;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = IOReborn.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class IODataGenerator {
    private IODataGenerator() {

    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        gen.addProvider(new ItemsModelProvider(gen, existingFileHelper));
        gen.addProvider(new BlocksStateProvider(gen, existingFileHelper));
    }
}
