package lv.cebbys.mcmods.celib.handlers.datagen.resources.common;

import lv.cebbys.mcmods.celib.handlers.datagen.resources.helper.ImageResource;
import lv.cebbys.mcmods.celib.handlers.datagen.DataType;
import net.minecraft.util.Identifier;

public class PackIcon extends ImageResource<PackIcon> {

    private Identifier path;

    public void path(Identifier p) {
        this.path = p;
    }

    @Override
    protected Identifier resourcePath() {
        return this.path;
    }

    @Override
    protected Class<PackIcon> builderClass() {
        return PackIcon.class;
    }

    @Override
    public DataType dataType() {
        return DataType.COMMON;
    }
}
