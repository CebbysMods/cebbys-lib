package lv.cebbys.mcmods.celib.api.v0.math;

import jakarta.annotation.Nonnull;

import java.util.function.Function;

public interface Vector3<T> {
    @Nonnull
    static <I extends Number, O extends Number> Vector3<O> of(Vector3<I> in, Function<I, O> mapper) {
        return of(
                mapper.apply(in.getX()),
                mapper.apply(in.getY()),
                mapper.apply(in.getZ())
        );
    }

    @Nonnull
    static <N extends Number> Vector3<N> of(N x, N y, N z) {
        return null;
    }

    T getX();

    T getY();

    T getZ();

    T get(CelibAxis axis);

    Vector3<T> add(T value);

    Vector3<T> add(Vector3<T> value);
}
