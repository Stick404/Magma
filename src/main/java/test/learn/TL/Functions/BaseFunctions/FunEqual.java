package test.learn.TL.Functions.BaseFunctions;

import test.learn.TL.Core.Engine;
import test.learn.TL.Core.TLEnvironment;
import test.learn.TL.Core.TLFunction;
import test.learn.TL.Core.TLListExpression;

import static test.learn.TL.Core.Engine.expressionOf;

public class FunEqual extends TLFunction {
    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment) throws Exception {
        Object arg1 = args.get(0).getValue();
        Object arg2 = args.get(1).getValue();
        return expressionOf(arg1 == arg2);
    }
}
