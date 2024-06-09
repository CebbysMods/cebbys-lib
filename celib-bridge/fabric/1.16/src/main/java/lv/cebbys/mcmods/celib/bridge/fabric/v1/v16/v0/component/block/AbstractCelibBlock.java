package lv.cebbys.mcmods.celib.bridge.fabric.v1.v16.v0.component.block;

import lv.cebbys.mcmods.celib.api.component.block.CelibBlock;
import lv.cebbys.mcmods.celib.api.component.block.properties.CelibBlockProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;

public abstract class AbstractCelibBlock extends Block {
    public AbstractCelibBlock(CelibBlock block) {
        super(getProperties(block.getProperties()));
    }

    private static Properties getProperties(CelibBlockProperties properties) {
        return Properties.of(Material.STONE);
    }
}
