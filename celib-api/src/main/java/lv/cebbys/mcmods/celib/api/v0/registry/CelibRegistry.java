package lv.cebbys.mcmods.celib.api.v0.registry;

import lv.cebbys.mcmods.celib.api.v0.content.block.CelibBlock;
import lv.cebbys.mcmods.celib.api.v0.identifier.CelibResourceLocation;
import lv.cebbys.mcmods.celib.api.v0.utility.lazy.Lazy;

public abstract class CelibRegistry<T> {
    public static final Lazy<CelibRegistry<CelibBlock>> BLOCK;

    public static <R, I extends R> CelibRegistryEntry<I> register(CelibRegistry<R> instance, CelibResourceLocation id, I resource) {
        return instance.register(id, resource);
    }

    protected abstract <I extends T> CelibRegistryEntry<I> register(CelibResourceLocation id, I instance);

    static {
        BLOCK = new Lazy<>("CelibRegistry.BLOCK");
    }
}
