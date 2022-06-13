package mrsalimoc.ioreborn.common.items.rfid_card;

import mrsalimoc.ioreborn.common.peripherals.mag_card_reader.MagCardReaderBlock;
import mrsalimoc.ioreborn.common.peripherals.mag_card_reader.MagCardReaderTileEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class RFIDCardItem extends Item {

    public RFIDCardItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand hand) {
        ItemStack itemStackIn = playerIn.getItemInHand(hand);
        CompoundNBT nbtTagCompound = itemStackIn.getTag();
        if (nbtTagCompound == null) {
            nbtTagCompound = new CompoundNBT();
            itemStackIn.setTag(nbtTagCompound);
        }
        Double rayLength = new Double(100);
        Vector3d playerRotation = playerIn.getViewVector(0);
        Vector3d rayPath = playerRotation.scale(rayLength);
        Vector3d from = playerIn.getEyePosition(0);
        Vector3d to = from.add(rayPath);
        RayTraceContext rayCtx = new RayTraceContext(from, to, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, null);
        BlockRayTraceResult rayHit = worldIn.clip(rayCtx);

        if (rayHit.getType() == RayTraceResult.Type.BLOCK){
            TileEntity te = worldIn.getBlockEntity(rayHit.getBlockPos());
            if(te instanceof MagCardReaderTileEntity) {
                if(rayHit.getDirection() == te.getBlockState().getValue(MagCardReaderBlock.FACING)) {

                    return new ActionResult<ItemStack>(ActionResultType.SUCCESS, itemStackIn);
                }
            }
        }

        //

        return super.use(worldIn, playerIn, hand);
    }

    @Override
    public Rarity getRarity(ItemStack p_77613_1_) {
        return Rarity.UNCOMMON;
    }

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> tooltip, ITooltipFlag p_77624_4_) {
        /*if(p_77624_1_.hasTag()) {
            CompoundNBT nbtTagCompound = p_77624_1_.getTag();
            if(nbtTagCompound != null || nbtTagCompound.contains("data")) {
                tooltip.add(1, ITextComponent.nullToEmpty(TextFormatting.GRAY + nbtTagCompound.getString("data")));
            }
        }*/

    }
}
