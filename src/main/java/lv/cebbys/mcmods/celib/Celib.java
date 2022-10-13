package lv.cebbys.mcmods.celib;

import lv.cebbys.mcmods.celib.api.registry.CelibRegistry;
import lv.cebbys.mcmods.celib.test.TestBasicBlock;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Celib implements ModInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Celib.class);
    public static final String MODID = "celib";

    @Override
    public void onInitialize() {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            CelibRegistry registry = CelibRegistry.create(MODID);
            registry.registerBlock("hihi", new TestBasicBlock());
        }
    }
}
