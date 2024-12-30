package lv.cebbys.mcmods.celib.api.v0.bridge;

import lv.cebbys.mcmods.celib.api.v0.content.block.CelibBlock;
import lv.cebbys.mcmods.celib.api.v0.registry.CelibRegistry;

public interface CelibBridge {


    CelibRegistry<CelibBlock> getBlockRegistry();
}
