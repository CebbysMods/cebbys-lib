package lv.cebbys.mcmods.celib.api.function;

@FunctionalInterface
public interface ExceptionalBiConsumer<T1, T2, E extends Exception> {
    void consume(T1 p1, T2 p2) throws E;
}
