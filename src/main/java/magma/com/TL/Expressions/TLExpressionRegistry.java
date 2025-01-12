package magma.com.TL.Expressions;

import magma.com.TL.Core.TL_OLD.TLExpressions;
import magma.com.TL.Expressions.BaseExpressions.*;
import magma.com.TL.Expressions.MagmaExpressions.ExDefun;
import magma.com.TL.Expressions.MagmaExpressions.ExNull;
import magma.com.TL.Expressions.MagmaExpressions.ExOptional;

import java.util.LinkedHashMap;
import java.util.Map;

public class TLExpressionRegistry {
    public static final Map<String, TLExpressions> EXPRES = new LinkedHashMap<>();

    //Base Exps
    public static final TLExpressions DEF = make("def", new ExDef());
    public static final TLExpressions DEF2 = make("setf","def");
    public static final TLExpressions IF = make("if", new ExIf());
    public static final TLExpressions LAMBDA = make("lambda", new ExLambda());
    public static final TLExpressions QUOTE = make("quote", new ExQuote());
    public static final TLExpressions QUOTE_REAL = make("'","quote");
    public static final TLExpressions LETISH = make("let*", new ExLetIsh());
    public static final TLExpressions PROGN = make("progn",new ExProgn());

    //custom Exs
    public static final TLExpressions DEFUN = make("defun", new ExDefun());
    public static final TLExpressions OPTIONAL = make("&optional", new ExOptional());
    public static final TLExpressions NULL = make("null", new ExNull());

    private static TLExpressions make(String symbol, TLExpressions exps) {
        EXPRES.put(symbol,exps);
        return exps;
    }
    //this is for things like = and eq
    private static TLExpressions make(String symbol, String copy) {
        EXPRES.put(symbol,EXPRES.get(copy));
        return EXPRES.get(copy);
    }
}

