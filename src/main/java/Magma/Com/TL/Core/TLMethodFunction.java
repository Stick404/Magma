package Magma.Com.TL.Core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static Magma.Com.TL.Core.Engine.expressionOf;

public class TLMethodFunction extends TLFunction {
    public static TLMethodFunction of(Object object, Method method) {
        TLMethodFunction function = new TLMethodFunction();
        function.object = object;
        function.method = method;
        return function;
    }
    private Object object;
    private Method method;
    @Override public Engine.TLExpression invoke(TLListExpression args, TLEnvironment environment, Engine engine) throws Exception {
        Object[] jargs = new Object[args.size()];
        for (int i = 0; i < args.size(); i++) {
            jargs[i] = args.get(i).getValue();
        }
        return expressionOf(method.invoke(object, jargs));
    }
    @Override protected List<?> getParameterHelpNames() {
        List<Object> names = new ArrayList<>();
        for (Class<?> param : method.getParameterTypes()) {
            names.add(param.getSimpleName());
        }
        return names;
    }
}
