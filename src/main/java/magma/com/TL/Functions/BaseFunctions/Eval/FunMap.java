package magma.com.TL.Functions.BaseFunctions.Eval;

import magma.com.TL.Core.Engine;
import magma.com.TL.Core.TLEnvironment;
import magma.com.TL.Core.TLFunction;
import magma.com.TL.Core.TLListExpression;

import java.util.Collections;

public class FunMap extends TLFunction {
    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment, Engine engine) throws Exception {
        TLListExpression result = new TLListExpression();
        TLFunction function = (TLFunction) args.get(0);
        TLListExpression list = (TLListExpression) args.get(1);
        for (Engine.TLExpression arg : list) {
            result.add(function.invoke(new TLListExpression(Collections.singletonList(arg)), environment, engine ));
        }
        return result;
    }
}
