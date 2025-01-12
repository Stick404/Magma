package magma.com.TL.Functions.BaseFunctions.Eval;

import magma.com.TL.Core.Engine;
import magma.com.TL.Core.TLEnvironment;
import magma.com.TL.Core.TLFunction;
import magma.com.TL.Core.TLListExpression;

public class FunExec extends TLFunction {
    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment, Engine engine) throws Exception {
        return engine.execute((String) args.get(0).getValue(), environment);
    }
}
