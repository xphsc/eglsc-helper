package com.eglsc.bean;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ${huipei.x} on 2017/6/13
 */
public class SimpleToStringParent {
    @Override
    public String toString() {
        try {
            StringBuilder stringBuilder = new StringBuilder("{");
            Field[] fields = this.getClass().getDeclaredFields();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Field field : fields) {
                Object value = getFiledValue(field, this);
                if (value == null) continue;
                if (value instanceof Date) {
                    value = dateFormat.format((Date)value);
                }
                stringBuilder.append(field.getName()).append("=").append(value).append(",");
            }
            String returnValue = stringBuilder.toString();
            if (returnValue.length() > 1) {
                returnValue = returnValue.substring(0, returnValue.length() - 1);
            }
            return this.getClass().getSimpleName() + returnValue + "}";
        } catch (Exception e) {
            // skip
        }
        return this.getClass().getSimpleName() + "{}";
    }

    /**
     * 获取属性值
     * @param field
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    private Object getFiledValue(Field field, Object obj) throws IllegalAccessException {
        //获取原有的访问权限
        boolean access = field.isAccessible();
        try {
            //设置可访问的权限
            field.setAccessible(true);
            return field.get(obj);
        } finally {
            //恢复访问权限
            field.setAccessible(access);
        }
    }
}
