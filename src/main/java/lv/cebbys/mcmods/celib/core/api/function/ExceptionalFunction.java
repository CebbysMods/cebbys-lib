package lv.cebbys.mcmods.celib.core.api.function;

import java.util.function.Function;

@FunctionalInterface
public interface ExceptionalFunction<I, O, E extends Exception> {
    O apply(I p) throws E;

    default Function<I, O> toFunction() {
        return (I in) -> {
            try {
                return apply(in);
            } catch (Exception e) {
                return null;
            }
        };
    }
}
