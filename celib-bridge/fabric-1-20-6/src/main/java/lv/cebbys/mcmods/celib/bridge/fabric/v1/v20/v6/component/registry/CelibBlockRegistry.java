package lv.cebbys.mcmods.celib.bridge.fabric.v1.v20.v6.component.registry;

import net.minecraft.resources.ResourceLocation;
import lv.cebbys.mcmods.celib.api.registry.CelibRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import lv.cebbys.mcmods.celib.bridge.fabric.v1.v20.v6.component.block.AbstractCelibBlock;
import lv.cebbys.mcmods.celib.api.block.CelibBlock;
import lv.cebbys.mcmods.celib.api.identifier.CelibResourceLocation;

public final class CelibBlockRegistry extends CelibRegistry<CelibBlock> {

	protected <I extends CelibBlock> I register(CelibResourceLocation id, I instance) {
		Registry.register(
		        BuiltInRegistries.BLOCK,
		        (ResourceLocation) id.as(ResourceLocation::new),
		        new AbstractCelibBlock(instance) {
		        }
		);
		return instance;
		/*
		// plz give me my moni
		// # na ava zapk
		////
		// plz helpppp\
		///  pls barbora send 5L spirte & kvass
		//gde  sergej????
		/// grivu lai sergejs atved zapiku
		/// CJ izdzer Bacardi
		/// CJ aizved visus majas
		zalupa
				SOS
				///
		// ??ku tu i>??\
		*/


	}

}
