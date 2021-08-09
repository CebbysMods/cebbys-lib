package lv.cebbys.mcmods.celib.dataloader.utils;

import lv.cebbys.mcmods.celib.dataloader.packs.CelibAssetPack;
import lv.cebbys.mcmods.celib.utilities.CelibNulls;
import lv.cebbys.mcmods.celib.api.registries.CelibRegistries;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackProvider;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class CelibAssetPackProvider implements ResourcePackProvider {
    @Override
    public void register(Consumer<ResourcePackProfile> profileAdder, ResourcePackProfile.Factory factory) {
        for (Identifier id : CelibRegistries.CLIENT_RESOURCES.getIds()) {
            CelibAssetPack pack = CelibRegistries.CLIENT_RESOURCES.get(id);
            CelibNulls.ifNotNull(pack, (p) -> profileAdder.accept(p.getResourcePack(factory)));
        }
    }
}
