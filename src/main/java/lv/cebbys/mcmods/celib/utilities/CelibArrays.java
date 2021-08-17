package lv.cebbys.mcmods.celib.utilities;

import java.util.ArrayList;
import java.util.Arrays;

public class CelibArrays {

    public static <T> ArrayList<T> getArrayList(T[] array) {
        ArrayList<T> list = new ArrayList<T>();
        Arrays.stream(array).forEach(element -> {
            list.add(element);
        });
        return list;
    }

    public static <T extends Enum<?>> ArrayList<T> getArrayList(Class<T> e) {
        return getArrayList(e.getEnumConstants());
    }
}
