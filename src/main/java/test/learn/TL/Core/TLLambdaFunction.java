package test.learn.TL.Core;

import java.util.List;

public class TLLambdaFunction extends TLFunction {
    public static TLLambdaFunction of(TLListExpression params, TLListExpression body, TLEnvironment env, Engine engine) {
        TLLambdaFunction lambda = new TLLambdaFunction();
        lambda.params = params;
        lambda.body = body;
        lambda.env = env;
        lambda.engine = engine;
        return lambda;
    }
    private TLListExpression params;
    private TLListExpression body;
    private TLEnvironment env;
    private Engine engine;
    @Override
    public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment) throws Exception {
        TLEnvironment tempEnv = new TLEnvironment(env);
        for (int i = 0; i < params.size(); i++) {
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