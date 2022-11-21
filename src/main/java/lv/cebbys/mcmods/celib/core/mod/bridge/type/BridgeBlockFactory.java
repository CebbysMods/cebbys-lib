package lv.cebbys.mcmods.celib.core.mod.bridge.type;

import lv.cebbys.mcmods.celib.core.api.component.block.CelibBlock;
import net.minecraft.world.level.block.Block;

public class BridgeBlockFactory extends AbstractBridgeFactory<CelibBlock, Block> {
    public BridgeBlockFactory() {
        super("CelibBridgeBlock", CelibBlock.class);
    }
}
