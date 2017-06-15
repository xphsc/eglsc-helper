package com.eglsc.util;

import com.eglsc.exception.BeanException;
import com.eglsc.Validator;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.*;
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
        Validate.notNull(toObj, "toObj [destination bean] not specified!", new Object[0]);
        Validate.notNull(fromObj, "fromObj [origin bean] not specified!", new Object[0]);
        if(Validator.isNullOrEmpty(includePropertyNames)) {
            try {
                PropertyUtils.copyProperties(toObj, fromObj);
            } catch (Exception var8) {
                throw new BeanException(var8);
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
        Validate.notNull(bean, "bean can\'t be null!", new Object[0]);
        if(Validator.isNullOrEmpty(propertyNames)) {
            try {
                return PropertyUtils.describe(bean);
            } catch (Exception var7) {
                throw new BeanException(var7);
            }
        } else {
            LinkedHashMap map = MapUtil.newLinkedHashMap(propertyNames.length);
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
        Validate.notNull(bean, "bean can\'t be null!", new Object[0]);
        Validate.notBlank(propertyName, "propertyName can\'t be null!", new Object[0]);

        try {
            PropertyUtils.setProperty(bean, propertyName, value);
        } catch (Exception var4) {
            throw new BeanException(var4);
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
        Validate.notNull(bean, "bean can\'t be null!", new Object[0]);
        Validate.notBlank(propertyName, "propertyName can\'t be blank!", new Object[0]);

        try {
            return (T) PropertyUtils.getProperty(bean, propertyName);
        } catch (Exception var3) {
            throw new BeanException(var3);
        }
    }



}
