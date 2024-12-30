package lv.cebbys.mcmods.celib.bridge.fabric.v1.v20.v6.component.block;

import lv.cebbys.mcmods.celib.api.block.CelibBlock;
import lv.cebbys.mcmods.celib.api.block.properties.CelibBlockProperties;
import net.minecraft.world.level.block.Block;

public abstract class AbstractCelibBlock extends Block {

    public AbstractCelibBlock(CelibBlock block) {
        super(getProperties(block.getProperties()));
    }

    private static Properties getProperties(CelibBlockProperties properties) {
        return Properties.of();
    }

}
