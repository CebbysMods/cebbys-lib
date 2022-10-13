package lv.cebbys.mcmods.celib.mod.bridge.type;

import lv.cebbys.mcmods.celib.api.component.block.CelibBlock;
import net.minecraft.world.level.block.Block;

public class BridgeBlockFactory extends AbstractBridgeFactory<CelibBlock, Block> {
    public BridgeBlockFactory() {
        super("CelibBridgeBlock", CelibBlock.class);
    }
}
