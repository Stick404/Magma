package test.learn.TL.Functions.BaseFunctions;

import test.learn.TL.Core.Engine;
import test.learn.TL.Core.TLEnvironment;
import test.learn.TL.Core.TLFunction;
import test.learn.TL.Core.TLListExpression;

import java.util.function.BiFunction;

import static test.learn.TL.Core.Engine.expressionOf;

public class FunBool extends TLFunction {
    BiFunction<Double,Double,Boolean> function;
    public FunBool(BiFunction<Double,Double,Boolean> function){
        this.function = function;
    }
    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment) throws Exception {
        double first = ((Number) args.get(0).getValue()).doubleValue();
        for (Engine.TLExpression arg : args.subList(1, args.size())) {
            Double x = ((Number) arg.getValue()).doubleValue();;
            if (!function.apply(first,x)) {
                return expressionOf(false);
            }
        }
        return expressionOf(true);
    }
}
