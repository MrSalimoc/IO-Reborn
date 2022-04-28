package mrsalimoc.ioreborn.common.peripherals.sensor;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;

public class SensorModel extends Model {

    private final ModelRenderer model;

    public SensorModel() {
        super(RenderType::entitySolid);

        texWidth = 48;
        texHeight = 48;

        model = new ModelRenderer(this);
        model.setPos(8.0F, 8, 0);
        model.texOffs(0, 1).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
        model.texOffs(1, 2).addBox(-0.5F, -4.0F, -2.0F, 1.0F, 5.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        model.render(matrixStack, buffer, packedLight, packedOverlay);
    }
}
