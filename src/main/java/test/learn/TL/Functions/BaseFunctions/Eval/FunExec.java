package test.learn.TL.Functions.BaseFunctions.Eval;

import test.learn.TL.Core.Engine;
import test.learn.TL.Core.TLEnvironment;
import test.learn.TL.Core.TLFunction;
import test.learn.TL.Core.TLListExpression;

public class FunExec extends TLFunction {
    final Engine engine = new Engine();

    @Override public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment) throws Exception {
        return engine.execute((String) args.get(0).getValue(), environment);
    }
}
