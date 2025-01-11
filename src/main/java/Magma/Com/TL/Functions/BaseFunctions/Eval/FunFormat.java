package Magma.Com.TL.Functions.BaseFunctions.Eval;

import Magma.Com.TL.Core.Engine;
import Magma.Com.TL.Core.TLEnvironment;
import Magma.Com.TL.Core.TLFunction;
import Magma.Com.TL.Core.TLListExpression;

public class FunFormat extends TLFunction {
    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment, Engine engine) {
        String fmt = (String) args.get(0).getValue();
        Object[] fmtArgs = new Object[args.size() - 1];
        for (int i = 1; i < args.size(); i++) {
            fmtArgs[i - 1] = args.get(i).getValue();
        }
        return Engine.expressionOf(String.format(fmt, fmtArgs));
    }
}
