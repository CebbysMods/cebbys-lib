package lv.cebbys.mcmods.celib.api.v0.bridge;

import lv.cebbys.mcmods.celib.api.v0.content.block.CelibBlock;
import lv.cebbys.mcmods.celib.api.v0.utility.lazy.Lazy;

import java.util.function.Function;

public class CelibMappers {
    public static final Lazy<Function<Object, CelibBlock.PlacementContext>> BLOCK_PLACEMENT_CONTEXT_MAPPER;

    private static <T> Lazy<T> create(String name) {
        return new Lazy<>("CelibMappers.%s".formatted(name));
    }

    static {
        BLOCK_PLACEMENT_CONTEXT_MAPPER = create("BLOCK_PLACEMENT_CONTEXT_MAPPER");
    }
}
