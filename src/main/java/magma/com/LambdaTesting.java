package magma.com;

import magma.com.TL.Core.Engine;
import magma.com.TL.Core.TL_OLD.TLEnvironment;
import magma.com.TL.Core.TL_OLD.TLListExpression;

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
        BigInteger result = factorial(BigInteger.valueOf(10000)); //about top magnitude
        long endTime = System.nanoTime();
        System.out.println((endTime - startTime)/1000000);
        //System.out.println(result);
        startTime = System.nanoTime();
        //BigInteger resultBounce = factorial(BigInteger.valueOf(100000),BigInteger.valueOf(1)).get(); //can be as big as wanted
        endTime = System.nanoTime();
        System.out.println((endTime - startTime)/1000000);
        //System.out.println(resultBounce);

    }

    public static BigInteger factorial(BigInteger n) {
        if (n.intValue() == 0) {
            return BigInteger.valueOf(1);
        } else {
            return n.multiply(factorial(n.subtract(BigInteger.valueOf(1))));
        }
    }
    public static Trampoline<BigInteger> factorial(BigInteger n, BigInteger acc){
        if (n.intValue() == 0) {
            return Trampoline.done(acc);
        } else {
            return Trampoline.more(() -> factorial(n.subtract(BigInteger.valueOf(1)), acc.multiply(n)));
        }
    }
}
