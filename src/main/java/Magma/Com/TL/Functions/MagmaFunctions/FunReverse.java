package Magma.Com.TL.Functions.MagmaFunctions;

import Magma.Com.TL.Core.*;

import java.util.Collections;
import java.util.List;

public class FunReverse extends TLFunction {
    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment, Engine engine) throws Exception {
        Engine.TLExpression first = args.get(1);
        List<Object> result = (List<Object>) first.getValue();
        Collections.reverse(result);
        return (Engine.TLExpression) result;
    }
}
