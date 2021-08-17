package lv.cebbys.mcmods.celib;

import lv.cebbys.mcmods.celib.handlers.directories.CelibDirectories;
import lv.cebbys.mcmods.celib.handlers.directories.DirectoryHandler;
import lv.cebbys.mcmods.celib.loggers.CelibLogger;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resource.ResourcePackSource;

public class Celib implements ModInitializer {
    public static final String MODID;

    static {
        MODID = "celib";
    }

    @Override
    public void onInitialize() {
        CelibLogger.info("Loading Celib - CebbyS Library !");
        if (!CelibDirectories.DATAPACKS.toFile().exists()) {
            CelibLogger.info("Creating datapacks directory in game directory!");
            DirectoryHandler.initDirectory(CelibDirectories.DATAPACKS);
        }
        CelibLogger.info("Celib - CebbyS Library loaded !");
    }
}
