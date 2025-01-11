package Magma.Com.TL.Expressions;

import Magma.Com.TL.Core.*;
import org.jetbrains.annotations.Nullable;

public class ExDefun extends TLExpression {
    @Override
    public Engine.TLExpression invoke(TLListExpression expression, TLEnvironment environment, Engine engine) throws Exception {
        TLSymbolExpression name = (TLSymbolExpression) expression.get(1);
        TLListExpression params = (TLListExpression) expression.get(2);
        TLListExpression paramsOptional = new TLListExpression();
        if (params.getNameLocation("&optional") != -1) {
            paramsOptional = new TLListExpression(params.subList(params.getNameLocation("&optional")+1, params.size() ));
            params = new TLListExpression(params.subList(0,params.getNameLocation("&optional")));
        }
        System.out.println(params);
        System.out.println(paramsOptional);
        TLListExpression body = new TLListExpression(expression.subList(3, expression.size()));
        body.add(0, TLSymbolExpression.of("progn"));
        TLLambdaFunction func = TLLambdaFunction.of(params, body, environment, engine, paramsOptional);
        environment.put(name, func);
        return func;
    }
}
