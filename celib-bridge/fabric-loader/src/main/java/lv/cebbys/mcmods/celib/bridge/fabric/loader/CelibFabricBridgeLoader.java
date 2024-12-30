package lv.cebbys.mcmods.celib.bridge.fabric.loader;

import lv.cebbys.mcmods.celib.bridge.CelibBridge;
import lv.cebbys.mcmods.mvl.MinecraftVersionUtility;
import lv.cebbys.mcmods.mvl.api.model.MinecraftVersion;
import net.fabricmc.api.ModInitializer;

import java.util.HashMap;
import java.util.Map;

public class CelibFabricBridgeLoader implements ModInitializer {
    private static final Map<MinecraftVersion, CelibBridge> BRIDGES;

    @Override
    public void onInitialize() {
        var current = MinecraftVersionUtility.getVersion();
        BRIDGES.entrySet().stream().sorted().filter((var entry) -> {
            var version = entry.getKey();
            return version.compareTo(current) >= 0;
        });
    }

    static {
        BRIDGES = new HashMap<>();
    }
}
