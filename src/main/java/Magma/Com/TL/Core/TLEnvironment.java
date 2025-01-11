package Magma.Com.TL.Core;

import java.util.*;

public class TLEnvironment extends HashMap<TLSymbolExpression, Engine.TLExpression> {
    public Engine engine;

    public TLEnvironment() {
        super();
    }
    public TLEnvironment(Map<TLSymbolExpression, Engine.TLExpression> env) {
        super(env);
    }
    public Engine.TLExpression alias(TLSymbolExpression from, TLSymbolExpression to) {
        return put(to, get(from));
    }
    public List<String> complete(String prefix) {
        List<String> result = new ArrayList<>();
        for (TLSymbolExpression symbol : keySet()) {
            String name = symbol.getValue();
            if (name.startsWith(prefix)) {
                result.add(name);
            }
        }
        Collections.sort(result);
        return Collections.unmodifiableList(result);
    }
}
