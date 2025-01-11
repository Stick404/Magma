package test.learn.TL.Functions.BaseFunctions.List;

import test.learn.TL.Core.Engine;
import test.learn.TL.Core.TLEnvironment;
import test.learn.TL.Core.TLFunction;
import test.learn.TL.Core.TLListExpression;

public class FunCons extends TLFunction {
    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment) throws Exception {
        TLListExpression result = new TLListExpression();
        result.add(args.get(0));
        Engine.TLExpression rest = args.get(1);
        if (rest instanceof TLListExpression) {
            result.addAll((TLListExpression) rest);
        } else {
            result.add(rest);
        }
        return result;
    }
}
