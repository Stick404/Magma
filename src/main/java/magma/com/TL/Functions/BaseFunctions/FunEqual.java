package magma.com.TL.Functions.BaseFunctions;

import magma.com.TL.Core.Engine;
import magma.com.TL.Core.TLEnvironment;
import magma.com.TL.Core.TLFunction;
import magma.com.TL.Core.TLListExpression;

import static magma.com.TL.Core.Engine.expressionOf;

public class FunEqual extends TLFunction {
    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment, Engine engine) throws Exception {
        Object arg1 = args.get(0).getValue();
        Object arg2 = args.get(1).getValue();
        return expressionOf(arg1 == arg2);
    }
}
