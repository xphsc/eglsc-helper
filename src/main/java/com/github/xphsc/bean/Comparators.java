package com.github.xphsc.bean;


import com.github.xphsc.bean.comparator.*;

import java.util.Collection;
import java.util.Comparator;

/**
 * Created by ${huipei.x} on 2017-8-7.
 */
public class Comparators {

    public static final Comparator NATURAL_COMPARATOR = ComparableComparator.comparableComparator();

    private Comparators() {
    }

    public static <E extends Comparable<? super E>> Comparator<E> naturalComparator() {
        return NATURAL_COMPARATOR;
    }

    public static <E> Comparator<E> chainedComparator(Comparator... comparators) {
        ComparatorChain chain = new ComparatorChain();
        Comparator[] arr$ = comparators;
        int len$ = comparators.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Comparator comparator = arr$[i$];
            if(comparator == null) {
                throw new NullPointerException("Comparator cannot be null");
            }

            chain.addComparator(comparator);
        }

        return chain;
    }

    public static <E> Comparator<E> chainedComparator(Collection<Comparator<E>> comparators) {
        return chainedComparator((Comparator[])((Comparator[])comparators.toArray(new Comparator[comparators.size()])));
    }

    public static <E> Comparator<E> reversedComparator(Comparator<E> comparator) {
        return new ReverseComparator(comparator);
    }

    public static Comparator<Boolean> booleanComparator(boolean trueFirst) {
        return BooleanComparator.booleanComparator(trueFirst);
    }

    public static <E> Comparator<E> nullLowComparator(Comparator<E> comparator) {
        if(comparator == null) {
            comparator = NATURAL_COMPARATOR;
        }

        return new NullComparator(comparator, false);
    }

    public static <E> Comparator<E> nullHighComparator(Comparator<E> comparator) {
        if(comparator == null) {
            comparator = NATURAL_COMPARATOR;
        }

        return new NullComparator(comparator, true);
    }

    public static <I, O> Comparator<I> transformedComparator(Comparator<O> comparator, Transformer<? super I, ? extends O> transformer) {
        if(comparator == null) {
            comparator = NATURAL_COMPARATOR;
        }

        return new TransformingComparator(transformer, comparator);
    }

    public static <E> E min(E o1, E o2, Comparator<E> comparator) {
        if(comparator == null) {
            comparator = NATURAL_COMPARATOR;
        }

        int c = comparator.compare(o1, o2);
        return c < 0?o1:o2;
    }

    public static <E> E max(E o1, E o2, Comparator<E> comparator) {
        if(comparator == null) {
            comparator = NATURAL_COMPARATOR;
        }

        int c = comparator.compare(o1, o2);
        return c > 0?o1:o2;
    }
}
