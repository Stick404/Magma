package Magma.Com.TL.Functions;

import Magma.Com.TL.Core.Engine;
import Magma.Com.TL.Core.TLEnvironment;
import Magma.Com.TL.Core.TLFunction;
import Magma.Com.TL.Core.TLListExpression;

public class FunPrint extends TLFunction {
    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment, Engine engine) throws Exception {
        System.out.println(args.get(0));
        return args.get(0);
    }
}
