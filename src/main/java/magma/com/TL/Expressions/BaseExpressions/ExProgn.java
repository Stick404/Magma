package magma.com.TL.Expressions.BaseExpressions;

import magma.com.TL.Core.Engine;
import magma.com.TL.Core.TLEnvironment;
import magma.com.TL.Core.TLExpression;
import magma.com.TL.Core.TLListExpression;

import static magma.com.TL.Core.Engine.expressionOf;

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
