package Magma.Com.TL.Expressions.BaseExpressions;

import Magma.Com.TL.Core.*;

public class ExLetIsh extends TLExpression {
    @Override
    public Engine.TLExpression invoke(TLListExpression expression, TLEnvironment environment, Engine engine) throws Exception {
        TLListExpression defs = (TLListExpression) expression.get(1);
        TLListExpression body = new TLListExpression(expression.subList(2, expression.size()));
        body.add(0, TLSymbolExpression.of("progn"));
        TLEnvironment localEnvironment = new TLEnvironment(environment);
        for (Engine.TLExpression exp : defs) {
            TLListExpression def = (TLListExpression) exp;
            TLSymbolExpression symbol = (TLSymbolExpression) def.get(0);
            localEnvironment.put(symbol, engine.evaluate(def.get(1), localEnvironment));
        }
        return engine.evaluate(body, localEnvironment);
    }
}
