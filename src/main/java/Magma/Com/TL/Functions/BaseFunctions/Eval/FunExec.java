package Magma.Com.TL.Functions.BaseFunctions.Eval;

import Magma.Com.TL.Core.Engine;
import Magma.Com.TL.Core.TLEnvironment;
import Magma.Com.TL.Core.TLFunction;
import Magma.Com.TL.Core.TLListExpression;

public class FunExec extends TLFunction {
    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment, Engine engine) throws Exception {
        return engine.execute((String) args.get(0).getValue(), environment);
    }
}
