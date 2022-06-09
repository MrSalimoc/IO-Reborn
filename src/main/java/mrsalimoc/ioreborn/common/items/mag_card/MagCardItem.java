package mrsalimoc.ioreborn.common.items.mag_card;

import com.sun.org.apache.bcel.internal.generic.IOR;
import mrsalimoc.ioreborn.IOReborn;
import mrsalimoc.ioreborn.common.peripherals.mag_card_reader.MagCardReaderBlock;
import mrsalimoc.ioreborn.common.peripherals.mag_card_reader.MagCardReaderTileEntity;
import mrsalimoc.ioreborn.utils.TextComponentUtil;
import mrsalimoc.ioreborn.utils.ToolTipUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;
import org.w3c.dom.Text;

import javax.annotation.Nullable;
import java.util.List;

public class MagCardItem extends Item {

    public MagCardItem(Properties properties) {
        super(properties);
    }


    //@Override
    //public ActionResultType useOn(ItemUseContext p_195939_1_) {
    //    //IOReborn.LOGGER.log(Level.DEBUG, p_195939_1_.getClickedPos().toString() + " " + p_195939_1_.getClickedFace().toString());
    //    if(!p_195939_1_.getLevel().isClientSide) {
    //        TileEntity te = p_195939_1_.getLevel().getBlockEntity(p_195939_1_.getClickedPos());
    //        if(te instanceof MagCardReaderTileEntity) {
    //            if(p_195939_1_.getClickedFace() == te.getBlockState().getValue(MagCardReaderBlock.FACING)) {
    //                IOReborn.LOGGER.log(Level.DEBUG,"test");
//
//
    //                ItemStack itemStackIn = p_195939_1_.getPlayer().getItemInHand(p_195939_1_.getHand());
    //                CompoundNBT nbtTagCompound = itemStackIn.getTag();
    //                if(nbtTagCompound == null) {
    //                    nbtTagCompound.putString("test", "caca");
    //                    itemStackIn.setTag(nbtTagCompound);
    //                    ((MagCardReaderTileEntity) te).setBlockState(1);
    //                } else {
    //                    ((MagCardReaderTileEntity) te).setBlockState(2);
    //                }
//
//
    //                return ActionResultType.SUCCESS;
    //            }
    //        }
    //    }
//
    //    return super.useOn(p_195939_1_);
    //}

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
                    if(nbtTagCompound.contains("data")) {
                        if(((MagCardReaderTileEntity) te).state == MagCardReaderTileEntity.STATE_READ_WAIT) {
                            ((MagCardReaderTileEntity) te).readCard(nbtTagCompound.getString("data"));
                            ((MagCardReaderTileEntity) te).setBlockState(MagCardReaderTileEntity.STATE_IDLE);
                        } else if(((MagCardReaderTileEntity) te).state == MagCardReaderTileEntity.STATE_WRITE_WAIT) {
                            nbtTagCompound.putString("data", ((MagCardReaderTileEntity) te).writeCard(nbtTagCompound.getString("data")));
                            ((MagCardReaderTileEntity) te).setBlockState(MagCardReaderTileEntity.STATE_IDLE);
                        }
                    } else {
                        if(((MagCardReaderTileEntity) te).state == MagCardReaderTileEntity.STATE_READ_WAIT) {
                            ((MagCardReaderTileEntity) te).readCard("none");
                            ((MagCardReaderTileEntity) te).setBlockState(MagCardReaderTileEntity.STATE_IDLE);
                        } else if(((MagCardReaderTileEntity) te).state == MagCardReaderTileEntity.STATE_WRITE_WAIT) {
                            nbtTagCompound.putString("data", ((MagCardReaderTileEntity) te).writeCard(nbtTagCompound.getString("data")));
                            ((MagCardReaderTileEntity) te).setBlockState(MagCardReaderTileEntity.STATE_IDLE);
                        }
                    }
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
        if(p_77624_1_.hasTag()) {
            CompoundNBT nbtTagCompound = p_77624_1_.getTag();
            if(nbtTagCompound != null || nbtTagCompound.contains("data")) {
                tooltip.add(1, ITextComponent.nullToEmpty(TextFormatting.GRAY + nbtTagCompound.getString("data")));
            }
        }

        //ToolTipUtil.getItemTooltip("silicon_ball", tooltip);
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        return super.getRGBDurabilityForDisplay(stack);
    }
}
