package magma.com.TL.Functions.BaseFunctions.Eval;

import magma.com.TL.Core.Engine;
import magma.com.TL.Core.TLEnvironment;
import magma.com.TL.Core.TLFunction;
import magma.com.TL.Core.TLListExpression;

public class FunParse extends TLFunction {
    Engine engine = new Engine();
    //tbh, don't know what this does? But it seems somewhat useful in some random use case
    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment, Engine engine) throws Exception {
        return engine.parse((String) args.get(0).getValue());
    }
}
