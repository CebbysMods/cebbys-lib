package lv.cebbys.mcmods.celib.handlers.datagen.providers;

import lv.cebbys.mcmods.celib.api.registries.CelibRegistries;
import lv.cebbys.mcmods.celib.handlers.datagen.packs.DataPack;
import lv.cebbys.mcmods.celib.utilities.CelibNulls;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackProvider;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class CelibDataPackProvider implements ResourcePackProvider {
    @Override
    public void register(Consumer<ResourcePackProfile> profileAdder, ResourcePackProfile.Factory factory) {
        for (Identifier id : CelibRegistries.SERVER_RESOURCES.getIds()) {
            DataPack pack = CelibRegistries.SERVER_RESOURCES.get(id);
            CelibNulls.ifNotNull(pack, (p) -> profileAdder.accept(p.getResourcePack(factory)));
        }
    }
}
