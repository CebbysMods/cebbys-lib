package lv.cebbys.mcmods.celib.api.v0.registry;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lv.cebbys.mcmods.celib.api.v0.identifier.CelibResourceLocation;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public final class CelibRegistryEntry<T> {
    private final CelibResourceLocation identifier;
    private final T instance;
}
