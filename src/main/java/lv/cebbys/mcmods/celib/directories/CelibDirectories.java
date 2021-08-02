package lv.cebbys.mcmods.celib.directories;

import java.nio.file.Path;

import net.fabricmc.loader.api.FabricLoader;

public class CelibDirectories {

    public static final Path GAME_DIRECTORY;
    public static final Path CONFIG_DIRECTORY;
    public static final Path DATAPACKS;

    static {
        GAME_DIRECTORY      = FabricLoader.getInstance().getGameDir();
        CONFIG_DIRECTORY    = FabricLoader.getInstance().getConfigDir();
        DATAPACKS           = DirectoryHandler.appendToPath(GAME_DIRECTORY, "datapacks");
    }

}
