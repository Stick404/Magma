package magma.com.TL.Expressions.BaseExpressions;

import magma.com.TL.Core.*;
import magma.com.TL.Core.TL_OLD.TLEnvironment;
import magma.com.TL.Core.TL_OLD.TLExpressions;
import magma.com.TL.Core.TL_OLD.TLListExpression;
import magma.com.TL.Core.TL_OLD.TLSymbolExpression;

public class ExDef extends TLExpressions {
    @Override
    public Engine.TLExpression invoke(TLListExpression expression, TLEnvironment environment, Engine engine) throws Exception {
        TLSymbolExpression name = (TLSymbolExpression) expression.get(1);
        Engine.TLExpression value = expression.get(2);
        Engine.TLExpression eValue = engine.evaluate(value, environment);
        environment.put(name, eValue);
        return eValue;
    }
}
