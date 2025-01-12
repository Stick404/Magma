package magma.com.TL.Core.TL_OLD;

import magma.com.TL.Core.Engine;

public abstract class TLExpressions extends TLFunction{
    public abstract Engine.TLExpression invoke(TLListExpression expression, TLEnvironment environment, Engine engine) throws Exception;
}
