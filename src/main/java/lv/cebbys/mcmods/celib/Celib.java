package lv.cebbys.mcmods.celib;

import lv.cebbys.mcmods.celib.directories.CelibDirectories;
import lv.cebbys.mcmods.celib.directories.DirectoryHandler;
import lv.cebbys.mcmods.celib.handlers.datapack.DatapackHandler;
import lv.cebbys.mcmods.celib.loggers.CelibLogger;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resource.ResourcePackSource;

public class Celib implements ModInitializer {
	public static final String MODID;

	@Override
	public void onInitialize() {
		CelibLogger.info("Loading Celib - CebbyS Library !");
		if (!CelibDirectories.DATAPACKS.toFile().exists()) {
			CelibLogger.info("Creating datapacks directory in game directory!");
			DirectoryHandler.initDirectory(CelibDirectories.DATAPACKS);
		}
		CelibLogger.info("Celib - CebbyS Library loaded !");

		DatapackHandler.addDatapackProvider(CelibDirectories.DATAPACKS.toFile(),
				ResourcePackSource.nameAndSource("pack.source.celib"));
	}

	static {
		MODID = "celib";
	}
}
