package lv.cebbys.mcmods.celib.core.api.function;

@FunctionalInterface
public interface ExceptionalSupplier<T, E extends Exception> {
    T get() throws E;
}
