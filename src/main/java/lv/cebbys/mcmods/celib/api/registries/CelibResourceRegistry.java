package lv.cebbys.mcmods.celib.api.registries;

import lv.cebbys.mcmods.celib.api.builders.CelibAssetPackBuilder;
import lv.cebbys.mcmods.celib.api.builders.CelibDataPackBuilder;
import lv.cebbys.mcmods.celib.handlers.datagen.packs.AssetPack;
import lv.cebbys.mcmods.celib.handlers.datagen.packs.DataPack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Consumer;

public class CelibResourceRegistry {

    public static AssetPack registerAssetPack(Identifier id, String name, Consumer<CelibAssetPackBuilder> factory) {
        CelibAssetPackBuilder assetFactory = new CelibAssetPackBuilder(id, name);
        factory.accept(assetFactory);
        return registerAssetPack(id, assetFactory.toAssetPack());
    }

    public static DataPack registerDataPack(Identifier id, String name, Consumer<CelibDataPackBuilder> factory) {
        CelibDataPackBuilder dataFactory = new CelibDataPackBuilder(id, name);
        factory.accept(dataFactory);
        return registerDataPack(id, dataFactory.toDataPack());
    }

    public static AssetPack registerAssetPack(Identifier id, AssetPack pack) {
        return Registry.register(CelibRegistries.CLIENT_RESOURCES, id, pack);
    }

    public static DataPack registerDataPack(Identifier id, DataPack pack) {
        return Registry.register(CelibRegistries.SERVER_RESOURCES, id, pack);
    }

}
