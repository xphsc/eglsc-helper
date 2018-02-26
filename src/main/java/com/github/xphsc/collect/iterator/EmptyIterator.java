package com.github.xphsc.collect.iterator;

import java.util.Iterator;

/**
 * Created by ${huipei.x} on 2017-8-7.
 */
public class EmptyIterator<E> extends AbstractEmptyIterator<E> implements ResettableIterator<E> {
    public static final ResettableIterator RESETTABLE_INSTANCE = new EmptyIterator();
    public static final Iterator INSTANCE;

    public static <E> ResettableIterator<E> resettableEmptyIterator() {
        return RESETTABLE_INSTANCE;
    }

    public static <E> Iterator<E> emptyIterator() {
        return INSTANCE;
    }

    protected EmptyIterator() {
    }

    static {
        INSTANCE = RESETTABLE_INSTANCE;
    }
}