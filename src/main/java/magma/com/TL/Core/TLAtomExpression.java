package magma.com.TL.Core;


import java.util.Objects;

import static magma.com.TL.Core.Engine.escapeString;

public abstract class TLAtomExpression<T> implements Engine.TLExpression {
    protected T value;
    public T getValue() {
        return value;
    }
    @Override public String toString() {
        return value instanceof String ? "\"" + escapeString((String) value) + '"' : String.valueOf(value);
    }
    @Override public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
    @Override public boolean equals(Object o) {
        if (o != null && this.getClass().equals(o.getClass())) {
            Object oVal = ((TLAtomExpression<?>) o).value;
            return Objects.equals(value, oVal);
        } else {
            return false;
        }
    }
    @Override public boolean asBoolean() {
        return value != null && !Boolean.FALSE.equals(value);
    }
}
