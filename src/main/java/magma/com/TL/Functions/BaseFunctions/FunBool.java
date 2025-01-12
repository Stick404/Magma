package magma.com.TL.Functions.BaseFunctions;

import magma.com.TL.Core.Engine;
import magma.com.TL.Core.TL_OLD.TLEnvironment;
import magma.com.TL.Core.TL_OLD.TLFunction;
import magma.com.TL.Core.TL_OLD.TLListExpression;

import java.util.function.BiFunction;

import static magma.com.TL.Core.Engine.expressionOf;

public class FunBool extends TLFunction {
    BiFunction<Double,Double,Boolean> function;
    public FunBool(BiFunction<Double,Double,Boolean> function){
        this.function = function;
    }
    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment, Engine engine) throws Exception {
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
