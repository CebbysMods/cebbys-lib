package lv.cebbys.mcmods.celib.handlers.datagen.resources.common;

import lv.cebbys.mcmods.celib.handlers.datagen.DataType;
import lv.cebbys.mcmods.celib.handlers.datagen.resources.helper.JsonObjectResource;
import lv.cebbys.mcmods.celib.handlers.datagen.ResourceExclude;
import lv.cebbys.mcmods.celib.loggers.CelibLogger;

import java.util.HashMap;
import java.util.Map;

public class PackMeta extends JsonObjectResource<PackMeta> {

    @ResourceExclude
    public static final String PACK_FORMAT = "pack_format";
    @ResourceExclude
    public static final String PACK_DESCRIPTION = "description";

    private Map<String, Object> pack;

    public PackMeta packFormat(Integer f) {
        if(this.pack == null) {
            this.pack = new HashMap<>();
        }
        if(this.pack.containsKey(PACK_FORMAT)) {
            CelibLogger.warn("Overriding existing pack format variable");
        }
        if(f != null) {
            this.pack.put(PACK_FORMAT, f);
        } else {
            CelibLogger.warn("Pack format cant be null");
        }
        return this;
    }

    public PackMeta description(String d) {
        if(this.pack == null) {
            this.pack = new HashMap<>();
        }
        if(this.pack.containsKey(PACK_DESCRIPTION)) {
            CelibLogger.warn("Overriding existing pack description variable");
        }
        if(d != null) {
            this.pack.put(PACK_DESCRIPTION, d);
        } else {
            CelibLogger.warn("Pack description cant be null");
        }
        return this;
    }

    @Override
    public DataType dataType() {
        return DataType.COMMON;
    }

    @Override
    protected Class<PackMeta> builderClass() {
        return PackMeta.class;
    }
}
