package test.learn.TL.Functions.BaseFunctions.List;

import test.learn.TL.Core.*;

import static test.learn.TL.Core.Engine.expressionOf;

public class FunLength extends TLFunction {
    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment) throws Exception {
        Engine.TLExpression listOrArray = args.get(0);
        if (listOrArray instanceof TLArrayExpression) {
            return expressionOf(((TLArrayExpression) listOrArray).length());
        } else {
            return expressionOf(((TLListExpression) listOrArray).size());
        }
    }
}
