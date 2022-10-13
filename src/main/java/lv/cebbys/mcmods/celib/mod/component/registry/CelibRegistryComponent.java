package lv.cebbys.mcmods.celib.mod.component.registry;

import lv.cebbys.mcmods.celib.api.component.block.CelibBlock;
import lv.cebbys.mcmods.celib.api.registry.CelibRegistry;
import lv.cebbys.mcmods.celib.mod.exception.CelibRegistrationException;
import lv.cebbys.mcmods.celib.mod.structure.collection.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import static lv.cebbys.mcmods.celib.bridge.BridgeFactories.BLOCK_FACTORY;
import static net.minecraft.core.Registry.BLOCK;
import static net.minecraft.core.Registry.register;

public class CelibRegistryComponent implements CelibRegistry {
    private final String namespace;

    public CelibRegistryComponent(String modid) {
        namespace = modid;
    }

    @Override
    public Pair<CelibBlock, Block> registerBlock(String blockId, CelibBlock instance) {
        Block registered;
        try {
            registered = register(BLOCK, location(blockId), BLOCK_FACTORY.create(instance));
        } catch (Exception e) {
            throw new CelibRegistrationException("Failed to register CelibBlock", e);
        }
        return new Pair<>(instance, registered);
    }

    private ResourceLocation location(String path) {
        return new ResourceLocation(namespace, path);
    }
}
