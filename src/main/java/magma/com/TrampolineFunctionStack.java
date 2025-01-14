package magma.com;

import java.util.function.Function;

sealed interface TrampolineFunctionStack<X, Y> permits TrampolineFunctionStack.Empty, TrampolineFunctionStack.Push {
    public static final record Empty<X, Y>(Function<X, Y> transform) implements TrampolineFunctionStack<X, Y> {
    }

    public static final record Push<X, I, Y>(Function<X, Trampoline<I>> top, TrampolineFunctionStack<I, Y> remainder) implements TrampolineFunctionStack<X, Y> {
    }
}