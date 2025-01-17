package Magma.Com.TL.Core;

import java.util.List;

public class TLArrayExpression extends TLAtomExpression<Object> {
    public static TLArrayExpression of(Object value) {
        if (value == null || !value.getClass().isArray()) {
            throw new IllegalArgumentException("Value is not an array: " + value);
        }
        TLArrayExpression array = new TLArrayExpression();
        array.value = value;
        return array;
    }
    public static TLArrayExpression from(List<Object> values) {
        Class<?> arrayClass = getClass(values);
        Object value;
        if (Integer.class.equals(arrayClass)) {
            int[] intArray = new int[values.size()];
            for (int i = 0; i < values.size(); i++) {
                intArray[i] = (int) values.get(i);
            }
            value = intArray;
        } else if (Double.class.equals(arrayClass)) {
            double[] doubleArray = new double[values.size()];
            for (int i = 0; i < values.size(); i++) {
                doubleArray[i] = (double) values.get(i);
            }
            value = doubleArray;
        } else {
            value = values.toArray();
        }
        TLArrayExpression array = new TLArrayExpression();
        array.value = value;
        return array;
    }
    public Object get(int index) {
        if (value instanceof int[]) {
            return ((int[]) value)[index];
        } else if (value instanceof double[]) {
            return ((double[]) value)[index];
        } else {
            return ((Object[]) value)[index];
        }
    }
    public int length() {
        if (value instanceof int[]) {
            return ((int[]) value).length;
        } else if (value instanceof double[]) {
            return ((double[]) value).length;
        } else {
            return ((Object[]) value).length;
        }
    }
    private static Class<?> getClass(List<Object> values) {
        if (values.isEmpty()) {
            return Object.class;
        }
        Class<?> result = values.get(0).getClass();
        for (Object value : values) {
            if (!value.getClass().equals(result)) {
                return Object.class;
            }
        }
        return result;
    }
    @Override public String toString() {
        StringBuilder builder = new StringBuilder("[");
        if (value instanceof int[]) {
            for (int i : ((int[]) value)) {
                builder.append(i).append(' ');
            }
        } else if (value instanceof double[]) {
            for (double d : ((double[]) value)) {
                builder.append(d).append(' ');
            }
        } else {
            for (Object o : ((Object[]) value)) {
                builder.append(o).append(' ');
            }
        }
        if (builder.charAt(builder.length() - 1) == ' ') {
            builder.deleteCharAt(builder.length() - 1);
        }
        builder.append(']');
        return builder.toString();
    }
}
