package Magma.Com.TL.Core;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;

import static Magma.Com.TL.Core.Engine.expressionOf;

public class TLLambdaFunction extends TLFunction {
    public static TLLambdaFunction of(TLListExpression params, TLListExpression body, TLEnvironment env, Engine engine,@Nullable TLListExpression paramsOptional) {
        TLLambdaFunction lambda = new TLLambdaFunction();
        lambda.params = params;
        lambda.body = body;
        lambda.env = env;
        lambda.paramsOptional = paramsOptional;
        return lambda;
    }
    private TLListExpression params;
    private TLListExpression body;
    private TLEnvironment env;
    private TLListExpression paramsOptional; //can be null if there are none

    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment, Engine engine) throws Exception {
        TLEnvironment tempEnv = new TLEnvironment(env);
        int paramCount;
        TLListExpression paramsOG = new TLListExpression();
        TLListExpression paramsNew = (TLListExpression) params.clone();

        if (paramsOptional != null) {
            paramsOG = (TLListExpression) paramsNew.clone();
            paramsNew.addAll(paramsOptional);
        }
        paramCount = paramsNew.size();
        for (int i = 0; i < paramCount; i++) {
            Object param = paramsNew.get(i);
            Engine.TLExpression arg;
            TLSymbolExpression result;
            int argSize = args.size();
            if (args.size() == 1) { //this is to fix functions that have only 1 "arg" (&optional)
                argSize = 0;
            }

            if ((argSize <= i && paramsOptional != null) && argSize >= paramsOG.size()){
                //first is to make sure the Lambda does have paramsOptional
                //second is to make sure that paramsOptional is not null
                //third is to make sure that the args qualify for Optional params
                if (param.getClass() == TLListExpression.class) {
                    TLListExpression x = (TLListExpression) param;
                    arg = expressionOf(x.get(1).getValue());
                    result = TLSymbolExpression.of(x.get(0).toString());
                } else {
                    result = (TLSymbolExpression) param;
                    arg = expressionOf(null);
                }
            } else {
                System.out.println(argSize);
                System.out.println(i);
                System.out.println(args.size() <= i);
                System.out.println(paramsOptional != null);
                System.out.println(args.size() >= paramsOG.size());
                result = (TLSymbolExpression) param;
                arg = args.get(i);
            }
            tempEnv.put(result, arg);
        }
        return engine.evaluate(body, tempEnv);
    }
    @Override protected List<?> getParameterHelpNames() {
        return params.getValue();
    }
}