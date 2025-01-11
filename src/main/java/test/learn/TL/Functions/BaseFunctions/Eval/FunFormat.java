package test.learn.TL.Functions.BaseFunctions.Eval;

import test.learn.TL.Core.Engine;
import test.learn.TL.Core.TLEnvironment;
import test.learn.TL.Core.TLFunction;
import test.learn.TL.Core.TLListExpression;

public class FunFormat extends TLFunction {
    @Override public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment) {
        String fmt = (String) args.get(0).getValue();
        Object[] fmtArgs = new Object[args.size() - 1];
        for (int i = 1; i < args.size(); i++) {
            fmtArgs[i - 1] = args.get(i).getValue();
        }
        return Engine.expressionOf(String.format(fmt, fmtArgs));
    }
}
