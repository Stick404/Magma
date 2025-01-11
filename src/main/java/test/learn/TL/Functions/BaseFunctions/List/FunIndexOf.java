package test.learn.TL.Functions.BaseFunctions.List;

import test.learn.TL.Core.*;

import static test.learn.TL.Core.Engine.expressionOf;

public class FunIndexOf extends TLFunction {
    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment) throws Exception {
        int n = (Integer) args.get(0).getValue();
        Engine.TLExpression listOrArray = args.get(1);
        if (listOrArray instanceof TLArrayExpression) {
            return expressionOf(((TLArrayExpression) listOrArray).get(n));
        } else {
            return ((TLListExpression) listOrArray).get(n);
        }
    }
}
