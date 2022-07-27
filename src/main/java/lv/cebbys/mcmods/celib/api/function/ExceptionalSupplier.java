package lv.cebbys.mcmods.celib.api.function;

@FunctionalInterface
public interface ExceptionalSupplier<T, E extends Exception> {
    T get() throws E;
}
