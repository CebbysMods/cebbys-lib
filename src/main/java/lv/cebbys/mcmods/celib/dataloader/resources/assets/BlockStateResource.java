package lv.cebbys.mcmods.celib.dataloader.resources.assets;

import lombok.Builder;

public class BlockStateResource {
    @Builder
    public record VariantResource(String model, Integer x, Integer y, Integer weight, Boolean uvlock) {
    }
}
