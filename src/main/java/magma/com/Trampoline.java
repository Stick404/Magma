package magma.com;

import java.util.function.Supplier;

public class Trampoline<T> {
    // gotten from https://codingtechroom.com/tutorial/java-mastering-trampoline-a-deep-dive-into-tail-recursion-in-java
    private final Supplier<Trampoline<T>> next;
    private final T value;

    private Trampoline(Supplier<Trampoline<T>> next, T value) {
        this.next = next;
        this.value = value;
    }

    public static <T> Trampoline<T> done(T value) {
        return new Trampoline<>(null, value);
    }

    public static <T> Trampoline<T> more(Supplier<Trampoline<T>> next) {
        return new Trampoline<>(next, null);
    }

    public T run() {
        Trampoline<T> trampoline = this;
        while (trampoline != null) {
            if (trampoline.value != null) {
                return trampoline.value;
            }
            trampoline = trampoline.next.get();
        }
        throw new IllegalStateException("Empty Trampoline");
    }
}