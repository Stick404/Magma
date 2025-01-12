package magma.com.TL.Functions.BaseFunctions.Eval;

import magma.com.TL.Core.Engine;
import magma.com.TL.Core.TLEnvironment;
import magma.com.TL.Core.TLFunction;
import magma.com.TL.Core.TLListExpression;

public class FunEval extends TLFunction {
    @Override public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment, Engine engine) throws Exception {
        return engine.evaluate(args.get(0), environment);
    }
}
