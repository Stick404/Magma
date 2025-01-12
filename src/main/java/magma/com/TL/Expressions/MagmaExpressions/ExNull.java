package magma.com.TL.Expressions.MagmaExpressions;

import magma.com.TL.Core.Engine;
import magma.com.TL.Core.TL_OLD.TLEnvironment;
import magma.com.TL.Core.TL_OLD.TLExpressions;
import magma.com.TL.Core.TL_OLD.TLListExpression;

import static magma.com.TL.Core.Engine.expressionOf;

public class ExNull extends TLExpressions {
    @Override
    public Engine.TLExpression invoke(TLListExpression expression, TLEnvironment environment, Engine engine) throws Exception {
        return expressionOf(null);
    }
}
