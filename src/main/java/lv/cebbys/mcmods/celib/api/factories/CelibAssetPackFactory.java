package lv.cebbys.mcmods.celib.api.factories;

import lv.cebbys.mcmods.celib.dataloader.factories.CelibResourcePackFactory;
import lv.cebbys.mcmods.celib.dataloader.factories.resource.assets.BlockStateFactory;
import lv.cebbys.mcmods.celib.dataloader.factories.resource.assets.MultipartBlockStateFactory;
import lv.cebbys.mcmods.celib.dataloader.factories.resource.assets.VariantBlockStateFactory;
import lv.cebbys.mcmods.celib.dataloader.packs.CelibAssetPack;
import net.minecraft.block.Block;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Consumer;

public class CelibAssetPackFactory extends CelibResourcePackFactory {

    public CelibAssetPackFactory(Identifier id, String name) {
        super(id, name, ResourceType.CLIENT_RESOURCES);
    }

    public CelibAssetPack toAssetPack() {
        return new CelibAssetPack(this.id, this.name, this.namespaces, this.resources);
    }


    public void addMultipartBlockState(Block block, Consumer<MultipartBlockStateFactory> blockstate) {
        this.addMultipartBlockState(Registry.BLOCK.getId(block), blockstate);
    }
    public void addMultipartBlockState(Identifier id, Consumer<MultipartBlockStateFactory> blockstate) {
        this.addBlockState(id, blockstate, new MultipartBlockStateFactory());
    }

    public void addVariantBlockState(Block block, Consumer<VariantBlockStateFactory> blockstate) {
        this.addVariantBlockState(Registry.BLOCK.getId(block), blockstate);
    }
    public void addVariantBlockState(Identifier id, Consumer<VariantBlockStateFactory> blockstate) {
        this.addBlockState(id, blockstate, new VariantBlockStateFactory());
    }

    private <T extends BlockStateFactory> void addBlockState(Identifier id, Consumer<T> blockstate, T factory) {
        this.addResource(this.wrap("blockstates/", id, ".json"), blockstate, factory);
    }
}
