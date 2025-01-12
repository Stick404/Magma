package magma.com.TL.Expressions.BaseExpressions;

import magma.com.TL.Core.Engine;
import magma.com.TL.Core.TLEnvironment;
import magma.com.TL.Core.TLExpression;
import magma.com.TL.Core.TLListExpression;

public class ExQuote extends TLExpression {
    @Override
    public Engine.TLExpression invoke(TLListExpression expression, TLEnvironment environment, Engine engine) throws Exception {
        return expression.get(1);
    }
}
