package magma.com.TL.Core;

import static magma.com.TL.Core.Engine.escapeString;

public abstract class LavaAtomExpression<T> {
    //Everything Magma runs is first an Atom
    //This is the basic Object of it
    protected T value;

    T getValue() {
        return value;
    }

    public String toString() {
        return value instanceof String ? "\"" + escapeString((String) value) + '"' : String.valueOf(value);
    }

    boolean getBoolean() {
        return value != null || value.equals(false);
    }

    @Override
    public boolean equals(Object obj) {
        return value.equals(obj);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
