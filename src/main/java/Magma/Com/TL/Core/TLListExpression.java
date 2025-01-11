package Magma.Com.TL.Core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static Magma.Com.TL.Core.Engine.expressionOf;
import static Magma.Com.TL.Core.Engine.listToString;

public class TLListExpression extends ArrayList<Engine.TLExpression> implements Engine.TLExpression {
    public static TLListExpression of (Collection<?> items) {
        TLListExpression list = new TLListExpression();
        for (Object item : items) {
            list.add(expressionOf(item));
        }
        return list;
    }
    public TLListExpression() {
        super();
    }
    public TLListExpression(List<Engine.TLExpression> list) {
        super(list);
    }
    @Override public boolean asBoolean() {
        return !isEmpty();
    }

    public List<Object> getValue() {
        List<Object> result = new ArrayList<>();
        for (Engine.TLExpression expression : this) {
            result.add(expression.getValue());
        }
        return result;
    }
    //returns the first instant of name
    public int getNameLocation(String name) {
        int i = 0;
        for (Engine.TLExpression expression : this) {
            if (Objects.equals(expression.toString(), name)) {
                return i;
            }
            i++;
        }
        return -1;
    }
    @Override public String toString() {
        return listToString("(", this, " ", ")");
    }
}
