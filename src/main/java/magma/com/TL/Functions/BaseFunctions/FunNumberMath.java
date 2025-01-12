package magma.com.TL.Functions.BaseFunctions;

import magma.com.TL.Core.Engine;
import magma.com.TL.Core.TLEnvironment;
import magma.com.TL.Core.TLFunction;
import magma.com.TL.Core.TLListExpression;

import java.math.BigDecimal;
import java.util.function.BiFunction;

import static magma.com.TL.Core.Engine.*;

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
