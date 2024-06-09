package lv.cebbys.mcmods.celib.bridge.fabric.v1.v16.v0.component.registry;

import lv.cebbys.mcmods.celib.api.bridge.CelibRegistry;
import lv.cebbys.mcmods.celib.api.component.block.CelibBlock;
import lv.cebbys.mcmods.celib.api.component.identifier.CelibResourceLocation;
import lv.cebbys.mcmods.celib.bridge.fabric.v1.v16.v0.component.block.AbstractCelibBlock;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

public class CelibBlockRegistry extends CelibRegistry<CelibBlock> {
    @Override
    protected <I extends CelibBlock> I register(CelibResourceLocation id, I instance) {
        Registry.register(Registry.BLOCK, (ResourceLocation) id.as(ResourceLocation::new), new AbstractCelibBlock(instance) {
        });
        return instance;
    }
}
