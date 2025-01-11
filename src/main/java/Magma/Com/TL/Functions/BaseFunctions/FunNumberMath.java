package Magma.Com.TL.Functions.BaseFunctions;

import Magma.Com.TL.Core.Engine;
import Magma.Com.TL.Core.TLEnvironment;
import Magma.Com.TL.Core.TLFunction;
import Magma.Com.TL.Core.TLListExpression;

import java.math.BigDecimal;
import java.util.function.BiFunction;

import static Magma.Com.TL.Core.Engine.*;

public class FunNumberMath extends TLFunction {
    BiFunction<BigDecimal,BigDecimal,BigDecimal> function;

    public FunNumberMath(BiFunction<BigDecimal,BigDecimal,BigDecimal> function){
        this.function = function;
    }

    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment, Engine engine) throws Exception {
        BigDecimal result = toBigDecimal((Number) args.get(0).getValue());
        for (Engine.TLExpression arg : args.subList(1, args.size())) {
            BigDecimal value = toBigDecimal((Number) arg.getValue());
            result = function.apply(result,value);
        }
        return expressionOf(reduceBigDecimal(result));
    }
}
