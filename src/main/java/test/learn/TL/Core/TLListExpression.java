package test.learn.TL.Core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static test.learn.TL.Core.Engine.expressionOf;
import static test.learn.TL.Core.Engine.listToString;

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
    @Override public String toString() {
        return listToString("(", this, " ", ")");
    }
}
