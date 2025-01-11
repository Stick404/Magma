package test.learn.TL.Functions.BaseFunctions.Eval;

import test.learn.TL.Core.Engine;
import test.learn.TL.Core.TLEnvironment;
import test.learn.TL.Core.TLFunction;
import test.learn.TL.Core.TLListExpression;

import java.util.Collections;

public class FunMap extends TLFunction {
    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment) throws Exception {
        TLListExpression result = new TLListExpression();
        TLFunction function = (TLFunction) args.get(0);
        TLListExpression list = (TLListExpression) args.get(1);
        for (Engine.TLExpression arg : list) {
            result.add(function.invoke(new TLListExpression(Collections.singletonList(arg)), environment));
        }
        return result;
    }
}
