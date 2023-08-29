package lv.cebbys.mcmods.celib.api.registry;

public interface ICelibRegistry<T> {
    T register(String path, T instance);
}
