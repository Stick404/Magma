package Magma.Com.TL.Functions.BaseFunctions;

import Magma.Com.TL.Core.Engine;
import Magma.Com.TL.Core.TLEnvironment;
import Magma.Com.TL.Core.TLFunction;
import Magma.Com.TL.Core.TLListExpression;

import static Magma.Com.TL.Core.Engine.expressionOf;

public class FunEqual extends TLFunction {
    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment, Engine engine) throws Exception {
        Object arg1 = args.get(0).getValue();
        Object arg2 = args.get(1).getValue();
        return expressionOf(arg1 == arg2);
    }
}
