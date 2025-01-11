package Magma.Com.TL.Core;

import org.jetbrains.annotations.Nullable;

import java.util.List;

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

        if (this.paramsOptional != null) {
            params.addAll(this.paramsOptional);
        }
        paramCount = this.params.size();
        for (int i = 0; i < paramCount; i++) {
            TLSymbolExpression param = (TLSymbolExpression) params.get(i);
            Engine.TLExpression arg = args.get(i);
            tempEnv.put(param, arg);
        }

        return engine.evaluate(body, tempEnv);
    }
    @Override protected List<?> getParameterHelpNames() {
        return params.getValue();
    }
}