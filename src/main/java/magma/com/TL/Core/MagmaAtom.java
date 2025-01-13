package magma.com.TL.Core;

import static magma.com.TL.Core.Engine.escapeString;

public class MagmaAtom<T> implements MagmaRoot<T> {
    //Everything Magma runs is first an Atom
    //This is the basic Object of it
    protected T value;
    public MagmaAtom<T> of(T object){
        MagmaAtom<T> atom = new MagmaAtom<>();
        atom.setValue(object);
        return atom;
    }

    public T getValue() {
        if (this.value != null){
            return this.value;
        }else {
            return null;
        }
    }

    private void setValue(T object){
        this.value = object;
    }

    @Override
    public boolean asBoolean() {
        return value != null || value.equals(false);
    }

    public String toString() {
        return value instanceof String ? "\"" + escapeString((String) value) + '"' : String.valueOf(value);
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
