package lv.cebbys.mcmods.celib.bridge.fabric.v1.v18.component.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import lv.cebbys.mcmods.celib.api.identifier.CelibResourceLocation;
import lv.cebbys.mcmods.celib.api.block.CelibBlock;
import lv.cebbys.mcmods.celib.api.registry.CelibRegistry;
import lv.cebbys.mcmods.celib.bridge.fabric.v1.v18.component.block.AbstractCelibBlock;

public final class CelibBlockRegistry extends CelibRegistry<CelibBlock> {

	protected <I extends CelibBlock> I register(CelibResourceLocation id, I instance) {
		Registry.register(
		        Registry.BLOCK,
		        (ResourceLocation) id.as(ResourceLocation::new),
		        new AbstractCelibBlock(instance) {
		        }
		);
		return instance;
	}

}
