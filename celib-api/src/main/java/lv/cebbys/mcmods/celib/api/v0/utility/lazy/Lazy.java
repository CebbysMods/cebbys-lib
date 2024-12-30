package lv.cebbys.mcmods.celib.api.v0.utility.lazy;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class Lazy<T> {
    private final String name;
    protected T value;

    public T get() {
        if (this.value == null) {
            throw new IllegalStateException(String.format("Value '%s' is not initialized", name));
        }
        return this.value;
    }

    public void set(@Nonnull T value) {
        if (this.value != null) {
            throw new IllegalStateException(String.format("Value '%s' is already initialized", name));
        }
        this.value = value;
    }
}
