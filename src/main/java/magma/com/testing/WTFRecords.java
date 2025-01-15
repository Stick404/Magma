package magma.com.testing;

import magma.com.TL.Core.MagmaRoot;

import java.util.function.Function;
import java.util.function.Supplier;

public class WTFRecords {
    // This class/interface is for testing `record` and how they work (like Flatten from the old Trampoline system)

    public sealed interface Bounce<T> permits Bounce.Flatten, Bounce.Map, Bounce.Pure {
        //Pure is the data type for Trampolines
        public static record Pure<T>(T t) implements Bounce<T> {
        }
        //Flatten turns a Trampoline<Trampoline<T>> into a Trampoline<T>
        public static record Flatten<T>(Bounce<Bounce<T>> ttt) implements Bounce<T> {
        }
        //Map turns Trampoline<S> into Trampoline<T>
        public static record Map<S, T>(Bounce<S> original, Function<S, T> transform) implements Bounce<T> {
        }
    }

    //this here, is the most complex ACC I have ever fucking seen. And I have had to learn *so much* Java to understand this
    sealed interface TrampolineFunctionStack<X, Y> permits TrampolineFunctionStack.Empty, TrampolineFunctionStack.Push {

        // this is when the stack is Empty
        record Empty<X, Y>(Function<X, Y> transform) implements TrampolineFunctionStack<X, Y> {
            @Override
            public <A> TrampolineFunctionStack<A, Y> prepend(Function<A, X> transform) {
                return new Empty<>(transform.andThen(this.transform));
            }
        }

        // top is a function of X that outputs I
        // and remainder is the things left on the stack from the Push
        record Push<X, I, Y>(Function<X, Trampoline<I>> top, TrampolineFunctionStack<I, Y> remainder) implements TrampolineFunctionStack<X, Y> {
            @Override
            public <A> TrampolineFunctionStack<A, Y> prepend(Function<A, X> transform) {
                return new Push<>(transform.andThen(this.top()), this.remainder);
            }
        }

        <A> TrampolineFunctionStack<A, Y> prepend(Function<A, X> transform);
    }

    public static Bounce<Integer> defer(Supplier<Bounce<Integer>> sup){
        return new Bounce.Flatten<Integer>(new Bounce.Map(new Bounce.Pure<Integer>(null), _ -> sup.get()));
    }

    public static void init(){
        Bounce<Integer> x = sumTo(5);
        Bounce<Bounce<Integer>> e = stack(x);
        Bounce<Bounce<Bounce<Integer>>> z = stack2(e);
        System.out.println(e);
        //System.out.println(new Bounce.Flatten<Integer>(e));

        System.out.println("PRINTING Z");
        //System.out.println(z);
        //System.out.println(new Bounce.Flatten<Bounce<Integer>>(z));
    }



    //public static Bounce<Integer> sumTo(int n) {
    //    return new Bounce.Pure<Integer>(n);
    //}
    public static Bounce<Integer> sumTo(Integer n) {
        if(n == 0) return new Bounce.Pure<Integer>(0);
        else return new Bounce.Flatten<>(
                new Bounce.Map<>(
                        defer(() -> sumTo(n -1)),
                        i -> new Bounce.Pure(n + i)
                )
        );
    }
    public static Bounce<Bounce<Integer>> stack(Bounce<Integer> e){
        return new Bounce.Pure<>(e);
    }
    public static Bounce<Bounce<Bounce<Integer>>> stack2(Bounce<Bounce<Integer>> e){
        return new Bounce.Pure<>(e);
    }

    public <S,T> T eval(Bounce<S> tr, TrampolineFunctionStack<S,T> acc) throws Throwable {
        switch (tr) { //depending on the type of bounce...
            case Bounce.Pure v -> { //if its pure...
                switch (acc){ //depending on the acc...
                    case TrampolineFunctionStack.Empty<S,T> v1 -> {
                        return v1.transform().apply((S) v.t()); //if the acc is empty, then just return the Value
                    }
                    case TrampolineFunctionStack.Push v1 -> {
                        return (T) eval((Bounce<S>) v1.top().apply((S) v.t()), v1.remainder()); //if the acc is not empty, run again
                    }
                }
            }
            case Bounce.Flatten v -> { //if its flatten...
                switch (acc) { //depending on the acc...
                    case TrampolineFunctionStack.Empty<S,T> v1 -> {
                        throw new Throwable("NOT DONE");
                    }
                    case TrampolineFunctionStack.Push v1 -> {
                        throw new Throwable("NOT DONE");
                    }
                }
            }
            case Bounce.Map v -> { //if its map...
                switch (acc) { //depending on the acc...
                    case TrampolineFunctionStack.Empty<S,T> v1 -> { //evaluate
                        return (T) eval(v.original(), new TrampolineFunctionStack.Empty<>(v1.transform));
                    }
                    case TrampolineFunctionStack.Push v1 -> {
                        return (T) eval(v.original(), new TrampolineFunctionStack.Push<>(v1.top(),v1.remainder()));
                    }
                }
            }
        }
    }

}
