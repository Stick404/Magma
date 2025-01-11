package Magma.Com.TL.Functions.BaseFunctions.List;

import Magma.Com.TL.Core.*;

import static Magma.Com.TL.Core.Engine.expressionOf;

public class FunLength extends TLFunction {
    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment, Engine engine) throws Exception {
        Engine.TLExpression listOrArray = args.get(0);
        if (listOrArray instanceof TLArrayExpression) {
            return expressionOf(((TLArrayExpression) listOrArray).length());
        } else {
            return expressionOf(((TLListExpression) listOrArray).size());
        }
    }
}
