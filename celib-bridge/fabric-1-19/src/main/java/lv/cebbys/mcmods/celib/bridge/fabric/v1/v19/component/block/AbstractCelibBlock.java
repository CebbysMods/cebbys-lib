package lv.cebbys.mcmods.celib.bridge.fabric.v1.v19.component.block;

import lv.cebbys.mcmods.celib.api.material.CelibMaterial;
import lv.cebbys.mcmods.celib.api.block.properties.CelibBlockProperties;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.block.Block;
import lv.cebbys.mcmods.celib.api.block.CelibBlock;
import net.minecraft.world.level.material.Material;

public abstract class AbstractCelibBlock extends Block {

	public AbstractCelibBlock(CelibBlock block) {
		super(getProperties(block.getProperties()));
	}

	private static Properties getProperties(CelibBlockProperties properties) {
		var material = getMaterial(properties.getMaterial());
		return Properties.of(material);
	}

	private static Material getMaterial(CelibMaterial material) {
		var builder = new Material.Builder(MaterialColor.NONE);
		if (material.isLiquidMatter()) {
		    builder.liquid();
		}
		if (!material.isSolidMatter()) {
		    builder.nonSolid();
		}
		return builder.build();
	}

}
