package lv.cebbys.mcmods.celib.utilities;

import java.util.function.Consumer;
import java.util.function.Function;

public class CelibNulls {
    public static <T> void ifNotNull(T object, Consumer<T> function) {
        if (object != null) {
            function.accept(object);
        }
    }

    public static <T> T ifNotNull(T object, T ifNull, Function<T, T> function) {
        if (object != null) {
            return function.apply(object);
        }
        return ifNull;
    }
}
