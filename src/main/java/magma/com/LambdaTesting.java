package magma.com;

import magma.com.TL.Core.Engine;
import magma.com.TL.Core.TL_OLD.TLEnvironment;
import magma.com.TL.Core.TL_OLD.TLListExpression;

import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Spliterators;

import static java.math.BigInteger.valueOf;
import static magma.com.TL.Core.Engine.expressionOf;
public class LambdaTesting {

    public static void init(PrintStream logger){
        Engine engine = new Engine();
        TLEnvironment env = engine.defaultEnvironment();
        TLListExpression args = new TLListExpression();
        args.add(expressionOf(1));
        args.add(expressionOf(2));

        System.out.println("Look Here!");
        BigInteger x;
        long startTime = System.nanoTime();
        //x = factorial(valueOf(100000));
        //logger.println(x);
        long endTime = System.nanoTime();

        System.out.println((endTime - startTime)/1000000);
        System.out.println("Used KB: " + (double) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024);
        startTime = System.nanoTime();

        logger.println("Running!");
        //count(10000).run();
        countOther(10000);
        logger.println("Ran!");

        //x = factorial(valueOf(100000), valueOf(1)).run();

        endTime = System.nanoTime();

        System.out.println("Used KB: " + (double) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024);
        System.out.println((endTime - startTime)/1000000);
        System.out.println("Look Here!");
    }

    public static Trampoline<BigInteger> factorial(BigInteger n, BigInteger accumulator) {
        if (n.intValue() == 0) return Trampoline.done(accumulator);
        return Trampoline.more(() -> factorial(n.subtract(valueOf(1)), n.multiply(accumulator)));
    }

    public static Trampoline<Integer> count(Integer integer){
        if (integer == 0) return Trampoline.done(integer);
        return Trampoline.more(() -> count(integer -1));
    }
    public static Integer countOther(Integer integer){
        if (integer == 0 ) return 0;
        return countOther(integer -1);
    }

    static BigInteger factorial(BigInteger n)
    {
        if (n.intValue() == 0 || n.intValue() == 1) return valueOf(1);
        return factorial(n.subtract(valueOf(1))).multiply(n);
    }
}
