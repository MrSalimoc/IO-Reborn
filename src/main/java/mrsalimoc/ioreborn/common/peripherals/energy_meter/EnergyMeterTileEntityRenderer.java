package mrsalimoc.ioreborn.common.peripherals.energy_meter;

import com.mojang.blaze3d.matrix.MatrixStack;
import mrsalimoc.ioreborn.utils.Registration;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.DyeColor;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;

@OnlyIn(Dist.CLIENT)
public class EnergyMeterTileEntityRenderer extends TileEntityRenderer<EnergyMeterTileEntity>  {

    public EnergyMeterTileEntityRenderer(TileEntityRendererDispatcher p_i226006_1_) {
        super(p_i226006_1_);
    }

    @Override
    public void render(EnergyMeterTileEntity te, float p_225616_2_, MatrixStack matrixStack, IRenderTypeBuffer p_225616_4_, int p_225616_5_, int p_225616_6_) {
        BlockState blockstate = te.getBlockState();
        matrixStack.pushPose();

        matrixStack.translate(0.5D, 0.5D, 0.5D);
        float f4 = -blockstate.getValue(EnergyMeterBlock.FACING).toYRot();
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(f4));
        matrixStack.translate(0.0D, -0.3125D, -0.5D);


        FontRenderer fontrenderer = this.renderer.getFont();

        float f2 = 0.010416667F;
        matrixStack.translate(0.0D, (double)0.53333334F, (double)1.001F);
        matrixStack.scale(0.010116667F, -0.010116667F, 0.010116667F);
        int i = DyeColor.WHITE.getTextColor();
        double d0 = 0.4D;
        int j = (int)((double)NativeImage.getR(i) * 0.4D);
        int k = (int)((double)NativeImage.getG(i) * 0.4D);
        int l = (int)((double)NativeImage.getB(i) * 0.4D);
        int i1 = NativeImage.combine(0, l, k, j);

        String text = te.getRenderMessage();
        if (text != null) {
            float f3 = (float)(-fontrenderer.width(text) / 2);

            fontrenderer.drawInBatch(text, f3, (float)(1 * 10 - 20), DyeColor.WHITE.getTextColor(), false, matrixStack.last().pose(), p_225616_4_, false, 0, 15728640);
        }
        matrixStack.popPose();
    }

    public static void register() {
        ClientRegistry.bindTileEntityRenderer(Registration.ENERGY_METER_TILEENTITY.get(), EnergyMeterTileEntityRenderer::new);
    }
}
