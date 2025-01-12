package magma.com.TL.Functions.MagmaFunctions;

import magma.com.TL.Core.Engine;
import magma.com.TL.Core.TL_OLD.TLEnvironment;
import magma.com.TL.Core.TL_OLD.TLFunction;
import magma.com.TL.Core.TL_OLD.TLListExpression;

import static magma.com.TL.Core.Engine.expressionOf;

public class FunZerop extends TLFunction {
    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment, Engine engine) throws Exception {
        //System.out.println(args.get(0).getValue());
        //System.out.println(args.get(0).getValue().getClass());
        Integer first = (Integer) args.get(0).getValue();
        if (first == 0) {
            return expressionOf(true);
        } else {
            return expressionOf(false);
        }
    }
}
