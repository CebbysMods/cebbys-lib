package lv.cebbys.mcmods.celib.core.api.function;

@FunctionalInterface
public interface ExceptionalConsumer<T, E extends Exception> {
    void consume(T instance) throws E;
}
