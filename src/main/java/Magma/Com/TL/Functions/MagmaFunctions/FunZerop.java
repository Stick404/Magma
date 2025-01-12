package Magma.Com.TL.Functions.MagmaFunctions;

import Magma.Com.TL.Core.Engine;
import Magma.Com.TL.Core.TLEnvironment;
import Magma.Com.TL.Core.TLFunction;
import Magma.Com.TL.Core.TLListExpression;

import java.math.BigDecimal;

import static Magma.Com.TL.Core.Engine.expressionOf;

public class FunZerop extends TLFunction {
    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment, Engine engine) throws Exception {
        //System.out.println(args.get(0).getValue());
        //System.out.println(args.get(0).getValue().getClass());
        Integer first = (Integer) args.get(0).getValue();
        if (first == 0) {
            return expressionOf(true);
        } else {
            return expressionOf(false);
        }
    }
}
