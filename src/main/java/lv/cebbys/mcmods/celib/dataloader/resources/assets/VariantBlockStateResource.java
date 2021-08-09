package lv.cebbys.mcmods.celib.dataloader.resources.assets;

import lombok.Builder;
import lombok.Singular;

import java.util.Map;

@Builder
public class VariantBlockStateResource extends BlockStateResource {
    @Singular private final Map<String, VariantResource> variants;
}
