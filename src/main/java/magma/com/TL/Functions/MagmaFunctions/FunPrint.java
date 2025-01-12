package magma.com.TL.Functions.MagmaFunctions;

import magma.com.TL.Core.Engine;
import magma.com.TL.Core.TLEnvironment;
import magma.com.TL.Core.TLFunction;
import magma.com.TL.Core.TLListExpression;

public class FunPrint extends TLFunction {
    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment, Engine engine) throws Exception {
        System.out.println(args.get(0));
        return args.get(0);
    }
}
