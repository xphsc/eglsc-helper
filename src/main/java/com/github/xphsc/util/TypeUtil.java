package com.github.xphsc.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by ${huipei.x} on 2017-6-1.
 */
public class TypeUtil {

    public TypeUtil() {
    }

    public static Class<?> getClass(Type type) {
        if(null != type) {
            if(type instanceof Class) {
                return (Class)type;
            }

            if(type instanceof ParameterizedType) {
                return (Class)((ParameterizedType)type).getRawType();
            }
        }

        return null;
    }

    public static Type getType(Field field) {
        if(null == field) {
            return null;
        } else {
            Object type = field.getGenericType();
            if(null == type) {
                type = field.getType();
            }

            return (Type)type;
        }
    }

    public static Class<?> getClass(Field field) {
        return null == field?null:field.getType();
    }

    public static Type getFirstParamType(Method method) {
        return getParamType(method, 0);
    }

    public static Class<?> getFirstParamClass(Method method) {
        return getParamClass(method, 0);
    }

    public static Type getParamType(Method method, int index) {
        Type[] types = getParamTypes(method);
        return null != types && types.length > index?types[index]:null;
    }

    public static Class<?> getParamClass(Method method, int index) {
        Class[] classes = getParamClasses(method);
        return null != classes && classes.length > index?classes[index]:null;
    }

    public static Type[] getParamTypes(Method method) {
        return null == method?null:method.getGenericParameterTypes();
    }

    public static Class<?>[] getParamClasses(Method method) {
        return null == method?null:method.getParameterTypes();
    }

    public static Type getReturnType(Method method) {
        return null == method?null:method.getGenericReturnType();
    }

    public static Class<?> getReturnClass(Method method) {
        return null == method?null:method.getReturnType();
    }

    public static Type getTypeArgument(Class<?> clazz) {
        return getTypeArgument((Class)clazz, 0);
    }

    public static Type getTypeArgument(Class<?> clazz, int index) {
        Object type = clazz;
        type = clazz.getGenericSuperclass();

        return getTypeArgument((Type)type, index);
    }

    public static Type getTypeArgument(Type type) {
        return getTypeArgument((Type)type, 0);
    }

    public static Type getTypeArgument(Type type, int index) {
        Type[] typeArguments = getTypeArguments(type);
        return null != typeArguments && typeArguments.length > index?typeArguments[index]:null;
    }

    public static Type[] getTypeArguments(Type type) {
        if(type instanceof ParameterizedType) {
            ParameterizedType genericSuperclass = (ParameterizedType)type;
            return genericSuperclass.getActualTypeArguments();
        } else {
            return null;
        }
    }
}
