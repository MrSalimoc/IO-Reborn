package mrsalimoc.ioreborn.common.items;

import mrsalimoc.ioreborn.IOReborn;
import mrsalimoc.ioreborn.common.items.mag_card.MagCardItem;
import mrsalimoc.ioreborn.common.items.silicon_ball.SiliconBallItem;
import mrsalimoc.ioreborn.utils.Registration;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class Items {

    public static final RegistryObject<Item> PROXIMITY_CARD = Registration.ITEMS.register("proximity_card", () -> new ProximityCardItem(new Item.Properties().tab(IOReborn.IO_TAB)));
    public static final RegistryObject<Item> SILICON_BALL = Registration.ITEMS.register("silicon_ball", () -> new SiliconBallItem(new Item.Properties().tab(IOReborn.IO_TAB)));
    public static final RegistryObject<Item> MAG_CARD = Registration.ITEMS.register("mag_card", () -> new MagCardItem(new Item.Properties().tab(IOReborn.IO_TAB)));

    public static void register() {

    }
}

