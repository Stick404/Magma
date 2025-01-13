package magma.com.TL.Core;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MagmaList extends ArrayList<MagmaRoot> implements MagmaRoot<ArrayList<MagmaRoot>>{
    public static MagmaList of (Collection<MagmaRoot> items) { // Can be used to make a list out of a list
        MagmaList list = new MagmaList();
        list.addAll(items);
        return list;
    }
    public MagmaList() { //so we can have a default constructor
        super();
    }
    public MagmaList(List<MagmaRoot> list) { // Just in case
        super(list);
    }

    @Override
    public boolean add(MagmaRoot magmaRoot) {
        return super.add(magmaRoot);
    }

    @Override
    public ArrayList<MagmaRoot> getValue() { // Converts the inner list into
        return new ArrayList<>(this);
    }

    @Override
    public boolean asBoolean() { // If the List is empty, then its false
        return !this.isEmpty();
    }
}
