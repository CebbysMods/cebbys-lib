package lv.cebbys.mcmods.celib.api.bridge;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class Lazy<T> {
    private final String name;
    private T value;

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
