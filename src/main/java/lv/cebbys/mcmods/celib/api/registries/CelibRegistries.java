package lv.cebbys.mcmods.celib.api.registries;

import com.mojang.serialization.Lifecycle;
import lv.cebbys.mcmods.celib.Celib;
import lv.cebbys.mcmods.celib.handlers.datagen.packs.AssetPack;
import lv.cebbys.mcmods.celib.handlers.datagen.packs.DataPack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;

@SuppressWarnings("all")
public class CelibRegistries {
    public static final MutableRegistry<AssetPack> CLIENT_RESOURCES;
    public static final MutableRegistry<DataPack> SERVER_RESOURCES;

    static {
        CLIENT_RESOURCES = new SimpleRegistry<>(
                RegistryKey.ofRegistry(new Identifier(Celib.MODID, "client_resources")),
                Lifecycle.stable()
        );
        SERVER_RESOURCES = Registry.register((Registry) Registry.REGISTRIES,
                new Identifier(Celib.MODID, "common_data_packs"),
                new SimpleRegistry<>(
                        RegistryKey.ofRegistry(new Identifier(Celib.MODID, "server_resources")),
                        Lifecycle.stable()
                )
        );
    }
}
