package lv.cebbys.mcmods.celib.api.v1.register;

import java.util.function.Supplier;

public interface WriteableRegister<T> {
    Holder<T> register(Identifier id, Supplier<T> supplier);

    default Holder<T> register(Identifier id, T value) {
        return register(id, () -> value);
    }
}
