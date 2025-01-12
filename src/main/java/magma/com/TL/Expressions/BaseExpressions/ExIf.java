package magma.com.TL.Expressions.BaseExpressions;

import magma.com.TL.Core.*;
import magma.com.TL.Core.TL_OLD.TLEnvironment;
import magma.com.TL.Core.TL_OLD.TLExpressions;
import magma.com.TL.Core.TL_OLD.TLListExpression;
import magma.com.TL.Core.TL_OLD.TLSymbolExpression;

public class ExIf extends TLExpressions {
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
