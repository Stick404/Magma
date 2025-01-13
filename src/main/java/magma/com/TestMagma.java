package magma.com;

import magma.com.TL.Core.MagmaAtom;
import magma.com.TL.Core.MagmaList;

public class TestMagma {
    public void init() {
        MagmaAtom<String> atom1 = new MagmaAtom<String>().of("Hello World");
        MagmaAtom<String> atom2 = new MagmaAtom<String>().of("Testing Objects");
        MagmaAtom<Integer> atom3 = new MagmaAtom<Integer>().of(1);
        MagmaList list = new MagmaList();
        list.add(atom1);
        list.add(atom2);
        list.add(atom3);
        System.out.println(atom1);
        System.out.println(atom2);
        System.out.println(atom3);
        System.out.println(list);
        MagmaList list2 = new MagmaList();
        list2.add(list);
        list2.add(atom1);
        System.out.println(list2);
        MagmaAtom<MagmaList> atomList = new MagmaAtom<MagmaList>().of(list);
        System.out.println(atomList);
    }
}