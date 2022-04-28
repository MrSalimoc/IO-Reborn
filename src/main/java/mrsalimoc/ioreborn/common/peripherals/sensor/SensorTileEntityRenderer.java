package mrsalimoc.ioreborn.common.peripherals.sensor;


import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;

public class SensorTileEntityRenderer extends TileEntityRenderer<SensorTileEntity> {

    public static final ResourceLocation ATLAS_REGION = new ResourceLocation("block/energy_meter");
    public static final ResourceLocation TEXTURE = new ResourceLocation("block/energy_meter");
    private RenderMaterial renderMaterial;
    private final SensorModel model;


    public SensorTileEntityRenderer(TileEntityRendererDispatcher p_i226006_1_) {
        super(p_i226006_1_);
        this.model = new SensorModel();
        renderMaterial = new RenderMaterial(ATLAS_REGION, TEXTURE);
    }

    @Override
    public void render(SensorTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        /*matrixStack.pushPose();
        IVertexBuilder builder = renderMaterial.buffer(buffer, RenderType::entityCutout);
        IOPeripheral.LOGGER.log(Level.DEBUG, "fils de pute");
        model.renderToBuffer(matrixStack, builder, combinedLight, combinedOverlay, 0, 0, 0, 0);
        matrixStack.popPose();*/
    }
}
