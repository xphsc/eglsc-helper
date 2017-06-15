package com.eglsc.util;

import com.eglsc.Validator;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.Validate;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ${huipei.x} on 2017-5-31.
 */
public class ObjectUtil extends ObjectUtils{

    private ObjectUtil() {
    }

    public static boolean equal(Object obj1, Object obj2) {
        return obj1 != null?obj1.equals(obj2):obj2 == null;
    }

    public static int length(Object obj) {
        if(obj == null) {
            return 0;
        } else if(obj instanceof CharSequence) {
            return ((CharSequence)obj).length();
        } else if(obj instanceof Collection) {
            return ((Collection)obj).size();
        } else if(obj instanceof Map) {
            return ((Map)obj).size();
        } else {
            int count;
            if(obj instanceof Iterator) {
                Iterator var3 = (Iterator)obj;
                count = 0;

                while(var3.hasNext()) {
                    ++count;
                    var3.next();
                }

                return count;
            } else if(!(obj instanceof Enumeration)) {
                return obj.getClass().isArray()? Array.getLength(obj):-1;
            } else {
                Enumeration enumeration = (Enumeration)obj;
                count = 0;

                while(enumeration.hasMoreElements()) {
                    ++count;
                    enumeration.nextElement();
                }

                return count;
            }
        }
    }

    public static boolean contains(Object obj, Object element) {
        if(obj == null) {
            return false;
        } else if(obj instanceof String) {
            return element == null?false:((String)obj).contains(element.toString());
        } else if(obj instanceof Collection) {
            return ((Collection)obj).contains(element);
        } else if(obj instanceof Map) {
            return ((Map)obj).values().contains(element);
        } else {
            Object var7;
            if(obj instanceof Iterator) {
                Iterator var6 = (Iterator)obj;

                do {
                    if(!var6.hasNext()) {
                        return false;
                    }

                    var7 = var6.next();
                } while(!equal(var7, element));

                return true;
            } else if(obj instanceof Enumeration) {
                Enumeration var5 = (Enumeration)obj;

                do {
                    if(!var5.hasMoreElements()) {
                        return false;
                    }

                    var7 = var5.nextElement();
                } while(!equal(var7, element));

                return true;
            } else {
                if(obj.getClass().isArray()) {
                    int len = Array.getLength(obj);

                    for(int i = 0; i < len; ++i) {
                        Object o = Array.get(obj, i);
                        if(equal(o, element)) {
                            return true;
                        }
                    }
                }

                return false;
            }
        }
    }


    public static boolean isArray(Object obj) {
        return obj != null && obj.getClass().isArray();
    }

    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(Object obj) {
        return obj == null?true:(obj.getClass().isArray()?Array.getLength(obj) == 0:(obj instanceof CharSequence?((CharSequence)obj).length() == 0:(obj instanceof Collection?((Collection)obj).isEmpty():(obj instanceof Map?((Map)obj).isEmpty():false))));
    }

    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    public static boolean isValidIfNumber(Object obj) {
        if(obj != null && obj instanceof Number) {
            if(obj instanceof Double) {
                if(((Double)obj).isInfinite() || ((Double)obj).isNaN()) {
                    return false;
                }
            } else if(obj instanceof Float && (((Float)obj).isInfinite() || ((Float)obj).isNaN())) {
                return false;
            }
        }

        return true;
    }

    public static <T> T defaultIfNullOrEmpty(T object, T defaultValue) {
        return Validator.isNotNullOrEmpty(object)?object:defaultValue;
    }


    public static boolean isPrimitiveArray(Object object) {
        Validate.notNull(object, "object can\'t be null!", new Object[0]);
        return isArray(object) && object.getClass().getComponentType().isPrimitive();
    }
}
