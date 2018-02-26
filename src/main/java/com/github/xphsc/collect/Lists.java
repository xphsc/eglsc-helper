package com.github.xphsc.collect;

import com.github.xphsc.lang.Validator;


import java.util.*;

/**
 *  Created by ${huipei.x} on 2017-5-31.
 */
public class Lists {

    private Lists() {
    }

    public static <T> ArrayList<T> newArrayList(Collection<T> collection) {
        return new ArrayList(collection);
    }
    @SafeVarargs
    public static <T> ArrayList<T> newArrayList(T... values) {
        ArrayList arrayList = new ArrayList(values.length);
        Object[] arr = values;
        int len = values.length;

        for(int i = 0; i < len; ++i) {
            Object t = arr[i];
            arrayList.add(t);
        }

        return arrayList;
    }
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
    public static boolean isEmpty(Iterable<?> iterable) {
        return null == iterable || isEmpty(iterable.iterator());
    }
    public static boolean isEmpty(Iterator<?> Iterator) {
        return null == Iterator || !Iterator.hasNext();
    }
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }
    public static boolean isNotEmpty(Iterable<?> iterable) {
        return null != iterable && isNotEmpty(iterable.iterator());
    }
    public static boolean isNotEmpty(Iterator<?> Iterator) {
        return null != Iterator && Iterator.hasNext();
    }

    public static <T> List<T> toList(Collection<T> collection) {
        return (List)(null == collection?Collections.emptyList():(collection instanceof List?(List)collection:new ArrayList(collection)));
    }
    public static <T> List<T> toList(Enumeration<T> enumeration) {
        return (List)(null == enumeration?Collections.emptyList():Collections.list(enumeration));
    }
    @SafeVarargs
    public static <T> List<T> toList(T... arrays) {
        return (List)(Validator.isNullOrEmpty(arrays)?Collections.emptyList():new ArrayList(Arrays.asList(arrays)));
    }
    public static <T> List<T> emptyIfNull(List<T> list) {
        return list == null? (List<T>) Collections.emptyList() :list;
    }

    public static <T> List<T> defaultIfNull(List<T> list, List<T> defaultList) {
        return list == null?defaultList:list;
    }

    public static <E> List<E> intersection(List<? extends E> list1, List<? extends E> list2) {
        ArrayList result = new ArrayList();
        List smaller = list1;
        List larger = list2;
        if(list1.size() > list2.size()) {
            smaller = list2;
            larger = list1;
        }

        HashSet hashSet = new HashSet(smaller);
        Iterator i$ = larger.iterator();

        while(i$.hasNext()) {
            Object e = i$.next();
            if(hashSet.contains(e)) {
                result.add(e);
                hashSet.remove(e);
            }
        }

        return result;
    }





    public static <E> List<E> union(List<? extends E> list1, List<? extends E> list2) {
        ArrayList result = new ArrayList(list1);
        result.addAll(list2);
        return result;
    }


    public static boolean isEqualList(Collection<?> list1, Collection<?> list2) {
        if(list1 == list2) {
            return true;
        } else if(isNotEmpty(list1) &&isNotEmpty(list2) && list1.size() == list2.size()) {
            Iterator it1 = list1.iterator();
            Iterator it2 = list2.iterator();
            Object obj1 = null;
            Object obj2 = null;

            while(true) {
                if(it1.hasNext() && it2.hasNext()) {
                    obj1 = it1.next();
                    obj2 = it2.next();
                    if(obj1 == null) {
                        if(obj2 == null) {
                            continue;
                        }
                    } else if(obj1.equals(obj2)) {
                        continue;
                    }

                    return false;
                }

                return !it1.hasNext() && !it2.hasNext();
            }
        } else {
            return false;
        }
    }

    public static int hashCodeForList(Collection<?> list) {
        if(list == null) {
            return 0;
        } else {
            int hashCode = 1;

            Object obj;
            for(Iterator it = list.iterator(); it.hasNext(); hashCode = 31 * hashCode + (obj == null?0:obj.hashCode())) {
                obj = it.next();
            }

            return hashCode;
        }
    }

    public static <E> List<E> retainAll(Collection<E> collection, Collection<?> retain) {
        ArrayList list = new ArrayList(Math.min(collection.size(), retain.size()));
        Iterator i$ = collection.iterator();

        while(i$.hasNext()) {
            Object obj = i$.next();
            if(retain.contains(obj)) {
                list.add(obj);
            }
        }

        return list;
    }

    public static <E> List<E> removeAll(Collection<E> collection, Collection<?> remove) {
        ArrayList list = new ArrayList();
        Iterator i$ = collection.iterator();

        while(i$.hasNext()) {
            Object obj = i$.next();
            if(!remove.contains(obj)) {
                list.add(obj);
            }
        }

        return list;
    }

    public static <E> List<E> synchronizedList(List<E> list) {
        return Collections.synchronizedList(list);
    }


    public static <T> List<List<T>> partition(List<T> list, int size) {
        if(list == null) {
            throw new NullPointerException("List must not be null");
        } else if(size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0");
        } else {
            return new Lists.Partition(list, size);
        }
    }

    private static class Partition<T> extends AbstractList<List<T>> {
        private final List<T> list;
        private final int size;

        private Partition(List<T> list, int size) {
            this.list = list;
            this.size = size;
        }

        @Override
        public List<T> get(int index) {
            int listSize = this.size();
            if(listSize < 0) {
                throw new IllegalArgumentException("negative size: " + listSize);
            } else if(index < 0) {
                throw new IndexOutOfBoundsException("Index " + index + " must not be negative");
            } else if(index >= listSize) {
                throw new IndexOutOfBoundsException("Index " + index + " must be less than size " + listSize);
            } else {
                int start = index * this.size;
                int end = Math.min(start + this.size, this.list.size());
                return this.list.subList(start, end);
            }
        }

        @Override
        public int size() {
            return (this.list.size() + this.size - 1) / this.size;
        }

        @Override
        public boolean isEmpty() {
            return this.list.isEmpty();
        }
    }

    private static final class CharSequenceAsList extends AbstractList<Character> {
        private final CharSequence sequence;

        public CharSequenceAsList(CharSequence sequence) {
            this.sequence = sequence;
        }

        @Override
        public Character get(int index) {
            return Character.valueOf(this.sequence.charAt(index));
        }

        @Override
        public int size() {
            return this.sequence.length();
        }
    }


}
