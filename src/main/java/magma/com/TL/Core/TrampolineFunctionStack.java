package magma.com.TL.Core;

import java.util.function.Function;

sealed interface TrampolineFunctionStack<X, Y> permits TrampolineFunctionStack.Empty, TrampolineFunctionStack.Push {
    record Empty<X, Y>(Function<X, Y> transform) implements TrampolineFunctionStack<X, Y> {
    }
    record Push<X, I, Y>(Function<X, Trampoline<I>> top, TrampolineFunctionStack<I, Y> remainder) implements TrampolineFunctionStack<X, Y> {
    }
}