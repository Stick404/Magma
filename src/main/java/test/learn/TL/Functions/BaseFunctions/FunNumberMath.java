package test.learn.TL.Functions.BaseFunctions;

import test.learn.TL.Core.Engine;
import test.learn.TL.Core.TLEnvironment;
import test.learn.TL.Core.TLFunction;
import test.learn.TL.Core.TLListExpression;

import java.math.BigDecimal;
import java.util.function.BiFunction;

import static test.learn.TL.Core.Engine.*;

public class FunNumberMath extends TLFunction {
    BiFunction<BigDecimal,BigDecimal,BigDecimal> function;

    public FunNumberMath(BiFunction<BigDecimal,BigDecimal,BigDecimal> function){
        this.function = function;
    }

    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment) throws Exception {
        BigDecimal result = toBigDecimal((Number) args.get(0).getValue());
        for (Engine.TLExpression arg : args.subList(1, args.size())) {
            BigDecimal value = toBigDecimal((Number) arg.getValue());
            result = function.apply(result,value);
        }
        return expressionOf(reduceBigDecimal(result));
    }
}
