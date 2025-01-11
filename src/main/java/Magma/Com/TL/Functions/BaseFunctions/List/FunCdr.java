package Magma.Com.TL.Functions.BaseFunctions.List;

import Magma.Com.TL.Core.Engine;
import Magma.Com.TL.Core.TLEnvironment;
import Magma.Com.TL.Core.TLFunction;
import Magma.Com.TL.Core.TLListExpression;

public class FunCdr extends TLFunction {
    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment, Engine engine) throws Exception {
        TLListExpression arg = (TLListExpression) args.get(0);
        return new TLListExpression(arg.subList(1, arg.size()));
    }
}
