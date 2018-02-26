package com.github.xphsc.collect;


import com.github.xphsc.lang.Validator;

import java.util.*;

/**
 * Created by ${huipei.x} on 2017-5-31.
 */
public class Sets {

    public static <T> HashSet<T> newHashSet(Collection<T> collection) {
        return new HashSet(collection);
    }
    public static <T> HashSet<T> newHashSet(boolean isSorted, Collection<T> collection) {
        return (HashSet)(isSorted?new LinkedHashSet():new HashSet(collection));
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
    @SafeVarargs
    public static <T> Set<T> toSet(T... arrays) {
        return (Set)(Validator.isNullOrEmpty(arrays)?Collections.emptySet():new LinkedHashSet(Arrays.asList(arrays)));
    }
    public static <T> Set<T> emptyIfNull(Set<T> set) {
        return set == null? (Set<T>) Collections.emptySet() :set;
    }
    public static boolean isEqualSet(Collection<?> set1, Collection<?> set2) {
        return set1 == set2?true:(set1 != null && set2 != null && set1.size() == set2.size()?set1.containsAll(set2):false);
    }
}
