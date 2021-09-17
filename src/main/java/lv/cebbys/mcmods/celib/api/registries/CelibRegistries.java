package lv.cebbys.mcmods.celib.api.registries;

import com.mojang.serialization.Lifecycle;
import lv.cebbys.mcmods.celib.Celib;
import lv.cebbys.mcmods.celib.api.builders.CelibAssetPackBuilder;
import lv.cebbys.mcmods.celib.api.builders.CelibDataPackBuilder;
import lv.cebbys.mcmods.celib.handlers.datagen.packs.AssetPack;
import lv.cebbys.mcmods.celib.handlers.datagen.packs.CelibPack;
import lv.cebbys.mcmods.celib.handlers.datagen.packs.DataPack;
import lv.cebbys.mcmods.celib.handlers.datagen.providers.CelibAssetPackProvider;
import lv.cebbys.mcmods.celib.handlers.datagen.providers.CelibDataPackProvider;
import lv.cebbys.mcmods.celib.loggers.CelibLogger;
import net.minecraft.client.resource.ClientBuiltinResourcePackProvider;
import net.minecraft.resource.ResourcePackProvider;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;

import java.util.function.Consumer;

@SuppressWarnings("all")
public class CelibRegistries {
    public static final MutableRegistry<CelibPack> CLIENT_RESOURCES;
    public static final MutableRegistry<CelibPack> SERVER_RESOURCES;
    public static final MutableRegistry<ResourcePackProvider> CLIENT_RESOURCE_PROVIDERS;
    public static final MutableRegistry<ResourcePackProvider> SERVER_RESOURCE_PROVIDERS;

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

    public static void registerAssetProvider(Identifier id, ResourcePackProvider provider) {
        Registry.register(CelibRegistries.CLIENT_RESOURCE_PROVIDERS, id, provider);
    }

    public static void registerDataProvider(Identifier id, ResourcePackProvider provider) {
        Registry.register(CelibRegistries.SERVER_RESOURCE_PROVIDERS, id, provider);
    }

    static {
        CLIENT_RESOURCES = new SimpleRegistry<>(
                RegistryKey.ofRegistry(new Identifier(Celib.MODID, "client_resources")),
                Lifecycle.stable()
        );
        SERVER_RESOURCES = Registry.register((Registry) Registry.REGISTRIES,
                new Identifier(Celib.MODID, "common_resources"),
                new SimpleRegistry<>(
                        RegistryKey.ofRegistry(new Identifier(Celib.MODID, "server_resources")),
                        Lifecycle.stable()
                )
        );
        CLIENT_RESOURCE_PROVIDERS = new SimpleRegistry<>(
                RegistryKey.ofRegistry(new Identifier(Celib.MODID, "client_resource_providers")),
                Lifecycle.stable()
        );
        SERVER_RESOURCE_PROVIDERS = Registry.register((Registry) Registry.REGISTRIES,
                new Identifier(Celib.MODID, "common_resource_providers"),
                new SimpleRegistry<>(
                        RegistryKey.ofRegistry(new Identifier(Celib.MODID, "server_resource_providers")),
                        Lifecycle.stable()
                )
        );
        CelibLogger.info("Registering Celib Asset Provider");
        CelibRegistries.registerAssetProvider(
                new Identifier(Celib.MODID, "celib_asset_provider"),
                new CelibAssetPackProvider()
        );
        CelibLogger.info("Registering Celib Data Provider");
        CelibRegistries.registerDataProvider(
                new Identifier(Celib.MODID, "celib_data_provider"),
                new CelibDataPackProvider()
        );
    }
}
