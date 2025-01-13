package magma.com;

import magma.com.TL.Core.MagmaAtom;
import magma.com.TL.Core.MagmaList;

public class TestMagma {
    public void init() {
        MagmaAtom<String> atom1 = new MagmaAtom<String>().of("Hello World");
        MagmaAtom<String> atom2 = new MagmaAtom<String>().of("Testing Objects");
        MagmaAtom<Float> atom3 = new MagmaAtom<Float>().of(1F);
        MagmaAtom<Integer> atom4 = new MagmaAtom<Integer>().of(1);

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

        //How do Bools work?
        MagmaAtom<Boolean> boolT = new MagmaAtom<Boolean>().of(true);
        MagmaAtom<Boolean> boolF = new MagmaAtom<Boolean>().of(false);
        System.out.println(atom1.asBoolean());
        System.out.println(boolT.asBoolean());
        System.out.println(boolF.asBoolean());

        //What about MagmaAtom<?>
        MagmaAtom<?> atom = new MagmaAtom<>().of(1);
        System.out.println(atom.getValue());

        // bad Arbitrary Object storage; but it does work
        MagmaAtom<Object> atomObject = new MagmaAtom<>().of(5);
        System.out.println(atomObject);
        atomObject.setValue(null);
        System.out.println(atomObject);
    }
}