package test.learn.TL.Core;

public class TLJavaObjectExpression extends TLAtomExpression<Object> {
    public static TLJavaObjectExpression of(Object value) {
        TLJavaObjectExpression jobj = new TLJavaObjectExpression();
        jobj.value = value;
        return jobj;
    }
}
