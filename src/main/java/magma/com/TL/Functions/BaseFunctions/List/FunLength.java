package magma.com.TL.Functions.BaseFunctions.List;

import magma.com.TL.Core.*;
import magma.com.TL.Core.TL_OLD.TLArrayExpression;
import magma.com.TL.Core.TL_OLD.TLEnvironment;
import magma.com.TL.Core.TL_OLD.TLFunction;
import magma.com.TL.Core.TL_OLD.TLListExpression;

import static magma.com.TL.Core.Engine.expressionOf;

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
