package lv.cebbys.mcmods.celib.core.api.function;

@FunctionalInterface
public interface ExceptionalBiFunction<P1, P2, O, E extends Exception> {
    O apply(P1 p1, P2 p2) throws E;
}
