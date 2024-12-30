package lv.cebbys.mcmods.celib.api.v1.register;

public interface Register<T> {
    Holder<T> getHolderById(Identifier id);
    Holder<T> getHolderByIndex(int id);

    default T getValueById(Identifier id) {
        return getHolderById(id).getValue();
    }

    default T getValueByIndex(int id) {
        return getHolderByIndex(id).getValue();
    }
}
