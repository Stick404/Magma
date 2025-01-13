package magma.com.TL.Core;

public interface MagmaRoot<T> { // The root of all Atom-likes
    T getValue();
    boolean asBoolean();
}
