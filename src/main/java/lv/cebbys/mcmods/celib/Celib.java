package lv.cebbys.mcmods.celib;

import lv.cebbys.mcmods.celib.handlers.directories.CelibDirectories;
import lv.cebbys.mcmods.celib.handlers.directories.DirectoryHandler;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Celib implements ModInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Celib.class);
    public static final String MODID = "celib";

    @Override
    public void onInitialize() {
        LOGGER.info("Loading Celib - CebbyS Library !");
        if (!CelibDirectories.DATAPACKS.toFile().exists()) {
            LOGGER.info("Creating datapacks directory in game directory!");
            DirectoryHandler.initDirectory(CelibDirectories.DATAPACKS);
        }
        LOGGER.info("Celib - CebbyS Library loaded !");
    }
}
