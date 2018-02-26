package com.github.xphsc.bean.register;


import com.github.xphsc.bean.BeanByRefMapper;
import com.github.xphsc.collect.Maps;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ${huipei.x} on 2017/6/13
 */
public class RegisterBean {
    private static final Map<String, Map<String, Field>> cacheFields = new ConcurrentHashMap<String, Map<String, Field>>();
    private static final Set<Class> basicClass = new HashSet<Class>();
    static {
        basicClass.add(Integer.class);
        basicClass.add(Character.class);
        basicClass.add(Byte.class);
        basicClass.add(Float.class);
        basicClass.add(Double.class);
        basicClass.add(Boolean.class);
        basicClass.add(Long.class);
        basicClass.add(Short.class);
        basicClass.add(String.class);
        basicClass.add(BigDecimal.class);
    }

    public static <T> T getBeanOfMapper(Object orig, Class<T> targetClass){
        try {
            T target = targetClass.newInstance();
            /** 获取源对象的所有变量 */
            Field[] fields = orig.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (RegisterBean.isStatic(field)) {continue;}
                /** 获取目标方法 */
                Field targetField = getTargetField(targetClass, field.getName());
                if (targetField == null) {continue;}
                Object value =RegisterBean.getFiledValue(field, orig);
                if (value == null) {continue;}
                Class type1 = field.getType();
                Class type2 = targetField.getType();
                //两个类型是否相同
                boolean sameType = type1.equals(type2);
                if (RegisterBean.isBasicType(type1)) {
                    if (sameType){RegisterBean.setFieldValue(targetField, target, value);}
                } else if (value instanceof Map && Map.class.isAssignableFrom(type2)){//对map
                    RegisterBean.registerMap((Map) value, field, targetField, target);
                } else if (value instanceof Set && Set.class.isAssignableFrom(type2)) {//对set
                    RegisterBean.registerCollection((Collection) value, field, targetField, target);
                } else if (value instanceof List && List.class.isAssignableFrom(type2)) {//对list
                    RegisterBean.registerCollection((Collection) value, field, targetField, target);
                } else if (value instanceof Enum && Enum.class.isAssignableFrom(type2)) {//对enum
                    RegisterBean.registerEnum((Enum) value, field, targetField, target);
                } else if (value instanceof java.util.Date &&
                        java.util.Date.class.isAssignableFrom(type2)) {//对日期类型，不处理如joda包之类的扩展时间，不处理calendar
                    RegisterBean.setDate((Date) value, targetField, type2, target, sameType);
                }
            }
            return target;
        } catch (Throwable t) {
            throw new RuntimeException(t.getMessage());
        }
    }
    /**
     * set Map
     * @param value
     * @param origField
     * @param targetField
     * @param targetObject
     * @param <T>
     */
    public static <T> void registerMap(Map value, Field origField, Field targetField, T targetObject) throws IllegalAccessException, InstantiationException{
        Type origType = origField.getGenericType();
        Type targetType = targetField.getGenericType();
        if (origType instanceof ParameterizedType && targetType instanceof ParameterizedType) {//泛型类型
            ParameterizedType origParameterizedType = (ParameterizedType)origType;
            Type[] origTypes = origParameterizedType.getActualTypeArguments();
            ParameterizedType targetParameterizedType = (ParameterizedType)targetType;
            Type[] targetTypes = targetParameterizedType.getActualTypeArguments();
            if (origTypes != null && origTypes.length == 2 && targetTypes != null && targetTypes.length == 2) {//正常泛型,查看第二个泛型是否不为基本类型
                Class clazz = (Class)origTypes[1];
                if (!isBasicType(clazz) && !clazz.equals(targetTypes[1])) {//如果不是基本类型并且泛型不一致，则需要继续转换
                    Set<Map.Entry> entries = value.entrySet();
                    Map targetMap = value.getClass().newInstance();
                    for (Map.Entry entry : entries) {
                        targetMap.put(entry.getKey(),getBeanOfMapper(entry.getValue(), (Class) targetTypes[1]));
                    }
                    setFieldValue(targetField, targetObject, targetMap);
                    return;
                }
            }
        }
        setFieldValue(targetField, targetObject, value);
    }

    /**
     * set
     * @param value
     * @param origField
     * @param targetField
     * @param targetObject
     * @param <T>
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> void  registerCollection(Collection value, Field origField, Field targetField, T targetObject) throws IllegalAccessException, InstantiationException{
        Type origType = origField.getGenericType();
        Type targetType = targetField.getGenericType();
        if (origType instanceof ParameterizedType && targetType instanceof ParameterizedType) {//泛型类型
            ParameterizedType origParameterizedType = (ParameterizedType)origType;
            Type[] origTypes = origParameterizedType.getActualTypeArguments();
            ParameterizedType targetParameterizedType = (ParameterizedType)targetType;
            Type[] targetTypes = targetParameterizedType.getActualTypeArguments();
            if (origTypes != null && origTypes.length == 1 && targetTypes != null && targetTypes.length == 1) {//正常泛型,查看第二个泛型是否不为基本类型
                Class clazz = (Class)origTypes[0];
                if (!isBasicType(clazz) && !clazz.equals(targetTypes[0])) {//如果不是基本类型并且泛型不一致，则需要继续转换
                    Collection collection = value.getClass().newInstance();
                    for (Object obj : value) {
                        collection.add(BeanByRefMapper.copyByRefMapper(obj, (Class) targetTypes[0]));
                    }
                    setFieldValue(targetField, targetObject, collection);
                    return;
                }
            }
        }
        setFieldValue(targetField, targetObject, value);
    }

    /**
     * set
     * @param value
     * @param origField
     * @param targetField
     * @param targetObject
     * @param <T>
     */
    public static <T> void registerEnum(Enum value, Field origField, Field targetField, T targetObject) throws Exception{
        if (origField.equals(targetField)) {
            setFieldValue(targetField, targetObject, value);
        } else {
            //枚举类型都具有一个static修饰的valueOf方法
            Method method = targetField.getType().getMethod("valueOf", String.class);
            setFieldValue(targetField, targetObject, method.invoke(null, value.toString()));
        }
    }
    /**
     * set
     * @param field
     * @param obj
     * @param value
     * @throws IllegalAccessException
     */
    public static void setFieldValue(Field field, Object obj, Object value) throws IllegalAccessException {

        boolean access = field.isAccessible();
        try {

            field.setAccessible(true);
            field.set(obj, value);
        } finally {

            field.setAccessible(access);
        }
    }

    /**
     *
     * @param value
     * @param targetField
     * @param targetFieldType
     * @param targetObject
     * @param <T>
     */
    public static <T> void setDate(Date value, Field targetField, Class targetFieldType, T targetObject, boolean sameType) throws IllegalAccessException {
        Date date = null;
        if (sameType) {
            date = value;
        } else if (targetFieldType.equals(java.sql.Date.class)) {
            date = new java.sql.Date(value.getTime());
        } else if (targetFieldType.equals(java.util.Date.class)) {
            date = new Date(value.getTime());
        } else if (targetFieldType.equals(java.sql.Timestamp.class)) {
            date = new java.sql.Timestamp(value.getTime());
        }
        RegisterBean.setFieldValue(targetField, targetObject, date);
    }

    /**
     * 获取适配方法
     * @param clazz
     * @param fieldName
     * @return
     */
    public static Field getTargetField(Class clazz, String fieldName) {
        String classKey = clazz.getName();
        Map<String, Field> fieldMap = cacheFields.get(classKey);
        if (fieldMap == null) {
            fieldMap = Maps.newHashMap();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (RegisterBean.isStatic(field)) {continue;}
                fieldMap.put(field.getName(), field);
            }
            cacheFields.put(classKey, fieldMap);
        }
        return fieldMap.get(fieldName);
    }
    /**
     * 获取字段值
     * @param field
     * @param obj
     * @return
     */
    public static Object getFiledValue(Field field, Object obj) throws IllegalAccessException {
        boolean access = field.isAccessible();
        try {
            field.setAccessible(true);
            return field.get(obj);
        } finally {

            field.setAccessible(access);
        }
    }
    /**
     *
     * @param clazz
     * @return
     */
    public static boolean isBasicType(Class clazz) {
        return clazz.isPrimitive() || basicClass.contains(clazz);
    }

    /**
     *
     * @param field
     * @return
     */
    public static boolean isStatic(Field field) {
        return (8 & field.getModifiers()) == 8;
    }
}
