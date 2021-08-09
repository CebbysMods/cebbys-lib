package lv.cebbys.mcmods.celib.dataloader.factories.assets;

import com.google.gson.JsonElement;
import lv.cebbys.mcmods.celib.dataloader.factories.CelibResourceFactory;
import lv.cebbys.mcmods.celib.dataloader.resources.assets.VariantBlockStateResource.VariantBlockStateResourceBuilder;

public class VariantBlockStateResourceFactory extends CelibResourceFactory<VariantBlockStateResourceBuilder> {
    private final VariantBlockStateResourceBuilder builder = this.createBuilder();
    public VariantBlockStateResourceBuilder variants() {
        return builder;
    }

    @Override
    public Class<VariantBlockStateResourceBuilder> builderClass() {
        return VariantBlockStateResourceBuilder.class;
    }

    public JsonElement toJson() {
        return this.toJson(this.builder.build());
    }
}
