package com.github.xphsc.util;

import com.github.xphsc.collect.Maps;
import com.github.xphsc.lang.Validator;
import com.github.xphsc.exception.UtilException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ${huipei.x} on 2017-6-12.
 */
public class PropertyUtil {


    private PropertyUtil() {
        throw new AssertionError("No " + this.getClass().getName() + " instances for you!");
    }

    public static void copyProperties(Object toObj, Object fromObj, String... includePropertyNames) {
        Validator.notNull(toObj, "toObj [destination bean] not specified!", new Object[0]);
        Validator.notNull(fromObj, "fromObj [origin bean] not specified!", new Object[0]);
        if(Validator.isNullOrEmpty(includePropertyNames)) {
            try {
                PropertyUtil.copyProperties(toObj, fromObj);
            } catch (Exception var8) {
                throw new UtilException(var8);
            }
        } else {
            String[] e = includePropertyNames;
            int var4 = includePropertyNames.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String propertyName = e[var5];
                Object value = getProperty(fromObj, propertyName);
                setProperty(toObj, propertyName, value);
            }

        }
    }

    public static Map<String, Object> describe(Object bean, String... propertyNames) {
        Validator.notNull(bean, "bean can\'t be null!", new Object[0]);
        if(Validator.isNullOrEmpty(propertyNames)) {
            try {
                return PropertyUtil.describe(bean);
            } catch (Exception var7) {
                throw new UtilException(var7);
            }
        } else {
            LinkedHashMap map = Maps.newLinkedHashMap(propertyNames.length);
            String[] var3 = propertyNames;
            int var4 = propertyNames.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String propertyName = var3[var5];
                map.put(propertyName, getProperty(bean, propertyName));
            }

            return map;
        }
    }

    public static void setProperty(Object bean, String propertyName, Object value) {
        Validator.notNull(bean, "bean can\'t be null!", new Object[0]);
        Validator.notBlank(propertyName, "propertyName can\'t be null!", new Object[0]);

        try {
            PropertyUtil.setProperty(bean, propertyName, value);
        } catch (Exception var4) {
            throw new UtilException(var4);
        }
    }

    public static void setPropertyIfValueNotNullOrEmpty(Object bean, String propertyName, Object value) {
        if(Validator.isNotNullOrEmpty(value)) {
            setProperty(bean, propertyName, value);
        }

    }

    public static void setPropertyIfValueNotNull(Object bean, String propertyName, Object value) {
        if(null != value) {
            setProperty(bean, propertyName, value);
        }

    }

    public static <T> T getProperty(Object bean, String propertyName) {
        Validator.notNull(bean, "bean can\'t be null!", new Object[0]);
        Validator.notBlank(propertyName, "propertyName can\'t be blank!", new Object[0]);

        try {
            return (T) PropertyUtil.getProperty(bean, propertyName);
        } catch (Exception var3) {
            throw new UtilException(var3);
        }
    }

    public static String getProperty(String key, String defautValue) {
        return getProperty(key, key, defautValue);
    }

    public static String getProperty(String dKey, String shellKey, String defautValue) {
        String value = System.getProperty(dKey);
        if(value == null || value.length() == 0) {
            value = System.getenv(shellKey);
            if(value == null || value.length() == 0) {
                value = defautValue;
            }
        }

        return value;
    }

}
