package Magma.Com.TL.Expressions.BaseExpressions;

import Magma.Com.TL.Core.Engine;
import Magma.Com.TL.Core.TLEnvironment;
import Magma.Com.TL.Core.TLExpression;
import Magma.Com.TL.Core.TLListExpression;

import static Magma.Com.TL.Core.Engine.expressionOf;

public class ExProgn extends TLExpression {
    @Override
    public Engine.TLExpression invoke(TLListExpression expression, TLEnvironment environment, Engine engine) throws Exception {
        Engine.TLExpression result = expressionOf(null);
        for (Engine.TLExpression exp : expression.subList(1, expression.size())) {
            result = engine.evaluate(exp, environment);
        }
        return result;
    }
}
