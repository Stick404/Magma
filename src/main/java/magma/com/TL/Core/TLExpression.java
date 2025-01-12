package magma.com.TL.Core;

public abstract class TLExpression extends TLFunction{
    public abstract Engine.TLExpression invoke(TLListExpression expression, TLEnvironment environment, Engine engine) throws Exception;
}
