package mrsalimoc.ioreborn.client;

import mrsalimoc.ioreborn.IOReborn;
import mrsalimoc.ioreborn.common.blocks.Blocks;
import mrsalimoc.ioreborn.common.peripherals.energy_meter.EnergyMeterTileEntityRenderer;
import mrsalimoc.ioreborn.common.peripherals.sensor.SensorTileEntityRenderer;
import mrsalimoc.ioreborn.utils.Registration;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ForgeRenderTypes;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static mrsalimoc.ioreborn.common.peripherals.sensor.SensorTileEntityRenderer.ANTENNA_TEXTURE;

@Mod.EventBusSubscriber(modid = IOReborn.MOD_ID, value= Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(final FMLClientSetupEvent event) {
        SensorTileEntityRenderer.register();
        EnergyMeterTileEntityRenderer.register();
        RenderTypeLookup.setRenderLayer(Blocks.RFID_WRITER.get(), RenderType.translucent());
    }

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
        if(!event.getMap().location().equals(AtlasTexture.LOCATION_BLOCKS)) {
            return;
        }
        event.addSprite(ANTENNA_TEXTURE);
    }
}
