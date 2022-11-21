package lv.cebbys.mcmods.celib.core.mod.structure.collection;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@EqualsAndHashCode
public class Reference<A> implements Collection<A> {
    @Getter
    @Setter
    private A reference;

    public Reference(A i) {
        reference = i;
    }

    public Reference() {
        this(null);
    }

    @Override
    public int size() {
        return reference == null ? 0 : 1;
    }

    @Override
    public boolean isEmpty() {
        return reference == null;
    }

    @Override
    public boolean contains(Object o) {
        if (reference == null) return null == o;
        return reference.equals(o);
    }

    @NotNull
    @Override
    public Iterator<A> iterator() {
        return List.of(reference).iterator();
    }

    @Override
    public @NotNull Object @NotNull [] toArray() {
        if (reference == null) return new Object[0];
        return new Object[]{reference};
    }


    @SuppressWarnings("all")
    @Override
    public <T> @NotNull T @NotNull [] toArray(@NotNull T @NotNull [] a) {
        if (reference == null) return List.of().toArray(a);
        return List.of(reference).toArray(a);
    }

    @Override
    public boolean add(A a) {
        reference = a;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (!contains(o)) return false;
        reference = null;
        return true;
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        if (c.size() > 1) return false;
        if (reference == null) {
            return c.size() == 0;
        } else {
            return c.stream().allMatch(e -> reference.equals(e));
        }
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends A> c) {
        return false;
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return c.stream().anyMatch(e -> {
            if (reference != null && reference.equals(e)) {
                reference = null;
                return true;
            }
            return false;
        });
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        if (reference != null) {
            if (c.stream().noneMatch(e -> reference.equals(e))) {
                reference = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        reference = null;
    }
}
