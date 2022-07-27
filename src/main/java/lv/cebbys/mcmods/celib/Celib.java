package lv.cebbys.mcmods.celib;

import lv.cebbys.mcmods.celib.mod.component.linkloader.LinkLoader;
import lv.cebbys.mcmods.celib.mod.component.linkloader.LinkType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Celib implements ModInitializer, ClientModInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Celib.class);
    public static final String MODID = "celib";

    @Override
    public void onInitialize() {
        LOGGER.info("Loading Celib and Minecraft common links!");
        LinkLoader.load(LinkType.COMMON);
        LOGGER.info("Celib common links loaded!");
    }

    @Override
    public void onInitializeClient() {
        LOGGER.info("Loading Celib and Minecraft client links!");
        LinkLoader.load(LinkType.CLIENT);
        LOGGER.info("Celib client links loaded!");
    }


}
