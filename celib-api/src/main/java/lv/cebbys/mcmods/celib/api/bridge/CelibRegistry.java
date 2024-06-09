package lv.cebbys.mcmods.celib.api.bridge;

import lv.cebbys.mcmods.celib.api.component.block.CelibBlock;
import lv.cebbys.mcmods.celib.api.component.identifier.CelibResourceLocation;

public abstract class CelibRegistry<T> {
    public static final Lazy<CelibRegistry<CelibBlock>> BLOCK;

    public static <R, I extends R> I register(Lazy<CelibRegistry<R>> registry, CelibResourceLocation id, I instance) {
        return registry.get().register(id, instance);
    }

    protected abstract <I extends T> I register(CelibResourceLocation id, I instance);


    static {
        BLOCK = new Lazy<>("CelibRegistry.BLOCK");
    }
}
