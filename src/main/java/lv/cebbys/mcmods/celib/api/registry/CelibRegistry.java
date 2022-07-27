package lv.cebbys.mcmods.celib.api.registry;

import lv.cebbys.mcmods.celib.api.component.block.CelibBlock;
import lv.cebbys.mcmods.celib.mod.component.registry.CelibRegistryComponent;
import lv.cebbys.mcmods.celib.mod.structure.collection.Pair;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.world.level.block.Block;

public interface CelibRegistry {
    static CelibRegistry create(String modid) {
        return new CelibRegistryComponent(modid);
    }

    static CelibRegistry create(ModMetadata container) {
        return create(container.getId());
    }

    static CelibRegistry create(ModContainer container) {
        return create(container.getMetadata());
    }

    Pair<CelibBlock, Block> registerBlock(String blockId, CelibBlock instance);
}
