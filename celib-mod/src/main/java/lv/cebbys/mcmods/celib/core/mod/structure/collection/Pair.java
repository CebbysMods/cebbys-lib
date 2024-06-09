package lv.cebbys.mcmods.celib.core.mod.structure.collection;

public class Pair<A, B> {
    private final A first;
    private final B second;

    public Pair(A f, B s) {
        this.first = f;
        this.second = s;
    }

    public A getFirst() {
        return this.first;
    }

    public B getSecond() {
        return this.second;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\t\"first\": " + this.first + ",\n" +
                "\t\"second\": " + this.second + "\n" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair<?, ?> two = (Pair<?, ?>) o;

        if (!first.equals(two.first)) return false;
        return second.equals(two.second);
    }

    @Override
    public int hashCode() {
        int result = first.hashCode();
        result = 31 * result + second.hashCode();
        return result;
    }
}
