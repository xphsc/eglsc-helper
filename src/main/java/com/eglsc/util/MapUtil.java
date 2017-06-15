package com.eglsc.util;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by ${huipei.x} on 2017-5-31.
 */
public class MapUtil {

    public MapUtil() {
    }

    public static Map<String, Object> toMap(Object obj) {
        HashMap map = null;
        if(obj == null) {
            return map;
        } else {
            map = new HashMap();

            try {
                BeanInfo e = Introspector.getBeanInfo(obj.getClass());
                PropertyDescriptor[] propertyDescriptors = e.getPropertyDescriptors();
                PropertyDescriptor[] var4 = propertyDescriptors;
                int var5 = propertyDescriptors.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    PropertyDescriptor property = var4[var6];
                    String key = property.getName();
                    if(!key.equals("class")) {
                        Method getter = property.getReadMethod();
                        Object value = getter.invoke(obj, new Object[0]);
                        map.put(key, value);
                    }
                }
            } catch (Exception var11) {
                map = null;
            }

            return map;
        }
    }

    public static Map<String, String> toStringMap(Object obj) {
        HashMap map = null;
        if(obj == null) {
            return map;
        } else {
            map = new HashMap();

            try {
                BeanInfo e = Introspector.getBeanInfo(obj.getClass());
                PropertyDescriptor[] propertyDescriptors = e.getPropertyDescriptors();
                PropertyDescriptor[] var4 = propertyDescriptors;
                int var5 = propertyDescriptors.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    PropertyDescriptor property = var4[var6];
                    String key = property.getName();
                    if(!key.equals("class")) {
                        Method getter = property.getReadMethod();
                        map.put(key, getter.invoke(obj, new Object[0]).toString());
                    }
                }
            } catch (Exception var10) {
                map = null;
            }

            return map;
        }
    }

    public static int getInt(Map<Object, Object> dataMap, Object key) {
        Object data = dataMap.get(key);

        int ret;
        try {
            ret = Integer.parseInt(data.toString());
        } catch (NumberFormatException var5) {
            ret = 0;
        }

        return ret;
    }

    public static double getDouble(Map<Object, Object> dataMap, Object key) {
        Object data = dataMap.get(key);

        double ret;
        try {
            ret = Double.parseDouble(data.toString());
        } catch (NumberFormatException var6) {
            ret = 0.0D;
        }

        return ret;
    }

    public static long getLong(Map<Object, Object> dataMap, Object key) {
        Object data = dataMap.get(key);

        long ret;
        try {
            ret = Long.parseLong(data.toString());
        } catch (NumberFormatException var6) {
            ret = 0L;
        }

        return ret;
    }

    public static String getString(Map<Object, Object> dataMap, Object key) {
        Object data = dataMap.get(key);
        return data.toString();
    }

    public static boolean check(Map<String, Boolean> data, String key) {
        boolean success;
        if(!data.containsKey(key)) {
            success = false;
        } else {
            success = ((Boolean)data.get(key)).booleanValue();
        }

        return success;
    }

    public static String linkString(Map<String, String> params) {
        String ret = "";
        ArrayList keys = new ArrayList(params.keySet());
        Collections.sort(keys);

        for(int i = 0; i < keys.size(); ++i) {
            String key = (String)keys.get(i);
            String value = (String)params.get(key);
            if(!StringUtils.isBlank(value)) {
                if(i == keys.size() - 1) {
                    ret = ret + key + "=" + value;
                } else {
                    ret = ret + key + "=" + value + "&";
                }
            }
        }

        return ret;
    }


    public static <K, V> void putIfValueNotNull(Map<K, V> map, K key, V value) {
        if(null != map && null != value) {
            map.put(key, value);
        }

    }

    public static <K, V> void putAllIfNotNull(Map<K, V> map, Map<? extends K, ? extends V> m) {
        if(null != map && null != m) {
            map.putAll(m);
        }

    }

    public static <K> Map<K, Integer> putSumValue(Map<K, Integer> map, K key, Integer value) {
        Validate.notNull(map, "map can\'t be null!", new Object[0]);
        Validate.notNull(value, "value can\'t be null!", new Object[0]);
        Integer v = (Integer)map.get(key);
        map.put(key, Integer.valueOf(null == v?value.intValue():value.intValue() + v.intValue()));
        return map;
    }

    public static <K, V> Map<K, List<V>> putMultiValue(Map<K, List<V>> map, K key, V value) {
        Validate.notNull(map, "map can\'t be null!", new Object[0]);
        List list = (List) ObjectUtils.defaultIfNull(map.get(key), new ArrayList());
        list.add(value);
        map.put(key, list);
        return map;
    }

    public static <K, V> HashMap<K, V> newHashMap(int expectedSize) {
        return new HashMap(toInitialCapacity(expectedSize));
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap(int expectedSize) {
        return new LinkedHashMap(toInitialCapacity(expectedSize));
    }

    private static int toInitialCapacity(int size) {
        Validate.isTrue(size >= 0, "size :[%s] must >=0", (long)size);
        return (int)((float)size / 0.75F) + 1;
    }
}
