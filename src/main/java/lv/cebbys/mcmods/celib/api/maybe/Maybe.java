package lv.cebbys.mcmods.celib.api.maybe;

import lv.cebbys.mcmods.celib.api.function.ExceptionalBiConsumer;
import lv.cebbys.mcmods.celib.api.function.ExceptionalBiFunction;
import lv.cebbys.mcmods.celib.api.function.ExceptionalConsumer;
import lv.cebbys.mcmods.celib.api.function.ExceptionalFunction;
import lv.cebbys.mcmods.celib.api.function.ExceptionalRunnable;
import lv.cebbys.mcmods.celib.api.function.ExceptionalSupplier;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class Maybe<T> {

    private T instance;

    public Maybe(T i) {
        instance = i;
    }

    public Maybe() {
        this(null);
    }

    public Maybe<T> provideInstance(T i) {
        instance = i;
        return this;
    }

    public Maybe<T> provideSupplier(ExceptionalSupplier<T, ?> supplier) {
        doOr(() -> instance = supplier.get(), () -> instance = null);
        return this;
    }

    public Maybe<T> continueIf(boolean bool) {
        if (!bool) instance = null;
        return this;
    }

    public Maybe<T> continueIf(ExceptionalSupplier<Boolean, ?> supplier) {
        AtomicBoolean bool = new AtomicBoolean(false);
        doOrNothing(() -> bool.set(supplier.get()));
        return continueIf(bool.get());
    }

    public Maybe<T> continueIf(ExceptionalFunction<T, Boolean, ?> function) {
        if (instance == null) return this;
        return continueIf(() -> function.apply(instance));
    }

    public <P> Maybe<T> continueIf(P param, ExceptionalBiFunction<T, P, Boolean, ?> function) {
        if (instance == null) return this;
        return continueIf(() -> function.apply(instance, param));
    }

    public Maybe<T> continueIfNot(boolean bool) {
        return continueIf(!bool);
    }

    public Maybe<T> continueIfNot(ExceptionalFunction<T, Boolean, ?> function) {
        if (instance == null) return this;
        return continueIfNot(() -> function.apply(instance));
    }

    public Maybe<T> continueIfNot(ExceptionalSupplier<Boolean, ?> supplier) {
        return continueIf(() -> !supplier.get());
    }

    public <O> Maybe<O> transform(ExceptionalFunction<T, O, ?> transformer) {
        AtomicReference<O> newInstance = new AtomicReference<>(null);
        doIfExists(() -> {
            newInstance.set(transformer.apply(instance));
        });
        return new Maybe<>(newInstance.get());
    }

    public Maybe<T> consume(ExceptionalConsumer<T, ?> consumer) {
        return doIfExists(() -> consumer.consume(instance));
    }

    public <P> Maybe<T> consume(P param, ExceptionalBiConsumer<T, P, ?> consumer) {
        return doIfExists(() -> consumer.consume(instance, param));
    }

    public T get() {
        return instance;
    }

    private Maybe<T> doIfExists(ExceptionalRunnable<?> runnable) {
        if (instance == null) return this;
        doOrNothing(runnable);
        return this;
    }

    private void doOr(ExceptionalRunnable<?> runnable, ExceptionalRunnable<?> onFail) {
        try {
            runnable.run();
        } catch (Exception e) {
            try {
                onFail.run();
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    private void doOrNothing(ExceptionalRunnable<?> runnable) {
        doOr(runnable, () -> {
        });
    }
}
