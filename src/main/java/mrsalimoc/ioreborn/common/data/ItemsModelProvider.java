package mrsalimoc.ioreborn.common.data;

import mrsalimoc.ioreborn.IOReborn;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemsModelProvider extends ItemModelProvider {

    public ItemsModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, IOReborn.MOD_ID, existingFileHelper);
    }


    @Override
    protected void registerModels() {
        //withExistingParent("sensor", modLoc("block/sensor"));
        //withExistingParent("cryptographic_accelerator", modLoc("block/cryptographic_accelerator"));

        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        builder(itemGenerated, "proximity_card");
    }

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name).texture("layer1", "item/tier3");
    }
}
