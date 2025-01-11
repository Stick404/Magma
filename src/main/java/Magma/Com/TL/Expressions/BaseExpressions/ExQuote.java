package Magma.Com.TL.Expressions.BaseExpressions;

import Magma.Com.TL.Core.Engine;
import Magma.Com.TL.Core.TLEnvironment;
import Magma.Com.TL.Core.TLExpression;
import Magma.Com.TL.Core.TLListExpression;

public class ExQuote extends TLExpression {
    @Override
    public Engine.TLExpression invoke(TLListExpression expression, TLEnvironment environment, Engine engine) throws Exception {
        return expression.get(1);
    }
}
