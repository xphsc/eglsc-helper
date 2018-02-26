package com.github.xphsc.bean.comparator;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by ${huipei.x} on 2017-8-7.
 */
public class ComparableComparator<E extends Comparable<? super E>> implements Comparator<E>, Serializable {
    private static final long serialVersionUID = -291439688585137865L;
    public static final ComparableComparator INSTANCE = new ComparableComparator();

    public static <E extends Comparable<? super E>> ComparableComparator<E> comparableComparator() {
        return INSTANCE;
    }

    public ComparableComparator() {
    }

    public int compare(E obj1, E obj2) {
        return obj1.compareTo(obj2);
    }

    public int hashCode() {
        return "ComparableComparator".hashCode();
    }

    public boolean equals(Object object) {
        return this == object || null != object && object.getClass().equals(this.getClass());
    }
}