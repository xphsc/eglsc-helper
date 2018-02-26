package com.github.xphsc.collect;



import com.github.xphsc.bean.Closure;
import com.github.xphsc.bean.comparator.Transformer;
import com.github.xphsc.collect.iterator.EmptyIterator;
import com.github.xphsc.collect.iterator.ResettableIterator;
import java.util.Iterator;



public class Iterators {

    public static boolean isEmpty(Iterator<?> iterator) {
        return iterator == null || !iterator.hasNext();
    }

    public static <E> void forEach(Iterator<E> iterator, Closure<Object> closure) {
        if(closure == null) {
            throw new NullPointerException("Closure must not be null");
        } else {
            if(iterator != null) {
                while(iterator.hasNext()) {
                    Object element = iterator.next();
                    closure.execute(element);
                }
            }

        }
    }
    public static <T> T forEachButLast(Iterator<T> iterator, Closure<? super T> closure) {
        if(closure == null) {
            throw new NullPointerException("Closure must not be null.");
        } else {
            if(iterator != null) {
                while(iterator.hasNext()) {
                    Object element = iterator.next();
                    if(!iterator.hasNext()) {
                        return (T) element;
                    }

                    closure.execute((T)element);
                }
            }

            return null;
        }
    }
    public static <E> ResettableIterator<E> emptyIterator() {
        return EmptyIterator.resettableEmptyIterator();
    }


    public static int size(Iterator<?> iterator) {
        int size = 0;
        if(iterator != null) {
            while(iterator.hasNext()) {
                iterator.next();
                ++size;
            }
        }

        return size;
    }


    public static <E> String toString(Iterator<E> iterator, Transformer<? super E, String> transformer) {
        return toString(iterator, transformer, ", ", "[", "]");
    }
    public static <E> String toString(Iterator<E> iterator, Transformer transformer, String delimiter, String prefix, String suffix) {
        if(transformer == null) {
            throw new NullPointerException("transformer may not be null");
        } else if(delimiter == null) {
            throw new NullPointerException("delimiter may not be null");
        } else if(prefix == null) {
            throw new NullPointerException("prefix may not be null");
        } else if(suffix == null) {
            throw new NullPointerException("suffix may not be null");
        } else {
            StringBuilder stringBuilder = new StringBuilder(prefix);
            if(iterator != null) {
                while(iterator.hasNext()) {
                    Object element = iterator.next();
                    stringBuilder.append(transformer.transform(element));
                    stringBuilder.append(delimiter);
                }

                if(stringBuilder.length() > prefix.length()) {
                    stringBuilder.setLength(stringBuilder.length() - delimiter.length());
                }
            }

            stringBuilder.append(suffix);
            return stringBuilder.toString();
        }
    }


}
