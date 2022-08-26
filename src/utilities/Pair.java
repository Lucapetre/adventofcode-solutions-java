package utilities;

public record Pair<T,V> (T first, V second) {

    @Override
    public String toString() {
        return first.toString() + "," + second.toString();
    }
}
