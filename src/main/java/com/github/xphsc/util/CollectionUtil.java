package com.github.xphsc.util;



import com.github.xphsc.lang.Validator;
import com.github.xphsc.bean.Closure;
import com.github.xphsc.bean.PropertyComparator;
import com.github.xphsc.collect.Iterables;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by ${huipei.x} on 2017-5-25.
 */
public class CollectionUtil {
    private CollectionUtil() {
    }


    @SafeVarargs
    public static <T> Collection<T> union(Collection<T> coll1, Collection<T> coll2, Collection... otherColls) {
        Collection union = union(coll1, coll2);
        Collection[] arr$ = otherColls;
        int len$ = otherColls.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Collection coll = arr$[i$];
            union = union(union, coll);
        }

        return union;
    }

    public static <T> List<T> toList(Enumeration<T> enumeration) {
        return (List)(null == enumeration?Collections.emptyList():Collections.list(enumeration));
    }

    public static <T> List<T> toList(Collection<T> collection) {
        return (List)(null == collection?Collections.emptyList():(collection instanceof List?(List)collection:new ArrayList(collection)));
    }

    @SafeVarargs
    public static <T> List<T> toList(T... arrays) {
        return (List)(Validator.isNullOrEmpty(arrays)?Collections.emptyList():new ArrayList(Arrays.asList(arrays)));
    }

    @SafeVarargs
    public static <T> Collection<T> intersection(Collection<T> coll1, Collection<T> coll2, Collection... otherColls) {
        Collection intersection = intersection(coll1, coll2);
        if(isEmpty(intersection)) {
            return intersection;
        } else {
            Collection[] arr$ = otherColls;
            int len$ = otherColls.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Collection coll = arr$[i$];
                intersection = intersection(intersection, coll);
                if(isEmpty(intersection)) {
                    return intersection;
                }
            }

            return intersection;
        }
    }



    public static boolean containsAny(Collection<?> coll1, Collection<?> coll2) {
        if(!isEmpty(coll1) && !isEmpty(coll2)) {
            Iterator i$;
            Object object;
            if(coll1.size() < coll2.size()) {
                i$ = coll1.iterator();

                while(i$.hasNext()) {
                    object = i$.next();
                    if(coll2.contains(object)) {
                        return true;
                    }
                }
            } else {
                i$ = coll2.iterator();

                while(i$.hasNext()) {
                    object = i$.next();
                    if(coll1.contains(object)) {
                        return true;
                    }
                }
            }

            return false;
        } else {
            return false;
        }
    }

    public static <T> Map<T, Integer> countMap(Collection<T> collection) {
        HashMap countMap = new HashMap();
        Iterator i$ = collection.iterator();

        while(i$.hasNext()) {
            Object t = i$.next();
            Integer count = (Integer)countMap.get(t);
            if(null == count) {
                countMap.put(t, Integer.valueOf(1));
            } else {
                countMap.put(t, Integer.valueOf(count.intValue() + 1));
            }
        }

        return countMap;
    }

    public static <T> String join(Iterable<T> iterable, String conjunction) {
        return null == iterable?null:join(iterable.iterator(), conjunction);
    }

    public static <T> String join(Iterator<T> iterator, String conjunction) {
        if(null == iterator) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            boolean isFirst = true;

            while(iterator.hasNext()) {
                if(isFirst) {
                    isFirst = false;
                } else {
                    sb.append(conjunction);
                }

                Object item = iterator.next();
                if(ArrayUtil.isArray(item)) {
                    sb.append(ArrayUtil.join(ArrayUtil.wrap(item), conjunction));
                } else if(item instanceof Iterable) {
                    sb.append(join((Iterable)item, conjunction));
                } else if(item instanceof Iterator) {
                    sb.append(join((Iterator)item, conjunction));
                } else {
                    sb.append(item);
                }
            }

            return sb.toString();
        }
    }



    public static <T> List<T> popPart(Stack<T> surplusAlaDatas, int partSize) {
        if(surplusAlaDatas != null && surplusAlaDatas.size() > 0) {
            ArrayList currentAlaDatas = new ArrayList();
            int size = surplusAlaDatas.size();
            int i;
            if(size > partSize) {
                for(i = 0; i < partSize; ++i) {
                    currentAlaDatas.add(surplusAlaDatas.pop());
                }
            } else {
                for(i = 0; i < size; ++i) {
                    currentAlaDatas.add(surplusAlaDatas.pop());
                }
            }

            return currentAlaDatas;
        } else {
            return null;
        }
    }

    public static <T> List<T> popPart(Deque<T> surplusAlaDatas, int partSize) {
        if(surplusAlaDatas != null && surplusAlaDatas.size() > 0) {
            ArrayList currentAlaDatas = new ArrayList();
            int size = surplusAlaDatas.size();
            int i;
            if(size > partSize) {
                for(i = 0; i < partSize; ++i) {
                    currentAlaDatas.add(surplusAlaDatas.pop());
                }
            } else {
                for(i = 0; i < size; ++i) {
                    currentAlaDatas.add(surplusAlaDatas.pop());
                }
            }

            return currentAlaDatas;
        } else {
            return null;
        }
    }

    public static <T, K> HashMap<T, K> newHashMap() {
        return new HashMap();
    }

    public static <T, K> HashMap<T, K> newHashMap(boolean isSorted) {
        return (HashMap)(isSorted?new LinkedHashMap():new HashMap());
    }

    public static <T, K> HashMap<T, K> newHashMap(int size) {
        return new HashMap((int)((double)size / 0.75D));
    }

    @SafeVarargs
    public static <T> HashSet<T> newHashSet(T... ts) {
        HashSet set = new HashSet(Math.max((int)((float)ts.length / 0.75F) + 1, 16));
        Object[] arr$ = ts;
        int len$ = ts.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Object t = arr$[i$];
            set.add(t);
        }

        return set;
    }

    @SafeVarargs
    public static <T> HashSet<T> newHashSet(boolean isSorted, T... ts) {
        int initialCapacity = Math.max((int)((float)ts.length / 0.75F) + 1, 16);
        Object set = isSorted?new LinkedHashSet(initialCapacity):new HashSet(initialCapacity);
        Object[] arr$ = ts;
        int len$ = ts.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Object t = arr$[i$];
            ((HashSet)set).add(t);
        }

        return (HashSet)set;
    }

    public static <T> HashSet<T> newHashSet(Collection<T> collection) {
        return new HashSet(collection);
    }

    public static <T> HashSet<T> newHashSet(boolean isSorted, Collection<T> collection) {
        return (HashSet)(isSorted?new LinkedHashSet():new HashSet(collection));
    }

    @SafeVarargs
    public static <T> ArrayList<T> newArrayList(T... values) {
        ArrayList arrayList = new ArrayList(values.length);
        Object[] arr$ = values;
        int len$ = values.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Object t = arr$[i$];
            arrayList.add(t);
        }

        return arrayList;
    }

    public static <T> ArrayList<T> newArrayList(Collection<T> collection) {
        return new ArrayList(collection);
    }

    public static <T> ArrayList<T> distinct(Collection<T> collection) {
        return isEmpty(collection)?new ArrayList():(collection instanceof Set?new ArrayList(collection):new ArrayList(new LinkedHashSet(collection)));
    }

    public static <T> List<T> sub(List<T> list, int start, int end) {
        if(list != null && !list.isEmpty()) {
            if(start < 0) {
                start = 0;
            }

            if(end < 0) {
                end = 0;
            }

            int size;
            if(start > end) {
                size = start;
                start = end;
                end = size;
            }

            size = list.size();
            if(end > size) {
                if(start >= size) {
                    return null;
                }

                end = size;
            }

            return list.subList(start, end);
        } else {
            return null;
        }
    }

    public static <T> List<T> sub(Collection<T> list, int start, int end) {
        return list != null && !list.isEmpty()?sub((List)(new ArrayList(list)), start, end):null;
    }

    public static <T> List<List<T>> split(Collection<T> collection, int size) {
        ArrayList result = new ArrayList();
        ArrayList subList = new ArrayList(size);

        Object t;
        for(Iterator i$ = collection.iterator(); i$.hasNext(); subList.add(t)) {
            t = i$.next();
            if(subList.size() > size) {
                result.add(subList);
                subList = new ArrayList(size);
            }
        }

        result.add(subList);
        return result;
    }




    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isEmpty(Iterable<?> iterable) {
        return null == iterable || isEmpty(iterable.iterator());
    }

    public static boolean isEmpty(Iterator<?> Iterator) {
        return null == Iterator || !Iterator.hasNext();
    }

    public static boolean isEmpty(Enumeration<?> enumeration) {
        return null == enumeration || !enumeration.hasMoreElements();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static <T> boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static boolean isNotEmpty(Iterable<?> iterable) {
        return null != iterable && isNotEmpty(iterable.iterator());
    }

    public static boolean isNotEmpty(Iterator<?> Iterator) {
        return null != Iterator && Iterator.hasNext();
    }

    public static boolean isNotEmpty(Enumeration<?> enumeration) {
        return null != enumeration && enumeration.hasMoreElements();
    }

    public static boolean sizeIsEmpty(Object object) {
        if(object == null) {
            return true;
        } else if(object instanceof Collection) {
            return ((Collection)object).isEmpty();
        } else if(object instanceof Iterable) {
            return Iterables.isEmpty((Iterable) object);
        } else if(object instanceof Map) {
            return ((Map)object).isEmpty();
        } else if(object instanceof Object[]) {
            return ((Object[])((Object[])object)).length == 0;
        } else if(object instanceof Iterator) {
            return !((Iterator)object).hasNext();
        } else if(object instanceof Enumeration) {
            return !((Enumeration)object).hasMoreElements();
        } else {
            try {
                return Array.getLength(object) == 0;
            } catch (IllegalArgumentException var2) {
                throw new IllegalArgumentException("Unsupported object type: " + object.getClass().getName());
            }
        }
    }


    public static <T, K> Map<T, K> zip(Collection<T> keys, Collection<K> values) {
        if(!isEmpty(keys) && !isEmpty(values)) {
            ArrayList keyList = new ArrayList(keys);
            ArrayList valueList = new ArrayList(values);
            int size = Math.min(keys.size(), values.size());
            HashMap map = new HashMap((int)((double)size / 0.75D));

            for(int i = 0; i < size; ++i) {
                map.put(keyList.get(i), valueList.get(i));
            }

            return map;
        } else {
            return null;
        }
    }

    public static <T, K> HashMap<T, K> toMap(Collection<Map.Entry<T, K>> entryCollection) {
        HashMap map = new HashMap();
        Iterator i$ = entryCollection.iterator();

        while(i$.hasNext()) {
            Map.Entry entry = (Map.Entry)i$.next();
            map.put(entry.getKey(), entry.getValue());
        }

        return map;
    }

    public static <T> TreeSet<T> toTreeSet(Collection<T> collection, Comparator<T> comparator) {
        TreeSet treeSet = new TreeSet(comparator);
        Iterator i$ = collection.iterator();

        while(i$.hasNext()) {
            Object t = i$.next();
            treeSet.add(t);
        }

        return treeSet;
    }

    public static <T> List<T> sort(Collection<T> collection, Comparator<T> comparator) {
        ArrayList list = new ArrayList(collection);
        Collections.sort(list, comparator);
        return list;
    }

    public static <E> Enumeration<E> asEnumeration(final Iterator<E> iter) {
        return new Enumeration() {
            public boolean hasMoreElements() {
                return iter.hasNext();
            }

            public E nextElement() {
                return iter.next();
            }
        };
    }

    public static <E> Iterator<E> asIterator(final Enumeration<E> e) {
        return new Iterator() {
            public boolean hasNext() {
                return e.hasMoreElements();
            }

            public E next() {
                return e.nextElement();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static <T> Collection<T> addAll(Collection<T> collection, Iterator<T> iterator) {
        if(null != collection && null != iterator) {
            while(iterator.hasNext()) {
                collection.add(iterator.next());
            }
        }

        return collection;
    }

    public static <T> Collection<T> addAll(Collection<T> collection, Iterable<T> iterable) {
        return addAll(collection, iterable.iterator());
    }

    public static <T> Collection<T> addAll(Collection<T> collection, Enumeration<T> enumeration) {
        if(null != collection && null != enumeration) {
            while(enumeration.hasMoreElements()) {
                collection.add(enumeration.nextElement());
            }
        }

        return collection;
    }



    public static <T> T getFirst(Iterable<T> iterable) {
        return null != iterable?getFirst(iterable.iterator()):null;
    }

    public static <T> T getFirst(Iterator<T> iterator) {
        return null != iterator && iterator.hasNext()?iterator.next():null;
    }



    public static <T> void forEach(Iterable<T> iterable, String propertyName, Object propertyValue) {
        if(Validator.isNotNullOrEmpty(iterable)) {
            Iterables.forEach(iterable, (Closure<? super T>) new PropertyComparator(propertyName, propertyValue));
        }

    }


    public interface KVConsumer<K, V> {
        void accept(K var1, V var2, int var3);
    }

    public interface Consumer<T> {
        void accept(T var1, int var2);
    }

}
