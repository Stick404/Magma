package Magma.Com.TL.Functions.BaseFunctions.Eval;

import Magma.Com.TL.Core.Engine;
import Magma.Com.TL.Core.TLEnvironment;
import Magma.Com.TL.Core.TLFunction;
import Magma.Com.TL.Core.TLListExpression;

public class FunParse extends TLFunction {
    Engine engine = new Engine();
    //tbh, don't know what this does? But it seems somewhat useful in some random use case
    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment, Engine engine) throws Exception {
        return engine.parse((String) args.get(0).getValue());
    }
}
