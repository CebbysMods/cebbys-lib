package lv.cebbys.mcmods.celib.bridge.fabric.v1.v20.component.block;

import lv.cebbys.mcmods.celib.api.block.CelibBlock;
import net.minecraft.world.level.block.Block;
import lv.cebbys.mcmods.celib.api.block.properties.CelibBlockProperties;

public abstract class AbstractCelibBlock extends Block {

	public AbstractCelibBlock(CelibBlock block) {
		super(getProperties(block.getProperties()));
	}

	private static Properties getProperties(CelibBlockProperties properties) {
		return Properties.of();
	}

}
