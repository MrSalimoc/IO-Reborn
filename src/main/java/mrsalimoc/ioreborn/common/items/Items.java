package mrsalimoc.ioreborn.common.items;

import mrsalimoc.ioreborn.IOReborn;
import mrsalimoc.ioreborn.utils.Registration;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class Items {

    public static final RegistryObject<Item> PROXIMITY_CARD = Registration.ITEMS.register("proximity_card", () -> new ProximityCardItem(new Item.Properties().tab(IOReborn.IO_TAB)));

    public static void register() {

    }
}

