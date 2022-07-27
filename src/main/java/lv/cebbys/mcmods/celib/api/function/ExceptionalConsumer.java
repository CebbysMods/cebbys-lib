package lv.cebbys.mcmods.celib.api.function;

@FunctionalInterface
public interface ExceptionalConsumer<T, E extends Exception> {
    void consume(T instance) throws E;
}
