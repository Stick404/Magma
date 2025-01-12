package magma.com.TL.Functions.BaseFunctions.List;

import magma.com.TL.Core.Engine;
import magma.com.TL.Core.TLEnvironment;
import magma.com.TL.Core.TLFunction;
import magma.com.TL.Core.TLListExpression;

public class FunCons extends TLFunction {
    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment, Engine engine) throws Exception {
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
