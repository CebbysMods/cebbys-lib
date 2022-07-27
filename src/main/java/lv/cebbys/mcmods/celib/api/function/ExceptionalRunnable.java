package lv.cebbys.mcmods.celib.api.function;

@FunctionalInterface
public interface ExceptionalRunnable<E extends Exception> {
    void run() throws E;
}
