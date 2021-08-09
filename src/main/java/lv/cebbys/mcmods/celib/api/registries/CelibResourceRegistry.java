package lv.cebbys.mcmods.celib.api.registries;

import lv.cebbys.mcmods.celib.api.factories.CelibAssetPackFactory;
import lv.cebbys.mcmods.celib.api.factories.CelibDataPackFactory;
import lv.cebbys.mcmods.celib.dataloader.packs.CelibAssetPack;
import lv.cebbys.mcmods.celib.dataloader.packs.CelibDataPack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Consumer;

public class CelibResourceRegistry {

    public static CelibAssetPack registerAssetPack(Identifier id, String name, Consumer<CelibAssetPackFactory> factory) {
        CelibAssetPackFactory assetFactory = new CelibAssetPackFactory(id, name);
        factory.accept(assetFactory);
        return registerAssetPack(id, assetFactory.toAssetPack());
    }

    public static CelibDataPack registerDataPack(Identifier id, String name, Consumer<CelibDataPackFactory> factory) {
        CelibDataPackFactory dataFactory = new CelibDataPackFactory(id, name);
        factory.accept(dataFactory);
        return registerDataPack(id, dataFactory.toDataPack());
    }

    public static CelibAssetPack registerAssetPack(Identifier id, CelibAssetPack pack) {
        return Registry.register(CelibRegistries.CLIENT_RESOURCES, id, pack);
    }

    public static CelibDataPack registerDataPack(Identifier id, CelibDataPack pack) {
        return Registry.register(CelibRegistries.SERVER_RESOURCES, id, pack);
    }

}
