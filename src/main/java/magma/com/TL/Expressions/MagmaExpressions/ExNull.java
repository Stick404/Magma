package magma.com.TL.Expressions.MagmaExpressions;

import magma.com.TL.Core.Engine;
import magma.com.TL.Core.TLEnvironment;
import magma.com.TL.Core.TLExpression;
import magma.com.TL.Core.TLListExpression;

import static magma.com.TL.Core.Engine.expressionOf;

public class ExNull extends TLExpression {
    @Override
    public Engine.TLExpression invoke(TLListExpression expression, TLEnvironment environment, Engine engine) throws Exception {
        return expressionOf(null);
    }
}
