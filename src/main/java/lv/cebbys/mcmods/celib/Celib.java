package lv.cebbys.mcmods.celib;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Celib implements ModInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Celib.class);

    @Override
    public void onInitialize() {
        LOGGER.info("Celib - CebbyS Library loaded!");
    }
}
