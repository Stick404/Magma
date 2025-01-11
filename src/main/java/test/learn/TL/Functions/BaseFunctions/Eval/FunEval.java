package test.learn.TL.Functions.BaseFunctions.Eval;

import test.learn.TL.Core.Engine;
import test.learn.TL.Core.TLEnvironment;
import test.learn.TL.Core.TLFunction;
import test.learn.TL.Core.TLListExpression;

public class FunEval extends TLFunction {
    @Override public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment) throws Exception {
        final Engine engine = new Engine();
        return engine.evaluate(args.get(0), environment);
    }
}
