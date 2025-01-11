package test.learn.TL.Functions.BaseFunctions.Eval;

import test.learn.TL.Core.Engine;
import test.learn.TL.Core.TLEnvironment;
import test.learn.TL.Core.TLFunction;
import test.learn.TL.Core.TLListExpression;

public class FunApply extends TLFunction {
    Engine engine = new Engine();
    @Override public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment) throws Exception {
        TLListExpression applyArgs = new TLListExpression(args.subList(1, args.size()));
        Engine.TLExpression last = applyArgs.get(applyArgs.size() - 1);
        if (last instanceof TLListExpression) {
            applyArgs.remove(applyArgs.size() - 1);
            applyArgs.addAll((TLListExpression) last);
        }
        return engine.apply((TLFunction) args.get(0), applyArgs, environment);
    }
}
