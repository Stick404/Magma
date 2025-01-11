package test.learn.TL.Functions.BaseFunctions.List;

import test.learn.TL.Core.Engine;
import test.learn.TL.Core.TLEnvironment;
import test.learn.TL.Core.TLFunction;
import test.learn.TL.Core.TLListExpression;

public class FunCdr extends TLFunction {
    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment) throws Exception {
        TLListExpression arg = (TLListExpression) args.get(0);
        return new TLListExpression(arg.subList(1, arg.size()));
    }
}
