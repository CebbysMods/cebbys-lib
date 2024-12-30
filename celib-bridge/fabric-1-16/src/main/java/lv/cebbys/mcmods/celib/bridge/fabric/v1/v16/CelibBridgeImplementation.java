package lv.cebbys.mcmods.celib.bridge.fabric.v1.v16;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lv.cebbys.mcmods.celib.bridge.CelibBridge;
import lv.cebbys.mcmods.celib.api.registry.CelibRegistry;
import lv.cebbys.mcmods.celib.api.block.CelibBlock;
import lv.cebbys.mcmods.celib.bridge.fabric.v1.v16.component.registry.CelibBlockRegistry;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class CelibBridgeImplementation implements CelibBridge {
    public static final CelibBridge INSTANCE = new CelibBridgeImplementation();

    private final CelibRegistry<CelibBlock> blockRegistry = new CelibBlockRegistry();

    @Override
    public CelibRegistry<CelibBlock> getBlockRegistry() {
        return blockRegistry;
    }
}
