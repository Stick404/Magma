package test.learn.TL.Expressions;

import test.learn.TL.Core.TLFunction;

import java.util.LinkedHashMap;
import java.util.Map;

public class TLExpressionRegistry {
    public static final Map<String, TLFunction> EXPRES = new LinkedHashMap<>();

    private static TLFunction make(String symbol, TLFunction func) {
        EXPRES.put(symbol,func);
        return func;
    }
    //this is for things like = and eq
    private static TLFunction make(String symbol, String copy) {
        EXPRES.put(symbol,EXPRES.get(copy));
        return EXPRES.get(copy);
    }
}
