package lv.cebbys.mcmods.celib.bridge;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lv.cebbys.mcmods.celib.mod.bridge.type.BridgeBlockFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BridgeFactories {
    public static final BridgeBlockFactory BLOCK_FACTORY = new BridgeBlockFactory();
}
