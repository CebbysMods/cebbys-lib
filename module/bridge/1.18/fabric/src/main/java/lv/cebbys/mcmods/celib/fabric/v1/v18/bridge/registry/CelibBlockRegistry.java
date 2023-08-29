package lv.cebbys.mcmods.celib.fabric.v1.v18.bridge.registry;

import lombok.RequiredArgsConstructor;
import lv.cebbys.mcmods.celib.api.block.ICelibBlock;
import lv.cebbys.mcmods.celib.api.registry.ICelibBlockRegistry;
import lv.cebbys.mcmods.celib.fabric.v1.v18.bridge.block.CelibBlock;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

@RequiredArgsConstructor
public class CelibBlockRegistry implements ICelibBlockRegistry {
    private final String namespace;

    @Override
    public ICelibBlock register(String path, ICelibBlock instance) {
        Registry.register(Registry.BLOCK, new ResourceLocation(namespace, path), new CelibBlock(instance));
        return instance;
    }
}
