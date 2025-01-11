package Magma.Com.TL.Expressions;

import Magma.Com.TL.Core.Engine;
import Magma.Com.TL.Core.TLEnvironment;
import Magma.Com.TL.Core.TLExpression;
import Magma.Com.TL.Core.TLListExpression;

import static Magma.Com.TL.Core.Engine.expressionOf;

public class ExOptional extends TLExpression {
    @Override
    public Engine.TLExpression invoke(TLListExpression expression, TLEnvironment environment, Engine engine) throws Exception {
        return expressionOf(null);
        //this is more of a stand in Class, the real logic happens in TLListExpression/TLLambdaFunction
    }
}
