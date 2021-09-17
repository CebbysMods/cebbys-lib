package lv.cebbys.mcmods.celib.handlers.datagen.providers;

import lv.cebbys.mcmods.celib.api.registries.CelibRegistries;
import lv.cebbys.mcmods.celib.handlers.datagen.packs.AssetPack;
import lv.cebbys.mcmods.celib.handlers.datagen.packs.CelibPack;
import lv.cebbys.mcmods.celib.loggers.CelibLogger;
import lv.cebbys.mcmods.celib.utilities.CelibNulls;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackProvider;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
public class CelibAssetPackProvider implements ResourcePackProvider {
    @Override
    public void register(Consumer<ResourcePackProfile> profileAdder, ResourcePackProfile.Factory factory) {
        for (Identifier id : CelibRegistries.CLIENT_RESOURCES.getIds()) {
            CelibLogger.info("Registering pack with id: " + id);
            CelibPack pack = CelibRegistries.CLIENT_RESOURCES.get(id);
            CelibNulls.ifNotNull(pack, (p) -> profileAdder.accept(p.getResourcePack(factory)));
        }
    }
}
