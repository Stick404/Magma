package magma.com.TL.Expressions.BaseExpressions;

import magma.com.TL.Core.Engine;
import magma.com.TL.Core.TL_OLD.TLEnvironment;
import magma.com.TL.Core.TL_OLD.TLExpressions;
import magma.com.TL.Core.TL_OLD.TLListExpression;

public class ExQuote extends TLExpressions {
    @Override
    public Engine.TLExpression invoke(TLListExpression expression, TLEnvironment environment, Engine engine) throws Exception {
        return expression.get(1);
    }
}
