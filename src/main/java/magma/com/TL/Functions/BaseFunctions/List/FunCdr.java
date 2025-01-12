package magma.com.TL.Functions.BaseFunctions.List;

import magma.com.TL.Core.Engine;
import magma.com.TL.Core.TL_OLD.TLEnvironment;
import magma.com.TL.Core.TL_OLD.TLFunction;
import magma.com.TL.Core.TL_OLD.TLListExpression;

public class FunCdr extends TLFunction {
    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment, Engine engine) throws Exception {
        TLListExpression arg = (TLListExpression) args.get(0);
        return new TLListExpression(arg.subList(1, arg.size()));
    }
}
