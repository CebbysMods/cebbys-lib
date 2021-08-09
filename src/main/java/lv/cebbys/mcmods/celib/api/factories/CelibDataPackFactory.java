package lv.cebbys.mcmods.celib.api.factories;

import lv.cebbys.mcmods.celib.dataloader.factories.CelibResourcePackFactory;
import lv.cebbys.mcmods.celib.dataloader.factories.data.TagFactory;
import lv.cebbys.mcmods.celib.dataloader.packs.CelibDataPack;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class CelibDataPackFactory extends CelibResourcePackFactory {

    public CelibDataPackFactory(Identifier id, String name) {
        super(id, name, ResourceType.SERVER_DATA);
    }

    public void addBlockTag(Identifier id, Consumer<TagFactory> tag) {
        this.addTag(this.prefix("blocks/", id), tag);
    }

    public void addTag(Identifier id, Consumer<TagFactory> tag) {
        this.addResource(this.wrap("tags/", id, ".json"), tag, new TagFactory());
    }

    public CelibDataPack toDataPack() {
        return new CelibDataPack(this.id, this.name, this.namespaces, this.resources);
    }
}
