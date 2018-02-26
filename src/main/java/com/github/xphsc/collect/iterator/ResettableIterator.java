package com.github.xphsc.collect.iterator;

import java.util.Iterator;

/**
 * Created by ${huipei.x} on 2017-8-7.
 */
public interface ResettableIterator<E> extends Iterator<E> {
    void reset();
}