package mrsalimoc.ioreborn.client;

import mrsalimoc.ioreborn.IOReborn;
import mrsalimoc.ioreborn.common.blocks.Blocks;
import mrsalimoc.ioreborn.common.items.Items;
import mrsalimoc.ioreborn.common.items.silicon_ball.SiliconBallItem;
import mrsalimoc.ioreborn.common.peripherals.energy_meter.EnergyMeterTileEntityRenderer;
import mrsalimoc.ioreborn.common.peripherals.sensor.SensorTileEntityRenderer;
import mrsalimoc.ioreborn.utils.Registration;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ForgeRenderTypes;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.apache.logging.log4j.Level;

import static mrsalimoc.ioreborn.common.peripherals.sensor.SensorTileEntityRenderer.ANTENNA_TEXTURE;

@Mod.EventBusSubscriber(modid = IOReborn.MOD_ID, value= Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(final FMLClientSetupEvent event) {
        SensorTileEntityRenderer.register();
        EnergyMeterTileEntityRenderer.register();
        RenderTypeLookup.setRenderLayer(Blocks.RFID_WRITER.get(), RenderType.translucent());
        event.enqueueWork(() -> {
            ItemModelsProperties.register(Items.SILICON_BALL.get(), new ResourceLocation(IOReborn.MOD_ID, "used"), (stack, world, entity) -> {
                if (stack.getItem() instanceof SiliconBallItem) {
                    if (stack.hasTag()) {
                        if (stack.getTag().contains("fistprint")) {
                            return 1.0F;
                        } else {
                            return 0.0F;
                        }
                    } else {
                        return 0.0F;
                    }
                } else {
                    return 0.0F;
                }
            });
        });

    }

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
        if(!event.getMap().location().equals(AtlasTexture.LOCATION_BLOCKS)) {
            return;
        }
        event.addSprite(ANTENNA_TEXTURE);
    }
}
