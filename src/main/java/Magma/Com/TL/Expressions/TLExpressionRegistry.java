package Magma.Com.TL.Expressions;

import Magma.Com.TL.Core.TLExpression;
import Magma.Com.TL.Expressions.BaseExpressions.*;
import Magma.Com.TL.Expressions.MagmaExpressions.ExDefun;
import Magma.Com.TL.Expressions.MagmaExpressions.ExNull;
import Magma.Com.TL.Expressions.MagmaExpressions.ExOptional;

import java.util.LinkedHashMap;
import java.util.Map;

public class TLExpressionRegistry {
    public static final Map<String, TLExpression> EXPRES = new LinkedHashMap<>();

    //Base Exps
    public static final TLExpression DEF = make("def", new ExDef());
    public static final TLExpression DEF2 = make("setf","def");
    public static final TLExpression IF = make("if", new ExIf());
    public static final TLExpression LAMBDA = make("lambda", new ExLambda());
    public static final TLExpression QUOTE = make("quote", new ExQuote());
    public static final TLExpression QUOTE_REAL = make("'","quote");
    public static final TLExpression LETISH = make("let*", new ExLetIsh());
    public static final TLExpression PROGN = make("progn",new ExProgn());

    //custom Exs
    public static final TLExpression DEFUN = make("defun", new ExDefun());
    public static final TLExpression OPTIONAL = make("&optional", new ExOptional());
    public static final TLExpression NULL = make("null", new ExNull());

    private static TLExpression make(String symbol, TLExpression exps) {
        EXPRES.put(symbol,exps);
        return exps;
    }
    //this is for things like = and eq
    private static TLExpression make(String symbol, String copy) {
        EXPRES.put(symbol,EXPRES.get(copy));
        return EXPRES.get(copy);
    }
}

