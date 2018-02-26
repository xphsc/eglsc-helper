package com.github.xphsc.bean.comparator;


import com.github.xphsc.bean.Comparators;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by ${huipei.x} on 2017-8-7.
 */
public class NullComparator <E> implements Comparator<E>, Serializable {
    private static final long serialVersionUID = -5820772575483504339L;
    private final Comparator<? super E> nonNullComparator;
    private final boolean nullsAreHigh;

    public NullComparator() {
        this(Comparators.NATURAL_COMPARATOR, true);
    }

    public NullComparator(Comparator<? super E> nonNullComparator) {
        this(nonNullComparator, true);
    }

    public NullComparator(boolean nullsAreHigh) {
        this(Comparators.NATURAL_COMPARATOR, nullsAreHigh);
    }

    public NullComparator(Comparator<? super E> nonNullComparator, boolean nullsAreHigh) {
        this.nonNullComparator = nonNullComparator;
        this.nullsAreHigh = nullsAreHigh;
        if(nonNullComparator == null) {
            throw new NullPointerException("null nonNullComparator");
        }
    }

    public int compare(E o1, E o2) {
        return o1 == o2?0:(o1 == null?(this.nullsAreHigh?1:-1):(o2 == null?(this.nullsAreHigh?-1:1):this.nonNullComparator.compare(o1, o2)));
    }

    public int hashCode() {
        return (this.nullsAreHigh?-1:1) * this.nonNullComparator.hashCode();
    }

    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        } else if(obj == this) {
            return true;
        } else if(!obj.getClass().equals(this.getClass())) {
            return false;
        } else {
            NullComparator other = (NullComparator)obj;
            return this.nullsAreHigh == other.nullsAreHigh && this.nonNullComparator.equals(other.nonNullComparator);
        }
    }
}
