package lv.cebbys.mcmods.celib;

import lv.cebbys.mcmods.celib.api.registries.CelibRegistries;
import lv.cebbys.mcmods.celib.handlers.directories.CelibDirectories;
import lv.cebbys.mcmods.celib.handlers.directories.DirectoryHandler;
import lv.cebbys.mcmods.celib.loggers.CelibLogger;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

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
        CelibRegistries.registerAssetPack(new Identifier(MODID, "celib_assets"), "Celib Assets", (pack) -> {
            pack.packMeta()
                    .packFormat(7)
                    .description("Temporary celib asset test");
            pack.itemModel(new Identifier(MODID, "test_item_model"), (model) -> {
                model.display().firstPersonHand().rotation(0, 0, 0);
            });
        });
    }
}
