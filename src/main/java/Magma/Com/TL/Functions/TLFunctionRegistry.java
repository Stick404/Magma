package Magma.Com.TL.Functions;

import Magma.Com.TL.Core.TLFunction;
import Magma.Com.TL.Functions.BaseFunctions.*;
import Magma.Com.TL.Functions.BaseFunctions.Eval.FunEval;
import Magma.Com.TL.Functions.BaseFunctions.Eval.FunMap;
import Magma.Com.TL.Functions.BaseFunctions.Eval.FunParse;
import Magma.Com.TL.Functions.BaseFunctions.List.*;
import Magma.Com.TL.Functions.MagmaFunctions.FunPrint;
import Magma.Com.TL.Functions.MagmaFunctions.FunZerop;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class TLFunctionRegistry {
    public static final Map<String, TLFunction> FUNCS = new LinkedHashMap<>();

    //Basic Math
    public static final TLFunction ADD = make("+", new FunNumberMath(BigDecimal::add)); //can be (Z,X) -> Z.add(X), but this is a little more compact
    public static final TLFunction ADD2 = make("add","+");                        //according to the IDE at least
    public static final TLFunction SUB = make("-", new FunNumberMath(BigDecimal::subtract));
    public static final TLFunction MULTIPLY = make("*",new FunNumberMath(BigDecimal::multiply));
    public static final TLFunction DIVIDE = make("/", new FunNumberMath(BigDecimal::divide));
    public static final TLFunction EXPONENT = make("^",new FunNumberMath( (Z,X) -> BigDecimal.valueOf(Math.pow(Z.doubleValue(),X.doubleValue())) ));

    //Boolean stuff
    public static final TLFunction GREATER_THAN = make("<", new FunBool( (x, z) -> x < z ));
    public static final TLFunction LESS_THAN = make(">", new FunBool( (x, z) -> x > z ));
    public static final TLFunction GREATER_EQUAL_THAN = make("<=", new FunBool( (x, z) -> x <= z ));
    public static final TLFunction LESS_EQUAL_THAN = make(">=", new FunBool( (x, z) -> x >= z ));
    public static final TLFunction EQUAL = make("=", new FunEqual());
    public static final TLFunction EQUAL2 = make("is", "=");
    public static final TLFunction EQUAL3 = make("eq", "=");

    public static final TLFunction ZEROP = make("zerop",new FunZerop());

    //List stuff
    public static final TLFunction CDR = make("cdr",new FunCdr());
    public static final TLFunction CAR = make("car",new FunCar());
    public static final TLFunction LIST = make("list", new FunList());
    public static final TLFunction INDEX_OF = make("nth", new FunIndexOf());
    public static final TLFunction CONS = make("cons", new FunCons());

    //Meta-evals (the Fun part of Lisp!)
    public static final TLFunction MAP = make("map", new FunMap());
    public static final TLFunction EVAL = make("eval", new FunEval());
    public static final TLFunction PARSE = make("parse", new FunParse());

    //Extras
    public static final TLFunction PRINT = make("print", new FunPrint());

    private static TLFunction make(String symbol, TLFunction func) {
        FUNCS.put(symbol,func);
        return func;
    }
    //this is for things like = and eq
    private static TLFunction make(String symbol, String copy) {
        FUNCS.put(symbol,FUNCS.get(copy));
        return FUNCS.get(copy);
    }

    //public static final TLFunction ADD = make("+", new FunAdd());
    //public static final TLFunction ADD2 = make("add","+");
    //public static final TLFunction SUB = make("-", new FunSub());
    //public static final TLFunction MULTIPLY = make("*",new FunMultiply());
    //public static final TLFunction DIVIDE = make("/", new FunDivide());
}
