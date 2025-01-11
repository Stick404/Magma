package test.learn.TL.Functions.BaseFunctions.Eval;

import test.learn.TL.Core.Engine;
import test.learn.TL.Core.TLEnvironment;
import test.learn.TL.Core.TLFunction;
import test.learn.TL.Core.TLListExpression;

public class FunParse extends TLFunction {
    Engine engine = new Engine();
    //tbh, don't know what this does? But it seems somewhat useful in some random use case
    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment) throws Exception {
        return engine.parse((String) args.get(0).getValue());
    }
}
