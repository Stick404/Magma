package magma.com.TL.Core;

import java.util.function.Function;
import java.util.function.Supplier;

public sealed interface Trampoline<T> permits Trampoline.Flatten, Trampoline.Pure, Trampoline.Map {
    static Pure of(Object object) {
        return new Pure<>(object);
    }
    default Trampoline<T> defer(Supplier<Trampoline> sup){
        return new Flatten(new Map(new Pure(null), _ -> sup.get()));
    }
    default Trampoline<T> map(Function function){
        return new Map<>(this,function);
    }
    default Trampoline<T> flatMap(Function function) {
        return new Flatten(this.map(function));
    }

    //Pure is the data type for Trampolines
    public static record Pure<T>(T t) implements Trampoline<T> {}
    //Flatten turns a Trampoline<Trampoline<T>> into a Trampoline<T>
    public static record Flatten<T>(Trampoline<Trampoline<T>> ttt) implements Trampoline<T> {}
    //Map turns Trampoline<S> into Trampoline<T>
    public static record Map<S, T>(Trampoline<S> original, Function<S, T> transform) implements Trampoline<T> {}
}
