package magma.com;

import magma.com.TL.Core.Engine;
import magma.com.TL.Core.TL_OLD.TLEnvironment;
import magma.com.TL.Core.TL_OLD.TLListExpression;
import magma.com.Trampoline.Pure;

import java.io.PrintStream;
import java.math.BigInteger;

import static magma.com.TL.Core.Engine.expressionOf;
public class LambdaTesting {

    public static void init(PrintStream logger){
        Engine engine = new Engine();
        TLEnvironment env = engine.defaultEnvironment();
        TLListExpression args = new TLListExpression();
        args.add(expressionOf(1));
        args.add(expressionOf(2));

        long startTime = System.nanoTime();
        //sumToOld(10000000);
        long endTime = System.nanoTime();
        System.out.println("AAAAAAAAAAAAAAAAAA");

        System.out.println((endTime - startTime)/1000000);
        startTime = System.nanoTime();

        Trampoline<Integer> x = sumTo(10000000);

        endTime = System.nanoTime();
        System.out.println((endTime - startTime)/1000000);

    }

    public static BigInteger factorial(BigInteger n) {
        if (n.intValue() == 0) {
            return BigInteger.valueOf(1);
        } else {
            return n.multiply(factorial(n.subtract(BigInteger.valueOf(1))));
        }
    }
    public static Trampoline<Integer> sumTo(int n) {
        if(n == 0) return new Pure<>(0);
        else return new Trampoline.Flatten<>(
                new Trampoline.Map<>(
                        sumTo(0).defer(() -> sumTo(n -1)),
                        i -> new Pure<>(n + i)
                )
        );
    }

    public static int sumToOld(int n) {
        if(n == 0) return 0;
        else return sumToOld(n - 1) + n;
    }
}
