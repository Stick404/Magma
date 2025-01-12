package magma.com.TL.Expressions.BaseExpressions;

import magma.com.TL.Core.*;

public class ExDef extends TLExpression {
    @Override
    public Engine.TLExpression invoke(TLListExpression expression, TLEnvironment environment, Engine engine) throws Exception {
        TLSymbolExpression name = (TLSymbolExpression) expression.get(1);
        Engine.TLExpression value = expression.get(2);
        Engine.TLExpression eValue = engine.evaluate(value, environment);
        environment.put(name, eValue);
        return eValue;
    }
}
