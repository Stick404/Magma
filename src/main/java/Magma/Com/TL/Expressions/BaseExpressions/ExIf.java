package Magma.Com.TL.Expressions.BaseExpressions;

import Magma.Com.TL.Core.*;

public class ExIf extends TLExpression {
    @Override
    public Engine.TLExpression invoke(TLListExpression expression, TLEnvironment environment, Engine engine) throws Exception {
        TLListExpression condition = (TLListExpression) expression.get(1);
        Engine.TLExpression then = expression.get(2);
        TLListExpression els = new TLListExpression(expression.subList(3, expression.size()));
        els.add(0, TLSymbolExpression.of("progn"));

        boolean result = engine.evaluate(condition, environment).asBoolean();
        return engine.evaluate(result ? then : els, environment);
    }
}
