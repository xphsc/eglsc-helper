package com.github.xphsc.collect;

import com.github.xphsc.lang.Validator;
import com.github.xphsc.bean.comparator.Transformer;
import com.github.xphsc.util.ObjectUtil;
import com.github.xphsc.util.StringUtil;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

/**
 * Created by ${huipei.x} on 2017-5-31.
 */
public class Maps {

    public Maps() {
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

    public static <K, V> Map<K, V> toMap(K key, V value) {
        LinkedHashMap map = new LinkedHashMap();
        map.put(key, value);
        return map;
    }

    public static <K, V> Map<K, V> toMap(K key1, V value1, K key2, V value2) {
        Map map = toMap(key1, value1);
        map.put(key2, value2);
        return map;
    }

    public static <K, V, I, J> Map<I, J> toMap(Map<K, V> inputMap, Transformer<K, I> keyTransformer, Transformer<V, J> valueTransformer) {
        if(Validator.isNullOrEmpty(inputMap)) {
            return Collections.emptyMap();
        } else {
            LinkedHashMap returnMap = new LinkedHashMap(inputMap.size());
            Iterator var4 = inputMap.entrySet().iterator();

            while(var4.hasNext()) {
                Map.Entry entry = (Map.Entry)var4.next();
                Object key = entry.getKey();
                Object value = entry.getValue();
                returnMap.put(null == keyTransformer?key:keyTransformer.transform((K) key), null == valueTransformer?value:valueTransformer.transform((V) value));
            }

            return returnMap;
        }
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static Map<String,Object> beanToMap(Object object){
        if(object==null){
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (!key.equals("class")) {
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(object);

                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    public static Object mapToBean(Map map,Object object){
        if(map == null || object==null){
            return null;
        }
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (map.containsKey(key)) {
                    Object value = map.get(key);
                    Method setter = property.getWriteMethod();
                    setter.invoke(object, value);
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;
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

    public static <K> Integer getInteger(Map<? super K, ?> map, K key) {
        Number value = getNumber(map, key);
        return value == null?null:(value instanceof Integer?(Integer)value:Integer.valueOf(value.intValue()));
    }

    public static <K> Double getDouble(Map<? super K, ?> map, K key) {
        Number value = getNumber(map, key);
        return value == null?null:(value instanceof Double?(Double)value:Double.valueOf(value.doubleValue()));
    }

    public static <K> Long getLong(Map<? super K, ?> map, K key) {
        Number value = getNumber(map, key);
        return value == null?null:(value instanceof Long?(Long)value:Long.valueOf(value.longValue()));
    }

    public static <K> Short getShort(Map<? super K, ?> map, K key, Short defaultValue) {
        Short value = getShort(map, key);
        if(value == null) {
            value = defaultValue;
        }

        return value;
    }

    public static <K> Short getShort(Map<? super K, ?> map, K key) {
        Number value = getNumber(map, key);
        return value == null?null:(value instanceof Short?(Short)value:Short.valueOf(value.shortValue()));
    }

    public static <K> Byte getByte(Map<? super K, ?> map, K key) {
        Number value = getNumber(map, key);
        return value == null?null:(value instanceof Byte?(Byte)value:Byte.valueOf(value.byteValue()));
    }

    public static <K> Float getFloat(Map<? super K, ?> map, K key) {
        Number value = getNumber(map, key);
        return value == null?null:(value instanceof Float?(Float)value:Float.valueOf(value.floatValue()));
    }

    public static <K> Float getFloat(Map<? super K, ?> map, K key, Float defaultValue) {
        Float value = getFloat(map, key);
        if(value == null) {
            value = defaultValue;
        }

        return value;
    }

    public static <K> Double getDouble(Map<? super K, ?> map, K key, Double defaultValue) {
        Double value = getDouble(map, key);
        if(value == null) {
            value = defaultValue;
        }

        return value;
    }

    public static <K> Boolean getBoolean(Map<? super K, ?> map, K key) {
        if(map != null) {
            Object value = map.get(key);
            if(value != null) {
                if(value instanceof Boolean) {
                    return (Boolean)value;
                }

                if(value instanceof String) {
                    return Boolean.valueOf((String)value);
                }

                if(value instanceof Number) {
                    Number n = (Number)value;
                    return n.intValue() != 0?Boolean.TRUE:Boolean.FALSE;
                }
            }
        }

        return null;
    }


    public static <K> int getIntValue(Map<? super K, ?> map, K key) {
        Integer integerObject = getInteger(map, key);
        return integerObject == null?0:integerObject.intValue();
    }

    public static <K> boolean getBooleanValue(Map<? super K, ?> map, K key) {
        return Boolean.TRUE.equals(getBoolean(map, key));
    }

    public static <K> long getLongValue(Map<? super K, ?> map, K key) {
        Long longObject = getLong(map, key);
        return longObject == null?0L:longObject.longValue();
    }


    public static <K> byte getByteValue(Map<? super K, ?> map, K key) {
        Byte byteObject = getByte(map, key);
        return byteObject == null?0:byteObject.byteValue();
    }

    public static <K> short getShortValue(Map<? super K, ?> map, K key) {
        Short shortObject = getShort(map, key);
        return shortObject == null?0:shortObject.shortValue();
    }





    public static <K> float getFloatValue(Map<? super K, ?> map, K key) {
        Float floatObject = getFloat(map, key);
        return floatObject == null?0.0F:floatObject.floatValue();
    }

    public static <K> byte getByteValue(Map<? super K, ?> map, K key, byte defaultValue) {
        Byte byteObject = getByte(map, key);
        return byteObject == null?defaultValue:byteObject.byteValue();
    }

    public static <K> short getShortValue(Map<? super K, ?> map, K key, short defaultValue) {
        Short shortObject = getShort(map, key);
        return shortObject == null?defaultValue:shortObject.shortValue();
    }


    public static <K> float getFloatValue(Map<? super K, ?> map, K key, float defaultValue) {
        Float floatObject = getFloat(map, key);
        return floatObject == null?defaultValue:floatObject.floatValue();
    }


    public static <K> Number getNumber(Map<? super K, ?> map, K key) {
        if(map != null) {
            Object value = map.get(key);
            if(value != null) {
                if(value instanceof Number) {
                    return (Number)value;
                }

                if(value instanceof String) {
                    try {
                        String e = (String)value;
                        return NumberFormat.getInstance().parse(e);
                    } catch (ParseException var4) {
                        ;
                    }
                }
            }
        }

        return null;
    }

    public static <K> String getString(Map<? super K, ?> map, K key) {
        Number value = getNumber(map, key);
        return value == null?null:String.valueOf(value);
    }

    public boolean containsKey(Object key) {
        return key instanceof Number?this.containsKey(((Number) key).intValue()):false;
    }

    public static boolean containsKey(Map<?, ?> map, String key) {
        return Maps.isNotEmpty(map)&&StringUtil.isNotBlank(key)&&map.containsKey(key)?true:false;
    }

    public static <K, V> Map<String, V> toCamelCaseMap(Map<K, V> map) {
        HashMap map2 = newHashMap(map.size());
        Iterator i = map.entrySet().iterator();

        while(i.hasNext()) {
            Map.Entry entry = (Map.Entry)i.next();
            Object key = entry.getKey();
            map2.put(StringUtil.toCamelCase(ObjectUtil.isEmpty(key)?null:key.toString()), entry.getValue());
        }

        return map2;
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
            if(!StringUtil.isBlank(value)) {
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
        Validator.notNull(map, "map can\'t be null!", new Object[0]);
        Validator.notNull(value, "value can\'t be null!", new Object[0]);
        Integer v = (Integer)map.get(key);
        map.put(key, Integer.valueOf(null == v?value.intValue():value.intValue() + v.intValue()));
        return map;
    }

    public static <K, V> Map<K, List<V>> putMultiValue(Map<K, List<V>> map, K key, V value) {
        Validator.notNull(map, "map can\'t be null!", new Object[0]);
        List list = (List) ObjectUtil.defaultIfNull(map.get(key), new ArrayList());
        list.add(value);
        map.put(key, list);
        return map;
    }
    public static <T, K> HashMap<T, K> newHashMap() {
        return new HashMap();
    }
    public static <K, V> HashMap<K, V> newHashMap(int expectedSize) {
        return new HashMap(toInitialCapacity(expectedSize));
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap(int expectedSize) {
        return new LinkedHashMap(toInitialCapacity(expectedSize));
    }

    private static int toInitialCapacity(int size) {
        Validator.isTrue(size >= 0, "size :[%s] must >=0", (long)size);
        return (int)((float)size / 0.75F) + 1;
    }
}
