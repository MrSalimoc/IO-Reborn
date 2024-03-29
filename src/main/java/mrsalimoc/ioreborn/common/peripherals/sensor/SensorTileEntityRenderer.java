package mrsalimoc.ioreborn.common.peripherals.sensor;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import mrsalimoc.ioreborn.IOReborn;
import mrsalimoc.ioreborn.utils.Registration;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.fml.client.registry.ClientRegistry;


public class SensorTileEntityRenderer extends TileEntityRenderer<SensorTileEntity> {

    public static final ResourceLocation ANTENNA_TEXTURE = new ResourceLocation(IOReborn.MOD_ID, "block/sensor");

    private final SensorModel model;
    private final RenderMaterial modelMaterial;


    public SensorTileEntityRenderer(TileEntityRendererDispatcher p_i226006_1_) {
        super(p_i226006_1_);
        this.model = new SensorModel();
        this.modelMaterial = new RenderMaterial(PlayerContainer.BLOCK_ATLAS, ANTENNA_TEXTURE);
    }

    private void add(IVertexBuilder renderer, MatrixStack stack, float x, float y, float z, float u, float v) {
        renderer.vertex(stack.last().pose(), x, y, z)
                .color(1.0f, 1.0f, 1.0f, 1.0f)
                .uv(u, v)
                .uv2(0, 240)
                .normal(1, 0, 0)
                .endVertex();
    }

    private static float diffFunction(long time, long delta, float scale) {
        long dt = time % (delta * 2);
        if (dt > delta) {
            dt = 2*delta - dt;
        }
        return dt * scale;
    }

    @Override
    public void render(SensorTileEntity tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        //TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(AtlasTexture.LOCATION_BLOCKS).apply(ANTENNA_TEXTURE);
        //IVertexBuilder builder = buffer.getBuffer(RenderType.translucent());
        IVertexBuilder builder = modelMaterial.buffer(buffer, RenderType::entitySolid);

        //Random rnd = new Random(tileEntity.getBlockPos().getX() * 337L + tileEntity.getBlockPos().getY() * 37L + tileEntity.getBlockPos().getZ() * 13L);

        long time = System.currentTimeMillis();
        /*float dx1 = diffFunction(time, 900 + rnd.nextInt(800), 0.00001f + rnd.nextFloat() * 0.0001f);
        float dx2 = diffFunction(time, 900 + rnd.nextInt(800), 0.00001f + rnd.nextFloat() * 0.0001f);
        float dx3 = diffFunction(time, 900 + rnd.nextInt(800), 0.00001f + rnd.nextFloat() * 0.0001f);
        float dx4 = diffFunction(time, 900 + rnd.nextInt(800), 0.00001f + rnd.nextFloat() * 0.0001f);
        float dy1 = diffFunction(time, 900 + rnd.nextInt(800), 0.00001f + rnd.nextFloat() * 0.0001f);
        float dy2 = diffFunction(time, 900 + rnd.nextInt(800), 0.00001f + rnd.nextFloat() * 0.0001f);
        float dy3 = diffFunction(time, 900 + rnd.nextInt(800), 0.00001f + rnd.nextFloat() * 0.0001f);
        float dy4 = diffFunction(time, 900 + rnd.nextInt(800), 0.00001f + rnd.nextFloat() * 0.0001f);*/

        double speed = 1.0d;
        float angle = (time / (int)speed / 3) % 360;
        Quaternion rotation = Vector3f.YP.rotationDegrees(angle);
        float scale = 1.0f; //+ diffFunction(time,900 + rnd.nextInt(800), 0.0001f + rnd.nextFloat() * 0.001f);

        matrixStack.pushPose();
        matrixStack.translate(.5, .5, .5);
        matrixStack.mulPose(rotation);
        matrixStack.scale(scale, scale, scale);


        model.renderToBuffer(matrixStack, builder, combinedLight, combinedOverlay, 1, 1, 1, 1);

        /*add(builder, matrixStack, 0 + dx1, 0 + dy1, .5f, sprite.getU0(), sprite.getV0());
        add(builder, matrixStack, 1 - dx2, 0 + dy2, .5f, sprite.getU1(), sprite.getV0());
        add(builder, matrixStack, 1 - dx3, 1 - dy3, .5f, sprite.getU1(), sprite.getV1());
        add(builder, matrixStack, 0 + dx4, 1 - dy4, .5f, sprite.getU0(), sprite.getV1());

        add(builder, matrixStack, 0 + dx4, 1 - dy4, .5f, sprite.getU0(), sprite.getV1());
        add(builder, matrixStack, 1 - dx3, 1 - dy3, .5f, sprite.getU1(), sprite.getV1());
        add(builder, matrixStack, 1 - dx2, 0 + dy2, .5f, sprite.getU1(), sprite.getV0());
        add(builder, matrixStack, 0 + dx1, 0 + dy1, .5f, sprite.getU0(), sprite.getV0());*/

        matrixStack.popPose();

        //matrixStack.pushPose();

        //matrixStack.translate(0.5, 1.5, 0.5);
        /*ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack stack = new ItemStack(Items.DIAMOND);
        IBakedModel ibakedmodel = itemRenderer.getModel(stack, tileEntity.getLevel(), null);
        itemRenderer.render(stack, ItemCameraTransforms.TransformType.FIXED, true, matrixStack, buffer, combinedLight, combinedOverlay, ibakedmodel);*/



        //matrixStack.translate(-.5, 1, -.5);
        /*BlockRendererDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();
        BlockState state = Blocks.ENDER_CHEST.defaultBlockState();
        blockRenderer.renderBlock(state, matrixStack, buffer, combinedLight, combinedOverlay, EmptyModelData.INSTANCE);*/



        //matrixStack.popPose();
    }

    public static void register() {
        ClientRegistry.bindTileEntityRenderer(Registration.SENSOR_TILEENTITY.get(), SensorTileEntityRenderer::new);
    }
}
