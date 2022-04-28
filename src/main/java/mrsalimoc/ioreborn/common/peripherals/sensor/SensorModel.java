package mrsalimoc.ioreborn.common.peripherals.sensor;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;

public class SensorModel extends Model {

    private final ModelRenderer bb_main;
    private final ModelRenderer antenna_right_r1;
    private final ModelRenderer antenna_left_r1;

    //private final ModelRenderer model;

    public SensorModel() {
        super(RenderType::entitySolid);

        texWidth = 128;
        texHeight = 128;

        bb_main = new ModelRenderer(this);
        bb_main.setPos(0.0F, 4.0F, 0.0F);
        bb_main.texOffs(0, 0).addBox(-0.5F, -8.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
        bb_main.texOffs(0, 47).addBox(-3.5F, -4.0F, -0.5F, 7.0F, 6.0F, 1.0F, 0.0F, false);

        antenna_right_r1 = new ModelRenderer(this);
        antenna_right_r1.setPos(0.0F, 8.5F, 0.3465F);
        //bb_main.addChild(antenna_right_r1);
        setRotationAngle(antenna_right_r1, 0.0F, 0.3927F, 0.0F);
        antenna_right_r1.texOffs(48, 47).addBox(-6.9488F, -8.5F, -2.1748F, 4.0F, 6.0F, 1.0F, 0.0F, true);

        antenna_left_r1 = new ModelRenderer(this);
        antenna_left_r1.setPos(0.0F, 8.5F, 0.3465F);
        //bb_main.addChild(antenna_left_r1);
        setRotationAngle(antenna_left_r1, 0.0F, -0.3927F, 0.0F);
        antenna_left_r1.texOffs(32, 47).addBox(2.9488F, -8.5F, -2.1748F, 4.0F, 6.0F, 1.0F, 0.0F, false);

    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        //model.render(matrixStack, buffer, packedLight, packedOverlay);
        bb_main.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        antenna_left_r1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        antenna_right_r1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
