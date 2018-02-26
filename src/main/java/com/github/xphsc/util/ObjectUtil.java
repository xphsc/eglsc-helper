package com.github.xphsc.util;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import com.github.xphsc.lang.Validator;
import com.github.xphsc.exception.UtilException;
import com.github.xphsc.lang.Emptys;
import com.github.xphsc.lang.text.StrBuilder;
import com.github.xphsc.mutable.Integers;


/**
 * Created by ${huipei.x} on 2017-5-31.
 */
public class ObjectUtil {

    public static final ObjectUtil.Null NULL = new ObjectUtil.Null();
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



    public static boolean isNullOrEmptyString(Object o)
    {
        if(o == null)
        { return true;}
        if(o instanceof String)
        {
            String str = (String)o;
            if(str.length() == 0)
            {  return true;}
        }
        return false;
    }

    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object o)
    {
        if(o == null)
        { return true;}
        if(o instanceof String)
        {
            if(((String)o).length() == 0)
            { return true;}
        } else
        if(o instanceof Collection)
        {
            if(((Collection)o).isEmpty())
            {return true;}
        } else
        if(o.getClass().isArray())
        {
            if(Array.getLength(o) == 0)
            {  return true;}
        } else
        if(o instanceof Map)
        {
            if(((Map)o).isEmpty())
            {  return true;}
        } else
        {
            return false;
        }
        return false;
    }


    public static boolean isNotEmpty(Object c)
            throws IllegalArgumentException
    {
        return !isEmpty(c);
    }

    public static boolean isAnyNull(Object...objects) {
        if (objects == null) {
            return true;
        }

        for (Object object : objects) {
            if (object == null) {
                return true;
            }
        }
        return false;
    }


    public static int nullNum(Object...objects) {
        if (objects == null) {
            return -1;
        }

        int result = 0;
        for (Object object : objects) {
            if (object == null) {
                result++;
            }
        }
        return result;
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
        Validator.notNull(object, "object can\'t be null!", new Object[0]);
        return isArray(object) && object.getClass().getComponentType().isPrimitive();
    }

    public static <T> T defaultIfNull(T object, T defaultValue) {
        return object != null?object:defaultValue;
    }

    public static <T> Object firstNonNull(T... values) {
        if(values != null) {
            Object[] arr$ = values;
            int len$ = values.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Object val = arr$[i$];
                if(val != null) {
                    return val;
                }
            }
        }

        return null;
    }

    /** @deprecated */
    @Deprecated
    public static boolean equals(Object object1, Object object2) {
        return object1 == object2?true:(object1 != null && object2 != null?object1.equals(object2):false);
    }

    public static boolean notEqual(Object object1, Object object2) {
        return !equals(object1, object2);
    }

    /** @deprecated */
    @Deprecated
    public static int hashCode(Object obj) {
        return obj == null?0:obj.hashCode();
    }

    /** @deprecated */
    @Deprecated
    public static int hashCodeMulti(Object... objects) {
        int hash = 1;
        if(objects != null) {
            Object[] arr$ = objects;
            int len$ = objects.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Object object = arr$[i$];
                int tmpHash = hashCode(object);
                hash = hash * 31 + tmpHash;
            }
        }

        return hash;
    }

    public static String identityToString(Object object) {
        if(object == null) {
            return null;
        } else {
            StringBuilder builder = new StringBuilder();
            identityToString(builder, object);
            return builder.toString();
        }
    }

    public static void identityToString(Appendable appendable, Object object) throws IOException {
        if(object == null) {
            throw new NullPointerException("Cannot get the toString of a null identity");
        } else {
            appendable.append(object.getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(object)));
        }
    }

    public static void identityToString(StrBuilder builder, Object object) {
        if(object == null) {
            throw new NullPointerException("Cannot get the toString of a null identity");
        } else {
            builder.append(object.getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(object)));
        }
    }

    public static void identityToString(StringBuffer buffer, Object object) {
        if(object == null) {
            throw new NullPointerException("Cannot get the toString of a null identity");
        } else {
            buffer.append(object.getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(object)));
        }
    }

    public static void identityToString(StringBuilder builder, Object object) {
        if(object == null) {
            throw new NullPointerException("Cannot get the toString of a null identity");
        } else {
            builder.append(object.getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(object)));
        }
    }

    public static String toString(Object object) {
        return toString(object, Emptys.EMPTY_STRING);
    }


    public static String toString(Object object, String nullStr) {
        if (object == null) {
            return nullStr;
        } else if (object instanceof Object[]) {
            return Arrays.deepToString((Object[]) object);
        } else if (object instanceof int[]) {
            return Arrays.toString((int[]) object);
        } else if (object instanceof long[]) {
            return Arrays.toString((long[]) object);
        } else if (object instanceof short[]) {
            return Arrays.toString((short[]) object);
        } else if (object instanceof byte[]) {
            return Arrays.toString((byte[]) object);
        } else if (object instanceof double[]) {
            return Arrays.toString((double[]) object);
        } else if (object instanceof float[]) {
            return Arrays.toString((float[]) object);
        } else if (object instanceof char[]) {
            return Arrays.toString((char[]) object);
        } else if (object instanceof boolean[]) {
            return Arrays.toString((boolean[]) object);
        } else {
            return object.toString();
        }
    }


    public static <T extends Comparable<? super T>> Comparable min(T... values) {
        Comparable result = null;
        if(values != null) {
            Comparable[] arr$ = values;
            int len$ = values.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Comparable value = arr$[i$];
                if(compare(value, result, true) < 0) {
                    result = value;
                }
            }
        }

        return result;
    }

    public static <T extends Comparable<? super T>> Comparable max(T... values) {
        Comparable result = null;
        if(values != null) {
            Comparable[] arr$ = values;
            int len$ = values.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Comparable value = arr$[i$];
                if(compare(value, result, false) > 0) {
                    result = value;
                }
            }
        }

        return result;
    }

    public static <T extends Comparable<? super T>> int compare(T c1, T c2) {
        return compare(c1, c2, false);
    }

    public static <T extends Comparable<? super T>> int compare(T c1, T c2, boolean nullGreater) {
        return c1 == c2?0:(c1 == null?(nullGreater?1:-1):(c2 == null?(nullGreater?-1:1):c1.compareTo(c2)));
    }

    public static <T extends Comparable<? super T>> Comparable median(T... items) {
        Validator.notEmpty(items);
        Validator.noNullElements(items);
        TreeSet sort = new TreeSet();
        Collections.addAll(sort, items);
        Comparable result = (Comparable)sort.toArray()[(sort.size() - 1) / 2];
        return result;
    }

    public static <T> Object median(Comparator<T> comparator, T... items) {
        Validator.notEmpty(items, "null/empty items", new Object[0]);
        Validator.noNullElements(items);
        Validator.notNull(comparator, "null comparator", new Object[0]);
        TreeSet sort = new TreeSet(comparator);
        Collections.addAll(sort, items);
        Object result = sort.toArray()[(sort.size() - 1) / 2];
        return result;
    }

    public static <T> Object mode(T... items) {
        if(ArrayUtil.isNotEmpty(items)) {
            HashMap occurrences = new HashMap(items.length);
            Object[] result = items;
            int max = items.length;

            for(int i$ = 0; i$ < max; ++i$) {
                Object e = result[i$];
                Integers cmp = (Integers)occurrences.get(e);
                if(cmp == null) {
                    occurrences.put(e, new Integers(1));
                } else {
                    cmp.increment();
                }
            }

            Object var7 = null;
            max = 0;
            Iterator var8 = occurrences.entrySet().iterator();

            while(var8.hasNext()) {
                Map.Entry var9 = (Map.Entry)var8.next();
                int var10 = ((Integers)var9.getValue()).intValue();
                if(var10 == max) {
                    var7 = null;
                } else if(var10 > max) {
                    max = var10;
                    var7 = var9.getKey();
                }
            }

            return var7;
        } else {
            return null;
        }
    }

    public static <T> Object clone(T obj) {
        if(!(obj instanceof Cloneable)) {
            return null;
        } else {
            Object result;
            if(obj.getClass().isArray()) {
                Class checked = obj.getClass().getComponentType();
                if(!checked.isPrimitive()) {
                    result = ((Object[])((Object[])obj)).clone();
                } else {
                    int length = Array.getLength(obj);
                    result = Array.newInstance(checked, length);

                    while(length-- > 0) {
                        Array.set(result, length, Array.get(obj, length));
                    }
                }
            } else {
                try {
                    Method var7 = obj.getClass().getMethod("clone", new Class[0]);
                    result = var7.invoke(obj, new Object[0]);
                } catch (NoSuchMethodException var4) {
                    throw new UtilException("Cloneable type " + obj.getClass().getName() + " has no clone method", var4);
                } catch (IllegalAccessException var5) {
                    throw new UtilException("Cannot clone Cloneable type " + obj.getClass().getName(), var5);
                } catch (InvocationTargetException var6) {
                    throw new UtilException("Exception cloning Cloneable type " + obj.getClass().getName(), var6.getCause());
                }
            }

            return result;
        }
    }

    public static <T> Object cloneIfPossible(T obj) {
        Object clone = clone(obj);
        return clone == null?obj:clone;
    }

    public static boolean CONST(boolean v) {
        return v;
    }

    public static byte CONST(byte v) {
        return v;
    }

    public static byte CONST_BYTE(int v) throws IllegalArgumentException {
        if(v >= -128 && v <= 127) {
            return (byte)v;
        } else {
            throw new IllegalArgumentException("Supplied value must be a valid byte literal between -128 and 127: [" + v + "]");
        }
    }

    public static char CONST(char v) {
        return v;
    }

    public static short CONST(short v) {
        return v;
    }

    public static short CONST_SHORT(int v) throws IllegalArgumentException {
        if(v >= -32768 && v <= 32767) {
            return (short)v;
        } else {
            throw new IllegalArgumentException("Supplied value must be a valid byte literal between -32768 and 32767: [" + v + "]");
        }
    }

    public static int CONST(int v) {
        return v;
    }

    public static long CONST(long v) {
        return v;
    }

    public static float CONST(float v) {
        return v;
    }

    public static double CONST(double v) {
        return v;
    }

    public static <T> T CONST(T v) {
        return v;
    }

    public static class Null implements Serializable {
        private static final long serialVersionUID = 7092611880189329093L;

        Null() {
        }

        private Object readResolve() {
            return ObjectUtil.NULL;
        }
    }


}
