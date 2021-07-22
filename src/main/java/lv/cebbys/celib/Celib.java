package lv.cebbys.celib;

import lv.cebbys.celib.directories.CelibDirectories;
import lv.cebbys.celib.directories.DirectoryHandler;
import lv.cebbys.celib.handlers.datapack.DatapackHandler;
import lv.cebbys.celib.loggers.CelibLogger;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resource.ResourcePackSource;

public class Celib implements ModInitializer {
	public static final String MOD_ID;

	@Override
	public void onInitialize() {
		CelibLogger.log("Loading Celib - CebbyS Library !");
		if (!CelibDirectories.DATAPACKS.toFile().exists()) {
			CelibLogger.log("Creating datapacks directory in game directory!");
			DirectoryHandler.initDirectory(CelibDirectories.DATAPACKS);
		}
		CelibLogger.log("Celib - CebbyS Library loaded !");

		DatapackHandler.addDatapackProvider(CelibDirectories.DATAPACKS.toFile(),
				ResourcePackSource.nameAndSource("pack.source.celib"));
	}

	static {
		MOD_ID = "celib";
	}
}
