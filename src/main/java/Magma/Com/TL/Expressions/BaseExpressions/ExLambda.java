package Magma.Com.TL.Expressions.BaseExpressions;

import Magma.Com.TL.Core.*;

public class ExLambda extends TLExpression {
    @Override
    public Engine.TLExpression invoke(TLListExpression expression, TLEnvironment environment, Engine engine) throws Exception {
        TLListExpression params = (TLListExpression) expression.get(1);
        System.out.println(expression);
        TLListExpression body = new TLListExpression(expression.subList(2, expression.size()));
        body.add(0, TLSymbolExpression.of("progn"));
        return TLLambdaFunction.of(params, body, environment, engine, null);
    }
}
