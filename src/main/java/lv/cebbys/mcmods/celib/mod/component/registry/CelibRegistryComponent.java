package lv.cebbys.mcmods.celib.mod.component.registry;

import lv.cebbys.mcmods.celib.api.component.block.CelibBlock;
import lv.cebbys.mcmods.celib.api.registry.CelibRegistry;
import lv.cebbys.mcmods.celib.mod.component.linkloader.LinkLoader;
import lv.cebbys.mcmods.celib.mod.exception.CelibRegistrationException;
import lv.cebbys.mcmods.celib.mod.structure.collection.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public class CelibRegistryComponent implements CelibRegistry {
    private final String namespace;

    public CelibRegistryComponent(String modid) {
        namespace = modid;
    }

    @Override
    public Pair<CelibBlock, Block> registerBlock(String blockId, CelibBlock instance) {
        Block registered;
        try {
            registered = Registry.register(Registry.BLOCK, location(blockId), LinkLoader.CELIB_BLOCK_LINK.apply(instance));
        } catch (Exception e) {
            throw new CelibRegistrationException("Failed to register CelibBlock", e);
        }
        return new Pair<>(instance, registered);
    }

    private ResourceLocation location(String path) {
        return new ResourceLocation(namespace, path);
    }
}
