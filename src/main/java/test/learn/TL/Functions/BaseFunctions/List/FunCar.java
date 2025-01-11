package test.learn.TL.Functions.BaseFunctions.List;

import test.learn.TL.Core.Engine;
import test.learn.TL.Core.TLEnvironment;
import test.learn.TL.Core.TLFunction;
import test.learn.TL.Core.TLListExpression;

public class FunCar extends TLFunction {
    //vroom vroom?
    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment) throws Exception {
        TLListExpression arg = (TLListExpression) args.get(0);
        return arg.get(0);
    }
}
