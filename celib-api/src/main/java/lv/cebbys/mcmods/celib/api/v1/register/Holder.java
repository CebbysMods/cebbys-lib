package lv.cebbys.mcmods.celib.api.v1.register;

public interface Holder<T> {
    T getValue();
    int getIndex();
    Identifier getIdentifier();
}
