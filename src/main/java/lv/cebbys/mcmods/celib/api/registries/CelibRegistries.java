package lv.cebbys.mcmods.celib.api.registries;

import com.mojang.serialization.Lifecycle;
import lv.cebbys.mcmods.celib.dataloader.packs.CelibAssetPack;
import lv.cebbys.mcmods.celib.dataloader.packs.CelibDataPack;
import lv.cebbys.mcmods.celib.Celib;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;

@SuppressWarnings(value = {"unchecked"})
public class CelibRegistries {
    public static final MutableRegistry<CelibAssetPack> CLIENT_RESOURCES;
    public static final MutableRegistry<CelibDataPack> SERVER_RESOURCES;

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
