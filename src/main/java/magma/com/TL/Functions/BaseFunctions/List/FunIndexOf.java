package magma.com.TL.Functions.BaseFunctions.List;

import magma.com.TL.Core.*;

import static magma.com.TL.Core.Engine.expressionOf;

public class FunIndexOf extends TLFunction {
    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment, Engine engine) throws Exception {
        int n = (Integer) args.get(0).getValue();
        Engine.TLExpression listOrArray = args.get(1);
        if (listOrArray instanceof TLArrayExpression) {
            return expressionOf(((TLArrayExpression) listOrArray).get(n));
        } else {
            return ((TLListExpression) listOrArray).get(n);
        }
    }
}
