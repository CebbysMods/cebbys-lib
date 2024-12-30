package lv.cebbys.mcmods.celib.bridge.fabric.v1.v20.component.registry;

import lv.cebbys.mcmods.celib.api.registry.CelibRegistry;
import lv.cebbys.mcmods.celib.api.identifier.CelibResourceLocation;
import lv.cebbys.mcmods.celib.api.block.CelibBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import lv.cebbys.mcmods.celib.bridge.fabric.v1.v20.component.block.AbstractCelibBlock;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

public final class CelibBlockRegistry extends CelibRegistry<CelibBlock> {

	protected <I extends CelibBlock> I register(CelibResourceLocation id, I instance) {
		Registry.register(
		        BuiltInRegistries.BLOCK,
		        (ResourceLocation) id.as(ResourceLocation::new),
		        new AbstractCelibBlock(instance) {
		        }
		);
		return instance;
	}

}
