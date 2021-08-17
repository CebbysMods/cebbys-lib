package lv.cebbys.mcmods.celib.handlers.datagen;

import net.minecraft.resource.ResourceType;

public enum DataType {
    DATA(false, true),
    ASSETS(true, false),
    COMMON(true, true);

    private final boolean clientData;
    private final boolean serverData;

    DataType(boolean client, boolean server) {
        this.clientData = client;
        this.serverData = server;
    }

    public boolean belongsTo(ResourceType type) {
        return ResourceType.CLIENT_RESOURCES.equals(type) ? this.clientData : this.serverData;
    }
}
