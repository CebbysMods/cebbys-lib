package lv.cebbys.mcmods.celib.api.builders;

import lv.cebbys.mcmods.celib.handlers.datagen.CelibPackBuilder;
import lv.cebbys.mcmods.celib.handlers.datagen.packs.AssetPack;
import lv.cebbys.mcmods.celib.handlers.datagen.resources.asset.BlockState;
import lv.cebbys.mcmods.celib.handlers.datagen.resources.asset.ItemModel;
import lv.cebbys.mcmods.celib.handlers.datagen.resources.asset.MultipartBlockState;
import lv.cebbys.mcmods.celib.handlers.datagen.resources.asset.VariantBlockState;
import lv.cebbys.mcmods.celib.handlers.datagen.IdentifierUtils;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Consumer;

public class CelibAssetPackBuilder extends CelibPackBuilder {

    public CelibAssetPackBuilder(Identifier id, String name) {
        super(id, name);
    }

    public void multipartBlockState(Identifier id, Consumer<MultipartBlockState> factory) {
        MultipartBlockState state = new MultipartBlockState();
        factory.accept(state);
        this.addBlockState(id, state);
    }

    public void multipartBlockState(Block id, Consumer<MultipartBlockState> factory) {
        this.multipartBlockState(Registry.BLOCK.getId(id), factory);
    }

    public void variantBlockState(Identifier id, Consumer<VariantBlockState> factory) {
        VariantBlockState state = new VariantBlockState();
        factory.accept(state);
        this.addBlockState(id, state);
    }

    public void variantBlockState(Block id, Consumer<VariantBlockState> factory) {
        this.variantBlockState(Registry.BLOCK.getId(id), factory);
    }

    public void itemModel(Identifier id, Consumer<ItemModel> factory) {
        ItemModel model = new ItemModel();
        factory.accept(model);
        this.addItemModel(id, model);
    }

    public AssetPack toAssetPack() {
        return new AssetPack(this.name, this.meta, this.icon, this.resources);
    }

    private void addBlockState(Identifier id, BlockState<?> state) {
        this.resource(IdentifierUtils.wrap("blockstates/", id, ".json"), state);
    }

    private void addItemModel(Identifier id, ItemModel state) {
        this.resource(IdentifierUtils.wrap("models/item/", id, ".json"), state);
    }
}
