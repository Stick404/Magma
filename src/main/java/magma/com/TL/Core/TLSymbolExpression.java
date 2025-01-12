package magma.com.TL.Core;

public class TLSymbolExpression extends TLAtomExpression<String> {
    public static TLSymbolExpression of(String value) {
        TLSymbolExpression symbol = new TLSymbolExpression();
        symbol.value = value;
        return symbol;
    }
    @Override public String toString() {
        return value;
    }
}
