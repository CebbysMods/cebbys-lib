package lv.cebbys.mcmods.celib.dataloader.utils;

import lv.cebbys.mcmods.celib.dataloader.packs.CelibDataPack;
import lv.cebbys.mcmods.celib.utilities.CelibNulls;
import lv.cebbys.mcmods.celib.loggers.CelibLogger;
import lv.cebbys.mcmods.celib.api.registries.CelibRegistries;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackProvider;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class CelibDataPackProvider implements ResourcePackProvider {
    @Override
    public void register(Consumer<ResourcePackProfile> profileAdder, ResourcePackProfile.Factory factory) {
        for (Identifier id : CelibRegistries.SERVER_RESOURCES.getIds()) {
            CelibLogger.info("Registering data pack with id " + id);
            CelibDataPack pack = CelibRegistries.SERVER_RESOURCES.get(id);
            CelibNulls.ifNotNull(pack, (p) -> profileAdder.accept(p.getResourcePack(factory)));
        }
    }
}
