package magma.com.TL.Core;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MagmaList extends ArrayList<MagmaRoot<?>> implements MagmaRoot<ArrayList<MagmaRoot<?>>>{
    public static MagmaList of (Collection<MagmaRoot<?>> items) {
        MagmaList list = new MagmaList();
        list.addAll(items);
        return list;
    }
    public MagmaList() {
        super();
    }
    public MagmaList(List<MagmaRoot<?>> list) {
        super(list);
    }

    @Override
    public ArrayList<MagmaRoot<?>> getValue() {
        ArrayList<MagmaRoot<?>> result = new ArrayList<>();
        for (MagmaRoot<?> e : this) {
            result.add((MagmaAtom<?>) e.getValue());
        }
        return result;
    }

    @Override
    public boolean asBoolean() {
        return !this.isEmpty();
    }
}
