package com.github.xphsc.lang;
import com.github.xphsc.util.PropertyUtil;
import com.github.xphsc.util.StringUtil;



/**
 * Created by ${huipei.x} on 2017-6-19.
 */
public class EnumUtil {

    private EnumUtil() {
        throw new AssertionError("No " + this.getClass().getName() + " instances for you!");
    }


    public static <E extends Enum<?>, T> E getEnumByPropertyValueIgnoreCase(Class<E> enumClass, String propertyName, T specifiedValue) {
        return getEnumByPropertyValue(enumClass, propertyName, specifiedValue, true);
    }

    public static <E extends Enum<?>, T> E getEnumByPropertyValue(Class<E> enumClass, String propertyName, T specifiedValue) {
        return getEnumByPropertyValue(enumClass, propertyName, specifiedValue, false);
    }

    private static <E extends Enum<?>, T> E getEnumByPropertyValue(Class<E> enumClass, String propertyName, T specifiedValue, boolean ignoreCase) {
        Validator.notNull(enumClass, "enumClass can\'t be null!", new Object[0]);
        Validator.notBlank(propertyName, "propertyName can\'t be null/empty!", new Object[0]);
        Enum[] enumConstants = (Enum[])enumClass.getEnumConstants();
        Enum[] messagePattern = enumConstants;
        int var6 = enumConstants.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            Enum e = messagePattern[var7];
            Object propertyValue = PropertyUtil.getProperty(e, propertyName);
            if(isEquals(propertyValue, specifiedValue, ignoreCase)) {
                return (E) e;
            }
        }

        return null;
    }

    private static <T> boolean isEquals(Object propertyValue, T specifiedValue, boolean ignoreCase) {
        if(propertyValue != null && specifiedValue != null) {
            if(propertyValue == specifiedValue) {
                return true;
            } else {
                String propertyValueString = propertyValue.toString();
                String specifiedValueString = specifiedValue.toString();
                return ignoreCase? StringUtil.equalsIgnoreCase(propertyValueString, specifiedValueString): StringUtil.equals(propertyValueString, specifiedValueString);
            }
        } else {
            return propertyValue == specifiedValue;
        }
    }

}
