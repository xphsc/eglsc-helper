package com.github.xphsc.collect;

import com.github.xphsc.bean.Closure;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by ${huipei.x} on 2017-8-7.
 */
public class Iterables {

    public static boolean isEmpty(Iterable<?> iterable) {
        return iterable instanceof Collection ?((Collection)iterable).isEmpty(): Iterators.isEmpty(emptyIteratorIfNull(iterable));
    }

    public static <E> void forEach(Iterable<E> iterable, Closure<? super E> closure) {
        Iterables.forEach((Iterable<E>) emptyIteratorIfNull(iterable), closure);
    }

    private static <E> Iterator<E> emptyIteratorIfNull(Iterable<E> iterable) {
        return (Iterator)(iterable != null?iterable.iterator(): Iterators.emptyIterator());
    }

    public static <E> E forEachButLast(Iterable<E> iterable, Closure<? super E> closure) {
        return Iterators.forEachButLast(emptyIteratorIfNull(iterable), closure);
    }

    public static int size(Iterable<?> iterable) {
        return iterable instanceof Collection?((Collection)iterable).size():Iterators.size(emptyIteratorIfNull(iterable));
    }



}
