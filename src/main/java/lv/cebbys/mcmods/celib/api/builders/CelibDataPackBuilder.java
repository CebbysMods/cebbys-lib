package lv.cebbys.mcmods.celib.api.builders;

import lv.cebbys.mcmods.celib.handlers.datagen.CelibPackBuilder;
import lv.cebbys.mcmods.celib.handlers.datagen.packs.DataPack;
import net.minecraft.util.Identifier;

public class CelibDataPackBuilder extends CelibPackBuilder {

    public CelibDataPackBuilder(Identifier id, String name) {
        super(id, name);
    }

    public DataPack toDataPack() {
        return new DataPack(this.name, this.meta, this.icon, this.resources);
    }
}
