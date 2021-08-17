package lv.cebbys.mcmods.celib.handlers.datagen;

import lv.cebbys.mcmods.celib.handlers.datagen.resources.common.PackIcon;
import lv.cebbys.mcmods.celib.handlers.datagen.resources.common.PackMeta;
import lv.cebbys.mcmods.celib.handlers.datagen.resources.helper.CelibResource;
import lv.cebbys.mcmods.celib.loggers.CelibLogger;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class CelibPackBuilder {

    protected final String name;
    protected final Identifier id;
    protected PackIcon icon;
    protected PackMeta meta;
    protected final Map<Identifier, CelibResource<?>> resources;

    protected CelibPackBuilder(Identifier packId, String packName) {
        this.id = packId;
        this.name = packName;
        this.resources = new HashMap<>();
    }

    public PackIcon packIcon() {
        this.icon = new PackIcon();
        return this.icon;
    }

    public PackMeta packMeta() {
        this.meta = new PackMeta();
        return this.meta;
    }

    protected void resource(Identifier id, CelibResource<?> resource) {
        if(id == null) {
            CelibLogger.error("Tried to append resource with key null");
        } else {
            if(resource == null) {
                CelibLogger.error("Tried to append null as resource");
            } else {
                this.resources.put(id, resource);
            }
        }
    }
}
