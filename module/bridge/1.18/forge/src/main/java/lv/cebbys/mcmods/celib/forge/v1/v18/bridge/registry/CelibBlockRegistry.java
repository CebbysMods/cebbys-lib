package lv.cebbys.mcmods.celib.forge.v1.v18.bridge.registry;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lv.cebbys.mcmods.celib.api.block.ICelibBlock;
import lv.cebbys.mcmods.celib.api.registry.ICelibBlockRegistry;
import lv.cebbys.mcmods.celib.forge.v1.v18.bridge.block.CelibBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CelibBlockRegistry implements ICelibBlockRegistry {
    private final DeferredRegister<Block> registry;

    public CelibBlockRegistry(String namespace) {
        this.registry = DeferredRegister.create(ForgeRegistries.BLOCKS, namespace);
    }

    @Override
    public ICelibBlock register(String path, ICelibBlock instance) {
        registry.register(path, () -> new CelibBlock(instance));
        return instance;
    }
}
